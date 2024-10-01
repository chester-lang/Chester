package chester.propagator

import chester.error.TyckProblem
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.{Get, LocalCtx, Reporter, SymbolTable, TyckResult, TyckResult0, TyckState, VectorReporter}
import chester.utils.propagator.*
import chester.utils.{MutBox, reuse}

import scala.language.implicitConversions

def resolve(expr: Expr, localCtx: LocalCtx)(using reporter: Reporter[TyckProblem]): Expr = {
  val result = SimpleDesalt.desugarUnwrap(expr) match {
    case opseq: OpSeq => {
      val result = resolveOpSeq(reporter, localCtx.ctx.operators, opseq)
      result
    }
    case default => default
  }
  reuse(expr, result)
}


object BaseTycker {
  type Literals = Expr & (IntegerLiteral | RationalLiteral | StringLiteral | SymbolLiteral)

  case class Unify(lhs: UniqIdOf[Term], rhs: UniqIdOf[Term], meta: Option[ExprMeta], uniqId: UniqId = UniqId.generate) extends Propagator[Ck] {
    override val readingCells = Set(lhs, rhs)
    override val writingCells = Set(lhs, rhs)
    override val zonkingCells = Set(lhs, rhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val lhs = state.read(this.lhs)
      val rhs = state.read(this.rhs)
      if (lhs.isDefined && rhs.isDefined && lhs.get == rhs.get) return true
      return false
    }

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val lhs = state.read(this.lhs)
      val rhs = state.read(this.rhs)
      (lhs, rhs) match {
        case (Some(lhs), Some(rhs)) if lhs == rhs => return ZonkResult.Done
        case (Some(lhs), None) => {
          state.fill(this.rhs, lhs)
          return ZonkResult.Done
        }
        case (None, Some(rhs)) => {
          state.fill(this.lhs, rhs)
          return ZonkResult.Done
        }
        case _ => return ZonkResult.NotYet
      }
    }
  }

  case class LiteralType(x: Literals, ty: UniqIdOf[Term], meta: Option[ExprMeta], uniqId: UniqId = UniqId.generate) extends Propagator[Ck] {
    override val readingCells = Set(ty)
    override val writingCells = Set(ty)
    override val zonkingCells = Set(ty)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      if(state.notStable(ty)) return false
      val ty_ = state.read(this.ty).get
      val result = x match {
        case IntegerLiteral(_, _) => ty_ == IntegerType
        case RationalLiteral(_, _) => ty_ == RationalType
        case StringLiteral(_, _) => ty_ == StringType
        case SymbolLiteral(_, _) => ty_ == SymbolType
      }
      if(result) {
        return true
      } else {
        ???
      }
    }

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult =
      state.fill(ty, x match {
        case IntegerLiteral(_, _) => IntegerType
        case RationalLiteral(_, _) => RationalType
        case StringLiteral(_, _) => StringType
        case SymbolLiteral(_, _) => SymbolType
      })
      ZonkResult.Done
  }

  case class IsEffects(effects: UniqIdOf[Effects], uniqId: UniqId = UniqId.generate) extends Propagator[Ck] {
    override val zonkingCells = Set(effects)
    override def run(using state: StateAbility[Ck], more: Ck): Boolean = state.isStable(effects)
    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      state.fill(effects, Effects.Empty)
      ZonkResult.Done
    }
  }

  def check(expr: Expr, ty: UniqIdOf[Term], effects: UniqIdOf[Effects], localCtx: LocalCtx = LocalCtx.Empty)(using ck: Ck, state: StateAbility[Ck]): UniqIdOf[Cell[Term]] = state.toId {
    resolve(expr, localCtx) match {
      case expr@IntegerLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty, meta))
        AbstractIntTerm.from(value)
      }
      case expr@RationalLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty, meta))
        RationalTerm(value)
      }
      case expr@StringLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty, meta))
        StringTerm(value)
      }
      case expr@SymbolLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty, meta))
        SymbolTerm(value)
      }
      case expr: Expr => ???
    }
  }
}

type Ck = Get[TyckProblem, CkState]

given ckToReport(using ck: Ck): Reporter[TyckProblem] = ck.reporter

case class CkState(
                      symbols: SymbolTable = Set.empty,
                    )

object Cker {
  def check(expr: Expr,ty: Option[Term] = None, effects: Option[Effects] = None, localCtx: LocalCtx = LocalCtx.Empty): TyckResult[CkState, Judge] = {
    val reporter = new VectorReporter[TyckProblem]
    implicit val get: Ck = new Get(reporter, new MutBox(CkState()))
    implicit val able: StateAbility[Ck] = new StateCells[Ck]()
    val ty1: UniqIdOf[Term] = ty match {
      case Some(ty) => {
        val cell = LiteralCell(UniqId.generate, ty)
        able.addCell(cell)
        cell.uniqId
      }
      case None => {
        val cell = OnceCell(UniqId.generate, None)
        able.addCell(cell)
        cell.uniqId
      }
    }
    val effects1: UniqIdOf[Effects] = effects match {
      case Some(effects) => {
        val cell = LiteralCell(UniqId.generate, effects)
        able.addCell(cell)
        cell.uniqId
      }
      case None => {
        val cell = OnceCell(UniqId.generate, None)
        able.addCell(cell)
        cell.uniqId
      }
    }
    able.addPropagator(BaseTycker.IsEffects(effects1))
    val wellTyped = BaseTycker.check(expr, ty1, effects1)
    able.naiveZonk(Vector(ty1, effects1, wellTyped))
    TyckResult0(get.getState, Judge(able.read(wellTyped).get, able.read(ty1).get, able.read(effects1).get), reporter.getReports)
  }
}
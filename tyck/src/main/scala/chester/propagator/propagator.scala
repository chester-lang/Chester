package chester.propagator

import chester.error.TyckProblem
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.{LocalCtx, Reporter}
import chester.utils.propagator.*
import chester.utils.reuse

type UniqOfOr[T] = UniqIdOf[Cell[T]] | T

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

  case class Unify(uniqId: UniqId, lhs: UniqIdOf[Term], rhs: UniqIdOf[Term], meta: Option[ExprMeta]) extends Propagator[Reporter[TyckProblem]] {
    override val readingCells = Set(lhs, rhs)
    override val writingCells = Set(lhs, rhs)
    override val zonkingCells = Set(lhs, rhs)

    override def run(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): Boolean = {
      val lhs = state.read(this.lhs)
      val rhs = state.read(this.rhs)
      if (lhs.isDefined && rhs.isDefined && lhs.get == rhs.get) return true
      return false
    }

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): ZonkResult = {
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

  case class LiteralType(uniqId: UniqId, x: Literals, ty: UniqIdOf[Term], meta: Option[ExprMeta]) extends Propagator[Reporter[TyckProblem]] {
    override val readingCells = Set(ty)
    override val writingCells = Set(ty)
    override val zonkingCells = Set(ty)

    override def run(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): Boolean = {
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

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): ZonkResult =
      state.fill(ty, x match {
        case IntegerLiteral(_, _) => IntegerType
        case RationalLiteral(_, _) => RationalType
        case StringLiteral(_, _) => StringType
        case SymbolLiteral(_, _) => SymbolType
      })
      ZonkResult.Done
  }

  def check(expr: Expr, ty: UniqIdOf[Term], effects: UniqIdOf[Effects], localCtx: LocalCtx = LocalCtx.Empty)(using reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqOfOr[Term] =
    resolve(expr, localCtx) match {
      case expr@IntegerLiteral(value, meta) => {
        state.addPropagator(LiteralType(UniqId.generate, expr, ty, meta))
        AbstractIntTerm.from(value)
      }
      case expr@RationalLiteral(value, meta) => {
        state.addPropagator(LiteralType(UniqId.generate, expr, ty, meta))
        RationalTerm(value)
      }
      case expr@StringLiteral(value, meta) => {
        state.addPropagator(LiteralType(UniqId.generate, expr, ty, meta))
        StringTerm(value)
      }
      case expr@SymbolLiteral(value, meta) => {
        state.addPropagator(LiteralType(UniqId.generate, expr, ty, meta))
        SymbolTerm(value)
      }
      case expr: Expr => ???
    }
}
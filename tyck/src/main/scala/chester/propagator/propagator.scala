package chester.propagator

import chester.error.TyckProblem
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.{LocalCtx, Reporter}
import chester.utils.propagator.*
import chester.utils.reuse

trait Tycker[T <: Expr] {
  def check(expr: T, ty: UniqIdOf[Term], effects: UniqIdOf[Effects], localCtx: LocalCtx = LocalCtx.Empty)(using reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqIdOf[Cell[Judge]]
}

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


object BaseTycker extends Tycker[Expr] {
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

    override def naiveZonk(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): Boolean = {
      val lhs = state.read(this.lhs)
      val rhs = state.read(this.rhs)
      (lhs, rhs) match {
        case (Some(lhs), Some(rhs)) if lhs == rhs => return true
        case (Some(lhs), None) => {
          state.fill(this.rhs, lhs)
          return true
        }
        case (None, Some(rhs)) => {
          state.fill(this.lhs, rhs)
          return true
        }
        case _ => return false
      }
    }
  }

  case class LiteralType(uniqId: UniqId, x: Literals, ty: UniqIdOf[Term], meta: Option[ExprMeta]) extends Propagator[Reporter[TyckProblem]] {
    override val readingCells = Set(ty)
    override val writingCells = Set(ty)
    override val zonkingCells = Set(ty)

    override def run(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): Boolean = ???

    override def naiveZonk(using state: StateAbility[Reporter[TyckProblem]], more: Reporter[TyckProblem]): Boolean = ???
  }

  def check(expr: Expr, ty: UniqIdOf[Term], effects: UniqIdOf[Effects], localCtx: LocalCtx = LocalCtx.Empty)(using reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqIdOf[Cell[Judge]] =
    resolve(expr, localCtx) match {
      case IntegerLiteral(value, meta) => ???
      case expr: Expr => ???
    }
}
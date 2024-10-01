package chester.propagator

import chester.error.TyckProblem
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.{LocalCtx, Reporter}
import chester.utils.propagator.*
import chester.utils.reuse

trait Tycker[T<:Expr] {
  def check(expr: T, ty: UniqIdOf[Term], effects: UniqIdOf[Effects], localCtx: LocalCtx = LocalCtx.Empty)(using reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqIdOf[Cell[Judge]]
}

def resolve(expr: Expr,  localCtx: LocalCtx)(using reporter: Reporter[TyckProblem]): Expr = {
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
  def check(expr: Expr, ty: UniqIdOf[Term], effects: UniqIdOf[Effects], localCtx: LocalCtx = LocalCtx.Empty)(using reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqIdOf[Cell[Judge]] =
    resolve(expr, localCtx) match {
      case IntegerLiteral(value, meta) => ???
      case expr: Expr => ???
    }
}
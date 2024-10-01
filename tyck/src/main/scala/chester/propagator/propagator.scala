package chester.propagator

import chester.error.TyckProblem
import chester.syntax.concrete.Expr
import chester.syntax.core.*
import chester.tyck.{LocalCtx, Reporter}
import chester.utils.propagator.{Cell, StateAbility}

trait Tycker[T<:Expr] {
  def check(expr: T, ty: Option[Term], effects: Option[Effects] = None, ctx: LocalCtx = LocalCtx.Empty)(reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqIdOf[Cell[Judge]]
}


object BaseTycker extends Tycker[Expr] {
  def check(expr: Expr, ty: Option[Term], effects: Option[Effects] = None, ctx: LocalCtx = LocalCtx.Empty)(reporter: Reporter[TyckProblem], state: StateAbility[Reporter[TyckProblem]]): UniqIdOf[Cell[Judge]] = {
    ???
  }
}
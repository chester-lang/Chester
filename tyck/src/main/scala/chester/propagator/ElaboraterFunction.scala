package chester.propagator

import chester.syntax.concrete.*
import chester.syntax.core.*

trait ElaboraterFunction extends ProvideCtx with Elaborater {
  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term]
}

trait ProvideElaboraterFunction extends ElaboraterFunction {
  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = ???
}
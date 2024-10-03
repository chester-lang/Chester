package chester.propagator

import chester.syntax.concrete.*
import chester.syntax.core.*

trait ElaboraterFunction extends ProvideCtx with Elaborater {
  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term]
}

trait ProvideElaboraterFunction extends ElaboraterFunction {
  def elabArg(arg: Arg, effects: CellId[Effects])(using localCtx: MutableLocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[ArgTerm] = {
    require(arg.decorations.isEmpty, "decorations are not supported yet")
    val ty = elabTy(arg.ty)
    val default = arg.exprOrDefault.map(elab(_, ty, effects))
    ???
  }
  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    ???
  }

}
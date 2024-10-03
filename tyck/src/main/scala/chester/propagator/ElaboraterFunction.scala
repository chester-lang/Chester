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
    val id = UniqId.generate[LocalV]
    val bind = newLocalv(arg.name.name, ty, id, arg.meta)
    val r = Reference.create(bind, id, arg)
    state.add(parameter.references, r)
    localCtx.update(_.add(ContextItem(arg.name.name, id, bind, ty, Some(r))))
    default match {
      case Some(default) =>
        Map3(bind, ty, default)((bind, ty, default)=>ArgTerm(bind, ty, Some(default), arg.vararg))
      case None =>
        Map2(bind, ty)((bind, ty)=>ArgTerm(bind, ty, None, arg.vararg))
    }
  }
  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    ???
  }

}
package chester.syntax.core

import chester.syntax.{Id, QualifiedIDString}

case class CtxItem(name: MaybeVarCall, judge: JudgeNoEffect)

class Context(map: Map[Id, CtxItem]) {
  private val varMap: Map[VarId, Id] = map.map { case (id, CtxItem(name, _)) => name.varId -> id }
  def get(id: Id): Option[CtxItem] = map.get(id)
  def getByVarId(varId: VarId): Option[CtxItem] = varMap.get(varId).flatMap(get)
}

object Context {
  def apply(map: Map[Id, CtxItem]): Context = new Context(map)
  def builtin: Context = Context(BuiltinCtx.builtinCtx)
}

object BuiltinCtx {
  def builtinItem(id: Id, value: Term, ty: Term): (Id, CtxItem) = {
    val varId = VarId.generate
    val name = ToplevelVarCall(QualifiedIDString.from(), id, ty, varId)
    val judge = JudgeNoEffect(value, ty)
    (id, CtxItem(name, judge))
  }

  val builtinCtx: Map[Id, CtxItem] = Map(
    builtinItem("Int", IntType, Type0),
    builtinItem("Integer", IntegerType, Type0),
    builtinItem("Float", FloatType, Type0),
    builtinItem("Rational", RationalType, Type0),
    builtinItem("String", StringType, Type0),
    builtinItem("Symbol", SymbolType, Type0),
    builtinItem("List", ListF, TyToty),
  )
}

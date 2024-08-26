package chester.syntax.core

import chester.syntax.{Id, QualifiedIDString}

case class CtxItem(name: VarCall, judge: JudgeNoEffect)

type Context = Map[Id, CtxItem]

object Context {
  def builtin: Context = BuiltinCtx.builtinCtx
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
  )
}

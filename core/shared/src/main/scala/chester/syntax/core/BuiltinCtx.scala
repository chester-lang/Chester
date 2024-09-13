package chester.syntax.core

import chester.syntax.concrete.ResolvingModules
import chester.syntax.{Id, QualifiedIDString}

case class CtxItem(name: MaybeVarCall, judge: JudgeNoEffect)

trait Import

case class ImportModuleOrObject(id: QualifiedIDString, open: Boolean = false, rename: Option[Id] = None) extends Import {
  if(open) require(rename.isEmpty)
}

type Imports = Vector[Import]

object Imports {
  val Empty: Imports = Vector.empty
}

class Context(map: Map[Id, CtxItem], imports: Imports = Imports.Empty, modules: ResolvingModules = ResolvingModules.Empty) {
  private val varMap: Map[UniqId, Id] = map.map { case (id, CtxItem(name, _)) => name.uniqId -> id }

  def get(id: Id): Option[CtxItem] = map.get(id)

  def getByVarId(varId: UniqId): Option[CtxItem] = varMap.get(varId).flatMap(get)
  
  def extend(name: LocalVar): Context = {
    val id = name.id
    val item = CtxItem(name, JudgeNoEffect(name, name.ty))
    new Context(map + (id -> item), imports, modules)
  }
}

object Context {
  def apply(map: Map[Id, CtxItem]): Context = new Context(map)

  def builtin: Context = Context(BuiltinCtx.builtinCtx)
}

object BuiltinCtx {
  def builtinItem(id: Id, value: Term, ty: Term): (Id, CtxItem) = {
    val varId = UniqId.generate
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

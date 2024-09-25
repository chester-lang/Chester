package chester.syntax.core

import chester.syntax.concrete.ResolvingModules
import chester.syntax.{Name, QualifiedIDString}

case class CtxItem(name: MaybeVarCall, judge: JudgeNoEffect)

trait Import

case class ImportModuleOrObject(id: QualifiedIDString, open: Boolean = false, rename: Option[Name] = None) extends Import {
  if(open) require(rename.isEmpty)
}

type Imports = Vector[Import]

object Imports {
  val Empty: Imports = Vector.empty
}

case class Context(
  map: Map[Name, UniqId],
  varMap: Map[UniqId, CtxItem],
  imports: Imports = Imports.Empty,
  modules: ResolvingModules = ResolvingModules.Empty
) {

  def get(id: Name): Option[CtxItem] = {
    map.get(id).flatMap(varMap.get)
  }

  def getByVarId(varId: UniqId): Option[CtxItem] = varMap.get(varId)

  def extend(name: LocalVar): Context = {
    val id = name.id
    val varId = name.uniqId
    val item = CtxItem(name, JudgeNoEffect(name, name.ty))
    assert(!varMap.contains(varId))
    new Context(
      map + (id -> varId),
      varMap + (varId -> item),
      imports,
      modules
    )
  }

  // Extend or update existing entry
  def extendOrSet(name: LocalVar): Context = {
    val id = name.id
    val varId = name.uniqId
    val item = CtxItem(name, JudgeNoEffect(name, name.ty))
    new Context(
      map + (id -> varId),
      varMap + (varId -> item),
      imports,
      modules
    )
  }
}

object Context {
  def apply(map: Map[Name, UniqId], varMap: Map[UniqId, CtxItem]): Context =
    new Context(map, varMap)

  def builtin: Context = {
    val (map, varMap) = BuiltinCtx.builtinCtx
    Context(map, varMap)
  }
}

object BuiltinCtx {
  def builtinItem(id: Name, value: Term, ty: Term): (Name, UniqId, CtxItem) = {
    val varId = UniqId.generate
    val name = ToplevelVarCall(QualifiedIDString.from(), id, ty, varId)
    val judge = JudgeNoEffect(value, ty)
    val item = CtxItem(name, judge)
    (id, varId, item)
  }

  val builtinItems: Seq[(Name, UniqId, CtxItem)] = Seq(
    builtinItem("Int", IntType, Type0),
    builtinItem("Integer", IntegerType, Type0),
    builtinItem("Float", FloatType, Type0),
    builtinItem("Rational", RationalType, Type0),
    builtinItem("String", StringType, Type0),
    builtinItem("Symbol", SymbolType, Type0),
    builtinItem("List", ListF, TyToty)
  )

  val map: Map[Name, UniqId] = builtinItems.map {
    case (id, varId, _) => id -> varId
  }.toMap

  val varMap: Map[UniqId, CtxItem] = builtinItems.map {
    case (_, varId, item) => varId -> item
  }.toMap

  def builtinCtx: (Map[Name, UniqId], Map[UniqId, CtxItem]) = (map, varMap)
}

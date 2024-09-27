package chester.syntax.core

import chester.syntax.accociativity.OperatorsContext
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
  knownMap: Map[UniqId, JudgeNoEffect],
  imports: Imports = Imports.Empty,
  modules: ResolvingModules = ResolvingModules.Empty,
  operators: OperatorsContext = OperatorsContext.Default
) {

  def get(id: Name): Option[CtxItem] = {
    map.get(id).flatMap(varMap.get)
  }

  def getByVarId(varId: UniqId): Option[CtxItem] = varMap.get(varId)

  // Retrieve known judgment by UniqId
  def getKnownJudge(varId: UniqId): Option[JudgeNoEffect] = knownMap.get(varId)

  def extend(name: LocalVar): Context = {
    val id = name.id
    val varId = name.uniqId
    val item = CtxItem(name, JudgeNoEffect(name, name.ty))
    assert(!varMap.contains(varId))
    new Context(
      map + (id -> varId),
      varMap + (varId -> item),
      knownMap,
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
      knownMap,
      imports,
      modules
    )
  }

  // Add a known judgment
  def addKnownJudge(varId: UniqId, judge: JudgeNoEffect): Context = {
    new Context(
      map,
      varMap,
      knownMap + (varId -> judge),
      imports,
      modules
    )
  }
}

object Context {
  def apply(
    map: Map[Name, UniqId],
    varMap: Map[UniqId, CtxItem],
    knownMap: Map[UniqId, JudgeNoEffect]
  ): Context =
    new Context(map, varMap, knownMap)

  def builtin: Context = {
    val (map, varMap, knownMap) = BuiltinCtx.builtinCtx
    Context(map, varMap, knownMap)
  }
}

object BuiltinCtx {
  def builtinItem(id: Name, value: Term, ty: Term): (Name, UniqId, CtxItem, JudgeNoEffect) = {
    val varId = UniqId.generate
    val name = ToplevelVarCall(QualifiedIDString.from(), id, ty, varId)
    val judge = JudgeNoEffect(value, ty)
    val item = CtxItem(name, judge)
    (id, varId, item, judge)
  }

  val builtinItems: Seq[(Name, UniqId, CtxItem, JudgeNoEffect)] = Seq(
    builtinItem("Int", IntType, Type0),
    builtinItem("Integer", IntegerType, Type0),
    builtinItem("Float", FloatType, Type0),
    builtinItem("Rational", RationalType, Type0),
    builtinItem("String", StringType, Type0),
    builtinItem("Symbol", SymbolType, Type0),
    builtinItem("List", ListF, TyToty)
  )

  val map: Map[Name, UniqId] = builtinItems.map {
    case (id, varId, _, _) => id -> varId
  }.toMap

  val varMap: Map[UniqId, CtxItem] = builtinItems.map {
    case (_, varId, item, _) => varId -> item
  }.toMap

  val knownMap: Map[UniqId, JudgeNoEffect] = builtinItems.map {
    case (_, varId, _, judge) => varId -> judge
  }.toMap

  def builtinCtx: (Map[Name, UniqId], Map[UniqId, CtxItem], Map[UniqId, JudgeNoEffect]) = (map, varMap, knownMap)
}

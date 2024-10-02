package chester.propagator

import chester.propagator.BuiltIn.BuiltinItem
import chester.syntax.core.*
import chester.syntax.accociativity.OperatorsContext
import chester.syntax.concrete.{Expr, ResolvingModules}
import chester.syntax.{Name, QualifiedIDString}
import chester.utils.propagator.*

case class ContextItem(
                      name: Name,
                      uniqId: UniqIdOf[? <: MaybeVarCall],
                        ref: CellId[? <: MaybeVarCall],
                        ty: CellId[Term]
                      ) {
  def asTerm: CellId[Term] = ref.asInstanceOf[CellId[Term]]
}

object ContextItem {
  def builtin[Ck](item: BuiltinItem)(using state: StateAbility[Ck]): (TyAndVal, ContextItem) = {
    val varId = UniqId.generate[ToplevelV]
    val name = ToplevelV(QualifiedIDString.from(), item.id, item.ty, varId)
    val ty1 = state.toId(item.ty)
    (TyAndVal(ty1, state.toId(item.value)),
      ContextItem(item.id, varId, literal(name), ty1))
  }
}

case class TyAndVal(
                     ty: CellId[Term],
                     value: CellId[Term]
                   )

object TyAndVal {
  def create[Ck](ty: Term, value: Term)(using state: StateAbility[Ck]): TyAndVal = {
    TyAndVal(literal(ty), literal(value))
  }
  def create[Ck]()(using state: StateAbility[Ck]): TyAndVal = {
    TyAndVal(OnceCell.create[Term](), OnceCell.create[Term]())
  }
}

case class Reference(
                      ref: UniqIdOf[? <: MaybeVarCall],
                      definedOn: CellId[Expr],
                      referencedOn: SeqId[Expr]
                    )

object Reference {
  def create[Ck](ref: UniqIdOf[? <: MaybeVarCall], definedOn: CellIdOr[Expr])(using state: StateAbility[Ck]): Reference = {
    Reference(ref, state.toId(definedOn), CollectionCell.create[Expr])
  }
}

case class LocalCtx(
                     map: Map[Name, ContextItem] = Map.empty,
                     knownMap: Map[UniqIdOf[? <: MaybeVarCall], TyAndVal] = Map.empty,
                     imports: Imports = Imports.Empty,
                     modules: ResolvingModules = ResolvingModules.Empty,
                     operators: OperatorsContext = OperatorsContext.Default
                   ) {
  def getKnown(x: MaybeVarCall): Option[TyAndVal] = knownMap.get(x.uniqId.asInstanceOf[UniqIdOf[? <: MaybeVarCall]])

  def get(id: Name): Option[ContextItem] =
    map.get(id)

  def knownAdd(seq: Seq[(UniqIdOf[? <: MaybeVarCall], TyAndVal)]): LocalCtx = {
    val newMap = seq.foldLeft(knownMap) { (acc, item) =>
      assert(!acc.contains(item._1), s"Duplicate key ${item._1}")
      acc + item
    }
    copy(knownMap = newMap)
  }
  def add(seq: Seq[(Name, ContextItem)]): LocalCtx = {
    val newMap = seq.foldLeft(map) { (acc, item) =>
      assert(!acc.contains(item._1), s"Duplicate key ${item._1}")
      acc + item
    }
    copy(map = newMap)
  }
}

case class Global(references: SeqId[Reference])

object LocalCtx {
  def default[Ck](using state: StateAbility[Ck]): LocalCtx = {
    val items = BuiltIn.builtinItems.map(ContextItem.builtin)
    val map = items.map(item => item._2.name -> item._2).toMap
    val knownMap: Map[UniqIdOf[? <: MaybeVarCall], TyAndVal] = items.map(item => item._2.uniqId -> item._1).toMap.asInstanceOf[Map[UniqIdOf[? <: MaybeVarCall], TyAndVal]]
    LocalCtx(map, knownMap)
  }
}
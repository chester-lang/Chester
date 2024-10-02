package chester.propagator

import chester.propagator.BuiltIn.BuiltinItem
import chester.syntax.core.*
import chester.syntax.accociativity.OperatorsContext
import chester.syntax.concrete.{Expr, ResolvingModules}
import chester.syntax.{Name, QualifiedIDString}
import chester.utils.propagator.*

trait ProvideCtx extends ProvideCellId {

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
                        call: CellId[? <: MaybeVarCall],
                        id: UniqIdOf[? <: MaybeVarCall],
                        definedOn: CellId[Expr],
                        referencedOn: SeqId[Expr]
                      ) {
    def callAsMaybeVarCall: CellId[MaybeVarCall] = call.asInstanceOf[CellId[MaybeVarCall]]
  }

  case class FinalReference(
                             call: MaybeVarCall,
                             id: UniqIdOf[? <: MaybeVarCall],
                             definedOn: Expr,
                             referencedOn: Seq[Expr]
                           ) {
    def name: Name = call.name
  }

  object Reference {
    def create[T <: MaybeVarCall](call: CellIdOr[T], ref: UniqIdOf[T], definedOn: CellIdOr[Expr])(using state: StateAbility[?]): Reference = {
      Reference(state.toId(call), ref, state.toId(definedOn), CollectionCell.create[Expr])
    }
  }

  case class Imports()

  object Imports {
    val Empty: Imports = Imports()
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

    def knownAdd(id: UniqIdOf[? <: MaybeVarCall], y: TyAndVal): LocalCtx = knownAdd(Seq(id -> y))

    def knownAdd(seq: Seq[(UniqIdOf[? <: MaybeVarCall], TyAndVal)]): LocalCtx = {
      val newMap = seq.foldLeft(knownMap) { (acc, item) =>
        assert(!acc.contains(item._1), s"Duplicate key ${item._1}")
        acc + item
      }
      copy(knownMap = newMap)
    }

    def add(item: ContextItem): LocalCtx = add(Seq(item))

    def add(seq: Seq[ContextItem]): LocalCtx = {
      val newMap = seq.foldLeft(map) { (acc, item) =>
        assert(!acc.contains(item.name), s"Duplicate key ${item.name}")
        acc + (item.name -> item)
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
}

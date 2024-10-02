package chester.propagator

import chester.syntax.core.*
import chester.syntax.accociativity.OperatorsContext
import chester.syntax.concrete.{Expr, ResolvingModules}
import chester.syntax.Name
import chester.utils.propagator.*

case class ContextItem(
                        ref: LocalV,
                        ty: CellId[Term]
                      ) {
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
                     imports: Imports = Imports.Empty,
                     modules: ResolvingModules = ResolvingModules.Empty,
                     operators: OperatorsContext = OperatorsContext.Default
                   ) {
  def get(id: Name): Option[ContextItem] =
    map.get(id)
}

case class Global(references: SeqId[Reference])

object LocalCtx {
  val Empty: LocalCtx = LocalCtx()
}
package chester.propagator

import chester.syntax.core.*
import chester.syntax.accociativity.OperatorsContext
import chester.syntax.concrete.ResolvingModules
import chester.syntax.Name
import chester.utils.propagator.*

case class CtxItem(
  name: LocalVar,
  ty: CellId[Term] // The type of the variable as a cell
)

case class LocalCtx(
                     map: Map[Name, CtxItem] = Map.empty,
                     parent: Option[LocalCtx] = None,
                     imports: Imports = Imports.Empty,
                     modules: ResolvingModules = ResolvingModules.Empty,
                     operators: OperatorsContext = OperatorsContext.Default
) {
  def get(id: Name): Option[CtxItem] =
    map.get(id).orElse(parent.flatMap(_.get(id)))

  def extend(item: CtxItem): LocalCtx = {
    copy(map = map + (item.name.id -> item))
  }

  def withParent(ctx: LocalCtx): LocalCtx = {
    copy(parent = Some(ctx))
  }
}

object LocalCtx {
  val Empty: LocalCtx = LocalCtx()
}
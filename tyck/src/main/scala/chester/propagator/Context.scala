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

case class Context(
  map: Map[Name, CtxItem] = Map.empty,
  parent: Option[Context] = None,
  imports: Imports = Imports.Empty,
  modules: ResolvingModules = ResolvingModules.Empty,
  operators: OperatorsContext = OperatorsContext.Default
) {
  def get(id: Name): Option[CtxItem] =
    map.get(id).orElse(parent.flatMap(_.get(id)))

  def extend(item: CtxItem): Context = {
    copy(map = map + (item.name.id -> item))
  }

  def withParent(ctx: Context): Context = {
    copy(parent = Some(ctx))
  }
}

case class LocalCtx(ctx: Context = Context()) {
  def resolve(id: Name): Option[CtxItem] = ctx.get(id)

  def extend(item: CtxItem): LocalCtx = {
    copy(ctx = ctx.extend(item))
  }

  def withParent(parentCtx: Context): LocalCtx = {
    copy(ctx = ctx.withParent(parentCtx))
  }
}

object LocalCtx {
  val Empty: LocalCtx = LocalCtx()
}
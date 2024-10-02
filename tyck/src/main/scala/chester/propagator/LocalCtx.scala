package chester.propagator

import chester.syntax.core.*
import chester.syntax.accociativity.OperatorsContext
import chester.syntax.concrete.ResolvingModules
import chester.syntax.Name
import chester.utils.propagator.*

case class ContextItem(
                        ref: LocalV,
                        ty: CellId[Term]
                      ) {
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

object LocalCtx {
  val Empty: LocalCtx = LocalCtx()
}
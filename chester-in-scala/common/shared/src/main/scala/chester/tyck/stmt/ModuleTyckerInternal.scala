package chester.tyck.stmt

import chester.resolve.MacroExpander
import chester.syntax.concrete.ResolvingModule
import chester.tyck.*

case class ModuleTyckState()

type ModuleTyckGetting[T] = Getting[TyckWarning, TyckError, ModuleTyckState, T]

case class ModuleTyckerInternal(macroExpander: MacroExpander = MacroExpander()) {

}

object ModuleTycker {

  def tyckModule(module: ResolvingModule, state: ModuleTyckState = ModuleTyckState()) = ???
}

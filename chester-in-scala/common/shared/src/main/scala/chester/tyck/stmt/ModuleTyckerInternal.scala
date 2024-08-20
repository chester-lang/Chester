package chester.tyck.stmt

import chester.resolve.MacroExpander
import chester.syntax.concrete.{ResolvingModule, ResolvingModules}
import chester.error.*
import chester.syntax.QualifiedIDString
import chester.tyck.{Get, Getting, TyckState}

import scala.concurrent.Promise

case class DependencyGraph(dependencies: Map[QualifiedIDString, Set[QualifiedIDString]]) {
  def getAllDependencies(id: QualifiedIDString): Set[QualifiedIDString] = {
    var result: Set[QualifiedIDString] = Set()
    var checking: Seq[QualifiedIDString] = Seq(id)
    while (checking.nonEmpty) {
      val tocheck = checking.flatMap(x => dependencies.getOrElse(x, Seq.empty))
      checking = Seq()
      for (dep <- tocheck) {
        if (id != dep && !result.contains(dep)) {
          checking = checking :+ dep
          result = result + dep
        }
      }
    }
    result
  }
  // if not in, empty, else, the cycle
  def inDependencyCycles(id: QualifiedIDString): Set[QualifiedIDString] = ???
}

case class ModuleTyckState(modules: ResolvingModules)

object ModuleTyckState {
  val Empty = ModuleTyckState(ResolvingModules.Empty)
}

type ModuleTyck = Get[TyckWarning, TyckError, ModuleTyckState]

case class ModuleTyckerInternal()(implicit S: ModuleTyck) {
  def checkModule(path: QualifiedIDString): Unit = {
    //val todo = S.state.modules.getOption(path)
  }

}

object ModuleTycker {
}

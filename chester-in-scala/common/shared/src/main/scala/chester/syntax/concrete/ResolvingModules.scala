package chester.syntax.concrete

import chester.syntax.QualifiedIDString
import chester.syntax.concrete.stmt.Statement
import chester.syntax.core.stmt.TyckedModule

import scala.collection.immutable.HashMap

case class ResolvingBlock(statements: Vector[Statement], expr: Option[Expr])

case class ResolvingModuleFile(fileName: FilePath, content: ResolvingBlock)

case class ResolvingModule(id: QualifiedIDString, resolving: Vector[ResolvingModuleFile], resolved: Option[TyckedModule])

case class ResolvingModules(modules: HashMap[QualifiedIDString, ResolvingModule]) {
}
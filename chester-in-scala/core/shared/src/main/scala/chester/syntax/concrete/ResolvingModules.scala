package chester.syntax.concrete

import chester.syntax.QualifiedIDString
import chester.syntax.concrete.*
import chester.syntax.core.stmt.TyckedModule

import scala.collection.immutable.HashMap

type FileName = String

case class ResolvingBlock(statements: Vector[Stmt], expr: Option[Expr])

case class ResolvingModuleFile(id: QualifiedIDString, fileName: FileName, content: Option[ResolvingBlock], tycked: Option[Nothing] = None) {
  require(content.isDefined || tycked.isDefined)
}


object ResolvingModuleFile {
  def apply(id: QualifiedIDString, fileName: FileName, content: Option[ResolvingBlock], tycked: Option[Nothing] = None): ResolvingModuleFile = {
    new ResolvingModuleFile(id, fileName, content, tycked)
  }
  def apply(id: QualifiedIDString, fileName: FileName, content: ResolvingBlock): ResolvingModuleFile = {
    new ResolvingModuleFile(id, fileName, Some(content))
  }
}

case class ResolvingModule(id: QualifiedIDString, resolving: Vector[ResolvingModuleFile])

case class ResolvingModules(modules: HashMap[QualifiedIDString, ResolvingModule]) {
  def getOption(id: QualifiedIDString): Option[ResolvingModule] = modules.get(id)
  def addModuleFile(id: QualifiedIDString, moduleFile: ResolvingModuleFile): ResolvingModules = {
    require(moduleFile.id == id)
    ???
  }
}

object ResolvingBlock {
  def fromParsed(block: Block): ResolvingBlock = {
    ResolvingBlock(
      statements = block.heads.map(x => ExprStmt(x, x.meta)),
      expr = block.tail
    )
  }
}

object ResolvingModule {
}

object ResolvingModules {
  val Empty: ResolvingModules = ResolvingModules(HashMap.empty)
}

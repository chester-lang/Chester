package chester.syntax.concrete

import chester.syntax.QualifiedIDString
import chester.syntax.concrete.stmt.{ExprStatement, Statement}
import chester.syntax.core.stmt.TyckedModule

import scala.collection.immutable.HashMap

case class ResolvingBlock(statements: Vector[Statement], expr: Option[Expr])

case class ResolvingModuleFile(fileName: FilePath, content: ResolvingBlock)

case class ResolvingModule(id: QualifiedIDString, resolving: Vector[ResolvingModuleFile], tycked: Option[TyckedModule])

case class ResolvingModules(modules: HashMap[QualifiedIDString, ResolvingModule]) {
}

object ResolvingBlock {
  def fromParsed(block: Block): ResolvingBlock = {
    ResolvingBlock(
      statements = block.heads.map(x => ExprStatement(x, x.meta)),
      expr = block.tail
    )
  }
}

object ResolvingModuleFile {
  def fromParsed(parsedFile: ParsedModuleFile): ResolvingModuleFile = {
    ResolvingModuleFile(
      fileName = parsedFile.fileName,
      content = ResolvingBlock.fromParsed(parsedFile.content)
    )
  }
}

object ResolvingModule {
  def fromParsed(id: QualifiedIDString, parsedFiles: Vector[ParsedModuleFile]): ResolvingModule = {
    ResolvingModule(
      id = id,
      resolving = parsedFiles.map(ResolvingModuleFile.fromParsed),
      tycked = None
    )
  }
}

object ResolvingModules {
  def fromParsed(parsedModules: ParsedModules): ResolvingModules = {
    ResolvingModules(
      modules = parsedModules.modules.map { case (id, parsedFiles) =>
        id -> ResolvingModule.fromParsed(id, parsedFiles)
      }
    )
  }
}

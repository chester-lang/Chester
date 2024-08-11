package chester.syntax.concrete

import scala.collection.immutable

type QualifiedIDString = Vector[String]

type FilePath = String

case class ParsedModuleFile(fileName: FilePath, content: Block)

case class ParsedModules(modules: immutable.HashMap[QualifiedIDString, Vector[ParsedModuleFile]]) {
  def addModule(id: QualifiedIDString, filePath: FilePath, block: Block): ParsedModules = {
    val newModuleFile = ParsedModuleFile(filePath, block)
    addModule(id, newModuleFile)
  }

  def addModule(id: QualifiedIDString, moduleFile: ParsedModuleFile): ParsedModules = {
    val updatedModules = modules.get(id) match {
      case Some(files) => modules.updated(id, files :+ moduleFile)
      case None => modules.updated(id, Vector(moduleFile))
    }
    ParsedModules(updatedModules)
  }
}

object ParsedModules {
  def Empty: ParsedModules = ParsedModules(immutable.HashMap())
}
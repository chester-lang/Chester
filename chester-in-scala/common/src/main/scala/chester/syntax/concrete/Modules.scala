package chester.syntax.concrete

import scala.collection.immutable

type QualifiedIDString = Vector[String]

type FilePath = String

case class ModuleFile(fileName: FilePath, content: Block)

case class Modules(modules: immutable.HashMap[QualifiedIDString, Vector[ModuleFile]]) {
  def addModule(id: QualifiedIDString, filePath: FilePath, block: Block): Modules = {
    val newModuleFile = ModuleFile(filePath, block)
    addModule(id, newModuleFile)
  }
  def addModule(id: QualifiedIDString, moduleFile: ModuleFile): Modules = {
    val updatedModules = modules.get(id) match {
      case Some(files) => modules.updated(id, files :+ moduleFile)
      case None => modules.updated(id, Vector(moduleFile))
    }
    Modules(updatedModules)
  }
}

object Modules {
  def Empty: Modules = Modules(immutable.HashMap())
}
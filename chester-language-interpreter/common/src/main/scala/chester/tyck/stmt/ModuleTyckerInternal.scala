package chester.tyck.stmt

import chester.syntax.concrete.{Block, ModuleFile, Modules, QualifiedIDString}
import chester.syntax.core.stmt.{TyckedBlock, TyckedDefinition, TyckedExpression, TyckedModule, TyckedModuleFile}
import chester.tyck.{ExprTyckerInternal, Getting, LocalCtx, TyckError, TyckState, TyckGetting}

case class ModuleTyckState()

type ModuleTyckGetting[T] = Getting[ModuleTyckState, T]

case class ModuleTyckerInternal() {

  // Type-check a single module file
  def tyckModuleFile(moduleFile: ModuleFile): ModuleTyckGetting[TyckedModuleFile] = {
    for {
      block <- tyckBlock(moduleFile.content)
    } yield TyckedModuleFile(moduleFile.fileName, block)
  }

  // Type-check a vector of module files
  def tyckModuleFiles(files: Vector[ModuleFile]): ModuleTyckGetting[Vector[TyckedModuleFile]] = {
    files.foldLeft(Getting.pure[ModuleTyckState, Vector[TyckedModuleFile]](Vector.empty)) { (acc, file) =>
      for {
        accFiles <- acc
        fileTycked <- tyckModuleFile(file)
      } yield accFiles :+ fileTycked
    }
  }

  // Type-check a module
  def tyckModule(id: QualifiedIDString, files: Vector[ModuleFile]): ModuleTyckGetting[TyckedModule] = {
    for {
      fileTycked <- tyckModuleFiles(files)
    } yield TyckedModule(id, fileTycked)
  }

  // Type-check a collection of modules
  def tyckModules(modules: Modules): ModuleTyckGetting[Vector[TyckedModule]] = {
    modules.modules.foldLeft(Getting.pure[ModuleTyckState, Vector[TyckedModule]](Vector.empty)) { case (acc, (id, files)) =>
      for {
        accModules <- acc
        moduleTycked <- tyckModule(id, files)
      } yield accModules :+ moduleTycked
    }
  }

  // Type-check a block of expressions
  def tyckBlock(block: Block): ModuleTyckGetting[TyckedBlock] = ???
}
object ModuleTycker {

  def tyckModuleFile(moduleFile: ModuleFile, state: ModuleTyckState = ModuleTyckState()): Either[Vector[TyckError], TyckedModuleFile] = {
    ModuleTyckerInternal().tyckModuleFile(moduleFile).getOne(state).map(_._2)
  }

  def tyckModule(id: QualifiedIDString, files: Vector[ModuleFile], state: ModuleTyckState = ModuleTyckState()): Either[Vector[TyckError], TyckedModule] = {
    ModuleTyckerInternal().tyckModule(id, files).getOne(state).map(_._2)
  }

  def tyckModules(modules: Modules, state: ModuleTyckState = ModuleTyckState()): Either[Vector[TyckError], Vector[TyckedModule]] = {
    ModuleTyckerInternal().tyckModules(modules).getOne(state).map(_._2)
  }
}

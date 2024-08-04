package chester.tyck.stmt

import chester.syntax.concrete.{Block, ModuleFile, Modules, QualifiedIDString}
import chester.syntax.core.stmt.{TyckedBlock, TyckedDefinition, TyckedExpression, TyckedModule, TyckedModuleFile}
import chester.tyck.{ExprTyckerInternal, Getting, LocalCtx, TyckError, TyckState}

case class ModuleTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty) {

  // Type-check a single module file
  def tyckModuleFile(moduleFile: ModuleFile): Getting[TyckedModuleFile] = {
    for {
      block <- tyckBlock(moduleFile.content)
    } yield TyckedModuleFile(moduleFile.fileName, block)
  }

  // Type-check a vector of module files
  def tyckModuleFiles(files: Vector[ModuleFile]): Getting[Vector[TyckedModuleFile]] = {
    files.foldLeft(Getting.pure(Vector.empty[TyckedModuleFile])) { (acc, file) =>
      for {
        accFiles <- acc
        fileTycked <- tyckModuleFile(file)
      } yield accFiles :+ fileTycked
    }
  }

  // Type-check a module
  def tyckModule(id: QualifiedIDString, files: Vector[ModuleFile]): Getting[TyckedModule] = {
    for {
      fileTycked <- tyckModuleFiles(files)
    } yield TyckedModule(id, fileTycked)
  }

  // Type-check a collection of modules
  def tyckModules(modules: Modules): Getting[Vector[TyckedModule]] = {
    modules.modules.foldLeft(Getting.pure(Vector.empty[TyckedModule])) { case (acc, (id, files)) =>
      for {
        accModules <- acc
        moduleTycked <- tyckModule(id, files)
      } yield accModules :+ moduleTycked
    }
  }

  // Type-check a block of expressions
  def tyckBlock(block: Block): Getting[TyckedBlock] = {
    block.heads.foldLeft(Getting.pure(Vector.empty[TyckedDefinition])) { (acc, expr) =>
      for {
        accExprs <- acc
        judge <- ExprTyckerInternal(localCtx).synthesize(expr)
      } yield accExprs :+ TyckedExpression(judge)
    }.map(TyckedBlock)
  }
}
object ModuleTycker {

  def tyckModuleFile(moduleFile: ModuleFile, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], TyckedModuleFile] = {
    ModuleTyckerInternal(ctx).tyckModuleFile(moduleFile).getOne(state).map(_._2)
  }

  def tyckModule(id: QualifiedIDString, files: Vector[ModuleFile], state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], TyckedModule] = {
    ModuleTyckerInternal(ctx).tyckModule(id, files).getOne(state).map(_._2)
  }

  def tyckModules(modules: Modules, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Vector[TyckedModule]] = {
    ModuleTyckerInternal(ctx).tyckModules(modules).getOne(state).map(_._2)
  }
}

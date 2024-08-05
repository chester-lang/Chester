package chester.tyck.stmt

import chester.syntax.concrete.{Block, ModuleFile, Modules, QualifiedIDString}
import chester.syntax.core.stmt.{TyckedBlock, TyckedDefinition, TyckedExpression, TyckedModule, TyckedModuleFile}
import chester.tyck.{ExprTycker, Getting, LocalCtx, TyckError, TyckState, TyckGetting}

case class ModuleTycker(localCtx: LocalCtx = LocalCtx.Empty) {

  // Type-check a single module file
  def tyckModuleFile(moduleFile: ModuleFile): TyckGetting[TyckedModuleFile] = {
    for {
      block <- tyckBlock(moduleFile.content)
    } yield TyckedModuleFile(moduleFile.fileName, block)
  }

  // Type-check a vector of module files
  def tyckModuleFiles(files: Vector[ModuleFile]): TyckGetting[Vector[TyckedModuleFile]] = {
    files.foldLeft(Getting.pure[TyckState, Vector[TyckedModuleFile]](Vector.empty)) { (acc, file) =>
      for {
        accFiles <- acc
        fileTycked <- tyckModuleFile(file)
      } yield accFiles :+ fileTycked
    }
  }

  // Type-check a module
  def tyckModule(id: QualifiedIDString, files: Vector[ModuleFile]): TyckGetting[TyckedModule] = {
    for {
      fileTycked <- tyckModuleFiles(files)
    } yield TyckedModule(id, fileTycked)
  }

  // Type-check a collection of modules
  def tyckModules(modules: Modules): TyckGetting[Vector[TyckedModule]] = {
    modules.modules.foldLeft(Getting.pure[TyckState, Vector[TyckedModule]](Vector.empty)) { case (acc, (id, files)) =>
      for {
        accModules <- acc
        moduleTycked <- tyckModule(id, files)
      } yield accModules :+ moduleTycked
    }
  }

  // Type-check a block of expressions
  def tyckBlock(block: Block): TyckGetting[TyckedBlock] = {
    block.heads.foldLeft(Getting.pure[TyckState, Vector[TyckedDefinition]](Vector.empty)) { (acc, expr) =>
      for {
        accExprs <- acc
        judge <- ExprTycker(localCtx).synthesize(expr)
      } yield accExprs :+ TyckedExpression(judge)
    }.map(TyckedBlock)
  }
}

object ModuleTycker {

  def tyckModuleFile(moduleFile: ModuleFile, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], TyckedModuleFile] = {
    ModuleTycker(ctx).tyckModuleFile(moduleFile).getOne(state).map(_._2)
  }

  def tyckModule(id: QualifiedIDString, files: Vector[ModuleFile], state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], TyckedModule] = {
    ModuleTycker(ctx).tyckModule(id, files).getOne(state).map(_._2)
  }

  def tyckModules(modules: Modules, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Vector[TyckedModule]] = {
    ModuleTycker(ctx).tyckModules(modules).getOne(state).map(_._2)
  }
}

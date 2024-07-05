package chester.syntax.concrete.stmt

import chester.error.{SourcePos, WithPos}

type ModuleName = ModuleNameQualified
case class ModuleNameQualified(ids: Vector[String])
sealed trait Stmt extends WithPos {

}

object ModuleName {
  def builtin = ModuleNameQualified(Vector())
}

case class QualifiedID(component: ModuleName, name: String, sourcePos: Option[SourcePos] = None) extends WithPos

object QualifiedID {
  def builtin(name: String) = QualifiedID(ModuleName.builtin, name)
}

package chester.syntax.concrete.stmt

import chester.error.{SourcePos, WithPos}
import chester.syntax.Id
import chester.syntax.concrete.{Expr, ExprMeta}

type ModuleName = ModuleNameQualified

case class ModuleNameQualified(ids: Vector[Id])

object ModuleName {
  def builtin = ModuleNameQualified(Vector())
}

case class QualifiedID(component: ModuleName, name: Id, sourcePos: Option[SourcePos] = None) extends WithPos

object QualifiedID {
  def builtin(name: String) = QualifiedID(ModuleName.builtin, name)
}
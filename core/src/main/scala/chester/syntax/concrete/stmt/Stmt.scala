package chester.syntax.concrete.stmt

import chester.error._
import chester.syntax.Name
import chester.syntax.concrete.{Expr, ExprMeta}
import upickle.default._

type ModuleName = ModuleNameQualified

case class ModuleNameQualified(ids: Vector[Name]) derives ReadWriter

object ModuleName {
  def builtin = ModuleNameQualified(Vector())
}

case class QualifiedID(component: ModuleName, name: Name, sourcePos: Option[SourcePos] = None) extends WithPos derives ReadWriter

object QualifiedID {
  def builtin(name: String) = QualifiedID(ModuleName.builtin, name)
}
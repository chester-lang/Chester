package chester.error

import chester.pretty.doc.*
import chester.syntax.concrete.{Expr, QualifiedName}
import chester.syntax.core.Term

trait TyckErrorOrWarning extends ToDoc {
  def message: String = render(toDoc)

  override def toDoc(implicit options: PrettierOptions = Map()): Doc = message

  def cause: Option[Term | Expr] = None

  def location: Option[SourcePos] = cause match {
    case Some(term: Term) => term.sourcePos
    case Some(expr: Expr) => expr.sourcePos
    case _ => None
  }

  val stack: Array[StackTraceElement] = new Exception().getStackTrace
}

trait TyckError extends Exception with TyckErrorOrWarning {
}

trait TyckWarning extends TyckErrorOrWarning {

}

case class EmptyResultsError() extends TyckError {
  override def message: String = "Empty Results"

  override def cause: Option[Term | Expr] = None
}

case class UnifyFailedError(subType: Term, superType: Term) extends TyckError {
  override def message: String = s"Unification failed: $subType is not a subtype of $superType"

  override def cause: Option[Term | Expr] = Some(subType)
}

case class UnsupportedExpressionError(expr: Expr) extends TyckError {
  override def message: String = s"Unsupported expression type: $expr"

  override def cause: Option[Term | Expr] = Some(expr)
}

case class FieldTypeNotFoundError(qualifiedName: QualifiedName | String) extends TyckError {
  override def message: String = s"Field type not found for $qualifiedName"

  override def cause: Option[Term | Expr] = qualifiedName match {
    case x: Term => Some(x)
    case x: Expr => Some(x)
    case _ => None
  }
}

case class ExpectedObjectTypeError() extends TyckError {
  override def message: String = "Expected an ObjectType for inheritance"

  override def cause: Option[Term | Expr] = None
}


case class ExpectCase() extends TyckError {
  override def message: String = "case clause must have a pattern and a return expression"
}
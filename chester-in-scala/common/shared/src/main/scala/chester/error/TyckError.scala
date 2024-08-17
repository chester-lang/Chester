package chester.error

import chester.pretty.doc.*
import chester.syntax.concrete.{Expr, QualifiedName}
import chester.syntax.core.Term

abstract class TyckErrorOrWarning extends Exception with ToDoc {
  def message: String = render(toDoc)

  override def toDoc(implicit options: PrettierOptions = Map()): Doc = message

  def cause: Option[Term | Expr] = None

  def location: Option[SourcePos] = cause match {
    case Some(term: Term) => term.sourcePos
    case Some(expr: Expr) => expr.sourcePos
    case _ => None
  }

  val stack: Array[StackTraceElement] = this.getStackTrace

  def renderWithLocation(implicit options: PrettierOptions = Map()): Doc = {
    val baseMessage = Doc.text("Error: ") <> Doc.text(message).colored(Attribute.BoldOn)

    val locationInfo = location match {
      case Some(pos) =>
        val lines = pos.getLinesInRange.map { case (lineNumber, line) =>
          Doc.text(s"$lineNumber: ") <> Doc.text(line).colored(Attribute.BoldOn)
        }
        val locationHeader = Doc.text("Location: ") <>
          Doc.text(s"${pos.fileName} [${pos.range.start.line + 1}:${pos.range.start.column + 1}] to [${pos.range.end.line + 1}:${pos.range.end.column + 1}]").colored(Attribute.BoldOn)

        val codeBlock = Doc.group(Doc.concat(lines.map(_ <|> Doc.empty): _*))

        locationHeader <|> codeBlock

      case None =>
        cause match {
          case Some(causeExpr) =>
            val causeHeader = Doc.text("Cause: ").colored(Attribute.BoldOn)
            val causeText = Doc.text(causeExpr.toString)
            causeHeader <|> causeText
          case None =>
            Doc.empty
        }
    }

    baseMessage <|> locationInfo
  }
}

abstract class TyckError extends TyckErrorOrWarning {
}

abstract class TyckWarning extends TyckErrorOrWarning {

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


case class ExpectCase(x: Expr) extends TyckError {
  override def message: String = "case clause must have a pattern and a return expression"

  override def cause: Some[Expr] = Some(x)
}

case class ExpectFullCaseBlock(block: Expr) extends TyckError {
  override def message: String = s"Expected a full case block, got $block"

  override def cause: Some[Expr] = Some(block)
}


case class ExpectSingleExpr(xs: Seq[Expr]) extends TyckError {
  override def message: String = s"Expected a single expression, got $xs"

  override def cause: Some[Expr] = Some(xs.head)
}

case class ExpectLambda(x: Expr) extends TyckError {
  override def message: String = s"Expected a lambda expression, got $x"

  override def cause: Some[Expr] = Some(x)
}

case class ExpectPattern(x: Expr) extends TyckError {
  override def message: String = s"Expected a pattern, got $x"

  override def cause: Some[Expr] = Some(x)
}
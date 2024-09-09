package chester.error

import chester.doc.*
import chester.i18n.*
import chester.syntax.concrete.*
import chester.syntax.core.Term
import chester.utils.doc._
import chester.utils.impls.*
import upickle.default.*

import scala.reflect.ClassTag

sealed trait TyckErrorOrWarning extends Exception with ToDoc derives ReadWriter {
  def message: String = {
    implicit val options: PrettierOptions = PrettierOptions.Default
    render(toDoc)
  }

  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = message

  def cause: Term | Expr

  def location: Option[SourcePos] = cause match {
    case x: WithPos => x.sourcePos
    case _ => None
  }

  val stack: Array[StackTraceElement] = this.getStackTrace

  def renderWithLocation(implicit options: PrettierOptions = PrettierOptions.Default): Doc = {
    val baseMessage = Doc.text(t"Error") <> Doc.text(message, Styling.BoldOn)

    val locationInfo = location match {
      case Some(pos) =>
        val lines = pos.getLinesInRange match {
          case Some(lines) => lines.map { case (lineNumber, line) =>
            Doc.text(t"$lineNumber") <> Doc.text(line, Styling.BoldOn)
          }
          case None => Vector.empty
        }
        val locationHeader = Doc.text(t"Location") <>
          Doc.text(t"${pos.fileName} [${pos.range.start.line + 1}:${pos.range.start.column + 1}] to [${pos.range.end.line + 1}:${pos.range.end.column + 1}]", Styling.BoldOn)

        val codeBlock = Doc.group(Doc.concat(lines.map(_.end): _*))

        locationHeader <|> codeBlock

      case None =>
        val causeHeader = Doc.text(t"Cause",Styling.BoldOn)
        val causeText = cause
        causeHeader <|> causeText
    }

    baseMessage <|> locationInfo
  }
}

sealed trait TyckError extends TyckErrorOrWarning derives ReadWriter {
}

sealed trait TyckWarning extends TyckErrorOrWarning derives ReadWriter {

}

case object ExampleWarning extends TyckWarning {
  override def cause: Term | Expr = EmptyExpr

  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Example warning"
}

case class EmptyResultsError() extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Empty Results"

  override def cause: Expr = EmptyExpr
}

case class UnifyFailedError(rhs: Term, lhs: Term) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unification failed: $rhs is not a subtype of $lhs"

  override def cause: Term = rhs
}

case class UnsupportedExpressionError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unsupported expression type"
}

case class IdentifierNotFoundError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Identifier not found"
}

case class UnexpectedStmt(cause: Stmt) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc =  t"Unexpected statement"
}

import chester.syntax.concrete.qualifiedNameRW
implicit val rwThis: ReadWriter[QualifiedName | String] = union2RW[Expr, String](using implicitly[ClassTag[Expr]], implicitly[ClassTag[String]], a=qualifiedNameRW.asInstanceOf[ReadWriter[Expr]], b=readwriter[String]).asInstanceOf
case class FieldTypeNotFoundError(qualifiedName: QualifiedName | String) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Field type not found for $qualifiedName"

  override def cause: Term | Expr = qualifiedName match {
    case x: Term => x
    case x: Expr => x
    case x: String => EmptyExpr
  }
}

case class ExpectedObjectTypeError() extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected an ObjectType for inheritance"

  override def cause: Term | Expr = EmptyExpr
}


case class ExpectCase(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"case clause must have a pattern and a return expression"
}

case class ExpectFullCaseBlock(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected a full case block, got "
}


case class ExpectSingleExpr(xs: Seq[Expr]) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected a single expression, got $xs"

  override def cause: Expr = xs.head
}

case class ExpectLambda(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected a lambda expression, got "
}

case class ExpectPattern(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected a pattern, got "
}

case class ExpectLetDef(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected a let or def statement, got "
}

case class DuplicateTelescopeArgError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Duplicate telescope argument"
}

case class UnsupportedDecorationError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unsupported decoration "
}

case class UnsupportedVarargError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unsupported vararg"
}

case class UnsupportedEmptyTelescopeError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unsupported empty telescope"
}
package chester.error

import chester.doc.*
import chester.i18n.*
import chester.syntax.Name
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.utils.doc.*
import chester.utils.impls.*
import upickle.default.*
import chester.syntax.accociativity.*

import scala.reflect.ClassTag

sealed trait TyckProblem extends Problem derives ReadWriter {
  final def stage: Problem.Stage = Problem.Stage.TYCK

  final def getMessage: String = {
    implicit val options: PrettierOptions = PrettierOptions.Default
    render(toDoc)
  }

  def hint: ToDoc = empty

  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc

  def cause: Term | Expr

  def location: Option[SourcePos] = cause match {
    case x: WithPos => x.sourcePos
    case _ => None
  }

  def renderWithLocation(implicit options: PrettierOptions = PrettierOptions.Default): Doc = {
    val baseMessage = Doc.text(t"Error") <+> this

    val locationInfo = location match {
      case Some(pos) =>
        val lines = pos.getLinesInRange match {
          case Some(lines) => lines.map { case (lineNumber, line) =>
            Doc.text(t"$lineNumber") <+> Doc.text(line, Styling.BoldOn)
          }
          case None => Vector.empty
        }
        val locationHeader = Doc.text(t"Location") <+>
          Doc.text(t"${pos.fileName} [${pos.range.start.line + 1}:${pos.range.start.column.i + 1}] to [${pos.range.end.line + 1}:${pos.range.end.column.i + 1}]", Styling.BoldOn)

        val codeBlock = Doc.group(Doc.concat(lines.map(_.end)*))

        locationHeader <|> codeBlock

      case None =>
        val causeHeader = Doc.text(t"Cause", Styling.BoldOn)
        val causeText = cause
        causeHeader <|> causeText
    }

    baseMessage <|> locationInfo
  }
}

sealed trait TyckError extends TyckProblem derives ReadWriter {
  final override def level: Problem.Severity = Problem.Severity.ERROR
}

sealed trait TyckWarning extends TyckProblem derives ReadWriter {
  final override def level: Problem.Severity = Problem.Severity.WARN
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
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unsupported expression type ${cause.getClass.getName}"
}

case class IdentifierNotFoundError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Identifier not found"
}

case class UnexpectedStmt(cause: Stmt) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unexpected statement"
}

import chester.syntax.concrete.qualifiedNameRW

implicit val rwThis: ReadWriter[QualifiedName | String] = union2RW[Expr, String](using implicitly[ClassTag[Expr]], implicitly[ClassTag[String]], a = qualifiedNameRW.asInstanceOf[ReadWriter[Expr]], b = readwriter[String]).asInstanceOf

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

case class InvalidNaturalError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Invalid natural number"
}

case class InvalidIntError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Invalid integer"
}

case class FieldNotFoundInObjectTypeError(field: Expr, objectType: ObjectType) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Field $field not found in object type $objectType"

  override def cause: Term | Expr = field
}

case class EffectUnifyError(lhs: Effects, rhs: Judge) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Effect unification failed"

  override def cause: Term | Expr = rhs.wellTyped
}

case class UnexpectedTelescope(cause: MaybeTelescope) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unexpected telescope"
}
case class NotAFunctionError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Expected a function, but got ${cause}"
}

case class ExplicitTelescopeRequiredError(cause: MaybeTelescope) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Explicit telescope required"
}
case class MultipleVarargsError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Multiple varargs not allowed"
}
case class DuplicateArgumentError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Duplicate argument"
}

case class UnknownArgumentError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unknown argument"
}
case class MissingArgumentError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Missing argument"
}
case class InvalidTelescopeError(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Invalid telescope"
}
case class ExpectParameterList(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc =
    t"Expected a parameter list, got ${cause}"
}
case class TupleArityMismatchError(provided: Int, expected: Int, cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc =
    t"Tuple arity mismatch: expected $expected elements but got $provided"

}
case class MissingTypeAnnotationError(cause: Identifier) extends TyckError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Missing type annotation for forward-referenced 'def' '${cause.name}'"
}

case class MissingBodyError(cause: Identifier) extends TyckError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Missing body expression for '${cause.name}'"
}

case class UnsupportedTermError(cause: Term) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unsupported term"
}

sealed trait OpInfoError extends TyckError derives ReadWriter {
  override def cause: Term | Expr = EmptyExpr
}

case class UnknownOperator(override val cause: Expr) extends OpInfoError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Unknown operator."
}

case class PrecedenceCycleDetected(groups: Iterable[PrecedenceGroup]) extends OpInfoError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Precedence cycle detected among groups: ${groups.map(_.name).mkString(" -> ")}"
}

case class UnexpectedTokens(tokens: List[Expr]) extends OpInfoError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Unexpected tokens after parsing expression: $tokens"
}

case class UnknownPrecedenceGroup(group: PrecedenceGroup) extends OpInfoError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Unknown precedence group: '${group.name}'."
}

case class UnconnectedPrecedenceGroups(group1: PrecedenceGroup, group2: PrecedenceGroup) extends OpInfoError {
  override def toDoc(implicit options: PrettierOptions): Doc =
    t"Precedence groups '${group1.name}' and '${group2.name}' are not connected."
}

case class UnboundVariable(name: Name, cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Unbound variable $name"
}

case class NotImplemented(cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = t"Not implemented"
}

case class TypeMismatch(lhs: Term, rhs: Term, cause: Expr) extends TyckError {
  override def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc = d"Type mismatch: expected $lhs but got $rhs"
}
package chester.lang

case class SourceLocation(file: String, start: Int, end: Int, startLine: Int, startCol: Int, endLine: Int, endCol: Int)

sealed trait Expr {
  def location: Option[SourceLocation]
}

case class TableExpr(entries: Map[Expr | String, Expr], location: Option[SourceLocation]) extends Expr
case class StringExpr(value: String, location: Option[SourceLocation]) extends Expr
case class ListExpr(elements: Seq[Expr], location: Option[SourceLocation]) extends Expr
case class FunctionCall(name: String, args: Seq[Expr], location: Option[SourceLocation]) extends Expr
case class InfixExpr(left: Expr, op: String, right: Expr, location: Option[SourceLocation]) extends Expr
case class MethodCall(target: Expr, method: String, args: Seq[Expr], location: Option[SourceLocation]) extends Expr
case class LambdaExpr(params: Seq[(String, Option[String])], body: Expr, location: Option[SourceLocation]) extends Expr
case class TypeAnnotation(expr: Expr, tpe: String, location: Option[SourceLocation]) extends Expr
case class IntExpr(value: Int, location: Option[SourceLocation]) extends Expr
case class FloatExpr(value: Double, location: Option[SourceLocation]) extends Expr

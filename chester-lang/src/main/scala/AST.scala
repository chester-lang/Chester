package chester.lang

case class SourceLocation(file: String, start: Int, end: Int, startLine: Int, startCol: Int, endLine: Int, endCol: Int)

sealed trait AST {
  def location: Option[SourceLocation]
}

case class TableAST(entries: Map[AST | String, AST], location: Option[SourceLocation]) extends AST
case class StringAST(value: String, location: Option[SourceLocation]) extends AST
case class ListAST(elements: Seq[AST], location: Option[SourceLocation]) extends AST
case class FunctionCall(name: String, args: Seq[AST], location: Option[SourceLocation]) extends AST
case class InfixAST(left: AST, op: String, right: AST, location: Option[SourceLocation]) extends AST
case class MethodCall(target: AST, method: String, args: Seq[AST], location: Option[SourceLocation]) extends AST
case class LambdaAST(params: Seq[(String, Option[String])], body: AST, location: Option[SourceLocation]) extends AST
case class TypeAnnotation(expr: AST, tpe: String, location: Option[SourceLocation]) extends AST
case class IntAST(value: Int, location: Option[SourceLocation]) extends AST
case class FloatAST(value: Double, location: Option[SourceLocation]) extends AST

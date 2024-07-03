package chester.lang

import fastparse.*
import NoWhitespace.*
import java.lang.Character.{isDigit, isLetter}

import ast.*

case class Parser(fileName: String) {

  def parseExpression(input: String): Parsed[AST] = parse(input, expr(_))

  def parseExpressions(input: String): Parsed[Seq[AST]] = parse(input, program(_))

  def parseIdentifier(input: String): Parsed[String] = parse(input, identifier(_))

  private def space[$: P]: P[Unit] = P(CharsWhileIn(" \t\r\n", 0))

  private def begin[$: P]: P[Int] = Index
  private def end[$: P]: P[Int] = Index

  private def loc(begin: Int, end: Int)(implicit ctx: P[_]): Option[SourceLocation] = {
    val (startLine, startCol) = computeLineCol(begin)
    val (endLine, endCol) = computeLineCol(end)
    Some(SourceLocation(fileName, begin, end, startLine, startCol, endLine, endCol))
  }

  private def computeLineCol(index: Int)(implicit ctx: P[_]): (Int, Int) = {
    val slice = ctx.input.slice(0, index)
    val lines = slice.split('\n')
    val line = lines.length
    val col = lines.lastOption.getOrElse("").length + 1
    (line, col)
  }

  private def stringChars(c: Char) = c != '\"' && c != '\\'

  private def hexDigit[$: P] = P(CharIn("0-9a-fA-F"))

  private def unicodeEscape[$: P] = P("u" ~ hexDigit ~ hexDigit ~ hexDigit ~ hexDigit)

  private def escape[$: P] = P("\\" ~ (CharIn("\"/\\\\bfnrt") | unicodeEscape))

  private def strChars[$: P] = P(CharsWhile(stringChars))

  private def string[$: P]: P[StringAST] = P(begin ~ space ~ "\"" ~/ (strChars | escape).rep.! ~ "\"" ~ end).map {
    case (begin, value, end) => StringAST(value, loc(begin, end))
  }

  private def integer[$: P]: P[IntAST] = P(begin ~ CharIn("+\\-").? ~ CharsWhileIn("0-9").rep(1).! ~ end).map {
    case (begin, value, end) => IntAST(value.toInt, loc(begin, end))
  }

  private def float[$: P]: P[FloatAST] = P(begin ~ CharIn("+\\-").? ~ CharsWhileIn("0-9").rep(1) ~ "." ~ CharsWhileIn("0-9").rep(1).! ~ end).map {
    case (begin, value, end) => FloatAST(value.toDouble, loc(begin, end))
  }

  private def number[$: P]: P[AST] = P(float | integer)

  private def table[$: P]: P[TableAST] = P(begin ~ "{" ~/ keyValue.rep(sep = P(";")./) ~ space ~ "}" ~ end).map {
    case (begin, entries, end) => TableAST(entries.toMap, loc(begin, end))
  }

  private def keyValue[$: P]: P[(String, AST)] = P(space ~/ identifier ~/ space ~ "=" ~/ expr).map {
    case (key, value) => (key, value)
  }

  private def list[$: P]: P[ListAST] = P(begin ~ "[" ~/ expr.rep(sep = P(",")./).? ~ space ~ "]" ~ end).map {
    case (begin, elements, end) => ListAST(elements.getOrElse(Seq()), loc(begin, end))
  }

  private def functionCall[$: P]: P[FunctionCall] = P(begin ~ identifier ~ "(" ~/ space ~ expr.rep(sep = P("," ~/ space)) ~ space ~ ")" ~ end).map {
    case (begin, name, args, end) => FunctionCall(name, args, loc(begin, end))
  }

  private def infixExpr[$: P]: P[AST] = P(begin ~ simpleExpr ~ space ~ operator ~ space ~ simpleExpr ~ end).map {
    case (begin, left, op, right, end) => InfixAST(left, op, right, loc(begin, end))
  }

  private def methodCall[$: P]: P[MethodCall] = P(begin ~ simpleExpr ~ "." ~ identifier ~ "(" ~/ space ~ expr.rep(sep = P("," ~/ space)) ~ space ~ ")" ~ end).map {
    case (begin, target, method, args, end) => MethodCall(target, method, args, loc(begin, end))
  }

  private def lambda[$: P]: P[LambdaAST] = P(begin ~ "(" ~/ space ~ params ~ space ~ ")" ~ space ~ "=>" ~/ space ~ expr ~ end).map {
    case (begin, params, body, end) => LambdaAST(params, body, loc(begin, end))
  }

  private def params[$: P]: P[Seq[(String, Option[String])]] = P(param.rep(sep = P("," ~/ space)))

  private def param[$: P]: P[(String, Option[String])] = P(identifier ~ space ~ (":" ~/ space ~ identifier).?)

  private def typeAnnotation[$: P]: P[TypeAnnotation] = P(begin ~ simpleExpr ~ ":" ~/ space ~ identifier ~ end).map {
    case (begin, expr, tpe, end) => TypeAnnotation(expr, tpe, loc(begin, end))
  }

  val Id0 = (c: Char) => isLetter(c) | c == '$' | c == '_'

  val Id_ = (c: Char) => Id0(c) | isDigit(c)

  def identifier[$: P]: P[String] = P(CharsWhile(Id0).! ~ CharsWhile(Id_).?.!) map {
    case (a, b) => a + b
  }

  private def operator[$: P]: P[String] = P(CharIn("+-*/").!)

  private def expr[$: P]: P[AST] = P(
    space ~ (table | list | string | number | functionCall | methodCall | lambda | typeAnnotation | infixExpr | simpleExpr) ~ space
  )

  private def simpleExpr[$: P]: P[AST] = P(begin ~ identifier ~ end).map {
    case (begin, id, end) => StringAST(id, loc(begin, end))
  }

  private def program[$: P]: P[Seq[AST]] = P(expr.rep(sep = P(";" ~/ space) | space))
}

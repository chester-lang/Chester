package chester.lang

import fastparse.*
import NoWhitespace.*

import java.lang.Character.{isDigit, isLetter}

case class Parser(fileName: String) {

  def parseExpression(input: String): Parsed[Expr] = parse(input, expr(_))

  def parseExpressions(input: String): Parsed[Seq[Expr]] = parse(input, program(_))

  def parseIdentifier(input: String): Parsed[String] = parse(input, identifier(_))

  def parseKeyValue(input: String): Parsed[(String, Expr)] = parse(input, keyValue(_))

  private def space[$: P]: P[Unit] = P(CharsWhileIn(" \t\r\n", 0))

  private def begin[$: P]: P[Int] = Index
  private def end[$: P]: P[Int] = Index

  private def loc(begin: Int, end: Int)(implicit ctx: P[_]): SourceLocation = {
    val (startLine, startCol) = computeLineCol(begin)
    val (endLine, endCol) = computeLineCol(end)
    SourceLocation(fileName, begin, end, startLine, startCol, endLine, endCol)
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

  private def string[$: P]: P[StringExpr] = P(begin ~ space ~ "\"" ~/ (strChars | escape).rep.! ~ "\"" ~ end).map {
    case (begin, value, end) => StringExpr(value, loc(begin, end))
  }

  private def integer[$: P]: P[IntExpr] = P(begin ~ CharIn("+\\-").? ~ CharsWhileIn("0-9").rep(1).! ~ end).map {
    case (begin, value, end) => IntExpr(value.toInt, loc(begin, end))
  }

  private def float[$: P]: P[FloatExpr] = P(begin ~ CharIn("+\\-").? ~ CharsWhileIn("0-9").rep(1) ~ "." ~ CharsWhileIn("0-9").rep(1).! ~ end).map {
    case (begin, value, end) => FloatExpr(value.toDouble, loc(begin, end))
  }

  private def number[$: P]: P[Expr] = P(float | integer)

  private def table[$: P]: P[TableExpr] = P(begin ~ "{" ~/ keyValue.rep(sep = P(";")./) ~ space ~ "}" ~ end).map {
    case (begin, entries, end) => TableExpr(entries.toMap, loc(begin, end))
  }

  private def keyValue[$: P]: P[(String, Expr)] = P(space ~/ identifier ~/ space ~ "=" ~/ expr).map {
    case (key, value) => (key, value)
  }

  private def list[$: P]: P[ListExpr] = P(begin ~ "[" ~/ expr.rep(sep = P(",")./).? ~ space ~ "]" ~ end).map {
    case (begin, elements, end) => ListExpr(elements.getOrElse(Seq()), loc(begin, end))
  }

  private def functionCall[$: P]: P[FunctionCall] = P(begin ~ identifier ~ "(" ~/ space ~ expr.rep(sep = P("," ~/ space)) ~ space ~ ")" ~ end).map {
    case (begin, name, args, end) => FunctionCall(name, args, loc(begin, end))
  }

  private def infixExpr[$: P]: P[Expr] = P(begin ~ simpleExpr ~ space ~ operator ~ space ~ simpleExpr ~ end).map {
    case (begin, left, op, right, end) => InfixExpr(left, op, right, loc(begin, end))
  }

  private def methodCall[$: P]: P[MethodCall] = P(begin ~ simpleExpr ~ "." ~ identifier ~ "(" ~/ space ~ expr.rep(sep = P("," ~/ space)) ~ space ~ ")" ~ end).map {
    case (begin, target, method, args, end) => MethodCall(target, method, args, loc(begin, end))
  }

  private def lambda[$: P]: P[LambdaExpr] = P(begin ~ "(" ~/ space ~ params ~ space ~ ")" ~ space ~ "=>" ~/ space ~ expr ~ end).map {
    case (begin, params, body, end) => LambdaExpr(params, body, loc(begin, end))
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

  private def expr[$: P]: P[Expr] = P(
    space ~ (table | list | string | number | functionCall | methodCall | lambda | typeAnnotation | infixExpr | simpleExpr) ~ space
  )

  private def simpleExpr[$: P]: P[Expr] = P(begin ~ identifier ~ end).map {
    case (begin, id, end) => StringExpr(id, loc(begin, end))
  }

  private def program[$: P]: P[Seq[Expr]] = P(expr.rep(sep = P(";" ~/ space) | space))
}

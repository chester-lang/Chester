package chester.lang

import fastparse.*
import NoWhitespace.*

import java.lang.Character.{isDigit, isLetter}

object Parser {

  def parseExpression(input: String): Parsed[Expr] = parse(input, expr(_))

  def parseExpressions(input: String): Parsed[Seq[Expr]] = parse(input, program(_))

  def parseIdentifier(input: String): Parsed[String] = parse(input, identifier(_))  // Add this line

  private def space[$: P]: P[Unit] = P(CharsWhileIn(" \t\r\n", 0))

  private def location[$: P]: P[SourceLocation] = P(Index).map(idx => SourceLocation("filename.scala", idx, idx))


  private def stringChars(c: Char) = c != '\"' && c != '\\'

  private def hexDigit[$: P] = P(CharIn("0-9a-fA-F"))

  private def unicodeEscape[$: P] = P("u" ~ hexDigit ~ hexDigit ~ hexDigit ~ hexDigit)

  private def escape[$: P] = P("\\" ~ (CharIn("\"/\\\\bfnrt") | unicodeEscape))

  private def strChars[$: P] = P(CharsWhile(stringChars))

  private def string[$: P] =
    P(location ~ space ~ "\"" ~/ (strChars | escape).rep.! ~ "\"").map {
      case (loc, value) => StringExpr(value, loc)
    }

  private def integer[$: P]: P[IntExpr] = P(location ~ CharIn("+\\-").? ~ CharsWhileIn("0-9").rep(1).!).map {
    case (loc, value) => IntExpr(value.toInt, loc)
  }

  private def float[$: P]: P[FloatExpr] = P(location ~ CharIn("+\\-").? ~ CharsWhileIn("0-9").rep(1) ~ "." ~ CharsWhileIn("0-9").rep(1).!).map {
    case (loc, value) => FloatExpr(value.toDouble, loc)
  }

  private def number[$: P]: P[Expr] = P(float | integer)

  private def table[$: P]: P[TableExpr] = P(location ~ "{" ~/ keyValue.rep(sep = P(";")./) ~ space ~ "}").map {
    case (loc, entries) => TableExpr(entries.toMap, loc)
  }

  private def keyValue[$: P]: P[(String, Expr)] = P(identifier ~/ space ~ "=" ~/ expr).map {
    case (key, value) => (key, value)
  }

  private def list[$: P]: P[ListExpr] = P(location ~ "[" ~/ expr.rep(sep = P(",")./).? ~ space ~ "]").map {
    case (loc, elements) => ListExpr(elements.getOrElse(Seq()), loc)
  }

  private def functionCall[$: P]: P[FunctionCall] = P(location ~ identifier ~ "(" ~/ space ~ expr.rep(sep = P("," ~/ space)) ~ space ~ ")").map {
    case (loc, name, args) => FunctionCall(name, args, loc)
  }

  private def infixExpr[$: P]: P[Expr] = P(location ~ simpleExpr ~ space ~ operator ~ space ~ simpleExpr).map {
    case (loc, left, op, right) => InfixExpr(left, op, right, loc)
  }

  private def methodCall[$: P]: P[MethodCall] = P(location ~ simpleExpr ~ "." ~ identifier ~ "(" ~/ space ~ expr.rep(sep = P("," ~/ space)) ~ space ~ ")").map {
    case (loc, target, method, args) => MethodCall(target, method, args, loc)
  }

  private def lambda[$: P]: P[LambdaExpr] = P(location ~ "(" ~/ space ~ params ~ space ~ ")" ~ space ~ "=>" ~/ space ~ expr).map {
    case (loc, params, body) => LambdaExpr(params, body, loc)
  }

  private def params[$: P]: P[Seq[(String, Option[String])]] = P(param.rep(sep = P("," ~/ space)))

  private def param[$: P]: P[(String, Option[String])] = P(identifier ~ space ~ (":" ~/ space ~ identifier).?)

  private def typeAnnotation[$: P]: P[TypeAnnotation] = P(location ~ simpleExpr ~ ":" ~/ space ~ identifier).map {
    case (loc, expr, tpe) => TypeAnnotation(expr, tpe, loc)
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

  private def simpleExpr[$: P]: P[Expr] = P(location ~ identifier).map {
    case (loc, id) => StringExpr(id, loc)
  }

  private def program[$: P]: P[Seq[Expr]] = P(expr.rep(sep = P(";" ~/ space) | space))
}

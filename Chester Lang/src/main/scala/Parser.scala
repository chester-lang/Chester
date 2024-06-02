package chester.lang

import fastparse._
import NoWhitespace._

object Parser {

  def parseExpression(input: String): Parsed[Expr] = parse(input, expr(_))

  def parseExpressions(input: String): Parsed[Seq[Expr]] = parse(input, program(_))

  private def ws[$: P]: P[Unit] = P(CharsWhileIn(" \t\r\n").rep)

  private def location[$: P]: P[SourceLocation] = P(Index).map(idx => SourceLocation("filename.scala", idx, idx))

  private def string[$: P]: P[StringExpr] = P(location ~ "\"" ~/ CharsWhile(_ != '"').! ~ "\"").map {
    case (loc, value) => StringExpr(value, loc)
  }

  private def table[$: P]: P[TableExpr] = P(location ~ "{" ~/ ws ~ keyValue.rep(sep = P(";") | ws) ~ ws ~ "}").map {
    case (loc, entries) => TableExpr(entries.toMap, loc)
  }

  private def keyValue[$: P]: P[(String, Expr)] = P(identifier ~ ws ~ "=" ~ ws ~ expr).map {
    case (key, value) => (key, value)
  }

  private def list[$: P]: P[ListExpr] = P(location ~ "[" ~/ ws ~ expr.rep(sep = P(",") ~ ws) ~ ws ~ "]").map {
    case (loc, elements) => ListExpr(elements, loc)
  }

  private def functionCall[$: P]: P[FunctionCall] = P(location ~ identifier ~ "(" ~/ ws ~ expr.rep(sep = P(",") ~ ws) ~ ws ~ ")").map {
    case (loc, name, args) => FunctionCall(name, args, loc)
  }

  private def infixExpr[$: P]: P[Expr] = P(simpleExpr ~ ws ~ operator ~ ws ~ simpleExpr).map {
    case (left, op, right) => InfixExpr(left, op, right, left.location)
  }

  private def methodCall[$: P]: P[MethodCall] = P(location ~ simpleExpr ~ "." ~ identifier ~ "(" ~/ ws ~ expr.rep(sep = P(",") ~ ws) ~ ws ~ ")").map {
    case (loc, target, method, args) => MethodCall(target, method, args, loc)
  }

  private def lambda[$: P]: P[LambdaExpr] = P(location ~ "(" ~ ws ~ params ~ ws ~ ")" ~ ws ~ "=>" ~ ws ~ expr).map {
    case (loc, params, body) => LambdaExpr(params, body, loc)
  }

  private def params[$: P]: P[Seq[(String, Option[String])]] = P(param.rep(sep = P(",") ~ ws))

  private def param[$: P]: P[(String, Option[String])] = P(identifier ~ ws ~ (":" ~ ws ~ identifier).?)

  private def typeAnnotation[$: P]: P[TypeAnnotation] = P(location ~ simpleExpr ~ ":" ~ ws ~ identifier).map {
    case (loc, expr, tpe) => TypeAnnotation(expr, tpe, loc)
  }

  private def identifier[$: P]: P[String] = P(CharIn("a-zA-Z") ~ CharsWhileIn("a-zA-Z0-9").rep.!)

  private def operator[$: P]: P[String] = P(CharIn("+-*/").!)

  private def expr[$: P]: P[Expr] = P(
    ws ~ (
      table | list | string | functionCall | methodCall | lambda | typeAnnotation | infixExpr | simpleExpr
      ) ~ ws
  )

  private def simpleExpr[$: P]: P[Expr] = P(location ~ identifier).map {
    case (loc, id) => StringExpr(id, loc)  // Simplified; you may want to handle different types
  }

  private def program[$: P]: P[Seq[Expr]] = P(expr.rep(sep = P(";") | ws))
}

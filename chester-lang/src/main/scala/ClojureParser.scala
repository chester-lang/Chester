package chester.lang

import fastparse._
import NoWhitespace._

case class ClojureParser(fileName: String) {

  def parseExpression(input: String): Parsed[Expr] = parse(input, expr(_))

  def parseExpressions(input: String): Parsed[Seq[Expr]] = parse(input, program(_))

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

  private def clojureList[$: P]: P[ListExpr] = P(begin ~ "[" ~/ expr.rep(sep = space) ~ space ~ "]" ~ end).map {
    case (begin, elements, end) => ListExpr(elements, loc(begin, end))
  }

  private def clojureTable[$: P]: P[TableExpr] = P(begin ~ "{" ~/ keyValue.rep(sep = space) ~ space ~ "}" ~ end).map {
    case (begin, entries, end) => TableExpr(entries.toMap, loc(begin, end))
  }

  private def keyValue[$: P]: P[(Expr, Expr)] = P(expr ~/ space ~ expr).map {
    case (key, value) => (key, value)
  }

  private def clojureFunctionCall[$: P]: P[FunctionCall] = P(begin ~ "(" ~/ expr ~ space ~ expr.rep(sep = space) ~ space ~ ")" ~ end).map {
    case (begin, func, args, end) => FunctionCall(func match {
      case StringExpr(name, _) => name
      case _ => throw new RuntimeException("Invalid function expression")
    }, args, loc(begin, end))
  }

  private def clojureMethodCall[$: P]: P[MethodCall] = P(begin ~ "(" ~ "." ~ identifier ~ space ~ expr ~ ")" ~ end).map {
    case (begin, method, target, end) => MethodCall(target, method, Seq.empty, loc(begin, end))
  }

  private def expr[$: P]: P[Expr] = P(
    space ~ (clojureTable | clojureList | string | number | clojureFunctionCall | clojureMethodCall) ~ space
  )

  private def program[$: P]: P[Seq[Expr]] = P(expr.rep(sep = P(";" ~/ space) | space))

  def identifier[$: P]: P[String] = P(CharsWhileIn("a-zA-Z").! ~ CharsWhileIn("a-zA-Z0-9_").!).map { case (a, b) => a + b }
}

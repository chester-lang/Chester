package chester.lang

import fastparse._
import NoWhitespace._

case class ClojureParser(fileName: String) {

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

  private def clojureList[$: P]: P[ListAST] = P(begin ~ "[" ~/ expr.rep(sep = space) ~ space ~ "]" ~ end).map {
    case (begin, elements, end) => ListAST(elements, loc(begin, end))
  }

  private def clojureTable[$: P]: P[TableAST] = P(begin ~ "{" ~/ keyValue.rep(sep = space) ~ space ~ "}" ~ end).map {
    case (begin, entries, end) => TableAST(entries.toMap, loc(begin, end))
  }

  private def keyValue[$: P]: P[(AST, AST)] = P(expr ~/ space ~ expr).map {
    case (key, value) => (key, value)
  }

  private def clojureFunctionCall[$: P]: P[FunctionCall] = P(begin ~ "(" ~/ expr ~ space ~ expr.rep(sep = space) ~ space ~ ")" ~ end).map {
    case (begin, func, args, end) => FunctionCall(func match {
      case StringAST(name, _) => name
      case _ => throw new RuntimeException("Invalid function expression")
    }, args, loc(begin, end))
  }

  private def clojureMethodCall[$: P]: P[MethodCall] = P(begin ~ "(" ~ "." ~ identifier ~ space ~ expr ~ ")" ~ end).map {
    case (begin, method, target, end) => MethodCall(target, method, Seq.empty, loc(begin, end))
  }

  private def expr[$: P]: P[AST] = P(
    space ~ (clojureTable | clojureList | string | number | clojureFunctionCall | clojureMethodCall) ~ space
  )

  private def program[$: P]: P[Seq[AST]] = P(expr.rep(sep = P(";" ~/ space) | space))

  def identifier[$: P]: P[String] = P(CharsWhileIn("a-zA-Z").! ~ CharsWhileIn("a-zA-Z0-9_").!).map { case (a, b) => a + b }
}

package chester.parser

import fastparse.*
import chester.syntax.concrete.Expr

object Main {
  def main(args: Array[String]): Unit = {
    val input = "0x" // Example input string
    val fileName = "testFile"

    Parser.parseExpression(fileName, input) match {
      case Parsed.Success(expr, index) =>
        println(s"Parsing succeeded: $expr")
      case Parsed.Failure(label, index, extra) =>
        println(s"Parsing failed: $label at index $index")
        println(extra.trace().longMsg)
    }
  }
}

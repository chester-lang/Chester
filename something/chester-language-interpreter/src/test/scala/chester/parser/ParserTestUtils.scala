package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete.*
import munit.Assertions.{assertEquals, fail}

def parseAndCheck(input: String, expected: Expr): Unit = {
  val resultignored = Parser.parseExpression("testFile", input) // it must parse with location
  val result = Parser.parseExpression("testFile", input, ignoreLocation = true)
  result match {
    case Parsed.Success(value, _) =>
      assertEquals(value, expected, s"Failed for input: $input")
    case Parsed.Failure(label, index, extra) =>
      fail(s"Parsing failed for input: $input $label $index ${extra.trace().msg}")
  }
}
package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete.*
import munit.Assertions.{assertEquals, fail}

def parseAndCheck(input: String, expected: Expr): Unit = {
  val resultignored = Parser.parseContent("testFile", input) // it must parse with location
  Parser.parseContent("testFile", input, ignoreLocation = true) match {
    case Right(value) =>
      assertEquals(value, expected, s"Failed for input: $input")
    case Left(error) =>
      fail(s"Parsing failed for input: $input ${error.message} at index ${error.index}")
  }
}

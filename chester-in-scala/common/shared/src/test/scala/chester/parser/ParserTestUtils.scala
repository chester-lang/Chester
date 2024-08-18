package chester.parser

import chester.syntax.concrete.*
import fastparse.*
import munit.Assertions.{assertEquals, fail}
import munit.FunSuite

def parseAndCheck(input: String, expected: Expr): Unit = {
  val resultignored = Parser.parseContent("testFile", input) // it must parse with location
  Parser.parseContent("testFile", input, ignoreLocation = true) match {
    case Right(value) =>
      assertEquals(value.descentAndApply(_.updateMeta(x=>None)), expected, s"Failed for input: $input")
    case Left(error) =>
      fail(s"Parsing failed for input: $input ${error.message} at index ${error.index}")
  }
}

def getParsed(input: String): Expr = {
  val resultignored = Parser.parseContent("testFile", input) // it must parse with location
  Parser.parseContent("testFile", input, ignoreLocation = true) match {
    case Right(value) => value
    case Left(error) => fail(s"Parsing failed for input: $input ${error.message} at index ${error.index}")
  }
}

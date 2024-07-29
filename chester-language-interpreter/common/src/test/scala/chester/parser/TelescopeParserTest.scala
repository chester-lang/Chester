package chester.parser

import munit.FunSuite
import fastparse._
import chester.syntax.concrete._
import chester.parser._

class TelescopeParserTest extends FunSuite {
  test("parse tuple with simple arguments") {
    val input = "(a, b, c)"
    val expected = Tuple(Vector(
      Identifier("a"),
      Identifier("b"),
      Identifier("c")
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with simple arguments ending comma") {
    val input = "(a, b, c,)"
    val expected = Tuple(Vector(
      Identifier("a"),
      Identifier("b"),
      Identifier("c")
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple empty") {
    val input = "()"
    val expected = Tuple(Vector())
    parseAndCheck(input, expected)
  }

  test("parse tuple with simple arguments with comments") {
    val input = "(a, // comment \n b, c)"
    val expected = Tuple(Vector(
      Identifier("a"),
      Identifier("b"),
      Identifier("c")
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with arguments having type") {
    val input = "(a: Integer)"
    val expected = Tuple(Vector(
      OpSeq(Vector(Identifier("a"), Identifier(":"), Identifier("Integer")))
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with arguments having types") {
    val input = "(a: Integer, b: String, c: Double)"
    val expected = Tuple(Vector(
      OpSeq(Vector(Identifier("a"), Identifier(":"), Identifier("Integer"))),
      OpSeq(Vector(Identifier("b"), Identifier(":"), Identifier("String"))),
      OpSeq(Vector(Identifier("c"), Identifier(":"), Identifier("Double")))
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with arguments having default value") {
    val input = "(a = 1)"
    val expected = Tuple(Vector(
      OpSeq(Vector(Identifier("a"), Identifier("="), IntegerLiteral(1)))
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with arguments having default values") {
    val input = "(a = 1, b = 2, c = 3)"
    val expected = Tuple(Vector(
      OpSeq(Vector(Identifier("a"), Identifier("="), IntegerLiteral(1))),
      OpSeq(Vector(Identifier("b"), Identifier("="), IntegerLiteral(2))),
      OpSeq(Vector(Identifier("c"), Identifier("="), IntegerLiteral(3)))
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with arguments having types and default values") {
    val input = "(a: Integer = 1, b: String = \"test\", c: Double = 3.14)"
    val expected = Tuple(Vector(
      OpSeq(Vector(Identifier("a"), Identifier(":"), Identifier("Integer"), Identifier("="), IntegerLiteral(1))),
      OpSeq(Vector(Identifier("b"), Identifier(":"), Identifier("String"), Identifier("="), StringLiteral("test"))),
      OpSeq(Vector(Identifier("c"), Identifier(":"), Identifier("Double"), Identifier("="), DoubleLiteral(BigDecimal(3.14))))
    ))
    parseAndCheck(input, expected)
  }

  test("parse tuple with arguments without names") {
    val input = "(1, 2, 3)"
    val expected = Tuple(Vector(
      IntegerLiteral(1),
      IntegerLiteral(2),
      IntegerLiteral(3)
    ))
    parseAndCheck(input, expected)
  }

  test("parse generics with arguments without names") {
    val input = "<Integer>"
    val expected = Generics(Vector(
      Identifier("Integer")
    ))
    parseAndCheck(input, expected)
  }
}

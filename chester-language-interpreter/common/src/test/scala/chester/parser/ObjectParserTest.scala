package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class ObjectParserTest extends FunSuite {

  test("parse empty object") {
    val input = "{}"
    val expected = ObjectExpr(Vector())
    parseAndCheck(input, expected)
  }

  test("parse object with single field") {
    val input = "{ a = 1 }"
    val expected = ObjectExpr(Vector(
      Identifier("a") -> IntegerLiteral(1)
    ))
    parseAndCheck(input, expected)
  }

  test("parse object with multiple fields") {
    val input = "{ a = 1, b = 2, c = 3 }"
    val expected = ObjectExpr(Vector(
      Identifier("a") -> IntegerLiteral(1),
      Identifier("b") -> IntegerLiteral(2),
      Identifier("c") -> IntegerLiteral(3)
    ))
    parseAndCheck(input, expected)
  }

  test("parse object with nested object") {
    val input = "{ a = { b = 2 }, c = 3 }"
    val expected = ObjectExpr(Vector(
      Identifier("a") -> ObjectExpr(Vector(
        Identifier("b") -> IntegerLiteral(2)
      )),
      Identifier("c") -> IntegerLiteral(3)
    ))
    parseAndCheck(input, expected)
  }

  test("parse object with mixed types") {
    val input = "{ a = 1, b = \"hello\", c = [1, 2, 3], d = { e = 4 } }"
    val expected = ObjectExpr(Vector(
      Identifier("a") -> IntegerLiteral(1),
      Identifier("b") -> StringLiteral("hello"),
      Identifier("c") -> ListExpr(Vector(
        IntegerLiteral(1),
        IntegerLiteral(2),
        IntegerLiteral(3)
      )),
      Identifier("d") -> ObjectExpr(Vector(
        Identifier("e") -> IntegerLiteral(4)
      ))
    ))
    parseAndCheck(input, expected)
  }

  test("parse object with trailing comma") {
    val input = "{ a = 1, b = 2, }"
    val expected = ObjectExpr(Vector(
      Identifier("a") -> IntegerLiteral(1),
      Identifier("b") -> IntegerLiteral(2)
    ))
    parseAndCheck(input, expected)
  }
}
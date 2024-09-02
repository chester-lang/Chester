package chester.parser

import chester.parser.*
import chester.syntax.concrete.*
import munit.FunSuite

class FunctionCallParserTest extends FunSuite {

  test("parse simple function call with no arguments") {
    val input = "func()"
    val expected = FunctionCall(Identifier("func"), Tuple(Vector()))
    parseAndCheck(input, expected)
  }

  test("parse function call with multiple arguments") {
    val input = "multiply(2, 3)"
    val expected = FunctionCall(Identifier("multiply"), Tuple(Vector(IntegerLiteral(2), IntegerLiteral(3))))
    parseAndCheck(input, expected)
  }

  test("parse function call with multiple arguments and symbol identifier") {
    val input = "+(2, 3)"
    val expected = FunctionCall(Identifier("+"), Tuple(Vector(IntegerLiteral(2), IntegerLiteral(3))))
    parseAndCheck(input, expected)
  }

  test("parse function call with mixed type arguments") {
    val input = "createPerson(\"John\", 30, true)"
    val expected = FunctionCall(Identifier("createPerson"), Tuple(Vector(StringLiteral("John"), IntegerLiteral(30), Identifier("true"))))
    parseAndCheck(input, expected)
  }

  test("parse function call with generics and mixed type arguments") {
    val input = "createPerson[Integer](\"John\", 30, true)"
    val expected = FunctionCall(
      FunctionCall(Identifier("createPerson"), ListExpr(Vector(Identifier("Integer")))),
      Tuple(Vector(StringLiteral("John"), IntegerLiteral(30), Identifier("true")))
    )
    parseAndCheck(input, expected)
  }
}

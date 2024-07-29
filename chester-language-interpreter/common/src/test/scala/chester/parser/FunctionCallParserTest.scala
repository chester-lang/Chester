package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class FunctionCallParserTest extends FunSuite {

  test("parse simple function call with no arguments") {
    val input = "func()"
    val expected = FunctionCall(Identifier("func"), Telescope(Vector()))
    parseAndCheck(input, expected)
  }

  test("parse function call with multiple arguments") {
    val input = "multiply(2, 3)"
    val expected = FunctionCall(Identifier("multiply"), Telescope(Vector(Arg.of(IntegerLiteral(2)), Arg.of(IntegerLiteral(3)))))
    parseAndCheck(input, expected)
  }

  test("parse function call with multiple arguments and symbol identifier") {
    val input = "+(2, 3)"
    val expected = FunctionCall(Identifier("+"), Telescope(Vector(Arg.of(IntegerLiteral(2)), Arg.of(IntegerLiteral(3)))))
    parseAndCheck(input, expected)
  }

  test("parse function call with mixed type arguments") {
    val input = "createPerson(\"John\", 30, true)"
    val expected = FunctionCall(Identifier("createPerson"), Telescope(Vector(Arg.of(StringLiteral("John")), Arg.of(IntegerLiteral(30)), Arg.of(Identifier("true")))))
    parseAndCheck(input, expected)
  }

  test("parse function call with mixed type arguments") {
    val input = "createPerson<Integer>(\"John\", 30, true)"
    val expected = FunctionCall(FunctionCall(Identifier("createPerson"), Telescope(Vector(
      Arg.of(
        (
          Identifier(
            name = "Integer",
            sourcePos = None
          )
          )
      )), true)), Telescope(Vector(
      Arg.of(StringLiteral("John")), Arg.of(IntegerLiteral(30)), Arg.of(Identifier("true")))))
    parseAndCheck(input, expected)
  }

}
package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class FunctionCallParserTest extends FunSuite {
  import chester.syntax.concrete._
  import chester.parser._

  test("parse simple function call with no arguments") {
    val input = "func()"
    val expected = FunctionCall(Identifier("func"), Telescope(Vector()))
    parseAndCheck(input, expected)
  }

  test("parse function call with multiple arguments") {
    val input = "multiply(2, 3)"
    val expected = FunctionCall(Identifier("multiply"), Telescope(Vector(Arg(IntegerLiteral(2)), Arg(IntegerLiteral(3)))))
    parseAndCheck(input, expected)
  }

  test("parse function call with mixed type arguments") {
    val input = "createPerson(\"John\", 30, true)"
    val expected = FunctionCall(Identifier("createPerson"), Telescope(Vector(Arg(StringLiteral("John")), Arg(IntegerLiteral(30)), Arg(Identifier("true")))))
    parseAndCheck(input, expected)
  }

}
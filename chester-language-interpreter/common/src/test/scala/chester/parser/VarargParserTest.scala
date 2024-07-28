package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class VarargParserTest extends FunSuite {

  test("parse function call with single vararg") {
    val input = "func(1, 2, 3...)"
    val expected = FunctionCall(
      Identifier("func"),
      Telescope(Vector(
        Arg.of(IntegerLiteral(1)),
        Arg.of(IntegerLiteral(2)),
        Arg(Vector.empty, None, None, Some(IntegerLiteral(3)), vararg = true)
      ))
    )
    parseAndCheck(input, expected)
  }

  test("parse function def with single vararg") {
    val input = "func(x : Integer ...)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "func",
          sourcePos = None
        ),
        telescope = Telescope(
          args = Vector(
            Arg(
              name = Some(
                value = Identifier(
                  name = "x",
                  sourcePos = None
                )
              ),
              ty = Some(
                value = Identifier(
                  name = "Integer",
                  sourcePos = None
                )
              ),
              exprOrDefault = None,
              vararg = true
            )
          ),
          sourcePos = None
        ),
        sourcePos = None
      )
    parseAndCheck(input, expected)
  }

}

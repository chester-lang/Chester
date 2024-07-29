package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class VarargParserTest extends FunSuite {

  test("parse function call with single vararg") {
    val input = "func(1, 2, xs*)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "func",
          sourcePos = None
        ),
        telescope = Tuple(
          terms = Vector(
            IntegerLiteral(
              value = 1,
              sourcePos = None
            ),
            IntegerLiteral(
              value = 2,
              sourcePos = None
            ),
            OpSeq(
              seq = Vector(
                Identifier(
                  name = "xs",
                  sourcePos = None
                ),
                Identifier(
                  name = "*",
                  sourcePos = None
                )
              ),
              sourcePos = None
            )
          ),
          sourcePos = None
        ),
        sourcePos = None
      )
    parseAndCheck(input, expected)
  }

  test("parse function def with single vararg") {
    val input = "func(x : Integer *)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "func",
          sourcePos = None
        ),
        telescope = Tuple(
          terms = Vector(
            OpSeq(
              seq = Vector(
                Identifier(
                  name = "x",
                  sourcePos = None
                ),
                Identifier(
                  name = ":",
                  sourcePos = None
                ),
                Identifier(
                  name = "Integer",
                  sourcePos = None
                ),
                Identifier(
                  name = "*",
                  sourcePos = None
                )
              ),
              sourcePos = None
            )
          ),
          sourcePos = None
        ),
        sourcePos = None
      )
    parseAndCheck(input, expected)
  }

}

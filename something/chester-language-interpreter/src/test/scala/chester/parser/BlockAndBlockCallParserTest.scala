package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class BlockAndBlockCallParserTest extends FunSuite {

  test("parse block with multiple statements without newlines") {
    val input = "{f(1); g(2); 3}"
    val expected = Block(
      Vector(
        FunctionCall(Identifier("f"), Telescope.of(Arg(IntegerLiteral(1)))),
        FunctionCall(Identifier("g"), Telescope.of(Arg(IntegerLiteral(2))))
      ),
      IntegerLiteral(3)
    )
    parseAndCheck(input, expected)
  }

  test("parse block with multiple statements with newlines") {
    val input = "{\n  f(1);\n  g(2);\n  3\n}"
    val expected = Block(
      Vector(
        FunctionCall(Identifier("f"), Telescope.of(Arg(IntegerLiteral(1)))),
        FunctionCall(Identifier("g"), Telescope.of(Arg(IntegerLiteral(2))))
      ),
      IntegerLiteral(3)
    )
    parseAndCheck(input, expected)
  }

  test("parse block call with nested block without newlines") {
    val input = "f{g(1); 2}"
    val expected = FunctionCall(
      Identifier("f"),
      Telescope.of(Arg(Block(
        Vector(
          FunctionCall(Identifier("g"), Telescope.of(Arg(IntegerLiteral(1))))
        ),
        IntegerLiteral(2)
      )))
    )
    parseAndCheck(input, expected)
  }

  test("parse block call with nested block with newlines") {
    val input = "f {\n  g(1);\n  2\n}"
    val expected = FunctionCall(
      Identifier("f"),
      Telescope.of(Arg(Block(
        Vector(
          FunctionCall(Identifier("g"), Telescope.of(Arg(IntegerLiteral(1))))
        ),
        IntegerLiteral(2)
      )))
    )
    parseAndCheck(input, expected)
  }
  test("parse function call with block as argument without newlines") {
    val input = "f(1) {2}"
    val expected = FunctionCall(
      FunctionCall(
        Identifier("f"),
        Telescope.of(Arg(IntegerLiteral(1)))
      ),
      Telescope.of(Arg(Block(Vector(), IntegerLiteral(2))))
    )
    parseAndCheck(input, expected)
  }
}

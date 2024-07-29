package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class OpSeqParserTest extends FunSuite {

  test("parse simple opSeq with single operator") {
    val input = "1 + 2"
    val expected = BinOpSeq(Vector(
      IntegerLiteral(1),
      Identifier("+"),
      IntegerLiteral(2)
    ))
    parseAndCheck(input, expected)
  }

  test("parse simple opSeq with single operator 2") {
    val input = "1 *2"
    val expected = BinOpSeq(Vector(
      IntegerLiteral(1),
      Identifier("*"),
      IntegerLiteral(2)
    ))
    parseAndCheck(input, expected)
  }

  test("parse opSeq with multiple operators") {
    val input = "1 + 2 + 3 + 4"
    val expected = BinOpSeq(Vector(
      IntegerLiteral(1),
      Identifier("+"),
      IntegerLiteral(2),
      Identifier("+"),
      IntegerLiteral(3),
      Identifier("+"),
      IntegerLiteral(4)
    ))
    parseAndCheck(input, expected)
  }

  test("parse opSeq with mixed operators and precedence") {
    val input = "1 + 2 * 4 + 5"
    val expected = BinOpSeq(Vector(
      IntegerLiteral(1),
      Identifier("+"),
      IntegerLiteral(2),
      Identifier("*"),
      IntegerLiteral(4),
      Identifier("+"),
      IntegerLiteral(5)
    ))
    parseAndCheck(input, expected)
  }

  test("parse prefix") {
    val input = "+ x"
    val expected = BinOpSeq(Vector(
      Identifier("+"),
      Identifier("x"),
    ))
    parseAndCheck(input, expected)
  }

  test("parse prefix2") {
    val input = "not x"
    val expected = BinOpSeq(Vector(
      Identifier("not"),
      Identifier("x"),
    ))
    parseAndCheck(input, expected)
  }

  test("parse mixfix") {
    val input = "if x then q else w"
    val expected = BinOpSeq(Vector(
      Identifier("if"),
      Identifier("x"),
      Identifier("then"),
      Identifier("q"),
      Identifier("else"),
      Identifier("w"),
    ))
    parseAndCheck(input, expected)
  }

  test("parse mixfix2") {
    val input = "if x then f(if o then a else b) else w"
    val expected = BinOpSeq(Vector(
      Identifier(
        name = "if",
        sourcePos = None
      ),
      Identifier(
        name = "x",
        sourcePos = None
      ),
      Identifier(
        name = "then",
        sourcePos = None
      ),
      FunctionCall(
        function = Identifier(
          name = "f",
          sourcePos = None
        ),
        telescope = Telescope(
          args = Vector(
            Arg(
              decorations = Vector(),
              name = None,
              ty = None,
              exprOrDefault = Some(
                value = BinOpSeq(
                  seq = Vector(
                    Identifier(
                      name = "if",
                      sourcePos = None
                    ),
                    Identifier(
                      name = "o",
                      sourcePos = None
                    ),
                    Identifier(
                      name = "then",
                      sourcePos = None
                    ),
                    Identifier(
                      name = "a",
                      sourcePos = None
                    ),
                    Identifier(
                      name = "else",
                      sourcePos = None
                    ),
                    Identifier(
                      name = "b",
                      sourcePos = None
                    )
                  ),
                  sourcePos = None
                )
              )
            )
          ),
          sourcePos = None
        ),
        sourcePos = None
      ),
      Identifier(
        name = "else",
        sourcePos = None
      ),
      Identifier(
        name = "w",
        sourcePos = None
      )
    ))
    parseAndCheck(input, expected)
  }

    test("parse opSeq with mixed operators without spaces") {
      val input = "1+2*4+5"
      val expected = BinOpSeq(Vector(
        IntegerLiteral(1),
        Identifier("+"),
        IntegerLiteral(2),
        Identifier("*"),
        IntegerLiteral(4),
        Identifier("+"),
        IntegerLiteral(5)
      ))
      parseAndCheck(input, expected)
    }

  test("parse opSeq with not") {
    val input = "!1"
    val expected = BinOpSeq(Vector(
      Identifier("!"),
      IntegerLiteral(1)
    ))
    parseAndCheck(input, expected)
  }
  test("parse opSeq with and") {
    val input = "1 and 5"
    val expected = BinOpSeq(Vector(
      IntegerLiteral(1),
      Identifier("and"),
      IntegerLiteral(5)
    ))
    parseAndCheck(input, expected)
  }
  test("parse val input") {
    val input = "val input = \"1 -> 5\""
    val expected =
      BinOpSeq(
        seq = Vector(
          Identifier(
            name = "val",
            sourcePos = None
          ),
          Identifier(
            name = "input",
            sourcePos = None
          ),
          Identifier(
            name = "=",
            sourcePos = None
          ),
          StringLiteral(
            value = "1 -> 5",
            sourcePos = None
          )
        ),
        sourcePos = None
      )
    parseAndCheck(input, expected)
  }

  test("parse opSeq with ->") {
    val input = "1 -> 5"
    val expected = BinOpSeq(Vector(
      IntegerLiteral(1),
      Identifier("->"),
      IntegerLiteral(5)
    ))
    parseAndCheck(input, expected)
  }
  test("parse infix with block") {
    val input = "so getthen { doSomething }"
    val expected =
      BinOpSeq(
        seq = Vector(
          Identifier(
            name = "so",
            sourcePos = None
          ),
          Identifier(
            name = "getthen",
            sourcePos = None
          ),
          Block(
            heads = Vector(),
            tail = Identifier(
              name = "doSomething",
              sourcePos = None
            ),
            sourcePos = None
          )
        ),
        sourcePos = None
      )
    parseAndCheck(input, expected)
  }


  test("parse function call with") {
    val input = "+(2 + 3)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "+",
          sourcePos = None
        ),
        telescope = Telescope(
          args = Vector(
            Arg(
              decorations = Vector(),
              name = None,
              ty = None,
              exprOrDefault = Some(
                value = BinOpSeq(
                  seq = Vector(
                    IntegerLiteral(
                      value = 2,
                      sourcePos = None
                    ),
                    Identifier(
                      name = "+",
                      sourcePos = None
                    ),
                    IntegerLiteral(
                      value = 3,
                      sourcePos = None
                    )
                  ),
                  sourcePos = None
                )
              )
            )
          ),
          sourcePos = None
        ),
        sourcePos = None
      )
    parseAndCheck(input, expected)
  }

  test("some macro") {
    val input = "def apply(heads: Vector<Expr>, tail: Expr): Block = Block(heads, Some(tail), None)"
    val expected = BinOpSeq(Vector(Identifier("def"), FunctionCall(Identifier("apply"),Telescope(Vector(Arg(Vector(),Some(Identifier("heads")),Some(FunctionCall(Identifier("Vector"),Telescope(Vector(Arg(Vector(),None,None,Some(Identifier("Expr")),false)),true,None),None)),None,false), Arg(Vector(),Some(Identifier("tail")),Some(Identifier("Expr")),None,false)),false,None),None), Identifier(":"), Identifier("Block"), Identifier("="), FunctionCall(Identifier("Block"),Telescope(Vector(Arg(Vector(),None,None,Some(Identifier("heads")),false), Arg(Vector(),None,None,Some(FunctionCall(Identifier("Some"),Telescope(Vector(Arg(Vector(),None,None,Some(Identifier("tail")),false)),false,None),None)),false), Arg(Vector(),None,None,Some(Identifier("None")),false)),false,None),None)),None)
    assertEquals(getParsed(input), expected)
  }

  test("more macro") {
    val input = "def apply(heads: Vector<Expr>, tail: Expr): Block"
    val expected = BinOpSeq(Vector(Identifier("def"), FunctionCall(Identifier("apply"),Telescope(Vector(Arg(Vector(),Some(Identifier("heads")),Some(FunctionCall(Identifier("Vector"),Telescope(Vector(Arg.of(Identifier("Expr"))),true,None),None)),None), Arg(Vector(),Some(Identifier("tail")),Some(Identifier("Expr")),None)),false,None),None), Identifier(":"), Identifier("Block")),None)
      assertEquals(getParsed(input), expected)
  }

}

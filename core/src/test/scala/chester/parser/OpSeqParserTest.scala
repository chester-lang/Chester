package chester.parser

import chester.parser.*
import chester.syntax.concrete.*
import munit.FunSuite

class OpSeqParserTest extends FunSuite {

  test("parse simple opSeq with single operator") {
    val input = "1 + 2"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("+"),
        IntegerLiteral(2)
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse simple opSeq with single operator 2") {
    val input = "1 *2"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("*"),
        IntegerLiteral(2)
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse opSeq with multiple operators") {
    val input = "1 + 2 + 3 + 4"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("+"),
        IntegerLiteral(2),
        Identifier("+"),
        IntegerLiteral(3),
        Identifier("+"),
        IntegerLiteral(4)
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse opSeq with mixed operators and precedence") {
    val input = "1 + 2 * 4 + 5"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("+"),
        IntegerLiteral(2),
        Identifier("*"),
        IntegerLiteral(4),
        Identifier("+"),
        IntegerLiteral(5)
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse prefix") {
    val input = "+ x"
    val expected = OpSeq(
      Vector(
        Identifier("+"),
        Identifier("x")
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse prefix2") {
    val input = "not x"
    val expected = OpSeq(
      Vector(
        Identifier("not"),
        Identifier("x")
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse mixfix") {
    val input = "if x then q else w"
    val expected = OpSeq(
      Vector(
        Identifier("if"),
        Identifier("x"),
        Identifier("then"),
        Identifier("q"),
        Identifier("else"),
        Identifier("w")
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse mixfix2") {
    val input = "if x then f(if o then a else b) else w"
    val expected = OpSeq(
      Vector(
        Identifier(
          name = "if"
        ),
        Identifier(
          name = "x"
        ),
        Identifier(
          name = "then"
        ),
        FunctionCall(
          function = Identifier(
            name = "f"
          ),
          telescope = Tuple(
            Vector(
              OpSeq(
                seq = Vector(
                  Identifier(
                    name = "if"
                  ),
                  Identifier(
                    name = "o"
                  ),
                  Identifier(
                    name = "then"
                  ),
                  Identifier(
                    name = "a"
                  ),
                  Identifier(
                    name = "else"
                  ),
                  Identifier(
                    name = "b"
                  )
                )
              )
            )
          )
        ),
        Identifier(
          name = "else"
        ),
        Identifier(
          name = "w"
        )
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse opSeq with mixed operators without spaces") {
    val input = "1+2*4+5"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("+"),
        IntegerLiteral(2),
        Identifier("*"),
        IntegerLiteral(4),
        Identifier("+"),
        IntegerLiteral(5)
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse opSeq with not") {
    val input = "!1"
    val expected = OpSeq(
      Vector(
        Identifier("!"),
        IntegerLiteral(1)
      )
    )
    parseAndCheck(input, expected)
  }
  test("parse opSeq with and") {
    val input = "1 and 5"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("and"),
        IntegerLiteral(5)
      )
    )
    parseAndCheck(input, expected)
  }
  test("parse val input") {
    val input = "val input = \"1 -> 5\""
    val expected =
      OpSeq(
        seq = Vector(
          Identifier(
            name = "val"
          ),
          Identifier(
            name = "input"
          ),
          Identifier(
            name = "="
          ),
          StringLiteral(
            value = "1 -> 5"
          )
        )
      )
    parseAndCheck(input, expected)
  }

  test("parse opSeq with ->") {
    val input = "1 -> 5"
    val expected = OpSeq(
      Vector(
        IntegerLiteral(1),
        Identifier("->"),
        IntegerLiteral(5)
      )
    )
    parseAndCheck(input, expected)
  }
  test("parse infix with block") {
    val input = "so getthen { doSomething }"
    val expected =
      OpSeq(
        seq = Vector(
          Identifier(
            name = "so"
          ),
          Identifier(
            name = "getthen"
          ),
          Block(
            heads = Vector(),
            tail = Identifier(
              name = "doSomething"
            )
          )
        )
      )
    parseAndCheck(input, expected)
  }

  test("parse function call with") {
    val input = "+(2 + 3)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "+"
        ),
        telescope = Tuple(
          Vector(
            OpSeq(
              seq = Vector(
                IntegerLiteral(2),
                Identifier("+"),
                IntegerLiteral(3)
              )
            )
          )
        )
      )
    parseAndCheck(input, expected)
  }

  test("some macro") {
    val input =
      "def apply(heads: Vector[Expr], tail: Expr): Block = Block(heads, Some(tail), None)"
    val expected = OpSeq(
      Vector(
        Identifier("def"),
        FunctionCall(
          Identifier("apply"),
          Tuple(
            Vector(
              OpSeq(
                Vector(
                  Identifier("heads"),
                  Identifier(":"),
                  FunctionCall(
                    Identifier("Vector"),
                    ListExpr(Vector(Identifier("Expr")), None),
                    None
                  )
                ),
                None
              ),
              OpSeq(
                Vector(Identifier("tail"), Identifier(":"), Identifier("Expr")),
                None
              )
            ),
            None
          ),
          None
        ),
        Identifier(":"),
        Identifier("Block"),
        Identifier("="),
        FunctionCall(
          Identifier("Block"),
          Tuple(
            Vector(
              Identifier("heads"),
              FunctionCall(
                Identifier("Some"),
                Tuple(Vector(Identifier("tail")), None),
                None
              ),
              Identifier("None")
            ),
            None
          ),
          None
        )
      ),
      None
    )
    assertEquals(getParsed(input), expected)
  }

  test("more macro") {
    val input = "def apply(heads: Vector[Expr], tail: Expr): Block"
    val expected = OpSeq(
      Vector(
        Identifier("def"),
        FunctionCall(
          Identifier("apply"),
          Tuple(
            Vector(
              OpSeq(
                Vector(
                  Identifier("heads"),
                  Identifier(":"),
                  FunctionCall(
                    Identifier("Vector"),
                    ListExpr(Vector(Identifier("Expr")), None),
                    None
                  )
                ),
                None
              ),
              OpSeq(
                Vector(Identifier("tail"), Identifier(":"), Identifier("Expr")),
                None
              )
            ),
            None
          ),
          None
        ),
        Identifier(":"),
        Identifier("Block")
      ),
      None
    )
    assertEquals(getParsed(input), expected)
  }

  test("parse Identifier to IntegerLiteral") {
    val input = "Identifier(\"b\") -> IntegerLiteral(2)"
    val expected = OpSeq(
      Vector(
        FunctionCall(
          Identifier("Identifier"),
          Tuple(Vector(StringLiteral("b")))
        ),
        Identifier("->"),
        FunctionCall(
          Identifier("IntegerLiteral"),
          Tuple(Vector(IntegerLiteral(2)))
        )
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse OpSeq with identifier to integer literal mapping") {
    val input = "Vector(\n  Identifier(\"b\") -> IntegerLiteral(2)\n)"
    val expected = FunctionCall(
      Identifier("Vector"),
      Tuple(
        Vector(
          OpSeq(
            Vector(
              FunctionCall(
                Identifier("Identifier"),
                Tuple(Vector(StringLiteral("b")))
              ),
              Identifier("->"),
              FunctionCall(
                Identifier("IntegerLiteral"),
                Tuple(Vector(IntegerLiteral(2)))
              )
            )
          )
        )
      )
    )
    parseAndCheck(input, expected)
  }

}

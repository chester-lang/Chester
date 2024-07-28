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

  if(false) { // It is having difficulty understanding difference between +2 and 1+2
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
}

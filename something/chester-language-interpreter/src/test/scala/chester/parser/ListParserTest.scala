package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class ListParserTest extends FunSuite {

  test("parse empty list") {
    val input = "[]"
    val expected = ListExpr(Vector())
    parseAndCheck(input, expected)
  }

  test("parse list with single integer") {
    val input = "[123]"
    val expected = ListExpr(Vector(IntegerLiteral(123)))
    parseAndCheck(input, expected)
  }

  test("parse list with multiple integers and trailing comma") {
    val input = "[1, 2, 3,]"
    val expected = ListExpr(Vector(IntegerLiteral(1), IntegerLiteral(2), IntegerLiteral(3)))
    parseAndCheck(input, expected)
  }

  test("parse list with mixed types") {
    val input = "[1, \"string\", 3.14]"
    val expected = ListExpr(Vector(
      IntegerLiteral(1),
      StringLiteral("string"),
      DoubleLiteral(BigDecimal(3.14))
    ))
    parseAndCheck(input, expected)
  }

  test("parse nested list") {
    val input = "[1, [2, 3], [4, [5]]]"
    val expected = ListExpr(Vector(
      IntegerLiteral(1),
      ListExpr(Vector(IntegerLiteral(2), IntegerLiteral(3))),
      ListExpr(Vector(IntegerLiteral(4), ListExpr(Vector(IntegerLiteral(5)))))
    ))
    parseAndCheck(input, expected)
  }
}

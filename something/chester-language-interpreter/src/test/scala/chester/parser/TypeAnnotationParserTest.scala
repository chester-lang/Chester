package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class TypeAnnotationParserTest extends FunSuite {
  import chester.syntax.concrete._
  import chester.parser._

  test("parse simple type annotation") {
    val input = "x: Int"
    val expected = TypeAnnotation(Identifier("x"), Identifier("Int"))
    parseAndCheck(input, expected)
  }

}

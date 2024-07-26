package chester.parser
import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class DotParserTest extends FunSuite {
  test("parse simple dot call") {
    val input = "obj.field"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("field"),
      None
    )
    parseAndCheck(input, expected)
  }

  test("parse dot call with function call") {
    val input = "obj.method()"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Some(Telescope(Vector()))
    )
    parseAndCheck(input, expected)
  }

  test("parse nested dot calls") {
    val input = "obj.field1.field2"
    val expected = DotCall(
      DotCall(
        Identifier("obj"),
        Identifier("field1"),
        None
      ),
      Identifier("field2"),
      None
    )
    parseAndCheck(input, expected)
  }
  test("parse dot call with arguments") {
    val input = "obj.method(arg1, arg2)"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Some(Telescope(Vector(
        Arg(Vector.empty, None, None, Some(Identifier("arg1"))),
        Arg(Vector.empty, None, None, Some(Identifier("arg2")))
      )))
    )
    parseAndCheck(input, expected)
  }

  test("parse dot call followed by function call") {
    val input = "obj.method().anotherMethod()"
    val expected = DotCall(
      DotCall(
        Identifier("obj"),
        Identifier("method"),
        Some(Telescope(Vector()))
      ),
      Identifier("anotherMethod"),
      Some(Telescope(Vector()))
    )
    parseAndCheck(input, expected)
  }

}
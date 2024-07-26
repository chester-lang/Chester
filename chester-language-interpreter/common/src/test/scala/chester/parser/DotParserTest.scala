package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class DotParserTest extends FunSuite {
  test("world.execute(me)") {
    val input = "world.execute(me)"
    val expected = DotCall(
      Identifier("world"),
      Identifier("execute"),
      Vector(Telescope(Vector(
        Arg(Vector.empty, None, None, Some(Identifier("me"))),
      )))
    )
    parseAndCheck(input, expected)
  }
  test("parse simple dot call") {
    val input = "obj.field"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("field"),
      Vector()
    )
    parseAndCheck(input, expected)
  }

  test("parse dot call with function call") {
    val input = "obj.method()"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Vector(Telescope(Vector()))
    )
    parseAndCheck(input, expected)
  }

  test("parse nested dot calls") {
    val input = "obj.field1.field2"
    val expected = DotCall(
      DotCall(
        Identifier("obj"),
        Identifier("field1"),
        Vector()
      ),
      Identifier("field2"),
      Vector()
    )
    parseAndCheck(input, expected)
  }
  test("parse dot call with arguments") {
    val input = "obj.method(arg1, arg2)"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Vector(Telescope(Vector(
        Arg(Vector.empty, None, None, Some(Identifier("arg1"))),
        Arg(Vector.empty, None, None, Some(Identifier("arg2")))
      )))
    )
    parseAndCheck(input, expected)
  }

  test("parse dot call with arguments arguments") {
    val input = "obj.method(arg1, arg2)(arg1)"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Vector(Telescope(Vector(
        Arg(Vector.empty, None, None, Some(Identifier("arg1"))),
        Arg(Vector.empty, None, None, Some(Identifier("arg2")))
      )), Telescope(Vector(
        Arg(Vector.empty, None, None, Some(Identifier("arg1"))),
      )))
    )
    parseAndCheck(input, expected)
  }

  test("parse dot call with arguments block arguments") {
    val input = "obj.method(arg1, arg2){arg1}"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Vector(Telescope(Vector(
        Arg(Vector.empty, None, None, Some(Identifier("arg1"))),
        Arg(Vector.empty, None, None, Some(Identifier("arg2")))
      )), Telescope(Vector(
        Arg.of(Block(Vector(), (Identifier("arg1")))),
      )))
    )
    parseAndCheck(input, expected)
  }

  test("parse dot + call with arguments") {
    val input = "obj.+(arg1, arg2)"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("+"),
      Vector(Telescope(Vector(
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
        Vector(Telescope(Vector()))
      ),
      Identifier("anotherMethod"),
      Vector(Telescope(Vector()))
    )
    parseAndCheck(input, expected)
  }

}
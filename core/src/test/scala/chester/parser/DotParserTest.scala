package chester.parser

import chester.parser.*
import chester.syntax.concrete.*
import fastparse.*
import munit.FunSuite

class DotParserTest extends FunSuite {
  test("world.execute(me)") {
    val input = "world.execute(me)"
    val expected = DotCall(
      Identifier("world"),
      Identifier("execute"),
      Vector(
        Tuple(
          Vector(
            Identifier("me")
          )
        )
      )
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
      Vector(Tuple(Vector()))
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
      Vector(
        Tuple(
          Vector(
            Identifier("arg1"),
            Identifier("arg2")
          )
        )
      )
    )
    parseAndCheck(input, expected)
  }
  test("parse dot call with arguments arguments") {
    val input = "obj.method(arg1, arg2)(arg1)"
    val expected =
      DotCall(
        expr = Identifier(
          name = "obj"
        ),
        field = Identifier(
          name = "method"
        ),
        telescope = Vector(
          Tuple(
            terms = Vector(
              Identifier(
                name = "arg1"
              ),
              Identifier(
                name = "arg2"
              )
            )
          ),
          Tuple(
            terms = Vector(
              Identifier(
                name = "arg1"
              )
            )
          )
        )
      )
    parseAndCheck(input, expected)
  }

  test("parse dot call with arguments block arguments") {
    val input = "obj.method(arg1, arg2){arg1}"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("method"),
      Vector(
        Tuple(Vector(Identifier("arg1"), Identifier("arg2"))),
        Tuple(Vector(Block(Vector(), Some(Identifier("arg1")))))
      )
    )
    parseAndCheck(input, expected)
  }

  test("parse dot + call with arguments") {
    val input = "obj.+(arg1, arg2)"
    val expected = DotCall(
      Identifier("obj"),
      Identifier("+"),
      Vector(Tuple(Vector(Identifier("arg1"), Identifier("arg2"))))
    )
    parseAndCheck(input, expected)
  }

  test("parse dot call followed by function call") {
    val input = "obj.method().anotherMethod()"
    val expected = DotCall(
      DotCall(
        Identifier("obj"),
        Identifier("method"),
        Vector(Tuple(Vector()))
      ),
      Identifier("anotherMethod"),
      Vector(Tuple(Vector()))
    )
    parseAndCheck(input, expected)
  }

}

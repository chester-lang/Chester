package chester.lang

import fastparse._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ParserSpec extends AnyFlatSpec with Matchers {

  val fileName = "testfile.scala"
  val parser = Parser(fileName)

  "Parser" should "parse a string expression correctly" in {
    val input = "\"Hello, World!\""
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(StringExpr(value, location), _) =>
        value should be("Hello, World!")
        location.file should be(fileName)
        location.start should be(0)
        location.end should be(15)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a table expression correctly" in {
    val input = "{x = 1; y = 2 }"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(TableExpr(entries, location), _) =>
        entries.keys should contain allOf ("x", "y")
        location.file should be(fileName)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a list expression correctly" in {
    val input = "[1, 2, 3]"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(ListExpr(elements, location), _) =>
        elements.length should be(3)
        location.file should be(fileName)
        location.start should be(0)
        location.end should be(9)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a table with multiple key-value pairs" in {
    val input = "{ x = 1; y = 2 }"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(TableExpr(entries, location), _) =>
        entries.keys should contain allOf ("x", "y")
        location.file should be(fileName)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a print function call" in {
    val input = "print(\"Hello, World!\")"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(FunctionCall(name, args, location), _) =>
        name should be("print")
        args.head should matchPattern { case StringExpr("Hello, World!", _) => }
        location.file should be(fileName)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a function call with multiple arguments" in {
    val input = "f(1, 2)"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(FunctionCall(name, args, location), _) =>
        name should be("f")
        args should matchPattern {
          case Seq(IntExpr(1, _), IntExpr(2, _)) =>
        }
        location.file should be(fileName)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a method call" in {
    val input = "x.f()"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(MethodCall(target, method, args, location), _) =>
        target should matchPattern { case StringExpr("x", _) => }
        method should be("f")
        args shouldBe empty
        location.file should be(fileName)
        location.start should be(0)
        location.end should be(5)
      case Parsed.Success(expr, _) =>
        fail(s"Unexpected expression: $expr")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a lambda expression" in {
    val input = "(x, y) => x + y"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(LambdaExpr(params, body, location), _) =>
        params should contain inOrder (("x", None), ("y", None))
        location.file should be(fileName)
      case Parsed.Success(expr, _) =>
        fail(s"Unexpected expression: $expr")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a type annotation" in {
    val input = "x: Int"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(TypeAnnotation(expr, tpe, location), _) =>
        expr should matchPattern { case StringExpr("x", _) => }
        tpe should be("Int")
        location.file should be(fileName)
      case Parsed.Success(expr, _) =>
        fail(s"Unexpected expression: $expr")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse an identifier correctly" in {
    val input = "myIdentifier"
    val result = parser.parseIdentifier(input)
    result match {
      case Parsed.Success(value, _) =>
        value should be("myIdentifier")
      case Parsed.Success(expr, _) =>
        fail(s"Unexpected expression: $expr")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse an identifier x correctly" in {
    val input = "x"
    val result = parser.parseIdentifier(input)
    result match {
      case Parsed.Success(value, _) =>
        value should be("x")
      case Parsed.Success(expr, _) =>
        fail(s"Unexpected expression: $expr")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a key-value pair correctly" in {
    val input = "key = 123"
    val result = parser.parseKeyValue(input)
    result match {
      case Parsed.Success((key, IntExpr(value, location)), _) =>
        key should be("key")
        value should be(123)
        location.file should be(fileName)
      case Parsed.Success(expr, _) =>
        fail(s"Unexpected expression: $expr")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }
}

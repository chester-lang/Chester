package chester.lang

import fastparse._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ParserSpec extends AnyFlatSpec with Matchers {

  "Parser" should "parse a string expression correctly" in {
    val input = "\"Hello, World!\""
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(StringExpr(value, _), _) =>
        value should be ("Hello, World!")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a table expression correctly" in {
    val input = "{x = 1; y = 2 }"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(TableExpr(entries, _), _) =>
        entries.keys should contain allOf ("x", "y")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a list expression correctly" in {
    val input = "[1, 2, 3]"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(ListExpr(elements, _), _) =>
        elements.length should be (3)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a table with multiple key-value pairs" in {
    val input = "{ x = 1; y = 2 }"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(TableExpr(entries, _), _) =>
        entries.keys should contain allOf ("x", "y")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a print function call" in {
    val input = "print(\"Hello, World!\")"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(FunctionCall(name, args, _), _) =>
        name should be ("print")
        args.head should matchPattern { case StringExpr("Hello, World!", _) => }
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a function call with multiple arguments" in {
    val input = "f(1, 2)"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(FunctionCall(name, args, _), _) =>
        name should be ("f")
        args should matchPattern {
          case Seq(IntExpr(1, _), IntExpr(2, _)) =>
        }
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a method call" in {
    val input = "x.f()"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(MethodCall(target, method, args, _), _) =>
        target should matchPattern { case StringExpr("x", _) => }
        method should be ("f")
        args shouldBe empty
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  if(false){
    // TODO: fix this case
    it should "parse a custom infix expression" in {
      val input = "x f y"
      val result = Parser.parseExpression(input)
      result match {
        case Parsed.Success(InfixExpr(left, op, right, _), _) =>
          op should be("f")
        case f: Parsed.Failure =>
          println(f.trace().longMsg)
          fail("Parsing failed")
      }
    }

    it should "parse an infix expression" in {
      val input = "x + y"
      val result = Parser.parseExpression(input)
      result match {
        case Parsed.Success(InfixExpr(left, op, right, _), _) =>
          left should matchPattern { case StringExpr("x", _) => }
          op should be("+")
          right should matchPattern { case StringExpr("y", _) => }
        case f: Parsed.Failure =>
          println(f.trace().longMsg)
          fail("Parsing failed")
      }
    }

  }
  
  it should "parse a lambda expression" in {
    val input = "(x, y) => x + y"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(LambdaExpr(params, body, _), _) => {}
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a type annotation" in {
    val input = "x: Int"
    val result = Parser.parseExpression(input)
    result match {
      case Parsed.Success(TypeAnnotation(expr, tpe, _), _) =>
        expr should matchPattern { case StringExpr("x", _) => }
        tpe should be ("Int")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse an identifier correctly" in {
    val input = "myIdentifier"
    val result = Parser.parseIdentifier(input)
    result match {
      case Parsed.Success(value, _) =>
        value should be ("myIdentifier")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse an identifier x correctly" in {
    val input = "x"
    val result = Parser.parseIdentifier(input)
    result match {
      case Parsed.Success(value, _) =>
        value should be ("x")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse a key-value pair correctly" in {
    val input = "key = 123"
    val result = Parser.parseKeyValue(input)
    result match {
      case Parsed.Success((key, IntExpr(value, _)), _) =>
        key should be ("key")
        value should be (123)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }
}

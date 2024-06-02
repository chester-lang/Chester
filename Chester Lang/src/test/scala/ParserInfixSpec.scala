package chester.lang

import fastparse._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ParserInfixSpec extends AnyFlatSpec with Matchers {

  val fileName = "testfile.scala"
  val parser = Parser(fileName)

  it should "parse an infix expression" in {
    val input = "x + y"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(InfixExpr(left, op, right, location), _) =>
        left should matchPattern { case StringExpr("x", _) => }
        op should be("+")
        right should matchPattern { case StringExpr("y", _) => }
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

  it should "parse a custom infix expression" in {
    val input = "x f y"
    val result = parser.parseExpression(input)
    result match {
      case Parsed.Success(InfixExpr(left, op, right, location), _) =>
        left should matchPattern { case StringExpr("x", _) => }
        op should be("f")
        right should matchPattern { case StringExpr("y", _) => }
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

}

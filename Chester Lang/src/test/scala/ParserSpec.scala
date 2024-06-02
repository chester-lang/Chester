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
        value should be("Hello, World!")
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
        entries.keys should contain allOf("x", "y")
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
        elements.length should be(3)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }

  it should "parse multiple expressions correctly" in {
    val input =
      """
        |{
        |  x = 1;
        |  y = 2
        |}
        |print("Hello, World!");
        |[1, 2, 3];
        |f(1, 2);
        |x + y;
        |x.f();
        |x f y;
        |(x, y) => x + y;
        |x: Int
        |""".stripMargin

    val result = Parser.parseExpressions(input)
    result match {
      case Parsed.Success(expressions, _) =>
        expressions.length should be(9)
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }


  "Parser" should "parse an identifier correctly" in {
    val input = "myIdentifier"
    val result = Parser.parseIdentifier(input)
    result match {
      case Parsed.Success(value, _) =>
        value should be("myIdentifier")
      case f: Parsed.Failure =>
        println(f.trace().longMsg)
        fail("Parsing failed")
    }
  }
  "Parser" should "parse an identifier x correctly" in {
    val input = "x"
    val result = Parser.parseIdentifier(input)
    result match {
      case Parsed.Success(value, _) =>
        value should be("x")
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

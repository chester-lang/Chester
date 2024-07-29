package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._

class ParserTest extends FunSuite {

  test("parse valid identifier") {
    val result = Parser.parseExpression("testFile", "validIdentifier123")
    result match {
      case Parsed.Success(Identifier(name, Some(pos)), _) =>
        assertEquals(name, "validIdentifier123")
        assertEquals(pos.fileName, "testFile")
        assertEquals(pos.range.start.line, 0)
        assertEquals(pos.range.start.column, 0)
      case _ => fail("Parsing failed")
    }
  }

  test("parse identifier with symbols") {
    val result = Parser.parseExpression("testFile", "valid-Identifier_123")
    result match {
      case Parsed.Success(Identifier(name, Some(pos)), _) =>
        assertEquals(name, "valid-Identifier_123")
        assertEquals(pos.fileName, "testFile")
        assertEquals(pos.range.start.line, 0)
        assertEquals(pos.range.start.column, 0)
      case _ => fail("Parsing failed")
    }
  }

  test("parse empty input") {
    val result = Parser.parseExpression("testFile", "")
    assert(result.isInstanceOf[Parsed.Failure])
  }

  // Tests for IntegerLiteral
  test("parse valid decimal integer") {
    val input = "12345"
    val expected = IntegerLiteral(BigInt("12345"))
    parseAndCheck(input, expected)
  }

  test("parse valid hexadecimal integer") {
    val input = "0x1A3F"
    val expected = IntegerLiteral(BigInt("1A3F", 16))
    parseAndCheck(input, expected)
  }

  test("parse valid binary integer") {
    val input = "0b1101"
    val expected = IntegerLiteral(BigInt("1101", 2))
    parseAndCheck(input, expected)
  }

  if(false) test("parse signed integer") { // we are see it as -(6789) now
    val input = "-6789"
    val expected = IntegerLiteral(BigInt("-6789"))
    parseAndCheck(input, expected)
  }

  // Tests for DoubleLiteral
  test("parse valid double with exponent") {
    val input = "3.14e2"
    val expected = DoubleLiteral(BigDecimal("3.14e2"))
    parseAndCheck(input, expected)
  }

  if(false) test("parse signed double with exponent") { // we are see it as -(1.23e-4) now
    val input = "-1.23e-4"
    val expected = DoubleLiteral(BigDecimal("-1.23e-4"))
    parseAndCheck(input, expected)
  }

  test("parse double without exponent") {
    val input = "456.789"
    val expected = DoubleLiteral(BigDecimal("456.789"))
    parseAndCheck(input, expected)
  }

  // General literal tests
  test("parse integerLiteral") {
    val input = "12345"
    val expected = IntegerLiteral(BigInt("12345"))
    parseAndCheck(input, expected)
  }

  test("parse doubleLiteral") {
    val input = "1.23e4"
    val expected = DoubleLiteral(BigDecimal("1.23e4"))
    parseAndCheck(input, expected)
  }


  test("parse single-line string literal") {
    val input = "\"Hello, world!\""
    val result = Parser.parseExpression("testFile", input)
    result match {
      case Parsed.Success(StringLiteral(value, _), _) =>
        assertEquals(value, "Hello, world!")
      case _ => fail(s"Expected StringLiteral but got $result")
    }
  }

  test("parse escaped characters in string literal") {
    val input = "\"Hello, \\\"world\\\"!\\n\""
    val result = Parser.parseExpression("testFile", input)
    result match {
      case Parsed.Success(StringLiteral(value, _), _) =>
        assertEquals(value, "Hello, \"world\"!\n")
      case _ => fail(s"Expected StringLiteral but got $result")
    }
  }
  if(false){ // heredoc broken

    test("parse heredoc string literal") {
      val input = "\"\"\"\n  Hello,\n  world!\n\"\"\""
      val result = Parser.parseExpression("testFile", input)
      result match {
        case Parsed.Success(StringLiteral(value, _), _) =>
          assertEquals(value, "Hello,\nworld!")
        case _ => fail(s"Expected StringLiteral but got $result")
      }
    }

    test("parse heredoc string literal with inconsistent indentation") {
      val input = "\"\"\"\n  Hello,\n   world!\n\"\"\""
      val result = Parser.parseExpression("testFile", input)
      result match {
        case Parsed.Failure(label, index, extra) =>
          assert(extra.trace().msg.contains("Inconsistent indentation in heredoc string literal"))
        case _ => fail(s"Expected parsing failure due to inconsistent indentation but got $result")
      }
    }

  }

  test("parse invalid escape sequence in string literal") {
    val input = "\"Hello, \\xworld!\""
    val result = Parser.parseExpression("testFile", input)
    result match {
      case Parsed.Failure(label, index, extra) => {}
      case _ => fail(s"Expected parsing failure due to invalid escape sequence but got $result")
    }
  }

  test("some expr") {
    val input =
      """
        |ObjectExpr(Vector(
        |      Identifier("a") -> ObjectExpr(Vector(
        |        Identifier("b") -> IntegerLiteral(2)
        |      )),
        |      Identifier("c") -> IntegerLiteral(3)
        |    ))
        |""".stripMargin
    val a = Parser.parseContent("testFile", input, ignoreLocation = true)
    val expected = FunctionCall(Identifier("ObjectExpr"), Telescope(Vector(Arg(Vector(), None, None, Some(FunctionCall(Identifier("Vector"), Telescope(Vector(Arg(Vector(), None, None, Some(OpSeq(Vector(FunctionCall(Identifier("Identifier"), Telescope(Vector(Arg(Vector(), None, None, Some(StringLiteral("a")))))), Identifier("->"), FunctionCall(Identifier("ObjectExpr"), Telescope(Vector(Arg(Vector(), None, None, Some(FunctionCall(Identifier("Vector"), Telescope(Vector(Arg(Vector(), None, None, Some(OpSeq(Vector(FunctionCall(Identifier("Identifier"), Telescope(Vector(Arg(Vector(), None, None, Some(StringLiteral("b")))))), Identifier("->"), FunctionCall(Identifier("IntegerLiteral"), Telescope(Vector(Arg(Vector(), None, None, Some(IntegerLiteral(2)))))))))))))))))))))), Arg(Vector(), None, None, Some(OpSeq(Vector(FunctionCall(Identifier("Identifier"), Telescope(Vector(Arg(Vector(), None, None, Some(StringLiteral("c")))))), Identifier("->"), FunctionCall(Identifier("IntegerLiteral"), Telescope(Vector(Arg(Vector(), None, None, Some(IntegerLiteral(3))))))))))))))))))
    parseAndCheck(input, expected)
  }

  test("emoji"){
    val input="👍"
    val expected = Identifier("👍")
    parseAndCheck(input, expected)
  }
}

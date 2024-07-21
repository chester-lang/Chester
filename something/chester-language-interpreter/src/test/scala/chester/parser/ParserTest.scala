package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete.{Expr, Identifier}

class ParserTest extends FunSuite {

  test("parse valid identifier") {
    val result = Parser.parseExpression("testFile", "validIdentifier123")
    result match {
      case Parsed.Success(Identifier(Some(pos), name), _) =>
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
      case Parsed.Success(Identifier(Some(pos), name), _) =>
        assertEquals(name, "valid-Identifier_123")
        assertEquals(pos.fileName, "testFile")
        assertEquals(pos.range.start.line, 0)
        assertEquals(pos.range.start.column, 0)
      case _ => fail("Parsing failed")
    }
  }

  test("parse invalid identifier starting with digit") {
    val result = Parser.parseExpression("testFile", "123invalidIdentifier")
    assert(result.isInstanceOf[Parsed.Failure])
  }

  test("parse empty input") {
    val result = Parser.parseExpression("testFile", "")
    assert(result.isInstanceOf[Parsed.Failure])
  }
}

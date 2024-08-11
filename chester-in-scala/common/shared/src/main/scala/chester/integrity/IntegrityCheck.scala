package chester.integrity

import chester.parser.{FileNameAndContent, Parser}
import chester.syntax.concrete.*
import chester.syntax.core.{AnyTerm, IntegerType}
import chester.tyck.{ExprTycker, LocalCtx, TyckState}

// Test that the binary is still performing well when compiled differently.
object IntegrityCheck {
  private var tests: List[() => Unit] = List()

  private def test(name: String)(f: => Unit): Unit = {
    tests = (() => {
      println(s"Running test: $name")
      f
    }) :: tests
  }

  private def assertEquals[T](actual: T, expected: T, message: String = ""): Unit = {
    if (actual != expected) {
      throw new AssertionError(s"$message. Expected: $expected, Actual: $actual")
    }
  }

  private def fail(message: String): Unit = {
    throw new AssertionError(message)
  }

  private def parseAndCheck(input: String, expected: Expr): Unit = {
    val resultignored = Parser.parseExpr(FileNameAndContent("testFile", input)) // it must parse with location
    Parser.parseExpr(FileNameAndContent("testFile", input), ignoreLocation = true) match {
      case Right(value) =>
        assertEquals(value, expected, s"Failed for input: $input")
      case Left(error) =>
        fail(s"Parsing failed for input: $input ${error.message} at index ${error.index}")
    }
  }


  test("parse opSeq with mixed operators without spaces") {
    val input = "1+2*4+5"
    val expected = OpSeq(Vector(
      IntegerLiteral(1),
      Identifier("+"),
      IntegerLiteral(2),
      Identifier("*"),
      IntegerLiteral(4),
      Identifier("+"),
      IntegerLiteral(5)
    ))
    parseAndCheck(input, expected)
  }

  test("parse Identifier to IntegerLiteral") {
    val input = "Identifier(\"b\") -> IntegerLiteral(2)"
    val expected = OpSeq(Vector(
      FunctionCall(Identifier("Identifier"), Tuple(Vector(StringLiteral("b")))),
      Identifier("->"),
      FunctionCall(Identifier("IntegerLiteral"), Tuple(Vector(IntegerLiteral(2))))
    ))
    parseAndCheck(input, expected)
  }

  test("parse OpSeq with identifier to integer literal mapping") {
    val input = "Vector(\n  Identifier(\"b\") -> IntegerLiteral(2)\n)"
    val expected = FunctionCall(
      Identifier("Vector"),
      Tuple(Vector(
        OpSeq(Vector(
          FunctionCall(Identifier("Identifier"), Tuple(Vector(StringLiteral("b")))),
          Identifier("->"),
          FunctionCall(Identifier("IntegerLiteral"), Tuple(Vector(IntegerLiteral(2))))
        ))
      ))
    )
    parseAndCheck(input, expected)
  }

  test("Unification with AnyTerm") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intType = IntegerType(None)
    val anyType = AnyTerm(None)

    val result = ExprTycker.unifyV0(intType, anyType, state, ctx)
    assertEquals(result, Right(intType))
  }

  def apply(): Unit = {
    tests.foreach(_())
  }
}

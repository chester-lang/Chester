package chester.tyck

import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.*
import munit.FunSuite

class ExprTyckerSuite extends FunSuite {

  test("Unification with AnyTerm") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intType = IntegerType
    val anyType = AnyType0

    val result = ExprTycker.unifyV0(intType, anyType, state, ctx)
    assertEquals(result, Right(intType))
  }


  test("Inheritance with AnyTerm") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intType = IntegerType
    val anyType = AnyType0
    val intExpr = IntegerLiteral(42, None)

    val result = ExprTycker.inheritV0(intExpr, anyType, state = state, ctx = ctx)

    assert(result.isRight)
    assertEquals(result.map(_.wellTyped), Right(IntTerm(42)))
    assertEquals(result.map(_.ty), Right(IntegerType))
  }
}

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

    val result = ExprTycker.unifyTy(anyType, intType, state, ctx)
    assertEquals(result.result, intType)
  }

  test("Inheritance with AnyTerm") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intType = IntegerType
    val anyType = AnyType0
    val intExpr = IntegerLiteral(42, None)

    val result = ExprTycker.inherit(intExpr, anyType, state = state, ctx = ctx)

    assert(result.errorsEmpty)
    assertEquals(result.result.wellTyped, IntTerm(42))
    assertEquals(result.result.ty, IntegerType)
  }
}

package chester.tyck

import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.*
import munit.FunSuite

class ExprTyckerSuite extends FunSuite {

  test("Unification with AnyTerm") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intType = IntegerType(None)
    val anyType = AnyTerm(None)

    val result = ExprTycker.unify(intType, anyType, state, ctx)
    assertEquals(result, Right(intType))
  }


  test("Inheritance with AnyTerm") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intType = IntegerType(None)
    val anyType = AnyTerm(None)
    val intExpr = IntegerLiteral(42, None)

    val result = ExprTycker.inherit(intExpr, anyType, state = state, ctx = ctx)

    assert(result.isRight)
    assertEquals(result.map(_.wellTyped), Right(IntegerTerm(42, None)))
    assertEquals(result.map(_.ty), Right(IntegerType(None)))
  }
}

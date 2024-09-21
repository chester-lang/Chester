package chester.tyck

import chester.parser.getParsed
import chester.syntax.concrete.*
import chester.syntax.core.*
import munit.FunSuite

class ObjectWithIntegersSuite extends FunSuite {
  test("Synthesize Object with Integer Fields") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intField1 = ObjectExprClause(Identifier("field1"), IntegerLiteral(42))
    val intField2 = ObjectExprClause(Identifier("field2"), IntegerLiteral(24))
    val intField3 = ObjectExprClause(Identifier("field3"), IntegerLiteral(100))

    val intObjectExpr = ObjectExpr(Vector(intField1, intField2, intField3))

    val result = ExprTycker.synthesize(intObjectExpr, state = state, ctx = ctx)

    assert(result.errorsEmpty)
    result.result match {
      case Judge(ObjectTerm(clauses), ObjectType(fieldTypes, _), _) =>
        assertEquals(clauses.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field1"), IntTerm(42)) => true }.isDefined, true)
        assertEquals(clauses.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field2"), IntTerm(24)) => true }.isDefined, true)
        assertEquals(clauses.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field3"), IntTerm(100)) => true }.isDefined, true)

        assertEquals(fieldTypes.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field1"), IntegerType) => true }.isDefined, true)
        assertEquals(fieldTypes.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field2"), IntegerType) => true }.isDefined, true)
        assertEquals(fieldTypes.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field3"), IntegerType) => true }.isDefined, true)

      case _ => fail("Synthesis failed")
    }
  }

  test("{x.y=1}") {
    val toparse = "{x.y=1}"
    val result = ExprTycker.synthesize(getParsed(toparse))
    assertEquals(result.result.wellTyped,
      ObjectTerm(
        clauses = Vector(
          ObjectClauseValueTerm(
            key = SymbolTerm(
              value = "x"
            ),
            value = ObjectTerm(
              clauses = Vector(
                ObjectClauseValueTerm(
                  key = SymbolTerm(
                    value = "y"
                  ),
                  value = IntTerm(
                    value = 1
                  )
                )
              )
            )
          )
        )
      ))
  }
}

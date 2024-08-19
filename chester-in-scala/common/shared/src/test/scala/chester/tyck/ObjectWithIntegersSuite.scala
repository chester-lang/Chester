package chester.tyck

import chester.parser.getParsed
import chester.syntax.concrete.*
import chester.syntax.core.*
import munit.FunSuite

class ObjectWithIntegersSuite extends FunSuite {
  test("Synthesize Object with Integer Fields") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    // Create ObjectExprClauses instead of tuples
    val intField1 = ObjectExprClause(Identifier("field1"), IntegerLiteral(42))
    val intField2 = ObjectExprClause(Identifier("field2"), IntegerLiteral(24))
    val intField3 = ObjectExprClause(Identifier("field3"), IntegerLiteral(100))

    // Create the ObjectExpr with the clauses
    val intObjectExpr = ObjectExpr(Vector(intField1, intField2, intField3))

    // Synthesize the object expression
    val result = ExprTycker.synthesizeV0(intObjectExpr, state, ctx)

    // Validate the result
    assert(result.isRight)
    result match {
      case Right(Judge(ObjectTerm(clauses, _), ObjectType(fieldTypes, _, _), _)) =>
        // Check each clause for the correct field name and value
        assertEquals(clauses.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field1"), IntegerTerm(42),_) => true }.isDefined, true)
        assertEquals(clauses.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field2"), IntegerTerm(24),_) => true }.isDefined, true)
        assertEquals(clauses.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field3"), IntegerTerm(100),_) => true }.isDefined, true)

        // Check the field types
        assertEquals(fieldTypes.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field1"), IntegerType,_) => true }.isDefined, true)
        assertEquals(fieldTypes.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field2"), IntegerType,_) => true }.isDefined, true)
        assertEquals(fieldTypes.collectFirst { case ObjectClauseValueTerm(SymbolTerm("field3"), IntegerType,_) => true }.isDefined, true)

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
                  value = IntegerTerm(
                    value = 1
                  ),
                  meta = None
                )
              ),
              meta = None
            ),
            meta = None
          )
        ),
        meta = None
      ))
  }
}

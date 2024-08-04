package chester.tyck

import chester.syntax.concrete._
import chester.syntax.core._
import munit.FunSuite

class ObjectWithIntegersSuite extends FunSuite {

  test("Synthesize Object with Integer Fields") {
    val state = TyckState()
    val ctx = LocalCtx.Empty

    val intField1 = (Identifier("field1"), IntegerLiteral(42))
    val intField2 = (Identifier("field2"), IntegerLiteral(24))
    val intField3 = (Identifier("field3"), IntegerLiteral(100))

    val intObjectExpr = ObjectExpr(Vector(intField1, intField2, intField3))

    val result = ExprTycker.synthesize(intObjectExpr, state, ctx)

    assert(result.isRight)
    result match {
      case Right(Judge(ObjectTerm(clauses, _), ObjectType(fieldTypes, _), _)) =>
        assertEquals(clauses.find(_._1 == "field1").map(_._2), Some(IntegerTerm(42)))
        assertEquals(clauses.find(_._1 == "field2").map(_._2), Some(IntegerTerm(24)))
        assertEquals(clauses.find(_._1 == "field3").map(_._2), Some(IntegerTerm(100)))
        assertEquals(fieldTypes.find(_._1 == "field1").map(_._2), Some(IntegerType(None)))
        assertEquals(fieldTypes.find(_._1 == "field2").map(_._2), Some(IntegerType(None)))
        assertEquals(fieldTypes.find(_._1 == "field3").map(_._2), Some(IntegerType(None)))
      case _ => fail("Synthesis failed")
    }
  }
}

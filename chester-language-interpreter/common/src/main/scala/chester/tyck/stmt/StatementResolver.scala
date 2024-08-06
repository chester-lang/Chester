package chester.tyck.stmt

import chester.syntax.concrete._
import chester.syntax.concrete.stmt._
import chester.tyck._

object StatementResolver {
  def resolveStatement(expr: Expr): (Vector[TyckWarning], Vector[TyckError], Option[Statement]) = expr match {
    case opSeq: OpSeq => opSeq.seq match {
      case Vector(Identifier("data", _), xs @ _*) =>
        (Vector.empty, Vector.empty, Some(DataStatement(xs.toVector, opSeq.meta)))

      case Vector(Identifier("trait", _), xs @ _*) =>
        (Vector.empty, Vector.empty, Some(TraitStatement(xs.toVector, opSeq.meta)))

      case Vector(Identifier("implement", _), xs @ _*) =>
        (Vector.empty, Vector.empty, Some(ImplementStatement(xs.toVector, opSeq.meta)))

      case _ =>
        (Vector.empty, Vector(UnsupportedExpressionError(opSeq)), None)
    }
    case _ => (Vector.empty, Vector.empty, Some(ExprStatement(expr)))
  }
}

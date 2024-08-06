package chester.tyck.stmt

import chester.syntax.concrete._
import chester.syntax.concrete.stmt._
import chester.tyck.{TyckError, UnsupportedExpressionError}

object StatementResolver {
  def resolveStatement(expr: Expr): Either[TyckError, Statement] = expr match {
    case opSeq: OpSeq => opSeq.seq match {
      case Vector(Identifier("data", _), xs @ _*) =>
        Right(DataStatement(xs.toVector, opSeq.meta))

      case Vector(Identifier("trait", _), xs @ _*) =>
        Right(TraitStatement(xs.toVector, opSeq.meta))

      case Vector(Identifier("implement", _), xs @ _*) =>
        Right(ImplementStatement(xs.toVector, opSeq.meta))

      case _ => Left(UnsupportedExpressionError(opSeq))
    }
    case _ => Right(ExprStatement(expr))
  }
}

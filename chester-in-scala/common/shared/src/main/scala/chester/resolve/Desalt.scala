package chester.resolve

import chester.syntax.concrete.*
import chester.syntax.Const
import chester.error._

case class DesugarInfo()

private object DesaltCaseClauseMatch {
  @throws[TyckError]
  def unapply(x: Expr): Option[(Expr, Expr, Option[ExprMeta])] = x match {
    case OpSeq(Vector(Identifier(Const.Case, _), pattern, Identifier(Const.>=, _), returning), meta) => Some(pattern, returning, meta)
    case OpSeq(Vector(Identifier(Const.Case, _), _*), _) => throw ExpectCase(x)
    case _ => None
  }
}

case object SimpleDesalt {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case DesaltCaseClauseMatch(pattern, returning, meta) => DesaltCaseClause(pattern, returning, meta)
    case b@Block(heads, tail, meta) if heads.contains(DesaltCaseClause) || tail.contains(DesaltCaseClause) => {
      if (heads.isEmpty || tail.nonEmpty || !heads.forall(_.isInstanceOf[DesaltCaseClause])) throw ExpectFullCaseBlock(b)
      val heads1: Vector[DesaltCaseClause] = heads.map(_.asInstanceOf[DesaltCaseClause])
      DesaltMatching(heads1, meta)
    }
    case default => default
  }

  def desugarEither(expr: Expr): Either[TyckError, Expr] = try {
    Right(desugar(expr))
  } catch {
    case e: TyckError => Left(e)
  }
}

case object OpSeqDesalt {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = ???
}

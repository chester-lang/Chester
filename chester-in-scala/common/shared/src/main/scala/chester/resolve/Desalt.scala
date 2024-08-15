package chester.resolve

import chester.syntax.concrete.*
import chester.syntax.Const
import chester.error._

case class DesugarInfo()

private object DesaltCaseClauseMatch {
  @throws[TyckError]
  def unapply(x: Expr): Option[(Expr, Expr, Option[ExprMeta])] = x match {
    case OpSeq(Vector(Identifier(Const.Case, _), pattern, Identifier(Const.>=, _), returning), meta) => Some(pattern, returning, meta)
    case OpSeq(Vector(Identifier(Const.Case, _), _*), _) => throw ExpectCase()
    case _ => None
  }
}

case class Desalt(info: DesugarInfo) {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case DesaltCaseClauseMatch(pattern, returning, meta) => DesaltCaseClause(pattern, returning, meta)
    case OpSeq(seq, _) => ???
    case default => default
  }
}

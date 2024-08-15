package chester.resolve

import chester.syntax.concrete.*
import chester.syntax.Const
import chester.error._

case class DesugarInfo()

case class DesaltCaseClause(pattern: Expr, returning: Expr)
object DesaltCaseClauseMatch {
  @throws[TyckError]
  def unapply(x: Expr): Option[(Expr, Expr)] = x match {
    case OpSeq(Vector(Identifier(Const.Case, _), pattern, Identifier(Const.>=, _), returning), _) => Some(pattern, returning)
    case OpSeq(Vector(Identifier(Const.Case, _), _*), _) => throw ExpectCase()
  }
}

case class Desalt(info: DesugarInfo) {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case OpSeq(seq, _) => ???
    case default => default
  }
}

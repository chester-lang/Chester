package chester.resolve

import chester.syntax.concrete.*
import chester.syntax.Const

case class DesugarInfo()

case class DesaltCaseClause(pattern: Expr, returning: Expr)
object DesaltCaseClauseMatch {
  def unapply(x: Expr): (Expr, Expr) = x match {
    case OpSeq(Vector(Identifier(Const.Case, _)), _) => ???
    case _ => ???
  }
}

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case OpSeq(seq, _) => ???
    case default => default
  }
}

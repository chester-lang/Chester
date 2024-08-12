package chester.resolve

import chester.syntax.concrete.*

case class DesugarInfo()

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case OpSeq(seq, _) => ???
    case default => default
  }
}

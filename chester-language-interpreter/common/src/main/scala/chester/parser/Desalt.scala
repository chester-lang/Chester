package chester.parser

import chester.syntax.concrete._

case class DesugarInfo ()

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case OpSeq(seq, _, _) => ???
    case default => default
  }
}

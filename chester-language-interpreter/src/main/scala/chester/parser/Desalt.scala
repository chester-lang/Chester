package chester.parser

import chester.syntax.concrete._

case class DesugarInfo ()

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case BinOpSeq(seq, _) => ???
    case default => default
  }
}

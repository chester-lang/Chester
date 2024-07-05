package chesteri.parser

import chesteri.parsed.Expr

case class DesugarInfo ()

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = ???
}

package chesteri.parser

import chesteri.syntax.concrete.Expr

case class DesugarInfo ()

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = ???
}

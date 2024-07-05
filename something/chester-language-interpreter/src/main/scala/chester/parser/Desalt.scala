package chester.parser

import chester.syntax.concrete.Expr

case class DesugarInfo ()

case class Desalt(info: DesugarInfo) {
  def desugar(expr: Expr): Expr = ???
}

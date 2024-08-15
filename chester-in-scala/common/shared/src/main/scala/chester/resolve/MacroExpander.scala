package chester.resolve

import chester.syntax.concrete.*
import chester.tyck.stmt.ModuleTyckGetting
import chester.tyck.{Getting}

case class MacroExpander() {
  def expand(expr: Expr): ModuleTyckGetting[Expr] = expr match {
    case _ => Getting.pure(expr)
  }

}

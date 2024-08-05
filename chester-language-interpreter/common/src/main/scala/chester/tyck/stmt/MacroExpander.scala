package chester.tyck.stmt

import chester.syntax.concrete._
import chester.tyck.{Getting, TyckError, TyckWarning}

case class MacroExpander() {
  def expand(expr: Expr): ModuleTyckGetting[Expr] = expr match {
    case _ => Getting.pure(expr)
  }

}

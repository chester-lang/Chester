package chester.tyck

import chester.syntax.concrete.Expr
import chester.syntax.core.Term

case class TyckState()

case class LocalCtx()

case class Judge(wellTyped: Term, ty: Term)

case class ExprTycker(state: TyckState, localCtx: LocalCtx) {
  def inherit(expr: Expr, ty: Term): Judge = ???

}

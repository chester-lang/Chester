package chester.tyck.core

import chester.error.{TyckError, TyckProblem}
import chester.syntax.core.*
import chester.tyck.{LocalCtx, Reporter}

case class CoreTycker(localCtx: LocalCtx = LocalCtx.Empty, reporter: Reporter[TyckError]) {
  def check(judge: Judge): Unit = ???
  def inferTyNoEffect(term: Term): Term = Typeω // TODO
}

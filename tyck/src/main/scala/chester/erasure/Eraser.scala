package chester.erasure

import chester.error.*
import chester.syntax.core.*
import chester.tyck.*

trait Eraser {
  def checkAndErase(term: Term, ty: Term, effects: Effects)(using reporter: Reporter[TyckProblem]): Term
}

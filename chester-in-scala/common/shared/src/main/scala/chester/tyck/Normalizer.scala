package chester.tyck

import cats.data.State
import chester.syntax.core.*


case class Normalizer() {
  def apply(term: Judge): State[TyckState, Judge] = term match {

    case _ => State.pure(term)
  }
}

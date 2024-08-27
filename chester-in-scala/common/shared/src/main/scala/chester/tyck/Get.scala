package chester.tyck

import chester.error.{TyckError, TyckWarning}
import chester.utils.MutBox

trait Reporter[T] {
  def report(value: T): Unit
}

class VectorReporter[T] extends Reporter[T] {
  private val buffer = scala.collection.mutable.ArrayBuffer[T]()

  def report(value: T): Unit = buffer += value

  def getReports: Vector[T] = buffer.toVector
}

case class Get[W, E, S](warnings: Reporter[W], errors: Reporter[E], state: MutBox[S])

type Tyck = Get[TyckWarning, TyckError, TyckState]

package chester.tyck

import chester.error.{TyckError, TyckWarning}
import chester.utils.MutBox

trait Reporter[-T] {
  def apply(value: T): Unit
}

class VectorReporter[T] extends Reporter[T] {
  private val buffer = scala.collection.mutable.ArrayBuffer[T]()

  def apply(value: T): Unit = buffer += value

  def getReports: Vector[T] = buffer.toVector
}

case class Get[W, E, S](warningsReporter: Reporter[W], errorsReporter: Reporter[E], state: MutBox[S]) {
  def getState: S = state.get
  def error(error: E): Unit = errorsReporter.apply(error)
  def errors(errors: Seq[E]): Unit = errors.foreach(error)
  def warning(warning: W): Unit = warningsReporter.apply(warning)
}

object Get {
  def run[W, E, S, A](program: Get[W, E, S] => A)(state: S): TyckResult0[W, E, S, A] = {
    val warnings = new VectorReporter[W]
    val errors = new VectorReporter[E]
    val stateBox = MutBox(state)
    val get = Get(warnings, errors, stateBox)
    val result = program(get)
    TyckResult0(stateBox.get, result, warnings.getReports, errors.getReports)
  }
}

type Tyck = Get[TyckWarning, TyckError, TyckState]

object Tyck {
  inline def run[A](inline program: Tyck => A)(inline state: TyckState): TyckResult[TyckState, A] = Get.run(program)(state)
}
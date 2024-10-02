package chester.tyck

import chester.error.*
import chester.utils.MutBox

trait Reporter[-T] {
  def apply(value: T): Unit
}

extension [T](reporter: Reporter[T]) {
  def report(xs: Seq[T]): Unit = xs.foreach(reporter.apply)
}

class VectorReporter[T] extends Reporter[T] {
  private val buffer = scala.collection.mutable.ArrayBuffer[T]()

  def apply(value: T): Unit = buffer += value

  def getReports: Vector[T] = buffer.toVector
}

class Get[P, S](val reporter: Reporter[P], private val state: MutBox[S]) {
  def getState: S = state.get
  
  implicit inline def toReporter: Reporter[P] = reporter

  def report(problem: P): Unit = reporter.apply(problem)

  def reportseq(problems: Seq[P]): Unit = problems.foreach(report)

  def updateState(f: S => S): Unit = {
    state.update(f)
  }

  def uncheckedSetState(newState: S): Unit = {
    state.set(newState)
  }

  def updateAndMap[T](f: S => (S, T)): T = {
    state.updateAndMap(f)
  }
}

object Get {
  def run[P <: WithServerity, S, A](program: Get[P, S] => A)(state: S): TyckResult0[P, S, A] = {
    val reporter = new VectorReporter[P]
    val stateBox = MutBox(state)
    val get = Get(reporter, stateBox)
    val result = program(get)
    TyckResult0(stateBox.get, result, reporter.getReports)
  }
}

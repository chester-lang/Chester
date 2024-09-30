package chester.propagator

import chester.syntax.core.{HasUniqId, UniqId}

sealed trait Cell[+T] extends HasUniqId {
  def value: Option[T]
}

sealed trait Propagator extends HasUniqId {
  def affectingCells: Set[UniqId]
  def run(state: CellsState): (cells: CellsState, finished: Boolean)
}

type CellsState = Map[UniqId, Cell[?]]

case class State(cells: CellsState) {

}

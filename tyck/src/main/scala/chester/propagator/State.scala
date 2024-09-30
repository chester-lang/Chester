package chester.propagator

import chester.syntax.core.{HasUniqId, UniqId, UniqIdOf}

sealed trait Cell[+T] extends HasUniqId {
  def read: Option[T]
}

case class OnceCell[T](uniqId: UniqId, value: Option[T]) extends Cell[T] {
  override def read: Option[T] = value

  def fill(newValue: T): OnceCell[T] = {
    require(value.isEmpty)
    copy(value = Some(newValue))
  }
}

case class MutableCell[T](uniqId: UniqId, value: Option[T]) extends Cell[T] {
  override def read: Option[T] = value

  def update(newValue: T): MutableCell[T] = {
    copy(value = Some(newValue))
  }
}

case class LiteralCell[T](uniqId: UniqId, value: T) extends Cell[T] {
  override def read: Option[T] = Some(value)
}

sealed trait Propagator[Ability] extends HasUniqId {
  def readingCells: Set[UniqIdOf[Cell[?]]]
  def writingCells: Set[UniqIdOf[Cell[?]]]

  /**
   * @return true if the propagator finished its work
   */
  def run(state: CellsStateAbility, more: Ability): Boolean
}

trait CellsStateAbility {
  def read[T <: Cell[?]](id: UniqIdOf[T]): Option[T] = ???

  def update[T <: Cell[?]](id: UniqIdOf[T], f: T => T): Unit = ???
}

type CellsState = Map[UniqIdOf[Cell[?]], Cell[?]]
type DiffCellsState = CellsState
type PropagatorsState[Ability] = Map[UniqIdOf[Propagator[Ability]], Propagator[Ability]]
type AffectingMap[Ability] = Map[UniqIdOf[Cell[?]], Set[UniqIdOf[Propagator[Ability]]]]

case class State[Ability](cells: CellsState, propagators: PropagatorsState[Ability], affectingMap: AffectingMap[Ability]) {

}

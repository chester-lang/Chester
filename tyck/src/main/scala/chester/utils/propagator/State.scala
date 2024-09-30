package chester.utils.propagator

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
  def readingCells: Set[UniqIdOf[Cell[?]]] = Set.empty
  def writingCells: Set[UniqIdOf[Cell[?]]] = Set.empty
  def zonkingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  require(zonkingCells.subsetOf(writingCells))

  /**
   * @return true if the propagator finished its work
   */
  def run(state: CellsStateAbility, more: Ability): Boolean

  /** make a best guess for zonkingCells */
  def zonk(state: CellsStateAbility, more: Ability): Unit
}

trait CellsStateAbility {
  def read[T <: Cell[?]](id: UniqIdOf[T]): Option[T]

  def update[T <: Cell[?]](id: UniqIdOf[T], f: T => T): Unit
  def addCell[T <: Cell[?]](cell: T): Unit
}

type CellsState = Map[UniqIdOf[Cell[?]], Cell[?]]
type PropagatorsState[Ability] = Map[UniqIdOf[Propagator[Ability]], Propagator[Ability]]

case class State[Ability](cells: CellsState, propagators: PropagatorsState[Ability], didChanged: Vector[UniqIdOf[Cell[?]]]) {

}

trait Tick[Ability] {
  def tick(more: Ability): Unit
}

trait StateAbility[Ability] extends CellsStateAbility with Tick[Ability] {
  def addPropagator(propagator: Propagator[Ability]): Unit
}


class StateCells[Ability](var state: State[Ability]) extends StateAbility[Ability] {
  override def read[T <: Cell[?]](id: UniqIdOf[T]): Option[T] = state.cells.get(id).asInstanceOf[Option[T]]

  override def update[T <: Cell[?]](id: UniqIdOf[T], f: T => T): Unit = {
    state.cells.get(id) match {
      case Some(cell) =>
        val newCell = f(cell.asInstanceOf[T])
        state = state.copy(cells = state.cells.updated(id, newCell))
      case None =>
        throw new IllegalArgumentException(s"Cell with id $id not found")
    }
  }

  override def addCell[T <: Cell[?]](cell: T): Unit = {
    state = state.copy(cells = state.cells.updated(cell.uniqId, cell))
  }

  override def addPropagator(propagator: Propagator[Ability]): Unit = {
    state = state.copy(propagators = state.propagators.updated(propagator.uniqId, propagator))
  }

  override def tick(more: Ability): Unit = {
    val didChanged = state.didChanged
    state = state.copy(didChanged = Vector.empty)
    state.propagators.filter((_, propagator) => propagator.readingCells.exists(didChanged.contains)).foreach {
      case (pid, propagator) =>
        val done = propagator.run(this, more)
        if (done) {
          state = state.copy(propagators = state.propagators.removed(pid))
        }
    }
  }
}

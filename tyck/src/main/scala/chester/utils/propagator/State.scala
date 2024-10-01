package chester.utils.propagator

import chester.syntax.core.{HasUniqId, UniqId, UniqIdOf}

sealed trait Cell[T] extends HasUniqId {
  def read: Option[T]

  def stable: Boolean = read.isDefined

  /** fill an unstable cell */
  def fill(newValue: T): Cell[T]
}

case class OnceCell[T](uniqId: UniqId, value: Option[T]) extends Cell[T] {
  override def read: Option[T] = value

  override def fill(newValue: T): OnceCell[T] = {
    require(value.isEmpty)
    copy(value = Some(newValue))
  }
}

case class MutableCell[T](uniqId: UniqId, value: Option[T]) extends Cell[T] {
  override def read: Option[T] = value

  override def fill(newValue: T): MutableCell[T] = {
    copy(value = Some(newValue))
  }
}

case class LiteralCell[T](uniqId: UniqId, value: T) extends Cell[T] {
  override def read: Option[T] = Some(value)

  override def stable: Boolean = true

  override def fill(newValue: T): LiteralCell[T] = throw new UnsupportedOperationException("LiteralCell cannot be filled")
}

trait Propagator[Ability] extends HasUniqId {
  def readingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  def writingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  def zonkingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  /**
   * @return true if the propagator finished its work
   */
  def run(using state: StateAbility[Ability], more: Ability): Boolean

  /** make a best guess for zonkingCells */
  def naiveZonk(using state: StateAbility[Ability], more: Ability): Boolean
}

trait CellsStateAbility {
  def read[T <: Cell[?]](id: UniqIdOf[T]): Option[T]

  def fill[T <: Cell[U], U](id: UniqIdOf[T], f: U): Unit

  def addCell[T <: Cell[?]](cell: T): Unit
  
  def isStable[T <: Cell[?]](id: UniqIdOf[T]): Boolean = read(id).exists((x:T)=>x.stable)
  
  def notStable[T <: Cell[?]](id: UniqIdOf[T]): Boolean = !isStable(id)
}

type CellsState = Map[UniqIdOf[Cell[?]], Cell[?]]
type PropagatorsState[Ability] = Map[UniqIdOf[Propagator[Ability]], Propagator[Ability]]

case class State[Ability](cells: CellsState, propagators: PropagatorsState[Ability], didChanged: Vector[UniqIdOf[Cell[?]]]) {
  def stable: Boolean = didChanged.isEmpty
}

trait StateAbility[Ability] extends CellsStateAbility {
  def addPropagator(propagator: Propagator[Ability])(using more: Ability): Unit

  def tick(using more: Ability): Unit

  def stable: Boolean

  def tickAll(using more: Ability): Unit = {
    while (!stable) {
      tick(using more)
    }
  }

  /** make a best guess for those cells */
  def naiveZonk(cells: Vector[UniqIdOf[Cell[?]]])(using more: Ability): Unit
}


class StateCells[Ability](var state: State[Ability]) extends StateAbility[Ability] {
  override def stable: Boolean = state.stable

  override def read[T <: Cell[?]](id: UniqIdOf[T]): Option[T] = state.cells.get(id).asInstanceOf[Option[T]]

  override def fill[T <: Cell[U], U](id: UniqIdOf[T], f: U): Unit = {
    update[T](id, _.fill(f).asInstanceOf[T])
  }

  private def update[T <: Cell[?]](id: UniqIdOf[T], f: T => T): Unit = {
    state.cells.get(id) match {
      case Some(cell) =>
        val newCell = f(cell.asInstanceOf[T])
        if (cell != newCell) {
          state = state.copy(didChanged = state.didChanged :+ id, cells = state.cells.updated(id, newCell))
        }
      case None =>
        throw new IllegalArgumentException(s"Cell with id $id not found")
    }
  }

  override def addCell[T <: Cell[?]](cell: T): Unit = {
    state = state.copy(cells = state.cells.updated(cell.uniqId, cell))
  }

  override def addPropagator(propagator: Propagator[Ability])(using more: Ability): Unit = {
    val uniqId = propagator.uniqId
    state = state.copy(propagators = state.propagators.updated(uniqId, propagator))
    if(propagator.run(using this, more)){
      state = state.copy(propagators = state.propagators.removed(uniqId))
    }
  }

  override def tick(using more: Ability): Unit = {
    val didChanged = state.didChanged
    state = state.copy(didChanged = Vector.empty)
    state.propagators.filter((_, propagator) => propagator.readingCells.exists(didChanged.contains)).foreach {
      case (pid, propagator) =>
        if (state.propagators.contains(pid)) {
          val done = propagator.run(using this, more)
          if (done) {
            state = state.copy(propagators = state.propagators.removed(pid))
          }
        }
    }
  }

  override def naiveZonk(cells: Vector[UniqIdOf[Cell[?]]])(using more: Ability): Unit = {
    val cellsToZonk = cells.filter(id => !state.cells(id).stable)
    state.propagators.filter((_, propagator) => propagator.zonkingCells.exists(cellsToZonk.contains)).foreach {
      case (pid, propagator) =>
        if (state.propagators.contains(pid)) {
          val done = propagator.naiveZonk(using this, more)
          if (done) {
            state = state.copy(propagators = state.propagators.removed(pid))
          }
        }
    }
  }
}

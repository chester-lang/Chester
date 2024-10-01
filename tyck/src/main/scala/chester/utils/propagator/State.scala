package chester.utils.propagator

import chester.syntax.core.*

type CellId[T] = UniqIdOf[Cell[T]]

type UniqOfOr[T] = CellId[T] | T

sealed trait Cell[T] extends HasUniqId {
  override def uniqId: UniqIdOf[Cell[T]]

  def read: Option[T]

  def stable: Boolean = read.isDefined

  /** fill an unstable cell */
  def fill(newValue: T): Cell[T]
}

case class OnceCell[T]( value: Option[T] = None,uniqId: UniqIdOf[OnceCell[T]]= UniqId.generate[OnceCell[T]]) extends Cell[T] {
  override def read: Option[T] = value

  override def fill(newValue: T): OnceCell[T] = {
    require(value.isEmpty)
    copy(value = Some(newValue))
  }
}

case class MutableCell[T](value: Option[T],uniqId: UniqIdOf[MutableCell[T]]= UniqId.generate[MutableCell[T]]) extends Cell[T] {
  override def read: Option[T] = value

  override def fill(newValue: T): MutableCell[T] = {
    copy(value = Some(newValue))
  }
}

case class LiteralCell[T](value: T, uniqId: UniqIdOf[LiteralCell[T]] = UniqId.generate[LiteralCell[T]]) extends Cell[T] {
  override def read: Option[T] = Some(value)

  override def stable: Boolean = true

  override def fill(newValue: T): LiteralCell[T] = throw new UnsupportedOperationException("LiteralCell cannot be filled")
}

trait Propagator[Ability] extends HasUniqId {
  override def uniqId: UniqIdOf[Propagator[Ability]]
  def readingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  def writingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  def zonkingCells: Set[UniqIdOf[Cell[?]]] = Set.empty

  /**
   * @return true if the propagator finished its work
   */
  def run(using state: StateAbility[Ability], more: Ability): Boolean

  /** make a best guess for zonkingCells */
  def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ability], more: Ability): ZonkResult
}

trait CellsStateAbility {
  def readCell[T <: Cell[?]](id: UniqIdOf[T]): Option[T]

  def read[U](id: UniqIdOf[ Cell[U]]): Option[U] = readCell[Cell[U]](id).get.read

  def fill[T <: Cell[U], U](id: UniqIdOf[T], f: U): Unit

  def addCell[T <: Cell[?]](cell: T): Unit

  def isStable[T <: Cell[?]](id: UniqIdOf[T]): Boolean = readCell(id).exists((x: T) => x.stable)

  def notStable[T <: Cell[?]](id: UniqIdOf[T]): Boolean = !isStable(id)
}

type CellsState = Map[UniqIdOf[Cell[?]], Cell[?]]
private val CellsStateEmpty: CellsState = Map.empty
type PropagatorsState[Ability] = Map[UniqIdOf[Propagator[Ability]], Propagator[Ability]]
private inline def PropagatorsStateEmpty[Ability]: PropagatorsState[Ability] = Map.empty

case class State[Ability](cells: CellsState = CellsStateEmpty, propagators: PropagatorsState[Ability] = PropagatorsStateEmpty[Ability], didChanged: Vector[UniqIdOf[Cell[?]]] = Vector.empty) {
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

  def toId[T](x: UniqOfOr[T]): UniqIdOf[Cell[T]] = x match {
    case x: UniqId => x.asInstanceOf[UniqIdOf[Cell[T]]]
    case x: T => {
      val cell = LiteralCell[T]( x)
      addCell(cell)
      cell.uniqId
    }
  }
}

enum ZonkResult {
  case Done extends ZonkResult
  case Require(needed: Vector[UniqIdOf[Cell[?]]]) extends ZonkResult
  case NotYet extends ZonkResult
}

class StateCells[Ability](var state: State[Ability] = State[Ability]()) extends StateAbility[Ability] {
  override def stable: Boolean = state.stable

  override def readCell[T <: Cell[?]](id: UniqIdOf[T]): Option[T] = state.cells.get(id).asInstanceOf[Option[T]]

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
    if (propagator.run(using this, more)) {
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
    var cellsNeeded = Vector.empty[UniqIdOf[Cell[?]]]
    while (true) {
      tickAll
      val cellsToZonk = if (cellsNeeded.nonEmpty) {
        val a = cellsNeeded
        cellsNeeded = Vector.empty
        a ++ cells.filter(id => !state.cells(id).stable)
      } else {
        cells.filter(id => !state.cells(id).stable)
      }
      state.propagators.filter((_, propagator) => propagator.zonkingCells.exists(cellsToZonk.contains)).foreach {
        case (pid, propagator) =>
          if (state.propagators.contains(pid)) {
            val result = propagator.naiveZonk(cells)(using this, more)
            result match {
              case ZonkResult.Done =>
                state = state.copy(propagators = state.propagators.removed(pid))
              case ZonkResult.Require(needed) =>
                cellsNeeded = cellsNeeded ++ needed
              case ZonkResult.NotYet =>
            }
          }
      }
      tickAll
      if (cellsToZonk.isEmpty) return
    }
  }
}

package chester.utils.propagator

import chester.syntax.core.*

type CellId[T] = UniqIdOf[Cell[T]]
type SeqId[T] = UniqIdOf[SeqCell[T]]

type CellIdOr[T] = CellId[T] | T

sealed trait Cell[T] extends HasUniqId {
  override def uniqId: UniqIdOf[Cell[T]]

  def read: Option[T]

  def hasValue: Boolean = read.isDefined

  /** fill an unstable cell */
  def fill(newValue: T): Cell[T]
}

sealed trait SeqCell[T] extends Cell[Seq[T]] {
  def add(newValue: T): SeqCell[T]

  override def fill(newValue: Seq[T]): SeqCell[T] = throw new UnsupportedOperationException("SeqCell cannot be filled")
}

sealed trait MapCell[A, B] extends Cell[Map[A, B]] {
  def add(key: A, value: B): MapCell[A, B]

  override def fill(newValue: Map[A, B]): MapCell[A, B] = throw new UnsupportedOperationException("MapCell cannot be filled")
}

case class OnceCell[T](value: Option[T] = None, uniqId: UniqIdOf[OnceCell[T]] = UniqId.generate[OnceCell[T]]) extends Cell[T] {
  override def read: Option[T] = value

  override def fill(newValue: T): OnceCell[T] = {
    require(value.isEmpty)
    copy(value = Some(newValue))
  }
}

object OnceCell {
  def create[T](value: Option[T] = None)(using state: StateAbility[?]): CellId[T] = {
    val cell = OnceCell[T](value)
    state.addCell(cell)
    cell.uniqId
  }
}

case class MutableCell[T](value: Option[T], uniqId: UniqIdOf[MutableCell[T]] = UniqId.generate[MutableCell[T]]) extends Cell[T] {
  override def read: Option[T] = value

  override def fill(newValue: T): MutableCell[T] = {
    copy(value = Some(newValue))
  }
}

case class CollectionCell[T](value: Vector[T] = Vector.empty, uniqId: UniqIdOf[CollectionCell[T]] = UniqId.generate[CollectionCell[T]]) extends SeqCell[T] {
  override def read: Option[Vector[T]] = Some(value)

  override def add(newValue: T): CollectionCell[T] = copy(value = value :+ newValue)
}

object CollectionCell {
  def create[T](using state: StateAbility[?]): SeqId[T] = {
    val cell = CollectionCell[T]()
    state.addCell(cell)
    cell.uniqId
  }
}

case class MappingCell[A, B](value: Map[A, B] = Map.empty[A, B], uniqId: UniqIdOf[MappingCell[A, B]] = UniqId.generate[MappingCell[A, B]]) extends MapCell[A, B] {
  override def read: Option[Map[A, B]] = Some(value)

  override def add(key: A, newValue: B): MappingCell[A, B] = copy(value = value + (key -> newValue))
}

object MappingCell {
  def create[A, B](using state: StateAbility[?]): CellId[Map[A, B]] = {
    val cell = MappingCell[A, B]()
    state.addCell(cell)
    cell.uniqId
  }
}

case class LiteralCell[T](value: T, uniqId: UniqIdOf[LiteralCell[T]] = UniqId.generate[LiteralCell[T]]) extends Cell[T] {
  override def read: Option[T] = Some(value)

  override def hasValue: Boolean = true

  override def fill(newValue: T): LiteralCell[T] = throw new UnsupportedOperationException("LiteralCell cannot be filled")
}

def literal[T](t: T)(using state: StateAbility[?]): CellId[T] = {
  val cell = LiteralCell[T](t)
  state.addCell(cell)
  cell.uniqId
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

  def read[U](id: CellId[U]): Option[U] = readCell[Cell[U]](id).get.read

  def fill[T <: Cell[U], U](id: UniqIdOf[T], f: U): Unit

  def add[T <: SeqCell[U], U](id: UniqIdOf[T], f: U): Unit

  def add[T <: MapCell[A, B], A, B](id: UniqIdOf[T], key: A, value: B): Unit

  def addCell[T <: Cell[?]](cell: T): Unit

  def hasValue[T <: Cell[?]](id: UniqIdOf[T]): Boolean = readCell(id).exists((x: T) => x.hasValue)

  def noValue[T <: Cell[?]](id: UniqIdOf[T]): Boolean = !hasValue(id)
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

  def readingZonkings(cells: Vector[UniqIdOf[Cell[?]]]): Vector[Propagator[Ability]]

  /** make a best guess for those cells */
  def naiveZonk(cells: Vector[UniqIdOf[Cell[?]]])(using more: Ability): Unit

  def toId[T](x: CellIdOr[T]): UniqIdOf[Cell[T]] = x match {
    case x: UniqId => x.asInstanceOf[UniqIdOf[Cell[T]]]
    case x: T => {
      val cell = LiteralCell[T](x)
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

  override def add[T <: SeqCell[U], U](id: UniqIdOf[T], f: U): Unit = {
    update[T](id, _.add(f).asInstanceOf[T])
  }

  override def add[T <: MapCell[A, B], A, B](id: UniqIdOf[T], key: A, value: B): Unit = {
    update[T](id, _.add(key, value).asInstanceOf[T])
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

  override def readingZonkings(cells: Vector[UniqIdOf[Cell[?]]]): Vector[Propagator[Ability]] = {
    state.propagators.filter((_, propagator) => propagator.zonkingCells.exists(cells.contains)).values.toVector
  }

  override def naiveZonk(cells: Vector[UniqIdOf[Cell[?]]])(using more: Ability): Unit = {
    var cellsNeeded = Vector.empty[UniqIdOf[Cell[?]]]
    while (true) {
      tickAll
      val cellsToZonk = if (cellsNeeded.nonEmpty) {
        val a = cellsNeeded
        cellsNeeded = Vector.empty
        (a ++ cells).filter(id => !state.cells(id).hasValue)
      } else {
        cells.filter(id => !state.cells(id).hasValue)
      }
      val xs = state.propagators.filter((_, propagator) => propagator.zonkingCells.exists(cellsToZonk.contains))
      val uncorvedCells = cellsToZonk.filter(id => !xs.values.exists(_.zonkingCells.contains(id)))
      if (uncorvedCells.nonEmpty) {
        throw new IllegalStateException(s"Cells $uncorvedCells are not covered by any propagator")
      }
      xs.foreach {
        case (pid, propagator) =>
          tickAll
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

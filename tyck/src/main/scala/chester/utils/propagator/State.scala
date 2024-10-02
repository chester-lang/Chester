package chester.utils.propagator

import chester.syntax.core

// TODO: maybe distinguish between read and fill to have more sound Scala types and functions. One is +T and one is -T
trait ProvideCellId {
  type CIdOf[+T <:Cell[?]]
  def generate[T <:Cell[?]]: CIdOf[T]
  def generateP[T <:Propagator[?]]: PIdOf[T]
  type PIdOf[+T<:Propagator[?]]
  type CellId[T] = CIdOf[Cell[T]]
  type SeqId[T] = CIdOf[SeqCell[T]]
  type CellIdOr[T] = CellId[T] | T
  def isCId(x: Any): Boolean

  sealed trait Cell[T] {
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

  case class OnceCell[T](value: Option[T] = None) extends Cell[T] {
    override def read: Option[T] = value

    override def fill(newValue: T): OnceCell[T] = {
      require(value.isEmpty)
      copy(value = Some(newValue))
    }
  }

  case class MutableCell[T](value: Option[T]) extends Cell[T] {
    override def read: Option[T] = value

    override def fill(newValue: T): MutableCell[T] = {
      copy(value = Some(newValue))
    }
  }

  case class CollectionCell[T](value: Vector[T] = Vector.empty) extends SeqCell[T] {
    override def read: Option[Vector[T]] = Some(value)

    override def add(newValue: T): CollectionCell[T] = copy(value = value :+ newValue)
  }

  case class MappingCell[A, B](value: Map[A, B] = Map.empty[A, B], uniqId: CIdOf[MappingCell[A, B]] = generate[MappingCell[A, B]]) extends MapCell[A, B] {
    override def read: Option[Map[A, B]] = Some(value)

    override def add(key: A, newValue: B): MappingCell[A, B] = copy(value = value + (key -> newValue))
  }

  case class LiteralCell[T](value: T, uniqId: CIdOf[LiteralCell[T]] = generate[LiteralCell[T]]) extends Cell[T] {
    override def read: Option[T] = Some(value)

    override def hasValue: Boolean = true

    override def fill(newValue: T): LiteralCell[T] = throw new UnsupportedOperationException("LiteralCell cannot be filled")
  }

  def literal[T](t: T)(using state: StateAbility[?]): CellId[T] = {
    val cell = LiteralCell[T](t)
    state.addCell(cell)
    cell.uniqId
  }

  trait Propagator[Ability] {

    def readingCells: Set[CIdOf[Cell[?]]] = Set.empty

    def writingCells: Set[CIdOf[Cell[?]]] = Set.empty

    def zonkingCells: Set[CIdOf[Cell[?]]] = Set.empty

    /**
     * @return true if the propagator finished its work
     */
    def run(using state: StateAbility[Ability], more: Ability): Boolean

    /** make a best guess for zonkingCells */
    def naiveZonk(needed: Vector[CIdOf[Cell[?]]])(using state: StateAbility[Ability], more: Ability): ZonkResult
  }

  trait CellsStateAbility {
    def readCell[T <: Cell[?]](id: CIdOf[T]): Option[T]

    def read[U](id: CellId[U]): Option[U] = readCell[Cell[U]](id).get.read

    protected def update[T <: Cell[?]](id: CIdOf[T], f: T => T): Unit

    def fill[T <: Cell[U], U](id: CIdOf[T], f: U): Unit = {
      update[T](id, _.fill(f).asInstanceOf[T])
    }

    def add[T <: SeqCell[U], U](id: CIdOf[T], f: U): Unit = {
      update[T](id, _.add(f).asInstanceOf[T])
    }

    def add[T <: MapCell[A, B], A, B](id: CIdOf[T], key: A, value: B): Unit = {
      update[T](id, _.add(key, value).asInstanceOf[T])
    }

    def addCell[T <: Cell[?]](cell: T): CIdOf[T]

    def hasValue[T <: Cell[?]](id: CIdOf[T]): Boolean = readCell(id).exists((x: T) => x.hasValue)

    def noValue[T <: Cell[?]](id: CIdOf[T]): Boolean = !hasValue(id)
  }

  trait StateAbility[Ability] extends CellsStateAbility {
    def addPropagator[T<:Propagator[Ability]](propagator: T)(using more: Ability): PIdOf[T]

    def tick(using more: Ability): Unit

    def stable: Boolean

    def tickAll(using more: Ability): Unit = {
      while (!stable) {
        tick(using more)
      }
    }

    def readingZonkings(cells: Vector[CIdOf[Cell[?]]]): Vector[Propagator[Ability]]

    /** make a best guess for those cells */
    def naiveZonk(cells: Vector[CIdOf[Cell[?]]])(using more: Ability): Unit

    def toId[T](x: CellIdOr[T]): CIdOf[Cell[T]] = x match {
      case x if isCId(x) => x.asInstanceOf[CIdOf[Cell[T]]]
      case x: T => {
        val cell = LiteralCell[T](x)
        addCell(cell)
        cell.uniqId
      }
    }
  }

  enum ZonkResult {
    case Done extends ZonkResult
    case Require(needed: Seq[CIdOf[Cell[?]]]) extends ZonkResult
    case NotYet extends ZonkResult
  }

}

trait ProvideImmutable extends ProvideCellId {
  type CIdOf[+T <:Cell[?]] = core.UniqIdOf[T]
  type PIdOf[+T<:Propagator[?]] = core.UniqIdOf[T]
  override def generate[T <:Cell[?]]: CIdOf[T] = core.UniqId.generate
  override def generateP[T <:Propagator[?]]: PIdOf[T] = core.UniqId.generate
  override def isCId(x: Any): Boolean = x.isInstanceOf[core.UniqId]

  type CellsState = Map[CIdOf[Cell[?]], Cell[?]]
  private val CellsStateEmpty: CellsState = Map.empty
  type PropagatorsState[Ability] = Map[PIdOf[Propagator[Ability]], Propagator[Ability]]

  private inline def PropagatorsStateEmpty[Ability]: PropagatorsState[Ability] = Map.empty

  case class State[Ability](cells: CellsState = CellsStateEmpty, propagators: PropagatorsState[Ability] = PropagatorsStateEmpty[Ability], didChanged: Vector[CIdOf[Cell[?]]] = Vector.empty) {
    def stable: Boolean = didChanged.isEmpty
  }

  class StateCells[Ability](var state: State[Ability] = State[Ability]()) extends StateAbility[Ability] {
    override def stable: Boolean = state.stable

    override def readCell[T <: Cell[?]](id: CIdOf[T]): Option[T] = state.cells.get(id).asInstanceOf[Option[T]]

    override def update[T <: Cell[?]](id: CIdOf[T], f: T => T): Unit = {
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

    override def addCell[T <: Cell[?]](cell: T): CIdOf[T] = {
      val id = generate[T]
      state = state.copy(cells = state.cells.updated(id, cell))
      id
    }

    override def addPropagator[T<:Propagator[Ability]](propagator: T)(using more: Ability): PIdOf[T] = {
      val uniqId = generateP[T]
      state = state.copy(propagators = state.propagators.updated(uniqId, propagator))
      if (propagator.run(using this, more)) {
        state = state.copy(propagators = state.propagators.removed(uniqId))
      }
      uniqId
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

    override def readingZonkings(cells: Vector[CIdOf[Cell[?]]]): Vector[Propagator[Ability]] = {
      state.propagators.filter((_, propagator) => propagator.zonkingCells.exists(cells.contains)).values.toVector
    }

    override def naiveZonk(cells: Vector[CIdOf[Cell[?]]])(using more: Ability): Unit = {
      var cellsNeeded = Vector.empty[CIdOf[Cell[?]]]
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

}

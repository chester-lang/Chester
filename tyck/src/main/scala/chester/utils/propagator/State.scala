package chester.utils.propagator

import chester.syntax.core
import cats.implicits.*

// TODO: maybe distinguish between read and fill to have more sound Scala types and functions. One is +T and one is -T
trait ProvideCellId {
  type CIdOf[+T <:Cell[?]]
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

  case class MappingCell[A, B](value: Map[A, B] = Map.empty[A, B]) extends MapCell[A, B] {
    override def read: Option[Map[A, B]] = Some(value)

    override def add(key: A, newValue: B): MappingCell[A, B] = copy(value = value + (key -> newValue))
  }

  case class LiteralCell[T](value: T) extends Cell[T] {
    override def read: Option[T] = Some(value)

    override def hasValue: Boolean = true

    override def fill(newValue: T): LiteralCell[T] = throw new UnsupportedOperationException("LiteralCell cannot be filled")
  }

  def literal[T](t: T)(using state: StateAbility[?]): CellId[T] = {
    val cell = state.addCell(LiteralCell[T](t))
    cell
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
        val cell = addCell(LiteralCell[T](x))
        cell
      }
    }
  }

  enum ZonkResult {
    case Done extends ZonkResult
    case Require(needed: Seq[CIdOf[Cell[?]]]) extends ZonkResult
    case NotYet extends ZonkResult
  }

}

trait CommonPropagator[Ck] extends ProvideCellId {

  case class Merge[T](a: CellId[T], b: CellId[T]) extends Propagator[Ck] {
    override val readingCells = Set(a, b)
    override val writingCells = Set(a, b)
    override val zonkingCells = Set(a, b)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val aVal = state.read(a)
      val bVal = state.read(b)
      if (aVal.isDefined && bVal.isDefined) {
        if (aVal.get == bVal.get) return true
        throw new IllegalStateException("Merge propagator should not be used if the values are different")
        return true
      }
      if (aVal.isDefined) {
        state.fill(b, aVal.get)
        return true
      }
      if (bVal.isDefined) {
        state.fill(a, bVal.get)
        return true
      }
      false
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val aVal = state.read(a)
      val bVal = state.read(b)
      if (aVal.isDefined && bVal.isDefined) {
        if (aVal.get == bVal.get) return ZonkResult.Done
        throw new IllegalStateException("Merge propagator should not be used if the values are different")
        return ZonkResult.Done
      }
      if (aVal.isDefined) {
        state.fill(b, aVal.get)
        return ZonkResult.Done
      }
      if (bVal.isDefined) {
        state.fill(a, bVal.get)
        return ZonkResult.Done
      }
      ZonkResult.NotYet
    }
  }

  case class FlatMaping[T, U](xs: Seq[CellId[T]], f: Seq[T] => U, result: CellId[U]) extends Propagator[Ck] {
    override val readingCells = xs.toSet
    override val writingCells = Set(result)
    override val zonkingCells = Set(result)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      xs.traverse(state.read(_)).map(f) match {
        case Some(result) => {
          state.fill(this.result, result)
          true
        }
        case None => false
      }
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val needed = xs.filter(state.noValue(_))
      if (needed.nonEmpty) return ZonkResult.Require(needed)
      val done = run
      require(done)
      ZonkResult.Done
    }
  }

  def FlatMap[T, U](xs: Seq[CellId[T]])(f: Seq[T] => U)(using ck: Ck, state: StateAbility[Ck]): CellId[U] = {
    val cell = state.addCell(OnceCell[U]())
    state.addPropagator(FlatMaping(xs, f, cell))
    cell
  }

  def Map1[T, U](x: CellId[T])(f: T => U)(using ck: Ck, state: StateAbility[Ck]): CellId[U] = {
    val cell = state.addCell(OnceCell[U]())
    state.addPropagator(FlatMaping(Vector(x), (xs: Seq[T]) => f(xs.head), cell))
    cell
  }

  def Map2[A, B, C](x: CellId[A], y: CellId[B])(f: (A, B) => C)(using ck: Ck, state: StateAbility[Ck]): CellId[C] = {
    val cell = state.addCell(OnceCell[C]())
    state.addPropagator(FlatMaping(Vector[CellId[Any]](x.asInstanceOf[CellId[Any]], y.asInstanceOf[CellId[Any]]), (xs: Seq[Any]) => f(xs(0).asInstanceOf[A], xs(1).asInstanceOf[B]), cell))
    cell
  }

  def Traverse[A](x: Seq[CellId[A]])(using ck: Ck, state: StateAbility[Ck]): CellId[Seq[A]] = FlatMap(x)(identity)

}

trait ProvideImmutable extends ProvideCellId {
  type CIdOf[+T <:Cell[?]] = core.UniqIdOf[T]
  type PIdOf[+T<:Propagator[?]] = core.UniqIdOf[T]
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
      val id = core.UniqId.generate[T]
      state = state.copy(cells = state.cells.updated(id, cell))
      id
    }

    override def addPropagator[T<:Propagator[Ability]](propagator: T)(using more: Ability): PIdOf[T] = {
      val uniqId = core.UniqId.generate[T]
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

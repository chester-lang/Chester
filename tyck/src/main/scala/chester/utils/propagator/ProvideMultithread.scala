package chester.utils.propagator

import chester.syntax.core.{UniqId, UniqIdOf}
import java.util.concurrent.atomic.{AtomicBoolean, AtomicReference}
import java.util.concurrent._
import scala.collection.mutable
import scala.jdk.CollectionConverters._

trait ProvideMultithread extends ProvideImpl {

  class HoldCell[+T <: Cell[?]](val uniqId: UniqIdOf[Impl[?]], initialValue: T) {
    private val storeRef = new AtomicReference[Cell[?]](initialValue)
    private val didChangeRef = new AtomicBoolean(false)
    val readingPropagators = new ConcurrentLinkedQueue[PIdOf[Propagator[?]]]()
    val zonkingPropagators = new ConcurrentLinkedQueue[PIdOf[Propagator[?]]]()

    def store: Cell[?] = storeRef.get()

    /** Atomically updates the store */
    def compareAndSetStore(expectedValue: Cell[?], newValue: Cell[?]): Boolean = {
      storeRef.compareAndSet(expectedValue, newValue)
    }

    def didChange: Boolean = didChangeRef.get()

    def setDidChange(value: Boolean): Unit = {
      didChangeRef.set(value)
    }

    def noAnyValue: Boolean = store.noAnyValue
    def noStableValue: Boolean = store.noStableValue
  }

  type CIdOf[+T <: Cell[?]] = HoldCell[T]
  type PIdOf[+T <: Propagator[?]] = HoldPropagator[T]
  type CellId[T] = CIdOf[Cell[T]]
  type SeqId[T] = CIdOf[SeqCell[T]]
  type CellIdOr[T] = CellId[T] | T

  def isCId(x: Any): Boolean = x.isInstanceOf[HoldCell[?]]

  override def assumeCId(x: Any): CIdOf[Cell[?]] = x.asInstanceOf[CIdOf[Cell[?]]]

  class HoldPropagator[+T <: Propagator[?]](val uniqId: UniqIdOf[Impl[?]], initialValue: T) {
    private val storeRef = new AtomicReference[Propagator[?]](initialValue)
    private val aliveRef = new AtomicBoolean(true)

    def store: Propagator[?] = storeRef.get()

    /** Atomically updates the store */
    def compareAndSetStore(expectedValue: Propagator[?], newValue: Propagator[?]): Boolean = {
      storeRef.compareAndSet(expectedValue, newValue)
    }

    def alive: Boolean = aliveRef.get()

    def setAlive(value: Boolean): Unit = {
      aliveRef.set(value)
    }
  }

  class Impl[Ability](val uniqId: UniqIdOf[Impl[Ability]] = UniqId.generate[Impl[Ability]])
      extends StateAbility[Ability] {

    private val didChanged = new ConcurrentLinkedQueue[CIdOf[?]]()
    private val propagators = new ConcurrentLinkedQueue[PIdOf[Propagator[Ability]]]()
    private val didSomething = new AtomicBoolean(false)
    private val forkJoinPool = new ForkJoinPool()

    override def readCell[T <: Cell[?]](id: CIdOf[T]): Option[T] = {
      require(id.uniqId == uniqId)
      Some(id.store.asInstanceOf[T])
    }

    override def update[T <: Cell[?]](id: CIdOf[T], f: T => T): Unit = {
      require(id.uniqId == uniqId)
      var updated = false
      while (!updated) {
        val oldValue = id.store.asInstanceOf[T]
        val newValue = f(oldValue)
        updated = id.compareAndSetStore(oldValue, newValue)
        if (updated) {
          didSomething.set(true)
          id.setDidChange(true)
          didChanged.add(id)
        }
      }
    }

    override def fill[T <: Cell[U], U](id: CIdOf[T], value: U): Unit = {
      require(id.uniqId == uniqId)
      var updated = false
      while (!updated) {
        val oldValue = id.store.asInstanceOf[T]
        val newValue = oldValue.fill(value).asInstanceOf[T]
        updated = id.compareAndSetStore(oldValue, newValue)
        if (updated) {
          didSomething.set(true)
          id.setDidChange(true)
          didChanged.add(id)
        }
      }
    }

    override def addCell[T <: Cell[?]](cell: T): CIdOf[T] = {
      val id = new HoldCell[T](uniqId, cell)
      didChanged.add(id)
      id
    }

    override def addPropagator[T <: Propagator[Ability]](propagator: T)(using more: Ability): PIdOf[T] = {
      val id = new HoldPropagator[T](uniqId, propagator)
      propagators.add(id.asInstanceOf[PIdOf[Propagator[Ability]]])
      id
    }

    override def stable: Boolean = propagators.isEmpty

    override def tick(using more: Ability): Unit = {
      val didChangedSet = didChanged.iterator().asScala.toSet
      didChanged.clear()

      val activePropagators = propagators.iterator().asScala.toVector
      for (p <- activePropagators) {
        require(p.uniqId == uniqId)
        val propagator = p.store.asInstanceOf[Propagator[Ability]]
        val readsChanged = propagator.readingCells.exists(didChangedSet.contains)
        if (readsChanged) {
          if (propagator.run(using this, more)) {
            propagators.remove(p)
            p.setAlive(false)
          }
        }
      }
    }

    override def tickAll(using more: Ability): Unit = {
      while (!stable) {
        tick(using more)
      }
    }

    override def naiveZonk(cells: Vector[CIdOf[Cell[?]]])(using more: Ability): Unit = {
      var cellsNeeded = cells
      var tryFallback = 0
      var loop = true

      while (loop) {
        didSomething.set(false)
        tickAll(using more)

        // Filter cells that don't have any value yet
        cellsNeeded = cellsNeeded.filter(this.noAnyValue(_))
          .sortBy(x => -x.zonkingPropagators.asScala.map(_.store.score).sum)

        if (cellsNeeded.isEmpty) {
          return
        }

        // Prepare a thread-safe collection to collect new needed cells
        val newNeededCells = new ConcurrentLinkedQueue[CIdOf[Cell[?]]]()

        if (tryFallback == 0) {
          // First fallback: try naiveZonk
          val zonkTasks = cellsNeeded.map(c => new ZonkTask(c, firstFallback = true, this, more, newNeededCells))
          ForkJoinTask.invokeAll(zonkTasks.asJava)

          if (didSomething.get()) {
            tryFallback = 0
            cellsNeeded = cellsNeeded ++ newNeededCells.asScala
            // Loop continues from the beginning
          } else {
            tryFallback += 1
          }
        } else if (tryFallback == 1) {
          // Second fallback: try naiveFallbackZonk
          val fallbackTasks = cellsNeeded.map(c => new ZonkTask(c, firstFallback = false, this, more, newNeededCells))
          ForkJoinTask.invokeAll(fallbackTasks.asJava)

          if (didSomething.get()) {
            tryFallback = 0
            cellsNeeded = cellsNeeded ++ newNeededCells.asScala
            // Loop continues from the beginning
          } else {
            tryFallback += 1
          }
        } else if (tryFallback == 2) {
          // Last fallback: try default values
          val defaultTasks = cellsNeeded.map(c => new DefaultValueTask(c, this))
          ForkJoinTask.invokeAll(defaultTasks.asJava)

          if (didSomething.get()) {
            tryFallback = 0
            // Loop continues from the beginning
          } else {
            // All fallbacks failed
            throw new IllegalStateException(s"Cells $cellsNeeded are not covered by any propagator")
          }
        } else {
          // Safety net to prevent infinite loops
          loop = false
        }
      }
    }

    class ZonkTask(
        c: CIdOf[Cell[?]],
        firstFallback: Boolean,
        state: Impl[Ability],
        more: Ability,
        newNeededCells: ConcurrentLinkedQueue[CIdOf[Cell[?]]]
    ) extends RecursiveAction {
      override def compute(): Unit = {
        require(c.uniqId == uniqId)
        if (c.noAnyValue) {
          val aliveP = c.zonkingPropagators.asScala.filter(_.alive).toSeq
          val zonking = aliveP.sortBy(x => -x.store.score)
          for (p <- zonking) {
            require(p.uniqId == uniqId)
            state.tickAll(using more)
            if (c.noAnyValue && p.alive) {
              val propagator = p.store.asInstanceOf[Propagator[Ability]]
              val zonkResult = if (firstFallback) {
                propagator.naiveZonk(Vector(c))(using state, more)
              } else {
                propagator.naiveFallbackZonk(Vector(c))(using state, more)
              }
              zonkResult match {
                case ZonkResult.Done =>
                  p.setAlive(false)
                  didSomething.set(true)
                  return // Stop immediately when something works
                case ZonkResult.Require(needed) =>
                  val needed1 = needed.filter(state.noStableValue(_))
                  if (needed1.nonEmpty) {
                    needed1.foreach(newNeededCells.add)
                    didSomething.set(true)
                  }
                case ZonkResult.NotYet =>
                  // Do nothing
              }
            }
          }
        }
      }
    }

    class DefaultValueTask(c: CIdOf[Cell[?]], state: Impl[Ability]) extends RecursiveAction {
      override def compute(): Unit = {
        if (c.noAnyValue && c.store.default.isDefined) {
          state.fill(c.asInstanceOf[CIdOf[Cell[Any]]], c.store.default.get)
          didSomething.set(true)
        }
      }
    }
  }
}
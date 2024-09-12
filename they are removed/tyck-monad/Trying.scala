package chester.tyck

import cats.Monad
import chester.error.{TyckError, TyckWarning}
import cps.{CpsMonad, CpsMonadContext}

import scala.annotation.targetName

/*
trait Trying[State, +Result] {
  def apply(state: State): Vector[TyckResult[State, Result]]
}
*/
opaque type Trying[State, +Result] = State => Vector[TyckResult[State, Result]]

//implicit inline def trying0[T, U](inline f: T => Vector[TyckResult[T, U]]): Trying[T, U] = f
implicit inline def hackfailed0[T, U](inline x: ([X] =>> Trying[T, X])[U]): Trying[T, U] = x
implicit inline def hackfailed1[A, T, U](inline x: A => ([X] =>> Trying[T, X])[U]): A => Trying[T, U] = x
implicit inline def hackfailed2[T, U](inline x: Unit => ([X] =>> Trying[T, X])[U]): Unit => Trying[T, U] = x

private def processSeq[State, Result](seq: Vector[TyckResult[State, Result]]): Vector[TyckResult[State, Result]] = {
  val (filtered, errors) = seq.partition(_.errorsEmpty)
  if (filtered.length < seq.length) filtered :+ errors.head else seq
}

extension [State, Result](self: Trying[State, Result] | ([X] =>> Trying[State, X])[Result]) {
  inline def run(inline state: State): Vector[TyckResult[State, Result]] = {
    val seq = self.apply(state)
    processSeq(seq)
  }

  inline def map[U](inline f: Result => U): Trying[State, U] = { (state: State) =>
    self.run(state).map { x => x.copy(result = f(x.result)) }
  }

  inline def flatMap[U](inline f: Result => Trying[State, U]): Trying[State, U] = { (state0: State) =>
    self.run(state0).flatMap { x =>
      f(x.result).run(x.state).map { y =>
        x >> y
      }
    }
  }

  inline def ||[U >: Result](inline other: Trying[State, U]): Trying[State, U] = { (state: State) =>
    val seq1 = self.run(state)
    val seq2 = other.run(state)
    seq1 ++ seq2
  }
}

object Trying {
  private def flatten[T](xs: Seq[Seq[T]]): Vector[T] = {
    var result = Vector.empty[T]
    var iter = xs.toVector.filter(_.nonEmpty)
    while (iter.nonEmpty) {
      val head = iter.map(_.head)
      val tail = iter.map(_.tail)
      result ++= head
      iter = tail.filter(_.nonEmpty)
    }
    result
  }

  def or[State, T](xs: Seq[Trying[State, T]]): Trying[State, T] = { (state: State) =>
    flatten(xs.map(_.run(state)))
  }

  @targetName("or1")
  def or[State, T](xs: Trying[State, T]*): Trying[State, T] = or(xs)

  inline def state[State]: Trying[State, State] = { (state: State) =>
    Vector(TyckResult(state = state, result = state))
  }

  inline def state_=[State](inline state: State): Trying[State, Unit] = { _ =>
    Vector(TyckResult(state = state, result = ()))
  }

  inline def error[State](inline error: TyckError): Trying[State, Unit] = { (state: State) =>
    Vector(TyckResult(state = state, result = (), errors = Vector(error)))
  }

  inline def errors[State](inline errors: Vector[TyckError]): Trying[State, Unit] = { (state: State) =>
    Vector(TyckResult(state = state, result = (), errors = errors))
  }

  inline def warning[State](inline warning: TyckWarning): Trying[State, Unit] = { (state: State) =>
    Vector(TyckResult(state = state, result = (), warnings = Vector(warning)))
  }

  inline def pure[State, Value](inline value: Value): Trying[State, Value] = { (state: State) =>
    Vector(TyckResult(state = state, result = value))
  }
}

val cpsMonadTryingInstance: CpsMonadTrying[?] = new CpsMonadTrying
implicit inline def cpsMonadTrying[State]: CpsMonadTrying[State] = cpsMonadTryingInstance.asInstanceOf

final class CpsMonadTrying[State] extends CpsMonad[[X] =>> Trying[State, X]] with CpsMonadContext[[X] =>> Trying[State, X]] {
  private implicit inline def hack1[X](inline f: WF[X]): Trying[State, X] = f
  private implicit inline def hack2[A,B](inline f: A => WF[B]): A => Trying[State, B] = f
  override inline def pure[A](a: A): WF[A] = Trying.pure(a)

  override inline def map[A, B](fa: WF[A])(f: A => B): WF[B] = fa.map(f)

  override inline def flatMap[A, B](fa: WF[A])(f: A => WF[B]): WF[B] = fa.flatMap(f)

  override inline def monad: CpsMonad[WF] = this

  override type Context = this.type

  override inline def apply[T](op: Context => WF[T]): WF[T] = op(this)
}

implicit def monadTrying[State]: Monad[[X] =>> Trying[State, X]] = new MonadTrying[State]

final class MonadTrying[State] extends Monad[[X] =>> Trying[State, X]] {
  type WF[A] = Trying[State, A]

  private implicit inline def hack1[X](inline f: WF[X]): Trying[State, X] = f

  private implicit inline def hack2[A, B](inline f: A => WF[B]): A => Trying[State, B] = f

  override inline def pure[A](a: A): WF[A] = Trying.pure(a)

  override inline def map[A, B](fa: WF[A])(f: A => B): WF[B] = fa.map(f)

  override inline def flatMap[A, B](fa: WF[A])(f: A => WF[B]): WF[B] = fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: A => WF[Either[A, B]]): WF[B] = { (state: State) =>
    f(a).run(state).flatMap { (x: TyckResult[State, Either[A, B]]) =>
      x.result match {
        case Left(a) => tailRecM(a)(f).run(state)
        case Right(b) => Vector(x.copy(result = b))
      }
    }
  }
}

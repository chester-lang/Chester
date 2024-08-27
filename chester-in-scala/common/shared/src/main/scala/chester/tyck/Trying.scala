package chester.tyck

import cats.Monad
import cps.{CpsMonad, CpsMonadContext}

case class TyringResult[+Warning, +Error, +State, +Value](warnings: Vector[Warning], errors: Vector[Error], state: State, result: Value) {
}

trait Trying[Warning, Error, State, Result] {
  def apply(state: State): Vector[TyringResult[Warning, Error, State, Result]]
}

private def processSeq[Warning, Error, State, Result](seq: Vector[TyringResult[Warning, Error, State, Result]]): Vector[TyringResult[Warning, Error, State, Result]] = {
  val filtered = seq.filter(_.errors.isEmpty)
  if (filtered.isEmpty) {
    seq
  } else {
    filtered
  }
}

extension [Warning, Error, State, Result](self: Trying[Warning, Error, State, Result]) {
  inline def run(inline state: State): Vector[TyringResult[Warning, Error, State, Result]] = {
    val seq = self.apply(state)
    processSeq(seq)
  }

  inline def map[U](inline f: Result => U): Trying[Warning, Error, State, U] = { (state: State) =>
    self.run(state).map { x => x.copy(result = f(x.result)) }
  }

  inline def flatMap[U](inline f: Result => Trying[Warning, Error, State, U]): Trying[Warning, Error, State, U] = { (state0: State) =>
    self.run(state0).flatMap { x =>
      f(x.result).run(x.state).map { y =>
        TyringResult(x.warnings ++ y.warnings, x.errors ++ y.errors, y.state, y.result)
      }
    }
  }
}

object Trying {
  inline def pure[Warning, Error, State, Value](inline value: Value): Trying[Warning, Error, State, Value] = { (state: State) =>
    Vector(TyringResult(Vector.empty, Vector.empty, state, value))
  }
}

implicit def cpsMonadTrying[Warning, Error, State]: CpsMonad[[X] =>> Trying[Warning, Error, State, X]] = new CpsMonadTrying[Warning, Error, State]

final class CpsMonadTrying[Warning, Error, State] extends CpsMonad[[X] =>> Trying[Warning, Error, State, X]] with CpsMonadContext[[X] =>> Trying[Warning, Error, State, X]] {
  override inline def pure[A](a: A): WF[A] = Trying.pure(a)

  override inline def map[A, B](fa: WF[A])(f: A => B): WF[B] = fa.map(f)

  override inline def flatMap[A, B](fa: WF[A])(f: A => WF[B]): WF[B] = fa.flatMap(f)

  override inline def monad: CpsMonad[WF] = this

  override type Context = this.type

  override inline def apply[T](op: Context => WF[T]): WF[T] = op(this)
}

implicit def monadTrying[Warning, Error, State]: Monad[[X] =>> Trying[Warning, Error, State, X]] = new MonadTrying[Warning, Error, State]

final class MonadTrying[Warning, Error, State] extends Monad[[X] =>> Trying[Warning, Error, State, X]] {
  type WF[A] = Trying[Warning, Error, State, A]

  override inline def pure[A](a: A): WF[A] = Trying.pure(a)

  override inline def map[A, B](fa: WF[A])(f: A => B): WF[B] = fa.map(f)

  override inline def flatMap[A, B](fa: WF[A])(f: A => WF[B]): WF[B] = fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: A => WF[Either[A, B]]): WF[B] = { (state: State) =>
    f(a).run(state).flatMap { (x: TyringResult[Warning, Error, State, Either[A, B]]) =>
      x.result match {
        case Left(a) => tailRecM(a)(f).run(state)
        case Right(b) => Vector(x.copy(result = b))
      }
    }
  }
}

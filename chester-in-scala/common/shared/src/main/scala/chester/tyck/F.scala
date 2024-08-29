package chester.tyck

import cps.*

type F = [X] =>> Trying[TyckState, X]
implicit inline def convertF[T](inline x: Trying[TyckState, T]): F[T] = x
implicit inline def unconvertF[T](inline x: F[T]): Trying[TyckState, T] = x

extension [F[_], T, G[_]](f: F[T]) {
  transparent inline def !(using inline ctx: _root_.cps.CpsMonadContext[G], inline conversion: _root_.cps.CpsMonadConversion[F, G]): T = await(f)
}

extension [A](xs: Seq[A]) {
  def foldLeftM[B](z: B)(f: (B, A) => F[B]): F[B] = async[F] {
    var acc = z
    for (elem <- xs) {
      acc = f(acc, elem).!
    }
    acc
  }
  def reduceM(f: (A, A) => F[A]): F[A] = xs.tail.foldLeftM(xs.head)(f)
  def mapM[B](f: A => F[B]): F[Vector[B]] = async[F] {
    var acc = Vector.empty[B]
    for (elem <- xs) {
      acc = acc :+ f(elem).!
    }
    acc
  }
}

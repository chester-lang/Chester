package chester.io

import cats.{Id, Monad}
import chester.repl.TerminalInit

import scala.annotation.tailrec
import scala.util.Try

implicit object DefaultMonad extends Monad[Id] {
  @inline
  override inline def pure[A](x: A): A = x

  @inline
  override inline def flatMap[A, B](fa: A)(f: A => B): B = f(fa)

  @inline
  override inline def map[A, B](fa: A)(f: A => B): B = f(fa)

  @tailrec
  override def tailRecM[A, B](a: A)(f: A => Id[Either[A, B]]): Id[B] = f(a) match {
    case Left(a1) => tailRecM(a1)(f)
    case Right(b) => b
  }
}

implicit object DefaultSpawn extends Spawn[Id] {
  @inline
  override inline def spawn(x: => Unit): Unit = x
}

implicit object DefaultTryable extends Tryable[Id] {
  @inline
  override inline def doTry[T](IO: Id[T]): Try[T] = Try(IO)
}

implicit object DefaultIO extends IO[Id] {
  @inline
  override inline def println(x: String): Unit = Predef.println(x)
}

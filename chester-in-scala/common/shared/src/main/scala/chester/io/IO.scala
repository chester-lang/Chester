package chester.io

import cats.Monad
import chester.repl.{ReadLineResult, TerminalInfo, TerminalInit}

import scala.util.Try

trait Spawn[F[_]] {
  def spawn(x: => F[Unit]): Unit
}

trait IO[F[_]] {
  def doTry[T](IO: F[T]): F[Try[T]]

  def println(x: String): F[Unit]

}

object IO {
  inline def doTry[F[_], T](inline IO: F[T])(using inline io: IO[F]): F[Try[T]] = io.doTry(IO)

  inline def println[F[_]](inline x: String)(using inline io: IO[F]): F[Unit] = io.println(x)
}

trait TerminalBlock[Effect[_[_]]] {
  def apply[F[_]](using terminal: Terminal[F], eff: Effect[F]): F[Unit]
}

trait TerminalFactory[F[_], Effect[_[_]]] {
  def runTerminal(init: TerminalInit, block: TerminalBlock[Effect])(using eff: Effect[F]): F[Unit]
}

trait Terminal[F[_]] {
  def readline(info: TerminalInfo): F[ReadLineResult]

  def getHistory: F[Seq[String]]
}
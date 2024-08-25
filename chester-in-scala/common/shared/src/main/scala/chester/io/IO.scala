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

trait Terminal[F[_]] {
  def runTerminal[T](init: TerminalInit, block: InTerminal[F] ?=> F[T]): F[T]
}

object Terminal {
  inline def runTerminal[F[_], T](inline init: TerminalInit)(inline block: InTerminal[F] ?=> F[T])(using inline terminal: Terminal[F]): F[T] =
    terminal.runTerminal(init, block)
}


trait InTerminal[F[_]] {
  def readline(info: TerminalInfo): F[ReadLineResult]

  def getHistory: F[Seq[String]]
}
package chester.io

import cats.Monad

trait IO[F[_]] extends Monad[F] {
  def println(x: String): F[Unit]
}

object IO {
  def println[F[_]](x: String)(using io: IO[F]): F[Unit] = io.println(x)
}

trait TerminalBlock {
  def apply[F[_]](using terminal: Terminal[F]): F[Unit]
}

trait TerminalFactory[F[_]] {
  def runTerminal(block: TerminalBlock): F[Unit]
}

trait Terminal[F[_]] {

}
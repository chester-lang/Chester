package chester.repl

import cats.free.Free
import cats.free.Free.liftF

enum CLIOp[A] {
  case ReadLine(info: TerminalInfo) extends CLIOp[ReadLineResult]
  case GetHistory extends CLIOp[Seq[String]]
  case Close extends CLIOp[Unit]
}

case class TerminalInit(historyFile: Option[String])

type CLI[A] = Free[CLIOp, A]

object CLI {
  def readLine(info: TerminalInfo): CLI[ReadLineResult] = liftF(CLIOp.ReadLine(info))

  def getHistory: CLI[Seq[String]] = liftF(CLIOp.GetHistory)

  def close: CLI[Unit] = liftF(CLIOp.Close)
}

trait CLIRunner {
  // note that run must be effectively the last line of main()
  def exec(init: TerminalInit, cli: CLI[?]): Unit
}

trait CLIRunnerIO extends CLIRunner {
  trait Handler {
    def apply[T](x: CLIOp[T]): T
  }

  def apply(init: TerminalInit): Handler

  override def exec(init: TerminalInit, cli: CLI[?]): Unit = ???
}

trait CLIRunnerMonad[F[_]] extends CLIRunner {
  trait Handler {
    def apply[T](x: CLIOp[T]): F[T]
  }

  def apply(init: TerminalInit): Handler

  def spawn[T](x: F[T]): Unit

  override def exec(init: TerminalInit, cli: CLI[?]): Unit = ???
}
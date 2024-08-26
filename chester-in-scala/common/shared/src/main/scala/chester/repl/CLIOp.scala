package chester.repl

import cats.free.*
import cats.free.Free.*
import cats._

@deprecated
enum CLIOp[A] {
  case ReadLine(info: TerminalInfo) extends CLIOp[ReadLineResult]
  case GetHistory extends CLIOp[Seq[String]]
  case Close extends CLIOp[Unit]
}

case class TerminalInit(historyFile: Option[String])

object TerminalInit {
  val Default: TerminalInit = TerminalInit(None)
}

@deprecated
type CLI[A] = Free[CLIOp, A]

@deprecated
object CLI {
  def readLine(info: TerminalInfo): CLI[ReadLineResult] = liftF(CLIOp.ReadLine(info))

  def getHistory: CLI[Seq[String]] = liftF(CLIOp.GetHistory)

  def close: CLI[Unit] = liftF(CLIOp.Close)
  
  def pure[A](a: A): CLI[A] = Free.pure(a)
}

@deprecated
trait CLIRunner {
  // note that run must be effectively the last line of main()
  def exec(cli: CLI[?], init: TerminalInit = TerminalInit.Default): Unit
}

@deprecated
trait CLIHandlerBase[F[_]] {
  def apply[T](x: CLIOp[T]): F[T]
}

@deprecated
trait CLIHandler[F[_]] extends CLIHandlerBase[F] {
  def readline(info: TerminalInfo): F[ReadLineResult]

  def getHistory: F[Seq[String]]

  def close: F[Unit]

  override def apply[T](x: CLIOp[T]): F[T] = x match {
    case CLIOp.ReadLine(info) => readline(info)
    case CLIOp.GetHistory => getHistory
    case CLIOp.Close => close
  }
}

@deprecated
type CLIHandlerImpure = CLIHandler[Id]


@deprecated
trait CLIRunnerMonad[F[_]](implicit evidence: Monad[F]) extends CLIRunner {

  def apply(init: TerminalInit): CLIHandlerBase[F]

  def spawn[T](x: => F[T]): Unit

  override final def exec(cli: CLI[?], init: TerminalInit): Unit = {
    val handler = apply(init)

    def impureCompiler: CLIOp ~> F = new(CLIOp ~> F) {
      override def apply[A](fa: CLIOp[A]): F[A] = handler(fa)
    }

    spawn {
      cli.foldMap(impureCompiler)
    }
  }
}

@deprecated
trait CLIRunnerImpure extends CLIRunnerMonad[Id] {
  override def spawn[T](x: => Id[T]): Unit = {
    x
    ()
  }
}

package chester.io

import cats.Id
import typings.node.processMod

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global

implicit object DefaultTryable extends Tryable[Future] {
  @inline
  inline def doTry[T](IO: Future[T]): Future[Try[T]] = IO.map(Try(_)).recover(e => Try(throw e))
}

implicit object DefaultIO extends IO[Future] {
  @inline
  inline def println(x: String): Future[Unit] = Future.successful(Predef.println(x))
}

implicit object DefaultSpawn extends Spawn[Future] {
  @inline
  inline def spawn(x: => Future[Unit]): Unit = x.recover{e =>
    e.printStackTrace()
    processMod.^.exit(1)
  }
}
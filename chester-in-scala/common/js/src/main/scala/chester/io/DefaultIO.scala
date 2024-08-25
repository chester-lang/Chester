package chester.io

import cats.Id

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

implicit class DefaultTryable(implicit executor: ExecutionContext) extends Tryable[Future] {
  @inline
  inline def doTry[T](IO: Future[T]): Future[Try[T]] = IO.map(Try(_)).recover(e => Try(throw e))
}

implicit class DefaultIO(implicit executor: ExecutionContext) extends IO[Future] {
  @inline
  inline def println(x: String): Future[Unit] = Future.successful(println(x))
}

package chester.utils.io

import cats.*
import cats.free.*
import cats.instances.future.*
import typings.node.*
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.MakeDirectoryOptions
import typings.std.global.fetch

import java.io.IOException
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.JavaScriptException
import scala.scalajs.js.Thenable.Implicits.*
import scala.scalajs.js.typedarray.Uint8Array
import scala.util.Try

implicit object DefaultRunner extends Runner[Future] with Spawn[Future] {
  inline override def doTry[T](IO: Future[T]): Future[Try[T]] = IO.transformWith(result => Future.successful(result))

  inline override def spawn(x: => Future[Unit]): Unit = x.recover { e =>
    e.printStackTrace()
    processMod.^.exit(1)
  }

  override inline def pure[A](x: A): Future[A] = Future.successful(x)

  override inline def flatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] = fa.flatMap(f)

  override inline def map[A, B](fa: Future[A])(f: A => B): Future[B] = fa.map(f)

  override def tailRecM[A, B](a: A)(f: A => Future[Either[A, B]]): Future[B] = f(a).flatMap {
    case Left(a1) => tailRecM(a1)(f)
    case Right(b) => Future.successful(b)
  }
}

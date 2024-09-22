package chester.utils.io

import cats.*
import cats.free.*
import cats.instances.future.*
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.MakeDirectoryOptions
import typings.node.*
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

implicit object DefaultRunner extends Runner[Future] {
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

implicit object DefaultIO extends IO[Future] {
  type Path = String

  def pathOps = PathOpsString

  inline override def println(x: String): Future[Unit] = Future.successful(Predef.println(x))

  inline override def readString(path: String): Future[String] = fsPromisesMod.readFile(path, BufferEncoding.utf8)

  inline override def writeString(path: String, content: String, append: Boolean = false): Future[Unit] = {
    if (append) {
      fsPromisesMod.appendFile(path, content)
    } else {
      fsPromisesMod.writeFile(path, content)
    }
  }

  inline override def removeWhenExists(path: String): Future[Boolean] =
    fsPromisesMod.unlink(path).map(_ => true).recover { case e: js.JavaScriptException => false }

  inline override def getHomeDir: Future[String] = Future.successful(osMod.homedir())

  inline override def exists(path: String): Future[Boolean] = Future.successful(fsMod.existsSync(path))

  inline override def createDirRecursiveIfNotExists(path: String): Future[Unit] =
    fsPromisesMod.mkdir(path, MakeDirectoryOptions().setRecursive(true)).map(_ => ())

  inline override def downloadToFile(url: String, path: String): Future[Unit] = for {
    fetched <- fetch(url).toFuture
    read <- if fetched.ok then
      fetched.arrayBuffer().toFuture
    else
      Future.failed(new IOException(s"Failed to fetch $url"))
    _ <- fsPromisesMod.writeFile(path, new Uint8Array(read))
  } yield ()

  inline override def chmodExecutable(path: String): Future[Unit] = fsPromisesMod.chmod(path, "755")

  inline override def getAbsolutePath(path: String): Future[String] = Future.successful(pathMod.resolve(path))

}

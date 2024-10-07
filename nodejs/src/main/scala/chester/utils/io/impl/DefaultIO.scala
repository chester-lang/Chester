package chester.utils.io.impl

import chester.utils.io.*
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.MakeDirectoryOptions
import typings.node.{fsMod, fsPromisesMod, osMod, pathMod}
import scala.scalajs.js.Thenable.Implicits.*
import java.io.IOException
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.typedarray.Uint8Array
import scala.concurrent.ExecutionContext.Implicits.global
import typings.std.global.fetch

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
  
  def bytesToJS(bytes: Array[Byte]): Uint8Array = Uint8Array.of(bytes.map(_.toShort)*)

  inline override def write(path: String, content: Array[Byte]): Future[Unit] = fsPromisesMod.writeFile(path, bytesToJS)

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

package chester.io

import cats.{Id, Monad}
import chester.repl.TerminalInit
import chester.utils.io.{FileDownloader, PathOps}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}
import scala.annotation.tailrec
import scala.util.Try

implicit object DefaultRunner extends Runner[Id] {
  override inline def pure[A](x: A): A = x

  override inline def flatMap[A, B](fa: A)(f: A => B): B = f(fa)

  override inline def map[A, B](fa: A)(f: A => B): B = f(fa)

  @tailrec
  override def tailRecM[A, B](a: A)(f: A => Id[Either[A, B]]): Id[B] = f(a) match {
    case Left(a1) => tailRecM(a1)(f)
    case Right(b) => b
  }

  override inline def spawn(x: => Unit): Unit = x

  override inline def doTry[T](IO: Id[T]): Try[T] = Try(IO)
}

implicit object DefaultIO extends IO[Id] {
  type Path = java.nio.file.Path

  override inline def pathOps = new PathOps[Path] {
    def of(path: String): Path = Paths.get(path)

    def join(p1: Path, p2: Path): Path = p1.resolve(p2)

    def asString(p: Path): String = p.toString
  }

  override inline def println(x: String): Unit = Predef.println(x)

  override inline def readString(path: Path): String = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)

  override inline def writeString(path: Path, content: String, append: Boolean = false): Unit = {
    val options = if (append) {
      Array(StandardOpenOption.CREATE, StandardOpenOption.APPEND)
    } else {
      Array(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }
    val outputStream = Files.newOutputStream(path, options: _*)
    try {
      outputStream.write(content.getBytes(StandardCharsets.UTF_8))
    } finally {
      outputStream.close()
    }
  }

  override inline def removeWhenExists(path: Path): Boolean = {
    try {
      Files.delete(path)
      true
    } catch {
      case _: java.nio.file.NoSuchFileException => false
    }
  }

  override inline def getHomeDir: Path = java.nio.file.Paths.get(System.getProperty("user.home"))

  override inline def exists(path: Path): Boolean = Files.exists(path)

  override inline def createDirRecursiveIfNotExists(path: Path): Unit = {
    if (!Files.exists(path)) {
      Files.createDirectories(path)
    }
  }

  override inline def downloadToFile(url: String, path: Path): Unit =
    FileDownloader.downloadFile(url, path)

  override inline def chmodExecutable(path: Path): Unit = {
    val perms = Files.getPosixFilePermissions(path)
    perms.add(java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE)
    perms.add(java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE)
    perms.add(java.nio.file.attribute.PosixFilePermission.OTHERS_EXECUTE)
    Files.setPosixFilePermissions(path, perms)
  }

  override inline def getAbsolutePath(path: Path): Path = path.toAbsolutePath
}

package chester.utils.io

import chester.io.{FileDownloader, PathOps}
import effekt.effects.Exc
import effekt.{Control, Handler}

import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter, IOException}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}
import java.net.URL
import java.nio.file.{Files, Path, StandardCopyOption}
import java.io.IOException

@deprecated
trait NioOps extends FileOpsEff1 {
  type P = java.nio.file.Path

  def pathOps: PathOps[P] = new PathOps[P] {
    def of(path: String): P = Paths.get(path)

    def join(p1: P, p2: P): P = p1.resolve(p2)

    def asString(p: P): String = p.toString
  }
}

@deprecated
case class NioHandler[R]() extends Handler[R] with NioOps {
  def errorFilter(e: Throwable) = e.isInstanceOf[IOException]

  def read(path: P) = use { k =>
    val content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
    k(content)
  }

  def write(path: P, content: Array[Byte], append: Boolean) = use { k =>
    val options = if (append) {
      Array(StandardOpenOption.CREATE, StandardOpenOption.APPEND)
    } else {
      Array(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }
    val outputStream = Files.newOutputStream(path, options: _*)
    try {
      outputStream.write(content)
    } finally {
      outputStream.close()
    }
    k(())
  }

  def removeWhenExists(path: P) = use { k =>
    val result = try {
      Files.delete(path)
      true
    } catch {
      case _: java.nio.file.NoSuchFileException => false
    }
    k(result)
  }

  def getHomeDir = use { k =>
    k(Paths.get(System.getProperty("user.home")))
  }

  def exists(path: P) = use { k =>
    k(Files.exists(path))
  }

  def createDirIfNotExists(path: P) = use { k =>
    if (!Files.exists(path)) {
      Files.createDirectories(path)
    }
    k(())
  }

  def downloadToFile(url: String, path: P) = use { k =>
    FileDownloader.downloadFile(url, path)
    k(())
  }
  
  def chmodExecutable(path: P) = use { k =>
    val perms = Files.getPosixFilePermissions(path)
    perms.add(java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE)
    perms.add(java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE)
    perms.add(java.nio.file.attribute.PosixFilePermission.OTHERS_EXECUTE)
    Files.setPosixFilePermissions(path, perms)
    k(())
  }
  
  def getAbsolutePath(path: P) = use { k =>
    k(path.toAbsolutePath)
  }
}

@deprecated
inline def nioHandler[R](inline prog: FileOpsEff ?=> Control[R]): Control[R] = NioHandler[R]().handle(x => prog(using x))

@deprecated
inline def nioExecute[R](inline prog: FileOpsEff ?=> Control[R]): R = nioHandler[R](prog).run()
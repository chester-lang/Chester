package chester.utils.io

import effekt.effects.Exc
import effekt.{Control, Handler}

import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter, IOException}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}

trait NioOps extends FileOpsEff {
  type P = java.nio.file.Path

  def pathOps: PathOps[P] = new PathOps[P] {
    def of(path: String): P = Paths.get(path)

    def join(p1: P, p2: P): P = p1.resolve(p2)

    def asString(p: P): String = p.toString
  }
}

case class NioHandler[R]() extends Handler[R] with NioOps {
  def errorFilter(e: Throwable) = e.isInstanceOf[IOException]

  def read(path: P) = use { k =>
    val content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
    k(content)
  }

  def write(path: P, content: String) = use { k =>
    val writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    try {
      writer.write(content)
    } finally {
      writer.close()
    }
    k(())
  }

  def append(path: P, content: String) = use { k =>
    val writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)
    try {
      writer.write(content)
    } finally {
      writer.close()
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

}

inline def nioHandler[R](inline prog: FileOpsEff ?=> Control[R]): Control[R] = NioHandler[R]().handle(x => prog(using x))

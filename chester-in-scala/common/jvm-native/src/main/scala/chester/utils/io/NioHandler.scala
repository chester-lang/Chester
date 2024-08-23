package chester.utils.io

import effekt.effects.Exc
import effekt.{Control, Handler}

import java.io.IOException

trait NioOps extends FileOpsEff {
  type P = java.nio.file.Path

  def pathOps: PathOps[P] = new PathOps[P] {
    def of(path: String): P = java.nio.file.Paths.get(path)

    def join(p1: P, p2: P): P = p1.resolve(p2)

    def asString(p: P): String = p.toString
  }
}

case class NioHandler[R]() extends Handler[R] with NioOps {
  def read(path: P) = use { k =>
    val content = java.nio.file.Files.readString(path)
    k(content)
  }

  def write(path: P, content: String) = use { k =>
    java.nio.file.Files.writeString(path, content, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.TRUNCATE_EXISTING)
    k(())
  }

  def append(path: P, content: String) = use { k =>
    java.nio.file.Files.writeString(path, content, java.nio.file.StandardOpenOption.APPEND)
    k(())
  }

  def removeWhenExists(path: P) = use { k =>
    val result = try {
      java.nio.file.Files.delete(path)
      true
    } catch {
      case _: java.nio.file.NoSuchFileException => false
    }
    k(result)
  }

  def getHomeDir = use { k =>
    k(java.nio.file.Paths.get(System.getProperty("user.home")))
  }
  
  def exists(path: P) = use { k =>
    k(java.nio.file.Files.exists(path))
  }

}

inline def nioHandler[R](inline prog: FileOpsEff ?=> Control[R]): Control[R] = NioHandler[R]().handle(x => prog(using x)) 
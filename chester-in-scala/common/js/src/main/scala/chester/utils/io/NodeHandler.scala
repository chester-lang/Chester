package chester.utils.io

import effekt.{Control, Handler}
import typings.node.anon.EncodingFlag
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod
import typings.node.osMod

case class NodeHandlerSync[R]() extends Handler[R] with FileOpsEff {
  type P = String

  def pathOps = PathOpsString

  def read(path: P) = use { k =>
    val content = fsMod.readFileSync(path, EncodingFlag(BufferEncoding.utf8))
    k(content)
  }

  def write(path: P, content: String) = use { k =>
    fsMod.writeFileSync(path, content)
    k(())
  }

  def append(path: P, content: String) = use { k =>
    fsMod.appendFileSync(path, content)
    k(())
  }

  def remove(path: P) = use { k =>
    fsMod.unlinkSync(path)
    k(())
  }

  def getHomeDir = use { k =>
    k(osMod.homedir())
  }
}

inline def nodeHandlerSync[R](inline prog: FileOpsEff ?=> Control[R]): Control[R] = NodeHandlerSync[R]().handle(x => prog(using x))
package chester.utils.io

import effekt.{Control, Handler}
import typings.node.anon.EncodingFlag
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod
import typings.node.osMod

import scala.scalajs.js
import scala.scalajs.js.JavaScriptException
import scala.scalajs.js.typedarray.{Int8Array, Uint8Array}

case class NodeHandlerSync[R]() extends Handler[R] with FileOpsEff {
  type P = String
  def errorFilter(e: Throwable) = e.isInstanceOf[JavaScriptException]

  def pathOps = PathOpsString

  def read(path: P) = use { k =>
    val content = fsMod.readFileSync(path, EncodingFlag(BufferEncoding.utf8))
    k(content)
  }

  def write(path: P, content: Array[Byte], append: Boolean) = use { k =>
    val result = Uint8Array.from(js.Array(content.map(x=>x.toShort)*))
    if(append) {
      fsMod.appendFileSync(path, result)
    } else {
      fsMod.writeFileSync(path, result)
    }
    k(())
  }

  def removeWhenExists(path: P) = use { k =>
    if (fsMod.existsSync(path)) {
      fsMod.unlinkSync(path)
      k(true)
    } else {
      k(false)
    }
  }

  def getHomeDir = use { k =>
    k(osMod.homedir())
  }

  def exists(path: P) = use { k =>
    k(fsMod.existsSync(path))
  }
  def createDirIfNotExists(path: P) = use { k =>
    if (!fsMod.existsSync(path)) {
      fsMod.mkdirSync(path)
    }
    k(())
  }
}

inline def nodeHandlerSync[R](inline prog: FileOpsEff ?=> Control[R]): Control[R] = NodeHandlerSync[R]().handle(x => prog(using x))
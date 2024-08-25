package chester.up

import chester.utils.io
import chester.utils.io.NodeFileOpsFree

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

def spawnUpdate(): Unit = {
  val future = io.nodeExecute {
    update(using NodeFileOpsFree)
  }
}

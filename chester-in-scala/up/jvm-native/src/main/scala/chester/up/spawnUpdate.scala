package chester.up

import chester.utils.io

def spawnUpdate(): Unit = {
  io.nioExecute {
    update
  }
}

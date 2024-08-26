package chester.repl

import chester.io._
import chester.repl.REPLEngine

def spawnREPLEngine[F[_]]()(using runner: Runner[F],terminal: Terminal[F]): Unit = {
  Runner.spawn {
    val repl = new REPLEngine[F]
    repl.startF
  }
}

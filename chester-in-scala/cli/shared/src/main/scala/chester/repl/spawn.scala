package chester.repl

import chester.io.*

inline private def spawnREPLEngine0[F[_]]()(using inline runner: Runner[F], inline terminal: Terminal[F]): Unit = {
  Runner.spawn {
    REPLEngine[F]
  }
}

inline def spawnREPLEngine(): Unit = {
  spawnREPLEngine0()
}
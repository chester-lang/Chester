package chester.repl

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

def startREPL(): Unit = REPLEngine(SimpleTerminal).start()
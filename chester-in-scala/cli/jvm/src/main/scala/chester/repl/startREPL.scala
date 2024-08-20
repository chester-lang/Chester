package chester.repl

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.{Await, Future}

def startREPL(): Unit = REPLEngine(JLineCLIRunner).start()
package chester.repl

import com.eed3si9n.ifdef.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.{Await, Future}

@ifndef("readline")
def startREPL(): Unit = {
  val future = REPLEngine(SimpleTerminal).start()
  Await.result(future, Inf)
}
@ifdef("readline")
def startREPL(): Unit = {
  val future = REPLEngine(ReadlineTerminal).start()
  Await.result(future, Inf)
}
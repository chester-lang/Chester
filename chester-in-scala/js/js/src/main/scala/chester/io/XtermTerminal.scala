package chester.io

import chester.repl.{ReadLineResult, TerminalInfo, TerminalInit}

import scala.concurrent.{Future, Promise}
import typings.xtermXterm.mod

private class InXterm(terminal: mod.Terminal, init: TerminalInit) extends InTerminal[Future] {
  inline override def writeln(line: fansi.Str): Future[Unit] = {
    val promise = Promise[Unit]()
    terminal.writeln(line.render, () => {
      promise.success(())
      ()
    })
    promise.future
  }

  inline override def readline(info: TerminalInfo): Future[ReadLineResult] = ???

  inline override def getHistory: Future[Seq[String]] = ???
  
}

case class XtermTerminal(terminal: mod.Terminal) extends Terminal[Future] {
  def runTerminal[T](init: TerminalInit, block: InTerminal[Future] ?=> Future[T]): Future[T] = {
    val future =
      block(using new InXterm(terminal, init))
    future
  }

}

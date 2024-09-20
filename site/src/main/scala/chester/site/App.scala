package chester.site

import chester.io.{DefaultRunner, XtermPty, XtermTerminal}
import chester.repl.{REPLEngine, TerminalInit}
import chester.utils.env.{BrowserEnv, Environment}
import typings.xtermXterm.mod.Terminal
import typings.xtermPty.mod.Slave

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.Thenable.Implicits.*
import scala.scalajs.js.JSConverters.*
import scala.concurrent.ExecutionContext.Implicits.global

@JSExportTopLevel("startRepl")
def startRepl(terminal: Terminal): js.Promise[Unit] = {
  XtermTerminal(terminal).runTerminal(TerminalInit.Default, {
    implicit val env: Environment = BrowserEnv
    REPLEngine
  }).toJSPromise
}

@JSExportTopLevel("startReplPty")
def startReplPty(pty: Slave): js.Promise[Unit] = {
  XtermPty(pty).runTerminal(TerminalInit.Default, {
    implicit val env: Environment = BrowserEnv
    REPLEngine
  }).toJSPromise
}
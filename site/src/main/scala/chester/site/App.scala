package chester.site

import chester.repl.{REPLEngine, TerminalInit}
import chester.utils.env.{BrowserEnv, Environment}
import chester.utils.io.{DefaultRunner, XtermPty, XtermReadline, XtermTerminal}
import typings.xtermPty.mod.Slave
import typings.xtermReadline.mod.Readline
import typings.xtermXterm.mod.Terminal

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.Thenable.Implicits.*
import scala.scalajs.js.annotation.JSExportTopLevel

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

@JSExportTopLevel("startReplReadline")
def startReplReadline(rl: Readline): js.Promise[Unit] = {
  XtermReadline(rl).runTerminal(TerminalInit.Default, {
    implicit val env: Environment = BrowserEnv
    REPLEngine
  }).toJSPromise
}
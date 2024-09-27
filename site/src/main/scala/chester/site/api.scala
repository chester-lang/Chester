package chester.site

import chester.doc.const.LightMode
import chester.parser.{FileNameAndContent, Parser}
import chester.repl.REPLEngine
import chester.tyck.{ExprTycker, TyckResult}
import chester.utils.doc.*
import chester.utils.doc.ColorfulToHtml.colorfulToHtml
import chester.utils.env.{BrowserEnv, Environment}
import chester.utils.io.*
import chester.utils.term.*
import typings.xtermPty.mod.Slave
import typings.xtermReadline.mod.Readline
import typings.xtermXterm.mod.Terminal

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
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


def runFileFuture(content: String, lightMode: Boolean): Future[String] = {
  implicit val options: PrettierOptions = PrettierOptions.Default.updated(LightMode, lightMode)
  Parser.parseTopLevel(FileNameAndContent("playground.chester", content)) match {
    case Right(parsedBlock) =>
      ExprTycker.synthesize(parsedBlock) match {
        case TyckResult.Success(result, status, warnings) =>
          Future.successful(colorfulToHtml(ColorfulPrettyPrinter.render(result.wellTyped)))
        case TyckResult.Failure(errors, warnings, state, result) =>
          Future.successful(s"Failed to type check file: $errors")
      }
    case Left(error) =>
      Future.successful(error.message)
  }
}

@JSExportTopLevel("runFile")
def runFile(content: String, lightMode: Boolean): js.Promise[String] = {
  runFileFuture(content, lightMode).toJSPromise
}
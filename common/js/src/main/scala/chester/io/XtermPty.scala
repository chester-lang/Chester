package chester.io

import chester.repl.TerminalInit
import typings.xtermPty.mod.Slave

import java.nio.charset.StandardCharsets
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.Thenable.Implicits.*
import scala.scalajs.js.JSConverters.*
import scala.concurrent.ExecutionContext.Implicits.global

final class InXtermPty(pty: Slave) extends InTerminalNoHistory[Future] {
  inline override def writeln(line: fansi.Str): Future[Unit] = {
    pty.write(line.render + "\n")
    Future.successful(())
  }
  override inline def readALine(prompt: fansi.Str): Future[String] = {
    val promise = Promise[String]()
    pty.write(prompt.render)
    val onReadable = pty.onReadable.asInstanceOf[js.Function1[js.Function0[Unit], Unit]]
    onReadable(() => {
      promise.success(new String(pty.read().map(_.toByte).toArray, StandardCharsets.UTF_8))
    })
    promise.future
  }
}

case class XtermPty(pty: Slave) extends Terminal[Future] {
  def runTerminal[T](init: TerminalInit, block: InTerminal[Future] ?=> Future[T]): Future[T] = {
    block(using InXtermPty(pty))
  }
}

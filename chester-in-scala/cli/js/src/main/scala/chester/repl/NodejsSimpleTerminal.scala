package chester.repl

import chester.parser.InputStatus.*
import chester.parser.ParserEngine

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import typings.node.processMod
import typings.node.readlinePromisesMod
import scala.scalajs.js.Thenable.Implicits._

class NodejsSimpleTerminal extends Terminal {
  private var history: Vector[String] = Vector()
  private var currentInputs: String = ""

  private val rl = readlinePromisesMod.createInterface(processMod.^.stdin.asInstanceOf, processMod.^.stdout.asInstanceOf)

  private def nodeReadLine(prompt: String): Future[String] = rl.question(prompt)

  def readLine(info: TerminalInfo): Future[ReadLineResult] = {
    def loop(prompt: String): Future[ReadLineResult] = {
      nodeReadLine(prompt).flatMap { line =>
        if (line == null) {
          Future.successful(EndOfFile)
        } else if (line.forall(_.isWhitespace)) {
          loop(prompt) // continue reading
        } else {
          currentInputs = if (currentInputs.isEmpty) line else s"$currentInputs\n$line"
          ParserEngine.checkInputStatus(currentInputs) match {
            case Complete =>
              history = history :+ currentInputs
              val result = LineRead(currentInputs)
              currentInputs = ""
              Future.successful(result)
            case Incomplete =>
              loop(info.continuationPrompt) // continue with continuation prompt
            case Error(message) =>
              currentInputs = ""
              Future.successful(StatusError(message))
          }
        }
      }
    }

    loop(info.defaultPrompt)
  }

  def close(): Unit = {
    rl.close()
  }

  def getHistory: Seq[String] = history
}

object NodejsSimpleTerminal extends TerminalFactory {
  def apply(): NodejsSimpleTerminal = new NodejsSimpleTerminal()
}

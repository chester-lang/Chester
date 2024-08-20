package chester.repl

import cats.Id
import chester.parser.InputStatus.*
import chester.parser.ParserEngine

import scala.concurrent.Future
import scala.io.StdIn

class SimpleTerminal {
  private var history: Vector[String] = Vector()
  private var currentInputs: String = ""

  def readLine(info: TerminalInfo): ReadLineResult = {
    var prompt = info.defaultPrompt
    var continue = true
    var result: ReadLineResult = EndOfFile

    while (continue) {
      print(prompt)
      val line = scala.io.StdIn.readLine()
      if (line == null) {
        continue = false
        result = EndOfFile
      } else if (line.forall(_.isWhitespace)) {
      } else {
        if (currentInputs.isEmpty) {
          currentInputs = line
        } else {
          currentInputs += "\n" + line
        }
        val status = chester.parser.ParserEngine.checkInputStatus(currentInputs)

        status match {
          case Complete =>
            history = history :+ currentInputs
            result = LineRead(currentInputs)
            continue = false
          case Incomplete =>
            prompt = info.continuationPrompt
          case Error(message) =>
            result = StatusError(message)
            continue = false
        }
        if (!continue) currentInputs = ""
      }
    }
    result
  }

  def close(): Unit = {}

  def getHistory: Seq[String] = history
}

object SimpleTerminal extends TerminalFactory {
  def apply(): Terminal = {
    val t = new SimpleTerminal()
    new Terminal {
      override def readLine(info: TerminalInfo): Future[ReadLineResult] = Future.successful(t.readLine(info))
      override def close(): Unit = t.close()
      override def getHistory: Seq[String] = t.getHistory
    }
  }
}

object SimpleCLIRunner extends CLIRunnerImpure {
  def apply(init: TerminalInit): CLIHandlerImpure = {
    val t = new SimpleTerminal()
    new CLIHandlerImpure {
      override def readline(info: TerminalInfo): ReadLineResult = t.readLine(info)
      override def getHistory: Seq[String] = t.getHistory
      override def close: Unit = t.close()
    }
  }
}

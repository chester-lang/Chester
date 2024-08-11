package chester.repl

import chester.parser.InputStatus._

import scala.io.StdIn
import chester.parser.{ParseError, ParserEngine}
import chester.syntax.concrete.ParsedExpr

class SimpleTerminal(info: TerminalInfo) extends Terminal {
  private var history: Vector[String] = Vector()
  private var currentInputs: String = ""

  def readLine(): ReadLineResult = {
    var prompt = info.defaultPrompt
    var continue = true
    var result: ReadLineResult = EndOfFile

    while (continue) {
      print(prompt)
      val line = StdIn.readLine()
      if (line == null) {
        continue = false
        result = EndOfFile
      } else if (line.forall(_.isWhitespace)) {
      } else {
        currentInputs += line
        val status = ParserEngine.checkInputStatus(currentInputs)

        status match {
          case Complete =>
            history = history :+ line
            result = LineRead(line)
            continue = false
          case Incomplete =>
            prompt = info.continuationPrompt // Switch to continuation prompt
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
  def apply(info: TerminalInfo): SimpleTerminal = new SimpleTerminal(info)
}
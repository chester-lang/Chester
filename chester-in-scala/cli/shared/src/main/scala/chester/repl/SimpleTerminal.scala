package chester.repl

import chester.parser.InputStatus._

import scala.io.StdIn
import chester.parser.{ParseError, ParserEngine}
import chester.syntax.concrete.ParsedExpr

class SimpleTerminal(info: TerminalInfo) extends Terminal {
  private var history: Vector[String] = Vector()

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
      } else {
        history = history :+ line
        val status = ParserEngine.checkInputStatus(line)

        status match {
          case Complete =>
            ParserEngine.parseInput(history, line) match {
              case Right(expr) =>
                println(s"Parsed expression: $expr")
                history = Vector()
                result = LineRead(line)
                continue = false
              case Left(error) =>
                println(s"Parse error: ${error.message}")
                history = Vector()
                result = LineRead("")
                continue = false
            }
          case Incomplete =>
            prompt = info.continuationPrompt // Switch to continuation prompt
          case Error(message) =>
            println(s"Input error: $message")
            history = Vector()
            result = LineRead("") // Returning empty to indicate error
            continue = false
        }
      }
    }
    result
  }

  def close(): Unit = {}

  def getHistory: Seq[String] = history
}

object SimpleTerminal extends TerminalFactory{
  def apply(info: TerminalInfo): SimpleTerminal = new SimpleTerminal(info)
}
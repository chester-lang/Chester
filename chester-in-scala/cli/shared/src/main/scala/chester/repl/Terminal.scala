package chester.repl

trait TerminalFactory {
  def apply(): Terminal
}

trait Terminal extends AutoCloseable  {
  def readLine(info: TerminalInfo): ReadLineResult

  def close(): Unit

  def getHistory: Seq[String]
}

import chester.parser.InputStatus

trait TerminalInfo {
  def checkInputStatus(input: String): InputStatus

  def defaultPrompt: String

  def continuationPrompt: String
}
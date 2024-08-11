package chester.repl

trait TerminalFactory {
  def apply(info: TerminalInfo): Terminal
}

trait Terminal {
  def readLine(): ReadLineResult

  def close(): Unit

  def getHistory: Seq[String]
}

import chester.parser.InputStatus

trait TerminalInfo {
  def checkInputStatus(input: String): InputStatus

  def defaultPrompt: String

  def continuationPrompt: String
}
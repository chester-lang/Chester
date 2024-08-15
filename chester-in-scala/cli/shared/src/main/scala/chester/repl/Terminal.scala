package chester.repl

import chester.parser.InputStatus
import scala.concurrent.Future

trait TerminalFactory {
  def apply(): Terminal
}

trait Terminal extends AutoCloseable  {
  def readLine(info: TerminalInfo): Future[ReadLineResult]

  def close(): Unit

  def getHistory: Seq[String]
}

trait TerminalInfo {
  def checkInputStatus(input: String): InputStatus

  def defaultPrompt: String

  def continuationPrompt: String
}
package chester.repl

import chester.parser.InputStatus
import scala.concurrent.Future

trait TerminalInfo {
  def checkInputStatus(input: String): InputStatus

  def defaultPrompt: fansi.Str

  def continuationPrompt: fansi.Str
}
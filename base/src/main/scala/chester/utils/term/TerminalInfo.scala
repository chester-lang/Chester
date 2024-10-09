package chester.utils.term

import chester.utils.io.*
import chester.utils.term.*

import scala.concurrent.Future

trait TerminalInfo {
  def checkInputStatus(input: String): InputStatus

  def defaultPrompt: fansi.Str

  def continuationPrompt: fansi.Str
}

package chester.utils.term

import chester.utils.io.*
import chester.utils.term.*

case class TerminalInit(historyFile: Option[String]) extends AnyVal

object TerminalInit {
  val Default: TerminalInit = TerminalInit(None)
}

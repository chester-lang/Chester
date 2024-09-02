package chester.repl

case class TerminalInit(historyFile: Option[String])

object TerminalInit {
  val Default: TerminalInit = TerminalInit(None)
}

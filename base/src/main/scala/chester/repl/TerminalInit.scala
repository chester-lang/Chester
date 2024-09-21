package chester.repl

case class TerminalInit(historyFile: Option[String]) extends AnyVal

object TerminalInit {
  val Default: TerminalInit = TerminalInit(None)
}

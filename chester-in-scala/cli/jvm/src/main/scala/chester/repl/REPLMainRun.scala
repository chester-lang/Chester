package chester.repl

object REPLMainRun {
  def apply(): Unit = REPLMain.runREPL(JLineInputHandler())
}

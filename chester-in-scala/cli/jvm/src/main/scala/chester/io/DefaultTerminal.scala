package chester.io

import cats.Id
import chester.repl.{JLineTerminal, ReadLineResult, TerminalInfo, TerminalInit}

implicit object DefaultTerminal extends Terminal[Id] {
  def runTerminal[T](init: TerminalInit, block: InTerminal[Id] ?=> T): T = {
    val terminal = new JLineTerminal(init)
    try {
      block(using new InTerminal[Id] {
        inline def writeln(line: fansi.Str): Unit = println(line.render)

        inline def readline(info: TerminalInfo): ReadLineResult = terminal.readLine(info)

        inline def getHistory: Seq[String] = terminal.getHistory
      })
    } finally {
      terminal.close()
    }
  }
}
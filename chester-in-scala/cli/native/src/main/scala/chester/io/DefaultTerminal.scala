package chester.io

import cats.Id
import chester.repl.{ReadLineResult, SimpleTerminal, TerminalInfo, TerminalInit}

implicit object DefaultTerminal extends Terminal[Id] {
  inline def runTerminal[T](init: TerminalInit, block: InTerminal[Id] ?=> T): T = {
    val terminal = new SimpleTerminal(init)
    block(using terminal)
  }
}
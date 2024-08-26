package chester.repl

import cats.Id
import chester.io.*
import fansi.Str

class SimpleTerminal(init: TerminalInit) extends AbstractInTerminal[Id] {
  override inline def initHistory = Vector()

  override inline def readALine(prompt: Str): String = {
    print(prompt.render)
    scala.io.StdIn.readLine()
  }

  override inline def writeln(line: Str): Unit = println(line.render)
}

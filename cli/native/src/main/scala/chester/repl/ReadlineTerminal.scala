package chester.repl

import cats.Id
import chester.io._
import chester.parser.InputStatus.*
import io.github.edadma.readline.facade.*
import io.github.edadma.readline.facade
/*
Currently this code is not working due to the following error:
[error] Undefined symbols for architecture arm64:
[error]   "_append_history", referenced from:
[error]       __SM41io.github.edadma.readline.facade.package$D14append_historyiL16java.lang.StringiEO in 10b0a649.ll.o
[error] ld: symbol(s) not found for architecture arm64
[error] clang: error: linker command failed with exit code 1 (use -v to see invocation)
*/
class ReadlineTerminal(init: TerminalInit) extends InTerminal[Id] {
  private var history: Vector[String] = Vector()
  private val historyFile = init.historyFile
  private var historyExists: Int = 0

  if (historyFile.isDefined) {
    readHistory()
  }

  // Method for reading history
  private def readHistory(): Unit = {
    historyFile.foreach { file =>
      historyExists = read_history(file)
    }
  }

  // Method for writing history
  private def writeHistory(): Unit = {
    historyFile.foreach { file =>
      if (historyExists == 0) {
        append_history(1, file)
      } else {
        historyExists = 0
        write_history(file)
      }
    }
  }

  override def writeln(line: fansi.Str): Unit = {
    println(line.render)
  }
  
  override def readline(info: TerminalInfo): ReadLineResult = {
    var prompt = info.defaultPrompt
    var continue = true
    var result: ReadLineResult = EndOfFile
    var currentInputs: String = ""

    while (continue) {
      val line = facade.readline(prompt.render)

      if (line == null) {
        continue = false
        result = EndOfFile
      } else if (line.forall(_.isWhitespace)) {
        // Ignore empty lines
      } else {
        if (currentInputs.isEmpty) {
          currentInputs = line
        } else {
          currentInputs += "\n" + line
        }

        add_history(line) // GNU Readline can only handle one line entry in history
        val status = info.checkInputStatus(currentInputs)

        status match {
          case Complete =>
            val prev = history_get(history_base + history_length - 1)
            if (prev == null || prev != line) {
              writeHistory() // Manage history based on existence
            }
            history = history :+ currentInputs
            result = LineRead(currentInputs)
            continue = false
          case Incomplete =>
            prompt = info.continuationPrompt
          case Error(message) =>
            result = StatusError(message)
            continue = false
        }

        if (!continue) {
          currentInputs = ""
        }
      }
    }
    result
  }

  def close(): Unit = {
    writeHistory()
  }

  override def getHistory: Seq[String] = history
}

object ReadlineTerminal extends Terminal[Id] {
  override def runTerminal[T](init: TerminalInit, block: InTerminal[Id] ?=> T): T = {
    val terminal = new ReadlineTerminal(init)
    try {
      block(using terminal)
    } finally {
      terminal.close()
    }
  }
}
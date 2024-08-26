package chester.repl

import chester.parser.InputStatus.*
import io.github.edadma.readline.facade.*

import scala.concurrent.Future

class ReadlineTerminal  {
  private var history: Vector[String] = Vector()
  private val homeDir = System.getProperty("user.home")
  private val HISTORY_FILE = s"$homeDir/.my_readline_history"
  private var historyExists: Int = 0

  readHistory()

  // Method for reading history
  private def readHistory(): Unit = {
    if (false) { // Logic to be activated in the future
      historyExists = read_history(HISTORY_FILE)
    }
  }

  // Method for writing history
  private def writeHistory(): Unit = {
    if (false) { // Logic to be activated in the future
      if (historyExists == 0) {
        append_history(1, HISTORY_FILE)
      } else {
        historyExists = 0
        write_history(HISTORY_FILE)
      }
    }
  }

  def readLine(info: TerminalInfo): ReadLineResult = {
    var prompt = info.defaultPrompt
    var continue = true
    var result: ReadLineResult = EndOfFile
    var currentInputs: String = ""

    while (continue) {
      val line = readline(prompt.render)

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

        add_history(line) //GNU Readline can only handle one line entry in history
        val status = info.checkInputStatus(currentInputs)

        status match {
          case Complete =>
            val prev = history_get(history_base + history_length - 1)
            if (prev == null || prev != currentInputs) {
              history = history :+ currentInputs
              writeHistory() // Manage history based on existence
            }
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
    (result)
  }

  def close(): Unit = {
    // Call to the stub method for writing history
    writeHistory()
  }

  def getHistory: Seq[String] = history
}

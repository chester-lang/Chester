package chester.repl
import org.jline.reader.{LineReader, LineReaderBuilder, UserInterruptException, EndOfFileException}
import org.jline.reader.impl.history.DefaultHistory
import org.jline.terminal.TerminalBuilder

class JLineInputHandler extends REPLInputHandler {
  private val terminal = TerminalBuilder.terminal()
  private val history = new DefaultHistory()
  private val reader: LineReader = LineReaderBuilder.builder()
    .terminal(terminal)
    .history(history)
    .build()

  def readLine(prompt: String): ReadLineResult = {
    try {
      LineRead(reader.readLine(prompt))
    } catch {
      case _: UserInterruptException => UserInterrupted
      case _: EndOfFileException => EndOfFile
    }
  }

  def addHistory(line: String): Unit = history.add(line)

  def close(): Unit = terminal.close()
}

object JLineInputHandler {
  def apply(): JLineInputHandler = new JLineInputHandler()
}
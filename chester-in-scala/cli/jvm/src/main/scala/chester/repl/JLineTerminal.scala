package chester.repl

import chester.parser.InputStatus.*
import chester.parser.{ParseError, ParserEngine}
import org.jline.reader.impl.DefaultParser
import org.jline.reader.impl.history.DefaultHistory
import org.jline.reader.*
import org.jline.terminal.TerminalBuilder

class JLineTerminal(info: TerminalInfo) extends Terminal {
  private val terminal = TerminalBuilder.terminal()
  private val history = new DefaultHistory()

  private val jlineParser: Parser = new DefaultParser() {
    override def parse(line: String, cursor: Int, context: Parser.ParseContext): ParsedLine = {
      info.checkInputStatus(line) match {
        case Complete => super.parse(line, cursor, context) // Proceed normally
        case Incomplete => throw new EOFError(-1, cursor, "Incomplete input, missing matching bracket")
        case Error(message) => throw new EOFError(-1, cursor, message)
      }
    }
  }

  private val reader: LineReader = LineReaderBuilder.builder()
    .terminal(terminal)
    .history(history)
    .parser(jlineParser)
    .build()

  def readLine(): ReadLineResult = {
    var prompt = info.defaultPrompt
    var continue = true
    var result: ReadLineResult = EndOfFile

    while (continue) {
      try {
        val line = reader.readLine(prompt)
        history.add(line)

        val status = ParserEngine.checkInputStatus(line)

        status match {
          case Complete =>
            val historySeq = (0 until history.size()).map(history.get(_).toString)
            ParserEngine.parseInput(historySeq, line) match {
              case Right(expr) =>
                println(s"Parsed expression: $expr")
                history.purge()
                result = LineRead(line)
                continue = false
              case Left(error) =>
                println(s"Parse error: ${error.message}")
                history.purge()
                result = LineRead("")
                continue = false
            }
          case Incomplete =>
            prompt = info.continuationPrompt // Switch to continuation prompt
          case Error(message) =>
            println(s"Input error: $message")
            history.purge()
            result = LineRead("") // Returning empty to indicate error
            continue = false
        }
      } catch {
        case _: EOFError =>
          result = LineRead("")
          continue = false
        case _: UserInterruptException =>
          result = UserInterrupted
          continue = false
        case _: EndOfFileException =>
          result = EndOfFile
          continue = false
      }
    }
    result
  }

  def close(): Unit = terminal.close()

  def getHistory: Seq[String] = (0 until history.size()).map(history.get(_).toString)
}

object JLineTerminal extends TerminalFactory{
  def apply(info: TerminalInfo): JLineTerminal = new JLineTerminal(info)
}

package chester.repl


sealed trait ReadLineResult
case class LineRead(line: String) extends ReadLineResult
case object UserInterrupted extends ReadLineResult
case object EndOfFile extends ReadLineResult

trait REPLInputHandler {
  def readLine(prompt: String): ReadLineResult
  def addHistory(line: String): Unit
  def close(): Unit
}

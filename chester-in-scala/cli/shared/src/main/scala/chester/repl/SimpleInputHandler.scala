package chester.repl
import scala.io.StdIn

class SimpleInputHandler extends REPLInputHandler {
  private var history: List[String] = List()

  def readLine(prompt: String): ReadLineResult = {
    print(prompt)
    val line = StdIn.readLine()
    if (line != null) {
      LineRead(line)
    } else {
      EndOfFile
    }
  }

  def addHistory(line: String): Unit = {
    history = history :+ line
  }

  def close(): Unit = {
    // No resources to close in this simple implementation
  }
}

object SimpleInputHandler {
  def apply(): SimpleInputHandler = new SimpleInputHandler()
}
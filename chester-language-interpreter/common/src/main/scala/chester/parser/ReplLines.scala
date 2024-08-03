package chester.parser

class ReplLines {
  private var history: Vector[String] = Vector()
  private var pendingLines: Vector[String] = Vector()

  def addLine(line: String): Unit = {
    pendingLines = pendingLines :+ line
  }

  def getPendingLines: Vector[String] = pendingLines

  def clearPendingLines(): Unit = {
    history = history ++ pendingLines
    pendingLines = Vector()
  }

  def getHistory: Vector[String] = history
}

package chester.utils

case class LineAndColumn(val line: Int, val column: Int)

case class StringPos(val string: String) {
  import java.lang.Character.{isHighSurrogate, isLowSurrogate}

  lazy val unicodeLength: Int = string.codePointCount(0, string.length)

  private lazy val lineBreaks: Array[Int] = {
    val lb = collection.mutable.ArrayBuffer[Int]()
    val n = string.length
    var i = 0
    while (i < n) {
      if (string(i) == '\n' || string(i) == '\r') {
        lb += i
        if (string(i) == '\r' && i + 1 < n && string(i + 1) == '\n') {
          i += 1
        }
      }
      i += 1
    }
    lb += n // End of string marker
    lb.toArray
  }

  def charIndexToUnicodeIndex(charIndex: Int): Int = {
    string.codePointCount(0, charIndex)
  }

  def unicodeIndexToCharIndex(unicodeIndex: Int): Int = {
    string.offsetByCodePoints(0, unicodeIndex)
  }

  def charIndexToCharLineAndColumn(charIndex: Int): LineAndColumn = {
    val line = lineBreaks.indexWhere(_ >= charIndex)
    val lineStart = if (line == 0) 0 else lineBreaks(line - 1) + 1
    LineAndColumn(line, charIndex - lineStart)
  }

  def charIndexToUnicodeLineAndColumn(charIndex: Int): LineAndColumn = {
    val lineAndColumn = charIndexToCharLineAndColumn(charIndex)
    val lineStartCharIndex = if (lineAndColumn.line == 0) 0 else lineBreaks(lineAndColumn.line - 1) + 1
    val lineStartUnicodeIndex = charIndexToUnicodeIndex(lineStartCharIndex)
    LineAndColumn(lineAndColumn.line, charIndexToUnicodeIndex(charIndex) - lineStartUnicodeIndex)
  }
}

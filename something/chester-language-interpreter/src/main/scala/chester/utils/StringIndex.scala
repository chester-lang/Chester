package chester.utils

case class LineAndColumn(val line: Int, val column: Int)

case class StringIndex(val string: String) {
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
    if (charIndex > 0 && Character.isSurrogatePair(string(charIndex - 1), string(charIndex))) {
      // Pointing to the second character of a surrogate pair, should not increment the index
      string.codePointCount(0, charIndex) - 1
    } else {
      string.codePointCount(0, charIndex)
    }
  }


  def unicodeIndexToCharIndex(unicodeIndex: Int): Int = {
    string.offsetByCodePoints(0, unicodeIndex)
  }

  def charIndexToCharLineAndColumn(charIndex: Int): LineAndColumn = {
    var line = 0
    var column = 0
    var i = 0

    while (i < charIndex) {
      if (string(i) == '\n') {
        line += 1
        column = 0
      } else {
        if (Character.isHighSurrogate(string(i)) && i + 1 < string.length && Character.isLowSurrogate(string(i + 1))) {
          // Only increment the column once for the high surrogate
          if (i < charIndex - 1) {
            column += 1 // Only increment if both parts of the surrogate pair are before the charIndex
          }
          i += 1 // Move past the low surrogate
        } else {
          column += 1
        }
      }
      i += 1
    }

    LineAndColumn(line, column)
  }

  def charIndexToUnicodeLineAndColumn(charIndex: Int): LineAndColumn = {
    // Initial conditions assuming no breaks
    var line = 0
    var lineStart = 0

    // Iterate through line breaks to determine the actual line and the start of the line
    for (breakIndex <- lineBreaks.indices) {
      if (lineBreaks(breakIndex) < charIndex) {
        line = breakIndex + 1
        lineStart = lineBreaks(breakIndex) + 1
      }
    }

    // Calculate Unicode column from the start of the current line to the current index
    val column = if (charIndex >= lineStart) {
      string.codePointCount(lineStart, charIndex)
    } else {
      0
    }

    LineAndColumn(line, column)
  }

}

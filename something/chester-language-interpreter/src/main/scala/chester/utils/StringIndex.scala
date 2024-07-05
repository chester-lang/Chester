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
    if (lineBreaks.isEmpty) {
      // Handling single line strings
      return LineAndColumn(0, charIndex)
    }

    // Finding the last line break before the current character index or the beginning if none found
    val line = if (charIndex > 0 && lineBreaks.exists(_ < charIndex)) {
      lineBreaks.lastIndexWhere(_ < charIndex) + 1
    } else {
      0
    }

    // Compute the start of the current line
    val lineStart = if (line > 0) lineBreaks(line - 1) + 1 else 0

    // Adjust column for characters following a newline character
    val column = charIndex - lineStart

    LineAndColumn(line, column)
  }

  def charIndexToUnicodeLineAndColumn(charIndex: Int): LineAndColumn = {
    // Find the last line break before the character index, or 0 if none
    val line = lineBreaks.lastIndexWhere(_ <= charIndex)
    val lineStart = if (line >= 0) lineBreaks(line) else 0

    // Calculate the Unicode index for the start of the line and the current character index
    val unicodeLineStartIndex = charIndexToUnicodeIndex(lineStart)
    val unicodeIndex = charIndexToUnicodeIndex(charIndex)

    // Calculate the column as the difference in Unicode indexes
    val column = unicodeIndex - unicodeLineStartIndex

    // Adjust line number if necessary based on specific rules (if any)
    LineAndColumn(line, column)
  }
}

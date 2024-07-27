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
      if (string(i) == '\n') {
        lb += i
      }
      i += 1
    }
    lb += n // End of string marker
    lb.toArray
  }

  def charIndexToUnicodeIndex(charIndex: Int): Int = {
    if(charIndex > string.length) throw new IllegalArgumentException("Index out of bounds")
    if(charIndex == string.length) return unicodeLength
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
        column += 1
      }
      i += 1
    }

    LineAndColumn(line, column)
  }

  def charIndexToUnicodeLineAndColumn(charIndex: Int): LineAndColumn = {
    var line = 0
    var column = 0
    var i = 0

    while (i < charIndex) {
      if (string(i) == '\n') {
        line += 1
        column = 0 // Reset column to 0 for the start of a new line
        i += 1 // Increment to skip the newline character
        if (i >= charIndex) { // If we're beyond charIndex after skipping, stop further processing
          return LineAndColumn(line, column)
        }
      } else {
        // Check if the current character is part of a surrogate pair
        if (Character.isHighSurrogate(string(i)) && i + 1 < string.length && Character.isLowSurrogate(string(i + 1))) {
          if (i + 1 < charIndex) { // Only increment column if both parts of the surrogate are within range
            column += 1
          }
          i += 1 // Skip the low surrogate
        } else {
          column += 1 // Increment column for normal characters
        }
        i += 1
      }
    }

    LineAndColumn(line, column)
  }

}

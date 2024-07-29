package chester.utils

case class LineAndColumn(val line: Int, val column: Int)

case class StringIndex(val stringList: LazyList[String]) {

  import java.lang.Character.{isHighSurrogate, isLowSurrogate}

  private def stringIterator: Iterator[Char] = stringList.iterator.flatten

  lazy val unicodeLength: Int = stringIterator.foldLeft(0)((count, char) => count + (if (isHighSurrogate(char)) 0 else 1))

  private lazy val lineBreaks: LazyList[Int] = {
    def lineBreakIndices(it: Iterator[Char], idx: Int = 0, acc: LazyList[Int] = LazyList.empty): LazyList[Int] = {
      if (it.hasNext) {
        val char = it.next()
        if (char == '\n') lineBreakIndices(it, idx + 1, acc :+ idx)
        else lineBreakIndices(it, idx + 1, acc)
      } else {
        acc :+ idx
      }
    }
    lineBreakIndices(stringIterator)
  }

  def charIndexToUnicodeIndex(charIndex: Int): Int = {
    var index = 0
    var unicodeIndex = 0
    val it = stringIterator
    while (it.hasNext && index < charIndex) {
      val char = it.next()
      if (index < charIndex && isHighSurrogate(char)) {
        val nextChar = if (it.hasNext) it.next() else '\u0000'
        if (isLowSurrogate(nextChar)) {
          if (index + 1 < charIndex) {
            unicodeIndex += 1
            index += 2
          } else {
            index += 1
          }
        } else {
          unicodeIndex += 1
          index += 1
        }
      } else {
        unicodeIndex += 1
        index += 1
      }
    }
    unicodeIndex
  }

  def unicodeIndexToCharIndex(unicodeIndex: Int): Int = {
    var index = 0
    var unicodeCount = 0
    val it = stringIterator
    while (it.hasNext && unicodeCount < unicodeIndex) {
      val char = it.next()
      if (isHighSurrogate(char)) {
        val nextChar = if (it.hasNext) it.next() else '\u0000'
        if (isLowSurrogate(nextChar)) {
          unicodeCount += 1
          index += 2
        } else {
          unicodeCount += 1
          index += 1
        }
      } else {
        unicodeCount += 1
        index += 1
      }
    }
    index
  }

  def charIndexToCharLineAndColumn(charIndex: Int): LineAndColumn = {
    var line = 0
    var column = 0
    var index = 0
    val it = stringIterator

    while (it.hasNext && index < charIndex) {
      val char = it.next()
      if (char == '\n') {
        line += 1
        column = 0
      } else {
        column += 1
      }
      index += 1
    }

    LineAndColumn(line, column)
  }

  def charIndexToUnicodeLineAndColumn(charIndex: Int): LineAndColumn = {
    if (charIndex < 0) throw new IllegalArgumentException("Index out of bounds (negative)")

    var line = 0
    var column = 0
    var index = 0
    val it = stringIterator

    while (it.hasNext && index < charIndex) {
      val char = it.next()
      if (char == '\n') {
        line += 1
        column = 0 // Reset column to 0 for the start of a new line
      } else {
        if (isHighSurrogate(char)) {
          val nextChar = if (it.hasNext) it.next() else '\u0000'
          if (isLowSurrogate(nextChar)) {
            if (index + 1 < charIndex) {
              column += 1
              index += 1 // Skip the low surrogate
            }
          } else {
            column += 1
          }
        } else {
          column += 1
        }
      }
      index += 1
    }

    LineAndColumn(line, column)
  }

}


object StringIndex {
  def apply(s: String): StringIndex = StringIndex(LazyList(s))
}
package chester.utils

import fastparse.ParserInput
import upickle.default.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.upickle.given

case class LineAndColumn(
    val line: Int :| Positive0,
    val column: Int :| Positive0
)
case class LineAndColumnWithUTF16(line: Int :| Positive0, column: WithUTF16)

case class StringIndex(val stringList: LazyList[String]) {

  import java.lang.Character.{isHighSurrogate, isLowSurrogate}

  private def stringIterator: Iterator[Char] = stringList.iterator.flatten

  lazy val unicodeLength: Int = stringIterator.foldLeft(0)((count, char) =>
    count + (if (isHighSurrogate(char)) 0 else 1)
  )

  lazy val charLength: Int = stringIterator.foldLeft(0)((count, _) => count + 1)

  private lazy val lineBreaks: LazyList[Int] = {
    def lineBreakIndices(s: LazyList[Char], idx: Int = 0): LazyList[Int] =
      s match {
        case LazyList()    => LazyList(idx)
        case '\n' #:: tail => idx #:: lineBreakIndices(tail, idx + 1)
        case _ #:: tail    => lineBreakIndices(tail, idx + 1)
      }

    lineBreakIndices(stringList.flatten)
  }

  def charIndexToWithUTF16(charIndex: Int :| Positive0): WithUTF16 =
    WithUTF16(charIndexToUnicodeIndex(charIndex), charIndex)

  /** 0 <= charIndex <= charLength */
  def charIndexToUnicodeIndex(charIndex: Int :| Positive0): Int :| Positive0 = {
    var index = 0
    var unicodeIndex = 0
    val it = stringIterator
    while (index < charIndex) {
      if (it.hasNext) {
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
      } else {
        throw new IllegalArgumentException(
          s"Index out of bounds (exceeds string length) $charIndex"
        )
      }
    }
    unicodeIndex.refineUnsafe
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

  /** 0 <= charIndex <= charLength */
  def charIndexToCharLineAndColumn(charIndex: Int): LineAndColumn = {
    var line = 0
    var column = 0
    var index = 0
    val it = stringIterator

    while (index < charIndex) {
      if (it.hasNext) {
        val char = it.next()
        if (char == '\n') {
          line += 1
          column = 0
        } else {
          column += 1
        }
        index += 1
      } else {
        throw new IllegalArgumentException(
          s"Index out of bounds (exceeds string length) $charIndex"
        )
      }
    }

    LineAndColumn(line.refineUnsafe, column.refineUnsafe)
  }

  def charIndexToLineAndColumnWithUTF16(
      charIndex: Int
  ): LineAndColumnWithUTF16 = {
    val utf16 = charIndexToCharLineAndColumn(charIndex)
    val unicode = charIndexToUnicodeLineAndColumn(charIndex)
    assert(utf16.line == unicode.line)
    LineAndColumnWithUTF16(
      unicode.line,
      WithUTF16(unicode.column, utf16.column)
    )
  }

  /** 0 <= charIndex <= charLength */
  def charIndexToUnicodeLineAndColumn(charIndex: Int): LineAndColumn = {
    if (charIndex < 0)
      throw new IllegalArgumentException(
        s"Index out of bounds (negative) $charIndex"
      )

    var line = 0
    var column = 0
    var index = 0
    val it = stringIterator

    while (index < charIndex) {
      if (it.hasNext) {
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
      } else {
        throw new IllegalArgumentException(
          s"Index out of bounds (exceeds string length) $charIndex"
        )
      }
    }

    LineAndColumn(line.refineUnsafe, column.refineUnsafe)
  }

}

object StringIndex {
  def apply(s: String): StringIndex = StringIndex(LazyList(s))

  def apply(iterator: Iterator[String]): StringIndex = {
    val lazyList = LazyList.from(iterator)
    StringIndex(lazyList)
  }

  def apply(parserInput: ParserInput): StringIndex = {
    StringIndex(parserInputToLazyList(parserInput))
  }
}

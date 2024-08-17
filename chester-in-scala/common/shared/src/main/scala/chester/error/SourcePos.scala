package chester.error

import chester.utils.{encodeString, parserInputToLazyList}
import fastparse.ParserInput

import scala.annotation.tailrec

case class Pos(index: Int, line: Int, column: Int)

object Pos {
  lazy val Zero = Pos(0, 0, 0)
}

case class RangeInFile(start: Pos, end: Pos)

type FileContent = String | LazyList[String] | ParserInput

object FileContent {
  @tailrec
  def convertToString(fileContent: FileContent): String = fileContent match {
    case s: String => s
    case ll: LazyList[String] => ll.mkString
    case pi: ParserInput => convertToString(parserInputToLazyList(pi))
  }
}

class SourcePos(val fileName: String, val fileContent: FileContent, val range: RangeInFile) {


  // Method to extract all lines within the range with line numbers
  def getLinesInRange: Vector[(Int, String)] = {
    val startLine = range.start.line
    val endLine = range.end.line
    val contentString = FileContent.convertToString(fileContent)
    val lines = contentString.split('\n').toVector

    // Assert that the start and end lines are within valid bounds
    assert(startLine >= 0 && endLine < lines.length, s"Invalid line range: startLine=$startLine, endLine=$endLine, totalLines=${lines.length}")

    // Slice the lines and keep their line numbers
    lines.zipWithIndex
      .slice(startLine, endLine + 1)
      .map { case (line, index) => (index + 1, line) } // Line numbers are 1-based
  }

  // Method to format the lines within the range for display
  def formatLinesInRange: String = {
    getLinesInRange.map { case (lineNumber, line) => s"$lineNumber: $line" }.mkString("\n")
  }

  def combine(other: SourcePos): SourcePos = {
    if (fileName != other.fileName) {
      throw new IllegalArgumentException("Cannot combine source positions from different files")
    }
    /*
    if (fileContent != other.fileContent) {
      throw new IllegalArgumentException("Cannot combine source positions from different files")
    }
    */
    require(range.start.index <= other.range.start.index)
    val newRange = RangeInFile(range.start, other.range.end)
    SourcePos(fileName, fileContent, newRange)
  }

  override def toString: String = s"SourcePos(\"${encodeString(fileName)}\",${range})"
}

object SourcePos {
  def apply(fileName: String, fileContent: FileContent, range: RangeInFile): SourcePos = {
    new SourcePos(fileName, fileContent, range)
  }
}

extension (pos: Option[SourcePos]) {
  def combineInOption(other: Option[SourcePos]): Option[SourcePos] = {
    (pos, other) match {
      case (None, None) => None
      case (Some(p), None) => Some(p)
      case (None, Some(p)) => Some(p)
      case (Some(p1), Some(p2)) => Some(p1.combine(p2))
    }
  }
}
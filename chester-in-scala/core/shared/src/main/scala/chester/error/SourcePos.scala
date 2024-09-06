package chester.error

import chester.parser.SourceOffset
import chester.utils.{encodeString, parserInputToLazyList}
import fastparse.ParserInput

import scala.annotation.tailrec
import upickle.default.*

case class Pos(index: Int, line: Int, column: Int)derives ReadWriter

object Pos {
  lazy val Zero = Pos(0, 0, 0)
}

case class RangeInFile(start: Pos, end: Pos)derives ReadWriter

type AcceptedString = String | LazyList[String] | ParserInput

case class FileContent(content: AcceptedString, lineOffset: Int, indexOffset: Int)

object FileContent {
  @tailrec
  private def convertToString0(fileContent: String | LazyList[String] | ParserInput): String = fileContent match {
    case s: String => s
    case ll: LazyList[String] => ll.mkString
    case pi: ParserInput => convertToString0(parserInputToLazyList(pi))
  }

  def convertToString(fileContent: FileContent): String = convertToString0(fileContent.content)
}

case class SourcePos(source: SourceOffset, range: RangeInFile)derives ReadWriter {
  lazy val fileContent = source.readContent.toOption.map(FileContent(_, source.linesOffset, source.posOffset))
  val fileName = source.fileName

  // Method to extract all lines within the range with line numbers
  def getLinesInRange: Option[Vector[(Int, String)]] = fileContent map { fileContent =>
    val startLine = range.start.line - fileContent.lineOffset
    val endLine = range.end.line - fileContent.lineOffset
    val contentString = FileContent.convertToString(fileContent)
    val lines = contentString.split('\n').toVector

    // Assert that the start and end lines are within valid bounds
    assert(startLine >= 0 && endLine < lines.length, s"Invalid line range: startLine=$startLine, endLine=$endLine, totalLines=${lines.length}")

    // Slice the lines and keep their line numbers
    lines.zipWithIndex
      .slice(startLine, endLine + 1)
      .map { case (line, index) => (fileContent.lineOffset + index + 1, line) } // Line numbers are 1-based
  }

  def combine(other: SourcePos): SourcePos = {
    if (fileName != other.fileName) {
      throw new IllegalArgumentException("Cannot combine source positions from different files")
    }
    require(range.start.index <= other.range.start.index)
    require(source == other.source)
    val newRange = RangeInFile(range.start, other.range.end)
    SourcePos(source, newRange)
  }

  override def toString: String = s"SourcePos(\"${encodeString(fileName)}\",${range})"
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
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
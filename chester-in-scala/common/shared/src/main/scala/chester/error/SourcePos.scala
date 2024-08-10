package chester.error

import chester.utils.encodeString

case class Pos(index: Int, line: Int, column: Int)

object Pos {
  lazy val Zero = Pos(0, 0, 0)
}

case class RangeInFile(start: Pos, end: Pos)

case class SourcePos(fileName: String, range: RangeInFile) {
  def combine(other: SourcePos): SourcePos = {
    if (fileName != other.fileName) {
      throw new IllegalArgumentException("Cannot combine source positions from different files")
    }
    require(range.start.index <= other.range.start.index)
    val newRange = RangeInFile(range.start, other.range.end)
    SourcePos(fileName, newRange)
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
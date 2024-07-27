package chester.error

case class Pos(index: Int, line: Int, column:Int)

object Pos {
  lazy val Zero = Pos(0, 0, 0)
}

case class RangeInFile(start: Pos, end: Pos)

case class SourcePos(fileName: String, range: RangeInFile) {
  def combine(other: SourcePos): SourcePos = {
    if (fileName != other.fileName) {
      throw new IllegalArgumentException("Cannot combine source positions from different files")
    }
    assert(range.start.index <= other.range.start.index)
    val newRange = RangeInFile(range.start, other.range.end)
    SourcePos(fileName, newRange)
  }
}

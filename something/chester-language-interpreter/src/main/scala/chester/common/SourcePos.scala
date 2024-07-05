package chester.common

case class Pos(index: Int, line: Int, column:Int)

case class RangeInFile(start: Pos, end: Pos)

case class SourcePos(file: String, range: RangeInFile)

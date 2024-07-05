package chester.common

case class PositionInFile(char: Int, line: Int, columnL:Int)

case class RangeInFile(start: PositionInFile, end: PositionInFile)

case class SourceLocation(file: String, range: RangeInFile)

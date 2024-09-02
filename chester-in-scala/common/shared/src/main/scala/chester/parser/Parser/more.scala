package chester.parser.Parser

import chester.parser.{FilePath, ParseError}
import chester.syntax.concrete.ParsedExpr

@deprecated("Use parseExpr with ParserSource instead")
def parseFile(fileName: String): Either[ParseError, ParsedExpr] = {
  parseExpr(FilePath(fileName))
}

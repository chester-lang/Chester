package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.{Pos, RangeInFile, SourcePos}
import chester.utils.StringIndex
import chester.utils.parse.*

import java.lang.Character.{isDigit, isLetter}

case class ParserInternal(fileName: String)(implicit ctx: P[?]) {
  val ASCIIAllowedSymbols = "-=_+\\|;:,.<>/?`~!@$%^&*"
  val ReservedSymbols = "#()[]{}'\""
  def identifierFirst(x: Character) = isLetter(x)

  def identifierRest(x: Character) = isLetter(x) || isDigit(x)

  def identifier: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)


  private def begin: P[Int] = Index
  private def end: P[Int] = Index


  val index = StringIndex(ctx.input.slice(0, ctx.input.length))

  private def loc(begin: Int, end: Int): Option[SourcePos] = {
    val start = index.charIndexToUnicodeLineAndColumn(begin)
    val endPos = index.charIndexToUnicodeLineAndColumn(end)
    Some(SourcePos(fileName, RangeInFile(Pos(begin, start.line, start.column), Pos(end, endPos.line, endPos.column))))
  }


}

object Parser {
  
}
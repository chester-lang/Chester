package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.SourcePos
import chester.utils.StringIndex
import chester.utils.parse.*

import java.lang.Character.{isDigit, isLetter}

case class ParserState(
                        fileName: String = "")

case class Parser(state: ParserState) {
  val ASCIIAllowedSymbols = "-=_+\\|;:,.<>/?`~!@$%^&*"
  val ReservedSymbols = "#()[]{}'\""
  def identifierFirst(x: Character) = isLetter(x)

  def identifierRest(x: Character) = isLetter(x) || isDigit(x)

  def identifier[$: P]: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)


  private def begin[$: P]: P[Int] = Index
  private def end[$: P]: P[Int] = Index


  private def loc(begin: Int, end: Int)(implicit ctx: P[?]): Option[SourcePos] = {
    val index = StringIndex(ctx.input.slice(0, ctx.input.length))
    ???
  }


}

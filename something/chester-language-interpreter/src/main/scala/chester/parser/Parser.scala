package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.utils.parse._

import java.lang.Character.{isDigit, isLetter}

case class ParserState(
                        fileName: String = "")

case class Parser(state: ParserState) {
  val ASCIIAllowedSymbols = "-=_+\\|;:,.<>/?`~!@$%^&*"
  val ReservedSymbols = "#()[]{}'\""
  def identifierFirst(x: Character) = isLetter(x)

  def identifierRest(x: Character) = isLetter(x) || isDigit(x)

  def identifier[$: P]: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)
}

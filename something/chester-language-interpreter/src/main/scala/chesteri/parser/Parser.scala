package chesteri.parser;

import fastparse.*
import NoWhitespace.*
import java.lang.Character.{isDigit, isLetter}

case class ParserState(
                        fileName: String = "")

case class Parser(state: ParserState) {
}

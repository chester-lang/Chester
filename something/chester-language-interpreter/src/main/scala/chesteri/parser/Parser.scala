package chesteri.parser;

import fastparse.*
import NoWhitespace.*
import java.lang.Character.{isDigit, isLetter}

case class ParserState(
                        fileName: String = "",
                        infixs: InfixDefitions = InfixDefitions())

case class PrecedenceGroup(
                            name: String,
                            higherThan: Vector[PrecedenceGroup] = Vector(),
                            lowerThan: Vector[PrecedenceGroup] = Vector(),
                            associativity: Associativity = Associativity.None,
                          )

enum Associativity {
  case None
  case Left
  case Right
}

case class Prefix(name: String)

case class Postfix(name: String)

case class Infix(name: String, group: PrecedenceGroup)

case class InfixDefitions(prefixes: Vector[Prefix] = Vector(),
                          postfixes: Vector[Postfix] = Vector(),
                          infixes: Vector[Infix] = Vector())

case class Parser(state: ParserState) {
}

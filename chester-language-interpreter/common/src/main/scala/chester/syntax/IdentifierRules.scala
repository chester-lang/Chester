package chester.syntax

import java.lang.Character.{isDigit, isLetter}
import chester.utils.parse.Character

object IdentifierRules {
  val AllowedOperatorSymbols = "=-+\\|<>/?`~!@$%^&*".toSet.map(_.toInt)
  val AllowedWordingSymbols = "_".toSet.map(_.toInt)
  val AllowedMiddleWordingSymbols = "-".toSet.map(_.toInt)
  val ReservedSymbols = ".;:,#()[]{}'\""

  def isOperatorSymbol(x: Character) = AllowedOperatorSymbols.contains(x)

  def isWordingSymbol(x: Character) = AllowedWordingSymbols.contains(x)

  def isMiddleWordingSymbol(x: Character) = AllowedMiddleWordingSymbols.contains(x)

  def identifierFirst(x: Character) = isLetter(x) || isWordingSymbol(x)

  def identifierRest(x: Character) = identifierFirst(x) || isDigit(x) || isMiddleWordingSymbol(x)

  def operatorIdentifierFirst(x: Character) = isOperatorSymbol(x)

  def operatorIdentifierRest(x: Character) = isOperatorSymbol(x) || isWordingSymbol(x)

  def strIsOperator(s: String): Boolean = {
    val codepoints = s.codePoints().toArray.toVector
    if(codepoints.isEmpty) return false
    if(!isOperatorSymbol(codepoints.head)) return false
    codepoints.forall(isOperatorSymbol)
  }

}

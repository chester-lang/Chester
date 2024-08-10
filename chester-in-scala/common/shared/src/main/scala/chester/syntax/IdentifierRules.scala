package chester.syntax

import chester.utils.codePointIsEmoji

import java.lang.Character.{isDigit, isLetter}
import chester.utils.parse.Character
import chester.utils.getCodePoints

object IdentifierRules {
  val AllowedOperatorSymbols = ".:=-+\\|<>/?`~!@$%^&*".toSet.map(_.toInt)
  val AllowedWordingSymbols = "_".toSet.map(_.toInt)
  val AllowedMiddleWordingSymbols = "-".toSet.map(_.toInt)
  val ReservedSymbols = ";,#()[]{}'\""

  def isEmoji(codePoint: Int): Boolean = {
    codePointIsEmoji(codePoint)
  }

  def isWording(x: Character) = isLetter(x) || isEmoji(x)

  def isOperatorSymbol(x: Character) = AllowedOperatorSymbols.contains(x)

  def isWordingSymbol(x: Character) = AllowedWordingSymbols.contains(x)

  def isMiddleWordingSymbol(x: Character) = AllowedMiddleWordingSymbols.contains(x)

  def identifierFirst(x: Character) = isWording(x) || isWordingSymbol(x)

  def identifierRest(x: Character) = identifierFirst(x) || isDigit(x) || isMiddleWordingSymbol(x)

  def operatorIdentifierFirst(x: Character) = isOperatorSymbol(x)

  def operatorIdentifierRest(x: Character) = isOperatorSymbol(x) || isWordingSymbol(x)

  def strIsOperator(s: String): Boolean = {
    val codepoints = s.getCodePoints
    if (codepoints.isEmpty) return false
    if (!isOperatorSymbol(codepoints.head)) return false
    codepoints.forall(isOperatorSymbol)
  }

}

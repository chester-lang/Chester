package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.{Pos, RangeInFile, SourcePos}
import chester.syntax.concrete.{DoubleLiteral, Expr, Identifier, IntegerLiteral}
import chester.utils.StringIndex
import chester.utils.parse.*

import java.lang.Character.{isDigit, isLetter}

case class ParserInternal(fileName: String)(implicit ctx: P[?]) {
  val ASCIIAllowedSymbols = "-=_+\\|;:,.<>/?`~!@$%^&*".toSet.map(_.toInt)
  val ReservedSymbols = "#()[]{}'\""

  def isSymbol(x: Character) = ASCIIAllowedSymbols.contains(x)

  def identifierFirst(x: Character) = isLetter(x) || isSymbol(x)

  def identifierRest(x: Character) = identifierFirst(x) || isDigit(x)

  def id: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)

  def begin: P[Int] = Index

  def end: P[Int] = Index


  val index = StringIndex(ctx.input.slice(0, ctx.input.length))

  private def loc(begin: Int, end: Int): SourcePos = {
    val start = index.charIndexToUnicodeLineAndColumn(begin)
    val endPos = index.charIndexToUnicodeLineAndColumn(end)
    SourcePos(fileName, RangeInFile(Pos(begin, start.line, start.column), Pos(end, endPos.line, endPos.column)))
  }

  extension [T](inline parse0: P[T]) {
    inline def withPos: P[(T, SourcePos)] = (begin ~ parse0 ~ end).map { case (b, x, e) => (x, loc(b, e)) }
  }

  def identifier: P[Expr] = P(id.withPos).map { case (name, pos) => Identifier(Some(pos), name) }

  def signed: P[String] = P(CharIn("+\\-").?.!)

  def hexLiteral: P[String] = P("0x"./ ~ CharsWhileIn("0-9a-fA-F")).!

  def binLiteral: P[String] = P("0b"./ ~ CharsWhileIn("01")).!

  def decLiteral: P[String] = P(CharsWhileIn("0-9")).!

  def expLiteral: P[String] = P(CharsWhileIn("0-9") ~ "." ~ CharsWhileIn("0-9") ~ (CharIn("eE") ~ signed ~ CharsWhileIn("0-9")).?).!

  def integerLiteral: P[Expr] = P(signed ~ (hexLiteral | binLiteral | decLiteral).!).withPos.map {
    case ((sign, value), pos) =>
      val actualValue = if (value.startsWith("0x")) BigInt(sign + value.drop(2), 16)
      else if (value.startsWith("0b")) BigInt(sign + value.drop(2), 2)
      else BigInt(sign + value)
      IntegerLiteral(actualValue, Some(pos))
  }

  def doubleLiteral: P[Expr] = P(signed ~ expLiteral.withPos).map {
    case (sign, (value, pos)) =>
      DoubleLiteral(BigDecimal(sign + value), Some(pos))
  }

  def literal: P[Expr] = P(doubleLiteral | integerLiteral)

  def apply: P[Expr] = P(literal | identifier)

}

object Parser {
  def parseExpression(fileName: String, input: String): Parsed[Expr] = parse(input, ParserInternal(fileName)(_).apply)
}
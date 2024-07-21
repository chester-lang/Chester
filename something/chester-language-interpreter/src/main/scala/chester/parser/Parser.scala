package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.{Pos, RangeInFile, SourcePos}
import chester.syntax.concrete.{Expr, Identifier}
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

  def apply: P[Expr] = identifier // | ...

}

object Parser {
  def parseExpression(fileName: String, input: String): Parsed[Expr] = parse(input, ParserInternal(fileName)(_).apply)
}
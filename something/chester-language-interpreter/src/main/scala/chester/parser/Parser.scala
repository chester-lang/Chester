package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.{Pos, RangeInFile, SourcePos}
import chester.syntax.concrete._
import chester.utils.StringIndex
import chester.utils.parse.*

import java.lang.Character.{isDigit, isLetter}

case class ParserInternal(fileName: String, ignoreLocation: Boolean = false)(implicit ctx: P[?]) {
  val ASCIIAllowedSymbols = "-_+\\|;.<>/?`~!@$%^&*".toSet.map(_.toInt)
  val ReservedSymbols = "=:,#()[]{}'\""

  def comment: P[Unit] = P("//" ~ CharPred(_!='\n').rep)

  def simpleDelimiter: P[Unit] = P(CharsWhileIn(" \t\r\n"))

  def delimiter: P[Unit] = P((simpleDelimiter | comment).rep)

  def maybeSpace: P[Unit] = P(delimiter.?)

  def isSymbol(x: Character) = ASCIIAllowedSymbols.contains(x)

  def identifierFirst(x: Character) = isLetter(x) || isSymbol(x)

  def identifierRest(x: Character) = identifierFirst(x) || isDigit(x)

  def id: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)

  def begin: P[Int] = Index

  def end: P[Int] = Index


  val index = StringIndex(ctx.input.slice(0, ctx.input.length))

  private def loc(begin: Int, end: Int): Option[SourcePos] = {
    if (ignoreLocation) return None
    val start = index.charIndexToUnicodeLineAndColumn(begin)
    val endPos = index.charIndexToUnicodeLineAndColumn(end)
    Some(SourcePos(fileName, RangeInFile(Pos(begin, start.line, start.column), Pos(end, endPos.line, endPos.column))))
  }

  extension [T](inline parse0: P[T]) {
    inline def withPos: P[(T, Option[SourcePos])] = (begin ~ parse0 ~ end).map { case (b, x, e) => (x, loc(b, e)) }
  }

  inline def PwithPos[T](inline parse0: P[T]): P[(T, Option[SourcePos])] = P(parse0.withPos)

  def identifier: P[Identifier] = P(id.withPos).map { case (name, pos) => Identifier(name, pos) }

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
      IntegerLiteral(actualValue, pos)
  }

  def doubleLiteral: P[Expr] = P(signed ~ expLiteral.withPos).map {
    case (sign, (value, pos)) =>
      DoubleLiteral(BigDecimal(sign + value), pos)
  }


  def escapeSequence: P[String] = P("\\" ~ CharIn("rnt\\\"").!).map {
    case "r" => "\r"
    case "n" => "\n"
    case "t" => "\t"
    case "\\" => "\\"
    case "\"" => "\""
  }

  def normalChar: P[String] = P(CharPred(c => c != '\\' && c != '"')).!

  def stringLiteral: P[String] = P("\"" ~/ (normalChar | escapeSequence).rep.map(_.mkString) ~ "\"")

  def heredocLiteral: P[String] = {
    def validateIndentation(str: String): Either[String, String] = {
      val lines = str.split("\n")
      val indentStrings = lines.filter(_.trim.nonEmpty).map(_.takeWhile(_.isWhitespace))

      if (indentStrings.distinct.length > 1) Left("Inconsistent indentation in heredoc string literal")
      else {
        val indentSize = if (indentStrings.nonEmpty) indentStrings.head.length else 0
        val trimmedLines = lines.map(_.drop(indentSize))
        Right(trimmedLines.mkString("\n").stripPrefix("\n").stripSuffix("\n"))
      }
    }

    P("\"\"\"" ~/ (!"\"\"\"".rep ~ AnyChar).rep.!.flatMap { str =>
      validateIndentation(str) match {
        case Right(validStr) => Pass(validStr)
        case Left(errorMsg) => Fail.opaque(errorMsg)
      }
    } ~ "\"\"\"")
  }

  def stringLiteralExpr: P[Expr] = P((stringLiteral | heredocLiteral).withPos).map {
    case (value, pos) => StringLiteral(value, pos)
  }

  def literal: P[Expr] = P(doubleLiteral | integerLiteral | stringLiteralExpr)

  def decoration: P[Identifier] = "#" ~ identifier

  def decorations: P[Vector[Identifier]] = P((decoration ~ delimiter).repX.map(_.toVector))

  def argName: P[Identifier] = identifier

  def argType: P[Expr] = P(maybeSpace ~ ":"./ ~ apply)

  def argExprOrDefault: P[Option[Expr]] = P(maybeSpace ~ "="./ ~ apply).?

  def argumentWithName: P[Arg] = P(decorations.? ~ argName ~ argType.? ~ argExprOrDefault).flatMap {
    case (dex, name, ty, exprOrDefault) if ty.isEmpty && exprOrDefault.isEmpty => Fail.opaque("Either type or default value should be provided")
    case (dec, name, ty, exprOrDefault) => Pass(Arg(dec.getOrElse(Vector.empty), Some(name), ty, exprOrDefault))
  }

  def argumentWithoutName: P[Arg] = P(decorations.? ~ apply).map {
    case (dec, expr) => Arg(dec.getOrElse(Vector.empty), None, None, Some(expr))
  }

  def argument: P[Arg] = maybeSpace ~ P(argumentWithName | argumentWithoutName)

  def comma: P[Unit] = P(maybeSpace ~ "," ~ maybeSpace)

  def telescope: P[Telescope] = PwithPos("(" ~/ argument.rep(sep = comma) ~ comma.? ~ maybeSpace ~ ")").map { (args, pos) =>
    Telescope(args.toVector)
  }

  def typeAnnotation: P[TypeAnnotation] = ???

  def list: P[ListExpr] = PwithPos("[" ~/ apply.rep(sep = comma) ~ comma.? ~ maybeSpace ~ "]").map { (terms, pos) =>
    ListExpr(terms.toVector)
  }

  def apply: P[Expr] = maybeSpace ~ P(telescope | literal | identifier)

}

object Parser {
  def parseExpression(fileName: String, input: String, ignoreLocation: Boolean = false): Parsed[Expr] = parse(input, ParserInternal(fileName, ignoreLocation = ignoreLocation)(_).apply)
}
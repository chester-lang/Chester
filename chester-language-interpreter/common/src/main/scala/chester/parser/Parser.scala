package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.{Pos, RangeInFile, SourcePos}
import chester.syntax.concrete.*
import chester.utils.StringIndex
import chester.utils.parse.*

import java.lang.Character.{isDigit, isLetter}
import java.nio.file.{Files, Paths}
import scala.util._

import chester.syntax.IdentifierRules._

case class ParserInternal(fileName: String, ignoreLocation: Boolean = false, defaultIndexer: Option[StringIndex] = None)(implicit ctx: P[?]) {

  def comment: P[Unit] = P("//" ~ CharPred(_ != '\n').rep)

  def simpleDelimiter: P[Unit] = P(CharsWhileIn(" \t\r\n"))

  def delimiter: P[Unit] = P((simpleDelimiter | comment).rep)

  def maybeSpace: P[Unit] = P(delimiter.?)

  def maybeSimpleSpace: P[Unit] = P(CharsWhileIn(" \t").?)

  def simpleId: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)

  def id: P[String] = operatorId | simpleId

  def operatorId: P[String] = P((CharacterPred(operatorIdentifierFirst).rep(1) ~ CharacterPred(operatorIdentifierRest).rep).!)

  def begin: P[Int] = Index

  def end: P[Int] = Index


  val indexer: StringIndex = defaultIndexer.getOrElse(StringIndex(ctx.input.slice(0, ctx.input.length)))

  private def loc(begin: Int, end: Int): Option[SourcePos] = {
    if (ignoreLocation) return None
    val start = indexer.charIndexToUnicodeLineAndColumn(begin)
    val endPos = indexer.charIndexToUnicodeLineAndColumn(end)
    Some(SourcePos(fileName, RangeInFile(Pos(indexer.charIndexToUnicodeIndex(begin), start.line, start.column), Pos(indexer.charIndexToUnicodeIndex(end), endPos.line, endPos.column))))
  }

  extension [T](inline parse0: P[T]) {
    inline def withPos: P[(T, Option[SourcePos])] = (begin ~ parse0 ~ end).map { case (b, x, e) => (x, loc(b, e)) }

    inline def must(inline message: String = "Expected something"): P[T] = parse0.? flatMap {
      case Some(x) => Pass(x)
      case None => Fail.opaque(message)./
    }

    inline def on(inline condition: Boolean): P[T] = if condition then parse0 else Fail("")
  }

  inline def PwithPos[T](inline parse0: P[T]): P[(T, Option[SourcePos])] = P(parse0.withPos)

  def identifier: P[Identifier] = P(id.withPos).map { case (name, pos) => Identifier(name, pos) }

  def infixIdentifier: P[Identifier] = P(operatorId.withPos).map { case (name, pos) => Identifier(name, pos) }

  def signed: P[String] = P(CharIn("+\\-").?.!)

  def hexLiteral: P[String] = P("0x" ~ CharsWhileIn("0-9a-fA-F").must()).!

  def binLiteral: P[String] = P("0b" ~ CharsWhileIn("01").must()).!

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

  def stringLiteral: P[String] = P("\"" ~ (normalChar | escapeSequence).rep.map(_.mkString) ~ "\"")

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

    P("\"\"\"" ~ (!"\"\"\"".rep ~ AnyChar).rep.!.flatMap { str =>
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

  def simpleAnnotation: P[Identifier] = "@" ~ identifier

  def simpleAnnotations: P[Vector[Identifier]] = P((simpleAnnotation ~ delimiter).repX.map(_.toVector))

  def argName: P[Identifier] = identifier

  def argType: P[Expr] = P(maybeSpace ~ ":" ~ maybeSpace ~ parse(ctx = ParsingContext(dontallowOpSeq = true)))

  def argExprOrDefault: P[Option[Expr]] = P(maybeSpace ~ "=" ~ maybeSpace ~ parse(ctx = ParsingContext(dontallowOpSeq = true))).?

  def argumentWithName: P[Arg] = P(simpleAnnotations.? ~ argName ~ argType.? ~ argExprOrDefault).flatMap {
    case (dex, name, ty, exprOrDefault) if ty.isEmpty && exprOrDefault.isEmpty => Fail.opaque("Either type or default value should be provided")
    case (dec, name, ty, exprOrDefault) => Pass(Arg(dec.getOrElse(Vector.empty), Some(name), ty, exprOrDefault))
  }

  def argumentWithoutName: P[Arg] = P(simpleAnnotations.? ~ maybeSpace ~ parse(ctx = ParsingContext(dontallowOpSeq = true))).map {
    case (dec, expr) => Arg(dec.getOrElse(Vector.empty), None, None, Some(expr))
  }

  def argument: P[Arg] = maybeSpace ~ P(argumentWithName | argumentWithoutName)

  def comma: P[Unit] = P(maybeSpace ~ "," ~ maybeSpace)

  def telescope: P[Telescope] = PwithPos("(" ~ argument.rep(sep = comma) ~ comma.? ~ maybeSpace ~ ")").map { (args, pos) =>
    Telescope(args.toVector, pos)
  }

  def argHasImplicit(arg: Arg): Boolean = arg.decorations.exists(_.name == "implicit")

  def argAddImplicit(arg: Arg): Arg = {
    if (argHasImplicit(arg)) return arg
    val newDecorations = arg.decorations :+ Identifier("implicit")
    arg.copy(decorations = newDecorations)
  }

  def implicitTelescope: P[Telescope] = PwithPos("<" ~ argument.rep(sep = comma) ~ comma.? ~ maybeSpace ~ ">").map { case (args, pos) =>
    Telescope(args.map(argAddImplicit).toVector, pos)
  }

  def typeAnnotation(expr: Expr, p: Option[SourcePos] => Option[SourcePos]): P[TypeAnnotation] = PwithPos(maybeSpace ~ ":" ~ maybeSpace ~ parse).map { case (ty, pos) =>
    TypeAnnotation(expr, ty, p(pos))
  }

  def list: P[ListExpr] = PwithPos("[" ~ parse.rep(sep = comma) ~ comma.? ~ maybeSpace ~ "]").map { (terms, pos) =>
    ListExpr(terms.toVector, pos)
  }

  def annotation: P[(Identifier, Option[Telescope])] = P("@" ~ identifier ~ telescope.?)

  def annotated: P[AnnotatedExpr] = PwithPos(annotation ~ parse).map { case ((annotation, telescope, expr), pos) =>
    AnnotatedExpr(annotation, telescope, expr, pos)
  }

  case class ParsingContext(inOpSeq: Boolean = false, dontallowOpSeq: Boolean = false) {
    def opSeq = !inOpSeq && !dontallowOpSeq

    def blockCall = !inOpSeq
  }

  def calling(implicit ctx: ParsingContext = ParsingContext()): P[Telescope] = P((implicitTelescope | telescope) | (maybeSimpleSpace ~ anonymousBlockLikeFunction.on(ctx.blockCall)).withPos.map { case (block, pos) =>
    Telescope.of(Arg.of(block))(pos)
  })

  def functionCall(function: Expr, p: Option[SourcePos] => Option[SourcePos], ctx: ParsingContext = ParsingContext()): P[FunctionCall] = PwithPos(calling(ctx = ctx)).map { case (telescope, pos) =>
    FunctionCall(function, telescope, p(pos))
  }

  def dotCall(expr: Expr, p: Option[SourcePos] => Option[SourcePos]): P[DotCall] = PwithPos(maybeSpace ~ "." ~ identifier ~ calling.rep.?).map { case ((field, telescope), pos) =>
    DotCall(expr, field, telescope.getOrElse(Seq()).toVector, p(pos))
  }

  def block: P[Expr] = PwithPos("{" ~ (maybeSpace ~ statement ~ maybeSpace ~ ";").rep ~ maybeSpace ~ parse ~ maybeSpace ~ "}").map { case ((heads, tail), pos) =>
    Block(Vector.from(heads), tail, pos)
  }

  def anonymousBlockLikeFunction: P[Expr] = block

  def statement: P[Expr] = parse // TODO

  def opSeq(expr: Expr, p: Option[SourcePos] => Option[SourcePos]): P[BinOpSeq] = PwithPos((maybeSpace ~ parse(ctx = ParsingContext(inOpSeq = true)) ~ maybeSpace).rep(min = 1)).flatMap((exprs, pos) => {
    val xs = (expr +: exprs)
    val exprCouldPrefix = expr match {
      case Identifier(name, _) if strIsOperator(name) => true
      case _ => false
    }
    val looksLikeOtherThings = {
      val start = xs.indexWhere(_ match {
        case Identifier("<", _) => true
        case _ => false
      })
      val end = xs.indexWhere(_ match {
        case Identifier(">", _) => true
        case _ => false
      }, start)
      start >= 0 && end >= 0 && start < end
    }
    if (looksLikeOtherThings) return Fail("Looks like a telescope")
    if (!(xs.exists(_.isInstanceOf[Identifier]))) Fail("Expected identifier") else Pass(BinOpSeq(xs.toVector, p(pos)))
  })

  def objectParse: P[Expr] = PwithPos("{" ~ (maybeSpace ~ identifier ~ maybeSpace ~ "=" ~ maybeSpace ~ parse ~ maybeSpace).rep(sep = comma) ~ comma.? ~ maybeSpace ~ "}").map { (fields, pos) =>
    ObjectExpr(fields.toVector, pos)
  }

  def tailExpr(expr: Expr, getPos: Option[SourcePos] => Option[SourcePos], ctx: ParsingContext = ParsingContext()): P[Expr] = (dotCall(expr, getPos) | typeAnnotation(expr, getPos) | functionCall(expr, getPos, ctx = ctx) | opSeq(expr, getPos).on(ctx.opSeq)).withPos.flatMap({ (expr, pos) => {
    val getPos1 = ((endPos: Option[SourcePos]) => for {
      p0 <- getPos(pos)
      p1 <- endPos
    } yield p0.combine(p1))
    tailExpr(expr, getPos1, ctx = ctx) | Pass(expr)
  }
  })

  def parse(implicit ctx: ParsingContext = ParsingContext()): P[Expr] = PwithPos(objectParse | block | annotated | implicitTelescope | list | telescope | literal | identifier).flatMap { (expr, pos) =>
    val getPos = ((endPos: Option[SourcePos]) => for {
      p0 <- pos
      p1 <- endPos
    } yield p0.combine(p1))
    tailExpr(expr, getPos, ctx = ctx) | Pass(expr)
  }

  def entrance: P[Expr] = P(Start ~ maybeSpace ~ parse ~ maybeSpace ~ End)

}

case class ParseError(message: String, index: Pos)

object Parser {
  def parseFile(fileName: String): Either[ParseError, Expr] = {
    Try(new String(Files.readAllBytes(Paths.get(fileName)))) match {
      case Success(content) =>
        parseContent(fileName, content)
      case Failure(exception) =>
        Left(ParseError(s"Failed to read file: ${exception.getMessage}", Pos.Zero))
    }
  }

  def parseContent(fileName: String, input: String, ignoreLocation: Boolean = false): Either[ParseError, Expr] = {
    val indexer = StringIndex(input.slice(0, input.length))
    parse(input, ParserInternal(fileName, ignoreLocation = ignoreLocation, defaultIndexer = Some(indexer))(_).entrance) match {
      case Parsed.Success(expr, _) => Right(expr)
      case Parsed.Failure(msg, index, extra) => {
        val pos = indexer.charIndexToUnicodeLineAndColumn(index)
        val p = Pos(indexer.charIndexToUnicodeIndex(index), pos.line, pos.column)
        Left(ParseError(s"Parsing failed: ${extra.trace().longMsg}", p))
      }
    }
  }

  @deprecated
  def parseExpression(fileName: String, input: String, ignoreLocation: Boolean = false): Parsed[Expr] = parse(input, ParserInternal(fileName, ignoreLocation = ignoreLocation)(_).entrance)
}
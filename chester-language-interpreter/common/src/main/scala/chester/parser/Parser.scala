package chester.parser;

import fastparse.*
import NoWhitespace.*
import chester.error.{Pos, RangeInFile, SourcePos}
import chester.syntax.concrete.*
import chester.utils.StringIndex
import chester.utils.parse.*

import java.lang.Character.{isDigit, isLetter}
import java.nio.file.{Files, Paths}
import scala.util.*
import chester.syntax.IdentifierRules.*

import scala.:+

case class ParserInternal(fileName: String, ignoreLocation: Boolean = false, defaultIndexer: Option[StringIndex] = None)(implicit p: P[?]) {

  def nEnd: P[Unit] = P("\n" | End)

  @deprecated("comment is lost")
  def comment: P[Unit] = P("//" ~ CharPred(_ != '\n').rep ~ nEnd)

  def commentOneLine: P[Comment] = PwithPos("//" ~ CharPred(_ != '\n').rep.! ~ ("\n" | End)).map(Comment(_, CommentType.OneLine, _))

  def allComment: P[Comment] = P(commentOneLine)

  def simpleDelimiter: P[Unit] = P(CharsWhileIn(" \t\r\n"))

  @deprecated("comment is lost")
  def delimiter: P[Unit] = P((simpleDelimiter | comment).rep)

  def delimiter1: P[Vector[Comment]] = P((simpleDelimiter.map(x => Vector()) | allComment.rep).rep).map(_.flatten.toVector)

  @deprecated("comment is lost")
  def lineEnding: P[Unit] = P(comment | (CharsWhileIn(" \t\r").? ~ ("\n" | End)))

  def lineEnding1: P[Vector[Comment]] = P(commentOneLine.map(Vector(_)) | (CharsWhileIn(" \t\r").? ~ nEnd).map(x => Vector()))

  def lineNonEndingSpace: P[Unit] = P((CharsWhileIn(" \t\r")))

  @deprecated("comment is lost")
  def maybeSpace: P[Unit] = P(delimiter.?)

  def maybeSpace1: P[Vector[Comment]] = P(delimiter1.?.map(_.toVector.flatten))

  def simpleId: P[String] = P((CharacterPred(identifierFirst).rep(1) ~ CharacterPred(identifierRest).rep).!)

  def id: P[String] = operatorId | simpleId

  def operatorId: P[String] = P((CharacterPred(operatorIdentifierFirst).rep(1) ~ CharacterPred(operatorIdentifierRest).rep).!)

  def begin: P[Int] = Index

  def end: P[Int] = Index


  val indexer: StringIndex = defaultIndexer.getOrElse(StringIndex(p.input))

  private def loc(begin: Int, end: Int): Option[SourcePos] = {
    if (ignoreLocation) return None
    val start = indexer.charIndexToUnicodeLineAndColumn(begin)
    val endPos = indexer.charIndexToUnicodeLineAndColumn(end - 1)
    Some(SourcePos(fileName, RangeInFile(Pos(indexer.charIndexToUnicodeIndex(begin), start.line, start.column), Pos(indexer.charIndexToUnicodeIndex(end - 1), endPos.line, endPos.column))))
  }

  extension [T](inline parse0: P[T]) {
    inline def withPos: P[(T, Option[SourcePos])] = (begin ~ parse0 ~ end).map { case (b, x, e) => (x, loc(b, e)) }

    inline def must(inline message: String = "Expected something"): P[T] = parse0.? flatMap {
      case Some(x) => Pass(x)
      case None => Fail.opaque(message)./
    }

    inline def on(inline condition: Boolean): P[T] = if condition then parse0 else Fail("")

    inline def checkOn(inline condition: Boolean): P[Unit] = if condition then parse0 else Pass(())

    inline def thenTry(inline parse1: P[T]): P[T] = parse0.?.flatMap {
      case Some(result) => Pass(result)
      case None => parse1
    }
  }

  inline def PwithPos[T](inline parse0: P[T]): P[(T, Option[SourcePos])] = P(parse0.withPos)

  def identifier: P[Identifier] = P(id.withPos).map { case (name, pos) => Identifier(name, pos) }

  def infixIdentifier: P[Identifier] = P(operatorId.withPos).map { case (name, pos) => Identifier(name, pos) }

  def signed: P[String] = P("".!) // P(CharIn("+\\-").?.!)

  def hexLiteral: P[String] = P("0x" ~ CharsWhileIn("0-9a-fA-F").must()).!

  def binLiteral: P[String] = P("0b" ~ CharsWhileIn("01").must()).!

  def decLiteral: P[String] = P(CharsWhileIn("0-9")).!

  def expLiteral: P[String] = P(CharsWhileIn("0-9") ~ "." ~ CharsWhileIn("0-9") ~ (CharIn("eE") ~ signed ~ CharsWhileIn("0-9")).?).!

  def integerLiteral: P[ParsedExpr] = P(signed ~ (hexLiteral | binLiteral | decLiteral).!).withPos.map {
    case ((sign, value), pos) =>
      val actualValue = if (value.startsWith("0x")) BigInt(sign + value.drop(2), 16)
      else if (value.startsWith("0b")) BigInt(sign + value.drop(2), 2)
      else BigInt(sign + value)
      IntegerLiteral(actualValue, pos)
  }

  def doubleLiteral: P[ParsedExpr] = P(signed ~ expLiteral.withPos).map {
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

  def stringLiteralExpr: P[ParsedExpr] = P((stringLiteral | heredocLiteral).withPos).map {
    case (value, pos) => StringLiteral(value, pos)
  }

  def literal: P[ParsedExpr] = P(doubleLiteral | integerLiteral | stringLiteralExpr)

  def simpleAnnotation: P[Identifier] = "@" ~ identifier

  def comma: P[Unit] = P(maybeSpace ~ "," ~ maybeSpace)

  def list: P[ListExpr] = PwithPos("[" ~ maybeSpace ~ parse().rep(sep = comma) ~ comma.? ~ maybeSpace ~ "]").map { (terms, pos) =>
    ListExpr(terms.toVector, pos)
  }

  def tuple: P[Tuple] = PwithPos("(" ~ maybeSpace ~ parse().rep(sep = comma) ~ comma.? ~ maybeSpace ~ ")").map { (terms, pos) =>
    Tuple(terms.toVector, pos)
  }

  def annotation: P[(Identifier, Vector[ParsedMaybeTelescope])] = P("@" ~ identifier ~ callingZeroOrMore())

  def annotated: P[AnnotatedExpr] = PwithPos(annotation ~ parse()).map { case ((annotation, telescope, expr), pos) =>
    AnnotatedExpr(annotation, telescope, expr, pos)
  }

  // TODO blockAndLineEndEnds
  case class ParsingContext(inOpSeq: Boolean = false, dontallowOpSeq: Boolean = false, dontallowBiggerSymbol: Boolean = false, dontAllowEqualSymbol: Boolean = false, dontAllowVararg: Boolean = false, newLineAfterBlockMeansEnds: Boolean = false) {
    def opSeq = !inOpSeq && !dontallowOpSeq

    def blockCall = !inOpSeq
  }

  def callingOnce(ctx: ParsingContext = ParsingContext()): P[ParsedMaybeTelescope] = P((list | tuple) | (lineNonEndingSpace.? ~ anonymousBlockLikeFunction.on(ctx.blockCall)).withPos.map { case (block, pos) =>
    Tuple(Vector(block), pos)
  })

  def callingMultiple(ctx: ParsingContext = ParsingContext()): P[Vector[ParsedMaybeTelescope]] = P(callingOnce(ctx = ctx).rep(min = 1).map(_.toVector))

  def callingZeroOrMore(ctx: ParsingContext = ParsingContext()): P[Vector[ParsedMaybeTelescope]] = P(callingOnce(ctx = ctx).rep.map(_.toVector))

  def functionCall(function: ParsedExpr, p: Option[SourcePos] => Option[SourcePos], ctx: ParsingContext = ParsingContext()): P[FunctionCall] = PwithPos(callingOnce(ctx = ctx)).map { case (telescope, pos) =>
    FunctionCall(function, telescope, p(pos))
  }

  def dotCall(expr: ParsedExpr, p: Option[SourcePos] => Option[SourcePos], ctx: ParsingContext = ParsingContext()): P[DotCall] = PwithPos(maybeSpace ~ "." ~ identifier ~ callingZeroOrMore(ctx = ctx)).map { case ((field, telescope), pos) =>
    DotCall(expr, field, telescope, p(pos))
  }

  def insideBlock: P[Block] = PwithPos((maybeSpace ~ statement).rep ~ maybeSpace ~ parse().? ~ maybeSpace).flatMap { case ((heads, tail), pos) =>
    if (heads.isEmpty && tail.isEmpty) Fail("expect something") else Pass(Block(Vector.from(heads), tail, pos))
  }

  def block: P[ParsedExpr] = PwithPos("{" ~ (maybeSpace ~ statement).rep ~ maybeSpace ~ parse().? ~ maybeSpace ~ "}").flatMap { case ((heads, tail), pos) =>
    if (heads.isEmpty && tail.isEmpty) Fail("expect something") else Pass(Block(Vector.from(heads), tail, pos))
  }

  inline def anonymousBlockLikeFunction: P[ParsedExpr] = block | objectParse

  def statement: P[ParsedExpr] = P((parse(ctx = ParsingContext(newLineAfterBlockMeansEnds = true)) ~ Index).flatMap((expr, index) => {
    val itWasBlockEnding = p.input(index - 1) == '}'
    Pass(expr) ~ (maybeSpace ~ ";" | lineEnding.on(itWasBlockEnding))
  }))

  def opSeq(expr: ParsedExpr, p: Option[SourcePos] => Option[SourcePos], ctx: ParsingContext): P[OpSeq] = PwithPos(opSeqGettingExprs(ctx = ctx)).flatMap((exprs, pos) => {
    val xs = (expr +: exprs)
    val exprCouldPrefix = expr match {
      case Identifier(name, _, _) if strIsOperator(name) => true
      case _ => false
    }
    if (ctx.dontAllowEqualSymbol && xs.exists(_ match {
      case Identifier("=", _, _) => true
      case _ => false
    })) return Fail("Looks like a equal")
    if (ctx.dontAllowVararg && xs.exists(_ match {
      case Identifier("...", _, _) => true
      case _ => false
    })) return Fail("Looks like a vararg")
    if (!(exprCouldPrefix || xs.exists(_.isInstanceOf[Identifier]))) Fail("Expected identifier") else Pass(OpSeq(xs.toVector, p(pos)))
  })

  def objectParse: P[ParsedExpr] = PwithPos("{" ~ (maybeSpace ~ identifier ~ maybeSpace ~ "=" ~ maybeSpace ~ parse() ~ maybeSpace).rep(sep = comma) ~ comma.? ~ maybeSpace ~ "}").map { (fields, pos) =>
    ObjectExpr(fields.toVector, pos)
  }

  def opSeqGettingExprs(ctx: ParsingContext): P[Vector[ParsedExpr]] = P(maybeSpace ~ parse(ctx = ctx.copy(inOpSeq = true)) ~ Index).flatMap { (expr, index) =>
    val itWasBlockEnding = p.input(index - 1) == '}'
    ((!lineEnding).checkOn(itWasBlockEnding && ctx.newLineAfterBlockMeansEnds) ~ opSeqGettingExprs(ctx = ctx).map(expr +: _)) | Pass(Vector(expr))
  }

  def tailExpr(expr: ParsedExpr, getPos: Option[SourcePos] => Option[SourcePos], ctx: ParsingContext = ParsingContext()): P[ParsedExpr] = P((dotCall(expr, getPos, ctx) | functionCall(expr, getPos, ctx = ctx).on(expr.isInstanceOf[Identifier] || expr.isInstanceOf[FunctionCall] || !ctx.inOpSeq) | opSeq(expr, getPos, ctx = ctx).on(ctx.opSeq)).withPos ~ Index).flatMap({ (expr, pos, index) => {
    val itWasBlockEnding = p.input(index - 1) == '}'
    val getPos1 = ((endPos: Option[SourcePos]) => for {
      p0 <- getPos(pos)
      p1 <- endPos
    } yield p0.combine(p1))
    ((!lineEnding).checkOn(itWasBlockEnding && ctx.newLineAfterBlockMeansEnds) ~ tailExpr(expr, getPos1, ctx = ctx)) | Pass(expr)
  }
  })

  inline def parse0: P[ParsedExpr] = objectParse | block | annotated | list | tuple | literal | identifier

  def parse(ctx: ParsingContext = ParsingContext()): P[ParsedExpr] = P(parse0.withPos ~ Index).flatMap { (expr, pos, index) =>
    val itWasBlockEnding = p.input(index - 1) == '}'
    val getPos = ((endPos: Option[SourcePos]) => for {
      p0 <- pos
      p1 <- endPos
    } yield p0.combine(p1))
    ((!lineEnding).checkOn(itWasBlockEnding && ctx.newLineAfterBlockMeansEnds) ~ tailExpr(expr, getPos, ctx = ctx)) | Pass(expr)
  }

  def exprEntrance: P[ParsedExpr] = P(Start ~ maybeSpace ~ parse() ~ maybeSpace ~ End)

  def statementsEntrance: P[Vector[ParsedExpr]] = P(Start ~ (maybeSpace ~ statement ~ maybeSpace).rep ~ maybeSpace ~ End).map(_.toVector)

  def toplevelEntrance: P[Block] = P(Start ~ maybeSpace ~ insideBlock ~ maybeSpace ~ End)
}

case class ParseError(message: String, index: Pos)

sealed trait ParserSource

case class FileNameAndContent(fileName: String, content: String) extends ParserSource

case class FilePath(path: String) extends ParserSource

object Parser {
  private def getContentFromSource(source: ParserSource): Either[ParseError, (String, String)] = {
    source match {
      case FileNameAndContent(fileName, content) =>
        Right((fileName, content))
      case FilePath(path) =>
        Try(new String(Files.readAllBytes(Paths.get(path)))) match {
          case Success(content) =>
            val fileName = Paths.get(path).getFileName.toString
            Right((fileName, content))
          case Failure(exception) =>
            Left(ParseError(s"Failed to read file: ${exception.getMessage}", Pos.Zero))
        }
    }
  }

  private def parseFromSource[T](source: ParserSource, parserFunc: ParserInternal => P[T], ignoreLocation: Boolean = false): Either[ParseError, T] = {
    getContentFromSource(source) match {
      case Right((fileName, content)) =>
        val indexer = StringIndex(content)
        parse(content, x => parserFunc(ParserInternal(fileName, ignoreLocation = ignoreLocation, defaultIndexer = Some(indexer))(x))) match {
          case Parsed.Success(result, _) => Right(result)
          case Parsed.Failure(msg, index, extra) =>
            val pos = indexer.charIndexToUnicodeLineAndColumn(index)
            val p = Pos(indexer.charIndexToUnicodeIndex(index), pos.line, pos.column)
            Left(ParseError(s"Parsing failed: ${extra.trace().longMsg}", p))
        }
      case Left(error) => Left(error)
    }
  }

  def parseStatements(source: ParserSource, ignoreLocation: Boolean = false): Either[ParseError, Vector[ParsedExpr]] = {
    parseFromSource(source, _.statementsEntrance, ignoreLocation)
  }

  def parseTopLevel(source: ParserSource, ignoreLocation: Boolean = false): Either[ParseError, Block] = {
    parseFromSource(source, _.toplevelEntrance, ignoreLocation)
  }

  def parseExpr(source: ParserSource, ignoreLocation: Boolean = false): Either[ParseError, ParsedExpr] = {
    parseFromSource(source, _.exprEntrance, ignoreLocation)
  }

  @deprecated("Use parseExpr with ParserSource instead")
  def parseFile(fileName: String): Either[ParseError, ParsedExpr] = {
    parseExpr(FilePath(fileName))
  }

  @deprecated("Use parseExpr with ParserSource instead")
  def parseContent(fileName: String, input: String, ignoreLocation: Boolean = false): Either[ParseError, ParsedExpr] = {
    parseExpr(FileNameAndContent(fileName, input), ignoreLocation)
  }

  @deprecated("Use parseExpr with ParserSource instead")
  def parseExpression(fileName: String, input: String, ignoreLocation: Boolean = false): Parsed[ParsedExpr] = {
    parse(input, x => ParserInternal(fileName, ignoreLocation = ignoreLocation)(x).exprEntrance)
  }
}
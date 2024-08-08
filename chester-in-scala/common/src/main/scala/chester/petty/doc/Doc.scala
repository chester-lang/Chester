package chester.petty.doc

import scala.annotation.tailrec
import scala.language.implicitConversions

import fansi.*

import scala.annotation.tailrec
import scala.language.implicitConversions

sealed trait Color

sealed trait ForegroundColor extends Color

object ForegroundColor:
  case object Black extends ForegroundColor

  case object Red extends ForegroundColor

  case object Green extends ForegroundColor

  case object Yellow extends ForegroundColor

  case object Blue extends ForegroundColor

  case object Magenta extends ForegroundColor

  case object Cyan extends ForegroundColor

  case object LightGray extends ForegroundColor

  case object DarkGray extends ForegroundColor

  case object LightRed extends ForegroundColor

  case object LightGreen extends ForegroundColor

  case object LightYellow extends ForegroundColor

  case object LightBlue extends ForegroundColor

  case object LightMagenta extends ForegroundColor

  case object LightCyan extends ForegroundColor

  case object White extends ForegroundColor

  case object Reset extends ForegroundColor

sealed trait BackgroundColor extends Color

object BackgroundColor:
  case object Black extends BackgroundColor

  case object Red extends BackgroundColor

  case object Green extends BackgroundColor

  case object Yellow extends BackgroundColor

  case object Blue extends BackgroundColor

  case object Magenta extends BackgroundColor

  case object Cyan extends BackgroundColor

  case object LightGray extends BackgroundColor

  case object DarkGray extends BackgroundColor

  case object LightRed extends BackgroundColor

  case object LightGreen extends BackgroundColor

  case object LightYellow extends BackgroundColor

  case object LightBlue extends BackgroundColor

  case object LightMagenta extends BackgroundColor

  case object LightCyan extends BackgroundColor

  case object White extends BackgroundColor

  case object Reset extends BackgroundColor

import fansi.Attr

object ColorMapping:
  def toFansiAttr(color: ForegroundColor): Attr = color match
    case ForegroundColor.Black => fansi.Color.Black
    case ForegroundColor.Red => fansi.Color.Red
    case ForegroundColor.Green => fansi.Color.Green
    case ForegroundColor.Yellow => fansi.Color.Yellow
    case ForegroundColor.Blue => fansi.Color.Blue
    case ForegroundColor.Magenta => fansi.Color.Magenta
    case ForegroundColor.Cyan => fansi.Color.Cyan
    case ForegroundColor.LightGray => fansi.Color.LightGray
    case ForegroundColor.DarkGray => fansi.Color.DarkGray
    case ForegroundColor.LightRed => fansi.Color.LightRed
    case ForegroundColor.LightGreen => fansi.Color.LightGreen
    case ForegroundColor.LightYellow => fansi.Color.LightYellow
    case ForegroundColor.LightBlue => fansi.Color.LightBlue
    case ForegroundColor.LightMagenta => fansi.Color.LightMagenta
    case ForegroundColor.LightCyan => fansi.Color.LightCyan
    case ForegroundColor.White => fansi.Color.White
    case ForegroundColor.Reset => fansi.Color.Reset

  def toHtmlCss(color: ForegroundColor): String = color match
    case ForegroundColor.Black => "black"
    case ForegroundColor.Red => "red"
    case ForegroundColor.Green => "green"
    case ForegroundColor.Yellow => "yellow"
    case ForegroundColor.Blue => "blue"
    case ForegroundColor.Magenta => "magenta"
    case ForegroundColor.Cyan => "cyan"
    case ForegroundColor.LightGray => "lightgray"
    case ForegroundColor.DarkGray => "darkgray"
    case ForegroundColor.LightRed => "lightcoral"
    case ForegroundColor.LightGreen => "lightgreen"
    case ForegroundColor.LightYellow => "lightyellow"
    case ForegroundColor.LightBlue => "lightblue"
    case ForegroundColor.LightMagenta => "lightpink"
    case ForegroundColor.LightCyan => "lightcyan"
    case ForegroundColor.White => "white"
    case ForegroundColor.Reset => "initial"

  def toFansiAttr(color: BackgroundColor): Attr = color match
    case BackgroundColor.Black => fansi.Back.Black
    case BackgroundColor.Red => fansi.Back.Red
    case BackgroundColor.Green => fansi.Back.Green
    case BackgroundColor.Yellow => fansi.Back.Yellow
    case BackgroundColor.Blue => fansi.Back.Blue
    case BackgroundColor.Magenta => fansi.Back.Magenta
    case BackgroundColor.Cyan => fansi.Back.Cyan
    case BackgroundColor.LightGray => fansi.Back.LightGray
    case BackgroundColor.DarkGray => fansi.Back.DarkGray
    case BackgroundColor.LightRed => fansi.Back.LightRed
    case BackgroundColor.LightGreen => fansi.Back.LightGreen
    case BackgroundColor.LightYellow => fansi.Back.LightYellow
    case BackgroundColor.LightBlue => fansi.Back.LightBlue
    case BackgroundColor.LightMagenta => fansi.Back.LightMagenta
    case BackgroundColor.LightCyan => fansi.Back.LightCyan
    case BackgroundColor.White => fansi.Back.White
    case BackgroundColor.Reset => fansi.Back.Reset

  def toHtmlCss(color: BackgroundColor): String = color match
    case BackgroundColor.Black => "black"
    case BackgroundColor.Red => "red"
    case BackgroundColor.Green => "green"
    case BackgroundColor.Yellow => "yellow"
    case BackgroundColor.Blue => "blue"
    case BackgroundColor.Magenta => "magenta"
    case BackgroundColor.Cyan => "cyan"
    case BackgroundColor.LightGray => "lightgray"
    case BackgroundColor.DarkGray => "darkgray"
    case BackgroundColor.LightRed => "lightcoral"
    case BackgroundColor.LightGreen => "lightgreen"
    case BackgroundColor.LightYellow => "lightyellow"
    case BackgroundColor.LightBlue => "lightblue"
    case BackgroundColor.LightMagenta => "lightpink"
    case BackgroundColor.LightCyan => "lightcyan"
    case BackgroundColor.White => "white"
    case BackgroundColor.Reset => "initial"

sealed trait Doc:
  def <>(other: Doc): Doc = concat(this, other)

  def <+>(other: Doc): Doc = concat(this, text(" "), other)

  def </>(other: Doc): Doc = concat(this, line(text(" ")), other)

  def <\>(other: Doc): Doc = concat(this, line(text("")), other)

case class Text(content: String) extends Doc:
  require(!content.contains("\n") && !content.contains("\r"), "Text cannot contain newlines or carriage returns")

  override def toString: String = content

case class Colored(doc: Doc, color: Color) extends Doc

case class Concat(docs: Seq[Doc]) extends Doc:
  require(docs.nonEmpty, "Concat requires at least one document")

case object NewLine extends Doc

case class Line(repl: Doc) extends Doc

case class Group(doc: Doc) extends Doc

case class Indented(indent: Indent, innerDoc: Doc) extends Doc

enum Indent:
  case Spaces(count: Int)
  case Tab

sealed trait Token

case class TokenText(content: String) extends Token

case object TokenNewLine extends Token

case class TokenColor(tokens: Vector[Token], color: Color) extends Token

implicit def text(s: String): Doc = {
  @tailrec
  def loop(chars: List[Char], acc: Vector[Doc], current: String): Vector[Doc] = {
    chars match {
      case Nil =>
        if (current.nonEmpty) acc :+ Text(current) else acc
      case '\n' :: tail =>
        val updatedAcc = if (current.nonEmpty) acc :+ Text(current) :+ NewLine else acc :+ NewLine
        loop(tail, updatedAcc, "")
      case '\r' :: tail =>
        loop(tail, acc, current) // Skip \r
      case ch :: tail =>
        loop(tail, acc, current + ch)
    }
  }

  val parts = loop(s.toList, Vector.empty, "")
  if (parts.isEmpty) Text("") else Concat(parts)
}

def concat(docs: Doc*): Doc = Concat(docs)

def colored(doc: Doc, color: Color): Doc = Colored(doc, color)

def line(repl: Doc): Doc = Line(repl)

def linebreak: Doc = line(text(""))

def group(doc: Doc): Doc = Group(doc)

def indented(indent: Indent, innerDoc: Doc): Doc = Indented(indent, innerDoc)

private def measure(doc: Doc, charCounter: CharCounter, currentIndent: String = ""): Int = {
  doc match {
    case Text(content) => charCounter.countString(currentIndent + content)
    case Colored(innerDoc, _) => measure(innerDoc, charCounter, currentIndent)
    case Concat(docs) => docs.map(d => measure(d, charCounter, currentIndent)).sum
    case NewLine => 0
    case Line(repl) => measure(repl, charCounter, currentIndent)
    case Group(innerDoc) => measure(innerDoc, charCounter, currentIndent)
    case Indented(indent, innerDoc) =>
      val indentStr = indent match {
        case Indent.Spaces(count) => " " * count
        case Indent.Tab => "\t"
      }
      measure(innerDoc, charCounter, currentIndent + indentStr)
  }
}

private def flatten(doc: Doc): Vector[Doc] = doc match {
  case Concat(docs) => docs.toVector.flatMap(flatten)
  case other => Vector(other)
}

private def decideGroup(group: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Doc] = {
  val flat = flatten(group)
  if (flat.map(d => measure(d, charCounter)).sum <= maxWidth) flat
  else flat.flatMap {
    case Line(_) => Vector(NewLine)
    case other => Vector(other)
  }
}

private def renderTokens(doc: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Token] = {
  def render(docs: Vector[Doc], tokens: Vector[Token], currentWidth: Int, maxWidth: Int, currentIndent: String): Vector[Token] = {
    if (docs.isEmpty) return tokens

    val nextDoc = docs.head
    val remainingDocs = docs.tail

    nextDoc match {
      case Text(content) =>
        val updatedTokens = tokens :+ TokenText(currentIndent + content)
        render(remainingDocs, updatedTokens, currentWidth + charCounter.countString(content), maxWidth, currentIndent)
      case Colored(innerDoc, color) =>
        val coloredTokens = renderTokens(innerDoc, maxWidth, charCounter)
        val updatedTokens = tokens :+ TokenColor(coloredTokens, color)
        render(remainingDocs, updatedTokens, currentWidth, maxWidth, currentIndent)
      case NewLine =>
        render(remainingDocs, tokens :+ TokenNewLine, 0, maxWidth, currentIndent)
      case Line(repl) =>
        if (currentWidth + measure(repl, charCounter) > maxWidth) {
          render(remainingDocs, tokens :+ TokenNewLine, 0, maxWidth, currentIndent)
        } else {
          val replTokens = renderTokens(repl, maxWidth, charCounter)
          render(remainingDocs, tokens ++ replTokens, currentWidth + measure(repl, charCounter), maxWidth, currentIndent)
        }
      case Group(innerDoc) =>
        val groupDocs = decideGroup(innerDoc, maxWidth, charCounter)
        render(groupDocs ++ remainingDocs, tokens, currentWidth, maxWidth, currentIndent)
      case Indented(indent, innerDoc) =>
        val indentStr = indent match {
          case Indent.Spaces(count) => " " * count
          case Indent.Tab => "\t"
        }
        val updatedTokens = tokens :+ TokenText(indentStr)
        render(Vector(innerDoc) ++ remainingDocs, updatedTokens, currentWidth + charCounter.countString(indentStr), maxWidth, currentIndent + indentStr)
    }
  }

  val flattenedDocs = flatten(doc)
  render(flattenedDocs, Vector.empty[Token], 0, maxWidth, "")
}

trait CharCounter:
  def countCodePoint: Int => Int

  def countString: String => Int = _.codePoints().toArray.foldLeft(0)((acc, cp) => acc + countCodePoint(cp))

object DefaultCharCounter extends CharCounter:
  override val countCodePoint: Int => Int = _ => 1

abstract class Renderer[T]:
  def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): T

  def charCounter: CharCounter = DefaultCharCounter

  def render(doc: Doc, maxWidth: Int, useCRLF: Boolean = false): T =
    val tokens = chester.petty.doc.renderTokens(doc, maxWidth, charCounter)
    renderTokens(tokens, useCRLF)

implicit object StringRenderer extends Renderer[String]:
  override def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): String =
    val newline = if (useCRLF) "\r\n" else "\n"
    tokens.map {
      case TokenText(content) => content
      case TokenNewLine => newline
      case TokenColor(innerTokens, _) => renderTokens(innerTokens, useCRLF)
    }.mkString

implicit object FansiRenderer extends Renderer[fansi.Str]:
  override def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): fansi.Str =
    val newline = if (useCRLF) "\r\n" else "\n"
    tokens.foldLeft(fansi.Str("")) {
      case (acc, TokenText(content)) => acc ++ fansi.Str(content)
      case (acc, TokenNewLine) => acc ++ fansi.Str(newline)
      case (acc, TokenColor(innerTokens, color: ForegroundColor)) =>
        acc ++ innerTokens.foldLeft(fansi.Str("")) {
          case (innerAcc, TokenText(content)) => innerAcc ++ ColorMapping.toFansiAttr(color)(content)
          case (innerAcc, TokenNewLine) => innerAcc ++ fansi.Str(newline)
          case (innerAcc, TokenColor(innerInnerTokens, innerColor)) =>
            innerAcc ++ innerInnerTokens.foldLeft(fansi.Str("")) {
              case (innermostAcc, TokenText(content)) => innermostAcc ++ ColorMapping.toFansiAttr(innerColor.asInstanceOf[ForegroundColor])(content)
              case (innermostAcc, TokenNewLine) => innermostAcc ++ fansi.Str(newline)
              case (innermostAcc, TokenColor(_, _)) => innermostAcc // This would rarely be the case, you might want to throw an exception here
            }
        }
    }

object HtmlRenderer extends Renderer[String]:
  override def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): String =
    val newline = "<br />"
    tokens.map {
      case TokenText(content) => content
      case TokenNewLine => newline
      case TokenColor(innerTokens, color: ForegroundColor) => s"<span style='color: ${ColorMapping.toHtmlCss(color)};'>${renderTokens(innerTokens, useCRLF)}</span>"
    }.mkString

def render[T](doc: Doc, maxWidth: Int, useCRLF: Boolean = false)(implicit renderer: Renderer[T]): T =
  renderer.render(doc, maxWidth, useCRLF)

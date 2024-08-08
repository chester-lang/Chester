package chester.petty.doc

import scala.annotation.tailrec
import scala.language.implicitConversions

import fansi.*

import scala.annotation.tailrec
import scala.language.implicitConversions

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

private def measure(doc: Doc, charCounter: CharCounter): Int = {
  doc match {
    case Text(content) => charCounter.countString(content)
    case Colored(innerDoc, _) => measure(innerDoc, charCounter)
    case Concat(docs) => docs.map(d => measure(d, charCounter)).sum
    case NewLine => 0
    case Line(repl) => measure(repl, charCounter)
    case Group(innerDoc) => measure(innerDoc, charCounter)
    case Indented(indent, innerDoc) =>
      val indentStr = indent match {
        case Indent.Spaces(count) => " " * count
        case Indent.Tab => "\t"
      }
      charCounter.countString(indentStr) + measure(innerDoc, charCounter)
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
  doc match {
    case Text(content) =>
      Vector(TokenText(content))

    case Colored(innerDoc, color) =>
      val coloredTokens = renderTokens(innerDoc, maxWidth, charCounter)
      Vector(TokenColor(coloredTokens, color))

    case NewLine =>
      Vector(TokenNewLine)

    case Line(repl) =>
      if (measure(repl, charCounter) > maxWidth) {
        Vector(TokenNewLine)
      } else {
        renderTokens(repl, maxWidth, charCounter)
      }

    case Group(innerDoc) =>
      decideGroup(innerDoc, maxWidth, charCounter).flatMap(renderTokens(_, maxWidth, charCounter))

    case Indented(indent, innerDoc) =>
      val indentStr = indent match {
        case Indent.Spaces(count) => " " * count
        case Indent.Tab => "\t"
      }
      Vector(TokenText(indentStr)) ++ renderTokens(innerDoc, maxWidth, charCounter)

    case Concat(innerDocs) =>
      innerDocs.flatMap(renderTokens(_, maxWidth, charCounter)).toVector
  }
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

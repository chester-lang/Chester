package chester.petty.doc

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

private def measureWithinLine(doc: Doc, charCounter: CharCounter, maxWidth: Int): Option[Int] = doc match {
  case Text(content) =>
    val length = charCounter.countString(content)
    if (length > maxWidth) None else Some(length)

  case Colored(innerDoc, _) =>
    measureWithinLine(innerDoc, charCounter, maxWidth)

  case Concat(docs) =>
    docs.foldLeft(Option(0)) {
      case (Some(acc), d) =>
        measureWithinLine(d, charCounter, maxWidth).flatMap { length =>
          val newLength = acc + length
          if (newLength > maxWidth) None else Some(newLength)
        }
      case (None, _) => None
    }

  case Line(repl) =>
    measureWithinLine(repl, charCounter, maxWidth)

  case Group(innerDoc) =>
    measureWithinLine(innerDoc, charCounter, maxWidth)

  case _ =>
    None
}

private def flatten(doc: Doc): Vector[Doc] = doc match {
  case Concat(docs) => docs.toVector.flatMap(flatten)
  case other => Vector(other)
}

private def decideGroup(group: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Doc] = {
  val flat = flatten(group)
  if (flat.map(d => measureWithinLine(d, charCounter, maxWidth)).forall(_.isDefined)) flat
  else flat.flatMap {
    case Line(_) => Vector(NewLine)
    case other => Vector(other, NewLine)
  }
}

private def renderWithinLine(doc: Doc, currentWidth: Int, charCounter: CharCounter, maxWidth: Int): Vector[Token] = doc match {
  case Text(content) =>
    Vector(TokenText(content))

  case Colored(innerDoc, color) =>
    val coloredTokens = renderWithinLine(innerDoc, currentWidth, charCounter, maxWidth)
    Vector(TokenColor(coloredTokens, color))

  case Concat(innerDocs) =>
    innerDocs.flatMap(renderWithinLine(_, currentWidth, charCounter, maxWidth)).toVector

  case Line(repl) =>
    renderWithinLine(repl, currentWidth, charCounter, maxWidth)

  case Group(innerDoc) =>
    renderWithinLine(innerDoc, currentWidth, charCounter, maxWidth)

  case _ =>
    throw new UnsupportedOperationException("This doc type should not be rendered within a line")
}

private def renderFromLineStart(doc: Doc, currentIndent: String, charCounter: CharCounter, maxWidth: Int): Vector[Token] = doc match {
  case Text(content) =>
    Vector(TokenText(currentIndent + content))

  case Colored(innerDoc, color) =>
    val coloredTokens = renderFromLineStart(innerDoc, currentIndent, charCounter, maxWidth)
    Vector(TokenColor(coloredTokens, color))

  case NewLine =>
    Vector(TokenNewLine)

  case Line(repl) =>
    measureWithinLine(repl, charCounter, maxWidth) match {
      case Some(length) if length <= maxWidth =>
        renderWithinLine(repl, 0, charCounter, maxWidth)
      case _ =>
        Vector(TokenNewLine)
    }

  case Group(innerDoc) =>
    decideGroup(innerDoc, maxWidth, charCounter).flatMap(renderFromLineStart(_, "", charCounter, maxWidth))

  case Indented(indent, innerDoc) =>
    val indentStr = indent match {
      case Indent.Spaces(count) => " " * count
      case Indent.Tab => "\t"
    }
    renderFromLineStart(innerDoc, currentIndent + indentStr, charCounter, maxWidth)

  case Concat(innerDocs) =>
    innerDocs.flatMap(renderFromLineStart(_, currentIndent, charCounter, maxWidth)).toVector

  case _ =>
    throw new UnsupportedOperationException("This doc type should not be rendered from the line start")
}

private def renderTokens(doc: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Token] = {
  renderFromLineStart(doc, "", charCounter, maxWidth)
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

def render[T](doc: Doc, maxWidth: Int, useCRLF: Boolean = false)(implicit renderer: Renderer[T]): T =
  renderer.render(doc, maxWidth, useCRLF)

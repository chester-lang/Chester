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

private def measure(doc: Doc, charCounter: CharCounter, maxWidth: Int): Option[Int] = doc match {
  case Text(content) =>
    val length = charCounter.countString(content)
    if (length > maxWidth) None else Some(length)

  case Colored(innerDoc, _) =>
    measure(innerDoc, charCounter, maxWidth)

  case Concat(docs) =>
    docs.foldLeft(Option(0)) {
      case (Some(acc), d) =>
        measure(d, charCounter, maxWidth).flatMap { length =>
          val newLength = acc + length
          if (newLength > maxWidth) None else Some(newLength)
        }
      case (None, _) => None
    }

  case NewLine =>
    None

  case Line(repl) =>
    measure(repl, charCounter, maxWidth)

  case Group(innerDoc) =>
    measure(innerDoc, charCounter, maxWidth)

  case Indented(_, innerDoc) =>
    measure(innerDoc, charCounter, maxWidth)
}

private def flatten(doc: Doc): Vector[Doc] = doc match {
  case Concat(docs) => docs.toVector.flatMap(flatten)
  case other => Vector(other)
}

private def decideGroup(group: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Doc] = {
  val flat = flatten(group)
  if (flat.map(d => measure(d, charCounter, maxWidth)).forall(_.isDefined)) flat
  else flat.flatMap {
    case Line(_) => Vector(NewLine)
    case other => Vector(other, NewLine)
  }
}

private def renderTokens(doc: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Token] = {
  def render(doc: Doc, currentWidth: Int, currentIndent: String, atLineStart: Boolean): Vector[Token] = doc match {
    case Text(content) =>
      if (atLineStart) Vector(TokenText(currentIndent + content))
      else Vector(TokenText(content))

    case Colored(innerDoc, color) =>
      val coloredTokens = renderTokens(innerDoc, maxWidth, charCounter)
      Vector(TokenColor(coloredTokens, color))

    case NewLine =>
      Vector(TokenNewLine)

    case Line(repl) =>
      measure(repl, charCounter, maxWidth) match {
        case Some(length) if currentWidth + length <= maxWidth =>
          renderTokens(repl, maxWidth, charCounter)
        case _ =>
          Vector(TokenNewLine)
      }

    case Group(innerDoc) =>
      decideGroup(innerDoc, maxWidth, charCounter).flatMap(render(_, 0, "", atLineStart = true))

    case Indented(indent, innerDoc) =>
      val indentStr = indent match {
        case Indent.Spaces(count) => " " * count
        case Indent.Tab => "\t"
      }
      if (atLineStart) {
        val indentedTokens = render(innerDoc, 0, currentIndent + indentStr, atLineStart = false)
        Vector(TokenText(indentStr)) ++ indentedTokens
      } else {
        render(innerDoc, currentWidth, currentIndent, atLineStart = false)
      }

    case Concat(innerDocs) =>
      innerDocs.flatMap(render(_, currentWidth, currentIndent, atLineStart)).toVector
  }

  render(doc, 0, "", atLineStart = true)
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

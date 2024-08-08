package chester.petty.doc

import fansi._
import scala.annotation.tailrec

sealed trait Doc {
  def <> (other: Doc): Doc = concat(this, other)
  def <+> (other: Doc): Doc = concat(this, text(" "), other)
  def </> (other: Doc): Doc = concat(this, line(text(" ")), other)
  def <\> (other: Doc): Doc = concat(this, line(text("")), other)
}

case class Text(content: String) extends Doc {
  require(!content.contains("\n") && !content.contains("\r"), "Text cannot contain newlines or carriage returns")
  override def toString: String = content
}

case class Colored(doc: Doc, color: Attr) extends Doc

case class Concat(docs: Seq[Doc]) extends Doc {
  require(docs.nonEmpty, "Concat requires at least one document")
}

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
case class TokenColor(tokens: Vector[Token], color: Attr) extends Token

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

def colored(doc: Doc, color: Attr): Doc = Colored(doc, color)

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

trait CharCounter {
  def countChar: Char => Int
  def countString: String => Int = _.foldLeft(0)((acc, c) => acc + countChar(c))
}

object DefaultCharCounter extends CharCounter {
  override val countChar: Char => Int = _ => 1
}

trait TokenRenderer[T] {
  def render(tokens: Vector[Token], useCRLF: Boolean = false): T
  def charCounter: CharCounter = DefaultCharCounter
}

implicit object StringTokenRenderer extends TokenRenderer[String] {
  override def render(tokens: Vector[Token], useCRLF: Boolean = false): String = {
    val newline = if (useCRLF) "\r\n" else "\n"
    tokens.map {
      case TokenText(content) => content
      case TokenNewLine => newline
      case TokenColor(tokens, color) => tokens.map {
        case TokenText(content) => color(content).render
        case TokenNewLine => newline
        case TokenColor(innerTokens, innerColor) => innerColor(innerTokens.map {
          case TokenText(content) => content
          case TokenNewLine => newline
          case TokenColor(innerInnerTokens, innerInnerColor) => innerInnerColor(innerInnerTokens.map {
            case TokenText(content) => content
            case TokenNewLine => newline
            case TokenColor(_, _) => "" // This would rarely be the case, you might want to throw an exception here
          }.mkString).render
        }.mkString).render
      }.mkString
    }.mkString
  }
}

implicit object FansiTokenRenderer extends TokenRenderer[fansi.Str] {
  override def render(tokens: Vector[Token], useCRLF: Boolean = false): fansi.Str = {
    val newline = if (useCRLF) "\r\n" else "\n"
    tokens.foldLeft(fansi.Str("")) {
      case (acc, TokenText(content)) => acc ++ Str(content)
      case (acc, TokenNewLine) => acc ++ Str(newline)
      case (acc, TokenColor(innerTokens, color)) =>
        acc ++ innerTokens.foldLeft(fansi.Str("")) {
          case (innerAcc, TokenText(content)) => innerAcc ++ color(content)
          case (innerAcc, TokenNewLine) => innerAcc ++ color(newline)
          case (innerAcc, TokenColor(innerInnerTokens, innerColor)) =>
            innerAcc ++ innerInnerTokens.foldLeft(fansi.Str("")) {
              case (innermostAcc, TokenText(content)) => innermostAcc ++ innerColor(content)
              case (innermostAcc, TokenNewLine) => innermostAcc ++ innerColor(newline)
              case (innermostAcc, TokenColor(_, _)) => innermostAcc // This would rarely be the case, you might want to throw an exception here
            }
        }
    }
  }
}

def render[T](doc: Doc, maxWidth: Int, useCRLF: Boolean = false)(implicit tokenRenderer: TokenRenderer[T]): T = {
  val tokens = renderTokens(doc, maxWidth, tokenRenderer.charCounter)
  tokenRenderer.render(tokens, useCRLF)
}

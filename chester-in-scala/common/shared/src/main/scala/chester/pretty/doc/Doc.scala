package chester.pretty.doc

import chester.pretty

import scala.annotation.tailrec
import scala.language.implicitConversions

object Doc {

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
    if (parts.isEmpty) Text("") else concat(parts *)
  }
  
  val empty: Doc = ""

  def concat(docs: ToDoc*): Doc = if (docs.isEmpty) Text("") else if (docs.length == 1) docs.head.toDoc else Concat(docs.map(_.toDoc))

  def colored(doc: ToDoc, color: Attribute): Doc = Colored(doc.toDoc, color)

  def line(repl: ToDoc): Doc = Line(repl.toDoc)

  def linebreak: Doc = line(text(""))

  def group(doc: ToDoc): Doc = Group(doc.toDoc)

  def indented(indent: Indent, innerDoc: ToDoc): Doc = Indented(indent, innerDoc.toDoc)

  def wrapperlist(begin: ToDoc, end: ToDoc, sep: ToDoc = ",")(docs: ToDoc*)(implicit options: PrettierOptions): Doc = {
    docs match {
      case Seq() => begin.toDoc <> end.toDoc
      case Seq(head) => begin.toDoc <> head.toDoc <> end.toDoc
      case Seq(head, tail) => begin.toDoc <> head.toDoc <> sep.toDoc <> tail.toDoc <> end.toDoc
      case Seq(head, tail*) =>
        val init = head.toDoc <> sep.toDoc
        val last = tail.foldRight(end) { (doc, acc) => doc.toDoc <> sep.toDoc <> acc.toDoc }
        begin.toDoc <> init <> last.toDoc
    }
  }
}

trait PrettierOptionsKey[T]

implicit class PrettierOptions(options: scala.collection.Map[PrettierOptionsKey[?], Any]) {
  def get[T](key: PrettierOptionsKey[T]): Option[T] = options.get(key).map(_.asInstanceOf[T])

  def getOrElse[T](key: PrettierOptionsKey[T], default: T): T = get(key).getOrElse(default)

  def updated[T](key: PrettierOptionsKey[T], value: T): PrettierOptions = options.updated(key, value)
}

case class PrettierKeyValue[T](key: PrettierOptionsKey[T], value: T)

implicit def tuple2PrettyKeyValue[T](tuple: (PrettierOptionsKey[T], T)): PrettierKeyValue[T] = PrettierKeyValue(tuple._1, tuple._2)
implicit def prettyKeyValue2Tuple[T](kv: PrettierKeyValue[T]): (PrettierOptionsKey[T], T) = (kv.key, kv.value)

object PrettierOptions {
  def apply(options: PrettierKeyValue[?]*): PrettierOptions = options.map(prettyKeyValue2Tuple).toMap
}

trait ToDoc {
  def toDoc(implicit options: PrettierOptions = Map()): Doc
}

import chester.pretty.doc.Doc.*

extension (d: ToDoc) {
  def <>(other: ToDoc): Doc = concat(d, other.toDoc)
  @deprecated("Use </> or <\\> instead")
  def <+>(other: ToDoc): Doc = concat(d, text(" "), other.toDoc)
  def </>(other: ToDoc): Doc = concat(d, line(text(" ")), other.toDoc)
  def <\>(other: ToDoc): Doc = concat(d, line(text("")), other.toDoc)
  def colored(color: Attribute): Doc = Doc.colored(d.toDoc, color)
}

sealed trait Doc extends ToDoc:

  override def toDoc(implicit options: PrettierOptions): Doc = this

case class Text(content: String) extends Doc:
  require(!content.contains("\n") && !content.contains("\r"), "Text cannot contain newlines or carriage returns")

  override def toString: String = content

case class Colored(doc: Doc, color: Attribute) extends Doc

case class Concat(docs: Seq[Doc]) extends Doc:
  require(docs.nonEmpty, "Concat requires at least one document")
  require(docs.length > 1, "Concat requires at least two documents")

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

case class TokenColor(tokens: Vector[Token], color: Attribute) extends Token

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
  case Indented(_, innerDoc) => measureWithinLine(innerDoc, charCounter, maxWidth)

  case _ =>
    None
}

private def flatten(doc: Doc): Vector[Doc] = doc match {
  case Concat(docs) => docs.toVector.flatMap(flatten)
  case other => Vector(other)
}

private def decideGroup(group: Doc, maxWidth: Int, charCounter: CharCounter): (Boolean, Vector[Doc]) = {
  val flat = flatten(group)
  val len = flat.map(d => measureWithinLine(d, charCounter, maxWidth))
  val fitsInOneLine = len.forall(_.isDefined) && len.map(_.get).sum <= maxWidth
  val content = if (fitsInOneLine) flat else flat.flatMap {
    case Line(_) => Vector(NewLine)
    case other => Vector(other)
  }
  (fitsInOneLine, content)
}

private def renderWithinLine(doc: Doc, charCounter: CharCounter, maxWidth: Int): Vector[Token] = doc match {
  case Text(content) =>
    Vector(TokenText(content))

  case Colored(innerDoc, color) =>
    val coloredTokens = renderWithinLine(innerDoc, charCounter, maxWidth)
    Vector(TokenColor(coloredTokens, color))

  case Concat(innerDocs) =>
    innerDocs.flatMap(renderWithinLine(_, charCounter, maxWidth)).toVector

  case Line(repl) =>
    renderWithinLine(repl, charCounter, maxWidth)

  case Group(innerDoc) =>
    renderWithinLine(innerDoc, charCounter, maxWidth)

  case Indented(indent, innerDoc) => renderWithinLine(innerDoc, charCounter, maxWidth)
  case _ =>
    throw new UnsupportedOperationException("This doc type should not be rendered within a line")
}

private def splitDocByNewLine(docs: Seq[Doc]): Vector[Vector[Doc]] = {
  @tailrec
  def loop(remaining: List[Doc], current: Vector[Doc], acc: Vector[Vector[Doc]]): Vector[Vector[Doc]] = remaining match {
    case Nil =>
      if (current.nonEmpty) acc :+ current else acc
    case NewLine :: tail =>
      val updatedAcc = acc :+ current
      loop(tail, Vector.empty, updatedAcc)
    case head :: tail =>
      loop(tail, current :+ head, acc)
  }

  loop(docs.toList, Vector.empty, Vector.empty)
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
        renderWithinLine(repl, charCounter, maxWidth)
      case _ =>
        Vector(TokenNewLine)
    }

  case Group(innerDoc) =>
    val (fitsInOneLine, content) = decideGroup(innerDoc, maxWidth, charCounter)
    if (fitsInOneLine) {
      TokenText(currentIndent) +: content.flatMap(renderWithinLine(_, charCounter, maxWidth))
    } else {
      renderFromLineStartDocs(content, currentIndent, charCounter, maxWidth)
    }

  case Indented(indent, innerDoc) =>
    val indentStr = indent match {
      case Indent.Spaces(count) => " " * count
      case Indent.Tab => "\t"
    }
    renderFromLineStart(innerDoc, currentIndent + indentStr, charCounter, maxWidth)
  case c: Concat => renderFromLineStart(Group(c), currentIndent, charCounter, maxWidth)
  case _ =>
    throw new UnsupportedOperationException("This doc type should not be rendered from the line start")
}

private def renderFromLineStartDocs(docs: Seq[Doc], currentIndent: String, charCounter: CharCounter, maxWidth: Int): Vector[Token] =
  if (docs.exists(_.isInstanceOf[NewLine.type])) {
    val splitContent = splitDocByNewLine(docs)
    splitContent.init.flatMap(line => renderFromLineStartDocs(line, currentIndent, charCounter, maxWidth) :+ TokenNewLine) ++
      renderFromLineStartDocs(splitContent.last, currentIndent, charCounter, maxWidth)
  }
  else
    renderFromLineStart(docs.head, currentIndent, charCounter, maxWidth) ++ docs.tail.flatMap(renderWithinLine(_, charCounter, maxWidth)).toVector

private def renderTokens(doc: Doc, maxWidth: Int, charCounter: CharCounter): Vector[Token] = {
  renderFromLineStart(doc, "", charCounter, maxWidth)
}

import chester.utils.getCodePoints

trait CharCounter:
  def countCodePoint: Int => Int

  def countString: String => Int = _.getCodePoints.foldLeft(0)((acc, cp) => acc + countCodePoint(cp))

object DefaultCharCounter extends CharCounter:
  override val countCodePoint: Int => Int = _ => 1

abstract class Renderer[T]:
  def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): T

  def charCounter: CharCounter = DefaultCharCounter

  def render(doc: ToDoc, maxWidth: Int, useCRLF: Boolean = false): T =
    val tokens = pretty.doc.renderTokens(doc.toDoc, maxWidth, charCounter)
    renderTokens(tokens, useCRLF)

def render[T](doc: ToDoc, maxWidth: Int = Integer.MAX_VALUE, useCRLF: Boolean = false)(implicit renderer: Renderer[T]): T =
  renderer.render(doc.toDoc, maxWidth, useCRLF)

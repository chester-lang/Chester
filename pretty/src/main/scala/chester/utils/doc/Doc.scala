package chester.utils.doc

import kiama2.output.*
import kiama2.output.PrettyPrinterTypes.Width

import scala.annotation.targetName
import scala.language.implicitConversions

type DocPrinter = ParenPrettyPrinter & StylePrettyPrinter

implicit object StringPrinter extends StringPrettyPrinter with ParenPrettyPrinter with StylePrettyPrinter {

}

sealed trait Doc extends ToDoc {
  final inline implicit def getDoc(using printer: DocPrinter): printer.Doc = printer.toParenDoc(printToExpr)

  def printToExpr(using printer: DocPrinter): printer.Expr

  final inline def toDoc(using options: PrettierOptions): Doc = this

  def descent(f: Doc => Doc): Doc = this

  def styled(style: Style): Doc = descent(_.styled(style))
}

implicit inline def textFrom(inline s: String): Doc = text(s)

inline def text(inline s: String, inline style: Style = Style.Empty): Doc = Text(s, style)

case class Text(s: String, style: Style = Style.Empty) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = printer.text(s, style)

  override def styled(style: Style): Doc = copy(style = this.style ++ style)
}

inline def group(inline doc: Doc): Doc = Group(doc)

case class Group(doc: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = printer.group(doc.getDoc)

  override def descent(f: Doc => Doc): Doc = copy(doc = f(doc))
}

val maxWidth = Integer.MAX_VALUE
export PrettyPrinter.defaultWidth

def renderToDocument(doc: Doc, w: Width = maxWidth)(using printer: DocPrinter): printer.Document = printer.pretty(printer.toParenDoc(doc.printToExpr), w)

private def render0(doc: Doc, w: Width = maxWidth)(using printer: DocPrinter): printer.Layout = renderToDocument(doc, w).layout
def render(doc: Doc, w: Width = maxWidth)(using printer: DocPrinter): printer.Layout = render0(doc, w)
def render(doc: ToDoc)(using options: PrettierOptions, printer: DocPrinter): printer.Layout = render0(doc.toDoc)
def render(doc: ToDoc, w: Width)(using options: PrettierOptions, printer: DocPrinter): printer.Layout = render0(doc.toDoc, w)

// TODO: this is broken, please fix
def wrapperlist(begin: ToDoc, end: ToDoc, sep: ToDoc = ",")(docs: ToDoc*)(using options: PrettierOptions): Doc = group {
  docs.toList match {
    case Nil =>
      begin.toDoc <> end.toDoc
    case head :: Nil =>
      begin.toDoc <> head.toDoc <> end.toDoc
    case head :: tail =>
      val content = tail.foldLeft(head.toDoc) { (acc, doc) =>
        acc <> sep.toDoc </> doc.toDoc
      }
      begin.toDoc <> content <> end.toDoc
  }
}

def concat(docs: ToDoc*)(using options: PrettierOptions): Doc = group {
  docs.foldLeft(Doc.empty) { (acc, doc) => acc <> doc.toDoc }
}

val empty = text("")
val hardline = text("\n") // TODO: CRLF?

object Doc {
  def indented(doc: ToDoc)(using options: PrettierOptions): Doc = doc.indented()

  export chester.utils.doc.{renderToDocument, render, text, group, wrapperlist, empty, concat, hardline}
}

implicit class DocOps(doc: Doc) extends AnyVal {
  def renderToDocument(w: Width = maxWidth)(using printer: DocPrinter): printer.Document = Doc.renderToDocument(doc, w)

  def render(w: Width = maxWidth)(using printer: DocPrinter): printer.Layout = Doc.render(doc, w)
}

implicit class DocPrinterOps[T <: DocPrinter](val printer: T) extends AnyVal {
  def render(doc: Doc, w: Width = maxWidth): printer.Layout = doc.render(w)(using printer)

  def render(doc: ToDoc)(using options: PrettierOptions): printer.Layout = Doc.render(doc)(using options, printer)

  def render(doc: ToDoc, w: Width)(using options: PrettierOptions): printer.Layout = Doc.render(doc, w)(using options, printer)

  def renderToDocument(doc: Doc, w: Width = maxWidth): printer.Document = doc.renderToDocument(w)(using printer)
}

trait ToDoc extends Any {
  def toDoc(using options: PrettierOptions): Doc
}

case class `$<>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$<+>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <+> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$</>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc </> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$<\\>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <\> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$<@>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <@> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$<@@>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <@@> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$<%>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <%> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

case class `$<%%>`(left: Doc, right: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = left.getDoc <%%> right.getDoc

  override def descent(f: Doc => Doc): Doc = copy(left = f(left), right = f(right))
}

// TODO: add custom indent
case class $indent(doc: Doc) extends Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr = printer.indent(doc.getDoc)

  override def descent(f: Doc => Doc): Doc = copy(doc = f(doc))
}

extension (self: ToDoc)(using options: PrettierOptions) {
  implicit inline def asDoc: Doc = self.toDoc
  /**
   * Return the concatenation of this document with the argument.
   */
  def <>(other: ToDoc): Doc = `$<>`(self, other)
  /**
   * Return the concatenation of this document with the argument
   * using a `space` separator.
   */
  def <+>(other: ToDoc): Doc = `$<+>`(self, other)
  def <+?>[A <: ToDoc](tuple: (Boolean, A)): Doc = <+?>[A](tuple._1, tuple._2)
  @targetName("plusQMark")
  def <+?>[A <: ToDoc](tuple: (A => Boolean, A)): Doc = <+?>[A](tuple._1, tuple._2)
  def <+?>[A <: ToDoc](pred: Boolean, other: A): Doc = if pred then self <+> other else self
  def <+?>[A <: ToDoc](pred: A => Boolean, other: A): Doc = if pred(other) then self <+> other else self
  /**
   * Return the concatenation of this document with the argument
   * using a `softline` separator.
   */
  def </>(other: ToDoc): Doc = `$</>`(self, other)
  /**
   * Return the concatenation of this document with the argument
   * using a `softbreak` separator.
   */
  def <\>(other: ToDoc): Doc = `$<\\>`(self, other)
  /**
   * Return the concatenation of this document with the argument
   * using a `line` separator.
   */
  def <@>(other: ToDoc): Doc = `$<@>`(self, other)
  /**
   * Return the concatenation of this document with the argument
   * using a `linebreak` separator.
   */
  def <@@>(other: ToDoc): Doc = `$<@@>`(self, other)
  /**
   * Align the argument below this document using a `line` separator.
   */
  def <%>(other: ToDoc): Doc = `$<%>`(self, other)
  /**
   * Align the argument below this document using a `linebreak` separator.
   */
  def <%%>(other: ToDoc): Doc = `$<%%>`(self, other)
  def end: Doc = self <> hardline
  def <|>(other: ToDoc): Doc = self <> hardline <> other
  // TODO: add custom indent
  def indented(): Doc = $indent(self)
}

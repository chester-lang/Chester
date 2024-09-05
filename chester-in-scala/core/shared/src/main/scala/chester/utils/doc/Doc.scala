package chester.utils.doc

import kiama.output.*
import kiama.output.PrettyPrinter.defaultWidth
import kiama.output.PrettyPrinterTypes.Width

import scala.language.implicitConversions

type DocPrinter = ParenPrettyPrinter & StylePrettyPrinter

object StringPrinter extends StringPrettyPrinter with ParenPrettyPrinter with StylePrettyPrinter {

}

trait Doc {
  def printToExpr(using printer: DocPrinter): printer.Expr
}

object Doc {
  def renderToDocument(doc: Doc, w: Width = defaultWidth)(using printer: DocPrinter): printer.Document = printer.pretty(printer.toParenDoc(doc.printToExpr), w)

  def render(doc: Doc, w: Width = defaultWidth)(using printer: DocPrinter): printer.Layout = renderToDocument(doc, w).layout
}

extension (doc: Doc) {
  def renderToDocument(w: Width = defaultWidth)(using printer: DocPrinter): printer.Document = Doc.renderToDocument(doc, w)
  def render(w: Width = defaultWidth)(using printer: DocPrinter): printer.Layout = Doc.render(doc, w)
}

implicit class DocPrinterOps(val printer: DocPrinter) {
  def render(doc: Doc, w: Width = defaultWidth): printer.Layout = doc.render(w)(using printer)

  def renderToDocument(doc: Doc, w: Width = defaultWidth): printer.Document = doc.renderToDocument(w)(using printer)
}

trait ToDoc {
  def toDoc(using options: PrettierOptions): Doc
}

extension (self: ToDoc)(using options: PrettierOptions) {
  inline implicit def getDoc(using printer: DocPrinter): printer.Doc = printer.toParenDoc(self.toDoc.printToExpr)
  /**
   * Return the concatenation of this document with the argument.
   */
  def <>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `space` separator.
   */
  def <+>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <+> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `softline` separator.
   */
  def </>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc </> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `softbreak` separator.
   */
  def <\>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <\> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `line` separator.
   */
  def <@>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <@> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `linebreak` separator.
   */
  def <@@>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <@@> other
  }
  /**
   * Align the argument below this document using a `line` separator.
   */
  def <%>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <%> other
  }
  /**
   * Align the argument below this document using a `linebreak` separator.
   */
  def <%%>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc <%%> other
  }
  def styled(style: Style): Doc = new Doc {
    def printToExpr(using printer: DocPrinter): printer.Expr = getDoc.styled(style)
  }
}

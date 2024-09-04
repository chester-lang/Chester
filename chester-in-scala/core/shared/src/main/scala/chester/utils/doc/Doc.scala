package chester.utils.doc

import kiama.output._

import scala.language.implicitConversions

trait Doc {
  def printToExpr(using printer: ParenPrettyPrinter): printer.Expr
}

trait ToDoc {
  def toDoc(using options: PrettierOptions): Doc
}

extension (self: ToDoc)(using options: PrettierOptions) {
  inline implicit def getDoc(using printer: ParenPrettyPrinter): printer.Doc = printer.toParenDoc(self.toDoc.printToExpr)
  /**
   * Return the concatenation of this document with the argument.
   */
  def <>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `space` separator.
   */
  def <+>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <+> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `softline` separator.
   */
  def </>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc </> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `softbreak` separator.
   */
  def <\>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <\> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `line` separator.
   */
  def <@>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <@> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `linebreak` separator.
   */
  def <@@>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <@@> other
  }
  /**
   * Align the argument below this document using a `line` separator.
   */
  def <%>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <%> other
  }
  /**
   * Align the argument below this document using a `linebreak` separator.
   */
  def <%%>(other: ToDoc): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <%%> other
  }
}

object Doc {
}
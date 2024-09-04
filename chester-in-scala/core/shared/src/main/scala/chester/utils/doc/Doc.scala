package chester.utils.doc

import chester.doc.PrettierOptions
import kiama.output._

import scala.language.implicitConversions

trait Doc {
  def printToExpr(using printer: ParenPrettyPrinter): printer.Expr
}

trait Printable {
  def print(using options: PrettierOptions): Doc
}

extension (self: Printable)(using options: PrettierOptions) {
  inline implicit def getDoc(using printer: ParenPrettyPrinter): printer.Doc = printer.toParenDoc(self.print.printToExpr)
  /**
   * Return the concatenation of this document with the argument.
   */
  def <>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `space` separator.
   */
  def <+>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <+> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `softline` separator.
   */
  def </>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc </> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `softbreak` separator.
   */
  def <\>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <\> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `line` separator.
   */
  def <@>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <@> other
  }
  /**
   * Return the concatenation of this document with the argument
   * using a `linebreak` separator.
   */
  def <@@>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <@@> other
  }
  /**
   * Align the argument below this document using a `line` separator.
   */
  def <%>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <%> other
  }
  /**
   * Align the argument below this document using a `linebreak` separator.
   */
  def <%%>(other: Printable): Doc = new Doc {
    def printToExpr(using printer: ParenPrettyPrinter): printer.Expr = getDoc <%%> other
  }
}

object Doc {
}
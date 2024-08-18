// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.string

import kala.collection.Map
import kala.tuple.Tuple
import org.aya.pretty.doc.Doc
import org.aya.pretty.printer.Printer
import org.aya.pretty.printer.PrinterConfig
import org.jetbrains.annotations.NotNull
import java.util
import java.util.function.IntFunction
import org.aya.pretty.backend.string.StringPrinterConfig.TextOptions.Unicode

/**
 * The class for all string-output printers.
 *
 * @author kiva
 */
object StringPrinter {
  /** renderer: where am I? */
  object Outer extends Enumeration {
    type Outer = Value
    val Code, Math, EnclosingTag, List = Value
  }

  @NotNull val FREE: util.EnumSet[StringPrinter.Outer] = util.EnumSet.noneOf(classOf[StringPrinter.Outer])
  @NotNull private val unicodeMapping = Map.ofEntries(Tuple.of("/\\", "∧"), Tuple.of("\\/", "∨"), Tuple.of("=>", "⇒"), Tuple.of("ulift", "↑"), Tuple.of("forall", "∀"), Tuple.of("->", "→"), Tuple.of("_|_", "⊥"), Tuple.of("top", "⊤"), Tuple.of("(|", "⦇"), Tuple.of("|)", "⦈"), Tuple.of("{|", "⦃"), Tuple.of("|}", "⦄"), Tuple.of("[|", "⟦"), Tuple.of("|]", "⟧"))
}

class StringPrinter[Config <: StringPrinterConfig[_$1]] extends Nothing {
  protected var config: Config = null

  @NotNull protected def makeIndent(indent: Int): String = " ".repeat(indent)

  @NotNull def render(@NotNull config: Config, @NotNull doc: Doc): String = {
    this.config = config
    val cursor = new Cursor(this)
    renderHeader(cursor)
    renderDoc(cursor, doc, StringPrinter.FREE)
    renderFooter(cursor)
    cursor.result.toString
  }

  private def lineRemaining(@NotNull cursor: Cursor) = {
    val pw = config.getPageWidth
    if (pw == PrinterConfig.INFINITE_SIZE) pw
    else pw - cursor.getCursor
  }

  protected def predictWidth(@NotNull cursor: Cursor, @NotNull doc: Doc): Int = doc match {
    case Doc.Empty _| Doc.Line _ => 0
    case Doc.PlainText( var text) => text.length
    case Doc.EscapedText( var text) => text.length
    case Doc.SpecialSymbol( var text) => text.length
    case Doc.HyperLinked text => predictWidth(cursor, text.doc)
    case Doc.Image i => predictWidth(cursor, i.alt)
    case Doc.Styled styled => predictWidth(cursor, styled.doc)
    case Doc.Tooltip tooltip => predictWidth(cursor, tooltip.doc)
    case Doc.FlatAlt alt => predictWidth(cursor, alt.defaultDoc)
    case Doc.Cat cat => cat.inner.view.map((inner) => predictWidth(cursor, inner)).reduce(Integer.sum)
    case Doc.Nest nest => predictWidth(cursor, nest.doc) + nest.indent
    case Doc.Union union => predictWidth(cursor, union.longerOne)
    case Doc.Column column => predictWidth(cursor, column.docBuilder.apply(cursor.getCursor))
    case Doc.Nesting nesting => predictWidth(cursor, nesting.docBuilder.apply(cursor.getNestLevel))
    case Doc.PageWidth pageWidth => predictWidth(cursor, pageWidth.docBuilder.apply(config.getPageWidth))
    case Doc.CodeBlock codeBlock => predictWidth(cursor, codeBlock.code)
    case Doc.InlineCode inlineCode => predictWidth(cursor, inlineCode.code)
    case Doc.InlineMath inlineMath => predictWidth(cursor, inlineMath.formula)
    case Doc.MathBlock mathBlock => predictWidth(cursor, mathBlock.formula)
    case Doc.List list => list.items.view.map((x) => predictWidth(cursor, x)).reduce(Integer.sum)
  }

  @NotNull protected def fitsBetter(@NotNull cursor: Cursor, @NotNull a: Doc, @NotNull b: Doc): Doc = {
    if (cursor.isAtLineStart) return a
    val lineRem = lineRemaining(cursor)
    if (lineRem == PrinterConfig.INFINITE_SIZE || predictWidth(cursor, a) <= lineRem) a
    else b
  }

  protected def renderHeader(@NotNull cursor: Cursor): Unit = {
  }

  protected def renderFooter(@NotNull cursor: Cursor): Unit = {
  }

  protected def renderDoc(@NotNull cursor: Cursor, @NotNull doc: Doc, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    doc match {
      case Doc.PlainText( var text) => renderPlainText (cursor, text, outer)
      case Doc.EscapedText( var text) => cursor.visibleContent (text)
      case Doc.SpecialSymbol( var symbol) => renderSpecialSymbol (cursor, symbol, outer)
      case Doc.HyperLinked text => renderHyperLinked(cursor, text, outer)
      case Doc.Image image => renderImage(cursor, image, outer)
      case Doc.Styled styled => renderStyled(cursor, styled, outer)
      case Doc.Line _ => renderHardLineBreak (cursor, outer)
      case Doc.FlatAlt alt => renderFlatAlt(cursor, alt, outer)
      case Doc.Cat cat => cat.inner.forEach((inner) => renderDoc(cursor, inner, outer))
      case Doc.Nest nest => renderNest(cursor, nest, outer)
      case Doc.Union union => renderUnionDoc(cursor, union, outer)
      case Doc.Column column => renderDoc(cursor, column.docBuilder.apply(cursor.getCursor), outer)
      case Doc.Nesting nesting => renderDoc(cursor, nesting.docBuilder.apply(cursor.getNestLevel), outer)
      case Doc.PageWidth pageWidth => renderDoc(cursor, pageWidth.docBuilder.apply(config.getPageWidth), outer)
      case Doc.CodeBlock codeBlock => renderCodeBlock(cursor, codeBlock, outer)
      case Doc.InlineCode inlineCode => renderInlineCode(cursor, inlineCode, outer)
      case Doc.List list => renderList(cursor, list, outer)
      case Doc.InlineMath inlineMath => renderInlineMath(cursor, inlineMath, outer)
      case Doc.MathBlock mathBlock => renderMathBlock(cursor, mathBlock, outer)
      case Doc.Tooltip tooltip => renderTooltip(cursor, tooltip, outer)
      case Doc.Empty _ =>
    }
  }

  protected def renderSpecialSymbol(@NotNull cursor: Cursor, @NotNull text: String, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    if (config.opt(Unicode, false)) {
      import scala.collection.JavaConversions._
      for (k <- StringPrinter.unicodeMapping.keysView) {
        if (text.trim == k) {
          cursor.visibleContent(text.replace(k, StringPrinter.unicodeMapping.get(k)))
          return
        }
      }
    }
    renderPlainText(cursor, text, outer)
  }

  protected def renderNest(@NotNull cursor: Cursor, @NotNull nest: Doc.Nest, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    cursor.nested(nest.indent, () => renderDoc(cursor, nest.doc, outer))
  }

  protected def renderUnionDoc(@NotNull cursor: Cursor, @NotNull union: Doc.Union, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderDoc(cursor, fitsBetter(cursor, union.shorterOne, union.longerOne), outer)
  }

  protected def renderFlatAlt(@NotNull cursor: Cursor, @NotNull alt: Doc.FlatAlt, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderDoc(cursor, fitsBetter(cursor, alt.defaultDoc, alt.preferWhenFlatten), outer)
  }

  protected def renderHyperLinked(@NotNull cursor: Cursor, @NotNull text: Doc.HyperLinked, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderDoc(cursor, text.doc, outer)
  }

  protected def renderImage(@NotNull cursor: Cursor, @NotNull image: Doc.Image, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderDoc(cursor, image.alt, outer)
  }

  protected def renderStyled(@NotNull cursor: Cursor, @NotNull styled: Doc.Styled, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    val stylist = prepareStylist
    stylist.format(styled.styles, cursor, outer, () => renderDoc(cursor, styled.doc, outer))
  }

  @NotNull protected def prepareStylist: Nothing = config.getStylist

  protected def renderPlainText(@NotNull cursor: Cursor, @NotNull content: String, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    cursor.visibleContent(escapePlainText(content, outer))
  }

  @NotNull protected def escapePlainText(@NotNull content: String, outer: util.EnumSet[StringPrinter.Outer]): String = content

  /**
   * This line break makes target source code beautiful (like .tex or .md generated from Doc).
   * It is not printed in the resulting document (like .pdf generated from .tex or .md),
   * since it only separates different block elements (list, code block, etc.) in generated source files.
   * <p>
   * The default implementation is to print a single `\n` character and move to new line.
   *
   * @apiNote This is called by {@link # renderCodeBlock}, {@link # renderMathBlock}, {@link # formatList},
   *          and other block rendering methods to separate the current block from the previous one.
   */
  protected def renderBlockSeparator(@NotNull cursor: Cursor, ignoredOuter: util.EnumSet[StringPrinter.Outer]): Unit = {
    cursor.lineBreakWith("\n")
  }

  /**
   * This line break is printed in the resulting document (like .pdf generated from .tex or .md).
   * The default implementation is same as {@link # renderBlockSeparator}.
   * Backends may override this method if the source code line break is different from
   * the printed line break, (like LaTeX use '\\' for new line in the printed document).
   */
  protected def renderHardLineBreak(@NotNull cursor: Cursor, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderBlockSeparator(cursor, outer)
  }

  protected def renderTooltip(@NotNull cursor: Cursor, @NotNull tooltip: Doc.Tooltip, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderDoc(cursor, tooltip.doc, outer)
  }

  protected def renderCodeBlock(@NotNull cursor: Cursor, @NotNull block: Doc.CodeBlock, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    separateBlockIfNeeded(cursor, outer)
    formatBlock(cursor, block.code, "", "", util.EnumSet.of(StringPrinter.Outer.Code))
  }

  protected def renderInlineCode(@NotNull cursor: Cursor, @NotNull code: Doc.InlineCode, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    // do not use `formatInline` here, because the backtick (`) should be visible in plain string,
    // while `formatInline` treats them as invisible.
    cursor.visibleContent("`")
    renderDoc(cursor, code.code, util.EnumSet.of(StringPrinter.Outer.Code))
    cursor.visibleContent("`")
  }

  protected def renderMathBlock(@NotNull cursor: Cursor, @NotNull block: Doc.MathBlock, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    separateBlockIfNeeded(cursor, outer)
    formatBlock(cursor, block.formula, "", "", util.EnumSet.of(StringPrinter.Outer.Math))
  }

  protected def renderInlineMath(@NotNull cursor: Cursor, @NotNull code: Doc.InlineMath, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    renderDoc(cursor, code.formula, util.EnumSet.of(StringPrinter.Outer.Math))
  }

  protected def renderList(@NotNull cursor: Cursor, @NotNull list: Doc.List, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    formatList(cursor, list, outer)
  }

  protected def formatList(@NotNull cursor: Cursor, @NotNull list: Doc.List, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    formatList(cursor, list, (idx: Int) => if (list.isOrdered) (idx + 1) + "."
    else "-", outer)
  }

  protected def formatList(@NotNull cursor: Cursor, @NotNull list: Doc.List, @NotNull itemBegin: IntFunction[String], outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    // Move to new line if needed, in case the list begins at the end of the previous doc.
    separateBlockIfNeeded(cursor, outer)
    // The items should be placed one by one, each at the beginning of a line.
    val items = Doc.vcat(list.items.mapIndexed((idx, item) => {
      // The beginning mark
      val pre = itemBegin.apply(idx)
      // The item content
      val content = Doc.align(item)
      Doc.stickySep(Doc.escaped(pre), content)
    }))
    renderDoc(cursor, items, util.EnumSet.of(StringPrinter.Outer.List))
    // Top level list should have a line after it, or the following content will be treated as list item.
    if (!outer.contains(StringPrinter.Outer.List)) {
      separateBlockIfNeeded(cursor, outer)
      renderBlockSeparator(cursor, outer)
    }
  }

  /** renderBlockSeparator if not line start */
  protected def separateBlockIfNeeded(@NotNull cursor: Cursor, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    cursor.whenLineUsed(() => renderBlockSeparator(cursor, outer))
  }

  protected def formatBlock(@NotNull cursor: Cursor, @NotNull doc: Doc, @NotNull begin: String, @NotNull end: String, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    formatBlock(cursor, begin, end, outer, () => renderDoc(cursor, doc, outer))
  }

  /**
   * Render the resulting document as:
   * <pre>
   * begin\n
   * inside()\n
   * end\n
   * </pre>
   */
  protected def formatBlock(@NotNull cursor: Cursor, @NotNull begin: String, @NotNull end: String, outer: util.EnumSet[StringPrinter.Outer], @NotNull inside: Runnable): Unit = {
    cursor.invisibleContent(begin)
    renderBlockSeparator(cursor, outer)
    inside.run()
    renderBlockSeparator(cursor, outer)
    cursor.invisibleContent(end)
    renderBlockSeparator(cursor, outer)
  }

  /**
   * Render the resulting document as:
   * <pre>
   * begin·doc·end
   * </pre>
   */
  protected def formatInline(@NotNull cursor: Cursor, @NotNull doc: Doc, @NotNull begin: String, @NotNull end: String, outer: util.EnumSet[StringPrinter.Outer]): Unit = {
    cursor.invisibleContent(begin)
    renderDoc(cursor, doc, outer)
    cursor.invisibleContent(end)
  }
}
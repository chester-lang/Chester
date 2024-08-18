// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.doc

import kala.collection.Seq
import kala.collection.SeqLike
import kala.collection.immutable.ImmutableSeq
import kala.collection.mutable.MutableList
import org.aya.pretty.backend.html.DocHtmlPrinter
import org.aya.pretty.backend.html.Html5Stylist
import org.aya.pretty.backend.latex.DocTeXPrinter
import org.aya.pretty.backend.md.DocMdPrinter
import org.aya.pretty.backend.md.MdStylist
import org.aya.pretty.backend.string.DebugStylist
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.backend.string.StringPrinterConfig
import org.aya.pretty.backend.terminal.AdaptiveCliStylist
import org.aya.pretty.backend.terminal.DocTermPrinter
import org.aya.pretty.printer.Printer
import org.aya.pretty.printer.PrinterConfig
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.function.IntFunction
import java.util.function.Supplier
import org.aya.pretty.printer.PrinterConfig.INFINITE_SIZE

/**
 * This class reimplemented Haskell
 * <a href="https://hackage.haskell.org/package/prettyprinter-1.7.0/docs/src/Prettyprinter.Internal.html">
 * PrettyPrint library's Doc module</a>.
 *
 * @author kiva
 */
object Doc {
  @NotNull val ONE_WS: Doc = plain(" ")
  @NotNull val ALT_WS: Doc = flatAlt(ONE_WS, line)
  @NotNull val COMMA: Doc = cat(plain(","), ALT_WS)

  /** The empty document; conceptually the unit of 'Cat' */
  //endregion
  //region Doc Variants
  final class Empty extends Doc {}

  /**
   * A plain text line without '\n'. which may be escaped by backend.
   */
  final class PlainText(@NotNull text: String) extends Doc {
    this.text = text
    @NotNull final private val text: String = null
  }

  /**
   * Already escaped text, which will not be escaped by backend.
   * Callers should be responsible for escaping the text (like '\n').
   */
  final class EscapedText(@NotNull text: String) extends Doc {
    this.text = text
    @NotNull final private val text: String = null
  }

  /**
   * A special symbol that may get rendered in a special way
   */
  final class SpecialSymbol(@NotNull text: String) extends Doc {
    this.text = text
    @NotNull final private val text: String = null
  }

  /**
   * A clickable text line without '\n'.
   *
   * @param id   The id of the doc itself.
   * @param href The id of jump target when clicked.
   */
  final class HyperLinked(@NotNull doc: Doc, @NotNull href: Nothing, @Nullable id: Nothing, @Nullable hover: String) extends Doc {
    this.doc = doc
    this.href = href
    this.id = id
    this.hover = hover
    @NotNull final private val doc: Doc = null
    @NotNull final private val href: Nothing = null
    @Nullable final private val id: Nothing = null
    @Nullable final private val hover: String = null
  }

  final class Image(@NotNull alt: Doc, @NotNull src: Nothing) extends Doc {
    this.alt = alt
    this.src = src
    @NotNull final private val alt: Doc = null
    @NotNull final private val src: Nothing = null
  }

  /** Inline code, with special escape settings compared to {@link PlainText} */
  final class InlineCode(@NotNull language: Nothing, @NotNull code: Doc) extends Doc {
    this.language = language
    this.code = code
    @NotNull final private val language: Nothing = null
    @NotNull final private val code: Doc = null
  }

  /** Code block, with special escape settings compared to {@link PlainText} */
  final class CodeBlock(@NotNull language: Nothing, @NotNull code: Doc) extends Doc {
    this.language = language
    this.code = code
    @NotNull final private val language: Nothing = null
    @NotNull final private val code: Doc = null
  }

  /** Inline math, with special escape settings compared to {@link PlainText} */
  final class InlineMath(@NotNull formula: Doc) extends Doc {
    this.formula = formula
    @NotNull final private val formula: Doc = null
  }

  /** Math block, with special escape settings compared to {@link PlainText} */
  final class MathBlock(@NotNull formula: Doc) extends Doc {
    this.formula = formula
    @NotNull final private val formula: Doc = null
  }

  /**
   * Styled document
   */
  final class Styled(@NotNull styles: Nothing, @NotNull doc: Doc) extends Doc {
    this.styles = styles
    this.doc = doc
    @NotNull final private val styles: Nothing = null
    @NotNull final private val doc: Doc = null
  }

  final class Tooltip(@NotNull doc: Doc, @NotNull tooltip: Nothing) extends Doc {
    this.doc = doc
    this.tooltip = tooltip
    @NotNull final private val doc: Doc = null
    @NotNull final private val tooltip: Nothing = null
  }

  /**
   * Hard line break
   */
  final class Line extends Doc {}

  /**
   * Lay out the defaultDoc 'Doc', but when flattened (via 'group'), prefer
   * the preferWhenFlatten.
   * The layout algorithms work under the assumption that the defaultDoc
   * alternative is less wide than the flattened preferWhenFlatten alternative.
   */
  final class FlatAlt(@NotNull defaultDoc: Doc, @NotNull preferWhenFlatten: Doc) extends Doc {
    this.defaultDoc = defaultDoc
    this.preferWhenFlatten = preferWhenFlatten
    @NotNull final private val defaultDoc: Doc = null
    @NotNull final private val preferWhenFlatten: Doc = null
  }

  /**
   * Concatenation of two documents
   */
  final class Cat(@NotNull inner: Nothing) extends Doc {
    this.inner = inner
    @NotNull final private val inner: Nothing = null
  }

  final class List(isOrdered: Boolean, @NotNull items: Nothing) extends Doc {
    this.isOrdered = isOrdered
    this.items = items
    final private val isOrdered = false
    @NotNull final private val items: Nothing = null
  }

  /**
   * Document indented by a number of columns
   */
  final class Nest(indent: Int, @NotNull doc: Doc) extends Doc {
    this.indent = indent
    this.doc = doc
    final private val indent = 0
    @NotNull final private val doc: Doc = null
  }

  /**
   * The first lines of first document should be shorter than the
   * first lines of the second one, so the layout algorithm can pick the one
   * that fits best. Used to implement layout alternatives for 'softline' and 'group'.
   */
  final class Union(@NotNull shorterOne: Doc, @NotNull longerOne: Doc) extends Doc {
    this.shorterOne = shorterOne
    this.longerOne = longerOne
    @NotNull final private val shorterOne: Doc = null
    @NotNull final private val longerOne: Doc = null
  }

  /**
   * A document that will react on the current cursor position.
   */
  final class Column(@NotNull docBuilder: IntFunction[Doc]) extends Doc {
    this.docBuilder = docBuilder
    @NotNull final private val docBuilder: IntFunction[Doc] = null
  }

  /**
   * A document that will react on the current nest level.
   */
  final class Nesting(@NotNull docBuilder: IntFunction[Doc]) extends Doc {
    this.docBuilder = docBuilder
    @NotNull final private val docBuilder: IntFunction[Doc] = null
  }

  /**
   * A document that will react on the page width.
   */
  final class PageWidth(@NotNull docBuilder: IntFunction[Doc]) extends Doc {
    this.docBuilder = docBuilder
    @NotNull final private val docBuilder: IntFunction[Doc] = null
  }

  //region DocFactory functions
  //endregion
  @NotNull def linkDef(@NotNull doc: Doc, @NotNull id: Nothing): Doc = linkDef(doc, id, null)

  @NotNull def linkRef(@NotNull doc: Doc, @NotNull href: Nothing): Doc = linkRef(doc, href, null)

  @NotNull def linkDef(@NotNull doc: Doc, @NotNull id: Nothing, @Nullable hover: String) = new Doc.HyperLinked(doc, id, id, hover)

  @NotNull def linkRef(@NotNull doc: Doc, @NotNull href: Nothing, @Nullable hover: String) = new Doc.HyperLinked(doc, href, null, hover)

  @NotNull def hyperLink(@NotNull doc: Doc, @NotNull href: Nothing, @Nullable hover: String) = new Doc.HyperLinked(doc, href, null, hover)

  @NotNull def hyperLink(@NotNull plain: String, @NotNull href: Nothing): Doc = hyperLink(plain(plain), href, null)

  @NotNull def image(@NotNull alt: Doc, @NotNull src: Nothing) = new Doc.Image(alt, src)

  @NotNull def code(@NotNull code: String): Doc = code(Language.Builtin.Aya, plain(code))

  @NotNull def code(@NotNull code: Doc): Doc = code(Language.Builtin.Aya, code)

  @NotNull def code(@NotNull language: Nothing, @NotNull code: Doc) = new Doc.InlineCode(language, code)

  @NotNull def codeBlock(@NotNull language: Nothing, @NotNull code: Doc) = new Doc.CodeBlock(language, code)

  @NotNull def codeBlock(@NotNull language: Nothing, @NotNull code: String): Doc = codeBlock(language, plain(code))

  @NotNull def math(@NotNull formula: Doc) = new Doc.InlineMath(formula)

  @NotNull def mathBlock(@NotNull formula: Doc) = new Doc.MathBlock(formula)

  @NotNull def styled(@NotNull style: Nothing, @NotNull doc: Doc) = new Doc.Styled(Seq.of(style), doc)

  @NotNull def styled(@NotNull style: Nothing, @NotNull plain: String) = new Doc.Styled(Seq.of(style), plain(plain))

  @NotNull def styled(@NotNull builder: Nothing, @NotNull doc: Doc) = new Doc.Styled(builder.styles, doc)

  @NotNull def styled(@NotNull builder: Nothing, @NotNull plain: String) = new Doc.Styled(builder.styles, plain(plain))

  @NotNull def licit(explicit: Boolean, doc: Doc): Doc = wrap(if (explicit) "("
  else "{", if (explicit) ")"
  else "}", doc)

  @NotNull def spaced(symbol: Doc): Doc = Doc.cat(Doc.ONE_WS, symbol, Doc.ONE_WS)

  @NotNull def wrap(@NotNull left: Doc, @NotNull right: Doc, @NotNull doc: Doc): Doc = cat(left, doc, right)

  @NotNull def wrap(leftSymbol: String, rightSymbol: String, doc: Doc): Doc = wrap(symbol(leftSymbol), symbol(rightSymbol), doc)

  @NotNull def spacedWrap(@NotNull left: Doc, @NotNull right: Doc, @NotNull doc: Doc): Doc = wrap(left, right, wrap(ONE_WS, ONE_WS, doc))

  @NotNull def spacedWrap(@NotNull leftSymbol: String, @NotNull rightSymbol: String, @NotNull doc: Doc): Doc = spacedWrap(symbol(leftSymbol), symbol(rightSymbol), doc)

  /** @param falsification when false, add braces */
  @NotNull def bracedUnless(doc: Doc, falsification: Boolean): Doc = if (falsification) doc
  else braced(doc)

  @NotNull def braced(doc: Doc): Doc = wrap("{", "}", doc)

  /**
   * Either `{ defaultDoc }` or `{\nflatDoc\n}`
   */
  @NotNull def flatAltBracedBlock(defaultDoc: Doc, flatDoc: Doc): Doc = flatAlt(stickySep(Doc.symbol("{"), defaultDoc, Doc.symbol("}")), vcat(Doc.symbol("{"), flatDoc, Doc.symbol("}")))

  @NotNull def angled(doc: Doc): Doc = wrap("<", ">", doc)

  @NotNull def parened(doc: Doc): Doc = wrap("(", ")", doc)

  /**
   * Return conditional {@link Doc# empty ( )}
   *
   * @param cond      condition
   * @param otherwise otherwise
   * @return {@link Empty} when {@code cond} is true, otherwise {@code otherwise}
   */
  @NotNull def emptyIf(cond: Boolean, otherwise: Supplier[Doc]): Doc = if (cond) empty
  else otherwise.get

  /**
   * The empty document; conceptually the unit of 'Cat'
   *
   * @return empty document
   */
  @NotNull def empty: Doc = Empty.INSTANCE

  /**
   * By default, flatAlt renders as {@param defaultDoc}. However, when 'group'-ed,
   * {@param preferWhenFlattened} will be preferred, with {@param defaultDoc} as
   * the fallback for the case when {@param preferWhenFlattened} doesn't fit.
   *
   * @param defaultDoc          default document
   * @param preferWhenFlattened document selected when flattened
   * @return alternative document
   */
  @Contract("_, _ -> new")
  @NotNull def flatAlt(@NotNull defaultDoc: Doc, @NotNull preferWhenFlattened: Doc) = new Doc.FlatAlt(defaultDoc, preferWhenFlattened)

  /**
   * Layout a document depending on which column it starts at.
   * {@link Doc# align ( Doc )} is implemented in terms of {@code column}.
   *
   * @param docBuilder document generator when current position provided
   * @return column action document
   */
  @Contract("_ -> new")
  @NotNull def column(@NotNull docBuilder: IntFunction[Doc]) = new Doc.Column(docBuilder)

  /**
   * Layout a document depending on the current 'nest'-ing level.
   * {@link Doc# align ( Doc )} is implemented in terms of {@code nesting}.
   *
   * @param docBuilder document generator when current nest level provided
   * @return nest level action document
   */
  @Contract("_ -> new")
  @NotNull def nesting(@NotNull docBuilder: IntFunction[Doc]) = new Doc.Nesting(docBuilder)

  /**
   * Layout a document depending on the page width, if one has been specified.
   *
   * @param docBuilder document generator when page width provided
   * @return page width action document
   */
  @Contract("_ -> new")
  @NotNull def pageWidth(@NotNull docBuilder: IntFunction[Doc]) = new Doc.PageWidth(docBuilder)

  /**
   * lays out the document {@param doc} with the current nesting level
   * (indentation of the following lines) increased by {@param indent}.
   * Negative values are allowed, and decrease the nesting level accordingly.
   * <p>
   * For differences between {@link Doc# hang ( int, Doc)}, {@link Doc# indent ( int, Doc)}
   * and {@link Doc# nest ( int, Doc)}, see unit tests in file "DocStringPrinterTest.java".
   *
   * @param indent indentation of the following lines
   * @param doc    the document to lay out
   * @return indented document
   */
  @Contract("_, _ -> new")
  @NotNull def nest(indent: Int, @NotNull doc: Doc): Doc = if (indent == 0 || doc.isEmpty) doc
  else new Doc.Nest(indent, doc)

  /**
   * align lays out the document {@param doc} with the nesting level set to the
   * current column. It is used for example to implement {@link Doc# hang ( int, Doc)}.
   * <p>
   * As an example, we will put a document right above another one, regardless of
   * the current nesting level. Without 'align'-ment, the second line is put simply
   * below everything we've had so far,
   * <p>
   * If we add an 'align' to the mix, the @'vsep'@'s contents all start in the
   * same column,
   *
   * @param doc document to be aligned
   * @return aligned document
   */
  @Contract("_ -> new")
  @NotNull def align(@NotNull doc: Doc): Doc = {
    // note: nesting might be negative
    column((k: Int) => nesting((i: Int) => nest(k - i, doc)))
  }

  /**
   * hang lays out the document {@param doc} with a nesting level set to the
   * /current column/ plus {@param deltaNest}.
   * Negative values are allowed, and decrease the nesting level accordingly.
   * <p>
   * This differs from {@link Doc# nest ( int, Doc)}, which is based on
   * the /current nesting level/ plus {@code indent}.
   * When you're not sure, try the more efficient 'nest' first. In our
   * example, this would yield
   * <p>
   * For differences between {@link Doc# hang ( int, Doc)}, {@link Doc# indent ( int, Doc)}
   * and {@link Doc# nest ( int, Doc)}, see unit tests in file "DocStringPrinterTest.java".
   *
   * @param deltaNest change of nesting level, relative to the start of the first line
   * @param doc       document to indent
   * @return hang-ed document
   */
  @Contract("_, _ -> new")
  @NotNull def hang(deltaNest: Int, @NotNull doc: Doc): Doc = align(nest(deltaNest, doc))

  /**
   * This method indent document {@param doc} by {@param indent} columns,
   * * starting from the current cursor position.
   * <p>
   * This differs from {@link Doc# hang ( int, Doc)}, which starts from the
   * next line.
   * <p>
   * For differences between {@link Doc# hang ( int, Doc)}, {@link Doc# indent ( int, Doc)}
   * and {@link Doc# nest ( int, Doc)}, see unit tests in file "DocStringPrinterTest.java".
   *
   * @param indent Number of spaces to increase indentation by
   * @return The indented document
   */
  @Contract("_, _ -> new")
  @NotNull def indent(indent: Int, @NotNull doc: Doc): Doc = hang(indent, cat(spaces(indent), doc))

  @NotNull def spaces(n: Int): Doc = if (n <= 0) empty
  else Doc.plain(" ".repeat(n))

  /**
   * Paragraph indentation: indent {@param doc} by {@param indent} columns,
   * and then indent the first line again by {@param indent} columns.
   * This should be used at the line start.
   */
  @Contract("_, _ -> new")
  @NotNull def par(indent: Int, @NotNull doc: Doc): Doc = nest(indent, cat(spaces(indent), doc))

  /**
   * Concat {@param left}, {@param delim} and {@param right}, with
   * {@param left} occupying at least {@param minBeforeDelim} length.
   * The "R" in method name stands for "right", which means the delim is placed near the right.
   * <p>
   * This behaves like {@code printf("%-*s%s%s", minBeforeDelim, left, delim, right);}
   * For example:
   * <pre>
   * var doc = split(8, plain("Help"), plain(":"), english("Show the help message"));
   * assertEquals("Help    :Show the help message", doc.commonRender());
   * </pre>
   *
   * @param minBeforeDelim The minimum length before {@param delim}
   * @apiNote {@param left}, {@param delim}, {@param right} should all be 1-line documents
   */
  @NotNull def splitR(minBeforeDelim: Int, @NotNull left: Doc, @NotNull delim: Doc, @NotNull right: Doc): Doc = {
    val alignedMiddle = column((k: Int) => nesting((i: Int) => indent(minBeforeDelim - k + i, delim)))
    Doc.cat(left, alignedMiddle, Doc.align(right))
  }

  /**
   * Concat {@param left}, {@param delim} and {@param right}, with
   * {@param left} and {@param delim} occupying at least {@param minBeforeRight} length.
   * The "L" in method name stands for "left", which means the delim is placed near the left.
   * <p>
   * This behaves like {@code printf("%*s%s", minBeforeRight, (left ++ delim), right);}
   * For example:
   * <pre>
   * var doc = splitR(8, plain("Help"), plain(":"), english("Show the help message"));
   * assertEquals("Help:   Show the help message", doc.commonRender());
   * </pre>
   *
   * @param minBeforeRight The minimum length before {@param right}
   * @apiNote {@param left}, {@param delim}, {@param right} should all be 1-line documents
   */
  @NotNull def splitL(minBeforeRight: Int, @NotNull left: Doc, @NotNull delim: Doc, @NotNull right: Doc): Doc = {
    val alignedRight = column((k: Int) => nesting((i: Int) => indent(minBeforeRight - k + i, Doc.align(right))))
    Doc.cat(left, delim, alignedRight)
  }

  @NotNull def catBlockR(minBeforeDelim: Int, @NotNull left: Nothing, @NotNull delim: Doc, @NotNull right: Nothing): Doc = {
    val vs = left.zipView(right).map((p) => Doc.splitR(minBeforeDelim, p.component1, delim, p.component2))
    Doc.vcat(vs)
  }

  @NotNull def catBlockL(minBeforeRight: Int, @NotNull left: Nothing, @NotNull delim: Doc, @NotNull right: Nothing): Doc = {
    val vs = left.zipView(right).map((p) => Doc.splitL(minBeforeRight, p.component1, delim, p.component2))
    Doc.vcat(vs)
  }

  /**
   * Creates a C-style indented block of statements.
   * <pre>
   * prefix {
   * [indent]block
   * }
   * </pre>
   */
  @Contract("_, _, _ -> new")
  @NotNull def cblock(@NotNull prefix: Doc, indent: Int, @NotNull block: Doc): Doc = {
    if (block.isEmpty) return prefix
    Doc.vcat(Doc.sepNonEmpty(prefix, Doc.symbol("{")), Doc.nest(indent, Doc.vcat(block)), Doc.symbol("}"))
  }

  @Contract("_ -> new")
  @NotNull def ordinal(n: Int): Doc = {
    val m = n % 100
    if (m >= 4 && m <= 20) return plain(n + "th")
    plain(n + n % 10 match { case 1 => "st"
    case 2 => "nd"
    case 3 => "rd"
    case _ => "th"
    })
  }

  /**
   * Plain text document. Backend will escape the text if it
   * contains offending characters.
   *
   * @param text text that may not contain '\n'
   * @return text document of the whole text
   */
  @Contract("_ -> new")
  @NotNull def plain(text: String): Doc = {
    if (text.isEmpty) return empty
    new Doc.PlainText(text)
  }

  /**
   * Already escaped text that will be rendered as-is.
   * Callers should be responsible for escaping offending characters (like '\n', '<', etc.)
   * depending on the backend. Use with care as this may result in invalid output format.
   * <p>
   * Note that this is not the same as {@link Doc# code} or {@link Doc# codeBlock}.
   * Although in most cases code segments are treated as "already escaped" text
   * that will be rendered as-is. But for HTML, code segments is still escaped because
   * they are placed in `<code>` and `<pre>`.
   *
   * @param text text that will be rendered as-is.
   */
  @Contract("_ -> new")
  @NotNull def escaped(text: String) = new Doc.EscapedText(text)

  @Contract("_ -> new")
  @NotNull def english(text: String): Doc = {
    if (!text.contains(" ")) return plain(text)
    sep(Seq.from(text.split(" ", -1)).view.map(Doc.plain).map((p) => flatAlt(p, cat(line, p))))
  }

  /**
   * @param symbol '\n' not allowed!
   * @return special symbol
   */
  @Contract("_ -> new")
  @NotNull def symbol(symbol: String): Doc = {
    assert(!symbol.contains("\n"))
    new Doc.SpecialSymbol(symbol)
  }

  @Contract("_ -> new")
  @NotNull def symbols(symbols: String*): Doc = sep(Seq.of(symbols).map(Doc.symbol))

  /**
   * cat tries laying out the documents {@param docs} separated with nothing,
   * and if this does not fit the page, separates them with newlines. This is what
   * differentiates it from 'vcat', which always lays out its contents beneath
   * each other.
   *
   * @param docs documents to concat
   * @return cat document
   */
  @Contract("_ -> new")
  @NotNull def cat(@NotNull docs: Nothing): Doc = simpleCat(docs)

  /** @see Doc#cat(Doc...) */
  @Contract("_ -> new")
  @NotNull def cat(docs: Doc*): Doc = cat(Seq.of(docs))

  @Contract("_ -> new")
  @NotNull def vcat(docs: Doc*): Doc = join(line, docs)

  @Contract("_ -> new")
  @NotNull def vcat(@NotNull docs: Nothing): Doc = join(line, docs)

  @Contract("_ -> new")
  @NotNull def vcatNonEmpty(docs: Doc*): Doc = vcatNonEmpty(Seq.of(docs))

  @Contract("_ -> new")
  @NotNull def vcatNonEmpty(@NotNull docs: Nothing): Doc = vcat(docs.view.filter(Doc.isNotEmpty))

  @Contract("_, _ -> new")
  @NotNull def list(isOrdered: Boolean, @NotNull docs: Nothing) = new Doc.List(isOrdered, docs.toImmutableSeq)

  @Contract("_ -> new")
  @NotNull def ordered(docs: Doc*): Doc = list(true, Seq.of(docs))

  @Contract("_ -> new")
  @NotNull def bullet(docs: Doc*): Doc = list(false, Seq.of(docs))

  /**
   * stickySep concatenates all documents {@param docs} horizontally with a space,
   * i.e. it puts a space between all entries.
   * <p>
   * stickySep does not introduce line breaks on its own, even when the page is too narrow:
   *
   * @param docs documents to separate
   * @return separated documents
   */
  @Contract("_ -> new")
  @NotNull def stickySep(@NotNull docs: Nothing): Doc = join(ONE_WS, docs)

  @Contract("_ -> new")
  @NotNull def stickySep(docs: Doc*): Doc = join(ONE_WS, docs)

  /**
   * fillSep concatenates the documents {@param docs} horizontally with a space
   * as long as it fits the page, then inserts a 'line' and continues doing that
   * for all documents in {@param docs}.
   * 'line' means that if 'group'-ed, the documents
   * are separated with a 'space' instead of newlines. Use {@link Doc# cat}
   * if you do not want a 'space'.
   * <p>
   * Let's print some words to fill the line:
   *
   * @param docs documents to separate
   * @return separated documents
   */
  @Contract("_ -> new")
  @NotNull def sep(docs: Doc*): Doc = join(ALT_WS, docs)

  @Contract("_ -> new")
  @NotNull def sep(@NotNull docs: Nothing): Doc = join(ALT_WS, docs)

  @Contract("_ -> new")
  @NotNull def sepNonEmpty(docs: Doc*): Doc = sepNonEmpty(Seq.of(docs))

  @Contract("_ -> new")
  @NotNull def sepNonEmpty(@NotNull docs: Nothing): Doc = sep(docs.view.filter(Doc.isNotEmpty))

  @Contract("_ -> new")
  @NotNull def commaList(@NotNull docs: Nothing): Doc = join(COMMA, docs)

  @Contract("_ -> new")
  @NotNull def vcommaList(@NotNull docs: Nothing): Doc = join(cat(plain(","), line), docs)

  @Contract("_, _ -> new")
  @NotNull def join(@NotNull delim: Doc, docs: Doc*): Doc = join(delim, Seq.of(docs))

  @Contract("_, _ -> new")
  @NotNull def join(@NotNull delim: Doc, @NotNull docs: Nothing): Doc = {
    // See https://github.com/ice1000/aya-prover/issues/753
    val cache = docs.toImmutableSeq
    if (cache.isEmpty) return empty
    val first = cache.getFirst
    if (cache.sizeEquals(1)) return first
    simpleCat(cache.view.drop(1).foldLeft(MutableList.of(first), (l, r) => {
      l.append(delim)
      l.append(r)
      l
    }))
  }

  /**
   * Unconditionally line break
   *
   * @return hard line document
   */
  @Contract("-> new")
  @NotNull def line: Doc = Line.INSTANCE

  //endregion
  @NotNull private def simpleCat(@NotNull xs: Nothing): Doc = {
    val seq = xs.toImmutableSeq
    if (seq.isEmpty) return empty
    if (seq.sizeEquals(1)) return seq.getFirst
    new Doc.Cat(seq)
  }
}

trait Doc extends Nothing {
  def isNotEmpty: Boolean = !isEmpty

  def isEmpty: Boolean = this.isInstanceOf[Doc.Empty]

  @NotNull def toDoc: Doc = this

  //region Doc APIs
  @NotNull def renderToString(@NotNull config: Nothing): String = render(new Nothing, config)

  @NotNull def renderToString(pageWidth: Int, unicode: Boolean): String = {
    val config = new Nothing(DebugStylist.DEFAULT)
    config.set(PrinterConfig.PageOptions.PageWidth, pageWidth)
    config.set(StringPrinterConfig.TextOptions.Unicode, unicode)
    renderToString(config)
  }

  @NotNull def renderToTerminal: String = renderToTerminal(INFINITE_SIZE, true)

  @NotNull def renderToTerminal(pageWidth: Int, unicode: Boolean): String = {
    val config = new Nothing(AdaptiveCliStylist.INSTANCE)
    config.set(PrinterConfig.PageOptions.PageWidth, pageWidth)
    config.set(StringPrinterConfig.TextOptions.Unicode, unicode)
    render(new Nothing, config)
  }

  @NotNull def renderToHtml: String = renderToHtml(true)

  @NotNull def renderToHtml(withHeader: Boolean): String = {
    val config = new Nothing(Html5Stylist.DEFAULT)
    config.set(StringPrinterConfig.StyleOptions.HeaderCode, withHeader)
    config.set(StringPrinterConfig.StyleOptions.StyleCode, withHeader)
    config.set(StringPrinterConfig.StyleOptions.SeparateStyle, withHeader)
    config.set(StringPrinterConfig.TextOptions.Unicode, true)
    render(new Nothing, config)
  }

  @NotNull def renderToMd: String = render(new Nothing, new Nothing(MdStylist.DEFAULT))

  @NotNull def renderToAyaMd: String = {
    val config = new Nothing(MdStylist.DEFAULT)
    config.set(StringPrinterConfig.StyleOptions.AyaFlavored, true)
    config.set(StringPrinterConfig.StyleOptions.HeaderCode, true)
    config.set(StringPrinterConfig.StyleOptions.StyleCode, true)
    config.set(StringPrinterConfig.StyleOptions.SeparateStyle, true)
    config.set(StringPrinterConfig.TextOptions.Unicode, true)
    render(new Nothing, config)
  }

  @NotNull def renderToTeX: String = render(new Nothing, new Nothing)

  def render[Out, Config <: PrinterConfig](@NotNull printer: Nothing, @NotNull config: Config): Out = printer.render(config, this)

  /** Produce ASCII and infinite-width output */
  @NotNull def debugRender: String = renderToString(INFINITE_SIZE, false)

  /** Produce unicode and 80-width output */
  @NotNull def commonRender: String = renderToString(80, true)
}
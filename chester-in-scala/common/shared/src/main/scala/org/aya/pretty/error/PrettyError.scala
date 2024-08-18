// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.error

import kala.collection.immutable.ImmutableSeq
import kala.collection.mutable.MutableList
import kala.control.Option
import kala.tuple.Tuple
import kala.tuple.Tuple2
import kala.tuple.Tuple3
import org.aya.pretty.doc.Doc
import org.aya.pretty.doc.Docile
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Comparator

/**
 * @author kiva
 */
object PrettyError {
  object FormatConfig {
    val CLASSIC = new PrettyError.FormatConfig(Option.none, '|', Option.none, '^', '^', '-', '+', '+')
    val UNICODE = new PrettyError.FormatConfig(Option.some('┝'), '│', Option.some('╯'), '╰', '╯', '─', '╭', '╰')
  }

  final class FormatConfig(@NotNull vbarForHints: Nothing, lineNoSeparator: Char, @NotNull errorIndicator: Nothing, underlineBegin: Char, underlineEnd: Char, underlineBody: Char, beginCorner: Char, endCorner: Char) {
    this.vbarForHints = vbarForHints
    this.lineNoSeparator = lineNoSeparator
    this.errorIndicator = errorIndicator
    this.underlineBegin = underlineBegin
    this.underlineEnd = underlineEnd
    this.underlineBody = underlineBody
    this.beginCorner = beginCorner
    this.endCorner = endCorner
    @NotNull final private val vbarForHints: Nothing = null
    final private val lineNoSeparator = 0
    @NotNull final private val errorIndicator: Nothing = null
    final private val underlineBegin = 0
    final private val underlineEnd = 0
    final private val underlineBody = 0
    final private val beginCorner = 0
    final private val endCorner = 0

    @NotNull def underlineBody(n: Int): String = Character.toString(underlineBody).repeat(n)

    @NotNull def underlineBodyDoc(n: Int): Doc = Doc.plain(underlineBody(n))

    @NotNull def lineNoSepDoc: Doc = Doc.plain(Character.toString(lineNoSeparator))

    @NotNull def beginCornerDoc: Doc = Doc.plain(Character.toString(beginCorner))

    @NotNull def endCornerDoc: Doc = Doc.plain(Character.toString(endCorner))
  }

  private[error] object MultilineMode extends Enumeration {
    type MultilineMode = Value
    val DISABLED, START, END = Value
  }

  final private class CodeBuilder {
    @NotNull final private val lineDocs = MutableList.create
    @NotNull final private val codeDocs = MutableList.create

    private[error] def add(currentLine: Int, @NotNull code: Doc): Unit = {
      lineDocs.append(Doc.plain(String.valueOf(currentLine)))
      codeDocs.append(code)
    }

    private[error] def add(@NotNull code: Doc): Unit = {
      lineDocs.append(Doc.ONE_WS) // cannot use `empty()` or `plain("")`
      codeDocs.append(code)
    }
  }

  final private class HintGroup {
    @NotNull private[error] val underlines = MutableList.create
    @NotNull private[error] val notes = MutableList.create
    @NotNull private[error] val overlapped = MutableList.create
    @Nullable private[error] var startOrEnd: PrettyError.HintLine = null

    def add(indent: Int, @NotNull line: PrettyError.HintLine, @NotNull cfg: PrettyError.FormatConfig): Unit = {
      val hint = renderHint(cfg, line.startCol, line.endCol, line.loc match { case Shot | Between | None => PrettyError.MultilineMode.DISABLED
      case Start => PrettyError.MultilineMode.START
      case End => PrettyError.MultilineMode.END
      })
      underlines.append(Doc.indent(indent, hint))
      notes.append(line.note)
      if ((line.loc eq Span.NowLoc.Start) || (line.loc eq Span.NowLoc.End)) startOrEnd = line
    }
  }

  final private[error] class HintLine private[error](note: Doc, loc: Nothing, startCol: Int, endCol: Int, allocIndent: Int) {
    this.note = note
    this.loc = loc
    this.startCol = startCol
    this.endCol = endCol
    this.allocIndent = allocIndent
    final private val note: Doc = null
    final private val loc: Nothing = null
    final private val startCol = 0
    final private val endCol = 0
    final private val allocIndent = 0
  }

  private val INDENT_FACTOR = 2

  private def overlaps(x1: Int, x2: Int, y1: Int, y2: Int) = {
    // assuming x1 <= x2, y1 <= y2
    x1 <= y2 && y1 <= x2
  }

  @NotNull private def renderHint(@NotNull cfg: PrettyError.FormatConfig, startCol: Int, endCol: Int, mode: PrettyError.MultilineMode) = {
    val builder = new lang.StringBuilder
    if ((mode eq PrettyError.MultilineMode.DISABLED) || cfg.errorIndicator.isEmpty) {
      builder.append(cfg.underlineBegin)
      // -1 for the start symbol
      val length = endCol - startCol - 1
      if (length > 0) {
        // endCol is in the next line
        builder.append(cfg.underlineBody(length))
      }
      builder.append(cfg.underlineEnd)
    }
    else {
      val length = endCol - startCol
      if (length > 0) {
        // endCol is in the next line
        builder.append(cfg.underlineBody(length))
      }
      builder.append(cfg.errorIndicator.get)
    }
    Doc.plain(builder.toString)
  }
}

final class PrettyError(@NotNull filePath: String, @NotNull errorRange: Nothing, @NotNull brief: Doc, @NotNull formatConfig: PrettyError.FormatConfig, @NotNull inlineHints: Nothing) extends Docile {
  this.filePath = filePath
  this.errorRange = errorRange
  this.brief = brief
  this.formatConfig = formatConfig
  this.inlineHints = inlineHints
  @NotNull final private val filePath: String = null
  @NotNull final private val errorRange: Nothing = null
  @NotNull final private val brief: Doc = null
  @NotNull final private val formatConfig: PrettyError.FormatConfig = null
  @NotNull final private val inlineHints: Nothing = null

  @NotNull def toDoc(@NotNull config: Nothing): Doc = {
    val primary = errorRange.normalize(config)
    val hints = inlineHints.view.map((kv) => Tuple.of(kv.component1.normalize(config), kv.component2)).filter((kv) => kv.component1.startLine eq kv.component1.endLine).toImmutableSeq // TODO: multiline inline hints?
    val allRange = hints.map(Tuple2.component1).foldLeft(primary, Span.Data.union)
    Doc.vcat(Doc.plain("In file " + filePath + ":" + primary.startLine + ":" + primary.startCol + " ->"), Doc.empty, Doc.hang(2, visualizeCode(config, allRange, primary, hints)), Doc.empty, brief, Doc.empty)
  }

  @NotNull override def toDoc: Doc = toDoc(PrettyErrorConfig.DEFAULT)

  @NotNull private def visualizeLine(@NotNull config: Nothing, @NotNull line: String) = {
    val tabWidth = config.tabWidth
    line.replaceAll("\t", " ".repeat(tabWidth))
  }

  /** invariant: `others` is sorted by `startCol` */
  @NotNull private def splitHints(@NotNull others: Nothing) = {
    val group = new PrettyError.HintGroup
    if (others.isNotEmpty) {
      val first = others.getFirst
      group.add(first.startCol, first, formatConfig)
      var left = first.startCol
      var right = first.endCol
      var last = right
      var i = 1
      while (i < others.size) {
        val the = others.get(i)
        if (PrettyError.overlaps(left, right, the.startCol, the.endCol)) group.overlapped.append(the)
        else {
          val indent = the.startCol - last
          group.add(indent, the, formatConfig)
          last = the.endCol
          left = Math.min(left, the.startCol)
          right = Math.max(right, the.endCol)
        }
        i += 1
      }
    }
    group
  }

  private def renderHints(continued: Boolean, continuedFromStartEnd: Boolean, currentLine: Int, codeIndent: Int, vbarUsedIndent: Int, @NotNull vbar: Doc, @NotNull currentCode: Doc, @NotNull builder: PrettyError.CodeBuilder, @NotNull others: Nothing): Unit = {
    val split = splitHints(others)
    val startOrEnd = split.startOrEnd
    val rest = codeIndent - vbarUsedIndent
    // commit code
    if (!continued) {
      val codeDoc = if (startOrEnd == null || (startOrEnd.loc ne Span.NowLoc.End)) Doc.cat(vbar, Doc.indent(rest * PrettyError.INDENT_FACTOR, currentCode))
      else Doc.cat(vbar, formatConfig.lineNoSepDoc, Doc.indent(rest * PrettyError.INDENT_FACTOR - 1, currentCode))
      builder.add(currentLine, codeDoc)
    }
    // commit hint
    val underlines = Doc.cat(split.underlines)
    val notes = Doc.cat(split.notes)
    val almost = if (notes.isEmpty || (notes.isInstanceOf[Doc.Cat] && cat.inner.isEmpty)) underlines
    else Doc.stickySep(underlines, Doc.align(notes))
    val codeHint = if (startOrEnd != null) renderStartEndHint(startOrEnd, vbar, almost, rest)
    else renderContinuedHint(continued, continuedFromStartEnd, vbar, almost, rest)
    builder.add(codeHint)
    // show overlapped hints in the next line
    if (split.overlapped.isNotEmpty) renderHints(true, startOrEnd != null, currentLine, codeIndent, vbarUsedIndent, vbar, currentCode, builder, split.overlapped.toImmutableSeq)
  }

  @NotNull private def renderStartEndHint(@NotNull startOrEnd: PrettyError.HintLine, @NotNull vbar: Doc, @NotNull almost: Doc, rest: Int) = if (startOrEnd.loc eq Span.NowLoc.Start) Doc.cat(vbar, formatConfig.beginCornerDoc, formatConfig.underlineBodyDoc(rest * PrettyError.INDENT_FACTOR - 1), almost)
  else Doc.cat(vbar, formatConfig.endCornerDoc, formatConfig.underlineBodyDoc(rest * PrettyError.INDENT_FACTOR - 1), almost)

  @NotNull private def renderContinuedHint(continued: Boolean, fromStartEnd: Boolean, @NotNull vbar: Doc, @NotNull almost: Doc, rest: Int) = if (continued && fromStartEnd // implies vbar.isEmpty())  { Doc.cat(formatConfig.lineNoSepDoc, Doc.indent(rest * PrettyError.INDENT_FACTOR, almost))}
  else Doc.cat(vbar, Doc.indent(rest * PrettyError.INDENT_FACTOR, almost))

  private def computeMultilineVBar(@NotNull between: Nothing) = {
    var last = 0
    var vbar = Doc.empty
    import scala.collection.JavaConversions._
    for (the <- between) {
      val level = the.allocIndent - last
      vbar = Doc.cat(vbar, Doc.indent(level * PrettyError.INDENT_FACTOR, formatConfig.lineNoSepDoc))
      last = the.endCol
    }
    Tuple.of(last, vbar)
  }

  private def renderHints(currentLine: Int, codeIndent: Int, @NotNull currentCode: Doc, @NotNull builder: PrettyError.CodeBuilder, @NotNull hintLines: Nothing): Unit = {
    val between = hintLines.view.filter((h) => h.loc eq Span.NowLoc.Between).sorted(Comparator.comparingInt((a: T) => a.allocIndent)).toImmutableSeq
    val others = hintLines.filter((h) => h.loc ne Span.NowLoc.Between)
    val vbar = computeMultilineVBar(between)
    renderHints(false, false, currentLine, codeIndent, vbar.component1, vbar.component2, currentCode, builder, others)
  }

  @NotNull private def visualizeCode(@NotNull config: Nothing, @NotNull fullRange: Nothing, @NotNull primaryRange: Nothing, @NotNull hints: Nothing) = {
    val startLine = fullRange.startLine
    val endLine = fullRange.endLine
    val showMore = config.showMore
    // calculate the maximum char width of line number
    val linenoWidth = Math.max(widthOfLineNumber(startLine), widthOfLineNumber(endLine)) + 1
    // collect lines from (startLine - SHOW_MORE_LINE) to (endLine + SHOW_MORE_LINE)
    val lines = errorRange.input.lines.skip(Math.max(startLine - 1 - showMore, 0)).limit(endLine - startLine + 1 + showMore).map((line) => visualizeLine(config, line)).toList
    val minLineNo = Math.max(startLine - showMore, 1)
    val maxLineNo = minLineNo + lines.size
    // allocate hint nest
    val alloc = MutableList.create[Nothing]
    alloc.append(Tuple.of(primaryRange, Doc.empty, 0))
    hints.view.filter((kv) => kv.component1.startLine >= minLineNo && kv.component1.endLine <= maxLineNo).mapIndexed((i, kv) => Tuple.of(kv.component1, kv.component2, i + 1)).forEach(alloc.append)
    val codeIndent = alloc.size
    var lineNo = minLineNo
    val builder = new PrettyError.CodeBuilder
    import scala.collection.JavaConversions._
    for (line <- lines) {
      val currentLine = lineNo
      val currentCode = Doc.plain(line)
      val hintLines = alloc.view.mapNotNull((note) => note.component1.nowLoc(currentLine) match {
        case Shot => new PrettyError.HintLine(note.component2, Span.NowLoc.Shot, note.component1.startCol, note.component1.endCol, note.component3)
        case Start => new PrettyError.HintLine(Doc.empty, Span.NowLoc.Start, 0, note.component1.startCol, note.component3)
        case End => new PrettyError.HintLine(note.component2, Span.NowLoc.End, 0, note.component1.endCol, note.component3)
        case Between => new PrettyError.HintLine(Doc.empty, Span.NowLoc.Between, 0, 0, note.component3)
        case None => null
      }).sorted(Comparator.comparingInt((a: T) => a.startCol)).toImmutableSeq
      if (hintLines.isEmpty) builder.add(currentLine, Doc.indent(codeIndent * PrettyError.INDENT_FACTOR, currentCode))
      else renderHints(currentLine, codeIndent, currentCode, builder, hintLines)
      lineNo += 1
    }
    Doc.catBlockR(linenoWidth, builder.lineDocs, Doc.cat(formatConfig.lineNoSepDoc, Doc.ONE_WS), builder.codeDocs)
  }

  private def widthOfLineNumber(line: Int) = String.valueOf(line).length
}
// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.md

import org.aya.pretty.backend.html.DocHtmlPrinter
import org.aya.pretty.backend.html.HtmlConstants
import org.aya.pretty.backend.string.Cursor
import org.aya.pretty.doc.Doc
import org.aya.pretty.doc.Language
import org.jetbrains.annotations.NotNull
import java.util
import java.util.regex.Pattern
import org.aya.pretty.backend.string.StringPrinterConfig.StyleOptions._

object DocMdPrinter {
  val MD_ESCAPE: Pattern = Pattern.compile("[#&()*+;<>\\[\\\\\\]_`|~]")
  /** `Doc.plain("1. hello")` should not be rendered as a list, see MdStyleTest */
  val MD_ESCAPE_FAKE_LIST: Pattern = Pattern.compile("(^\\s*\\d+)\\.( |$)", Pattern.MULTILINE)

  class Config(@NotNull stylist: Nothing) extends Config(stylist) {
  }
}

class DocMdPrinter extends DocHtmlPrinter[DocMdPrinter.Config] {
  protected def renderHeader(@NotNull cursor: Nothing): Unit = {
  }

  protected def renderFooter(@NotNull cursor: Nothing): Unit = {
    // put generated styles at the end of the file
    if (config.opt(HeaderCode, false)) {
      if (config.opt(AyaFlavored, false)) {
        cursor.invisibleContent(HtmlConstants.HOVER_STYLE)
        cursor.invisibleContent(HtmlConstants.HOVER_TYPE_POPUP_STYLE)
        cursor.invisibleContent(if (config.opt(ServerSideRendering, false)) HtmlConstants.HOVER_SSR
        else HtmlConstants.HOVER)
      }
      renderCssStyle(cursor)
    }
  }

  // markdown escape: https://spec.commonmark.org/0.30/#backslash-escapes
  @NotNull protected def escapePlainText(@NotNull content: String, outer: util.EnumSet[Nothing]): String = {
    if (outer.contains(Outer.EnclosingTag)) {
      // If we are in HTML tag (like rendered Aya code), use HTML escape settings.
      return super.escapePlainText(content, outer)
    }
    // If we are in Markdown, do not escape text in code block.
    if (outer.contains(Outer.Code) || outer.contains(Outer.Math)) return content
    // We are not need to call `super.escapePlainText`, we will escape them in markdown way.
    // I wish you can understand this genius regexp
    // What we will escape:
    // .
    // What we won't escape, which are not special characters
    // or don't matter in a plain text (like `:` and `"` work in footnotes only):
    // ":,%$'=@?^{}/-
    // What we should escape, but we don't:
    // `!`: `!` is only used in `![]()`, but we already escape `[`, `]`, `(`, `)`, so `!` doesn't work.
    content = DocMdPrinter.MD_ESCAPE.matcher(content).replaceAll((result: MatchResult) => {
      var chara = result.group
      // special characters, see Matcher#appendReplacement
      if (chara == "\\") chara = "\\\\"
      "\\\\" + chara
    })
    // escape fake lists
    content = DocMdPrinter.MD_ESCAPE_FAKE_LIST.matcher(content).replaceAll("$1\\\\.$2")
    content
  }

  protected def renderHardLineBreak(@NotNull cursor: Nothing, outer: util.EnumSet[Nothing]): Unit = {
    cursor.lineBreakWith("\n")
  }

  protected def renderHyperLinked(@NotNull cursor: Nothing, @NotNull text: Doc.HyperLinked, outer: util.EnumSet[Nothing]): Unit = {
    val pureMd = () => {
      // use markdown typesetting only when the stylist is pure markdown
      val href = text.href
      cursor.invisibleContent("[")
      renderDoc(cursor, text.doc, outer)
      cursor.invisibleContent("](")
      cursor.invisibleContent(normalizeHref(href))
      cursor.invisibleContent(")")
    }
    runSwitch(pureMd, () => {
      if (!outer.isEmpty) super.renderHyperLinked(cursor, text, outer)
      else pureMd.run()
    })
  }

  protected def renderImage(@NotNull cursor: Nothing, @NotNull image: Doc.Image, outer: util.EnumSet[Nothing]): Unit = {
    cursor.invisibleContent("![")
    renderDoc(cursor, image.alt, outer)
    cursor.invisibleContent("](")
    cursor.invisibleContent(normalizeHref(image.src))
    cursor.invisibleContent(")")
  }

  protected def renderList(@NotNull cursor: Nothing, @NotNull list: Doc.List, outer: util.EnumSet[Nothing]): Unit = {
    formatList(cursor, list, outer)
  }

  protected def renderInlineMath(@NotNull cursor: Nothing, code: Doc.InlineMath, outer: util.EnumSet[Nothing]): Unit = {
    formatInline(cursor, code.formula, "$", "$", util.EnumSet.of(Outer.Math))
  }

  /**
   * @implNote We don't call {@link # separateBlockIfNeeded}, as Markdown spec says:
   *           any block is surrounded with Paragraphs, which is handled in {@link MdStylist# formatCustom}
   *           by inserting a blank line to generated source code (just like {@link # separateBlockIfNeeded}).
   */
  protected def renderMathBlock(@NotNull cursor: Nothing, block: Doc.MathBlock, outer: util.EnumSet[Nothing]): Unit = {
    formatBlock(cursor, block.formula, "$$", "$$", util.EnumSet.of(Outer.Math))
  }

  protected def renderInlineCode(@NotNull cursor: Nothing, @NotNull code: Doc.InlineCode, outer: util.EnumSet[Nothing]): Unit = {
    // assumption: inline code cannot be nested in markdown, but don't assert it.
    val pureMd = () => formatInline(cursor, code.code, "`", "`", util.EnumSet.of(Outer.Code))
    runSwitch(pureMd, () => {
      if (code.language.isAya) formatInline(cursor, code.code, "<code class=\"Aya\">", "</code>", util.EnumSet.of(Outer.EnclosingTag))
      else pureMd.run()
    })
  }

  /**
   * @implNote We don't call {@link # separateBlockIfNeeded}, as Markdown spec says:
   *           any block is surrounded with Paragraphs, which is handled in {@link MdStylist# formatCustom}
   *           by inserting a blank line to generated source code (just like {@link # separateBlockIfNeeded}).
   */
  protected def renderCodeBlock(@NotNull cursor: Nothing, @NotNull block: Doc.CodeBlock, outer: util.EnumSet[Nothing]): Unit = {
    // assumption: code block cannot be nested in markdown, but don't assert it.
    val mark = if (block.language eq Language.Builtin.Markdown) "~~~"
    else "```"
    val pureMd = () => formatBlock(cursor, block.code, mark + block.language.displayName.toLowerCase, mark, util.EnumSet.of(Outer.Code))
    runSwitch(pureMd, () => {
      if (block.language.isAya) formatBlock(cursor, "<pre class=\"Aya\">", "</pre>", outer, () => formatInline(cursor, block.code, "<code>", "</code>", util.EnumSet.of(Outer.EnclosingTag)))
      else pureMd.run()
    })
  }

  private def runSwitch(@NotNull pureMd: Runnable, @NotNull ayaMd: Runnable): Unit = {
    if (config.opt(AyaFlavored, false)) ayaMd.run()
    else pureMd.run()
  }
}
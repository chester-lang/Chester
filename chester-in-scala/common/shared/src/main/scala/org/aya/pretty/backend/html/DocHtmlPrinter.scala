// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.html

import kala.collection.immutable.ImmutableMap
import org.aya.pretty.backend.string.Cursor
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.backend.string.StringPrinterConfig
import org.aya.pretty.backend.string.StringStylist
import org.aya.pretty.doc.Doc
import org.aya.pretty.doc.Link
import org.intellij.lang.annotations.Language
import org.jetbrains.annotations.NotNull
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util
import java.util.regex.Pattern
import org.aya.pretty.backend.string.StringPrinterConfig.StyleOptions._

/**
 * Html backend, which ignores page width.
 */
object DocHtmlPrinter {
  /**
   * <a href="https://developer.mozilla.org/en-US/docs/Glossary/Entity">Mozilla doc: entity</a>
   * Added backslash for vitepress compatibility.
   */
    @NotNull val entityPattern: Pattern = Pattern.compile("[&<>\"\\\\]")
  @NotNull val entityMapping: Nothing = ImmutableMap.of("&", "&amp;", "<", "&lt;", ">", "&gt;", "\\", "&bsol;", "\"", "&quot;")

  class Config(@NotNull stylist: Html5Stylist) extends Nothing(stylist) {
  }
}

class DocHtmlPrinter[Config <: DocHtmlPrinter.Config] extends Nothing {
  @Language(value = "HTML")
  @NotNull private[html] val HEAD =
    """
    <!DOCTYPE html><html lang="en"><head>
    <title>Aya file</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    """ + HtmlConstants.HOVER_STYLE + HtmlConstants.HOVER_TYPE_POPUP_STYLE

  protected def renderHeader(@NotNull cursor: Nothing): Unit = {
    if (config.opt(HeaderCode, false)) {
      cursor.invisibleContent(HEAD)
      renderCssStyle(cursor)
      if (config.opt(ServerSideRendering, false)) cursor.invisibleContent(HtmlConstants.HOVER_SSR)
      else {
        cursor.invisibleContent(HtmlConstants.HOVER)
        cursor.invisibleContent(HtmlConstants.KATEX_AUTO_RENDER)
      }
      cursor.invisibleContent("</head><body>\n")
    }
  }

  protected def renderFooter(@NotNull cursor: Nothing): Unit = {
    if (config.opt(HeaderCode, false)) cursor.invisibleContent("\n</body></html>\n")
  }

  protected def renderCssStyle(@NotNull cursor: Nothing): Unit = {
    if (!config.opt(SeparateStyle, false)) return
    if (!config.opt(StyleCode, false)) return
    cursor.invisibleContent("<style>")
    // colors are defined in global scope `:root`
    val colors = Html5Stylist.colorsToCss(config.getStylist.colorScheme)
    cursor.invisibleContent("\n:root {\n%s\n}\n".formatted(colors))
    config.getStylist.styleFamily.definedStyles.forEach((name, style) => {
      val selector = Html5Stylist.styleKeyToCss(name).map((x) => s".${x}"
      ).joinToString(" ")
      val css = style.styles.mapNotNull(Html5Stylist.styleToCss).joinToString("\n", "  %s".formatted)
      val stylesheet = "%s {\n%s\n}\n".formatted(selector, css)
      cursor.invisibleContent(stylesheet)
    })
    cursor.invisibleContent("</style>\n")
  }

  @NotNull protected def prepareStylist: Nothing = if (config.opt(SeparateStyle, false)) new Html5Stylist.ClassedPreset(config.getStylist)
  else super.prepareStylist

  @NotNull protected def escapePlainText(@NotNull content: String, outer: util.EnumSet[Nothing]): String = {
    // HTML always needs escaping, unless we are in KaTeX math mode
    if (outer.contains(Outer.Math)) return content
    DocHtmlPrinter.entityPattern.matcher(content).replaceAll((result: MatchResult) => DocHtmlPrinter.entityMapping.get(result.group)) // fail if bug
  }

  protected def renderTooltip(@NotNull cursor: Nothing, tooltip: Doc.Tooltip, outer: util.EnumSet[Nothing]): Unit = {
    val newCursor = new Nothing(this)
    renderDoc(newCursor, tooltip.tooltip.toDoc, FREE)
    val tip = newCursor.result.toString
    // ^ note: the tooltip is shown in a popup, which is a new document.
    cursor.invisibleContent("<span class=\"aya-tooltip\" ")
    cursor.invisibleContent("data-tooltip-text=\"")
    cursor.invisibleContent(Base64.getEncoder.encodeToString(tip.getBytes(StandardCharsets.UTF_8)))
    cursor.invisibleContent("\">")
    renderDoc(cursor, tooltip.doc, util.EnumSet.of(Outer.EnclosingTag))
    cursor.invisibleContent("</span>")
  }

  protected def renderHyperLinked(@NotNull cursor: Nothing, text: Doc.HyperLinked, outer: util.EnumSet[Nothing]): Unit = {
    val href = text.href
    cursor.invisibleContent("<a ")
    if (text.id != null) {
      cursor.invisibleContent(
      s"id=\"${normalizeId(text.id())}\" "
      )
    }
    if (text.hover != null) {
      cursor.invisibleContent("class=\"aya-hover\" ")
      cursor.invisibleContent(s"aya-hover-text=\"${text.hover()}\" "
      )
    }
    cursor.invisibleContent("href=\"")
    cursor.invisibleContent(normalizeHref(href))
    cursor.invisibleContent("\">")
    renderDoc(cursor, text.doc, util.EnumSet.of(Outer.EnclosingTag))
    cursor.invisibleContent("</a>")
  }

  protected def renderImage(@NotNull cursor: Nothing, image: Doc.Image, outer: util.EnumSet[Nothing]): Unit = {
    cursor.invisibleContent("<img ")
    cursor.invisibleContent(s"src=\"${normalizeHref(image.src())}\" "
    )
    cursor.invisibleContent("alt=\"")
    renderDoc(cursor, image.alt, outer)
    cursor.invisibleContent("\"/>")
  }

  @NotNull def normalizeId(@NotNull linkId: Link): String = linkId match {
    case Link.CrossLink( var path, var loc) => if (path.isEmpty) {
  `yield` loc == null ? "": normalizeId (loc) // todo: Java's yield is not supported
  }
  val prefix: Null = config.opt (StringPrinterConfig.LinkOptions.CrossLinkPrefix, "/")
  val postfix: Null = config.opt (StringPrinterConfig.LinkOptions.CrossLinkPostfix, ".html")
  val sep: Null = config.opt (StringPrinterConfig.LinkOptions.CrossLinkSeparator, "/")
  path.joinToString (sep, prefix, postfix) + (if (loc == null) {
  ""
  }
  else {
  STR."#\{normalizeId(loc)}"
  })

    case Link.DirectLink( var link) => link
    case Link.LocalId( var id) => id.fold (Html5Stylist.normalizeCssId, (x) => STR."v\{x}")
    // ^ CSS3 selector does not support IDs starting with a digit, so we prefix them with "v".
    // See https://stackoverflow.com/a/37271406/9506898 for more details.
  }

  @NotNull def normalizeHref(@NotNull linkId: Link): String = linkId match {
    case Link.CrossLink link => normalizeId(link)
    case Link.DirectLink( var link) => link
    case Link.LocalId localId => STR.
      "#\{normalizeId(localId)}"
  }

  protected def renderHardLineBreak(@NotNull cursor: Nothing, outer: util.EnumSet[Nothing]): Unit = {
    cursor.lineBreakWith("<br>")
  }

  protected def renderInlineCode(@NotNull cursor: Nothing, code: Doc.InlineCode, outer: util.EnumSet[Nothing]): Unit = {
    // `<code class="" />` is valid, see:
    // https://stackoverflow.com/questions/30748847/is-an-empty-class-attribute-valid-html
    cursor.invisibleContent(STR.
    "<code class=\"\{capitalize(code.language())}\">"
    )
    renderDoc(cursor, code.code, util.EnumSet.of(Outer.EnclosingTag)) // Even in code mode, we still need to escape
    cursor.invisibleContent("</code>")
  }

  protected def renderCodeBlock(@NotNull cursor: Nothing, block: Doc.CodeBlock, outer: util.EnumSet[Nothing]): Unit = {
    cursor.invisibleContent(STR.
    "<pre class=\"\{capitalize(block.language())}\">"
    )
    renderDoc(cursor, block.code, util.EnumSet.of(Outer.EnclosingTag)) // Even in code mode, we still need to escape
    cursor.invisibleContent("</pre>")
  }

  protected def renderInlineMath(@NotNull cursor: Nothing, code: Doc.InlineMath, outer: util.EnumSet[Nothing]): Unit = {
    // https://katex.org/docs/autorender.html
    formatInline(cursor, code.formula, "<span class=\"doc-katex-input\">\\(", "\\)</span>", util.EnumSet.of(Outer.Math))
  }

  protected def renderMathBlock(@NotNull cursor: Nothing, block: Doc.MathBlock, outer: util.EnumSet[Nothing]): Unit = {
    cursor.invisibleContent("<pre><div class=\"doc-katex-input\">")
    // https://katex.org/docs/autorender.html
    formatBlock(cursor, block.formula, "\\[", "\\]", util.EnumSet.of(Outer.Math))
    cursor.invisibleContent("</div></pre>")
  }

  protected def renderList(@NotNull cursor: Nothing, list: Doc.List, outer: util.EnumSet[Nothing]): Unit = {
    val tag = if (list.isOrdered) "ol"
    else "ul"
    cursor.invisibleContent(STR.
    "<\{tag}>"
    )
    list.items.forEach((item) => {
      cursor.invisibleContent("<li>")
      renderDoc(cursor, item, util.EnumSet.of(Outer.List, Outer.EnclosingTag))
      cursor.invisibleContent("</li>")
    })
    cursor.invisibleContent(STR.
    "</\{tag}>"
    )
  }

  @NotNull private def capitalize(@NotNull s: Language) = {
    val name = s.displayName
    if (name.isEmpty) name
    else name.substring(0, 1).toUpperCase + name.substring(1)
  }
}
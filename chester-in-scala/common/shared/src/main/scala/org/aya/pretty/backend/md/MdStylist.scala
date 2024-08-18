// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.md

import org.aya.pretty.backend.html.Html5Stylist
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.aya.pretty.style.AyaColorScheme
import org.aya.pretty.style.AyaStyleFamily
import org.jetbrains.annotations.NotNull
import java.util

object MdStylist {
  @NotNull val DEFAULT = new MdStylist(AyaColorScheme.EMACS, AyaStyleFamily.DEFAULT)
}

class MdStylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing) extends Html5Stylist(colorScheme, styleFamily) {
  /**
   * @implNote Actually, we are abusing the undefined behavior (outer != Free) of markdown.
   *           Typical markdown does not allow italic/bold/strike/... in code segments,
   *           which means the `outer` should always be `Free`, but Literate Aya uses
   *           HTML tag `pre` and `code` for rendered Aya code, which allows more
   *           HTML typesetting to be applied.
   *           A solution may be a separate `AyaMdStylist` for Literate Aya only, and
   *           the standard markdown backend should always use markdown typesetting
   *           and assume `outer == Free` (but don't assert it).
   */
  @NotNull protected def formatItalic(outer: util.EnumSet[Nothing]): Nothing = if (outer.isEmpty) new Nothing("_", "_", false)
  else super.formatItalic(outer)

  @NotNull protected def formatBold(outer: util.EnumSet[Nothing]): Nothing = {
    // see comments in `formatItalic`
    if (outer.isEmpty) new Nothing("**", "**", false)
    else super.formatBold(outer)
  }

  @NotNull protected def formatLineThrough(line: Style.LineThrough, outer: util.EnumSet[Nothing]): Nothing = {
    // see comments in `formatItalic`
    if (outer.isEmpty && (line.position eq Style.LineThrough.Position.Strike)) new Nothing("~~", "~~", false)
    else super.formatLineThrough(line, outer)
  }

  @NotNull protected def formatCustom(style: Style.CustomStyle): Nothing = {
    if (style.isInstanceOf[MdStyle]) return md match {
      case MdStyle.GFM gfm =>
        gfm match {
          case BlockQuote => new Nothing((c) => c.invisibleContent("> "), (c) => c.lineBreakWith("\n\n"))
          case Paragraph =>
            new Nothing((c) => {
            }, (c) => c.lineBreakWith("\n\n"))
          case ThematicBreak => new Nothing((c) => c.invisibleContent("---"), (c) => c.lineBreakWith("\n\n"))
        }
      case MdStyle.Heading(int level) =>
        new Nothing((c) => {
          c.invisibleContent("#".repeat(level))
          c.invisibleContent(" ")
        }, (c) => c.lineBreakWith("\n"))
    }
    super.formatCustom(style)
  }
}
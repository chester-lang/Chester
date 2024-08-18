// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.latex

import kala.collection.immutable.ImmutableSeq
import kala.tuple.Tuple
import kala.tuple.Tuple2
import org.aya.pretty.backend.string.ClosingStylist
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.doc.Style
import org.aya.pretty.doc.Styles
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.aya.pretty.style.AyaColorScheme
import org.aya.pretty.style.AyaStyleFamily
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util

object TeXStylist {
  @NotNull val DEFAULT = new TeXStylist(AyaColorScheme.INTELLIJ, AyaStyleFamily.DEFAULT, false)
  @NotNull val DEFAULT_KATEX = new TeXStylist(AyaColorScheme.INTELLIJ, AyaStyleFamily.DEFAULT, true)

  class ClassedPreset(@NotNull delegate: Nothing) extends Nothing(delegate) {
    @NotNull protected def formatPresetStyle(@NotNull styleName: String, outer: util.EnumSet[Nothing]): Nothing = {
      val commandName = styleKeyToTex(styleName)
      ImmutableSeq.of(new Nothing("\\%s{".formatted(commandName), "}", false))
    }
  }

  /** @see org.aya.pretty.style.AyaStyleKey */
  @NotNull def styleKeyToTex(@NotNull styleName: String): String = normalizeTexId(styleName)

  @NotNull def normalizeTexId(@NotNull id: String): String = id.replace("::", "")

  @NotNull def stylesToTexCmd(@NotNull styles: Styles, @NotNull arg: String): String = styles.styles.view.mapNotNull(TeXStylist.styleToTex).foldLeft(arg, (acc, t) => t.component1 + acc + t.component2)

  @Nullable def styleToTex(@NotNull style: Style): Nothing = style match {
    case Style.Attr attr =>
      attr match {
        case Italic => Tuple.of("\\textit{", "}")
        case Bold => Tuple.of("\\textbf{", "}")
      }
    case Style.LineThrough( var pos, _, _) => pos match {
  case Strike => Tuple.of ("\\sout{", "}")
  case Underline => Tuple.of ("\\underline{", "}")
  case Overline => null
  }
    case Style.ColorHex( var rgb, var background) => Tuple.of ("\\%s[HTML]{%06x}{".formatted (if (background) {
  "colorbox"
  }
  else {
  "textcolor"
  }, rgb), "}")
    case Style.ColorName( var name, var background) => Tuple.of ("\\%s{%s}{".formatted (if (background) {
  "colorbox"
  }
  else {
  "textcolor"
  }, normalizeTexId (name) ), "}")
    case _ => null
  }

  @NotNull def colorsToTex(@NotNull colorScheme: Nothing): String = colorScheme.definedColors.toImmutableSeq.view.map((t) => "\\definecolor{%s}{HTML}{%06x}".formatted(normalizeTexId(t.component1), t.component2)).joinToString("\n")
}

class TeXStylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing, val isKaTeX: Boolean) extends Nothing(colorScheme, styleFamily) {
  @NotNull protected def formatItalic(outer: util.EnumSet[Nothing]) = new Nothing("\\textit{", "}", false)

  @NotNull protected def formatBold(outer: util.EnumSet[Nothing]) = new Nothing("\\textbf{", "}", false)

  @NotNull protected def formatLineThrough(@NotNull line: Style.LineThrough, outer: util.EnumSet[Nothing]): Nothing = line.position match {
    case Underline => new Nothing("\\underline{", "}", false)
    case Strike => new Nothing("\\sout{", "}", false)
    case Overline => StyleToken.NULL
  }

  @NotNull protected def formatColorHex(rgb: Int, background: Boolean): Nothing = {
    val cmd = "\\%s%s{%06x}{".formatted(if (background) "colorbox"
    else "textcolor", if (isKaTeX) ""
    else "[HTML]", rgb)
    new Nothing(cmd, "}", false)
  }
}
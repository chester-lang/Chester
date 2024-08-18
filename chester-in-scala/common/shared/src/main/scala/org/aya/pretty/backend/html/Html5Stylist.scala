// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.html

import kala.collection.immutable.ImmutableSeq
import org.aya.pretty.backend.string.ClosingStylist
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.aya.pretty.style.AyaColorScheme
import org.aya.pretty.style.AyaStyleFamily
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util

object Html5Stylist {
  @NotNull val DEFAULT = new Html5Stylist(AyaColorScheme.EMACS, AyaStyleFamily.DEFAULT)

  class ClassedPreset(@NotNull delegate: Nothing) extends Nothing(delegate) {
    @NotNull protected def formatPresetStyle(@NotNull styleName: String, outer: util.EnumSet[Nothing]): Nothing = {
      val className = styleKeyToCss(styleName).getLast
      ImmutableSeq.of(new Nothing("<span class=\"%s\">".formatted(className), "</span>", false))
    }
  }

  /** @see org.aya.pretty.style.AyaStyleKey */
  @NotNull def styleKeyToCss(@NotNull className: String): Nothing = ImmutableSeq.from(className.split("::", -1)).map(Html5Stylist.normalizeCssId)

  @Nullable def styleToCss(@NotNull style: Style): String = style match {
    case attr:Style.Attr  =>
      attr match {
        case Italic => "font-style: italic;"
        case Bold => "font-weight: bold;"
      }
    case Style.LineThrough( var pos, var shape, var color) => val decoLine: String = pos match {
  case Overline => "text-decoration-line: overline;"
  case Underline => "text-decoration-line: underline; text-underline-position: under;"
  case Strike => "text-decoration-line: line-through;"
  }
  val decoStyle: String = shape match {
  case Solid => "text-decoration-style: solid;"
  case Curly => "text-decoration-style: wavy;"
  }
  val colorRef: String = color match {
  case Style.ColorHex (var rgb, _) => cssColor (rgb)
  case Style.ColorName (var name, _) => "var(%s)".formatted (cssVar (name) )
  case null => null
  }
  val decoColor: String = if (colorRef != null) {
  "text-decoration-color: %s;".formatted (colorRef)
  }
  else {
  ""
  }
  decoLine + decoStyle + decoColor

    case Style.ColorHex( var rgb, var background) => if (background) {
  "background-color: %s".formatted (cssColor (rgb) )
  }
  else {
  "color: %s;".formatted (cssColor (rgb) )
  }
    case Style.ColorName( var name, var background) => if (background) {
  "background-color: var(%s);".formatted (cssVar (name) )
  }
  else {
  "color: var(%s);".formatted (cssVar (name) )
  }
    case _ => null
  }

  @NotNull def colorsToCss(@NotNull colorScheme: Nothing): String = colorScheme.definedColors.toImmutableSeq.view.map((t) => "%s: %s;".formatted(Html5Stylist.cssVar(t.component1), Html5Stylist.cssColor(t.component2))).joinToString("\n", "  %s".formatted)

  @NotNull def cssVar(@NotNull name: String): String = {
    return STR.
    "--\{normalizeCssId(name)}"
  }

  @NotNull def cssColor(rgb: Int): String = "#%06x".formatted(rgb)

  /**
   * <a href="https://stackoverflow.com/a/45519999/9506898">Thank you!</a>
   * <a href="https://jkorpela.fi/ucs.html8">ISO 10646 character listings</a>
   * <p>
   * In CSS, identifiers (including element names, classes, and IDs in selectors)
   * can contain only the characters [a-zA-Z0-9] and ISO 10646 characters U+00A0 and higher,
   * plus the hyphen (-) and the underscore (_);
   * they cannot start with a digit, two hyphens, or a hyphen followed by a digit.
   * Identifiers can also contain escaped characters and any ISO 10646 character as a numeric code (see next item).
   * For instance, the identifier {@code B&W?} may be written as
   * {@code B\&W\?} or {@code B\26 W\3F}.
   */
  @NotNull def normalizeCssId(@NotNull selector: String): String = {
    selector = selector.replaceAll("::", "-") // note: scope::name -> scope-name
    // Java's `Pattern` assumes only ASCII text are matched, where `T²` will be incorrectly normalize to "T?".
    // But according to CSS3, `T²` is a valid identifier.
    val builder = new lang.StringBuilder
    for (c <- selector.toCharArray) {
      if (Character.isLetterOrDigit(c) || c >= 0x00A0 || c == '-' || c == '_') builder.append(c)
      else builder.append(Integer.toHexString(c))
    }
    builder.toString
  }
}

class Html5Stylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing) extends Nothing(colorScheme, styleFamily) {
  @NotNull protected def formatItalic(outer: util.EnumSet[Nothing]) = new Nothing("<i>", "</i>", false)

  @NotNull protected def formatBold(outer: util.EnumSet[Nothing]) = new Nothing("<b>", "</b>", false)

  @NotNull protected def formatLineThrough(@NotNull line: Style.LineThrough, outer: util.EnumSet[Nothing]) = new Nothing("<span style=\"%s\">".formatted(Html5Stylist.styleToCss(line)), "</span>", false)

  @NotNull protected def formatColorHex(rgb: Int, background: Boolean) = new Nothing("<span style=\"%s:%s;\">".formatted(if (background) "background-color"
  else "color", Html5Stylist.cssColor(rgb)), "</span>", false)
}
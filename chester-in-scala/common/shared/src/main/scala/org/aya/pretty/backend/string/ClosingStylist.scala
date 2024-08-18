// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.string

import kala.collection.Seq
import kala.collection.SeqView
import kala.collection.immutable.ImmutableSeq
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.jetbrains.annotations.NotNull
import java.util
import java.util.function.Consumer

object ClosingStylist {
  object StyleToken {
    @NotNull val NULL = new ClosingStylist.StyleToken((_: Nothing) => {
    }, (_: Nothing) => {
    })
  }

  final class StyleToken(@NotNull start: Consumer[Nothing], @NotNull end: Consumer[Nothing]) {
    this.start = start
    this.end = end
    @NotNull final private val start: Consumer[Nothing] = null
    @NotNull final private val end: Consumer[Nothing] = null

    def this(@NotNull start: String, @NotNull end: String, visible: Boolean) {
      this((c: Nothing) => c.content(start, visible), (c: Nothing) => c.content(end, visible))
    }
  }

  class Delegate(@NotNull protected val delegate: ClosingStylist) extends ClosingStylist(delegate.colorScheme, delegate.styleFamily) {
    @NotNull override protected def formatCustom(style: Style.CustomStyle): ClosingStylist.StyleToken = delegate.formatCustom(style)

    @NotNull override protected def formatPresetStyle(@NotNull styleName: String, outer: util.EnumSet[Nothing]): Nothing = delegate.formatPresetStyle(styleName, outer)

    @NotNull override protected def formatPresetColor(@NotNull colorName: String, background: Boolean): ClosingStylist.StyleToken = delegate.formatPresetColor(colorName, background)

    @NotNull override protected def formatItalic(outer: util.EnumSet[Nothing]): ClosingStylist.StyleToken = delegate.formatItalic(outer)

    @NotNull override protected def formatBold(outer: util.EnumSet[Nothing]): ClosingStylist.StyleToken = delegate.formatBold(outer)

    @NotNull override protected def formatLineThrough(@NotNull line: Style.LineThrough, outer: util.EnumSet[Nothing]): ClosingStylist.StyleToken = delegate.formatLineThrough(line, outer)

    @NotNull override protected def formatColorHex(rgb: Int, background: Boolean): ClosingStylist.StyleToken = delegate.formatColorHex(rgb, background)
  }
}

abstract class ClosingStylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing) extends Nothing(colorScheme, styleFamily) {
  def format(@NotNull styles: Nothing, @NotNull cursor: Nothing, outer: util.EnumSet[Nothing], @NotNull inside: Runnable): Unit = {
    formatInternal(styles.view, cursor, outer, inside)
  }

  private def formatInternal(@NotNull styles: Nothing, @NotNull cursor: Nothing, outer: util.EnumSet[Nothing], @NotNull inside: Runnable): Unit = {
    if (styles.isEmpty) {
      inside.run()
      return
    }
    val style = styles.getFirst
    val formats = if (style.isInstanceOf[Style.Preset]) formatPresetStyle(preset.styleName, outer)
    else ImmutableSeq.of(formatOne(style, outer))
    formats.forEach((format) => format.start.accept(cursor))
    formatInternal(styles.drop(1), cursor, outer, inside)
    formats.reversed.forEach((format) => format.end.accept(cursor))
  }

  @NotNull private def formatOne(@NotNull style: Style, outer: util.EnumSet[Nothing]) = style match {
    case Style.Attr attr =>
      attr match {
        case Italic => formatItalic(outer)
        case Bold => formatBold(outer)
      }
    case Style.LineThrough line => formatLineThrough(line, outer)
    case Style.ColorName color => formatPresetColor(color.colorName, color.background)
    case Style.ColorHex hex => formatColorHex(hex.color, hex.background)
    case Style.CustomStyle custom => formatCustom(custom)
    case _ => ClosingStylist.StyleToken.NULL
  }

  @NotNull protected def formatPresetStyle(@NotNull styleName: String, outer: util.EnumSet[Nothing]): Nothing = styleFamily.definedStyles.getOption(styleName).getOrDefault((it) => it.styles.map((sub) => formatOne(sub, outer)), ImmutableSeq.empty)

  @NotNull protected def formatPresetColor(@NotNull colorName: String, background: Boolean): ClosingStylist.StyleToken = colorScheme.definedColors.getOption(colorName).getOrDefault((it) => formatColorHex(it, background), ClosingStylist.StyleToken.NULL)

  @NotNull protected def formatCustom(@NotNull style: Style.CustomStyle): ClosingStylist.StyleToken = ClosingStylist.StyleToken.NULL

  @NotNull protected def formatItalic(outer: util.EnumSet[Nothing]): ClosingStylist.StyleToken

  @NotNull protected def formatBold(outer: util.EnumSet[Nothing]): ClosingStylist.StyleToken

  @NotNull protected def formatLineThrough(@NotNull line: Style.LineThrough, outer: util.EnumSet[Nothing]): ClosingStylist.StyleToken

  @NotNull protected def formatColorHex(rgb: Int, background: Boolean): ClosingStylist.StyleToken
}
// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.doc

import kala.collection.mutable.MutableList
import org.aya.pretty.printer.ColorScheme
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.io.Serializable

/**
 * Text styles. Inspired by terminal-colors.d(5)
 *
 * @author kiva
 */
object Style {
  final class Attr extends Style {}

  trait Color extends Style {}

  object LineThrough {
    object Position extends Enumeration {
      type Position = Value
      val Underline, Overline, Strike = Value
    }

    object Shape extends Enumeration {
      type Shape = Value
      val Solid, Curly = Value
    }
  }

  final class LineThrough(@NotNull position: LineThrough.Position, @NotNull shape: LineThrough.Shape, @Nullable color: Style.Color) extends Style {
    this.position = position
    this.shape = shape
    this.color = color
    @NotNull final private val position: LineThrough.Position = null
    @NotNull final private val shape: LineThrough.Shape = null
    @Nullable final private val color: Style.Color = null
  }

  final class ColorName(@NotNull colorName: String, background: Boolean) extends Style.Color {
    this.colorName = colorName
    this.background = background
    @NotNull final private val colorName: String = null
    final private val background = false
  }

  final class ColorHex(color: Int, background: Boolean) extends Style.Color {
    this.color = color
    this.background = background
    final private val color = 0
    final private val background = false
  }

  final class Preset(@NotNull styleName: String) extends Style {
    this.styleName = styleName
    @NotNull final private val styleName: String = null
  }

  /**
   * Make your custom style a subclass of this one. For example:
   * <pre>
   * enum UnixTermStyle implements CustomStyle {
   * DoubleUnderline,
   * CurlyUnderline,
   * }
   * </pre>
   */
  trait CustomStyle extends Style {}

  @NotNull def italic: Style = Attr.Italic

  @NotNull def bold: Style = Attr.Bold

  @NotNull def strike = new Style.LineThrough(LineThrough.Position.Strike, LineThrough.Shape.Solid, null)

  @NotNull def underline = new Style.LineThrough(LineThrough.Position.Underline, LineThrough.Shape.Solid, null)

  @NotNull def color(@NotNull colorName: String) = new Style.ColorName(colorName, false)

  @NotNull def colorBg(@NotNull colorName: String) = new Style.ColorName(colorName, true)

  @NotNull def color(color: Int) = new Style.ColorHex(color, false)

  @NotNull def color(r: Float, g: Float, b: Float) = new Style.ColorHex(ColorScheme.colorOf(r, g, b), false)

  @NotNull def colorBg(color: Int) = new Style.ColorHex(color, true)

  @NotNull def preset(@NotNull styleName: String) = new Style.Preset(styleName)
}

trait Style extends Serializable {
  def and = new Styles(MutableList.of(this))
}
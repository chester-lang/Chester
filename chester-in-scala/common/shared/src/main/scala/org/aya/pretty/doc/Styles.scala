// Copyright (c) 2020-2022 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.doc

import kala.collection.mutable.MutableList
import org.jetbrains.annotations.NotNull

object Styles {
  @NotNull def empty = new Styles(MutableList.create)
}

final class Styles(@NotNull styles: Nothing) {
  this.styles = styles
  @NotNull final private val styles: Nothing = null

  @NotNull def italic: Styles = {
    styles.append(Style.italic)
    this
  }

  @NotNull def bold: Styles = {
    styles.append(Style.bold)
    this
  }

  @NotNull def strike: Styles = {
    styles.append(Style.strike)
    this
  }

  @NotNull def underline: Styles = {
    styles.append(Style.underline)
    this
  }

  @NotNull def color(@NotNull colorName: String): Styles = {
    styles.append(Style.color(colorName))
    this
  }

  @NotNull def colorBG(@NotNull colorName: String): Styles = {
    styles.append(Style.colorBg(colorName))
    this
  }

  @NotNull def color(color: Int): Styles = {
    styles.append(Style.color(color))
    this
  }

  @NotNull def color(r: Float, g: Float, b: Float): Styles = {
    styles.append(Style.color(r, g, b))
    this
  }

  @NotNull def colorBG(color: Int): Styles = {
    styles.append(Style.colorBg(color))
    this
  }

  @NotNull def custom(@NotNull style: Style.CustomStyle): Styles = {
    styles.append(style)
    this
  }

  @NotNull def preset(@NotNull styleName: String): Styles = {
    styles.append(Style.preset(styleName))
    this
  }
}
// Copyright (c) 2020-2021 Yinsen (Tesla) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.printer

import kala.collection.mutable.MutableMap
import org.jetbrains.annotations.NotNull

object ColorScheme {
  def colorOf(r: Float, g: Float, b: Float): Int = {
    val red = (r * 0xFF).toInt
    val green = (g * 0xFF).toInt
    val blue = (b * 0xFF).toInt
    red << 16 | green << 8 | blue
  }
}

trait ColorScheme {
  @NotNull def definedColors: Nothing
}
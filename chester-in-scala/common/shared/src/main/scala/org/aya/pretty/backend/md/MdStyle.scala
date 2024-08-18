// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.md

import org.aya.pretty.doc.Style
import org.jetbrains.annotations.NotNull

object MdStyle {
  /** GitHub flavored markdown */
  final class GFM extends MdStyle {}

  @NotNull def h(level: Int) = new MdStyle.Heading(level)

  final class Heading(level: Int) extends MdStyle {
    this.level = level
    final private val level = 0
  }
}

trait MdStyle extends Style.CustomStyle {}
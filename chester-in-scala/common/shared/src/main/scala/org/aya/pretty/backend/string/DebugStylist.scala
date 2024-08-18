// Copyright (c) 2020-2022 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.string

import kala.collection.Seq
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.aya.pretty.style.AyaColorScheme
import org.aya.pretty.style.AyaStyleFamily
import org.jetbrains.annotations.NotNull
import java.util

/**
 * @author kiva, ice1000
 */
object DebugStylist {
  val DEFAULT = new DebugStylist(AyaColorScheme.INTELLIJ, AyaStyleFamily.DEFAULT)
}

class DebugStylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing) extends Nothing(colorScheme, styleFamily) {
  def format(@NotNull style: Nothing, @NotNull cursor: Cursor, outer: util.EnumSet[Nothing], @NotNull inside: Runnable): Unit = {
    inside.run()
  }
}
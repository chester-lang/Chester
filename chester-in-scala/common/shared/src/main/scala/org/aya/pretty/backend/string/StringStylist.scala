// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.string

import kala.collection.Seq
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.aya.pretty.printer.Stylist
import org.jetbrains.annotations.NotNull
import java.util

abstract class StringStylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing) extends Nothing(colorScheme, styleFamily) {
  def format(@NotNull style: Nothing, @NotNull cursor: Cursor, outer: util.EnumSet[StringPrinter.Outer.type], @NotNull inside: Runnable): Unit
}
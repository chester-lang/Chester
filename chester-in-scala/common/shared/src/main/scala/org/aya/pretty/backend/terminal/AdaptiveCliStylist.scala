// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.terminal

import kala.collection.mutable.MutableMap
import kala.tuple.Tuple
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.aya.pretty.style.AyaStyleKey
import org.jetbrains.annotations.NotNull

/** use colors from terminal instead of absolute colors to protect eyes */
object AdaptiveCliStylist {
  @NotNull val INSTANCE = new AdaptiveCliStylist(true)
  @NotNull val INSTANCE_16 = new AdaptiveCliStylist(false)
}

class AdaptiveCliStylist private(use256Colors: Boolean) extends Nothing(MutableMap.create, new Nothing(MutableMap.ofEntries(Tuple.of(AyaStyleKey.Keyword.key, (if (use256Colors) Style.color(ColorScheme.colorOf(1.0f, 0.43f, 0))
else UnixTermStyle.TerminalRed).and), Tuple.of(AyaStyleKey.Prim.key, (if (use256Colors) Style.color(ColorScheme.colorOf(1.0f, 0.43f, 0))
else UnixTermStyle.TerminalRed).and), Tuple.of(AyaStyleKey.Fn.key, UnixTermStyle.TerminalYellow.and), Tuple.of(AyaStyleKey.Data.key, UnixTermStyle.TerminalGreen.and), Tuple.of(AyaStyleKey.Clazz.key, UnixTermStyle.TerminalGreen.and), Tuple.of(AyaStyleKey.Con.key, UnixTermStyle.TerminalBlue.and), Tuple.of(AyaStyleKey.Member.key, UnixTermStyle.TerminalBlue.and), Tuple.of(AyaStyleKey.Generalized.key, Style.italic.and), Tuple.of(AyaStyleKey.Comment.key, Style.italic.and), Tuple.of(AyaStyleKey.LocalVar.key, Style.italic.and)))) {
}
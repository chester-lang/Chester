// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.terminal

import org.aya.pretty.backend.string.ClosingStylist
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.doc.Style
import org.aya.pretty.printer.ColorScheme
import org.aya.pretty.printer.StyleFamily
import org.jetbrains.annotations.NotNull
import java.util

class UnixTermStylist(@NotNull colorScheme: Nothing, @NotNull styleFamily: Nothing) extends ClosingStylist(colorScheme, styleFamily) {
  @NotNull protected def formatItalic(outer: util.EnumSet[StringPrinter.Outer.type]) = new ClosingStylist.StyleToken("\033[3m", "\033[23m", false)

  @NotNull protected def formatBold(outer: util.EnumSet[StringPrinter.Outer.type]) = new ClosingStylist.StyleToken("\033[1m", "\033[22m", false)

  @NotNull protected def formatLineThrough(@NotNull line: Style.LineThrough, outer: util.EnumSet[StringPrinter.Outer.type]): ClosingStylist.StyleToken = line.position match {
    case Underline => new ClosingStylist.StyleToken("\033[4m", "\033[24m", false)
    case Strike => new ClosingStylist.StyleToken("\033[9m", "\033[29m", false)
    case Overline => StyleToken.NULL
  }

  @NotNull override protected def formatCustom(style: Style.CustomStyle): ClosingStylist.StyleToken = {
    if (style.isInstanceOf[UnixTermStyle]) return termStyle match {
      case Dim => new ClosingStylist.StyleToken("\033[2m", "\033[22m", false)
      case DoubleUnderline => new ClosingStylist.StyleToken("\033[21m", "\033[24m", false)
      case CurlyUnderline => new ClosingStylist.StyleToken("\033[4:3m", "\033[4:0m", false)
      case Overline => new ClosingStylist.StyleToken("\033[53m", "\033[55m", false)
      case Blink => new ClosingStylist.StyleToken("\033[5m", "\033[25m", false)
      case Reverse => new ClosingStylist.StyleToken("\033[7m", "\033[27m", false)
      case TerminalRed => new ClosingStylist.StyleToken("\033[31m", "\033[39m", false)
      case TerminalGreen => new ClosingStylist.StyleToken("\033[32m", "\033[39m", false)
      case TerminalBlue => new ClosingStylist.StyleToken("\033[34m", "\033[39m", false)
      case TerminalYellow => new ClosingStylist.StyleToken("\033[33m", "\033[39m", false)
      case TerminalPurple => new ClosingStylist.StyleToken("\033[35m", "\033[39m", false)
      case TerminalCyan => new ClosingStylist.StyleToken("\033[36m", "\033[39m", false)
    }
    super.formatCustom(style)
  }

  @NotNull override protected def formatColorHex(rgb: Int, bg: Boolean): ClosingStylist.StyleToken = {
    val r = (rgb & 0xFF0000) >> 16
    val g = (rgb & 0xFF00) >> 8
    val b = rgb & 0xFF
    new ClosingStylist.StyleToken(String.format("\033[%d;2;%d;%d;%dm", if (bg) 48
    else 38, r, g, b), String.format("\033[%dm", if (bg) 49
    else 39), false)
  }
}
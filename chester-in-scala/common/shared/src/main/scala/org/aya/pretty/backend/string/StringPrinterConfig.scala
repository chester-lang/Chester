// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.string

import org.aya.pretty.printer.PrinterConfig
import org.jetbrains.annotations.NotNull

object StringPrinterConfig {
  final class TextOptions extends Nothing {}

  final class StyleOptions extends Nothing {}

  final class LinkOptions extends Nothing {}
}

class StringPrinterConfig[S <: StringStylist](@NotNull stylist: S) extends Nothing(stylist) {
}
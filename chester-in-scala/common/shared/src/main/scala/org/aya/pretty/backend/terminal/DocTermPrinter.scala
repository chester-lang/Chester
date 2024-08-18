// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.terminal

import org.aya.pretty.backend.string.Cursor
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.backend.string.StringPrinterConfig
import org.aya.pretty.doc.Doc
import org.jetbrains.annotations.NotNull
import java.util

object DocTermPrinter {
  class Config(@NotNull stylist: Nothing) extends StringPrinterConfig[Nothing](stylist) {
  }
}

class DocTermPrinter extends StringPrinter[DocTermPrinter.Config] {
  protected def renderInlineCode(@NotNull cursor: Cursor, code: Doc.InlineCode, outer: util.EnumSet[StringPrinter.Outer.type]): Unit = {
    cursor.visibleContent("`")
    renderDoc(cursor, code.code, util.EnumSet.of(Outer.Code))
    cursor.visibleContent("'")
  }
}
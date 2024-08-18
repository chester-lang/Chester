// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.html

import org.intellij.lang.annotations.Language
import org.jetbrains.annotations.NotNull
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.stream.Collectors

object HtmlConstants {
  @NotNull var HOVER_TYPE_POPUP_STYLE: String = null
  @NotNull var HOVER_STYLE: String = null

  @NotNull private def readResource(name: String) = try {
    val stream = classOf[HtmlConstants].getResourceAsStream(name)
    try {
      assert(stream != null)
      try {
        val reader = new BufferedReader(new InputStreamReader(stream))
        try reader.lines.collect(Collectors.joining("\n"))
        finally if (reader != null) reader.close()
      }
    } catch {
      case e: IOException =>
        throw new RuntimeException(e)
    } finally if (stream != null) stream.close()
  }

  @NotNull private def tag(tag: String, read: String) = "<" + tag + ">\n" + read + "\n</" + tag + ">\n"

  /**
   * wrap JavaScript code segment in a new scope,
   * for possible local variable name clashing.
   */
  @NotNull private def scoped(read: String) = "{\n" + read.indent(2) + "\n}\n"

  @NotNull var HOVER_ALL_OCCURS_JS_HIGHLIGHT_FN: String = null
  @NotNull var HOVER_ALL_OCCURS_JS_INIT: String = null
  @NotNull var HOVER_SHOW_TOOLTIP_JS_SHOW_FN: String = null
  @NotNull var HOVER_SHOW_TOOLTIP_JS_INIT: String = null
  @Language(value = "HTML")
  @NotNull val HOVER: String =
    """
    <script>
    """ + HOVER_ALL_OCCURS_JS_HIGHLIGHT_FN + HOVER_SHOW_TOOLTIP_JS_SHOW_FN +
      """
    window.onload = function () {
    """ + scoped(HOVER_ALL_OCCURS_JS_INIT) + scoped(HOVER_SHOW_TOOLTIP_JS_INIT) +
      """
    };
    </script>
    """
  @Language(value = "HTML")
  @NotNull val HOVER_SSR: String =
    """
    <script>
    export default {
      mounted() {
    """ + HOVER_ALL_OCCURS_JS_HIGHLIGHT_FN + HOVER_SHOW_TOOLTIP_JS_SHOW_FN +
      """
    """ + scoped(HOVER_ALL_OCCURS_JS_INIT) + scoped(HOVER_SHOW_TOOLTIP_JS_INIT) +
      """
      }
    }
    </script>
    """
  @NotNull var KATEX_AUTO_RENDER_EXTERNAL_RESOURCES: String = null
  @NotNull var KATEX_AUTO_RENDER_INIT: String = null
  @NotNull val KATEX_AUTO_RENDER: String = KATEX_AUTO_RENDER_EXTERNAL_RESOURCES + tag("script", KATEX_AUTO_RENDER_INIT)
  try HOVER_TYPE_POPUP_STYLE = tag("style", readResource("/aya-html/hover-tooltip.css"))
  HOVER_STYLE = tag("style", readResource("/aya-html/hover.css"))
  HOVER_ALL_OCCURS_JS_HIGHLIGHT_FN = readResource("/aya-html/highlight-fn.js")
  HOVER_SHOW_TOOLTIP_JS_INIT = readResource("/aya-html/show-tooltip.js")
  HOVER_SHOW_TOOLTIP_JS_SHOW_FN = readResource("/aya-html/show-tooltip-fn.js")
  HOVER_ALL_OCCURS_JS_INIT = readResource("/aya-html/highlight-occurrences.js")
  KATEX_AUTO_RENDER_EXTERNAL_RESOURCES = readResource("/aya-html/katex.html")
  KATEX_AUTO_RENDER_INIT = readResource("/aya-html/katex-auto-render.js")
}
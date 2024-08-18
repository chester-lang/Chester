// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.latex

import kala.collection.Map
import kala.tuple.Tuple
import kala.tuple.Tuple2
import org.aya.pretty.backend.string.Cursor
import org.aya.pretty.backend.string.StringPrinter
import org.aya.pretty.backend.string.StringPrinterConfig
import org.aya.pretty.backend.string.StringStylist
import org.aya.pretty.doc.Doc
import org.jetbrains.annotations.NotNull
import java.util
import org.aya.pretty.backend.string.StringPrinterConfig.StyleOptions._

object DocTeXPrinter {
  @NotNull private def id(@NotNull name: String) = Tuple.of(name, name)

  /** similar to StringPrinter, but with mappings from source code unicode to LaTeX unicode. */
  @NotNull private val commandMapping = Map.ofEntries(Tuple.of("Sig", "\\Sigma"), Tuple.of("\\", "\\backslash"), Tuple.of("\\/", "\\lor"), Tuple.of("/\\", "\\land"), Tuple.of("|", "\\mid"), Tuple.of("=>", "\\Rightarrow"), Tuple.of("↑", "\\uparrow"), Tuple.of("->", "\\to"), Tuple.of("_|_", "\\bot"), Tuple.of("forall", "\\forall"), Tuple.of("Σ", "\\Sigma"), Tuple.of("∨", "\\lor"), Tuple.of("∧", "\\land"), Tuple.of("⇒", "\\Rightarrow"), Tuple.of("→", "\\to"), Tuple.of("⊥", "\\bot"), Tuple.of("∀", "\\forall"), Tuple.of("_", "\\_"), Tuple.of("~", "\\neg"), Tuple.of("**", "\\times"), id("(|"), id("|)"), Tuple.of("⦇", "(|"), Tuple.of("⦈", "|)"), id("[|"), id("|]"), Tuple.of("⟦", "[|"), Tuple.of("⟧", "|]"), Tuple.of("{|", "\\{|"), Tuple.of("|}", "|\\}"), Tuple.of("⦃", "\\{|"), Tuple.of("⦄", "|\\}"), id(":"), id("."), id(":="), id("="), id("("), id(")"), Tuple.of("{", "\\{"), Tuple.of("}", "\\}"))

  class Config(@NotNull stylist: Nothing) extends Nothing(stylist) {
    def this {
      this(TeXStylist.DEFAULT)
    }

    def katex: Boolean = getStylist.isKaTeX

    def separateStyle: Boolean = opt(SeparateStyle, false) && !katex
  }
}

class DocTeXPrinter extends Nothing {
  protected def renderHeader(@NotNull cursor: Nothing): Unit = {
    if (config.opt(HeaderCode, false)) {
      // cursor.invisibleContent("\\noindent{}");
      // This prevents us from using \raggedright followed by a \setlength\parindent.
      // We should expect users to deal with indentations themselves.
      renderStyleCommand(cursor)
    }
  }

  protected def renderStyleCommand(@NotNull cursor: Nothing): Unit = {
    if (!config.separateStyle) return
    if (!config.opt(StyleCode, false)) return
    // colors are converted to `\definecolor` in package xcolor.
    val colors = TeXStylist.colorsToTex(config.getStylist.colorScheme)
    cursor.invisibleContent(colors + "\n")
    // styles are converted to `\newcommand`.
    config.getStylist.styleFamily.definedStyles.forEach((name, style) => {
      val cmdName = TeXStylist.styleKeyToTex(name)
      val cmd = TeXStylist.stylesToTexCmd(style, "#1")
      val line = "\\newcommand\\%s[1]{%s}".formatted(cmdName, cmd)
      cursor.invisibleContent(line + "\n")
    })
  }

  @NotNull protected def prepareStylist: Nothing = if (config.separateStyle) new Nothing(config.getStylist)
  else super.prepareStylist

  @NotNull protected def escapePlainText(@NotNull content: String, outer: util.EnumSet[Nothing]): String = {
    // `Outer.Code` means we are in the minted environment --- no need to escape
    if (outer.contains(Outer.Code)) return content
    // TODO: math mode escape?
    if (outer.contains(Outer.Math)) return content
    content.replace("\\", "").replace("_", "\\textunderscore{}").replace("  ", makeIndent(2)).replace(" ", makeIndent(1))
  }

  protected def renderSpecialSymbol(@NotNull cursor: Nothing, @NotNull text: String, outer: util.EnumSet[Nothing]): Unit = {
    import scala.collection.JavaConversions._
    for (k <- DocTeXPrinter.commandMapping.keysView) {
      if (text == k) {
        if (!config.katex) cursor.invisibleContent("\\(")
        cursor.visibleContent(DocTeXPrinter.commandMapping.get(k))
        if (!config.katex) cursor.invisibleContent("\\)")
        return
      }
    }
    if (!text.chars.allMatch(Character.isLetter)) System.err.println("Warn: unknown symbol " + text)
    renderPlainText(cursor, text, outer)
  }

  @NotNull protected def makeIndent(indent: Int): String = {
    if (indent == 0) return ""
    "\\hspace{" + indent * 0.5 + "em}"
  }

  protected def renderHardLineBreak(@NotNull cursor: Nothing, outer: util.EnumSet[Nothing]): Unit = {
    if (outer.contains(Outer.List)) cursor.lineBreakWith("\n") // list items are separated by source code new line `\n`
    else cursor.lineBreakWith("~\\\\\n") // LaTeX uses `\\` for printed line breaks.
  }

  protected def renderInlineCode(@NotNull cursor: Nothing, code: Doc.InlineCode, outer: util.EnumSet[Nothing]): Unit = {
    formatInline(cursor, code.code, "\\texttt{", "}", outer)
    // ^ `Outer.Code` is only for minted. Do not switch to code mode.
  }

  protected def renderCodeBlock(@NotNull cursor: Nothing, code: Doc.CodeBlock, outer: util.EnumSet[Nothing]): Unit = {
    separateBlockIfNeeded(cursor, outer)
    if (code.language.isAya) renderDoc(cursor, code.code, outer) // `Outer.Code` is only for minted. Do not switch to code mode.
    else formatBlock(cursor, code.code, "\\begin{minted}[%s]".formatted(code.language.displayName.toLowerCase), "\\end{minted}", util.EnumSet.of(Outer.Code))
  }

  protected def renderInlineMath(@NotNull cursor: Nothing, code: Doc.InlineMath, outer: util.EnumSet[Nothing]): Unit = {
    formatInline(cursor, code.formula, "$", "$", util.EnumSet.of(Outer.Math))
  }

  protected def renderMathBlock(@NotNull cursor: Nothing, block: Doc.MathBlock, outer: util.EnumSet[Nothing]): Unit = {
    separateBlockIfNeeded(cursor, outer)
    formatBlock(cursor, block.formula, "\\[", "\\]", util.EnumSet.of(Outer.Math))
  }

  protected def renderList(@NotNull cursor: Nothing, list: Doc.List, outer: util.EnumSet[Nothing]): Unit = {
    val env = if (list.isOrdered) "enumerate"
    else "itemize"
    separateBlockIfNeeded(cursor, outer)
    formatBlock(cursor, "\\begin{" + env + "}", "\\end{" + env + "}", outer, () => formatList(cursor, list, (idx) => "\\item", outer))
  }
}
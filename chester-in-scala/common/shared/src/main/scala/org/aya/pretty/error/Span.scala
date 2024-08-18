// Copyright (c) 2020-2022 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.error

import org.jetbrains.annotations.NotNull

object Span {
  object NowLoc extends Enumeration {
    type NowLoc = Value
    val Shot, Start, End, Between, None = Value
  }

  final class Data(startLine: Int, startCol: Int, endLine: Int, endCol: Int) {
    this.startLine = startLine
    this.startCol = startCol
    this.endLine = endLine
    this.endCol = endCol
    final private val startLine = 0
    final private val startCol = 0
    final private val endLine = 0
    final private val endCol = 0

    def oneLinear: Boolean = startLine == endLine

    def nowLoc(currentLine: Int): Span.NowLoc = {
      if (currentLine == startLine) return if (oneLinear) NowLoc.Shot
      else NowLoc.Start
      if (currentLine == endLine) return NowLoc.End
      if (currentLine > startLine && currentLine < endLine) return NowLoc.Between
      NowLoc.None
    }

    @NotNull def union(@NotNull other: Span.Data) = new Span.Data(Math.min(startLine, other.startLine), Math.max(startCol, other.startCol), Math.max(endLine, other.endLine), Math.max(endCol, other.endCol))
  }
}

trait Span {
  @NotNull def input: String

  @NotNull def normalize(config: PrettyErrorConfig): Span.Data
}
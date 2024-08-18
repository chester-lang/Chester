// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.error

import org.jetbrains.annotations.NotNull

final class LineColSpan(@NotNull input: String, startLine: Int, startCol: Int, endLine: Int, endCol: Int) extends Nothing {
  this.input = input
  this.startLine = startLine
  this.startCol = startCol
  this.endLine = endLine
  this.endCol = endCol
  @NotNull final private val input: String = null
  final private val startLine = 0
  final private val startCol = 0
  final private val endLine = 0
  final private val endCol = 0

  @NotNull def normalize(config: Nothing) = new Nothing(startLine, startCol, endLine, endCol)
}
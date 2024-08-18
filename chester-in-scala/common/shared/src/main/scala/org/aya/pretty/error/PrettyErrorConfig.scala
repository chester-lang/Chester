// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.error

import org.jetbrains.annotations.NotNull

/**
 * @author kiva
 */
object PrettyErrorConfig {
  @NotNull val DEFAULT: PrettyErrorConfig = new PrettyErrorConfig() {}
}

trait PrettyErrorConfig {
  /**
   * Returns the number of spaces occupied by the tab in different terminals.
   * e.g. return 4 for normal console output, 2 for compact format style.
   *
   * @return space count
   */
  def tabWidth = 4

  /**
   * Show more lines before startLine and after endLine
   *
   * @return line count
   */
  def showMore = 2
}
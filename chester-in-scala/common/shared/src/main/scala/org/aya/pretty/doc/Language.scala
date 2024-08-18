// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.doc

import kala.collection.immutable.ImmutableSeq
import org.jetbrains.annotations.NotNull

/** Languages supported by {@link Doc# code} and {@link Doc# codeBlock} */
object Language {
  @NotNull def of(@NotNull displayName: String): Language = {
    for (e <- Builtin.values) {
      if (e.displayName.equalsIgnoreCase(displayName)) return e
    }
    new Language.Some(displayName)
  }

  final class Some(@NotNull displayName: String) extends Language {
    this.displayName = displayName
    @NotNull final private val displayName: String = null

    @NotNull override def parentLanguage: Nothing = ImmutableSeq.empty
  }

  final class Builtin private(private[doc] val displayName: String) extends Language {
  }
}

trait Language {
  @NotNull def displayName: String

  @NotNull def parentLanguage: Nothing

  def isAya: Boolean = (this eq Language.Builtin.Aya) || parentLanguage.anyMatch(Language.isAya)
}
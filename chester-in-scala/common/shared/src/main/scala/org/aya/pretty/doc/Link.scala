// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.doc

import kala.collection.immutable.ImmutableSeq
import kala.control.Either
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.io.Serializable

/**
 * @author imkiva
 */
object Link {
  @NotNull def page(@NotNull link: String) = new Link.DirectLink(link)

  @NotNull def cross(@NotNull path: Nothing, @Nullable location: Link.LocalId) = new Link.CrossLink(path, location)

  @NotNull def loc(@NotNull where: String) = new Link.LocalId(Either.left(where))

  @NotNull def loc(where: Int) = new Link.LocalId(Either.right(where))

  final class DirectLink(@NotNull link: String) extends Link {
    this.link = link
    @NotNull final private val link: String = null
  }

  final class CrossLink(@NotNull path: Nothing, @Nullable location: Link.LocalId) extends Link {
    this.path = path
    this.location = location
    @NotNull final private val path: Nothing = null
    @Nullable final private val location: Link.LocalId = null
  }

  final class LocalId(@NotNull `type`: Nothing) extends Link {
    this.`type` = `type`
    @NotNull final private val `type`: Nothing = null
  }
}

trait Link extends Serializable {}
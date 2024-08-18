// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.style

import org.aya.pretty.doc.Style

/**
 * The key syntax:
 * <pre>
 * key ::= (key '::') key
 * | [A-Za-z_][A-Za-z0-9_]*
 * </pre>
 * The '::' is used to indicate scopes in some backends (as some backends do not have scopes).
 * For example, we will use `::` to distinguish nested "class" attributes in the HTML backend.
 *
 * @see org.aya.pretty.backend.html.Html5Stylist#styleKeyToCss(String)
 * @see org.aya.pretty.backend.latex.TeXStylist#styleKeyToTex(String)
 */
object AyaStyleKey extends Enumeration {
  type AyaStyleKey = Value
  val Keyword, Fn, Prim, Data, Con, Clazz, Member, Generalized, CallTerm, Error, Warning, Goal, Comment, LocalVar = Value
  private val key = nulldef
  this (key: String) {
    this ()
    this.key = key
  }

  def key: String = key

  def preset: Style = Style.preset(key)
}
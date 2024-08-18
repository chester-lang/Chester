// Copyright (c) 2020-2024 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.backend.string

import org.jetbrains.annotations.NotNull

class Cursor(private val printer: Nothing) {
  private var cursor = 0
  private var nestLevel = 0
  private var lineStartCursor = 0
  final private val builder = new lang.StringBuilder

  @NotNull def result: CharSequence = builder

  def getCursor: Int = cursor

  def getNestLevel: Int = nestLevel

  def content(@NotNull content: CharSequence, visible: Boolean): Unit = {
    if (visible) visibleContent(content)
    else invisibleContent(content)
  }

  def invisibleContent(@NotNull content: CharSequence): Unit = {
    checkLineStart()
    builder.append(content)
  }

  def visibleContent(@NotNull content: CharSequence): Unit = {
    invisibleContent(content)
    moveForward(content.length)
  }

  private def checkLineStart(): Unit = {
    if (isAtLineStart) {
      builder.append(printer.makeIndent(nestLevel))
      moveForward(nestLevel)
    }
  }

  /** Do something when I am not at line start. */
  def whenLineUsed(@NotNull runnable: Runnable): Unit = {
    if (!isAtLineStart) runnable.run()
  }

  def lineBreakWith(@NotNull lineBreak: CharSequence): Unit = {
    invisibleContent(lineBreak)
    moveToNewLine()
  }

  def isAtLineStart: Boolean = cursor == lineStartCursor

  def moveToNewLine(): Unit = {
    cursor = lineStartCursor = 0
  }

  def moveForward(count: Int): Unit = {
    cursor += Math.max(0, count)
  }

  def nested(nest: Int, @NotNull r: Runnable): Unit = {
    nestLevel += nest
    r.run()
    nestLevel -= nest
  }
}
// Copyright (c) 2020-2023 Tesla (Yinsen) Zhang.
// Use of this source code is governed by the MIT license that can be found in the LICENSE.md file.
package org.aya.pretty.printer

import kala.collection.mutable.MutableMap
import org.jetbrains.annotations.NotNull

/**
 * This class was designed to support various PrettyPrint backend.
 * Example usage:
 * <pre>
 * public class HtmlPrinterConfig implements PrinterConfig {}
 * </pre>
 * <p>
 * For a more practical example, see {@link org.aya.pretty.backend.string.StringPrinterConfig}
 *
 * @author kiva
 */
object PrinterConfig {
  /**
   * Indicate that the width or height has infinite size.
   */
  val INFINITE_SIZE: Int = -1

  trait Options[T] {}

  final class PageOptions extends PrinterConfig.Options[Integer] {}

  /**
   * Basic configure for other configs to easily extend config flags.
   */
  class Basic[S <: Stylist](@NotNull private val stylist: S) extends PrinterConfig {
    @NotNull final protected val options = MutableMap.create

    @SuppressWarnings(Array("unchecked"))
    @NotNull def opt[T](@NotNull key: PrinterConfig.Options[T], @NotNull defaultValue: T): T = options.getOrDefault(key, defaultValue).asInstanceOf[T]

    def set[T](@NotNull key: PrinterConfig.Options[T], @NotNull value: T): Unit = {
      options.put(key, value)
    }

    @NotNull override def getStylist: S = stylist

    override def getPageWidth: Int = opt(PageOptions.PageWidth, INFINITE_SIZE)

    override def getPageHeight: Int = opt(PageOptions.PageHeight, INFINITE_SIZE)
  }
}

trait PrinterConfig {
  /**
   * The character count that a line can hold.
   *
   * @return page width or -1 for infinity page width.
   */
    def getPageWidth: Int = PrinterConfig.INFINITE_SIZE

  /**
   * The line count that a page can hold.
   *
   * @return page height or -1 for infinity page height.
   */
  def getPageHeight: Int = PrinterConfig.INFINITE_SIZE

  @NotNull def getStylist: Nothing
}
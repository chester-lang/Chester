package typings.node.anon

import typings.node.nodeColonbufferMod.Blob
import typings.node.urlMod.URL_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeof_URL extends StObject {
  
  /**
    * Checks if an `input` relative to the `base` can be parsed to a `URL`.
    *
    * ```js
    * const isValid = URL.canParse('/foo', 'https://example.org/'); // true
    *
    * const isNotValid = URL.canParse('/foo'); // false
    * ```
    * @since v19.9.0
    * @param input The absolute or relative input URL to parse. If `input` is relative, then `base` is required. If `input` is absolute, the `base` is ignored. If `input` is not a string, it is
    * `converted to a string` first.
    * @param base The base URL to resolve against if the `input` is not absolute. If `base` is not a string, it is `converted to a string` first.
    */
  /* static member */
  def canParse(input: String): Boolean = js.native
  def canParse(input: String, base: String): Boolean = js.native
  
  /**
    * Creates a `'blob:nodedata:...'` URL string that represents the given `Blob` object and can be used to retrieve the `Blob` later.
    *
    * ```js
    * const {
    *   Blob,
    *   resolveObjectURL,
    * } = require('node:buffer');
    *
    * const blob = new Blob(['hello']);
    * const id = URL.createObjectURL(blob);
    *
    * // later...
    *
    * const otherBlob = resolveObjectURL(id);
    * console.log(otherBlob.size);
    * ```
    *
    * The data stored by the registered `Blob` will be retained in memory until `URL.revokeObjectURL()` is called to remove it.
    *
    * `Blob` objects are registered within the current thread. If using Worker
    * Threads, `Blob` objects registered within one Worker will not be available
    * to other workers or the main thread.
    * @since v16.7.0
    * @experimental
    */
  /* static member */
  def createObjectURL(blob: Blob): String = js.native
  
  /**
    * Parses a string as a URL. If `base` is provided, it will be used as the base URL for the purpose of resolving non-absolute `input` URLs.
    * Returns `null` if `input` is not a valid.
    * @param input The absolute or relative input URL to parse. If `input` is relative, then `base` is required. If `input` is absolute, the `base` is ignored. If `input` is not a string, it is
    * `converted to a string` first.
    * @param base The base URL to resolve against if the `input` is not absolute. If `base` is not a string, it is `converted to a string` first.
    * @since v22.1.0
    */
  /* static member */
  def parse(input: String): URL_ | Null = js.native
  def parse(input: String, base: String): URL_ | Null = js.native
  
  /**
    * Removes the stored `Blob` identified by the given ID. Attempting to revoke a
    * ID that isn't registered will silently fail.
    * @since v16.7.0
    * @experimental
    * @param id A `'blob:nodedata:...` URL string returned by a prior call to `URL.createObjectURL()`.
    */
  /* static member */
  def revokeObjectURL(id: String): Unit = js.native
}

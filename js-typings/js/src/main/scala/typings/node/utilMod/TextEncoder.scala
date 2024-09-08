package typings.node.utilMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@JSImport("util", "TextEncoder")
@js.native
open class TextEncoder () extends StObject {
  
  /**
    * UTF-8 encodes the `input` string and returns a `Uint8Array` containing the
    * encoded bytes.
    * @param [input='an empty string'] The text to encode.
    */
  def encode(): js.typedarray.Uint8Array = js.native
  def encode(input: String): js.typedarray.Uint8Array = js.native
  
  /**
    * UTF-8 encodes the `src` string to the `dest` Uint8Array and returns an object
    * containing the read Unicode code units and written UTF-8 bytes.
    *
    * ```js
    * const encoder = new TextEncoder();
    * const src = 'this is some data';
    * const dest = new Uint8Array(10);
    * const { read, written } = encoder.encodeInto(src, dest);
    * ```
    * @param src The text to encode.
    * @param dest The array to hold the encode result.
    */
  def encodeInto(src: String, dest: js.typedarray.Uint8Array): EncodeIntoResult = js.native
  
  /**
    * The encoding supported by the `TextEncoder` instance. Always set to `'utf-8'`.
    */
  val encoding: String = js.native
}

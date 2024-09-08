package typings.node.utilMod

import typings.node.anon.Fatal
import typings.node.anon.Stream
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@JSImport("util", "TextDecoder")
@js.native
open class TextDecoder () extends StObject {
  def this(encoding: String) = this()
  def this(encoding: String, options: Fatal) = this()
  def this(encoding: Unit, options: Fatal) = this()
  
  /**
    * Decodes the `input` and returns a string. If `options.stream` is `true`, any
    * incomplete byte sequences occurring at the end of the `input` are buffered
    * internally and emitted after the next call to `textDecoder.decode()`.
    *
    * If `textDecoder.fatal` is `true`, decoding errors that occur will result in a `TypeError` being thrown.
    * @param input An `ArrayBuffer`, `DataView`, or `TypedArray` instance containing the encoded data.
    */
  def decode(): String = js.native
  def decode(input: js.typedarray.ArrayBuffer): String = js.native
  def decode(input: js.typedarray.ArrayBuffer, options: Stream): String = js.native
  def decode(input: Null, options: Stream): String = js.native
  def decode(input: Unit, options: Stream): String = js.native
  def decode(input: ArrayBufferView): String = js.native
  def decode(input: ArrayBufferView, options: Stream): String = js.native
  
  /**
    * The encoding supported by the `TextDecoder` instance.
    */
  val encoding: String = js.native
  
  /**
    * The value will be `true` if decoding errors result in a `TypeError` being
    * thrown.
    */
  val fatal: Boolean = js.native
  
  /**
    * The value will be `true` if the decoding result will include the byte order
    * mark.
    */
  val ignoreBOM: Boolean = js.native
}

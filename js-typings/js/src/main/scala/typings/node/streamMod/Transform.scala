package typings.node.streamMod

import typings.node.bufferMod.global.BufferEncoding
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * Transform streams are `Duplex` streams where the output is in some way
  * related to the input. Like all `Duplex` streams, `Transform` streams
  * implement both the `Readable` and `Writable` interfaces.
  *
  * Examples of `Transform` streams include:
  *
  * * `zlib streams`
  * * `crypto streams`
  * @since v0.9.4
  */
@JSImport("stream", "Transform")
@js.native
open class Transform () extends StObject {
  def this(opts: TransformOptions) = this()
  
  def _flush(callback: TransformCallback): Unit = js.native
  
  def _transform(chunk: Any, encoding: BufferEncoding, callback: TransformCallback): Unit = js.native
}

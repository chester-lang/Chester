package typings.node.anon

import typings.node.zlibMod.CompressCallback
import typings.node.zlibMod.InputType
import typings.node.zlibMod.ZlibOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofinflateRaw extends StObject {
  
  /**
    * @since v0.6.0
    */
  def apply(buf: InputType, callback: CompressCallback): Unit = js.native
  def apply(buf: InputType, options: ZlibOptions, callback: CompressCallback): Unit = js.native
  
  def __promisify__(buffer: InputType): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(buffer: InputType, options: ZlibOptions): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
}

package typings.node.anon

import typings.node.zlibMod.BrotliOptions
import typings.node.zlibMod.CompressCallback
import typings.node.zlibMod.InputType
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofbrotliCompress extends StObject {
  
  def apply(buf: InputType, callback: CompressCallback): Unit = js.native
  /**
    * @since v11.7.0, v10.16.0
    */
  def apply(buf: InputType, options: BrotliOptions, callback: CompressCallback): Unit = js.native
  
  def __promisify__(buffer: InputType): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(buffer: InputType, options: BrotliOptions): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
}

package typings.node.anon

import typings.node.streamMod.Writable
import typings.node.streamWebMod.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofWritable extends StObject {
  
  /**
    * A utility method for creating a `Writable` from a web `WritableStream`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  def fromWeb(writableStream: WritableStream[Any]): Writable = js.native
  def fromWeb(writableStream: WritableStream[Any], options: PickWritableOptionsdecode): Writable = js.native
  
  /**
    * A utility method for creating a web `WritableStream` from a `Writable`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  def toWeb(streamWritable: Writable): WritableStream[Any] = js.native
}

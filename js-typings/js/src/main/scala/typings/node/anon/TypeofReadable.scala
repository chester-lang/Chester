package typings.node.anon

import typings.node.streamWebMod.ReadableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofReadable extends StObject {
  
  /**
    * A utility method for creating a `Readable` from a web `ReadableStream`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  def fromWeb(readableStream: ReadableStream[Any]): typings.node.streamMod.Readable = js.native
  def fromWeb(readableStream: ReadableStream[Any], options: PickReadableOptionsencodi): typings.node.streamMod.Readable = js.native
  
  /**
    * A utility method for creating a web `ReadableStream` from a `Readable`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  def toWeb(streamReadable: typings.node.streamMod.Readable): ReadableStream[Any] = js.native
}

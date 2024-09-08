package typings.node.streamMod

import typings.node.anon.PickReadableOptionsencodi
import typings.node.streamWebMod.ReadableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * @since v0.9.4
  */
@JSImport("stream", "Readable")
@js.native
open class Readable () extends StObject
object Readable {
  
  @JSImport("stream", "Readable")
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * A utility method for creating a `Readable` from a web `ReadableStream`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  inline def fromWeb(readableStream: ReadableStream[Any]): Readable = ^.asInstanceOf[js.Dynamic].applyDynamic("fromWeb")(readableStream.asInstanceOf[js.Any]).asInstanceOf[Readable]
  inline def fromWeb(readableStream: ReadableStream[Any], options: PickReadableOptionsencodi): Readable = (^.asInstanceOf[js.Dynamic].applyDynamic("fromWeb")(readableStream.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Readable]
  
  /**
    * A utility method for creating a web `ReadableStream` from a `Readable`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  inline def toWeb(streamReadable: Readable): ReadableStream[Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("toWeb")(streamReadable.asInstanceOf[js.Any]).asInstanceOf[ReadableStream[Any]]
}

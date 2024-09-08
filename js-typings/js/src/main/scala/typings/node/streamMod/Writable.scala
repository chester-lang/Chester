package typings.node.streamMod

import typings.node.anon.PickWritableOptionsdecode
import typings.node.streamWebMod.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * @since v0.9.4
  */
@JSImport("stream", "Writable")
@js.native
open class Writable () extends StObject
object Writable {
  
  @JSImport("stream", "Writable")
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * A utility method for creating a `Writable` from a web `WritableStream`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  inline def fromWeb(writableStream: WritableStream[Any]): Writable = ^.asInstanceOf[js.Dynamic].applyDynamic("fromWeb")(writableStream.asInstanceOf[js.Any]).asInstanceOf[Writable]
  inline def fromWeb(writableStream: WritableStream[Any], options: PickWritableOptionsdecode): Writable = (^.asInstanceOf[js.Dynamic].applyDynamic("fromWeb")(writableStream.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Writable]
  
  /**
    * A utility method for creating a web `WritableStream` from a `Writable`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  inline def toWeb(streamWritable: Writable): WritableStream[Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("toWeb")(streamWritable.asInstanceOf[js.Any]).asInstanceOf[WritableStream[Any]]
}

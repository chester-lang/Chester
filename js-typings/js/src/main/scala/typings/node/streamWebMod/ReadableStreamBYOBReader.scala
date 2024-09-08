package typings.node.streamWebMod

import typings.node.streamWebMod.^
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait ReadableStreamBYOBReader
  extends StObject
     with ReadableStreamGenericReader {
  
  def read[T /* <: js.typedarray.ArrayBufferView */](view: T): js.Promise[ReadableStreamReadResult[T]] = js.native
  
  def releaseLock(): Unit = js.native
}
object ReadableStreamBYOBReader {
  
  inline def apply: Any = ^.asInstanceOf[js.Dynamic].selectDynamic("ReadableStreamBYOBReader").asInstanceOf[Any]
}

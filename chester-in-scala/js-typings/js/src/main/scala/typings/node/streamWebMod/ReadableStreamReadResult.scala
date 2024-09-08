package typings.node.streamWebMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Rewritten from type alias, can be one of: 
  - typings.node.streamWebMod.ReadableStreamReadValueResult[T]
  - typings.node.streamWebMod.ReadableStreamReadDoneResult[T]
*/
trait ReadableStreamReadResult[T] extends StObject
object ReadableStreamReadResult {
  
  inline def ReadableStreamReadDoneResult[T](): typings.node.streamWebMod.ReadableStreamReadDoneResult[T] = {
    val __obj = js.Dynamic.literal(done = true)
    __obj.asInstanceOf[typings.node.streamWebMod.ReadableStreamReadDoneResult[T]]
  }
  
  inline def ReadableStreamReadValueResult[T](value: T): typings.node.streamWebMod.ReadableStreamReadValueResult[T] = {
    val __obj = js.Dynamic.literal(done = false, value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[typings.node.streamWebMod.ReadableStreamReadValueResult[T]]
  }
}

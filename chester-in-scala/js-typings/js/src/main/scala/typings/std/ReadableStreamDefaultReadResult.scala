package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Rewritten from type alias, can be one of: 
  - typings.std.ReadableStreamDefaultReadValueResult[T]
  - typings.std.ReadableStreamDefaultReadDoneResult
*/
trait ReadableStreamDefaultReadResult[T] extends StObject
object ReadableStreamDefaultReadResult {
  
  inline def ReadableStreamDefaultReadDoneResult(value: Unit): typings.std.ReadableStreamDefaultReadDoneResult = {
    val __obj = js.Dynamic.literal(done = true, value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[typings.std.ReadableStreamDefaultReadDoneResult]
  }
  
  inline def ReadableStreamDefaultReadValueResult[T](value: T): typings.std.ReadableStreamDefaultReadValueResult[T] = {
    val __obj = js.Dynamic.literal(done = false, value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[typings.std.ReadableStreamDefaultReadValueResult[T]]
  }
}

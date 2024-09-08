package typings.std

import typings.std.stdBooleans.`false`
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ReadableStreamDefaultReadValueResult[T]
  extends StObject
     with ReadableStreamDefaultReadResult[T] {
  
  /* standard dom */
  var done: `false`
  
  /* standard dom */
  var value: T
}
object ReadableStreamDefaultReadValueResult {
  
  inline def apply[T](value: T): ReadableStreamDefaultReadValueResult[T] = {
    val __obj = js.Dynamic.literal(done = false, value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[ReadableStreamDefaultReadValueResult[T]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ReadableStreamDefaultReadValueResult[?], T] (val x: Self & ReadableStreamDefaultReadValueResult[T]) extends AnyVal {
    
    inline def setDone(value: `false`): Self = StObject.set(x, "done", value.asInstanceOf[js.Any])
    
    inline def setValue(value: T): Self = StObject.set(x, "value", value.asInstanceOf[js.Any])
  }
}

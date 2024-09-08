package typings.node.streamWebMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DecompressionStream[R, W] extends StObject {
  
  val readable: ReadableStream[R]
  
  val writable: WritableStream[W]
}
object DecompressionStream {
  
  inline def apply[R, W](readable: ReadableStream[R], writable: WritableStream[W]): DecompressionStream[R, W] = {
    val __obj = js.Dynamic.literal(readable = readable.asInstanceOf[js.Any], writable = writable.asInstanceOf[js.Any])
    __obj.asInstanceOf[DecompressionStream[R, W]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DecompressionStream[?, ?], R, W] (val x: Self & (DecompressionStream[R, W])) extends AnyVal {
    
    inline def setReadable(value: ReadableStream[R]): Self = StObject.set(x, "readable", value.asInstanceOf[js.Any])
    
    inline def setWritable(value: WritableStream[W]): Self = StObject.set(x, "writable", value.asInstanceOf[js.Any])
  }
}

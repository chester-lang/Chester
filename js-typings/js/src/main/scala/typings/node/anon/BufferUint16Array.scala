package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BufferUint16Array extends StObject {
  
  var buffer: js.typedarray.Uint16Array
  
  var bytesRead: Double
}
object BufferUint16Array {
  
  inline def apply(buffer: js.typedarray.Uint16Array, bytesRead: Double): BufferUint16Array = {
    val __obj = js.Dynamic.literal(buffer = buffer.asInstanceOf[js.Any], bytesRead = bytesRead.asInstanceOf[js.Any])
    __obj.asInstanceOf[BufferUint16Array]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BufferUint16Array] (val x: Self) extends AnyVal {
    
    inline def setBuffer(value: js.typedarray.Uint16Array): Self = StObject.set(x, "buffer", value.asInstanceOf[js.Any])
    
    inline def setBytesRead(value: Double): Self = StObject.set(x, "bytesRead", value.asInstanceOf[js.Any])
  }
}

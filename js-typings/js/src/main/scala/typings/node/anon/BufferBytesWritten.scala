package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BufferBytesWritten[TBuffer /* <: ArrayBufferView */] extends StObject {
  
  var buffer: TBuffer
  
  var bytesWritten: Double
}
object BufferBytesWritten {
  
  inline def apply[TBuffer /* <: ArrayBufferView */](buffer: TBuffer, bytesWritten: Double): BufferBytesWritten[TBuffer] = {
    val __obj = js.Dynamic.literal(buffer = buffer.asInstanceOf[js.Any], bytesWritten = bytesWritten.asInstanceOf[js.Any])
    __obj.asInstanceOf[BufferBytesWritten[TBuffer]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BufferBytesWritten[?], TBuffer /* <: ArrayBufferView */] (val x: Self & BufferBytesWritten[TBuffer]) extends AnyVal {
    
    inline def setBuffer(value: TBuffer): Self = StObject.set(x, "buffer", value.asInstanceOf[js.Any])
    
    inline def setBytesWritten(value: Double): Self = StObject.set(x, "bytesWritten", value.asInstanceOf[js.Any])
  }
}

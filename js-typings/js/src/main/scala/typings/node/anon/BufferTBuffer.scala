package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BufferTBuffer[TBuffer /* <: ArrayBufferView */] extends StObject {
  
  var buffer: TBuffer
  
  var bytesRead: Double
}
object BufferTBuffer {
  
  inline def apply[TBuffer /* <: ArrayBufferView */](buffer: TBuffer, bytesRead: Double): BufferTBuffer[TBuffer] = {
    val __obj = js.Dynamic.literal(buffer = buffer.asInstanceOf[js.Any], bytesRead = bytesRead.asInstanceOf[js.Any])
    __obj.asInstanceOf[BufferTBuffer[TBuffer]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BufferTBuffer[?], TBuffer /* <: ArrayBufferView */] (val x: Self & BufferTBuffer[TBuffer]) extends AnyVal {
    
    inline def setBuffer(value: TBuffer): Self = StObject.set(x, "buffer", value.asInstanceOf[js.Any])
    
    inline def setBytesRead(value: Double): Self = StObject.set(x, "bytesRead", value.asInstanceOf[js.Any])
  }
}

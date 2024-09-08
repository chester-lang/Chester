package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BufferArrayBufferView extends StObject {
  
  var buffer: ArrayBufferView
  
  var bytesRead: Double
}
object BufferArrayBufferView {
  
  inline def apply(buffer: ArrayBufferView, bytesRead: Double): BufferArrayBufferView = {
    val __obj = js.Dynamic.literal(buffer = buffer.asInstanceOf[js.Any], bytesRead = bytesRead.asInstanceOf[js.Any])
    __obj.asInstanceOf[BufferArrayBufferView]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BufferArrayBufferView] (val x: Self) extends AnyVal {
    
    inline def setBuffer(value: ArrayBufferView): Self = StObject.set(x, "buffer", value.asInstanceOf[js.Any])
    
    inline def setBytesRead(value: Double): Self = StObject.set(x, "bytesRead", value.asInstanceOf[js.Any])
  }
}

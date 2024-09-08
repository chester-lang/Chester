package typings.node.anon

import typings.node.globalsMod.global.RelativeIndexable
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BufferRelativeIndexable extends StObject {
  
  var buffer: RelativeIndexable[js.BigInt]
  
  var bytesRead: Double
}
object BufferRelativeIndexable {
  
  inline def apply(buffer: RelativeIndexable[js.BigInt], bytesRead: Double): BufferRelativeIndexable = {
    val __obj = js.Dynamic.literal(buffer = buffer.asInstanceOf[js.Any], bytesRead = bytesRead.asInstanceOf[js.Any])
    __obj.asInstanceOf[BufferRelativeIndexable]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BufferRelativeIndexable] (val x: Self) extends AnyVal {
    
    inline def setBuffer(value: RelativeIndexable[js.BigInt]): Self = StObject.set(x, "buffer", value.asInstanceOf[js.Any])
    
    inline def setBytesRead(value: Double): Self = StObject.set(x, "bytesRead", value.asInstanceOf[js.Any])
  }
}

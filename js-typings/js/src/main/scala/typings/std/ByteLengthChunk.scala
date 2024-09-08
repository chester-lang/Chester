package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ByteLengthChunk extends StObject {
  
  /* standard dom */
  var byteLength: js.UndefOr[Double] = js.undefined
}
object ByteLengthChunk {
  
  inline def apply(): ByteLengthChunk = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ByteLengthChunk]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ByteLengthChunk] (val x: Self) extends AnyVal {
    
    inline def setByteLength(value: Double): Self = StObject.set(x, "byteLength", value.asInstanceOf[js.Any])
    
    inline def setByteLengthUndefined: Self = StObject.set(x, "byteLength", js.undefined)
  }
}

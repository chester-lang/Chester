package typings.node.utilMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait EncodeIntoResult extends StObject {
  
  /**
    * The read Unicode code units of input.
    */
  var read: Double
  
  /**
    * The written UTF-8 bytes of output.
    */
  var written: Double
}
object EncodeIntoResult {
  
  inline def apply(read: Double, written: Double): EncodeIntoResult = {
    val __obj = js.Dynamic.literal(read = read.asInstanceOf[js.Any], written = written.asInstanceOf[js.Any])
    __obj.asInstanceOf[EncodeIntoResult]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: EncodeIntoResult] (val x: Self) extends AnyVal {
    
    inline def setRead(value: Double): Self = StObject.set(x, "read", value.asInstanceOf[js.Any])
    
    inline def setWritten(value: Double): Self = StObject.set(x, "written", value.asInstanceOf[js.Any])
  }
}

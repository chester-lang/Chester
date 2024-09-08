package typings.node

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DiagnosticData
  extends StObject
     with TestLocationInfo {
  
  /**
    * The diagnostic message.
    */
  var message: String
  
  /**
    * The nesting level of the test.
    */
  var nesting: Double
}
object DiagnosticData {
  
  inline def apply(message: String, nesting: Double): DiagnosticData = {
    val __obj = js.Dynamic.literal(message = message.asInstanceOf[js.Any], nesting = nesting.asInstanceOf[js.Any])
    __obj.asInstanceOf[DiagnosticData]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DiagnosticData] (val x: Self) extends AnyVal {
    
    inline def setMessage(value: String): Self = StObject.set(x, "message", value.asInstanceOf[js.Any])
    
    inline def setNesting(value: Double): Self = StObject.set(x, "nesting", value.asInstanceOf[js.Any])
  }
}

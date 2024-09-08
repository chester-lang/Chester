package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MAXLENGTH extends StObject {
  
  var MAX_LENGTH: Double
  
  var MAX_STRING_LENGTH: Double
}
object MAXLENGTH {
  
  inline def apply(MAX_LENGTH: Double, MAX_STRING_LENGTH: Double): MAXLENGTH = {
    val __obj = js.Dynamic.literal(MAX_LENGTH = MAX_LENGTH.asInstanceOf[js.Any], MAX_STRING_LENGTH = MAX_STRING_LENGTH.asInstanceOf[js.Any])
    __obj.asInstanceOf[MAXLENGTH]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MAXLENGTH] (val x: Self) extends AnyVal {
    
    inline def setMAX_LENGTH(value: Double): Self = StObject.set(x, "MAX_LENGTH", value.asInstanceOf[js.Any])
    
    inline def setMAX_STRING_LENGTH(value: Double): Self = StObject.set(x, "MAX_STRING_LENGTH", value.asInstanceOf[js.Any])
  }
}

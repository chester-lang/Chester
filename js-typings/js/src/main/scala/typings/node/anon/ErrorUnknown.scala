package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ErrorUnknown extends StObject {
  
  var error: Any
}
object ErrorUnknown {
  
  inline def apply(error: Any): ErrorUnknown = {
    val __obj = js.Dynamic.literal(error = error.asInstanceOf[js.Any])
    __obj.asInstanceOf[ErrorUnknown]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ErrorUnknown] (val x: Self) extends AnyVal {
    
    inline def setError(value: Any): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
  }
}

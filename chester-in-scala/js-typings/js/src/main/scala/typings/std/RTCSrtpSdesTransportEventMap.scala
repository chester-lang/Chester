package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCSrtpSdesTransportEventMap extends StObject {
  
  /* standard dom */
  var error: org.scalajs.dom.Event
}
object RTCSrtpSdesTransportEventMap {
  
  inline def apply(error: org.scalajs.dom.Event): RTCSrtpSdesTransportEventMap = {
    val __obj = js.Dynamic.literal(error = error.asInstanceOf[js.Any])
    __obj.asInstanceOf[RTCSrtpSdesTransportEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCSrtpSdesTransportEventMap] (val x: Self) extends AnyVal {
    
    inline def setError(value: org.scalajs.dom.Event): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
  }
}

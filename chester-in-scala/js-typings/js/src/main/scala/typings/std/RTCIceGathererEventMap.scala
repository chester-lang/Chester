package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCIceGathererEventMap extends StObject {
  
  /* standard dom */
  var error: org.scalajs.dom.Event
  
  /* standard dom */
  var localcandidate: RTCIceGathererEvent
}
object RTCIceGathererEventMap {
  
  inline def apply(error: org.scalajs.dom.Event, localcandidate: RTCIceGathererEvent): RTCIceGathererEventMap = {
    val __obj = js.Dynamic.literal(error = error.asInstanceOf[js.Any], localcandidate = localcandidate.asInstanceOf[js.Any])
    __obj.asInstanceOf[RTCIceGathererEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCIceGathererEventMap] (val x: Self) extends AnyVal {
    
    inline def setError(value: org.scalajs.dom.Event): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setLocalcandidate(value: RTCIceGathererEvent): Self = StObject.set(x, "localcandidate", value.asInstanceOf[js.Any])
  }
}

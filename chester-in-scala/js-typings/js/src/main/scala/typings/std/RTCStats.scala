package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCStats extends StObject {
  
  /* standard dom */
  var id: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var timestamp: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var `type`: js.UndefOr[org.scalajs.dom.RTCStatsType] = js.undefined
}
object RTCStats {
  
  inline def apply(): RTCStats = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCStats]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCStats] (val x: Self) extends AnyVal {
    
    inline def setId(value: java.lang.String): Self = StObject.set(x, "id", value.asInstanceOf[js.Any])
    
    inline def setIdUndefined: Self = StObject.set(x, "id", js.undefined)
    
    inline def setTimestamp(value: Double): Self = StObject.set(x, "timestamp", value.asInstanceOf[js.Any])
    
    inline def setTimestampUndefined: Self = StObject.set(x, "timestamp", js.undefined)
    
    inline def setType(value: org.scalajs.dom.RTCStatsType): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
    
    inline def setTypeUndefined: Self = StObject.set(x, "type", js.undefined)
  }
}

package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCInboundRTPStreamStats
  extends StObject
     with RTCRTPStreamStats {
  
  /* standard dom */
  var bytesReceived: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var fractionLost: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var jitter: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var packetsLost: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var packetsReceived: js.UndefOr[Double] = js.undefined
}
object RTCInboundRTPStreamStats {
  
  inline def apply(): RTCInboundRTPStreamStats = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCInboundRTPStreamStats]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCInboundRTPStreamStats] (val x: Self) extends AnyVal {
    
    inline def setBytesReceived(value: Double): Self = StObject.set(x, "bytesReceived", value.asInstanceOf[js.Any])
    
    inline def setBytesReceivedUndefined: Self = StObject.set(x, "bytesReceived", js.undefined)
    
    inline def setFractionLost(value: Double): Self = StObject.set(x, "fractionLost", value.asInstanceOf[js.Any])
    
    inline def setFractionLostUndefined: Self = StObject.set(x, "fractionLost", js.undefined)
    
    inline def setJitter(value: Double): Self = StObject.set(x, "jitter", value.asInstanceOf[js.Any])
    
    inline def setJitterUndefined: Self = StObject.set(x, "jitter", js.undefined)
    
    inline def setPacketsLost(value: Double): Self = StObject.set(x, "packetsLost", value.asInstanceOf[js.Any])
    
    inline def setPacketsLostUndefined: Self = StObject.set(x, "packetsLost", js.undefined)
    
    inline def setPacketsReceived(value: Double): Self = StObject.set(x, "packetsReceived", value.asInstanceOf[js.Any])
    
    inline def setPacketsReceivedUndefined: Self = StObject.set(x, "packetsReceived", js.undefined)
  }
}

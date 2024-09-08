package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCOutboundRTPStreamStats
  extends StObject
     with RTCRTPStreamStats {
  
  /* standard dom */
  var bytesSent: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var packetsSent: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var roundTripTime: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var targetBitrate: js.UndefOr[Double] = js.undefined
}
object RTCOutboundRTPStreamStats {
  
  inline def apply(): RTCOutboundRTPStreamStats = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCOutboundRTPStreamStats]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCOutboundRTPStreamStats] (val x: Self) extends AnyVal {
    
    inline def setBytesSent(value: Double): Self = StObject.set(x, "bytesSent", value.asInstanceOf[js.Any])
    
    inline def setBytesSentUndefined: Self = StObject.set(x, "bytesSent", js.undefined)
    
    inline def setPacketsSent(value: Double): Self = StObject.set(x, "packetsSent", value.asInstanceOf[js.Any])
    
    inline def setPacketsSentUndefined: Self = StObject.set(x, "packetsSent", js.undefined)
    
    inline def setRoundTripTime(value: Double): Self = StObject.set(x, "roundTripTime", value.asInstanceOf[js.Any])
    
    inline def setRoundTripTimeUndefined: Self = StObject.set(x, "roundTripTime", js.undefined)
    
    inline def setTargetBitrate(value: Double): Self = StObject.set(x, "targetBitrate", value.asInstanceOf[js.Any])
    
    inline def setTargetBitrateUndefined: Self = StObject.set(x, "targetBitrate", js.undefined)
  }
}

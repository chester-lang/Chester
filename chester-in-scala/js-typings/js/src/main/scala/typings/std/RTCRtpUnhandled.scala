package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCRtpUnhandled extends StObject {
  
  /* standard dom */
  var muxId: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var payloadType: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var ssrc: js.UndefOr[Double] = js.undefined
}
object RTCRtpUnhandled {
  
  inline def apply(): RTCRtpUnhandled = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCRtpUnhandled]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCRtpUnhandled] (val x: Self) extends AnyVal {
    
    inline def setMuxId(value: java.lang.String): Self = StObject.set(x, "muxId", value.asInstanceOf[js.Any])
    
    inline def setMuxIdUndefined: Self = StObject.set(x, "muxId", js.undefined)
    
    inline def setPayloadType(value: Double): Self = StObject.set(x, "payloadType", value.asInstanceOf[js.Any])
    
    inline def setPayloadTypeUndefined: Self = StObject.set(x, "payloadType", js.undefined)
    
    inline def setSsrc(value: Double): Self = StObject.set(x, "ssrc", value.asInstanceOf[js.Any])
    
    inline def setSsrcUndefined: Self = StObject.set(x, "ssrc", js.undefined)
  }
}

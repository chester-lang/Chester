package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCRtpRtxParameters extends StObject {
  
  /* standard dom */
  var ssrc: js.UndefOr[Double] = js.undefined
}
object RTCRtpRtxParameters {
  
  inline def apply(): RTCRtpRtxParameters = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCRtpRtxParameters]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCRtpRtxParameters] (val x: Self) extends AnyVal {
    
    inline def setSsrc(value: Double): Self = StObject.set(x, "ssrc", value.asInstanceOf[js.Any])
    
    inline def setSsrcUndefined: Self = StObject.set(x, "ssrc", js.undefined)
  }
}

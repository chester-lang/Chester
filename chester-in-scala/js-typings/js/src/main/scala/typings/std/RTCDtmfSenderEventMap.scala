package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCDtmfSenderEventMap extends StObject {
  
  /* standard dom */
  var tonechange: RTCDTMFToneChangeEvent
}
object RTCDtmfSenderEventMap {
  
  inline def apply(tonechange: RTCDTMFToneChangeEvent): RTCDtmfSenderEventMap = {
    val __obj = js.Dynamic.literal(tonechange = tonechange.asInstanceOf[js.Any])
    __obj.asInstanceOf[RTCDtmfSenderEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCDtmfSenderEventMap] (val x: Self) extends AnyVal {
    
    inline def setTonechange(value: RTCDTMFToneChangeEvent): Self = StObject.set(x, "tonechange", value.asInstanceOf[js.Any])
  }
}

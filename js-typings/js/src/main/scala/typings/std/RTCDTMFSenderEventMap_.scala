package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCDTMFSenderEventMap_ extends StObject {
  
  /* standard dom */
  var tonechange: RTCDTMFToneChangeEvent
}
object RTCDTMFSenderEventMap_ {
  
  inline def apply(tonechange: RTCDTMFToneChangeEvent): RTCDTMFSenderEventMap_ = {
    val __obj = js.Dynamic.literal(tonechange = tonechange.asInstanceOf[js.Any])
    __obj.asInstanceOf[RTCDTMFSenderEventMap_]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCDTMFSenderEventMap_] (val x: Self) extends AnyVal {
    
    inline def setTonechange(value: RTCDTMFToneChangeEvent): Self = StObject.set(x, "tonechange", value.asInstanceOf[js.Any])
  }
}

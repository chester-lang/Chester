package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCIceGatherOptions extends StObject {
  
  /* standard dom */
  var gatherPolicy: js.UndefOr[RTCIceGatherPolicy] = js.undefined
  
  /* standard dom */
  var iceservers: js.UndefOr[js.Array[org.scalajs.dom.RTCIceServer]] = js.undefined
}
object RTCIceGatherOptions {
  
  inline def apply(): RTCIceGatherOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCIceGatherOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCIceGatherOptions] (val x: Self) extends AnyVal {
    
    inline def setGatherPolicy(value: RTCIceGatherPolicy): Self = StObject.set(x, "gatherPolicy", value.asInstanceOf[js.Any])
    
    inline def setGatherPolicyUndefined: Self = StObject.set(x, "gatherPolicy", js.undefined)
    
    inline def setIceservers(value: js.Array[org.scalajs.dom.RTCIceServer]): Self = StObject.set(x, "iceservers", value.asInstanceOf[js.Any])
    
    inline def setIceserversUndefined: Self = StObject.set(x, "iceservers", js.undefined)
    
    inline def setIceserversVarargs(value: org.scalajs.dom.RTCIceServer*): Self = StObject.set(x, "iceservers", js.Array(value*))
  }
}

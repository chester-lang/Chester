package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCRtpHeaderExtension extends StObject {
  
  /* standard dom */
  var kind: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var preferredEncrypt: js.UndefOr[scala.Boolean] = js.undefined
  
  /* standard dom */
  var preferredId: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var uri: js.UndefOr[java.lang.String] = js.undefined
}
object RTCRtpHeaderExtension {
  
  inline def apply(): RTCRtpHeaderExtension = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCRtpHeaderExtension]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCRtpHeaderExtension] (val x: Self) extends AnyVal {
    
    inline def setKind(value: java.lang.String): Self = StObject.set(x, "kind", value.asInstanceOf[js.Any])
    
    inline def setKindUndefined: Self = StObject.set(x, "kind", js.undefined)
    
    inline def setPreferredEncrypt(value: scala.Boolean): Self = StObject.set(x, "preferredEncrypt", value.asInstanceOf[js.Any])
    
    inline def setPreferredEncryptUndefined: Self = StObject.set(x, "preferredEncrypt", js.undefined)
    
    inline def setPreferredId(value: Double): Self = StObject.set(x, "preferredId", value.asInstanceOf[js.Any])
    
    inline def setPreferredIdUndefined: Self = StObject.set(x, "preferredId", js.undefined)
    
    inline def setUri(value: java.lang.String): Self = StObject.set(x, "uri", value.asInstanceOf[js.Any])
    
    inline def setUriUndefined: Self = StObject.set(x, "uri", js.undefined)
  }
}

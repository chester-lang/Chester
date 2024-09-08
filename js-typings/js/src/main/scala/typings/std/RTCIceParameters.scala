package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCIceParameters extends StObject {
  
  /* standard dom */
  var password: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var usernameFragment: js.UndefOr[java.lang.String] = js.undefined
}
object RTCIceParameters {
  
  inline def apply(): RTCIceParameters = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCIceParameters]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCIceParameters] (val x: Self) extends AnyVal {
    
    inline def setPassword(value: java.lang.String): Self = StObject.set(x, "password", value.asInstanceOf[js.Any])
    
    inline def setPasswordUndefined: Self = StObject.set(x, "password", js.undefined)
    
    inline def setUsernameFragment(value: java.lang.String): Self = StObject.set(x, "usernameFragment", value.asInstanceOf[js.Any])
    
    inline def setUsernameFragmentUndefined: Self = StObject.set(x, "usernameFragment", js.undefined)
  }
}

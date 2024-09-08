package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCIdentityAssertion extends StObject {
  
  /* standard dom */
  var idp: java.lang.String
  
  /* standard dom */
  var name: java.lang.String
}
object RTCIdentityAssertion {
  
  inline def apply(idp: java.lang.String, name: java.lang.String): RTCIdentityAssertion = {
    val __obj = js.Dynamic.literal(idp = idp.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any])
    __obj.asInstanceOf[RTCIdentityAssertion]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCIdentityAssertion] (val x: Self) extends AnyVal {
    
    inline def setIdp(value: java.lang.String): Self = StObject.set(x, "idp", value.asInstanceOf[js.Any])
    
    inline def setName(value: java.lang.String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

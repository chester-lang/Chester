package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PayerErrors extends StObject {
  
  /* standard dom */
  var email: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var name: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var phone: js.UndefOr[java.lang.String] = js.undefined
}
object PayerErrors {
  
  inline def apply(): PayerErrors = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PayerErrors]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PayerErrors] (val x: Self) extends AnyVal {
    
    inline def setEmail(value: java.lang.String): Self = StObject.set(x, "email", value.asInstanceOf[js.Any])
    
    inline def setEmailUndefined: Self = StObject.set(x, "email", js.undefined)
    
    inline def setName(value: java.lang.String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
    
    inline def setNameUndefined: Self = StObject.set(x, "name", js.undefined)
    
    inline def setPhone(value: java.lang.String): Self = StObject.set(x, "phone", value.asInstanceOf[js.Any])
    
    inline def setPhoneUndefined: Self = StObject.set(x, "phone", js.undefined)
  }
}

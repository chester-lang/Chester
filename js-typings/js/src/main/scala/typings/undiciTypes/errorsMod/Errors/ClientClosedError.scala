package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_CLOSED
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ClientClosedError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_ClientClosedError: UND_ERR_CLOSED
  
  @JSName("name")
  var name_ClientClosedError: typings.undiciTypes.undiciTypesStrings.ClientClosedError
}
object ClientClosedError {
  
  inline def apply(message: String): ClientClosedError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_CLOSED", message = message.asInstanceOf[js.Any], name = "ClientClosedError")
    __obj.asInstanceOf[ClientClosedError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ClientClosedError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_CLOSED): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.ClientClosedError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_INVALID_RETURN_VALUE
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait InvalidReturnValueError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_InvalidReturnValueError: UND_ERR_INVALID_RETURN_VALUE
  
  @JSName("name")
  var name_InvalidReturnValueError: typings.undiciTypes.undiciTypesStrings.InvalidReturnValueError
}
object InvalidReturnValueError {
  
  inline def apply(message: String): InvalidReturnValueError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_INVALID_RETURN_VALUE", message = message.asInstanceOf[js.Any], name = "InvalidReturnValueError")
    __obj.asInstanceOf[InvalidReturnValueError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: InvalidReturnValueError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_INVALID_RETURN_VALUE): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.InvalidReturnValueError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

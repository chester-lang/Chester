package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_INVALID_ARG
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait InvalidArgumentError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_InvalidArgumentError: UND_ERR_INVALID_ARG
  
  @JSName("name")
  var name_InvalidArgumentError: typings.undiciTypes.undiciTypesStrings.InvalidArgumentError
}
object InvalidArgumentError {
  
  inline def apply(message: String): InvalidArgumentError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_INVALID_ARG", message = message.asInstanceOf[js.Any], name = "InvalidArgumentError")
    __obj.asInstanceOf[InvalidArgumentError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: InvalidArgumentError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_INVALID_ARG): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.InvalidArgumentError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

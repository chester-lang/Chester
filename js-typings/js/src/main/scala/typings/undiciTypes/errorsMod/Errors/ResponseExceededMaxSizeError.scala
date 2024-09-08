package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_RES_EXCEEDED_MAX_SIZE
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ResponseExceededMaxSizeError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_ResponseExceededMaxSizeError: UND_ERR_RES_EXCEEDED_MAX_SIZE
  
  @JSName("name")
  var name_ResponseExceededMaxSizeError: typings.undiciTypes.undiciTypesStrings.ResponseExceededMaxSizeError
}
object ResponseExceededMaxSizeError {
  
  inline def apply(message: String): ResponseExceededMaxSizeError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_RES_EXCEEDED_MAX_SIZE", message = message.asInstanceOf[js.Any], name = "ResponseExceededMaxSizeError")
    __obj.asInstanceOf[ResponseExceededMaxSizeError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ResponseExceededMaxSizeError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_RES_EXCEEDED_MAX_SIZE): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.ResponseExceededMaxSizeError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_RES_CONTENT_LENGTH_MISMATCH
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ResponseContentLengthMismatchError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_ResponseContentLengthMismatchError: UND_ERR_RES_CONTENT_LENGTH_MISMATCH
  
  @JSName("name")
  var name_ResponseContentLengthMismatchError: typings.undiciTypes.undiciTypesStrings.ResponseContentLengthMismatchError
}
object ResponseContentLengthMismatchError {
  
  inline def apply(message: String): ResponseContentLengthMismatchError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_RES_CONTENT_LENGTH_MISMATCH", message = message.asInstanceOf[js.Any], name = "ResponseContentLengthMismatchError")
    __obj.asInstanceOf[ResponseContentLengthMismatchError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ResponseContentLengthMismatchError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_RES_CONTENT_LENGTH_MISMATCH): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.ResponseContentLengthMismatchError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

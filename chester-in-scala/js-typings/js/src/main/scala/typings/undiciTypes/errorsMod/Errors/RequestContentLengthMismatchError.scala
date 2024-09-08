package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_REQ_CONTENT_LENGTH_MISMATCH
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RequestContentLengthMismatchError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_RequestContentLengthMismatchError: UND_ERR_REQ_CONTENT_LENGTH_MISMATCH
  
  @JSName("name")
  var name_RequestContentLengthMismatchError: typings.undiciTypes.undiciTypesStrings.RequestContentLengthMismatchError
}
object RequestContentLengthMismatchError {
  
  inline def apply(message: String): RequestContentLengthMismatchError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_REQ_CONTENT_LENGTH_MISMATCH", message = message.asInstanceOf[js.Any], name = "RequestContentLengthMismatchError")
    __obj.asInstanceOf[RequestContentLengthMismatchError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RequestContentLengthMismatchError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_REQ_CONTENT_LENGTH_MISMATCH): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.RequestContentLengthMismatchError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

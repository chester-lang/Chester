package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.UND_ERR_HEADERS_TIMEOUT
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait HeadersTimeoutError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_HeadersTimeoutError: UND_ERR_HEADERS_TIMEOUT
  
  @JSName("name")
  var name_HeadersTimeoutError: typings.undiciTypes.undiciTypesStrings.HeadersTimeoutError
}
object HeadersTimeoutError {
  
  inline def apply(message: String): HeadersTimeoutError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_HEADERS_TIMEOUT", message = message.asInstanceOf[js.Any], name = "HeadersTimeoutError")
    __obj.asInstanceOf[HeadersTimeoutError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: HeadersTimeoutError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_HEADERS_TIMEOUT): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.HeadersTimeoutError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

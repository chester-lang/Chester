package typings.undiciTypes.errorsMod.Errors

import typings.undiciTypes.undiciTypesStrings.AbortError
import typings.undiciTypes.undiciTypesStrings.UND_ERR_ABORTED
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RequestAbortedError
  extends StObject
     with UndiciError {
  
  @JSName("code")
  var code_RequestAbortedError: UND_ERR_ABORTED
  
  @JSName("name")
  var name_RequestAbortedError: AbortError
}
object RequestAbortedError {
  
  inline def apply(message: String): RequestAbortedError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_ABORTED", message = message.asInstanceOf[js.Any], name = "AbortError")
    __obj.asInstanceOf[RequestAbortedError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RequestAbortedError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: UND_ERR_ABORTED): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setName(value: AbortError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

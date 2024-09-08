package typings.undiciTypes.errorsMod.Errors

import typings.std.Record
import typings.undiciTypes.headerMod.IncomingHttpHeaders
import typings.undiciTypes.undiciTypesStrings.UND_ERR_RESPONSE_STATUS_CODE
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ResponseStatusCodeError
  extends StObject
     with UndiciError {
  
  var body: Null | (Record[String, Any]) | String
  
  @JSName("code")
  var code_ResponseStatusCodeError: UND_ERR_RESPONSE_STATUS_CODE
  
  var headers: IncomingHttpHeaders | js.Array[String] | Null
  
  @JSName("name")
  var name_ResponseStatusCodeError: typings.undiciTypes.undiciTypesStrings.ResponseStatusCodeError
  
  var status: Double
  
  var statusCode: Double
}
object ResponseStatusCodeError {
  
  inline def apply(message: String, status: Double, statusCode: Double): ResponseStatusCodeError = {
    val __obj = js.Dynamic.literal(code = "UND_ERR_RESPONSE_STATUS_CODE", message = message.asInstanceOf[js.Any], name = "ResponseStatusCodeError", status = status.asInstanceOf[js.Any], statusCode = statusCode.asInstanceOf[js.Any], body = null, headers = null)
    __obj.asInstanceOf[ResponseStatusCodeError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ResponseStatusCodeError] (val x: Self) extends AnyVal {
    
    inline def setBody(value: (Record[String, Any]) | String): Self = StObject.set(x, "body", value.asInstanceOf[js.Any])
    
    inline def setBodyNull: Self = StObject.set(x, "body", null)
    
    inline def setCode(value: UND_ERR_RESPONSE_STATUS_CODE): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setHeaders(value: IncomingHttpHeaders | js.Array[String]): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
    
    inline def setHeadersNull: Self = StObject.set(x, "headers", null)
    
    inline def setHeadersVarargs(value: String*): Self = StObject.set(x, "headers", js.Array(value*))
    
    inline def setName(value: typings.undiciTypes.undiciTypesStrings.ResponseStatusCodeError): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
    
    inline def setStatus(value: Double): Self = StObject.set(x, "status", value.asInstanceOf[js.Any])
    
    inline def setStatusCode(value: Double): Self = StObject.set(x, "statusCode", value.asInstanceOf[js.Any])
  }
}

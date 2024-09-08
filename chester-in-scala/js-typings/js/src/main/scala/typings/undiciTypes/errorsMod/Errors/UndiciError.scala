package typings.undiciTypes.errorsMod.Errors

import typings.std.Error
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait UndiciError
  extends StObject
     with Error {
  
  var code: String
}
object UndiciError {
  
  inline def apply(code: String, message: String, name: String): UndiciError = {
    val __obj = js.Dynamic.literal(code = code.asInstanceOf[js.Any], message = message.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any])
    __obj.asInstanceOf[UndiciError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: UndiciError] (val x: Self) extends AnyVal {
    
    inline def setCode(value: String): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
  }
}

package typings.node

import typings.std.Error
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestError
  extends StObject
     with Error {
  
  var cause: js.Error
}
object TestError {
  
  inline def apply(cause: js.Error, message: String, name: String): TestError = {
    val __obj = js.Dynamic.literal(cause = cause.asInstanceOf[js.Any], message = message.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestError]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestError] (val x: Self) extends AnyVal {
    
    inline def setCause(value: js.Error): Self = StObject.set(x, "cause", value.asInstanceOf[js.Any])
  }
}

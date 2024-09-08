package typings.node

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestStderr extends StObject {
  
  /**
    * The path of the test file.
    */
  var file: String
  
  /**
    * The message written to `stderr`.
    */
  var message: String
}
object TestStderr {
  
  inline def apply(file: String, message: String): TestStderr = {
    val __obj = js.Dynamic.literal(file = file.asInstanceOf[js.Any], message = message.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestStderr]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestStderr] (val x: Self) extends AnyVal {
    
    inline def setFile(value: String): Self = StObject.set(x, "file", value.asInstanceOf[js.Any])
    
    inline def setMessage(value: String): Self = StObject.set(x, "message", value.asInstanceOf[js.Any])
  }
}

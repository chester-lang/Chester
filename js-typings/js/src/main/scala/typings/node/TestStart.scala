package typings.node

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestStart
  extends StObject
     with TestLocationInfo {
  
  /**
    * The test name.
    */
  var name: String
  
  /**
    * The nesting level of the test.
    */
  var nesting: Double
}
object TestStart {
  
  inline def apply(name: String, nesting: Double): TestStart = {
    val __obj = js.Dynamic.literal(name = name.asInstanceOf[js.Any], nesting = nesting.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestStart]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestStart] (val x: Self) extends AnyVal {
    
    inline def setName(value: String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
    
    inline def setNesting(value: Double): Self = StObject.set(x, "nesting", value.asInstanceOf[js.Any])
  }
}

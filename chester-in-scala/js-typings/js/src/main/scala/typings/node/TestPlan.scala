package typings.node

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestPlan
  extends StObject
     with TestLocationInfo {
  
  /**
    * The number of subtests that have ran.
    */
  var count: Double
  
  /**
    * The nesting level of the test.
    */
  var nesting: Double
}
object TestPlan {
  
  inline def apply(count: Double, nesting: Double): TestPlan = {
    val __obj = js.Dynamic.literal(count = count.asInstanceOf[js.Any], nesting = nesting.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestPlan]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestPlan] (val x: Self) extends AnyVal {
    
    inline def setCount(value: Double): Self = StObject.set(x, "count", value.asInstanceOf[js.Any])
    
    inline def setNesting(value: Double): Self = StObject.set(x, "nesting", value.asInstanceOf[js.Any])
  }
}

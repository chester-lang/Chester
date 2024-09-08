package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MockFunctionOptions extends StObject {
  
  /**
    * The number of times that the mock will use the behavior of `implementation`.
    * Once the mock function has been called `times` times,
    * it will automatically restore the behavior of `original`.
    * This value must be an integer greater than zero.
    * @default Infinity
    */
  var times: js.UndefOr[Double] = js.undefined
}
object MockFunctionOptions {
  
  inline def apply(): MockFunctionOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[MockFunctionOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MockFunctionOptions] (val x: Self) extends AnyVal {
    
    inline def setTimes(value: Double): Self = StObject.set(x, "times", value.asInstanceOf[js.Any])
    
    inline def setTimesUndefined: Self = StObject.set(x, "times", js.undefined)
  }
}

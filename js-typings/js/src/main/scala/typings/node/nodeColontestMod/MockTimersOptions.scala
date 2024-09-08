package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MockTimersOptions extends StObject {
  
  var apis: js.Array[Timer]
  
  var now: js.UndefOr[Double | js.Date] = js.undefined
}
object MockTimersOptions {
  
  inline def apply(apis: js.Array[Timer]): MockTimersOptions = {
    val __obj = js.Dynamic.literal(apis = apis.asInstanceOf[js.Any])
    __obj.asInstanceOf[MockTimersOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MockTimersOptions] (val x: Self) extends AnyVal {
    
    inline def setApis(value: js.Array[Timer]): Self = StObject.set(x, "apis", value.asInstanceOf[js.Any])
    
    inline def setApisVarargs(value: Timer*): Self = StObject.set(x, "apis", js.Array(value*))
    
    inline def setNow(value: Double | js.Date): Self = StObject.set(x, "now", value.asInstanceOf[js.Any])
    
    inline def setNowUndefined: Self = StObject.set(x, "now", js.undefined)
  }
}

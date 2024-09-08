package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DestroyOnReturn extends StObject {
  
  var destroyOnReturn: js.UndefOr[Boolean] = js.undefined
}
object DestroyOnReturn {
  
  inline def apply(): DestroyOnReturn = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[DestroyOnReturn]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DestroyOnReturn] (val x: Self) extends AnyVal {
    
    inline def setDestroyOnReturn(value: Boolean): Self = StObject.set(x, "destroyOnReturn", value.asInstanceOf[js.Any])
    
    inline def setDestroyOnReturnUndefined: Self = StObject.set(x, "destroyOnReturn", js.undefined)
  }
}

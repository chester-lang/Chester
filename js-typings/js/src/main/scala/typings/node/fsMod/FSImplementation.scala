package typings.node.fsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait FSImplementation extends StObject {
  
  var close: js.UndefOr[js.Function1[/* repeated */ Any, Any]] = js.undefined
  
  var open: js.UndefOr[js.Function1[/* repeated */ Any, Any]] = js.undefined
}
object FSImplementation {
  
  inline def apply(): FSImplementation = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[FSImplementation]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: FSImplementation] (val x: Self) extends AnyVal {
    
    inline def setClose(value: /* repeated */ Any => Any): Self = StObject.set(x, "close", js.Any.fromFunction1(value))
    
    inline def setCloseUndefined: Self = StObject.set(x, "close", js.undefined)
    
    inline def setOpen(value: /* repeated */ Any => Any): Self = StObject.set(x, "open", js.Any.fromFunction1(value))
    
    inline def setOpenUndefined: Self = StObject.set(x, "open", js.undefined)
  }
}

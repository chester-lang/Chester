package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait KeepOpen extends StObject {
  
  var keepOpen: js.UndefOr[Boolean] = js.undefined
}
object KeepOpen {
  
  inline def apply(): KeepOpen = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[KeepOpen]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: KeepOpen] (val x: Self) extends AnyVal {
    
    inline def setKeepOpen(value: Boolean): Self = StObject.set(x, "keepOpen", value.asInstanceOf[js.Any])
    
    inline def setKeepOpenUndefined: Self = StObject.set(x, "keepOpen", js.undefined)
  }
}

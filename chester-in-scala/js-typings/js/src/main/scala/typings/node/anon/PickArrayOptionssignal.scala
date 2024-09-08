package typings.node.anon

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Inlined std.Pick<node.stream.ArrayOptions, 'signal'> */
trait PickArrayOptionssignal extends StObject {
  
  var signal: js.UndefOr[AbortSignal] = js.undefined
}
object PickArrayOptionssignal {
  
  inline def apply(): PickArrayOptionssignal = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PickArrayOptionssignal]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PickArrayOptionssignal] (val x: Self) extends AnyVal {
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
  }
}

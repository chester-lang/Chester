package typings.node.anon

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Inlined std.Pick<node.node:timers.TimerOptions, 'signal'> */
trait PickTimerOptionssignal extends StObject {
  
  var signal: js.UndefOr[AbortSignal] = js.undefined
}
object PickTimerOptionssignal {
  
  inline def apply(): PickTimerOptionssignal = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PickTimerOptionssignal]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PickTimerOptionssignal] (val x: Self) extends AnyVal {
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
  }
}

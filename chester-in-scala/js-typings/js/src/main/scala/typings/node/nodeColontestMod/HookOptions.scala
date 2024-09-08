package typings.node.nodeColontestMod

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * Configuration options for hooks.
  * @since v18.8.0
  */
trait HookOptions extends StObject {
  
  /**
    * Allows aborting an in-progress hook.
    */
  var signal: js.UndefOr[AbortSignal] = js.undefined
  
  /**
    * A number of milliseconds the hook will fail after. If unspecified, subtests inherit this
    * value from their parent.
    * @default Infinity
    */
  var timeout: js.UndefOr[Double] = js.undefined
}
object HookOptions {
  
  inline def apply(): HookOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[HookOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: HookOptions] (val x: Self) extends AnyVal {
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    
    inline def setTimeout(value: Double): Self = StObject.set(x, "timeout", value.asInstanceOf[js.Any])
    
    inline def setTimeoutUndefined: Self = StObject.set(x, "timeout", js.undefined)
  }
}

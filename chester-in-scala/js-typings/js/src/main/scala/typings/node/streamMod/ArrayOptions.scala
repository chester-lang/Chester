package typings.node.streamMod

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ArrayOptions extends StObject {
  
  /**
    * The maximum concurrent invocations of `fn` to call on the stream at once.
    * @default 1
    */
  var concurrency: js.UndefOr[Double] = js.undefined
  
  /** Allows destroying the stream if the signal is aborted. */
  var signal: js.UndefOr[AbortSignal] = js.undefined
}
object ArrayOptions {
  
  inline def apply(): ArrayOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ArrayOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ArrayOptions] (val x: Self) extends AnyVal {
    
    inline def setConcurrency(value: Double): Self = StObject.set(x, "concurrency", value.asInstanceOf[js.Any])
    
    inline def setConcurrencyUndefined: Self = StObject.set(x, "concurrency", js.undefined)
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
  }
}

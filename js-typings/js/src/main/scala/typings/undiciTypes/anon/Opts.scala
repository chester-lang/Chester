package typings.undiciTypes.anon

import typings.undiciTypes.retryHandlerMod.RetryHandler.RetryState
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Opts extends StObject {
  
  var opts: DispatchOptionsretryOptio
  
  var state: RetryState
}
object Opts {
  
  inline def apply(opts: DispatchOptionsretryOptio, state: RetryState): Opts = {
    val __obj = js.Dynamic.literal(opts = opts.asInstanceOf[js.Any], state = state.asInstanceOf[js.Any])
    __obj.asInstanceOf[Opts]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Opts] (val x: Self) extends AnyVal {
    
    inline def setOpts(value: DispatchOptionsretryOptio): Self = StObject.set(x, "opts", value.asInstanceOf[js.Any])
    
    inline def setState(value: RetryState): Self = StObject.set(x, "state", value.asInstanceOf[js.Any])
  }
}

package typings.undiciTypes

import typings.undiciTypes.agentMod.Agent.DispatchOptions
import typings.undiciTypes.agentMod.Agent.Options
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchHandlers
import typings.undiciTypes.dispatcherMod.Dispatcher.HttpMethod
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object agentMod {
  
  @JSImport("undici-types/agent", JSImport.Default)
  @js.native
  open class default () extends Agent {
    def this(opts: Options) = this()
  }
  
  @js.native
  trait Agent
    extends typings.undiciTypes.dispatcherMod.default {
    
    /** `true` after `dispatcher.close()` has been called. */
    var closed: Boolean = js.native
    
    /** `true` after `dispatcher.destroyed()` has been called or `dispatcher.close()` has been called and the dispatcher shutdown has completed. */
    var destroyed: Boolean = js.native
    
    /** Dispatches a request. */
    def dispatch(options: DispatchOptions, handler: DispatchHandlers): Boolean = js.native
  }
  object Agent {
    
    trait DispatchOptions
      extends StObject
         with typings.undiciTypes.dispatcherMod.Dispatcher.DispatchOptions {
      
      /** Integer. */
      var maxRedirections: js.UndefOr[Double] = js.undefined
    }
    object DispatchOptions {
      
      inline def apply(method: HttpMethod, path: String): DispatchOptions = {
        val __obj = js.Dynamic.literal(method = method.asInstanceOf[js.Any], path = path.asInstanceOf[js.Any])
        __obj.asInstanceOf[DispatchOptions]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: DispatchOptions] (val x: Self) extends AnyVal {
        
        inline def setMaxRedirections(value: Double): Self = StObject.set(x, "maxRedirections", value.asInstanceOf[js.Any])
        
        inline def setMaxRedirectionsUndefined: Self = StObject.set(x, "maxRedirections", js.undefined)
      }
    }
    
    trait Options
      extends StObject
         with typings.undiciTypes.poolMod.Pool.Options
    object Options {
      
      inline def apply(): Options = {
        val __obj = js.Dynamic.literal()
        __obj.asInstanceOf[Options]
      }
    }
  }
}

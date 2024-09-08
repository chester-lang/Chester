package typings.undiciTypes

import typings.undiciTypes.agentMod.Agent.DispatchOptions
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchHandlers
import typings.undiciTypes.envHttpProxyAgentMod.EnvHttpProxyAgent.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object envHttpProxyAgentMod {
  
  @JSImport("undici-types/env-http-proxy-agent", JSImport.Default)
  @js.native
  open class default () extends EnvHttpProxyAgent {
    def this(opts: Options) = this()
  }
  
  @js.native
  trait EnvHttpProxyAgent
    extends typings.undiciTypes.dispatcherMod.default {
    
    def dispatch(options: DispatchOptions, handler: DispatchHandlers): Boolean = js.native
  }
  object EnvHttpProxyAgent {
    
    trait Options
      extends StObject
         with typings.undiciTypes.agentMod.Agent.Options {
      
      /** Overrides the value of the HTTP_PROXY environment variable  */
      var httpProxy: js.UndefOr[String] = js.undefined
      
      /** Overrides the value of the HTTPS_PROXY environment variable  */
      var httpsProxy: js.UndefOr[String] = js.undefined
      
      /** Overrides the value of the NO_PROXY environment variable  */
      var noProxy: js.UndefOr[String] = js.undefined
    }
    object Options {
      
      inline def apply(): Options = {
        val __obj = js.Dynamic.literal()
        __obj.asInstanceOf[Options]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: Options] (val x: Self) extends AnyVal {
        
        inline def setHttpProxy(value: String): Self = StObject.set(x, "httpProxy", value.asInstanceOf[js.Any])
        
        inline def setHttpProxyUndefined: Self = StObject.set(x, "httpProxy", js.undefined)
        
        inline def setHttpsProxy(value: String): Self = StObject.set(x, "httpsProxy", value.asInstanceOf[js.Any])
        
        inline def setHttpsProxyUndefined: Self = StObject.set(x, "httpsProxy", js.undefined)
        
        inline def setNoProxy(value: String): Self = StObject.set(x, "noProxy", value.asInstanceOf[js.Any])
        
        inline def setNoProxyUndefined: Self = StObject.set(x, "noProxy", js.undefined)
      }
    }
  }
}

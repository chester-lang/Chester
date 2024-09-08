package typings.undiciTypes

import org.scalajs.dom.URL
import typings.undiciTypes.agentMod.Agent.DispatchOptions
import typings.undiciTypes.connectorMod.buildConnector.BuildOptions
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchHandlers
import typings.undiciTypes.headerMod.IncomingHttpHeaders
import typings.undiciTypes.proxyAgentMod.ProxyAgent.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object proxyAgentMod {
  
  @JSImport("undici-types/proxy-agent", JSImport.Default)
  @js.native
  open class default protected () extends ProxyAgent {
    def this(options: String) = this()
    def this(options: Options) = this()
  }
  
  @js.native
  trait ProxyAgent
    extends typings.undiciTypes.dispatcherMod.default {
    
    def dispatch(options: DispatchOptions, handler: DispatchHandlers): Boolean = js.native
  }
  object ProxyAgent {
    
    trait Options
      extends StObject
         with typings.undiciTypes.agentMod.Agent.Options {
      
      /**
        * @deprecated use opts.token
        */
      var auth: js.UndefOr[String] = js.undefined
      
      var clientFactory: js.UndefOr[
            js.Function2[/* origin */ URL, /* opts */ js.Object, typings.undiciTypes.dispatcherMod.default]
          ] = js.undefined
      
      var headers: js.UndefOr[IncomingHttpHeaders] = js.undefined
      
      var proxyTls: js.UndefOr[BuildOptions] = js.undefined
      
      var requestTls: js.UndefOr[BuildOptions] = js.undefined
      
      var token: js.UndefOr[String] = js.undefined
      
      var uri: String
    }
    object Options {
      
      inline def apply(uri: String): Options = {
        val __obj = js.Dynamic.literal(uri = uri.asInstanceOf[js.Any])
        __obj.asInstanceOf[Options]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: Options] (val x: Self) extends AnyVal {
        
        inline def setAuth(value: String): Self = StObject.set(x, "auth", value.asInstanceOf[js.Any])
        
        inline def setAuthUndefined: Self = StObject.set(x, "auth", js.undefined)
        
        inline def setClientFactory(value: (/* origin */ URL, /* opts */ js.Object) => typings.undiciTypes.dispatcherMod.default): Self = StObject.set(x, "clientFactory", js.Any.fromFunction2(value))
        
        inline def setClientFactoryUndefined: Self = StObject.set(x, "clientFactory", js.undefined)
        
        inline def setHeaders(value: IncomingHttpHeaders): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
        
        inline def setHeadersUndefined: Self = StObject.set(x, "headers", js.undefined)
        
        inline def setProxyTls(value: BuildOptions): Self = StObject.set(x, "proxyTls", value.asInstanceOf[js.Any])
        
        inline def setProxyTlsUndefined: Self = StObject.set(x, "proxyTls", js.undefined)
        
        inline def setRequestTls(value: BuildOptions): Self = StObject.set(x, "requestTls", value.asInstanceOf[js.Any])
        
        inline def setRequestTlsUndefined: Self = StObject.set(x, "requestTls", js.undefined)
        
        inline def setToken(value: String): Self = StObject.set(x, "token", value.asInstanceOf[js.Any])
        
        inline def setTokenUndefined: Self = StObject.set(x, "token", js.undefined)
        
        inline def setUri(value: String): Self = StObject.set(x, "uri", value.asInstanceOf[js.Any])
      }
    }
  }
}

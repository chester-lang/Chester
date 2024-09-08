package typings.undiciTypes

import typings.undiciTypes.anon.AllowH2
import typings.undiciTypes.connectorMod.buildConnector.BuildOptions
import typings.undiciTypes.connectorMod.buildConnector.connector
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object connectorMod {
  
  object default {
    
    inline def apply(): connector = ^.asInstanceOf[js.Dynamic].apply().asInstanceOf[connector]
    inline def apply(options: BuildOptions): connector = ^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any]).asInstanceOf[connector]
    
    @JSImport("undici-types/connector", JSImport.Default)
    @js.native
    val ^ : js.Any = js.native
  }
  
  object buildConnector {
    
    type BuildOptions = (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify ConnectionOptions */ Any) & AllowH2
    
    type Callback = js.Function1[/* args */ CallbackArgs, Unit]
    
    type CallbackArgs = js.Tuple2[
        js.Error | Null, 
        (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any) | Null
      ]
    
    trait Options extends StObject {
      
      var host: js.UndefOr[String] = js.undefined
      
      var hostname: String
      
      var httpSocket: js.UndefOr[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
          ] = js.undefined
      
      var localAddress: js.UndefOr[String | Null] = js.undefined
      
      var port: String
      
      var protocol: String
      
      var servername: js.UndefOr[String] = js.undefined
    }
    object Options {
      
      inline def apply(hostname: String, port: String, protocol: String): Options = {
        val __obj = js.Dynamic.literal(hostname = hostname.asInstanceOf[js.Any], port = port.asInstanceOf[js.Any], protocol = protocol.asInstanceOf[js.Any])
        __obj.asInstanceOf[Options]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: Options] (val x: Self) extends AnyVal {
        
        inline def setHost(value: String): Self = StObject.set(x, "host", value.asInstanceOf[js.Any])
        
        inline def setHostUndefined: Self = StObject.set(x, "host", js.undefined)
        
        inline def setHostname(value: String): Self = StObject.set(x, "hostname", value.asInstanceOf[js.Any])
        
        inline def setHttpSocket(
          value: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
        ): Self = StObject.set(x, "httpSocket", value.asInstanceOf[js.Any])
        
        inline def setHttpSocketUndefined: Self = StObject.set(x, "httpSocket", js.undefined)
        
        inline def setLocalAddress(value: String): Self = StObject.set(x, "localAddress", value.asInstanceOf[js.Any])
        
        inline def setLocalAddressNull: Self = StObject.set(x, "localAddress", null)
        
        inline def setLocalAddressUndefined: Self = StObject.set(x, "localAddress", js.undefined)
        
        inline def setPort(value: String): Self = StObject.set(x, "port", value.asInstanceOf[js.Any])
        
        inline def setProtocol(value: String): Self = StObject.set(x, "protocol", value.asInstanceOf[js.Any])
        
        inline def setServername(value: String): Self = StObject.set(x, "servername", value.asInstanceOf[js.Any])
        
        inline def setServernameUndefined: Self = StObject.set(x, "servername", js.undefined)
      }
    }
    
    type connector = js.Function2[/* options */ Options, /* callback */ Callback, Unit]
  }
}

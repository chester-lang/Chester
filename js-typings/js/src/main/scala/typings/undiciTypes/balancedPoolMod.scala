package typings.undiciTypes

import org.scalajs.dom.URL
import typings.undiciTypes.dispatcherMod.AbortSignal
import typings.undiciTypes.dispatcherMod.Dispatcher.ConnectData
import typings.undiciTypes.headerMod.IncomingHttpHeaders
import typings.undiciTypes.poolMod.Pool.Options
import typings.undiciTypes.undiciTypesStrings.raw
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object balancedPoolMod {
  
  @JSImport("undici-types/balanced-pool", JSImport.Default)
  @js.native
  open class default protected () extends BalancedPool {
    def this(url: String) = this()
    def this(url: js.Array[String | URL]) = this()
    def this(url: URL) = this()
    def this(url: String, options: Options) = this()
    def this(url: js.Array[String | URL], options: Options) = this()
    def this(url: URL, options: Options) = this()
  }
  
  @js.native
  trait BalancedPool
    extends typings.undiciTypes.dispatcherMod.default {
    
    def addUpstream(upstream: String): BalancedPool = js.native
    def addUpstream(upstream: URL): BalancedPool = js.native
    
    /** `true` after `pool.close()` has been called. */
    var closed: Boolean = js.native
    
    // Override dispatcher APIs.
    def connect(options: BalancedPoolConnectOptions): js.Promise[ConnectData] = js.native
    def connect(
      options: BalancedPoolConnectOptions,
      callback: js.Function2[/* err */ js.Error | Null, /* data */ ConnectData, Unit]
    ): Unit = js.native
    
    /** `true` after `pool.destroyed()` has been called or `pool.close()` has been called and the pool shutdown has completed. */
    var destroyed: Boolean = js.native
    
    def removeUpstream(upstream: String): BalancedPool = js.native
    def removeUpstream(upstream: URL): BalancedPool = js.native
    
    var upstreams: js.Array[String] = js.native
  }
  
  /* Inlined std.Omit<undici-types.undici-types/dispatcher.default.ConnectOptions, 'origin'> */
  trait BalancedPoolConnectOptions extends StObject {
    
    var headers: js.UndefOr[IncomingHttpHeaders | js.Array[String] | Null] = js.undefined
    
    var maxRedirections: js.UndefOr[Double] = js.undefined
    
    var opaque: js.UndefOr[Any] = js.undefined
    
    var path: String
    
    var redirectionLimitReached: js.UndefOr[Boolean] = js.undefined
    
    var responseHeader: js.UndefOr[raw | Null] = js.undefined
    
    var signal: js.UndefOr[
        AbortSignal | (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify EventEmitter */ Any) | Null
      ] = js.undefined
  }
  object BalancedPoolConnectOptions {
    
    inline def apply(path: String): BalancedPoolConnectOptions = {
      val __obj = js.Dynamic.literal(path = path.asInstanceOf[js.Any])
      __obj.asInstanceOf[BalancedPoolConnectOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: BalancedPoolConnectOptions] (val x: Self) extends AnyVal {
      
      inline def setHeaders(value: IncomingHttpHeaders | js.Array[String]): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
      
      inline def setHeadersNull: Self = StObject.set(x, "headers", null)
      
      inline def setHeadersUndefined: Self = StObject.set(x, "headers", js.undefined)
      
      inline def setHeadersVarargs(value: String*): Self = StObject.set(x, "headers", js.Array(value*))
      
      inline def setMaxRedirections(value: Double): Self = StObject.set(x, "maxRedirections", value.asInstanceOf[js.Any])
      
      inline def setMaxRedirectionsUndefined: Self = StObject.set(x, "maxRedirections", js.undefined)
      
      inline def setOpaque(value: Any): Self = StObject.set(x, "opaque", value.asInstanceOf[js.Any])
      
      inline def setOpaqueUndefined: Self = StObject.set(x, "opaque", js.undefined)
      
      inline def setPath(value: String): Self = StObject.set(x, "path", value.asInstanceOf[js.Any])
      
      inline def setRedirectionLimitReached(value: Boolean): Self = StObject.set(x, "redirectionLimitReached", value.asInstanceOf[js.Any])
      
      inline def setRedirectionLimitReachedUndefined: Self = StObject.set(x, "redirectionLimitReached", js.undefined)
      
      inline def setResponseHeader(value: raw): Self = StObject.set(x, "responseHeader", value.asInstanceOf[js.Any])
      
      inline def setResponseHeaderNull: Self = StObject.set(x, "responseHeader", null)
      
      inline def setResponseHeaderUndefined: Self = StObject.set(x, "responseHeader", js.undefined)
      
      inline def setSignal(
        value: AbortSignal | (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify EventEmitter */ Any)
      ): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
      
      inline def setSignalNull: Self = StObject.set(x, "signal", null)
      
      inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    }
  }
}

package typings.undiciTypes

import typings.undiciTypes.dispatcherMod.Dispatcher.DispatcherComposeInterceptor
import typings.undiciTypes.retryHandlerMod.RetryHandler.RetryOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object interceptorsMod {
  
  @JSImport("undici-types/interceptors", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  inline def createRedirectInterceptor(opts: RedirectInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("createRedirectInterceptor")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  
  inline def dump(): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("dump")().asInstanceOf[DispatcherComposeInterceptor]
  inline def dump(opts: DumpInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("dump")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  
  inline def redirect(): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("redirect")().asInstanceOf[DispatcherComposeInterceptor]
  inline def redirect(opts: RedirectInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("redirect")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  
  inline def retry(): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("retry")().asInstanceOf[DispatcherComposeInterceptor]
  inline def retry(opts: RetryInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("retry")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  
  trait DumpInterceptorOpts extends StObject {
    
    var maxSize: js.UndefOr[Double] = js.undefined
  }
  object DumpInterceptorOpts {
    
    inline def apply(): DumpInterceptorOpts = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[DumpInterceptorOpts]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: DumpInterceptorOpts] (val x: Self) extends AnyVal {
      
      inline def setMaxSize(value: Double): Self = StObject.set(x, "maxSize", value.asInstanceOf[js.Any])
      
      inline def setMaxSizeUndefined: Self = StObject.set(x, "maxSize", js.undefined)
    }
  }
  
  trait RedirectInterceptorOpts extends StObject {
    
    var maxRedirections: js.UndefOr[Double] = js.undefined
  }
  object RedirectInterceptorOpts {
    
    inline def apply(): RedirectInterceptorOpts = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[RedirectInterceptorOpts]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: RedirectInterceptorOpts] (val x: Self) extends AnyVal {
      
      inline def setMaxRedirections(value: Double): Self = StObject.set(x, "maxRedirections", value.asInstanceOf[js.Any])
      
      inline def setMaxRedirectionsUndefined: Self = StObject.set(x, "maxRedirections", js.undefined)
    }
  }
  
  type RetryInterceptorOpts = RetryOptions
}

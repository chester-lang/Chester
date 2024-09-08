package typings.undiciTypes.mod.default

import typings.undiciTypes.dispatcherMod.Dispatcher.DispatcherComposeInterceptor
import typings.undiciTypes.interceptorsMod.DumpInterceptorOpts
import typings.undiciTypes.interceptorsMod.RedirectInterceptorOpts
import typings.undiciTypes.interceptorsMod.RetryInterceptorOpts
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object interceptors {
  
  @JSImport("undici-types", "default.interceptors")
  @js.native
  val ^ : js.Any = js.native
  
  inline def dump(): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("dump")().asInstanceOf[DispatcherComposeInterceptor]
  inline def dump(opts: DumpInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("dump")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  @JSImport("undici-types", "default.interceptors.dump")
  @js.native
  def dump_Finterceptors: js.Function1[/* opts */ js.UndefOr[DumpInterceptorOpts], DispatcherComposeInterceptor] = js.native
  
  inline def dump_Finterceptors_=(x: js.Function1[/* opts */ js.UndefOr[DumpInterceptorOpts], DispatcherComposeInterceptor]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("dump")(x.asInstanceOf[js.Any])
  
  inline def redirect(): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("redirect")().asInstanceOf[DispatcherComposeInterceptor]
  inline def redirect(opts: RedirectInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("redirect")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  @JSImport("undici-types", "default.interceptors.redirect")
  @js.native
  def redirect_Finterceptors: js.Function1[/* opts */ js.UndefOr[RedirectInterceptorOpts], DispatcherComposeInterceptor] = js.native
  
  inline def redirect_Finterceptors_=(x: js.Function1[/* opts */ js.UndefOr[RedirectInterceptorOpts], DispatcherComposeInterceptor]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("redirect")(x.asInstanceOf[js.Any])
  
  inline def retry(): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("retry")().asInstanceOf[DispatcherComposeInterceptor]
  inline def retry(opts: RetryInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("retry")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]
  @JSImport("undici-types", "default.interceptors.retry")
  @js.native
  def retry_Finterceptors: js.Function1[/* opts */ js.UndefOr[RetryInterceptorOpts], DispatcherComposeInterceptor] = js.native
  
  inline def retry_Finterceptors_=(x: js.Function1[/* opts */ js.UndefOr[RetryInterceptorOpts], DispatcherComposeInterceptor]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("retry")(x.asInstanceOf[js.Any])
}

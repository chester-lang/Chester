package typings.undiciTypes

import typings.undiciTypes.anon.DispatchOptionsretryOptio
import typings.undiciTypes.anon.Opts
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchHandlers
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchOptions
import typings.undiciTypes.dispatcherMod.Dispatcher.HttpMethod
import typings.undiciTypes.retryHandlerMod.RetryHandler.RetryHandlers
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object retryHandlerMod {
  
  @JSImport("undici-types/retry-handler", JSImport.Default)
  @js.native
  open class default protected ()
    extends StObject
       with DispatchHandlers {
    def this(options: DispatchOptionsretryOptio, retryHandlers: RetryHandlers) = this()
  }
  
  object RetryHandler {
    
    type OnRetryCallback = js.Function1[/* result */ js.UndefOr[js.Error | Null], Unit]
    
    type RetryCallback = js.Function3[/* err */ js.Error, /* context */ Opts, /* callback */ OnRetryCallback, Double | Null]
    
    trait RetryContext extends StObject {
      
      var opts: DispatchOptionsretryOptio
      
      var state: RetryState
    }
    object RetryContext {
      
      inline def apply(opts: DispatchOptionsretryOptio, state: RetryState): RetryContext = {
        val __obj = js.Dynamic.literal(opts = opts.asInstanceOf[js.Any], state = state.asInstanceOf[js.Any])
        __obj.asInstanceOf[RetryContext]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RetryContext] (val x: Self) extends AnyVal {
        
        inline def setOpts(value: DispatchOptionsretryOptio): Self = StObject.set(x, "opts", value.asInstanceOf[js.Any])
        
        inline def setState(value: RetryState): Self = StObject.set(x, "state", value.asInstanceOf[js.Any])
      }
    }
    
    trait RetryHandlers extends StObject {
      
      def dispatch(options: DispatchOptions, handler: DispatchHandlers): Boolean
      @JSName("dispatch")
      var dispatch_Original: js.Function2[/* options */ DispatchOptions, /* handler */ DispatchHandlers, Boolean]
      
      var handler: DispatchHandlers
    }
    object RetryHandlers {
      
      inline def apply(
        dispatch: (/* options */ DispatchOptions, /* handler */ DispatchHandlers) => Boolean,
        handler: DispatchHandlers
      ): RetryHandlers = {
        val __obj = js.Dynamic.literal(dispatch = js.Any.fromFunction2(dispatch), handler = handler.asInstanceOf[js.Any])
        __obj.asInstanceOf[RetryHandlers]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RetryHandlers] (val x: Self) extends AnyVal {
        
        inline def setDispatch(value: (/* options */ DispatchOptions, /* handler */ DispatchHandlers) => Boolean): Self = StObject.set(x, "dispatch", js.Any.fromFunction2(value))
        
        inline def setHandler(value: DispatchHandlers): Self = StObject.set(x, "handler", value.asInstanceOf[js.Any])
      }
    }
    
    trait RetryOptions extends StObject {
      
      /**
        * Error codes to be retried. e.g. `ECONNRESET`, `ENOTFOUND`, `ETIMEDOUT`, `ECONNREFUSED`, etc.
        *
        * @type {string[]}
        * @default ['ECONNRESET','ECONNREFUSED','ENOTFOUND','ENETDOWN','ENETUNREACH','EHOSTDOWN','EHOSTUNREACH','EPIPE']
        */
      var errorCodes: js.UndefOr[js.Array[String]] = js.undefined
      
      /**
        * Maximum number of retries to allow.
        *
        * @type {number}
        * @memberof RetryOptions
        * @default 5
        */
      var maxRetries: js.UndefOr[Double] = js.undefined
      
      /**
        * Max number of milliseconds allow between retries
        *
        * @type {number}
        * @memberof RetryOptions
        * @default 30000
        */
      var maxTimeout: js.UndefOr[Double] = js.undefined
      
      /**
        * HTTP methods to retry.
        *
        * @type {Dispatcher.HttpMethod[]}
        * @memberof RetryOptions
        * @default ['GET', 'HEAD', 'OPTIONS', 'PUT', 'DELETE', 'TRACE'],
        */
      var methods: js.UndefOr[js.Array[HttpMethod]] = js.undefined
      
      /**
        * Initial number of milliseconds to wait before retrying for the first time.
        *
        * @type {number}
        * @memberof RetryOptions
        * @default 500
        */
      var minTimeout: js.UndefOr[Double] = js.undefined
      
      /**
        * Callback to be invoked on every retry iteration.
        * It receives the error, current state of the retry object and the options object
        * passed when instantiating the retry handler.
        *
        * @type {RetryCallback}
        * @memberof RetryOptions
        */
      var retry: js.UndefOr[RetryCallback] = js.undefined
      
      /**
        * It enables to automatically infer timeout between retries based on the `Retry-After` header.
        *
        * @type {boolean}
        * @memberof RetryOptions
        * @default true
        */
      var retryAfter: js.UndefOr[Boolean] = js.undefined
      
      /**
        * HTTP status codes to be retried.
        *
        * @type {number[]}
        * @memberof RetryOptions
        * @default [500, 502, 503, 504, 429],
        */
      var statusCodes: js.UndefOr[js.Array[Double]] = js.undefined
      
      /**
        * Factior to multiply the timeout factor between retries.
        *
        * @type {number}
        * @memberof RetryOptions
        * @default 2
        */
      var timeoutFactor: js.UndefOr[Double] = js.undefined
    }
    object RetryOptions {
      
      inline def apply(): RetryOptions = {
        val __obj = js.Dynamic.literal()
        __obj.asInstanceOf[RetryOptions]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RetryOptions] (val x: Self) extends AnyVal {
        
        inline def setErrorCodes(value: js.Array[String]): Self = StObject.set(x, "errorCodes", value.asInstanceOf[js.Any])
        
        inline def setErrorCodesUndefined: Self = StObject.set(x, "errorCodes", js.undefined)
        
        inline def setErrorCodesVarargs(value: String*): Self = StObject.set(x, "errorCodes", js.Array(value*))
        
        inline def setMaxRetries(value: Double): Self = StObject.set(x, "maxRetries", value.asInstanceOf[js.Any])
        
        inline def setMaxRetriesUndefined: Self = StObject.set(x, "maxRetries", js.undefined)
        
        inline def setMaxTimeout(value: Double): Self = StObject.set(x, "maxTimeout", value.asInstanceOf[js.Any])
        
        inline def setMaxTimeoutUndefined: Self = StObject.set(x, "maxTimeout", js.undefined)
        
        inline def setMethods(value: js.Array[HttpMethod]): Self = StObject.set(x, "methods", value.asInstanceOf[js.Any])
        
        inline def setMethodsUndefined: Self = StObject.set(x, "methods", js.undefined)
        
        inline def setMethodsVarargs(value: HttpMethod*): Self = StObject.set(x, "methods", js.Array(value*))
        
        inline def setMinTimeout(value: Double): Self = StObject.set(x, "minTimeout", value.asInstanceOf[js.Any])
        
        inline def setMinTimeoutUndefined: Self = StObject.set(x, "minTimeout", js.undefined)
        
        inline def setRetry(value: (/* err */ js.Error, /* context */ Opts, /* callback */ OnRetryCallback) => Double | Null): Self = StObject.set(x, "retry", js.Any.fromFunction3(value))
        
        inline def setRetryAfter(value: Boolean): Self = StObject.set(x, "retryAfter", value.asInstanceOf[js.Any])
        
        inline def setRetryAfterUndefined: Self = StObject.set(x, "retryAfter", js.undefined)
        
        inline def setRetryUndefined: Self = StObject.set(x, "retry", js.undefined)
        
        inline def setStatusCodes(value: js.Array[Double]): Self = StObject.set(x, "statusCodes", value.asInstanceOf[js.Any])
        
        inline def setStatusCodesUndefined: Self = StObject.set(x, "statusCodes", js.undefined)
        
        inline def setStatusCodesVarargs(value: Double*): Self = StObject.set(x, "statusCodes", js.Array(value*))
        
        inline def setTimeoutFactor(value: Double): Self = StObject.set(x, "timeoutFactor", value.asInstanceOf[js.Any])
        
        inline def setTimeoutFactorUndefined: Self = StObject.set(x, "timeoutFactor", js.undefined)
      }
    }
    
    trait RetryState extends StObject {
      
      var counter: Double
    }
    object RetryState {
      
      inline def apply(counter: Double): RetryState = {
        val __obj = js.Dynamic.literal(counter = counter.asInstanceOf[js.Any])
        __obj.asInstanceOf[RetryState]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RetryState] (val x: Self) extends AnyVal {
        
        inline def setCounter(value: Double): Self = StObject.set(x, "counter", value.asInstanceOf[js.Any])
      }
    }
  }
  type RetryHandler = DispatchHandlers
}

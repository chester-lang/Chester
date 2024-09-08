package typings.undiciTypes.mod

import typings.undiciTypes.mockAgentMod.MockAgent.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object Undici {
  
  /* was `typeof imported_agent.default` */
  type Agent = typings.undiciTypes.agentMod.default
  
  /* was `typeof imported_balanced-pool.default` */
  type BalancedPool = typings.undiciTypes.balancedPoolMod.default
  
  /* was `typeof imported_client.default` */
  type Client = typings.undiciTypes.clientMod.default
  
  /* was `typeof imported_handlers.DecoratorHandler` */
  type DecoratorHandler = typings.undiciTypes.handlersMod.DecoratorHandler
  
  /** Dispatcher is the core API used to dispatch requests. */
  /* was `typeof imported_dispatcher.default` */
  type Dispatcher = typings.undiciTypes.dispatcherMod.default
  
  /* was `typeof imported_file.File` */
  type File = typings.undiciTypes.fileMod.File
  
  /* was `typeof imported_filereader.FileReader` */
  type FileReader = typings.undiciTypes.filereaderMod.FileReader
  
  /* was `typeof imported_formdata.FormData` */
  type FormData = typings.undiciTypes.formdataMod.FormData
  
  /* was `typeof imported_fetch.Headers` */
  type Headers = typings.undiciTypes.fetchMod.Headers
  
  /** A mocked Agent class that implements the Agent API. It allows one to intercept HTTP requests made through undici and return mocked responses instead. */
  /* was `typeof imported_mock-agent.default` */
  type MockAgent[TMockAgentOptions /* <: Options */] = typings.undiciTypes.mockAgentMod.default[TMockAgentOptions]
  
  /** MockClient extends the Client API and allows one to mock requests. */
  /* was `typeof imported_mock-client.default` */
  type MockClient = typings.undiciTypes.mockClientMod.default
  
  /** MockPool extends the Pool API and allows one to mock requests. */
  /* was `typeof imported_mock-pool.default` */
  type MockPool = typings.undiciTypes.mockPoolMod.default
  
  /* was `typeof imported_pool.default` */
  type Pool = typings.undiciTypes.poolMod.default
  
  /* was `typeof imported_handlers.RedirectHandler` */
  type RedirectHandler = typings.undiciTypes.handlersMod.RedirectHandler
  
  /* was `typeof imported_fetch.Request` */
  type Request = typings.undiciTypes.fetchMod.Request
  
  /* was `typeof imported_fetch.Response` */
  type Response = typings.undiciTypes.fetchMod.Response
  
  /* was `typeof imported_retry-handler.default` */
  type RetryHandler = typings.undiciTypes.retryHandlerMod.default
  
  /* was `typeof imported_errors.default` */
  object errors {
    
    type BalancedPoolMissingUpstreamError = typings.undiciTypes.errorsMod.default.BalancedPoolMissingUpstreamError
    
    type BodyTimeoutError = typings.undiciTypes.errorsMod.default.BodyTimeoutError
    
    type ClientClosedError = typings.undiciTypes.errorsMod.default.ClientClosedError
    
    type ClientDestroyedError = typings.undiciTypes.errorsMod.default.ClientDestroyedError
    
    type ConnectTimeoutError = typings.undiciTypes.errorsMod.default.ConnectTimeoutError
    
    type HTTPParserError = typings.undiciTypes.errorsMod.default.HTTPParserError
    
    type HeadersOverflowError = typings.undiciTypes.errorsMod.default.HeadersOverflowError
    
    type HeadersTimeoutError = typings.undiciTypes.errorsMod.default.HeadersTimeoutError
    
    type InformationalError = typings.undiciTypes.errorsMod.default.InformationalError
    
    type InvalidArgumentError = typings.undiciTypes.errorsMod.default.InvalidArgumentError
    
    type InvalidReturnValueError = typings.undiciTypes.errorsMod.default.InvalidReturnValueError
    
    type NotSupportedError = typings.undiciTypes.errorsMod.default.NotSupportedError
    
    type RequestAbortedError = typings.undiciTypes.errorsMod.default.RequestAbortedError
    
    type RequestContentLengthMismatchError = typings.undiciTypes.errorsMod.default.RequestContentLengthMismatchError
    
    type ResponseContentLengthMismatchError = typings.undiciTypes.errorsMod.default.ResponseContentLengthMismatchError
    
    type ResponseExceededMaxSizeError = typings.undiciTypes.errorsMod.default.ResponseExceededMaxSizeError
    
    type ResponseStatusCodeError = typings.undiciTypes.errorsMod.default.ResponseStatusCodeError
    
    type SocketError = typings.undiciTypes.errorsMod.default.SocketError
    
    type UndiciError = typings.undiciTypes.errorsMod.default.UndiciError
  }
  
  /* was `typeof imported_mock-errors.default` */
  object mockErrors {
    
    type MockNotMatchedError = typings.undiciTypes.mockErrorsMod.default.MockNotMatchedError
  }
}

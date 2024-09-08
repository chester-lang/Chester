package typings.undiciTypes.errorsMod

import typings.std.Record
import typings.undiciTypes.clientMod.Client.SocketInfo
import typings.undiciTypes.headerMod.IncomingHttpHeaders
import typings.undiciTypes.undiciTypesStrings.AbortError
import typings.undiciTypes.undiciTypesStrings.MissingUpstreamError
import typings.undiciTypes.undiciTypesStrings.UND_ERR_ABORTED
import typings.undiciTypes.undiciTypesStrings.UND_ERR_BODY_TIMEOUT
import typings.undiciTypes.undiciTypesStrings.UND_ERR_BPL_MISSING_UPSTREAM
import typings.undiciTypes.undiciTypesStrings.UND_ERR_CLOSED
import typings.undiciTypes.undiciTypesStrings.UND_ERR_CONNECT_TIMEOUT
import typings.undiciTypes.undiciTypesStrings.UND_ERR_DESTROYED
import typings.undiciTypes.undiciTypesStrings.UND_ERR_HEADERS_OVERFLOW
import typings.undiciTypes.undiciTypesStrings.UND_ERR_HEADERS_TIMEOUT
import typings.undiciTypes.undiciTypesStrings.UND_ERR_INFO
import typings.undiciTypes.undiciTypesStrings.UND_ERR_INVALID_ARG
import typings.undiciTypes.undiciTypesStrings.UND_ERR_INVALID_RETURN_VALUE
import typings.undiciTypes.undiciTypesStrings.UND_ERR_NOT_SUPPORTED
import typings.undiciTypes.undiciTypesStrings.UND_ERR_REQ_CONTENT_LENGTH_MISMATCH
import typings.undiciTypes.undiciTypesStrings.UND_ERR_RESPONSE_STATUS_CODE
import typings.undiciTypes.undiciTypesStrings.UND_ERR_RES_CONTENT_LENGTH_MISMATCH
import typings.undiciTypes.undiciTypesStrings.UND_ERR_RES_EXCEEDED_MAX_SIZE
import typings.undiciTypes.undiciTypesStrings.UND_ERR_SOCKET
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object default {
  
  @JSImport("undici-types/errors", "default.BalancedPoolMissingUpstreamError")
  @js.native
  open class BalancedPoolMissingUpstreamError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.BalancedPoolMissingUpstreamError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_BalancedPoolMissingUpstreamError: UND_ERR_BPL_MISSING_UPSTREAM = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_BalancedPoolMissingUpstreamError: MissingUpstreamError = js.native
  }
  
  @JSImport("undici-types/errors", "default.BodyTimeoutError")
  @js.native
  open class BodyTimeoutError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.BodyTimeoutError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_BodyTimeoutError: UND_ERR_BODY_TIMEOUT = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_BodyTimeoutError: typings.undiciTypes.undiciTypesStrings.BodyTimeoutError = js.native
  }
  
  @JSImport("undici-types/errors", "default.ClientClosedError")
  @js.native
  open class ClientClosedError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.ClientClosedError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_ClientClosedError: UND_ERR_CLOSED = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_ClientClosedError: typings.undiciTypes.undiciTypesStrings.ClientClosedError = js.native
  }
  
  @JSImport("undici-types/errors", "default.ClientDestroyedError")
  @js.native
  open class ClientDestroyedError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.ClientDestroyedError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_ClientDestroyedError: UND_ERR_DESTROYED = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_ClientDestroyedError: typings.undiciTypes.undiciTypesStrings.ClientDestroyedError = js.native
  }
  
  @JSImport("undici-types/errors", "default.ConnectTimeoutError")
  @js.native
  open class ConnectTimeoutError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.ConnectTimeoutError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_ConnectTimeoutError: UND_ERR_CONNECT_TIMEOUT = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_ConnectTimeoutError: typings.undiciTypes.undiciTypesStrings.ConnectTimeoutError = js.native
  }
  
  @JSImport("undici-types/errors", "default.HTTPParserError")
  @js.native
  open class HTTPParserError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.HTTPParserError {
    
    /* CompleteClass */
    var code: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_HTTPParserError: typings.undiciTypes.undiciTypesStrings.HTTPParserError = js.native
  }
  
  @JSImport("undici-types/errors", "default.HeadersOverflowError")
  @js.native
  open class HeadersOverflowError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.HeadersOverflowError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_HeadersOverflowError: UND_ERR_HEADERS_OVERFLOW = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_HeadersOverflowError: typings.undiciTypes.undiciTypesStrings.HeadersOverflowError = js.native
  }
  
  @JSImport("undici-types/errors", "default.HeadersTimeoutError")
  @js.native
  open class HeadersTimeoutError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.HeadersTimeoutError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_HeadersTimeoutError: UND_ERR_HEADERS_TIMEOUT = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_HeadersTimeoutError: typings.undiciTypes.undiciTypesStrings.HeadersTimeoutError = js.native
  }
  
  @JSImport("undici-types/errors", "default.InformationalError")
  @js.native
  open class InformationalError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.InformationalError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_InformationalError: UND_ERR_INFO = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_InformationalError: typings.undiciTypes.undiciTypesStrings.InformationalError = js.native
  }
  
  @JSImport("undici-types/errors", "default.InvalidArgumentError")
  @js.native
  open class InvalidArgumentError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.InvalidArgumentError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_InvalidArgumentError: UND_ERR_INVALID_ARG = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_InvalidArgumentError: typings.undiciTypes.undiciTypesStrings.InvalidArgumentError = js.native
  }
  
  @JSImport("undici-types/errors", "default.InvalidReturnValueError")
  @js.native
  open class InvalidReturnValueError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.InvalidReturnValueError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_InvalidReturnValueError: UND_ERR_INVALID_RETURN_VALUE = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_InvalidReturnValueError: typings.undiciTypes.undiciTypesStrings.InvalidReturnValueError = js.native
  }
  
  @JSImport("undici-types/errors", "default.NotSupportedError")
  @js.native
  open class NotSupportedError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.NotSupportedError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_NotSupportedError: UND_ERR_NOT_SUPPORTED = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_NotSupportedError: typings.undiciTypes.undiciTypesStrings.NotSupportedError = js.native
  }
  
  @JSImport("undici-types/errors", "default.RequestAbortedError")
  @js.native
  open class RequestAbortedError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.RequestAbortedError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_RequestAbortedError: UND_ERR_ABORTED = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_RequestAbortedError: AbortError = js.native
  }
  
  @JSImport("undici-types/errors", "default.RequestContentLengthMismatchError")
  @js.native
  open class RequestContentLengthMismatchError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.RequestContentLengthMismatchError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_RequestContentLengthMismatchError: UND_ERR_REQ_CONTENT_LENGTH_MISMATCH = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_RequestContentLengthMismatchError: typings.undiciTypes.undiciTypesStrings.RequestContentLengthMismatchError = js.native
  }
  
  @JSImport("undici-types/errors", "default.ResponseContentLengthMismatchError")
  @js.native
  open class ResponseContentLengthMismatchError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.ResponseContentLengthMismatchError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_ResponseContentLengthMismatchError: UND_ERR_RES_CONTENT_LENGTH_MISMATCH = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_ResponseContentLengthMismatchError: typings.undiciTypes.undiciTypesStrings.ResponseContentLengthMismatchError = js.native
  }
  
  @JSImport("undici-types/errors", "default.ResponseExceededMaxSizeError")
  @js.native
  open class ResponseExceededMaxSizeError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.ResponseExceededMaxSizeError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_ResponseExceededMaxSizeError: UND_ERR_RES_EXCEEDED_MAX_SIZE = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_ResponseExceededMaxSizeError: typings.undiciTypes.undiciTypesStrings.ResponseExceededMaxSizeError = js.native
  }
  
  @JSImport("undici-types/errors", "default.ResponseStatusCodeError")
  @js.native
  open class ResponseStatusCodeError protected ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.ResponseStatusCodeError {
    def this(
      message: js.UndefOr[String],
      statusCode: js.UndefOr[Double],
      headers: js.UndefOr[IncomingHttpHeaders | js.Array[String] | Null],
      body: js.UndefOr[Null | (Record[String, Any]) | String]
    ) = this()
    
    /* CompleteClass */
    var body: Null | (Record[String, Any]) | String = js.native
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_ResponseStatusCodeError: UND_ERR_RESPONSE_STATUS_CODE = js.native
    
    /* CompleteClass */
    var headers: IncomingHttpHeaders | js.Array[String] | Null = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_ResponseStatusCodeError: typings.undiciTypes.undiciTypesStrings.ResponseStatusCodeError = js.native
    
    /* CompleteClass */
    var status: Double = js.native
    
    /* CompleteClass */
    var statusCode: Double = js.native
  }
  
  @JSImport("undici-types/errors", "default.SocketError")
  @js.native
  open class SocketError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.SocketError {
    
    /* CompleteClass */
    var code: String = js.native
    /* CompleteClass */
    @JSName("code")
    var code_SocketError: UND_ERR_SOCKET = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
    /* CompleteClass */
    @JSName("name")
    var name_SocketError: typings.undiciTypes.undiciTypesStrings.SocketError = js.native
    
    /* CompleteClass */
    var socket: SocketInfo | Null = js.native
  }
  
  @JSImport("undici-types/errors", "default.UndiciError")
  @js.native
  open class UndiciError ()
    extends StObject
       with typings.undiciTypes.errorsMod.Errors.UndiciError {
    
    /* CompleteClass */
    var code: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var message: String = js.native
    
    /* standard es5 */
    /* CompleteClass */
    var name: String = js.native
  }
}

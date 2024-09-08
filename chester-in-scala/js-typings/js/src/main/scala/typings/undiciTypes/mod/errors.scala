package typings.undiciTypes.mod

import typings.std.Record
import typings.undiciTypes.headerMod.IncomingHttpHeaders
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object errors {
  
  @JSImport("undici-types", "errors.BalancedPoolMissingUpstreamError")
  @js.native
  open class BalancedPoolMissingUpstreamError ()
    extends typings.undiciTypes.errorsMod.default.BalancedPoolMissingUpstreamError
  
  @JSImport("undici-types", "errors.BodyTimeoutError")
  @js.native
  open class BodyTimeoutError ()
    extends typings.undiciTypes.errorsMod.default.BodyTimeoutError
  
  @JSImport("undici-types", "errors.ClientClosedError")
  @js.native
  open class ClientClosedError ()
    extends typings.undiciTypes.errorsMod.default.ClientClosedError
  
  @JSImport("undici-types", "errors.ClientDestroyedError")
  @js.native
  open class ClientDestroyedError ()
    extends typings.undiciTypes.errorsMod.default.ClientDestroyedError
  
  @JSImport("undici-types", "errors.ConnectTimeoutError")
  @js.native
  open class ConnectTimeoutError ()
    extends typings.undiciTypes.errorsMod.default.ConnectTimeoutError
  
  @JSImport("undici-types", "errors.HTTPParserError")
  @js.native
  open class HTTPParserError ()
    extends typings.undiciTypes.errorsMod.default.HTTPParserError
  
  @JSImport("undici-types", "errors.HeadersOverflowError")
  @js.native
  open class HeadersOverflowError ()
    extends typings.undiciTypes.errorsMod.default.HeadersOverflowError
  
  @JSImport("undici-types", "errors.HeadersTimeoutError")
  @js.native
  open class HeadersTimeoutError ()
    extends typings.undiciTypes.errorsMod.default.HeadersTimeoutError
  
  @JSImport("undici-types", "errors.InformationalError")
  @js.native
  open class InformationalError ()
    extends typings.undiciTypes.errorsMod.default.InformationalError
  
  @JSImport("undici-types", "errors.InvalidArgumentError")
  @js.native
  open class InvalidArgumentError ()
    extends typings.undiciTypes.errorsMod.default.InvalidArgumentError
  
  @JSImport("undici-types", "errors.InvalidReturnValueError")
  @js.native
  open class InvalidReturnValueError ()
    extends typings.undiciTypes.errorsMod.default.InvalidReturnValueError
  
  @JSImport("undici-types", "errors.NotSupportedError")
  @js.native
  open class NotSupportedError ()
    extends typings.undiciTypes.errorsMod.default.NotSupportedError
  
  @JSImport("undici-types", "errors.RequestAbortedError")
  @js.native
  open class RequestAbortedError ()
    extends typings.undiciTypes.errorsMod.default.RequestAbortedError
  
  @JSImport("undici-types", "errors.RequestContentLengthMismatchError")
  @js.native
  open class RequestContentLengthMismatchError ()
    extends typings.undiciTypes.errorsMod.default.RequestContentLengthMismatchError
  
  @JSImport("undici-types", "errors.ResponseContentLengthMismatchError")
  @js.native
  open class ResponseContentLengthMismatchError ()
    extends typings.undiciTypes.errorsMod.default.ResponseContentLengthMismatchError
  
  @JSImport("undici-types", "errors.ResponseExceededMaxSizeError")
  @js.native
  open class ResponseExceededMaxSizeError ()
    extends typings.undiciTypes.errorsMod.default.ResponseExceededMaxSizeError
  
  @JSImport("undici-types", "errors.ResponseStatusCodeError")
  @js.native
  open class ResponseStatusCodeError protected ()
    extends typings.undiciTypes.errorsMod.default.ResponseStatusCodeError {
    def this(
      message: js.UndefOr[String],
      statusCode: js.UndefOr[Double],
      headers: js.UndefOr[IncomingHttpHeaders | js.Array[String] | Null],
      body: js.UndefOr[Null | (Record[String, Any]) | String]
    ) = this()
  }
  
  @JSImport("undici-types", "errors.SocketError")
  @js.native
  open class SocketError ()
    extends typings.undiciTypes.errorsMod.default.SocketError
  
  @JSImport("undici-types", "errors.UndiciError")
  @js.native
  open class UndiciError ()
    extends typings.undiciTypes.errorsMod.default.UndiciError
}

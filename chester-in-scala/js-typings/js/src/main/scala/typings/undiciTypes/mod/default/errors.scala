package typings.undiciTypes.mod.default

import typings.std.Record
import typings.undiciTypes.headerMod.IncomingHttpHeaders
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* was `typeof imported_errors.default` */
object errors {
  
  @JSImport("undici-types", "default.errors.BalancedPoolMissingUpstreamError")
  @js.native
  open class BalancedPoolMissingUpstreamError ()
    extends typings.undiciTypes.errorsMod.default.BalancedPoolMissingUpstreamError
  
  @JSImport("undici-types", "default.errors.BodyTimeoutError")
  @js.native
  open class BodyTimeoutError ()
    extends typings.undiciTypes.errorsMod.default.BodyTimeoutError
  
  @JSImport("undici-types", "default.errors.ClientClosedError")
  @js.native
  open class ClientClosedError ()
    extends typings.undiciTypes.errorsMod.default.ClientClosedError
  
  @JSImport("undici-types", "default.errors.ClientDestroyedError")
  @js.native
  open class ClientDestroyedError ()
    extends typings.undiciTypes.errorsMod.default.ClientDestroyedError
  
  @JSImport("undici-types", "default.errors.ConnectTimeoutError")
  @js.native
  open class ConnectTimeoutError ()
    extends typings.undiciTypes.errorsMod.default.ConnectTimeoutError
  
  @JSImport("undici-types", "default.errors.HTTPParserError")
  @js.native
  open class HTTPParserError ()
    extends typings.undiciTypes.errorsMod.default.HTTPParserError
  
  @JSImport("undici-types", "default.errors.HeadersOverflowError")
  @js.native
  open class HeadersOverflowError ()
    extends typings.undiciTypes.errorsMod.default.HeadersOverflowError
  
  @JSImport("undici-types", "default.errors.HeadersTimeoutError")
  @js.native
  open class HeadersTimeoutError ()
    extends typings.undiciTypes.errorsMod.default.HeadersTimeoutError
  
  @JSImport("undici-types", "default.errors.InformationalError")
  @js.native
  open class InformationalError ()
    extends typings.undiciTypes.errorsMod.default.InformationalError
  
  @JSImport("undici-types", "default.errors.InvalidArgumentError")
  @js.native
  open class InvalidArgumentError ()
    extends typings.undiciTypes.errorsMod.default.InvalidArgumentError
  
  @JSImport("undici-types", "default.errors.InvalidReturnValueError")
  @js.native
  open class InvalidReturnValueError ()
    extends typings.undiciTypes.errorsMod.default.InvalidReturnValueError
  
  @JSImport("undici-types", "default.errors.NotSupportedError")
  @js.native
  open class NotSupportedError ()
    extends typings.undiciTypes.errorsMod.default.NotSupportedError
  
  @JSImport("undici-types", "default.errors.RequestAbortedError")
  @js.native
  open class RequestAbortedError ()
    extends typings.undiciTypes.errorsMod.default.RequestAbortedError
  
  @JSImport("undici-types", "default.errors.RequestContentLengthMismatchError")
  @js.native
  open class RequestContentLengthMismatchError ()
    extends typings.undiciTypes.errorsMod.default.RequestContentLengthMismatchError
  
  @JSImport("undici-types", "default.errors.ResponseContentLengthMismatchError")
  @js.native
  open class ResponseContentLengthMismatchError ()
    extends typings.undiciTypes.errorsMod.default.ResponseContentLengthMismatchError
  
  @JSImport("undici-types", "default.errors.ResponseExceededMaxSizeError")
  @js.native
  open class ResponseExceededMaxSizeError ()
    extends typings.undiciTypes.errorsMod.default.ResponseExceededMaxSizeError
  
  @JSImport("undici-types", "default.errors.ResponseStatusCodeError")
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
  
  @JSImport("undici-types", "default.errors.SocketError")
  @js.native
  open class SocketError ()
    extends typings.undiciTypes.errorsMod.default.SocketError
  
  @JSImport("undici-types", "default.errors.UndiciError")
  @js.native
  open class UndiciError ()
    extends typings.undiciTypes.errorsMod.default.UndiciError
}

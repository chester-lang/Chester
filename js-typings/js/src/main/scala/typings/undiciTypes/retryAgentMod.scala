package typings.undiciTypes

import typings.undiciTypes.retryHandlerMod.RetryHandler.RetryOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object retryAgentMod {
  
  @JSImport("undici-types/retry-agent", JSImport.Default)
  @js.native
  open class default protected ()
    extends typings.undiciTypes.dispatcherMod.default {
    def this(dispatcher: typings.undiciTypes.dispatcherMod.default) = this()
    def this(dispatcher: typings.undiciTypes.dispatcherMod.default, options: RetryOptions) = this()
  }
  
  type RetryAgent = typings.undiciTypes.dispatcherMod.default
}

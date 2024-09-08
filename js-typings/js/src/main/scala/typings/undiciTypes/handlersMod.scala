package typings.undiciTypes

import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchHandlers
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchOptions
import typings.undiciTypes.dispatcherMod.default
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object handlersMod {
  
  @JSImport("undici-types/handlers", "DecoratorHandler")
  @js.native
  open class DecoratorHandler protected ()
    extends StObject
       with DispatchHandlers {
    def this(handler: DispatchHandlers) = this()
  }
  
  @JSImport("undici-types/handlers", "RedirectHandler")
  @js.native
  open class RedirectHandler protected ()
    extends StObject
       with DispatchHandlers {
    def this(
      dispatch: default,
      maxRedirections: Double,
      opts: DispatchOptions,
      handler: DispatchHandlers,
      redirectionLimitReached: Boolean
    ) = this()
  }
}

package typings.undiciTypes.mod

import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchHandlers
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatchOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@JSImport("undici-types", "RedirectHandler")
@js.native
open class RedirectHandler protected ()
  extends typings.undiciTypes.handlersMod.RedirectHandler {
  def this(
    dispatch: typings.undiciTypes.dispatcherMod.default,
    maxRedirections: Double,
    opts: DispatchOptions,
    handler: DispatchHandlers,
    redirectionLimitReached: Boolean
  ) = this()
}

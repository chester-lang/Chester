package typings.undiciTypes.mod.default

import typings.undiciTypes.mockAgentMod.MockAgent.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** A mocked Agent class that implements the Agent API. It allows one to intercept HTTP requests made through undici and return mocked responses instead. */
/* was `typeof imported_mock-agent.default` */
@JSImport("undici-types", "default.MockAgent")
@js.native
open class MockAgent[TMockAgentOptions /* <: Options */] ()
  extends typings.undiciTypes.mockAgentMod.default[TMockAgentOptions] {
  def this(options: Options) = this()
}

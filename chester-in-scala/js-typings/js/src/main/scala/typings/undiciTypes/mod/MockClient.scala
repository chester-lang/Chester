package typings.undiciTypes.mod

import typings.undiciTypes.mockClientMod.MockClient.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** MockClient extends the Client API and allows one to mock requests. */
@JSImport("undici-types", "MockClient")
@js.native
open class MockClient protected ()
  extends typings.undiciTypes.mockClientMod.default {
  def this(origin: String, options: Options) = this()
}

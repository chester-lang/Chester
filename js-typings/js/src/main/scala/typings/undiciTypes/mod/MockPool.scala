package typings.undiciTypes.mod

import typings.undiciTypes.mockPoolMod.MockPool.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** MockPool extends the Pool API and allows one to mock requests. */
@JSImport("undici-types", "MockPool")
@js.native
open class MockPool protected ()
  extends typings.undiciTypes.mockPoolMod.default {
  def this(origin: String, options: Options) = this()
}

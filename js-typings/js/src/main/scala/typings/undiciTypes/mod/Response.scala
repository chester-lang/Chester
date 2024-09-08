package typings.undiciTypes.mod

import org.scalajs.dom.URL
import typings.undiciTypes.fetchMod.BodyInit
import typings.undiciTypes.fetchMod.ResponseInit
import typings.undiciTypes.fetchMod.ResponseRedirectStatus
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@JSImport("undici-types", "Response")
@js.native
open class Response ()
  extends typings.undiciTypes.fetchMod.Response {
  def this(body: BodyInit) = this()
  def this(body: Unit, init: ResponseInit) = this()
  def this(body: BodyInit, init: ResponseInit) = this()
}
/* static members */
object Response {
  
  @JSImport("undici-types", "Response")
  @js.native
  val ^ : js.Any = js.native
  
  inline def error(): typings.undiciTypes.fetchMod.Response = ^.asInstanceOf[js.Dynamic].applyDynamic("error")().asInstanceOf[typings.undiciTypes.fetchMod.Response]
  
  inline def json(data: Any): typings.undiciTypes.fetchMod.Response = ^.asInstanceOf[js.Dynamic].applyDynamic("json")(data.asInstanceOf[js.Any]).asInstanceOf[typings.undiciTypes.fetchMod.Response]
  inline def json(data: Any, init: ResponseInit): typings.undiciTypes.fetchMod.Response = (^.asInstanceOf[js.Dynamic].applyDynamic("json")(data.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[typings.undiciTypes.fetchMod.Response]
  
  inline def redirect(url: String, status: ResponseRedirectStatus): typings.undiciTypes.fetchMod.Response = (^.asInstanceOf[js.Dynamic].applyDynamic("redirect")(url.asInstanceOf[js.Any], status.asInstanceOf[js.Any])).asInstanceOf[typings.undiciTypes.fetchMod.Response]
  inline def redirect(url: URL, status: ResponseRedirectStatus): typings.undiciTypes.fetchMod.Response = (^.asInstanceOf[js.Dynamic].applyDynamic("redirect")(url.asInstanceOf[js.Any], status.asInstanceOf[js.Any])).asInstanceOf[typings.undiciTypes.fetchMod.Response]
}

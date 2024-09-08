package typings.undiciTypes.mod

import org.scalajs.dom.URL
import typings.undiciTypes.websocketMod.WebSocketInit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
@JSImport("undici-types", "WebSocket")
@js.native
open class WebSocketCls protected ()
  extends StObject
     with typings.undiciTypes.websocketMod.WebSocket {
  def this(url: String) = this()
  def this(url: URL) = this()
  def this(url: String, protocols: String) = this()
  def this(url: String, protocols: js.Array[String]) = this()
  def this(url: String, protocols: WebSocketInit) = this()
  def this(url: URL, protocols: String) = this()
  def this(url: URL, protocols: js.Array[String]) = this()
  def this(url: URL, protocols: WebSocketInit) = this()
}

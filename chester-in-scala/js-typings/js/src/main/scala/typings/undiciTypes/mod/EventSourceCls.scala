package typings.undiciTypes.mod

import org.scalajs.dom.URL
import typings.undiciTypes.eventsourceMod.EventSourceInit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
@JSImport("undici-types", "EventSource")
@js.native
open class EventSourceCls protected ()
  extends StObject
     with typings.undiciTypes.eventsourceMod.EventSource {
  def this(url: String) = this()
  def this(url: URL) = this()
  def this(url: String, init: EventSourceInit) = this()
  def this(url: URL, init: EventSourceInit) = this()
}

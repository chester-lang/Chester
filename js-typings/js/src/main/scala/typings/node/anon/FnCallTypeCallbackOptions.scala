package typings.node.anon

import org.scalajs.dom.EventListenerOptions
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait FnCallTypeCallbackOptions extends StObject {
  
  def apply(`type`: String): Unit = js.native
  def apply(`type`: String, callback: Null, options: EventListenerOptions): Unit = js.native
  def apply(`type`: String, callback: Null, options: Boolean): Unit = js.native
  def apply(`type`: String, callback: EventListenerOrEventListenerObject): Unit = js.native
  def apply(`type`: String, callback: EventListenerOrEventListenerObject, options: EventListenerOptions): Unit = js.native
  def apply(`type`: String, callback: EventListenerOrEventListenerObject, options: Boolean): Unit = js.native
}

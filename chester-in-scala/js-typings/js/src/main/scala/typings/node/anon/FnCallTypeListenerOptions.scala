package typings.node.anon

import typings.std.AddEventListenerOptions
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait FnCallTypeListenerOptions extends StObject {
  
  def apply(`type`: String): Unit = js.native
  def apply(`type`: String, listener: Null, options: Boolean): Unit = js.native
  def apply(`type`: String, listener: Null, options: AddEventListenerOptions): Unit = js.native
  def apply(`type`: String, listener: EventListenerOrEventListenerObject): Unit = js.native
  def apply(`type`: String, listener: EventListenerOrEventListenerObject, options: Boolean): Unit = js.native
  def apply(`type`: String, listener: EventListenerOrEventListenerObject, options: AddEventListenerOptions): Unit = js.native
}

package typings.undiciTypes.mod

import typings.undiciTypes.websocketMod.CloseEvent
import typings.undiciTypes.websocketMod.CloseEventInit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
@JSImport("undici-types", "CloseEvent")
@js.native
open class CloseEventCls protected ()
  extends StObject
     with CloseEvent {
  def this(`type`: String) = this()
  def this(`type`: String, eventInitDict: CloseEventInit) = this()
  
  /* CompleteClass */
  override val code: Double = js.native
  
  /* CompleteClass */
  override val reason: String = js.native
  
  /* CompleteClass */
  override val wasClean: Boolean = js.native
}

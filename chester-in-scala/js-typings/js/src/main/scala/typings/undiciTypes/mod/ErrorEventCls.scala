package typings.undiciTypes.mod

import typings.undiciTypes.websocketMod.ErrorEvent
import typings.undiciTypes.websocketMod.ErrorEventInit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
@JSImport("undici-types", "ErrorEvent")
@js.native
open class ErrorEventCls protected ()
  extends StObject
     with ErrorEvent {
  def this(`type`: String) = this()
  def this(`type`: String, eventInitDict: ErrorEventInit) = this()
  
  /* CompleteClass */
  override val colno: Double = js.native
  
  /* CompleteClass */
  override val error: Any = js.native
  
  /* CompleteClass */
  override val filename: String = js.native
  
  /* CompleteClass */
  override val lineno: Double = js.native
  
  /* CompleteClass */
  override val message: String = js.native
}

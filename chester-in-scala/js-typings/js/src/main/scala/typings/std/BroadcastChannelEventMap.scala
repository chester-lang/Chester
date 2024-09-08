package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BroadcastChannelEventMap extends StObject {
  
  /* standard dom */
  var message: org.scalajs.dom.MessageEvent
  
  /* standard dom */
  var messageerror: org.scalajs.dom.MessageEvent
}
object BroadcastChannelEventMap {
  
  inline def apply(message: org.scalajs.dom.MessageEvent, messageerror: org.scalajs.dom.MessageEvent): BroadcastChannelEventMap = {
    val __obj = js.Dynamic.literal(message = message.asInstanceOf[js.Any], messageerror = messageerror.asInstanceOf[js.Any])
    __obj.asInstanceOf[BroadcastChannelEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BroadcastChannelEventMap] (val x: Self) extends AnyVal {
    
    inline def setMessage(value: org.scalajs.dom.MessageEvent): Self = StObject.set(x, "message", value.asInstanceOf[js.Any])
    
    inline def setMessageerror(value: org.scalajs.dom.MessageEvent): Self = StObject.set(x, "messageerror", value.asInstanceOf[js.Any])
  }
}

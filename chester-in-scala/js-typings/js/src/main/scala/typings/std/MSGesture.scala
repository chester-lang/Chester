package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MSGesture extends StObject {
  
  /* standard dom */
  def addPointer(pointerId: Double): Unit
  
  /* standard dom */
  def stop(): Unit
  
  /* standard dom */
  var target: org.scalajs.dom.Element
}
object MSGesture {
  
  inline def apply(addPointer: Double => Unit, stop: () => Unit, target: org.scalajs.dom.Element): MSGesture = {
    val __obj = js.Dynamic.literal(addPointer = js.Any.fromFunction1(addPointer), stop = js.Any.fromFunction0(stop), target = target.asInstanceOf[js.Any])
    __obj.asInstanceOf[MSGesture]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MSGesture] (val x: Self) extends AnyVal {
    
    inline def setAddPointer(value: Double => Unit): Self = StObject.set(x, "addPointer", js.Any.fromFunction1(value))
    
    inline def setStop(value: () => Unit): Self = StObject.set(x, "stop", js.Any.fromFunction0(value))
    
    inline def setTarget(value: org.scalajs.dom.Element): Self = StObject.set(x, "target", value.asInstanceOf[js.Any])
  }
}

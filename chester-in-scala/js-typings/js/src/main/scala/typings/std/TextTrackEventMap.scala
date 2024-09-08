package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TextTrackEventMap extends StObject {
  
  /* standard dom */
  var cuechange: org.scalajs.dom.Event
}
object TextTrackEventMap {
  
  inline def apply(cuechange: org.scalajs.dom.Event): TextTrackEventMap = {
    val __obj = js.Dynamic.literal(cuechange = cuechange.asInstanceOf[js.Any])
    __obj.asInstanceOf[TextTrackEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TextTrackEventMap] (val x: Self) extends AnyVal {
    
    inline def setCuechange(value: org.scalajs.dom.Event): Self = StObject.set(x, "cuechange", value.asInstanceOf[js.Any])
  }
}

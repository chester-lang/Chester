package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MediaStreamEventInit
  extends StObject
     with EventInit {
  
  /* standard dom */
  var stream: js.UndefOr[org.scalajs.dom.MediaStream] = js.undefined
}
object MediaStreamEventInit {
  
  inline def apply(): MediaStreamEventInit = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[MediaStreamEventInit]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MediaStreamEventInit] (val x: Self) extends AnyVal {
    
    inline def setStream(value: org.scalajs.dom.MediaStream): Self = StObject.set(x, "stream", value.asInstanceOf[js.Any])
    
    inline def setStreamUndefined: Self = StObject.set(x, "stream", js.undefined)
  }
}

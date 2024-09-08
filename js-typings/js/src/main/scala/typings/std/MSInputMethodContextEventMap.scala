package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MSInputMethodContextEventMap extends StObject {
  
  /* standard dom */
  var MSCandidateWindowHide: org.scalajs.dom.Event
  
  /* standard dom */
  var MSCandidateWindowShow: org.scalajs.dom.Event
  
  /* standard dom */
  var MSCandidateWindowUpdate: org.scalajs.dom.Event
}
object MSInputMethodContextEventMap {
  
  inline def apply(
    MSCandidateWindowHide: org.scalajs.dom.Event,
    MSCandidateWindowShow: org.scalajs.dom.Event,
    MSCandidateWindowUpdate: org.scalajs.dom.Event
  ): MSInputMethodContextEventMap = {
    val __obj = js.Dynamic.literal(MSCandidateWindowHide = MSCandidateWindowHide.asInstanceOf[js.Any], MSCandidateWindowShow = MSCandidateWindowShow.asInstanceOf[js.Any], MSCandidateWindowUpdate = MSCandidateWindowUpdate.asInstanceOf[js.Any])
    __obj.asInstanceOf[MSInputMethodContextEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MSInputMethodContextEventMap] (val x: Self) extends AnyVal {
    
    inline def setMSCandidateWindowHide(value: org.scalajs.dom.Event): Self = StObject.set(x, "MSCandidateWindowHide", value.asInstanceOf[js.Any])
    
    inline def setMSCandidateWindowShow(value: org.scalajs.dom.Event): Self = StObject.set(x, "MSCandidateWindowShow", value.asInstanceOf[js.Any])
    
    inline def setMSCandidateWindowUpdate(value: org.scalajs.dom.Event): Self = StObject.set(x, "MSCandidateWindowUpdate", value.asInstanceOf[js.Any])
  }
}

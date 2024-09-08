package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** The msGraphicsTrust() constructor returns an object that provides properties for info on protected video playback. */
trait MSGraphicsTrust extends StObject {
  
  /* standard dom */
  val constrictionActive: scala.Boolean
  
  /* standard dom */
  val status: java.lang.String
}
object MSGraphicsTrust {
  
  inline def apply(constrictionActive: scala.Boolean, status: java.lang.String): MSGraphicsTrust = {
    val __obj = js.Dynamic.literal(constrictionActive = constrictionActive.asInstanceOf[js.Any], status = status.asInstanceOf[js.Any])
    __obj.asInstanceOf[MSGraphicsTrust]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MSGraphicsTrust] (val x: Self) extends AnyVal {
    
    inline def setConstrictionActive(value: scala.Boolean): Self = StObject.set(x, "constrictionActive", value.asInstanceOf[js.Any])
    
    inline def setStatus(value: java.lang.String): Self = StObject.set(x, "status", value.asInstanceOf[js.Any])
  }
}

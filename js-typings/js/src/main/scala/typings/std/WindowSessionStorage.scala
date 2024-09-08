package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait WindowSessionStorage extends StObject {
  
  /* standard dom */
  val sessionStorage: org.scalajs.dom.Storage
}
object WindowSessionStorage {
  
  inline def apply(sessionStorage: org.scalajs.dom.Storage): WindowSessionStorage = {
    val __obj = js.Dynamic.literal(sessionStorage = sessionStorage.asInstanceOf[js.Any])
    __obj.asInstanceOf[WindowSessionStorage]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: WindowSessionStorage] (val x: Self) extends AnyVal {
    
    inline def setSessionStorage(value: org.scalajs.dom.Storage): Self = StObject.set(x, "sessionStorage", value.asInstanceOf[js.Any])
  }
}

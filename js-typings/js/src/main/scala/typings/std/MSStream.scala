package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MSStream extends StObject {
  
  /* standard dom */
  def msClose(): Unit
  
  /* standard dom */
  def msDetachStream(): Any
  
  /* standard dom */
  val `type`: java.lang.String
}
object MSStream {
  
  inline def apply(msClose: () => Unit, msDetachStream: () => Any, `type`: java.lang.String): MSStream = {
    val __obj = js.Dynamic.literal(msClose = js.Any.fromFunction0(msClose), msDetachStream = js.Any.fromFunction0(msDetachStream))
    __obj.updateDynamic("type")(`type`.asInstanceOf[js.Any])
    __obj.asInstanceOf[MSStream]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MSStream] (val x: Self) extends AnyVal {
    
    inline def setMsClose(value: () => Unit): Self = StObject.set(x, "msClose", js.Any.fromFunction0(value))
    
    inline def setMsDetachStream(value: () => Any): Self = StObject.set(x, "msDetachStream", js.Any.fromFunction0(value))
    
    inline def setType(value: java.lang.String): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

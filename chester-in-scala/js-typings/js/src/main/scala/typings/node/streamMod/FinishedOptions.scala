package typings.node.streamMod

import typings.node.eventsMod.Abortable
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait FinishedOptions
  extends StObject
     with Abortable {
  
  var error: js.UndefOr[Boolean] = js.undefined
  
  var readable: js.UndefOr[Boolean] = js.undefined
  
  var writable: js.UndefOr[Boolean] = js.undefined
}
object FinishedOptions {
  
  inline def apply(): FinishedOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[FinishedOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: FinishedOptions] (val x: Self) extends AnyVal {
    
    inline def setError(value: Boolean): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setErrorUndefined: Self = StObject.set(x, "error", js.undefined)
    
    inline def setReadable(value: Boolean): Self = StObject.set(x, "readable", value.asInstanceOf[js.Any])
    
    inline def setReadableUndefined: Self = StObject.set(x, "readable", js.undefined)
    
    inline def setWritable(value: Boolean): Self = StObject.set(x, "writable", value.asInstanceOf[js.Any])
    
    inline def setWritableUndefined: Self = StObject.set(x, "writable", js.undefined)
  }
}

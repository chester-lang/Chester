package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait InputEventInit
  extends StObject
     with UIEventInit {
  
  /* standard dom */
  var data: js.UndefOr[java.lang.String | Null] = js.undefined
  
  /* standard dom */
  var inputType: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var isComposing: js.UndefOr[scala.Boolean] = js.undefined
}
object InputEventInit {
  
  inline def apply(): InputEventInit = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[InputEventInit]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: InputEventInit] (val x: Self) extends AnyVal {
    
    inline def setData(value: java.lang.String): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setDataNull: Self = StObject.set(x, "data", null)
    
    inline def setDataUndefined: Self = StObject.set(x, "data", js.undefined)
    
    inline def setInputType(value: java.lang.String): Self = StObject.set(x, "inputType", value.asInstanceOf[js.Any])
    
    inline def setInputTypeUndefined: Self = StObject.set(x, "inputType", js.undefined)
    
    inline def setIsComposing(value: scala.Boolean): Self = StObject.set(x, "isComposing", value.asInstanceOf[js.Any])
    
    inline def setIsComposingUndefined: Self = StObject.set(x, "isComposing", js.undefined)
  }
}

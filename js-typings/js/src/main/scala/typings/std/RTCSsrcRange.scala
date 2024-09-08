package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCSsrcRange extends StObject {
  
  /* standard dom */
  var max: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var min: js.UndefOr[Double] = js.undefined
}
object RTCSsrcRange {
  
  inline def apply(): RTCSsrcRange = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCSsrcRange]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCSsrcRange] (val x: Self) extends AnyVal {
    
    inline def setMax(value: Double): Self = StObject.set(x, "max", value.asInstanceOf[js.Any])
    
    inline def setMaxUndefined: Self = StObject.set(x, "max", js.undefined)
    
    inline def setMin(value: Double): Self = StObject.set(x, "min", value.asInstanceOf[js.Any])
    
    inline def setMinUndefined: Self = StObject.set(x, "min", js.undefined)
  }
}

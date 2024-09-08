package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RTCSrtpKeyParam extends StObject {
  
  /* standard dom */
  var keyMethod: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var keySalt: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var lifetime: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var mkiLength: js.UndefOr[Double] = js.undefined
  
  /* standard dom */
  var mkiValue: js.UndefOr[Double] = js.undefined
}
object RTCSrtpKeyParam {
  
  inline def apply(): RTCSrtpKeyParam = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RTCSrtpKeyParam]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RTCSrtpKeyParam] (val x: Self) extends AnyVal {
    
    inline def setKeyMethod(value: java.lang.String): Self = StObject.set(x, "keyMethod", value.asInstanceOf[js.Any])
    
    inline def setKeyMethodUndefined: Self = StObject.set(x, "keyMethod", js.undefined)
    
    inline def setKeySalt(value: java.lang.String): Self = StObject.set(x, "keySalt", value.asInstanceOf[js.Any])
    
    inline def setKeySaltUndefined: Self = StObject.set(x, "keySalt", js.undefined)
    
    inline def setLifetime(value: java.lang.String): Self = StObject.set(x, "lifetime", value.asInstanceOf[js.Any])
    
    inline def setLifetimeUndefined: Self = StObject.set(x, "lifetime", js.undefined)
    
    inline def setMkiLength(value: Double): Self = StObject.set(x, "mkiLength", value.asInstanceOf[js.Any])
    
    inline def setMkiLengthUndefined: Self = StObject.set(x, "mkiLength", js.undefined)
    
    inline def setMkiValue(value: Double): Self = StObject.set(x, "mkiValue", value.asInstanceOf[js.Any])
    
    inline def setMkiValueUndefined: Self = StObject.set(x, "mkiValue", js.undefined)
  }
}

package typings.node.cryptoMod

import typings.node.nodeStrings.explicit
import typings.node.nodeStrings.named
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ECKeyPairKeyObjectOptions extends StObject {
  
  /**
    * Name of the curve to use
    */
  var namedCurve: String
  
  /**
    * Must be `'named'` or `'explicit'`. Default: `'named'`.
    */
  var paramEncoding: js.UndefOr[explicit | named] = js.undefined
}
object ECKeyPairKeyObjectOptions {
  
  inline def apply(namedCurve: String): ECKeyPairKeyObjectOptions = {
    val __obj = js.Dynamic.literal(namedCurve = namedCurve.asInstanceOf[js.Any])
    __obj.asInstanceOf[ECKeyPairKeyObjectOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ECKeyPairKeyObjectOptions] (val x: Self) extends AnyVal {
    
    inline def setNamedCurve(value: String): Self = StObject.set(x, "namedCurve", value.asInstanceOf[js.Any])
    
    inline def setParamEncoding(value: explicit | named): Self = StObject.set(x, "paramEncoding", value.asInstanceOf[js.Any])
    
    inline def setParamEncodingUndefined: Self = StObject.set(x, "paramEncoding", js.undefined)
  }
}

package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MockMethodOptions
  extends StObject
     with MockFunctionOptions {
  
  /**
    * If `true`, `object[methodName]` is treated as a getter.
    * This option cannot be used with the `setter` option.
    */
  var getter: js.UndefOr[Boolean] = js.undefined
  
  /**
    * If `true`, `object[methodName]` is treated as a setter.
    * This option cannot be used with the `getter` option.
    */
  var setter: js.UndefOr[Boolean] = js.undefined
}
object MockMethodOptions {
  
  inline def apply(): MockMethodOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[MockMethodOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MockMethodOptions] (val x: Self) extends AnyVal {
    
    inline def setGetter(value: Boolean): Self = StObject.set(x, "getter", value.asInstanceOf[js.Any])
    
    inline def setGetterUndefined: Self = StObject.set(x, "getter", js.undefined)
    
    inline def setSetter(value: Boolean): Self = StObject.set(x, "setter", value.asInstanceOf[js.Any])
    
    inline def setSetterUndefined: Self = StObject.set(x, "setter", js.undefined)
  }
}

package typings.node.utilMod

import typings.node.nodeStrings.boolean
import typings.node.nodeStrings.string
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ParseArgsOptionConfig extends StObject {
  
  /**
    * The default option value when it is not set by args.
    * It must be of the same type as the the `type` property.
    * When `multiple` is `true`, it must be an array.
    * @since v18.11.0
    */
  var default: js.UndefOr[String | Boolean | (js.Array[Boolean | String])] = js.undefined
  
  /**
    * Whether this option can be provided multiple times.
    * If `true`, all values will be collected in an array.
    * If `false`, values for the option are last-wins.
    * @default false.
    */
  var multiple: js.UndefOr[Boolean] = js.undefined
  
  /**
    * A single character alias for the option.
    */
  var short: js.UndefOr[String] = js.undefined
  
  /**
    * Type of argument.
    */
  var `type`: string | boolean
}
object ParseArgsOptionConfig {
  
  inline def apply(`type`: string | boolean): ParseArgsOptionConfig = {
    val __obj = js.Dynamic.literal()
    __obj.updateDynamic("type")(`type`.asInstanceOf[js.Any])
    __obj.asInstanceOf[ParseArgsOptionConfig]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ParseArgsOptionConfig] (val x: Self) extends AnyVal {
    
    inline def setDefault(value: String | Boolean | (js.Array[Boolean | String])): Self = StObject.set(x, "default", value.asInstanceOf[js.Any])
    
    inline def setDefaultUndefined: Self = StObject.set(x, "default", js.undefined)
    
    inline def setDefaultVarargs(value: (Boolean | String)*): Self = StObject.set(x, "default", js.Array(value*))
    
    inline def setMultiple(value: Boolean): Self = StObject.set(x, "multiple", value.asInstanceOf[js.Any])
    
    inline def setMultipleUndefined: Self = StObject.set(x, "multiple", js.undefined)
    
    inline def setShort(value: String): Self = StObject.set(x, "short", value.asInstanceOf[js.Any])
    
    inline def setShortUndefined: Self = StObject.set(x, "short", js.undefined)
    
    inline def setType(value: string | boolean): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

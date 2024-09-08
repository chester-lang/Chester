package typings.node.utilMod

import typings.node.nodeStrings.get
import typings.node.nodeStrings.set
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait InspectOptions extends StObject {
  
  /**
    * The length at which input values are split across multiple lines.
    * Set to `Infinity` to format the input as a single line
    * (in combination with `compact` set to `true` or any number >= `1`).
    * @default 80
    */
  var breakLength: js.UndefOr[Double] = js.undefined
  
  /**
    * If `true`, the output is styled with ANSI color codes. Colors are customizable.
    */
  var colors: js.UndefOr[Boolean] = js.undefined
  
  /**
    * Setting this to `false` causes each object key
    * to be displayed on a new line. It will also add new lines to text that is
    * longer than `breakLength`. If set to a number, the most `n` inner elements
    * are united on a single line as long as all properties fit into
    * `breakLength`. Short array elements are also grouped together. Note that no
    * text will be reduced below 16 characters, no matter the `breakLength` size.
    * For more information, see the example below.
    * @default true
    */
  var compact: js.UndefOr[Boolean | Double] = js.undefined
  
  /**
    * If `false`, `[util.inspect.custom](depth, opts, inspect)` functions are not invoked.
    * @default true
    */
  var customInspect: js.UndefOr[Boolean] = js.undefined
  
  /**
    * Specifies the number of times to recurse while formatting object.
    * This is useful for inspecting large objects.
    * To recurse up to the maximum call stack size pass `Infinity` or `null`.
    * @default 2
    */
  var depth: js.UndefOr[Double | Null] = js.undefined
  
  /**
    * If set to `true`, getters are going to be
    * inspected as well. If set to `'get'` only getters without setter are going
    * to be inspected. If set to `'set'` only getters having a corresponding
    * setter are going to be inspected. This might cause side effects depending on
    * the getter function.
    * @default false
    */
  var getters: js.UndefOr[get | set | Boolean] = js.undefined
  
  /**
    * Specifies the maximum number of `Array`, `TypedArray`, `WeakMap`, and `WeakSet` elements
    * to include when formatting. Set to `null` or `Infinity` to show all elements.
    * Set to `0` or negative to show no elements.
    * @default 100
    */
  var maxArrayLength: js.UndefOr[Double | Null] = js.undefined
  
  /**
    * Specifies the maximum number of characters to
    * include when formatting. Set to `null` or `Infinity` to show all elements.
    * Set to `0` or negative to show no characters.
    * @default 10000
    */
  var maxStringLength: js.UndefOr[Double | Null] = js.undefined
  
  /**
    * If set to `true`, an underscore is used to separate every three digits in all bigints and numbers.
    * @default false
    */
  var numericSeparator: js.UndefOr[Boolean] = js.undefined
  
  /**
    * If `true`, object's non-enumerable symbols and properties are included in the formatted result.
    * `WeakMap` and `WeakSet` entries are also included as well as user defined prototype properties (excluding method properties).
    * @default false
    */
  var showHidden: js.UndefOr[Boolean] = js.undefined
  
  /**
    * If `true`, `Proxy` inspection includes the target and handler objects.
    * @default false
    */
  var showProxy: js.UndefOr[Boolean] = js.undefined
  
  /**
    * If set to `true` or a function, all properties of an object, and `Set` and `Map`
    * entries are sorted in the resulting string.
    * If set to `true` the default sort is used.
    * If set to a function, it is used as a compare function.
    */
  var sorted: js.UndefOr[Boolean | (js.Function2[/* a */ String, /* b */ String, Double])] = js.undefined
}
object InspectOptions {
  
  inline def apply(): InspectOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[InspectOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: InspectOptions] (val x: Self) extends AnyVal {
    
    inline def setBreakLength(value: Double): Self = StObject.set(x, "breakLength", value.asInstanceOf[js.Any])
    
    inline def setBreakLengthUndefined: Self = StObject.set(x, "breakLength", js.undefined)
    
    inline def setColors(value: Boolean): Self = StObject.set(x, "colors", value.asInstanceOf[js.Any])
    
    inline def setColorsUndefined: Self = StObject.set(x, "colors", js.undefined)
    
    inline def setCompact(value: Boolean | Double): Self = StObject.set(x, "compact", value.asInstanceOf[js.Any])
    
    inline def setCompactUndefined: Self = StObject.set(x, "compact", js.undefined)
    
    inline def setCustomInspect(value: Boolean): Self = StObject.set(x, "customInspect", value.asInstanceOf[js.Any])
    
    inline def setCustomInspectUndefined: Self = StObject.set(x, "customInspect", js.undefined)
    
    inline def setDepth(value: Double): Self = StObject.set(x, "depth", value.asInstanceOf[js.Any])
    
    inline def setDepthNull: Self = StObject.set(x, "depth", null)
    
    inline def setDepthUndefined: Self = StObject.set(x, "depth", js.undefined)
    
    inline def setGetters(value: get | set | Boolean): Self = StObject.set(x, "getters", value.asInstanceOf[js.Any])
    
    inline def setGettersUndefined: Self = StObject.set(x, "getters", js.undefined)
    
    inline def setMaxArrayLength(value: Double): Self = StObject.set(x, "maxArrayLength", value.asInstanceOf[js.Any])
    
    inline def setMaxArrayLengthNull: Self = StObject.set(x, "maxArrayLength", null)
    
    inline def setMaxArrayLengthUndefined: Self = StObject.set(x, "maxArrayLength", js.undefined)
    
    inline def setMaxStringLength(value: Double): Self = StObject.set(x, "maxStringLength", value.asInstanceOf[js.Any])
    
    inline def setMaxStringLengthNull: Self = StObject.set(x, "maxStringLength", null)
    
    inline def setMaxStringLengthUndefined: Self = StObject.set(x, "maxStringLength", js.undefined)
    
    inline def setNumericSeparator(value: Boolean): Self = StObject.set(x, "numericSeparator", value.asInstanceOf[js.Any])
    
    inline def setNumericSeparatorUndefined: Self = StObject.set(x, "numericSeparator", js.undefined)
    
    inline def setShowHidden(value: Boolean): Self = StObject.set(x, "showHidden", value.asInstanceOf[js.Any])
    
    inline def setShowHiddenUndefined: Self = StObject.set(x, "showHidden", js.undefined)
    
    inline def setShowProxy(value: Boolean): Self = StObject.set(x, "showProxy", value.asInstanceOf[js.Any])
    
    inline def setShowProxyUndefined: Self = StObject.set(x, "showProxy", js.undefined)
    
    inline def setSorted(value: Boolean | (js.Function2[/* a */ String, /* b */ String, Double])): Self = StObject.set(x, "sorted", value.asInstanceOf[js.Any])
    
    inline def setSortedFunction2(value: (/* a */ String, /* b */ String) => Double): Self = StObject.set(x, "sorted", js.Any.fromFunction2(value))
    
    inline def setSortedUndefined: Self = StObject.set(x, "sorted", js.undefined)
  }
}

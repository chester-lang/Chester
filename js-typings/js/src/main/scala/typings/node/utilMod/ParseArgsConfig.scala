package typings.node.utilMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ParseArgsConfig extends StObject {
  
  /**
    * Whether this command accepts positional arguments.
    */
  var allowPositionals: js.UndefOr[Boolean] = js.undefined
  
  /**
    * Array of argument strings.
    */
  var args: js.UndefOr[js.Array[String]] = js.undefined
  
  /**
    * Used to describe arguments known to the parser.
    */
  var options: js.UndefOr[ParseArgsOptionsConfig] = js.undefined
  
  /**
    * Should an error be thrown when unknown arguments are encountered,
    * or when arguments are passed that do not match the `type` configured in `options`.
    * @default true
    */
  var strict: js.UndefOr[Boolean] = js.undefined
  
  /**
    * Return the parsed tokens. This is useful for extending the built-in behavior,
    * from adding additional checks through to reprocessing the tokens in different ways.
    * @default false
    */
  var tokens: js.UndefOr[Boolean] = js.undefined
}
object ParseArgsConfig {
  
  inline def apply(): ParseArgsConfig = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ParseArgsConfig]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ParseArgsConfig] (val x: Self) extends AnyVal {
    
    inline def setAllowPositionals(value: Boolean): Self = StObject.set(x, "allowPositionals", value.asInstanceOf[js.Any])
    
    inline def setAllowPositionalsUndefined: Self = StObject.set(x, "allowPositionals", js.undefined)
    
    inline def setArgs(value: js.Array[String]): Self = StObject.set(x, "args", value.asInstanceOf[js.Any])
    
    inline def setArgsUndefined: Self = StObject.set(x, "args", js.undefined)
    
    inline def setArgsVarargs(value: String*): Self = StObject.set(x, "args", js.Array(value*))
    
    inline def setOptions(value: ParseArgsOptionsConfig): Self = StObject.set(x, "options", value.asInstanceOf[js.Any])
    
    inline def setOptionsUndefined: Self = StObject.set(x, "options", js.undefined)
    
    inline def setStrict(value: Boolean): Self = StObject.set(x, "strict", value.asInstanceOf[js.Any])
    
    inline def setStrictUndefined: Self = StObject.set(x, "strict", js.undefined)
    
    inline def setTokens(value: Boolean): Self = StObject.set(x, "tokens", value.asInstanceOf[js.Any])
    
    inline def setTokensUndefined: Self = StObject.set(x, "tokens", js.undefined)
  }
}

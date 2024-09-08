package typings.node.utilMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Rewritten from type alias, can be one of: 
  - typings.node.utilMod.OptionToken
  - typings.node.anon.Index
  - typings.node.anon.Kind
*/
trait Token extends StObject
object Token {
  
  inline def Index(index: Double, value: String): typings.node.anon.Index = {
    val __obj = js.Dynamic.literal(index = index.asInstanceOf[js.Any], kind = "positional", value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[typings.node.anon.Index]
  }
  
  inline def InlineValue(index: Double, inlineValue: Boolean, name: String, rawName: String, value: String): typings.node.anon.InlineValue = {
    val __obj = js.Dynamic.literal(index = index.asInstanceOf[js.Any], inlineValue = inlineValue.asInstanceOf[js.Any], kind = "option", name = name.asInstanceOf[js.Any], rawName = rawName.asInstanceOf[js.Any], value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[typings.node.anon.InlineValue]
  }
  
  inline def Kind(index: Double): typings.node.anon.Kind = {
    val __obj = js.Dynamic.literal(index = index.asInstanceOf[js.Any], kind = "option-terminator")
    __obj.asInstanceOf[typings.node.anon.Kind]
  }
  
  inline def Name(index: Double, inlineValue: Unit, name: String, rawName: String, value: Unit): typings.node.anon.Name = {
    val __obj = js.Dynamic.literal(index = index.asInstanceOf[js.Any], inlineValue = inlineValue.asInstanceOf[js.Any], kind = "option", name = name.asInstanceOf[js.Any], rawName = rawName.asInstanceOf[js.Any], value = value.asInstanceOf[js.Any])
    __obj.asInstanceOf[typings.node.anon.Name]
  }
}

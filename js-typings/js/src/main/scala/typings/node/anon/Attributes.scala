package typings.node.anon

import typings.node.moduleMod.ImportAttributes
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Attributes extends StObject {
  
  var attributes: ImportAttributes
}
object Attributes {
  
  inline def apply(attributes: ImportAttributes): Attributes = {
    val __obj = js.Dynamic.literal(attributes = attributes.asInstanceOf[js.Any])
    __obj.asInstanceOf[Attributes]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Attributes] (val x: Self) extends AnyVal {
    
    inline def setAttributes(value: ImportAttributes): Self = StObject.set(x, "attributes", value.asInstanceOf[js.Any])
  }
}

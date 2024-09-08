package typings.node.utilMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait InspectOptionsStylized
  extends StObject
     with InspectOptions {
  
  def stylize(text: String, styleType: Style): String
}
object InspectOptionsStylized {
  
  inline def apply(stylize: (String, Style) => String): InspectOptionsStylized = {
    val __obj = js.Dynamic.literal(stylize = js.Any.fromFunction2(stylize))
    __obj.asInstanceOf[InspectOptionsStylized]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: InspectOptionsStylized] (val x: Self) extends AnyVal {
    
    inline def setStylize(value: (String, Style) => String): Self = StObject.set(x, "stylize", js.Any.fromFunction2(value))
  }
}

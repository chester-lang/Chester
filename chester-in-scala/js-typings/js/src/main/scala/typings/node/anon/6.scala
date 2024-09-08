package typings.node.anon

import typings.node.nodeStrings.summary
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait `6` extends StObject {
  
  var format: summary
}
object `6` {
  
  inline def apply(): `6` = {
    val __obj = js.Dynamic.literal(format = "summary")
    __obj.asInstanceOf[`6`]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: `6`] (val x: Self) extends AnyVal {
    
    inline def setFormat(value: summary): Self = StObject.set(x, "format", value.asInstanceOf[js.Any])
  }
}

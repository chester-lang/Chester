package typings.node.anon

import typings.node.nodeStrings.count
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait `5` extends StObject {
  
  var format: count
}
object `5` {
  
  inline def apply(): `5` = {
    val __obj = js.Dynamic.literal(format = "count")
    __obj.asInstanceOf[`5`]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: `5`] (val x: Self) extends AnyVal {
    
    inline def setFormat(value: count): Self = StObject.set(x, "format", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Count extends StObject {
  
  /**
    * The number of times the function was called.
    */
  var count: Double
  
  /**
    * The line number where the function is defined.
    */
  var line: Double
  
  /**
    * The name of the function.
    */
  var name: String
}
object Count {
  
  inline def apply(count: Double, line: Double, name: String): Count = {
    val __obj = js.Dynamic.literal(count = count.asInstanceOf[js.Any], line = line.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any])
    __obj.asInstanceOf[Count]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Count] (val x: Self) extends AnyVal {
    
    inline def setCount(value: Double): Self = StObject.set(x, "count", value.asInstanceOf[js.Any])
    
    inline def setLine(value: Double): Self = StObject.set(x, "line", value.asInstanceOf[js.Any])
    
    inline def setName(value: String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}

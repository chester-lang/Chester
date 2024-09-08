package typings.node.anon

import typings.node.nodeStrings.suite
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DurationmsType extends StObject {
  
  /**
    * The duration of the test in milliseconds.
    */
  var duration_ms: Double
  
  /**
    * The type of the test, used to denote whether this is a suite.
    * @since 20.0.0, 19.9.0, 18.17.0
    */
  var `type`: js.UndefOr[suite] = js.undefined
}
object DurationmsType {
  
  inline def apply(duration_ms: Double): DurationmsType = {
    val __obj = js.Dynamic.literal(duration_ms = duration_ms.asInstanceOf[js.Any])
    __obj.asInstanceOf[DurationmsType]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DurationmsType] (val x: Self) extends AnyVal {
    
    inline def setDuration_ms(value: Double): Self = StObject.set(x, "duration_ms", value.asInstanceOf[js.Any])
    
    inline def setType(value: suite): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
    
    inline def setTypeUndefined: Self = StObject.set(x, "type", js.undefined)
  }
}

package typings.node.anon

import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonwatchColondrained
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataUndefined
  extends StObject
     with TestEvent {
  
  var data: Unit
  
  var `type`: testColonwatchColondrained
}
object DataUndefined {
  
  inline def apply(data: Unit): DataUndefined = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:watch:drained")
    __obj.asInstanceOf[DataUndefined]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataUndefined] (val x: Self) extends AnyVal {
    
    inline def setData(value: Unit): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonwatchColondrained): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

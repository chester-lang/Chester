package typings.node.anon

import typings.node.TestDequeue
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColondequeue
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestDequeue
  extends StObject
     with TestEvent {
  
  var data: TestDequeue
  
  var `type`: testColondequeue
}
object DataTestDequeue {
  
  inline def apply(data: TestDequeue): DataTestDequeue = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:dequeue")
    __obj.asInstanceOf[DataTestDequeue]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestDequeue] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestDequeue): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColondequeue): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

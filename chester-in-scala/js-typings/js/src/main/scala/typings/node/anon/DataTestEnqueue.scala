package typings.node.anon

import typings.node.TestEnqueue
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonenqueue
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestEnqueue
  extends StObject
     with TestEvent {
  
  var data: TestEnqueue
  
  var `type`: testColonenqueue
}
object DataTestEnqueue {
  
  inline def apply(data: TestEnqueue): DataTestEnqueue = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:enqueue")
    __obj.asInstanceOf[DataTestEnqueue]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestEnqueue] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestEnqueue): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonenqueue): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

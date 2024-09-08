package typings.node.anon

import typings.node.TestCoverage
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColoncoverage
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataType
  extends StObject
     with TestEvent {
  
  var data: TestCoverage
  
  var `type`: testColoncoverage
}
object DataType {
  
  inline def apply(data: TestCoverage): DataType = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:coverage")
    __obj.asInstanceOf[DataType]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataType] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestCoverage): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColoncoverage): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

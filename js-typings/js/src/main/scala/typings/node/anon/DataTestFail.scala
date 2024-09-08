package typings.node.anon

import typings.node.TestFail
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonfail
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestFail
  extends StObject
     with TestEvent {
  
  var data: TestFail
  
  var `type`: testColonfail
}
object DataTestFail {
  
  inline def apply(data: TestFail): DataTestFail = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:fail")
    __obj.asInstanceOf[DataTestFail]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestFail] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestFail): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonfail): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import typings.node.TestPlan
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonplan
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestPlan
  extends StObject
     with TestEvent {
  
  var data: TestPlan
  
  var `type`: testColonplan
}
object DataTestPlan {
  
  inline def apply(data: TestPlan): DataTestPlan = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:plan")
    __obj.asInstanceOf[DataTestPlan]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestPlan] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestPlan): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonplan): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

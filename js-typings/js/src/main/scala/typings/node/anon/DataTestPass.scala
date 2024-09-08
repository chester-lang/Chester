package typings.node.anon

import typings.node.TestPass
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonpass
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestPass
  extends StObject
     with TestEvent {
  
  var data: TestPass
  
  var `type`: testColonpass
}
object DataTestPass {
  
  inline def apply(data: TestPass): DataTestPass = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:pass")
    __obj.asInstanceOf[DataTestPass]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestPass] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestPass): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonpass): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import typings.node.TestStart
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonstart
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestStart
  extends StObject
     with TestEvent {
  
  var data: TestStart
  
  var `type`: testColonstart
}
object DataTestStart {
  
  inline def apply(data: TestStart): DataTestStart = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:start")
    __obj.asInstanceOf[DataTestStart]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestStart] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestStart): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonstart): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

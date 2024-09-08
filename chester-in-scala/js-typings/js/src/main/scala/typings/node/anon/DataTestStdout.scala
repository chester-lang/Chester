package typings.node.anon

import typings.node.TestStdout
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonstdout
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestStdout
  extends StObject
     with TestEvent {
  
  var data: TestStdout
  
  var `type`: testColonstdout
}
object DataTestStdout {
  
  inline def apply(data: TestStdout): DataTestStdout = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:stdout")
    __obj.asInstanceOf[DataTestStdout]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestStdout] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestStdout): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonstdout): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

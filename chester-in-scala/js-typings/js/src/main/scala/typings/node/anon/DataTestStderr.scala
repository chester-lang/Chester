package typings.node.anon

import typings.node.TestStderr
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColonstderr
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestStderr
  extends StObject
     with TestEvent {
  
  var data: TestStderr
  
  var `type`: testColonstderr
}
object DataTestStderr {
  
  inline def apply(data: TestStderr): DataTestStderr = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:stderr")
    __obj.asInstanceOf[DataTestStderr]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestStderr] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestStderr): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColonstderr): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import typings.node.TestComplete
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColoncomplete
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataTestComplete
  extends StObject
     with TestEvent {
  
  var data: TestComplete
  
  var `type`: testColoncomplete
}
object DataTestComplete {
  
  inline def apply(data: TestComplete): DataTestComplete = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:complete")
    __obj.asInstanceOf[DataTestComplete]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataTestComplete] (val x: Self) extends AnyVal {
    
    inline def setData(value: TestComplete): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColoncomplete): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

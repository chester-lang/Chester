package typings.node.anon

import typings.node.DiagnosticData
import typings.node.nodeColontestReportersMod.TestEvent
import typings.node.nodeStrings.testColondiagnostic
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DataDiagnosticData
  extends StObject
     with TestEvent {
  
  var data: DiagnosticData
  
  var `type`: testColondiagnostic
}
object DataDiagnosticData {
  
  inline def apply(data: DiagnosticData): DataDiagnosticData = {
    val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
    __obj.updateDynamic("type")("test:diagnostic")
    __obj.asInstanceOf[DataDiagnosticData]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DataDiagnosticData] (val x: Self) extends AnyVal {
    
    inline def setData(value: DiagnosticData): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
    
    inline def setType(value: testColondiagnostic): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

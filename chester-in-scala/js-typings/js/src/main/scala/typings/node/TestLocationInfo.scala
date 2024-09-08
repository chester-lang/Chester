package typings.node

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestLocationInfo extends StObject {
  
  /**
    * The column number where the test is defined, or
    * `undefined` if the test was run through the REPL.
    */
  var column: js.UndefOr[Double] = js.undefined
  
  /**
    * The path of the test file, `undefined` if test was run through the REPL.
    */
  var file: js.UndefOr[String] = js.undefined
  
  /**
    * The line number where the test is defined, or `undefined` if the test was run through the REPL.
    */
  var line: js.UndefOr[Double] = js.undefined
}
object TestLocationInfo {
  
  inline def apply(): TestLocationInfo = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[TestLocationInfo]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestLocationInfo] (val x: Self) extends AnyVal {
    
    inline def setColumn(value: Double): Self = StObject.set(x, "column", value.asInstanceOf[js.Any])
    
    inline def setColumnUndefined: Self = StObject.set(x, "column", js.undefined)
    
    inline def setFile(value: String): Self = StObject.set(x, "file", value.asInstanceOf[js.Any])
    
    inline def setFileUndefined: Self = StObject.set(x, "file", js.undefined)
    
    inline def setLine(value: Double): Self = StObject.set(x, "line", value.asInstanceOf[js.Any])
    
    inline def setLineUndefined: Self = StObject.set(x, "line", js.undefined)
  }
}

package typings.node

import typings.node.anon.Files
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestCoverage extends StObject {
  
  /**
    * The nesting level of the test.
    */
  var nesting: Double
  
  /**
    * An object containing the coverage report.
    */
  var summary: Files
}
object TestCoverage {
  
  inline def apply(nesting: Double, summary: Files): TestCoverage = {
    val __obj = js.Dynamic.literal(nesting = nesting.asInstanceOf[js.Any], summary = summary.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestCoverage]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestCoverage] (val x: Self) extends AnyVal {
    
    inline def setNesting(value: Double): Self = StObject.set(x, "nesting", value.asInstanceOf[js.Any])
    
    inline def setSummary(value: Files): Self = StObject.set(x, "summary", value.asInstanceOf[js.Any])
  }
}

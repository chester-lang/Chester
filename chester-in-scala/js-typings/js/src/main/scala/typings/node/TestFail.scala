package typings.node

import typings.node.anon.DurationmsError
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestFail
  extends StObject
     with TestLocationInfo {
  
  /**
    * Additional execution metadata.
    */
  var details: DurationmsError
  
  /**
    * The test name.
    */
  var name: String
  
  /**
    * The nesting level of the test.
    */
  var nesting: Double
  
  /**
    * Present if `context.skip` is called.
    */
  var skip: js.UndefOr[String | Boolean] = js.undefined
  
  /**
    * The ordinal number of the test.
    */
  var testNumber: Double
  
  /**
    * Present if `context.todo` is called.
    */
  var todo: js.UndefOr[String | Boolean] = js.undefined
}
object TestFail {
  
  inline def apply(details: DurationmsError, name: String, nesting: Double, testNumber: Double): TestFail = {
    val __obj = js.Dynamic.literal(details = details.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any], nesting = nesting.asInstanceOf[js.Any], testNumber = testNumber.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestFail]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestFail] (val x: Self) extends AnyVal {
    
    inline def setDetails(value: DurationmsError): Self = StObject.set(x, "details", value.asInstanceOf[js.Any])
    
    inline def setName(value: String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
    
    inline def setNesting(value: Double): Self = StObject.set(x, "nesting", value.asInstanceOf[js.Any])
    
    inline def setSkip(value: String | Boolean): Self = StObject.set(x, "skip", value.asInstanceOf[js.Any])
    
    inline def setSkipUndefined: Self = StObject.set(x, "skip", js.undefined)
    
    inline def setTestNumber(value: Double): Self = StObject.set(x, "testNumber", value.asInstanceOf[js.Any])
    
    inline def setTodo(value: String | Boolean): Self = StObject.set(x, "todo", value.asInstanceOf[js.Any])
    
    inline def setTodoUndefined: Self = StObject.set(x, "todo", js.undefined)
  }
}

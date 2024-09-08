package typings.node.anon

import typings.node.TestError
import typings.node.nodeStrings.suite
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Durationms extends StObject {
  
  /**
    * The duration of the test in milliseconds.
    */
  var duration_ms: Double
  
  /**
    * An error wrapping the error thrown by the test if it did not pass.
    */
  var error: js.UndefOr[TestError] = js.undefined
  
  /**
    * Whether the test passed or not.
    */
  var passed: Boolean
  
  /**
    * The type of the test, used to denote whether this is a suite.
    */
  var `type`: js.UndefOr[suite] = js.undefined
}
object Durationms {
  
  inline def apply(duration_ms: Double, passed: Boolean): Durationms = {
    val __obj = js.Dynamic.literal(duration_ms = duration_ms.asInstanceOf[js.Any], passed = passed.asInstanceOf[js.Any])
    __obj.asInstanceOf[Durationms]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Durationms] (val x: Self) extends AnyVal {
    
    inline def setDuration_ms(value: Double): Self = StObject.set(x, "duration_ms", value.asInstanceOf[js.Any])
    
    inline def setError(value: TestError): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setErrorUndefined: Self = StObject.set(x, "error", js.undefined)
    
    inline def setPassed(value: Boolean): Self = StObject.set(x, "passed", value.asInstanceOf[js.Any])
    
    inline def setType(value: suite): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
    
    inline def setTypeUndefined: Self = StObject.set(x, "type", js.undefined)
  }
}

package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait AssertSnapshotOptions extends StObject {
  
  /**
    * An array of synchronous functions used to serialize `value` into a string.
    * `value` is passed as the only argument to the first serializer function.
    * The return value of each serializer is passed as input to the next serializer.
    * Once all serializers have run, the resulting value is coerced to a string.
    *
    * If no serializers are provided, the test runner's default serializers are used.
    */
  var serializers: js.UndefOr[js.Array[js.Function1[/* value */ Any, Any]]] = js.undefined
}
object AssertSnapshotOptions {
  
  inline def apply(): AssertSnapshotOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[AssertSnapshotOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: AssertSnapshotOptions] (val x: Self) extends AnyVal {
    
    inline def setSerializers(value: js.Array[js.Function1[/* value */ Any, Any]]): Self = StObject.set(x, "serializers", value.asInstanceOf[js.Any])
    
    inline def setSerializersUndefined: Self = StObject.set(x, "serializers", js.undefined)
    
    inline def setSerializersVarargs(value: (js.Function1[/* value */ Any, Any])*): Self = StObject.set(x, "serializers", js.Array(value*))
  }
}

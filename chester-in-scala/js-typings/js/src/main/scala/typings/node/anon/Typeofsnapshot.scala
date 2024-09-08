package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Typeofsnapshot extends StObject {
  
  /**
    * This function is used to customize the default serialization mechanism used by the test runner.
    *
    * By default, the test runner performs serialization by calling `JSON.stringify(value, null, 2)` on the provided value.
    * `JSON.stringify()` does have limitations regarding circular structures and supported data types.
    * If a more robust serialization mechanism is required, this function should be used to specify a list of custom serializers.
    *
    * Serializers are called in order, with the output of the previous serializer passed as input to the next.
    * The final result must be a string value.
    * @since v22.3.0
    * @param serializers An array of synchronous functions used as the default serializers for snapshot tests.
    */
  def setDefaultSnapshotSerializers(serializers: js.Array[js.Function1[/* value */ Any, Any]]): Unit
  
  /**
    * This function is used to set a custom resolver for the location of the snapshot file used for snapshot testing.
    * By default, the snapshot filename is the same as the entry point filename with `.snapshot` appended.
    * @since v22.3.0
    * @param fn A function which returns a string specifying the location of the snapshot file.
    * The function receives the path of the test file as its only argument.
    * If `process.argv[1]` is not associated with a file (for example in the REPL), the input is undefined.
    */
  def setResolveSnapshotPath(fn: js.Function1[/* path */ js.UndefOr[String], String]): Unit
}
object Typeofsnapshot {
  
  inline def apply(
    setDefaultSnapshotSerializers: js.Array[js.Function1[/* value */ Any, Any]] => Unit,
    setResolveSnapshotPath: js.Function1[/* path */ js.UndefOr[String], String] => Unit
  ): Typeofsnapshot = {
    val __obj = js.Dynamic.literal(setDefaultSnapshotSerializers = js.Any.fromFunction1(setDefaultSnapshotSerializers), setResolveSnapshotPath = js.Any.fromFunction1(setResolveSnapshotPath))
    __obj.asInstanceOf[Typeofsnapshot]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Typeofsnapshot] (val x: Self) extends AnyVal {
    
    inline def setSetDefaultSnapshotSerializers(value: js.Array[js.Function1[/* value */ Any, Any]] => Unit): Self = StObject.set(x, "setDefaultSnapshotSerializers", js.Any.fromFunction1(value))
    
    inline def setSetResolveSnapshotPath(value: js.Function1[/* path */ js.UndefOr[String], String] => Unit): Self = StObject.set(x, "setResolveSnapshotPath", js.Any.fromFunction1(value))
  }
}

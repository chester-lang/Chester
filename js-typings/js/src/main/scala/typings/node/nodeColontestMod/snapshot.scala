package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * Only available through the [--experimental-test-snapshots](https://nodejs.org/api/cli.html#--experimental-test-snapshots) flag.
  * @since v22.3.0
  * @experimental
  */
object snapshot {
  
  @JSImport("node:test", "snapshot")
  @js.native
  val ^ : js.Any = js.native
  
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
  inline def setDefaultSnapshotSerializers(serializers: js.Array[js.Function1[/* value */ Any, Any]]): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setDefaultSnapshotSerializers")(serializers.asInstanceOf[js.Any]).asInstanceOf[Unit]
  
  /**
    * This function is used to set a custom resolver for the location of the snapshot file used for snapshot testing.
    * By default, the snapshot filename is the same as the entry point filename with `.snapshot` appended.
    * @since v22.3.0
    * @param fn A function which returns a string specifying the location of the snapshot file.
    * The function receives the path of the test file as its only argument.
    * If `process.argv[1]` is not associated with a file (for example in the REPL), the input is undefined.
    */
  inline def setResolveSnapshotPath(fn: js.Function1[/* path */ js.UndefOr[String], String]): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setResolveSnapshotPath")(fn.asInstanceOf[js.Any]).asInstanceOf[Unit]
}

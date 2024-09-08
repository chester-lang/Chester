package typings.node.nodeColontestMod

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RunOptions extends StObject {
  
  /**
    * If a number is provided, then that many test processes would run in parallel, where each process corresponds to one test file.
    * If `true`, it would run `os.availableParallelism() - 1` test files in parallel. If `false`, it would only run one test file at a time.
    * @default false
    */
  var concurrency: js.UndefOr[Double | Boolean] = js.undefined
  
  /**
    * An array containing the list of files to run. If omitted, files are run according to the
    * [test runner execution model](https://nodejs.org/docs/latest-v22.x/api/test.html#test-runner-execution-model).
    */
  var files: js.UndefOr[js.Array[String]] = js.undefined
  
  /**
    * Configures the test runner to exit the process once all known
    * tests have finished executing even if the event loop would
    * otherwise remain active.
    * @default false
    */
  var forceExit: js.UndefOr[Boolean] = js.undefined
  
  /**
    * Sets inspector port of test child process.
    * If a nullish value is provided, each process gets its own port,
    * incremented from the primary's `process.debugPort`.
    * @default undefined
    */
  var inspectPort: js.UndefOr[Double | js.Function0[Double]] = js.undefined
  
  /**
    * If truthy, the test context will only run tests that have the `only` option set
    */
  var only: js.UndefOr[Boolean] = js.undefined
  
  /**
    * A function that accepts the `TestsStream` instance and can be used to setup listeners before any tests are run.
    * @default undefined
    */
  var setup: js.UndefOr[js.Function1[/* reporter */ TestsStream, Unit | js.Promise[Unit]]] = js.undefined
  
  /**
    * Running tests in a specific shard.
    * @default undefined
    */
  var shard: js.UndefOr[TestShard] = js.undefined
  
  /**
    * Allows aborting an in-progress test execution.
    */
  var signal: js.UndefOr[AbortSignal] = js.undefined
  
  /**
    * If provided, only run tests whose name matches the provided pattern.
    * Strings are interpreted as JavaScript regular expressions.
    * @default undefined
    */
  var testNamePatterns: js.UndefOr[String | js.RegExp | (js.Array[String | js.RegExp])] = js.undefined
  
  /**
    * A String, RegExp or a RegExp Array, that can be used to exclude running tests whose
    * name matches the provided pattern. Test name patterns are interpreted as JavaScript
    * regular expressions. For each test that is executed, any corresponding test hooks,
    * such as `beforeEach()`, are also run.
    * @default undefined
    * @since v22.1.0
    */
  var testSkipPatterns: js.UndefOr[String | js.RegExp | (js.Array[String | js.RegExp])] = js.undefined
  
  /**
    * The number of milliseconds after which the test execution will fail.
    * If unspecified, subtests inherit this value from their parent.
    * @default Infinity
    */
  var timeout: js.UndefOr[Double] = js.undefined
  
  /**
    * Whether to run in watch mode or not.
    * @default false
    */
  var watch: js.UndefOr[Boolean] = js.undefined
}
object RunOptions {
  
  inline def apply(): RunOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RunOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RunOptions] (val x: Self) extends AnyVal {
    
    inline def setConcurrency(value: Double | Boolean): Self = StObject.set(x, "concurrency", value.asInstanceOf[js.Any])
    
    inline def setConcurrencyUndefined: Self = StObject.set(x, "concurrency", js.undefined)
    
    inline def setFiles(value: js.Array[String]): Self = StObject.set(x, "files", value.asInstanceOf[js.Any])
    
    inline def setFilesUndefined: Self = StObject.set(x, "files", js.undefined)
    
    inline def setFilesVarargs(value: String*): Self = StObject.set(x, "files", js.Array(value*))
    
    inline def setForceExit(value: Boolean): Self = StObject.set(x, "forceExit", value.asInstanceOf[js.Any])
    
    inline def setForceExitUndefined: Self = StObject.set(x, "forceExit", js.undefined)
    
    inline def setInspectPort(value: Double | js.Function0[Double]): Self = StObject.set(x, "inspectPort", value.asInstanceOf[js.Any])
    
    inline def setInspectPortFunction0(value: () => Double): Self = StObject.set(x, "inspectPort", js.Any.fromFunction0(value))
    
    inline def setInspectPortUndefined: Self = StObject.set(x, "inspectPort", js.undefined)
    
    inline def setOnly(value: Boolean): Self = StObject.set(x, "only", value.asInstanceOf[js.Any])
    
    inline def setOnlyUndefined: Self = StObject.set(x, "only", js.undefined)
    
    inline def setSetup(value: /* reporter */ TestsStream => Unit | js.Promise[Unit]): Self = StObject.set(x, "setup", js.Any.fromFunction1(value))
    
    inline def setSetupUndefined: Self = StObject.set(x, "setup", js.undefined)
    
    inline def setShard(value: TestShard): Self = StObject.set(x, "shard", value.asInstanceOf[js.Any])
    
    inline def setShardUndefined: Self = StObject.set(x, "shard", js.undefined)
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    
    inline def setTestNamePatterns(value: String | js.RegExp | (js.Array[String | js.RegExp])): Self = StObject.set(x, "testNamePatterns", value.asInstanceOf[js.Any])
    
    inline def setTestNamePatternsUndefined: Self = StObject.set(x, "testNamePatterns", js.undefined)
    
    inline def setTestNamePatternsVarargs(value: (String | js.RegExp)*): Self = StObject.set(x, "testNamePatterns", js.Array(value*))
    
    inline def setTestSkipPatterns(value: String | js.RegExp | (js.Array[String | js.RegExp])): Self = StObject.set(x, "testSkipPatterns", value.asInstanceOf[js.Any])
    
    inline def setTestSkipPatternsUndefined: Self = StObject.set(x, "testSkipPatterns", js.undefined)
    
    inline def setTestSkipPatternsVarargs(value: (String | js.RegExp)*): Self = StObject.set(x, "testSkipPatterns", js.Array(value*))
    
    inline def setTimeout(value: Double): Self = StObject.set(x, "timeout", value.asInstanceOf[js.Any])
    
    inline def setTimeoutUndefined: Self = StObject.set(x, "timeout", js.undefined)
    
    inline def setWatch(value: Boolean): Self = StObject.set(x, "watch", value.asInstanceOf[js.Any])
    
    inline def setWatchUndefined: Self = StObject.set(x, "watch", js.undefined)
  }
}

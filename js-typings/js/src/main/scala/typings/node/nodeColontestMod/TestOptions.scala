package typings.node.nodeColontestMod

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestOptions extends StObject {
  
  /**
    * If a number is provided, then that many tests would run in parallel.
    * If truthy, it would run (number of cpu cores - 1) tests in parallel.
    * For subtests, it will be `Infinity` tests in parallel.
    * If falsy, it would only run one test at a time.
    * If unspecified, subtests inherit this value from their parent.
    * @default false
    */
  var concurrency: js.UndefOr[Double | Boolean] = js.undefined
  
  /**
    * If truthy, and the test context is configured to run `only` tests, then this test will be
    * run. Otherwise, the test is skipped.
    * @default false
    */
  var only: js.UndefOr[Boolean] = js.undefined
  
  /**
    * The number of assertions and subtests expected to be run in the test.
    * If the number of assertions run in the test does not match the number
    * specified in the plan, the test will fail.
    * @default undefined
    * @since v22.2.0
    */
  var plan: js.UndefOr[Double] = js.undefined
  
  /**
    * Allows aborting an in-progress test.
    * @since v18.8.0
    */
  var signal: js.UndefOr[AbortSignal] = js.undefined
  
  /**
    * If truthy, the test is skipped. If a string is provided, that string is displayed in the
    * test results as the reason for skipping the test.
    * @default false
    */
  var skip: js.UndefOr[Boolean | String] = js.undefined
  
  /**
    * A number of milliseconds the test will fail after. If unspecified, subtests inherit this
    * value from their parent.
    * @default Infinity
    * @since v18.7.0
    */
  var timeout: js.UndefOr[Double] = js.undefined
  
  /**
    * If truthy, the test marked as `TODO`. If a string is provided, that string is displayed in
    * the test results as the reason why the test is `TODO`.
    * @default false
    */
  var todo: js.UndefOr[Boolean | String] = js.undefined
}
object TestOptions {
  
  inline def apply(): TestOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[TestOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestOptions] (val x: Self) extends AnyVal {
    
    inline def setConcurrency(value: Double | Boolean): Self = StObject.set(x, "concurrency", value.asInstanceOf[js.Any])
    
    inline def setConcurrencyUndefined: Self = StObject.set(x, "concurrency", js.undefined)
    
    inline def setOnly(value: Boolean): Self = StObject.set(x, "only", value.asInstanceOf[js.Any])
    
    inline def setOnlyUndefined: Self = StObject.set(x, "only", js.undefined)
    
    inline def setPlan(value: Double): Self = StObject.set(x, "plan", value.asInstanceOf[js.Any])
    
    inline def setPlanUndefined: Self = StObject.set(x, "plan", js.undefined)
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    
    inline def setSkip(value: Boolean | String): Self = StObject.set(x, "skip", value.asInstanceOf[js.Any])
    
    inline def setSkipUndefined: Self = StObject.set(x, "skip", js.undefined)
    
    inline def setTimeout(value: Double): Self = StObject.set(x, "timeout", value.asInstanceOf[js.Any])
    
    inline def setTimeoutUndefined: Self = StObject.set(x, "timeout", js.undefined)
    
    inline def setTodo(value: Boolean | String): Self = StObject.set(x, "todo", value.asInstanceOf[js.Any])
    
    inline def setTodoUndefined: Self = StObject.set(x, "todo", js.undefined)
  }
}

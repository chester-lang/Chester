package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * The `MockFunctionContext` class is used to inspect or manipulate the behavior of
  * mocks created via the `MockTracker` APIs.
  * @since v19.1.0, v18.13.0
  */
@js.native
trait MockFunctionContext[F /* <: js.Function */] extends StObject {
  
  /**
    * This function returns the number of times that this mock has been invoked. This
    * function is more efficient than checking `ctx.calls.length` because `ctx.calls` is a getter that creates a copy of the internal call tracking array.
    * @since v19.1.0, v18.13.0
    * @return The number of times that this mock has been invoked.
    */
  def callCount(): Double = js.native
  
  /**
    * A getter that returns a copy of the internal array used to track calls to the
    * mock. Each entry in the array is an object with the following properties.
    * @since v19.1.0, v18.13.0
    */
  val calls: js.Array[
    MockFunctionCall[
      F, 
      /* import warning: importer.ImportType#apply Failed type conversion: F extends (args : any): infer T ? T : F extends abstract new (args : any): infer T ? T : unknown */ js.Any, 
      /* import warning: importer.ImportType#apply Failed type conversion: F extends (args : infer Y): any ? Y : F extends abstract new (args : infer Y): any ? Y : std.Array<unknown> */ js.Any
    ]
  ] = js.native
  
  /**
    * This function is used to change the behavior of an existing mock.
    *
    * The following example creates a mock function using `t.mock.fn()`, calls the
    * mock function, and then changes the mock implementation to a different function.
    *
    * ```js
    * test('changes a mock behavior', (t) => {
    *   let cnt = 0;
    *
    *   function addOne() {
    *     cnt++;
    *     return cnt;
    *   }
    *
    *   function addTwo() {
    *     cnt += 2;
    *     return cnt;
    *   }
    *
    *   const fn = t.mock.fn(addOne);
    *
    *   assert.strictEqual(fn(), 1);
    *   fn.mock.mockImplementation(addTwo);
    *   assert.strictEqual(fn(), 3);
    *   assert.strictEqual(fn(), 5);
    * });
    * ```
    * @since v19.1.0, v18.13.0
    * @param implementation The function to be used as the mock's new implementation.
    */
  def mockImplementation(implementation: F): Unit = js.native
  
  /**
    * This function is used to change the behavior of an existing mock for a single
    * invocation. Once invocation `onCall` has occurred, the mock will revert to
    * whatever behavior it would have used had `mockImplementationOnce()` not been
    * called.
    *
    * The following example creates a mock function using `t.mock.fn()`, calls the
    * mock function, changes the mock implementation to a different function for the
    * next invocation, and then resumes its previous behavior.
    *
    * ```js
    * test('changes a mock behavior once', (t) => {
    *   let cnt = 0;
    *
    *   function addOne() {
    *     cnt++;
    *     return cnt;
    *   }
    *
    *   function addTwo() {
    *     cnt += 2;
    *     return cnt;
    *   }
    *
    *   const fn = t.mock.fn(addOne);
    *
    *   assert.strictEqual(fn(), 1);
    *   fn.mock.mockImplementationOnce(addTwo);
    *   assert.strictEqual(fn(), 3);
    *   assert.strictEqual(fn(), 4);
    * });
    * ```
    * @since v19.1.0, v18.13.0
    * @param implementation The function to be used as the mock's implementation for the invocation number specified by `onCall`.
    * @param onCall The invocation number that will use `implementation`. If the specified invocation has already occurred then an exception is thrown.
    */
  def mockImplementationOnce(implementation: F): Unit = js.native
  def mockImplementationOnce(implementation: F, onCall: Double): Unit = js.native
  
  /**
    * Resets the call history of the mock function.
    * @since v19.3.0, v18.13.0
    */
  def resetCalls(): Unit = js.native
  
  /**
    * Resets the implementation of the mock function to its original behavior. The
    * mock can still be used after calling this function.
    * @since v19.1.0, v18.13.0
    */
  def restore(): Unit = js.native
}

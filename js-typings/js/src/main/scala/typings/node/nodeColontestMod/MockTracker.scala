package typings.node.nodeColontestMod

import typings.node.anon.MockFunction
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * The `MockTracker` class is used to manage mocking functionality. The test runner
  * module provides a top level `mock` export which is a `MockTracker` instance.
  * Each test also provides its own `MockTracker` instance via the test context's `mock` property.
  * @since v19.1.0, v18.13.0
  */
@js.native
trait MockTracker extends StObject {
  
  /**
    * This function is used to create a mock function.
    *
    * The following example creates a mock function that increments a counter by one
    * on each invocation. The `times` option is used to modify the mock behavior such
    * that the first two invocations add two to the counter instead of one.
    *
    * ```js
    * test('mocks a counting function', (t) => {
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
    *   const fn = t.mock.fn(addOne, addTwo, { times: 2 });
    *
    *   assert.strictEqual(fn(), 2);
    *   assert.strictEqual(fn(), 4);
    *   assert.strictEqual(fn(), 5);
    *   assert.strictEqual(fn(), 6);
    * });
    * ```
    * @since v19.1.0, v18.13.0
    * @param original An optional function to create a mock on.
    * @param implementation An optional function used as the mock implementation for `original`. This is useful for creating mocks that exhibit one behavior for a specified number of calls and
    * then restore the behavior of `original`.
    * @param options Optional configuration options for the mock function.
    * @return The mocked function. The mocked function contains a special `mock` property, which is an instance of {@link MockFunctionContext}, and can be used for inspecting and changing the
    * behavior of the mocked function.
    */
  def fn[F /* <: js.Function */](): Mock_[F] = js.native
  def fn[F /* <: js.Function */](original: F): Mock_[F] = js.native
  def fn[F /* <: js.Function */](original: F, options: MockFunctionOptions): Mock_[F] = js.native
  def fn[F /* <: js.Function */](original: Unit, options: MockFunctionOptions): Mock_[F] = js.native
  def fn[F /* <: js.Function */, Implementation /* <: js.Function */](original: F, implementation: Implementation): Mock_[F | Implementation] = js.native
  def fn[F /* <: js.Function */, Implementation /* <: js.Function */](original: F, implementation: Implementation, options: MockFunctionOptions): Mock_[F | Implementation] = js.native
  def fn[F /* <: js.Function */, Implementation /* <: js.Function */](original: F, implementation: Unit, options: MockFunctionOptions): Mock_[F | Implementation] = js.native
  def fn[F /* <: js.Function */, Implementation /* <: js.Function */](original: Unit, implementation: Implementation): Mock_[F | Implementation] = js.native
  def fn[F /* <: js.Function */, Implementation /* <: js.Function */](original: Unit, implementation: Implementation, options: MockFunctionOptions): Mock_[F | Implementation] = js.native
  def fn[F /* <: js.Function */, Implementation /* <: js.Function */](original: Unit, implementation: Unit, options: MockFunctionOptions): Mock_[F | Implementation] = js.native
  @JSName("fn")
  def fn_FImplementation[F /* <: js.Function */, Implementation /* <: js.Function */](): Mock_[F | Implementation] = js.native
  @JSName("fn")
  def fn_FImplementation[F /* <: js.Function */, Implementation /* <: js.Function */](original: F): Mock_[F | Implementation] = js.native
  
  /**
    * This function is syntax sugar for `MockTracker.method` with `options.getter` set to `true`.
    * @since v19.3.0, v18.13.0
    */
  def getter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */](`object`: MockedObject, methodName: MethodName): Mock_[
    js.Function0[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ js.Any
    ]
  ] = js.native
  def getter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */](`object`: MockedObject, methodName: MethodName, options: MockFunctionOptions): Mock_[
    js.Function0[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ js.Any
    ]
  ] = js.native
  def getter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName, implementation: Implementation): Mock_[
    (js.Function0[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ js.Any
    ]) | Implementation
  ] = js.native
  def getter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](
    `object`: MockedObject,
    methodName: MethodName,
    implementation: Implementation,
    options: MockFunctionOptions
  ): Mock_[
    (js.Function0[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ js.Any
    ]) | Implementation
  ] = js.native
  def getter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName, implementation: Unit, options: MockFunctionOptions): Mock_[
    (js.Function0[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ js.Any
    ]) | Implementation
  ] = js.native
  @JSName("getter")
  def getter_MockedObjectMethodNameImplementation[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName): Mock_[
    (js.Function0[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ js.Any
    ]) | Implementation
  ] = js.native
  
  def method[MockedObject /* <: js.Object */](
    `object`: MockedObject,
    methodName: /* keyof MockedObject */ String,
    implementation: js.Function,
    options: MockMethodOptions
  ): MockFunction = js.native
  def method[MockedObject /* <: js.Object */](`object`: MockedObject, methodName: /* keyof MockedObject */ String, options: MockMethodOptions): MockFunction = js.native
  /**
    * This function is used to create a mock on an existing object method. The
    * following example demonstrates how a mock is created on an existing object
    * method.
    *
    * ```js
    * test('spies on an object method', (t) => {
    *   const number = {
    *     value: 5,
    *     subtract(a) {
    *       return this.value - a;
    *     },
    *   };
    *
    *   t.mock.method(number, 'subtract');
    *   assert.strictEqual(number.subtract.mock.calls.length, 0);
    *   assert.strictEqual(number.subtract(3), 2);
    *   assert.strictEqual(number.subtract.mock.calls.length, 1);
    *
    *   const call = number.subtract.mock.calls[0];
    *
    *   assert.deepStrictEqual(call.arguments, [3]);
    *   assert.strictEqual(call.result, 2);
    *   assert.strictEqual(call.error, undefined);
    *   assert.strictEqual(call.target, undefined);
    *   assert.strictEqual(call.this, number);
    * });
    * ```
    * @since v19.1.0, v18.13.0
    * @param object The object whose method is being mocked.
    * @param methodName The identifier of the method on `object` to mock. If `object[methodName]` is not a function, an error is thrown.
    * @param implementation An optional function used as the mock implementation for `object[methodName]`.
    * @param options Optional configuration options for the mock method.
    * @return The mocked method. The mocked method contains a special `mock` property, which is an instance of {@link MockFunctionContext}, and can be used for inspecting and changing the
    * behavior of the mocked method.
    */
  def method[MockedObject /* <: js.Object */, MethodName /* <: FunctionPropertyNames[MockedObject] */](`object`: MockedObject, methodName: MethodName): /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] extends std.Function ? node.node:test.Mock<MockedObject[MethodName]> : never */ js.Any = js.native
  def method[MockedObject /* <: js.Object */, MethodName /* <: FunctionPropertyNames[MockedObject] */](`object`: MockedObject, methodName: MethodName, options: MockFunctionOptions): /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] extends std.Function ? node.node:test.Mock<MockedObject[MethodName]> : never */ js.Any = js.native
  def method[MockedObject /* <: js.Object */, MethodName /* <: FunctionPropertyNames[MockedObject] */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName, implementation: Implementation): /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] extends std.Function ? node.node:test.Mock<MockedObject[MethodName] | Implementation> : never */ js.Any = js.native
  def method[MockedObject /* <: js.Object */, MethodName /* <: FunctionPropertyNames[MockedObject] */, Implementation /* <: js.Function */](
    `object`: MockedObject,
    methodName: MethodName,
    implementation: Implementation,
    options: MockFunctionOptions
  ): /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] extends std.Function ? node.node:test.Mock<MockedObject[MethodName] | Implementation> : never */ js.Any = js.native
  
  /**
    * This function is used to mock the exports of ECMAScript modules, CommonJS modules, and Node.js builtin modules.
    * Any references to the original module prior to mocking are not impacted.
    *
    * Only available through the [--experimental-test-module-mocks](https://nodejs.org/api/cli.html#--experimental-test-module-mocks) flag.
    * @since v22.3.0
    * @experimental
    * @param specifier A string identifying the module to mock.
    * @param options Optional configuration options for the mock module.
    */
  def module(specifier: String): MockModuleContext = js.native
  def module(specifier: String, options: MockModuleOptions): MockModuleContext = js.native
  
  /**
    * This function restores the default behavior of all mocks that were previously
    * created by this `MockTracker` and disassociates the mocks from the `MockTracker` instance. Once disassociated, the mocks can still be used, but the `MockTracker` instance can no longer be
    * used to reset their behavior or
    * otherwise interact with them.
    *
    * After each test completes, this function is called on the test context's `MockTracker`. If the global `MockTracker` is used extensively, calling this
    * function manually is recommended.
    * @since v19.1.0, v18.13.0
    */
  def reset(): Unit = js.native
  
  /**
    * This function restores the default behavior of all mocks that were previously
    * created by this `MockTracker`. Unlike `mock.reset()`, `mock.restoreAll()` does
    * not disassociate the mocks from the `MockTracker` instance.
    * @since v19.1.0, v18.13.0
    */
  def restoreAll(): Unit = js.native
  
  /**
    * This function is syntax sugar for `MockTracker.method` with `options.setter` set to `true`.
    * @since v19.3.0, v18.13.0
    */
  def setter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */](`object`: MockedObject, methodName: MethodName): Mock_[
    js.Function1[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ /* value */ js.Any, 
      Unit
    ]
  ] = js.native
  def setter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */](`object`: MockedObject, methodName: MethodName, options: MockFunctionOptions): Mock_[
    js.Function1[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ /* value */ js.Any, 
      Unit
    ]
  ] = js.native
  def setter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName, implementation: Implementation): Mock_[
    (js.Function1[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ /* value */ js.Any, 
      Unit
    ]) | Implementation
  ] = js.native
  def setter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](
    `object`: MockedObject,
    methodName: MethodName,
    implementation: Implementation,
    options: MockFunctionOptions
  ): Mock_[
    (js.Function1[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ /* value */ js.Any, 
      Unit
    ]) | Implementation
  ] = js.native
  def setter[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName, implementation: Unit, options: MockFunctionOptions): Mock_[
    (js.Function1[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ /* value */ js.Any, 
      Unit
    ]) | Implementation
  ] = js.native
  @JSName("setter")
  def setter_MockedObjectMethodNameImplementation[MockedObject /* <: js.Object */, MethodName /* <: /* keyof MockedObject */ String */, Implementation /* <: js.Function */](`object`: MockedObject, methodName: MethodName): Mock_[
    (js.Function1[
      /* import warning: importer.ImportType#apply Failed type conversion: MockedObject[MethodName] */ /* value */ js.Any, 
      Unit
    ]) | Implementation
  ] = js.native
  
  var timers: MockTimers = js.native
}

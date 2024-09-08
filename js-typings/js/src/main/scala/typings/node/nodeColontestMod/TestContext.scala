package typings.node.nodeColontestMod

import typings.node.anon.FnCallNameOptionsFn
import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * An instance of `TestContext` is passed to each test function in order to
  * interact with the test runner. However, the `TestContext` constructor is not
  * exposed as part of the API.
  * @since v18.0.0, v16.17.0
  */
@JSImport("node:test", "TestContext")
@js.native
open class TestContext () extends StObject {
  
  /**
    * This function is used to create a hook that runs after the current test finishes.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v18.13.0
    */
  def after(): Unit = js.native
  def after(fn: Unit, options: HookOptions): Unit = js.native
  def after(fn: HookFn): Unit = js.native
  def after(fn: HookFn, options: HookOptions): Unit = js.native
  
  /**
    * This function is used to create a hook running after each subtest of the current test.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v18.8.0
    */
  def afterEach(): Unit = js.native
  def afterEach(fn: Unit, options: HookOptions): Unit = js.native
  def afterEach(fn: HookFn): Unit = js.native
  def afterEach(fn: HookFn, options: HookOptions): Unit = js.native
  /**
    * This function is used to create a hook running after each subtest of the current test.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v18.8.0
    */
  @JSName("afterEach")
  var afterEach_Original: js.Function2[/* fn */ js.UndefOr[HookFn], /* options */ js.UndefOr[HookOptions], Unit] = js.native
  
  /**
    * This function is used to create a hook that runs after the current test finishes.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v18.13.0
    */
  @JSName("after")
  var after_Original: js.Function2[/* fn */ js.UndefOr[HookFn], /* options */ js.UndefOr[HookOptions], Unit] = js.native
  
  /**
    * An object containing assertion methods bound to the test context.
    * The top-level functions from the `node:assert` module are exposed here for the purpose of creating test plans.
    * @since v22.2.0
    */
  val assert: TestContextAssert = js.native
  
  /**
    * This function is used to create a hook running before subtest of the current test.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v20.1.0
    */
  def before(): Unit = js.native
  def before(fn: Unit, options: HookOptions): Unit = js.native
  def before(fn: HookFn): Unit = js.native
  def before(fn: HookFn, options: HookOptions): Unit = js.native
  
  /**
    * This function is used to create a hook running before each subtest of the current test.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v18.8.0
    */
  def beforeEach(): Unit = js.native
  def beforeEach(fn: Unit, options: HookOptions): Unit = js.native
  def beforeEach(fn: HookFn): Unit = js.native
  def beforeEach(fn: HookFn, options: HookOptions): Unit = js.native
  /**
    * This function is used to create a hook running before each subtest of the current test.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v18.8.0
    */
  @JSName("beforeEach")
  var beforeEach_Original: js.Function2[/* fn */ js.UndefOr[HookFn], /* options */ js.UndefOr[HookOptions], Unit] = js.native
  
  /**
    * This function is used to create a hook running before subtest of the current test.
    * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
    * @param options Configuration options for the hook.
    * @since v20.1.0
    */
  @JSName("before")
  var before_Original: js.Function2[/* fn */ js.UndefOr[HookFn], /* options */ js.UndefOr[HookOptions], Unit] = js.native
  
  /**
    * This function is used to write diagnostics to the output. Any diagnostic
    * information is included at the end of the test's results. This function does
    * not return a value.
    *
    * ```js
    * test('top level test', (t) => {
    *   t.diagnostic('A diagnostic message');
    * });
    * ```
    * @since v18.0.0, v16.17.0
    * @param message Message to be reported.
    */
  def diagnostic(message: String): Unit = js.native
  
  /**
    * The name of the test and each of its ancestors, separated by `>`.
    * @since v22.3.0
    */
  val fullName: String = js.native
  
  /**
    * Each test provides its own MockTracker instance.
    */
  val mock: MockTracker = js.native
  
  /**
    * The name of the test.
    * @since v18.8.0, v16.18.0
    */
  val name: String = js.native
  
  /**
    * Used to set the number of assertions and subtests that are expected to run within the test.
    * If the number of assertions and subtests that run does not match the expected count, the test will fail.
    *
    * To make sure assertions are tracked, the assert functions on `context.assert` must be used,
    * instead of importing from the `node:assert` module.
    * ```js
    * test('top level test', (t) => {
    *   t.plan(2);
    *   t.assert.ok('some relevant assertion here');
    *   t.test('subtest', () => {});
    * });
    * ```
    *
    * When working with asynchronous code, the `plan` function can be used to ensure that the correct number of assertions are run:
    * ```js
    * test('planning with streams', (t, done) => {
    *   function* generate() {
    *     yield 'a';
    *     yield 'b';
    *     yield 'c';
    *   }
    *   const expected = ['a', 'b', 'c'];
    *   t.plan(expected.length);
    *   const stream = Readable.from(generate());
    *   stream.on('data', (chunk) => {
    *     t.assert.strictEqual(chunk, expected.shift());
    *   });
    *   stream.on('end', () => {
    *     done();
    *   });
    * });
    * ```
    * @since v22.2.0
    */
  def plan(count: Double): Unit = js.native
  
  /**
    * If `shouldRunOnlyTests` is truthy, the test context will only run tests that
    * have the `only` option set. Otherwise, all tests are run. If Node.js was not
    * started with the `--test-only` command-line option, this function is a
    * no-op.
    *
    * ```js
    * test('top level test', (t) => {
    *   // The test context can be set to run subtests with the 'only' option.
    *   t.runOnly(true);
    *   return Promise.all([
    *     t.test('this subtest is now skipped'),
    *     t.test('this subtest is run', { only: true }),
    *   ]);
    * });
    * ```
    * @since v18.0.0, v16.17.0
    * @param shouldRunOnlyTests Whether or not to run `only` tests.
    */
  def runOnly(shouldRunOnlyTests: Boolean): Unit = js.native
  
  /**
    * ```js
    * test('top level test', async (t) => {
    *   await fetch('some/uri', { signal: t.signal });
    * });
    * ```
    * @since v18.7.0, v16.17.0
    */
  val signal: AbortSignal = js.native
  
  /**
    * This function causes the test's output to indicate the test as skipped. If `message` is provided, it is included in the output. Calling `skip()` does
    * not terminate execution of the test function. This function does not return a
    * value.
    *
    * ```js
    * test('top level test', (t) => {
    *   // Make sure to return here as well if the test contains additional logic.
    *   t.skip('this is skipped');
    * });
    * ```
    * @since v18.0.0, v16.17.0
    * @param message Optional skip message.
    */
  def skip(): Unit = js.native
  def skip(message: String): Unit = js.native
  
  /**
    * This function is used to create subtests under the current test. This function behaves in
    * the same fashion as the top level {@link test} function.
    * @since v18.0.0
    * @param name The name of the test, which is displayed when reporting test results.
    * Defaults to the `name` property of `fn`, or `'<anonymous>'` if `fn` does not have a name.
    * @param options Configuration options for the test.
    * @param fn The function under test. This first argument to this function is a {@link TestContext} object.
    * If the test uses callbacks, the callback function is passed as the second argument.
    * @returns A {@link Promise} resolved with `undefined` once the test completes.
    */
  def test(): js.Promise[Unit] = js.native
  def test(fn: TestFn): js.Promise[Unit] = js.native
  def test(name: String): js.Promise[Unit] = js.native
  def test(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def test(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def test(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def test(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def test(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def test(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def test(options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def test(options: TestOptions): js.Promise[Unit] = js.native
  def test(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  /**
    * This function is used to create subtests under the current test. This function behaves in
    * the same fashion as the top level {@link test} function.
    * @since v18.0.0
    * @param name The name of the test, which is displayed when reporting test results.
    * Defaults to the `name` property of `fn`, or `'<anonymous>'` if `fn` does not have a name.
    * @param options Configuration options for the test.
    * @param fn The function under test. This first argument to this function is a {@link TestContext} object.
    * If the test uses callbacks, the callback function is passed as the second argument.
    * @returns A {@link Promise} resolved with `undefined` once the test completes.
    */
  @JSName("test")
  var test_Original: FnCallNameOptionsFn = js.native
  
  /**
    * This function adds a `TODO` directive to the test's output. If `message` is
    * provided, it is included in the output. Calling `todo()` does not terminate
    * execution of the test function. This function does not return a value.
    *
    * ```js
    * test('top level test', (t) => {
    *   // This test is marked as `TODO`
    *   t.todo('this is a todo');
    * });
    * ```
    * @since v18.0.0, v16.17.0
    * @param message Optional `TODO` message.
    */
  def todo(): Unit = js.native
  def todo(message: String): Unit = js.native
}

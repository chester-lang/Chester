package typings.node.nodeColontestMod

import typings.node.anon.Mock
import typings.node.nodeColontestMod.^
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}


/**
  * This function creates a hook that runs after executing a suite.
  *
  * ```js
  * describe('tests', async () => {
  *   after(() => console.log('finished running tests'));
  *   it('is a subtest', () => {
  *     assert.ok('some relevant assertion here');
  *   });
  * });
  * ```
  * @since v18.8.0, v16.18.0
  * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
  * @param options Configuration options for the hook.
  */
inline def after(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("after")().asInstanceOf[Unit]
inline def after(fn: Unit, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("after")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def after(fn: HookFn): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("after")(fn.asInstanceOf[js.Any]).asInstanceOf[Unit]
inline def after(fn: HookFn, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("after")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]

/**
  * This function creates a hook that runs after each test in the current suite.
  * The `afterEach()` hook is run even if the test fails.
  *
  * ```js
  * describe('tests', async () => {
  *   afterEach(() => console.log('finished running a test'));
  *   it('is a subtest', () => {
  *     assert.ok('some relevant assertion here');
  *   });
  * });
  * ```
  * @since v18.8.0, v16.18.0
  * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
  * @param options Configuration options for the hook.
  */
inline def afterEach(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("afterEach")().asInstanceOf[Unit]
inline def afterEach(fn: Unit, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("afterEach")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def afterEach(fn: HookFn): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("afterEach")(fn.asInstanceOf[js.Any]).asInstanceOf[Unit]
inline def afterEach(fn: HookFn, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("afterEach")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]

/**
  * This function creates a hook that runs before executing a suite.
  *
  * ```js
  * describe('tests', async () => {
  *   before(() => console.log('about to run some test'));
  *   it('is a subtest', () => {
  *     assert.ok('some relevant assertion here');
  *   });
  * });
  * ```
  * @since v18.8.0, v16.18.0
  * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
  * @param options Configuration options for the hook.
  */
inline def before(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("before")().asInstanceOf[Unit]
inline def before(fn: Unit, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("before")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def before(fn: HookFn): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("before")(fn.asInstanceOf[js.Any]).asInstanceOf[Unit]
inline def before(fn: HookFn, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("before")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]

/**
  * This function creates a hook that runs before each test in the current suite.
  *
  * ```js
  * describe('tests', async () => {
  *   beforeEach(() => console.log('about to run a test'));
  *   it('is a subtest', () => {
  *     assert.ok('some relevant assertion here');
  *   });
  * });
  * ```
  * @since v18.8.0, v16.18.0
  * @param fn The hook function. If the hook uses callbacks, the callback function is passed as the second argument.
  * @param options Configuration options for the hook.
  */
inline def beforeEach(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("beforeEach")().asInstanceOf[Unit]
inline def beforeEach(fn: Unit, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("beforeEach")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def beforeEach(fn: HookFn): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("beforeEach")(fn.asInstanceOf[js.Any]).asInstanceOf[Unit]
inline def beforeEach(fn: HookFn, options: HookOptions): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("beforeEach")(fn.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def mock: MockTracker = ^.asInstanceOf[js.Dynamic].selectDynamic("mock").asInstanceOf[MockTracker]

/**
  * Shorthand for marking a test as `only`. This is the same as calling {@link test} with `options.only` set to `true`.
  * @since v20.2.0
  */
inline def only(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")().asInstanceOf[js.Promise[Unit]]
inline def only(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def only(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def only(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def only(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def only(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]

/**
  * **Note:** `shard` is used to horizontally parallelize test running across
  * machines or processes, ideal for large-scale executions across varied
  * environments. It's incompatible with `watch` mode, tailored for rapid
  * code iteration by automatically rerunning tests on file changes.
  *
  * ```js
  * import { tap } from 'node:test/reporters';
  * import { run } from 'node:test';
  * import process from 'node:process';
  * import path from 'node:path';
  *
  * run({ files: [path.resolve('./tests/test.js')] })
  *   .compose(tap)
  *   .pipe(process.stdout);
  * ```
  * @since v18.9.0, v16.19.0
  * @param options Configuration options for running tests.
  */
inline def run(): TestsStream = ^.asInstanceOf[js.Dynamic].applyDynamic("run")().asInstanceOf[TestsStream]
inline def run(options: RunOptions): TestsStream = ^.asInstanceOf[js.Dynamic].applyDynamic("run")(options.asInstanceOf[js.Any]).asInstanceOf[TestsStream]

/**
  * Shorthand for skipping a test. This is the same as calling {@link test} with `options.skip` set to `true`.
  * @since v20.2.0
  */
inline def skip(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")().asInstanceOf[js.Promise[Unit]]
inline def skip(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def skip(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def skip(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def skip(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def skip(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]

/**
  * The `test()` function is the value imported from the `test` module. Each
  * invocation of this function results in reporting the test to the `TestsStream`.
  *
  * The `TestContext` object passed to the `fn` argument can be used to perform
  * actions related to the current test. Examples include skipping the test, adding
  * additional diagnostic information, or creating subtests.
  *
  * `test()` returns a `Promise` that fulfills once the test completes.
  * if `test()` is called within a suite, it fulfills immediately.
  * The return value can usually be discarded for top level tests.
  * However, the return value from subtests should be used to prevent the parent
  * test from finishing first and cancelling the subtest
  * as shown in the following example.
  *
  * ```js
  * test('top level test', async (t) => {
  *   // The setTimeout() in the following subtest would cause it to outlive its
  *   // parent test if 'await' is removed on the next line. Once the parent test
  *   // completes, it will cancel any outstanding subtests.
  *   await t.test('longer running subtest', async (t) => {
  *     return new Promise((resolve, reject) => {
  *       setTimeout(resolve, 1000);
  *     });
  *   });
  * });
  * ```
  *
  * The `timeout` option can be used to fail the test if it takes longer than `timeout` milliseconds to complete. However, it is not a reliable mechanism for
  * canceling tests because a running test might block the application thread and
  * thus prevent the scheduled cancellation.
  * @since v18.0.0, v16.17.0
  * @param name The name of the test, which is displayed when reporting test results.
  * Defaults to the `name` property of `fn`, or `'<anonymous>'` if `fn` does not have a name.
  * @param options Configuration options for the test.
  * @param fn The function under test. The first argument to this function is a {@link TestContext} object.
  * If the test uses callbacks, the callback function is passed as the second argument.
  * @return Fulfilled with `undefined` once the test completes, or immediately if the test runs within a suite.
  */
inline def test(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("test")().asInstanceOf[js.Promise[Unit]]
inline def test(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("test")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def test(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def test(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def test(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("test")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def test(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("test")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]

/**
  * Shorthand for marking a test as `TODO`. This is the same as calling {@link test} with `options.todo` set to `true`.
  * @since v20.2.0
  */
inline def todo(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")().asInstanceOf[js.Promise[Unit]]
inline def todo(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def todo(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def todo(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
inline def todo(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
inline def todo(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]

type FunctionPropertyNames[T] = /* import warning: importer.ImportType#apply Failed type conversion: {[ K in keyof T ]: T[K] extends std.Function? K : never}[keyof T] */ js.Any

/**
  * The hook function. If the hook uses callbacks, the callback function is passed as the
  * second argument.
  */
type HookFn = js.Function2[
/* s */ SuiteContext, 
/* done */ js.Function1[/* result */ js.UndefOr[Any], Unit], 
Any]

type Mock_[F /* <: js.Function */] = F & Mock[F]

/**
  * The type of a suite test function. The argument to this function is a {@link SuiteContext} object.
  */
type SuiteFn = js.Function1[/* s */ SuiteContext, Unit | js.Promise[Unit]]

/**
  * The type of a function passed to {@link test}. The first argument to this function is a {@link TestContext} object.
  * If the test uses callbacks, the callback function is passed as the second argument.
  */
type TestFn = js.Function2[
/* t */ TestContext, 
/* done */ js.Function1[/* result */ js.UndefOr[Any], Unit], 
Unit | js.Promise[Unit]]

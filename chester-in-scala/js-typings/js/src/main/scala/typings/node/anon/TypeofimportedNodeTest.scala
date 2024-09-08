package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import typings.node.nodeColontestMod.HookFn
import typings.node.nodeColontestMod.HookOptions
import typings.node.nodeColontestMod.MockTracker
import typings.node.nodeColontestMod.RunOptions
import typings.node.nodeColontestMod.SuiteFn
import typings.node.nodeColontestMod.TestFn
import typings.node.nodeColontestMod.TestOptions
import typings.node.nodeColontestMod.TestsStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodeTest extends StObject {
  
  def default(): js.Promise[Unit] = js.native
  def default(name: String): js.Promise[Unit] = js.native
  def default(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def default(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def default(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def default(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def default(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def default(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def default(options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def default(options: TestOptions): js.Promise[Unit] = js.native
  def default(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  
  /**
    * An instance of `SuiteContext` is passed to each suite function in order to
    * interact with the test runner. However, the `SuiteContext` constructor is not
    * exposed as part of the API.
    * @since v18.7.0, v16.17.0
    */
  var SuiteContext: Instantiable0[typings.node.nodeColontestMod.SuiteContext] = js.native
  
  /**
    * An instance of `TestContext` is passed to each test function in order to
    * interact with the test runner. However, the `TestContext` constructor is not
    * exposed as part of the API.
    * @since v18.0.0, v16.17.0
    */
  var TestContext: Instantiable0[typings.node.nodeColontestMod.TestContext] = js.native
  
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
  def after(): Unit = js.native
  def after(fn: Unit, options: HookOptions): Unit = js.native
  def after(fn: HookFn): Unit = js.native
  def after(fn: HookFn, options: HookOptions): Unit = js.native
  
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
  def afterEach(): Unit = js.native
  def afterEach(fn: Unit, options: HookOptions): Unit = js.native
  def afterEach(fn: HookFn): Unit = js.native
  def afterEach(fn: HookFn, options: HookOptions): Unit = js.native
  
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
  def before(): Unit = js.native
  def before(fn: Unit, options: HookOptions): Unit = js.native
  def before(fn: HookFn): Unit = js.native
  def before(fn: HookFn, options: HookOptions): Unit = js.native
  
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
  def beforeEach(): Unit = js.native
  def beforeEach(fn: Unit, options: HookOptions): Unit = js.native
  def beforeEach(fn: HookFn): Unit = js.native
  def beforeEach(fn: HookFn, options: HookOptions): Unit = js.native
  
  @JSName("default")
  val default_FTypeofimportedNodeTest: Typeofdefault = js.native
  
  def describe(): js.Promise[Unit] = js.native
  def describe(name: String): js.Promise[Unit] = js.native
  def describe(name: String, fn: SuiteFn): js.Promise[Unit] = js.native
  def describe(options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def describe(options: TestOptions): js.Promise[Unit] = js.native
  def describe(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  @JSName("describe")
  val describe_FTypeofimportedNodeTest: Typeofdescribe = js.native
  
  def it(): js.Promise[Unit] = js.native
  def it(name: String): js.Promise[Unit] = js.native
  def it(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def it(options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def it(options: TestOptions): js.Promise[Unit] = js.native
  def it(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  @JSName("it")
  val it_FTypeofimportedNodeTest: Typeofit = js.native
  
  val mock: MockTracker = js.native
  
  def only(): js.Promise[Unit] = js.native
  def only(fn: TestFn): js.Promise[Unit] = js.native
  def only(name: String): js.Promise[Unit] = js.native
  def only(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def only(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def only(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def only(options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def only(options: TestOptions): js.Promise[Unit] = js.native
  def only(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  
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
  def run(): TestsStream = js.native
  def run(options: RunOptions): TestsStream = js.native
  
  def skip(): js.Promise[Unit] = js.native
  def skip(fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: String): js.Promise[Unit] = js.native
  def skip(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def skip(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def skip(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def skip(options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def skip(options: TestOptions): js.Promise[Unit] = js.native
  def skip(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  
  /**
    * Only available through the [--experimental-test-snapshots](https://nodejs.org/api/cli.html#--experimental-test-snapshots) flag.
    * @since v22.3.0
    * @experimental
    */
  val snapshot: Typeofsnapshot = js.native
  
  def suite(): js.Promise[Unit] = js.native
  def suite(name: String): js.Promise[Unit] = js.native
  def suite(name: String, fn: SuiteFn): js.Promise[Unit] = js.native
  def suite(options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def suite(options: TestOptions): js.Promise[Unit] = js.native
  def suite(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  @JSName("suite")
  val suite_FTypeofimportedNodeTest: Typeofsuite = js.native
  
  def test(): js.Promise[Unit] = js.native
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
  @JSName("test")
  val test_FTypeofimportedNodeTest: Typeoftest = js.native
  
  def todo(): js.Promise[Unit] = js.native
  def todo(fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: String): js.Promise[Unit] = js.native
  def todo(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def todo(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def todo(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def todo(options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def todo(options: TestOptions): js.Promise[Unit] = js.native
  def todo(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
}

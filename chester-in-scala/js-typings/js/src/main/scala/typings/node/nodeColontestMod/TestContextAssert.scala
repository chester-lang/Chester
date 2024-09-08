package typings.node.nodeColontestMod

import typings.node.anon.Fn0
import typings.node.anon.FnCall
import typings.node.anon.FnCallActualExpectedMessageOperatorStackStartFn
import typings.node.anon.FnCallBlockErrorMessage
import typings.node.assertMod.AssertPredicate
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TestContextAssert extends StObject {
  
  /**
    * Identical to the `deepEqual` function from the `node:assert` module, but bound to the test context.
    */
  def deepEqual(actual: Any, expected: Any): Unit = js.native
  def deepEqual(actual: Any, expected: Any, message: String): Unit = js.native
  def deepEqual(actual: Any, expected: Any, message: js.Error): Unit = js.native
  /**
    * Identical to the `deepEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("deepEqual")
  var deepEqual_Original: js.Function3[
    /* actual */ Any, 
    /* expected */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `deepStrictEqual` function from the `node:assert` module, but bound to the test context.
    */
  def deepStrictEqual[T](actual: Any, expected: T): /* asserts actual is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(T))),IArray())*/ Boolean = js.native
  def deepStrictEqual[T](actual: Any, expected: T, message: String): /* asserts actual is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(T))),IArray())*/ Boolean = js.native
  def deepStrictEqual[T](actual: Any, expected: T, message: js.Error): /* asserts actual is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(T))),IArray())*/ Boolean = js.native
  /**
    * Identical to the `deepStrictEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("deepStrictEqual")
  var deepStrictEqual_Original: FnCall = js.native
  
  /**
    * Identical to the `doesNotMatch` function from the `node:assert` module, but bound to the test context.
    */
  def doesNotMatch(value: String, regExp: js.RegExp): Unit = js.native
  def doesNotMatch(value: String, regExp: js.RegExp, message: String): Unit = js.native
  def doesNotMatch(value: String, regExp: js.RegExp, message: js.Error): Unit = js.native
  /**
    * Identical to the `doesNotMatch` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("doesNotMatch")
  var doesNotMatch_Original: js.Function3[
    /* value */ String, 
    /* regExp */ js.RegExp, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `doesNotReject` function from the `node:assert` module, but bound to the test context.
    */
  def doesNotReject(block: js.Function0[js.Promise[Any]]): js.Promise[Unit] = js.native
  /**
    * Identical to the `doesNotReject` function from the `node:assert` module, but bound to the test context.
    */
  def doesNotReject(block: js.Function0[js.Promise[Any]], error: AssertPredicate): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Function0[js.Promise[Any]], error: AssertPredicate, message: String): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Function0[js.Promise[Any]], error: AssertPredicate, message: js.Error): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Function0[js.Promise[Any]], message: String): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Function0[js.Promise[Any]], message: js.Error): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Promise[Any]): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Promise[Any], error: AssertPredicate): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Promise[Any], error: AssertPredicate, message: String): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Promise[Any], error: AssertPredicate, message: js.Error): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Promise[Any], message: String): js.Promise[Unit] = js.native
  def doesNotReject(block: js.Promise[Any], message: js.Error): js.Promise[Unit] = js.native
  /**
    * Identical to the `doesNotReject` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("doesNotReject")
  var doesNotReject_Original: FnCallBlockErrorMessage = js.native
  
  /**
    * Identical to the `doesNotThrow` function from the `node:assert` module, but bound to the test context.
    */
  def doesNotThrow(block: js.Function0[Any]): Unit = js.native
  /**
    * Identical to the `doesNotThrow` function from the `node:assert` module, but bound to the test context.
    */
  def doesNotThrow(block: js.Function0[Any], error: AssertPredicate): Unit = js.native
  def doesNotThrow(block: js.Function0[Any], error: AssertPredicate, message: String): Unit = js.native
  def doesNotThrow(block: js.Function0[Any], error: AssertPredicate, message: js.Error): Unit = js.native
  def doesNotThrow(block: js.Function0[Any], message: String): Unit = js.native
  def doesNotThrow(block: js.Function0[Any], message: js.Error): Unit = js.native
  /**
    * Identical to the `doesNotThrow` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("doesNotThrow")
  var doesNotThrow_Original: Fn0 = js.native
  
  /**
    * Identical to the `equal` function from the `node:assert` module, but bound to the test context.
    */
  def equal(actual: Any, expected: Any): Unit = js.native
  def equal(actual: Any, expected: Any, message: String): Unit = js.native
  def equal(actual: Any, expected: Any, message: js.Error): Unit = js.native
  /**
    * Identical to the `equal` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("equal")
  var equal_Original: js.Function3[
    /* actual */ Any, 
    /* expected */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `fail` function from the `node:assert` module, but bound to the test context.
    */
  def fail(): scala.Nothing = js.native
  /**
    * Identical to the `fail` function from the `node:assert` module, but bound to the test context.
    */
  def fail(actual: Any, expected: Any): scala.Nothing = js.native
  def fail(actual: Any, expected: Any, message: String): scala.Nothing = js.native
  def fail(actual: Any, expected: Any, message: String, operator: String): scala.Nothing = js.native
  def fail(
    actual: Any,
    expected: Any,
    message: String,
    operator: String,
    // eslint-disable-next-line @typescript-eslint/ban-types
  stackStartFn: js.Function
  ): scala.Nothing = js.native
  def fail(
    actual: Any,
    expected: Any,
    message: String,
    operator: Unit,
    // eslint-disable-next-line @typescript-eslint/ban-types
  stackStartFn: js.Function
  ): scala.Nothing = js.native
  def fail(actual: Any, expected: Any, message: js.Error): scala.Nothing = js.native
  def fail(actual: Any, expected: Any, message: js.Error, operator: String): scala.Nothing = js.native
  def fail(
    actual: Any,
    expected: Any,
    message: js.Error,
    operator: String,
    // eslint-disable-next-line @typescript-eslint/ban-types
  stackStartFn: js.Function
  ): scala.Nothing = js.native
  def fail(
    actual: Any,
    expected: Any,
    message: js.Error,
    operator: Unit,
    // eslint-disable-next-line @typescript-eslint/ban-types
  stackStartFn: js.Function
  ): scala.Nothing = js.native
  def fail(actual: Any, expected: Any, message: Unit, operator: String): scala.Nothing = js.native
  def fail(
    actual: Any,
    expected: Any,
    message: Unit,
    operator: String,
    // eslint-disable-next-line @typescript-eslint/ban-types
  stackStartFn: js.Function
  ): scala.Nothing = js.native
  def fail(
    actual: Any,
    expected: Any,
    message: Unit,
    operator: Unit,
    // eslint-disable-next-line @typescript-eslint/ban-types
  stackStartFn: js.Function
  ): scala.Nothing = js.native
  def fail(message: String): scala.Nothing = js.native
  def fail(message: js.Error): scala.Nothing = js.native
  /**
    * Identical to the `fail` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("fail")
  var fail_Original: FnCallActualExpectedMessageOperatorStackStartFn = js.native
  
  /**
    * Identical to the `ifError` function from the `node:assert` module, but bound to the test context.
    */
  def ifError(value: Any): /* asserts value is TsTypeUnion(IArray(TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(null))),IArray()), TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(undefined))),IArray())))*/ Boolean = js.native
  /**
    * Identical to the `ifError` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("ifError")
  var ifError_Original: js.Function1[
    /* value */ Any, 
    /* asserts value is TsTypeUnion(IArray(TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(null))),IArray()), TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(undefined))),IArray())))*/ Boolean
  ] = js.native
  
  /**
    * Identical to the `match` function from the `node:assert` module, but bound to the test context.
    */
  def `match`(value: String, regExp: js.RegExp): Unit = js.native
  def `match`(value: String, regExp: js.RegExp, message: String): Unit = js.native
  def `match`(value: String, regExp: js.RegExp, message: js.Error): Unit = js.native
  /**
    * Identical to the `match` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("match")
  var match_Original: js.Function3[
    /* value */ String, 
    /* regExp */ js.RegExp, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `notDeepEqual` function from the `node:assert` module, but bound to the test context.
    */
  def notDeepEqual(actual: Any, expected: Any): Unit = js.native
  def notDeepEqual(actual: Any, expected: Any, message: String): Unit = js.native
  def notDeepEqual(actual: Any, expected: Any, message: js.Error): Unit = js.native
  /**
    * Identical to the `notDeepEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("notDeepEqual")
  var notDeepEqual_Original: js.Function3[
    /* actual */ Any, 
    /* expected */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `notDeepStrictEqual` function from the `node:assert` module, but bound to the test context.
    */
  def notDeepStrictEqual(actual: Any, expected: Any): Unit = js.native
  def notDeepStrictEqual(actual: Any, expected: Any, message: String): Unit = js.native
  def notDeepStrictEqual(actual: Any, expected: Any, message: js.Error): Unit = js.native
  /**
    * Identical to the `notDeepStrictEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("notDeepStrictEqual")
  var notDeepStrictEqual_Original: js.Function3[
    /* actual */ Any, 
    /* expected */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `notEqual` function from the `node:assert` module, but bound to the test context.
    */
  def notEqual(actual: Any, expected: Any): Unit = js.native
  def notEqual(actual: Any, expected: Any, message: String): Unit = js.native
  def notEqual(actual: Any, expected: Any, message: js.Error): Unit = js.native
  /**
    * Identical to the `notEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("notEqual")
  var notEqual_Original: js.Function3[
    /* actual */ Any, 
    /* expected */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `notStrictEqual` function from the `node:assert` module, but bound to the test context.
    */
  def notStrictEqual(actual: Any, expected: Any): Unit = js.native
  def notStrictEqual(actual: Any, expected: Any, message: String): Unit = js.native
  def notStrictEqual(actual: Any, expected: Any, message: js.Error): Unit = js.native
  /**
    * Identical to the `notStrictEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("notStrictEqual")
  var notStrictEqual_Original: js.Function3[
    /* actual */ Any, 
    /* expected */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    Unit
  ] = js.native
  
  /**
    * Identical to the `ok` function from the `node:assert` module, but bound to the test context.
    */
  def ok(value: Any): /* asserts value */ Boolean = js.native
  def ok(value: Any, message: String): /* asserts value */ Boolean = js.native
  def ok(value: Any, message: js.Error): /* asserts value */ Boolean = js.native
  /**
    * Identical to the `ok` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("ok")
  var ok_Original: js.Function2[
    /* value */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    /* asserts value */ Boolean
  ] = js.native
  
  /**
    * Identical to the `rejects` function from the `node:assert` module, but bound to the test context.
    */
  def rejects(block: js.Function0[js.Promise[Any]]): js.Promise[Unit] = js.native
  /**
    * Identical to the `rejects` function from the `node:assert` module, but bound to the test context.
    */
  def rejects(block: js.Function0[js.Promise[Any]], error: AssertPredicate): js.Promise[Unit] = js.native
  def rejects(block: js.Function0[js.Promise[Any]], error: AssertPredicate, message: String): js.Promise[Unit] = js.native
  def rejects(block: js.Function0[js.Promise[Any]], error: AssertPredicate, message: js.Error): js.Promise[Unit] = js.native
  def rejects(block: js.Function0[js.Promise[Any]], message: String): js.Promise[Unit] = js.native
  def rejects(block: js.Function0[js.Promise[Any]], message: js.Error): js.Promise[Unit] = js.native
  def rejects(block: js.Promise[Any]): js.Promise[Unit] = js.native
  def rejects(block: js.Promise[Any], error: AssertPredicate): js.Promise[Unit] = js.native
  def rejects(block: js.Promise[Any], error: AssertPredicate, message: String): js.Promise[Unit] = js.native
  def rejects(block: js.Promise[Any], error: AssertPredicate, message: js.Error): js.Promise[Unit] = js.native
  def rejects(block: js.Promise[Any], message: String): js.Promise[Unit] = js.native
  def rejects(block: js.Promise[Any], message: js.Error): js.Promise[Unit] = js.native
  /**
    * Identical to the `rejects` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("rejects")
  var rejects_Original: FnCallBlockErrorMessage = js.native
  
  /**
    * This function implements assertions for snapshot testing.
    * ```js
    * test('snapshot test with default serialization', (t) => {
    *   t.assert.snapshot({ value1: 1, value2: 2 });
    * });
    *
    * test('snapshot test with custom serialization', (t) => {
    *   t.assert.snapshot({ value3: 3, value4: 4 }, {
    *     serializers: [(value) => JSON.stringify(value)]
    *   });
    * });
    * ```
    *
    * Only available through the [--experimental-test-snapshots](https://nodejs.org/api/cli.html#--experimental-test-snapshots) flag.
    * @since v22.3.0
    * @experimental
    */
  def snapshot(value: Any): Unit = js.native
  def snapshot(value: Any, options: AssertSnapshotOptions): Unit = js.native
  
  /**
    * Identical to the `strictEqual` function from the `node:assert` module, but bound to the test context.
    */
  def strictEqual[T](actual: Any, expected: T): /* asserts actual is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(T))),IArray())*/ Boolean = js.native
  def strictEqual[T](actual: Any, expected: T, message: String): /* asserts actual is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(T))),IArray())*/ Boolean = js.native
  def strictEqual[T](actual: Any, expected: T, message: js.Error): /* asserts actual is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(T))),IArray())*/ Boolean = js.native
  /**
    * Identical to the `strictEqual` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("strictEqual")
  var strictEqual_Original: FnCall = js.native
  
  /**
    * Identical to the `throws` function from the `node:assert` module, but bound to the test context.
    */
  def throws(block: js.Function0[Any]): Unit = js.native
  /**
    * Identical to the `throws` function from the `node:assert` module, but bound to the test context.
    */
  def throws(block: js.Function0[Any], error: AssertPredicate): Unit = js.native
  def throws(block: js.Function0[Any], error: AssertPredicate, message: String): Unit = js.native
  def throws(block: js.Function0[Any], error: AssertPredicate, message: js.Error): Unit = js.native
  def throws(block: js.Function0[Any], message: String): Unit = js.native
  def throws(block: js.Function0[Any], message: js.Error): Unit = js.native
  /**
    * Identical to the `throws` function from the `node:assert` module, but bound to the test context.
    */
  @JSName("throws")
  var throws_Original: Fn0 = js.native
}

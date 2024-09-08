package typings.node

import typings.node.nodeColonstreamMod.Transform
import typings.node.streamMod.TransformOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * The `node:test/reporters` module exposes the builtin-reporters for `node:test`.
  * To access it:
  *
  * ```js
  * import test from 'node:test/reporters';
  * ```
  *
  * This module is only available under the `node:` scheme. The following will not
  * work:
  *
  * ```js
  * import test from 'test/reporters';
  * ```
  * @since v19.9.0
  * @see [source](https://github.com/nodejs/node/blob/v22.x/lib/test/reporters.js)
  */
object nodeColontestReportersMod {
  
  @JSImport("node:test/reporters", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * The `dot` reporter outputs the test results in a compact format,
    * where each passing test is represented by a `.`,
    * and each failing test is represented by a `X`.
    */
  inline def dot(source: TestEventGenerator): Any = ^.asInstanceOf[js.Dynamic].applyDynamic("dot")(source.asInstanceOf[js.Any]).asInstanceOf[Any]
  
  /**
    * The `junit` reporter outputs test results in a jUnit XML format.
    */
  inline def junit(source: TestEventGenerator): Any = ^.asInstanceOf[js.Dynamic].applyDynamic("junit")(source.asInstanceOf[js.Any]).asInstanceOf[Any]
  
  /**
    * The `lcov` reporter outputs test coverage when used with the [`--experimental-test-coverage`](https://nodejs.org/docs/latest-v22.x/api/cli.html#--experimental-test-coverage) flag.
    */
  @JSImport("node:test/reporters", "lcov")
  @js.native
  open class lcov () extends StObject {
    def this(opts: TransformOptions) = this()
  }
  
  /**
    * The `spec` reporter outputs the test results in a human-readable format.
    */
  @JSImport("node:test/reporters", "spec")
  @js.native
  open class spec () extends StObject
  
  /**
    * The `tap` reporter outputs the test results in the [TAP](https://testanything.org/) format.
    */
  inline def tap(source: TestEventGenerator): Any = ^.asInstanceOf[js.Dynamic].applyDynamic("tap")(source.asInstanceOf[js.Any]).asInstanceOf[Any]
  
  /**
    * The `lcov` reporter outputs test coverage when used with the [`--experimental-test-coverage`](https://nodejs.org/docs/latest-v22.x/api/cli.html#--experimental-test-coverage) flag.
    */
  type Lcov_ = Transform
  
  /**
    * The `spec` reporter outputs the test results in a human-readable format.
    */
  type Spec_ = Transform
  
  /* Rewritten from type alias, can be one of: 
    - typings.node.anon.DataType
    - typings.node.anon.DataTestComplete
    - typings.node.anon.DataTestDequeue
    - typings.node.anon.DataDiagnosticData
    - typings.node.anon.DataTestEnqueue
    - typings.node.anon.DataTestFail
    - typings.node.anon.DataTestPass
    - typings.node.anon.DataTestPlan
    - typings.node.anon.DataTestStart
    - typings.node.anon.DataTestStderr
    - typings.node.anon.DataTestStdout
    - typings.node.anon.DataUndefined
  */
  trait TestEvent extends StObject
  object TestEvent {
    
    inline def DataDiagnosticData(data: DiagnosticData): typings.node.anon.DataDiagnosticData = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:diagnostic")
      __obj.asInstanceOf[typings.node.anon.DataDiagnosticData]
    }
    
    inline def DataTestComplete(data: TestComplete): typings.node.anon.DataTestComplete = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:complete")
      __obj.asInstanceOf[typings.node.anon.DataTestComplete]
    }
    
    inline def DataTestDequeue(data: TestDequeue): typings.node.anon.DataTestDequeue = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:dequeue")
      __obj.asInstanceOf[typings.node.anon.DataTestDequeue]
    }
    
    inline def DataTestEnqueue(data: TestEnqueue): typings.node.anon.DataTestEnqueue = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:enqueue")
      __obj.asInstanceOf[typings.node.anon.DataTestEnqueue]
    }
    
    inline def DataTestFail(data: TestFail): typings.node.anon.DataTestFail = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:fail")
      __obj.asInstanceOf[typings.node.anon.DataTestFail]
    }
    
    inline def DataTestPass(data: TestPass): typings.node.anon.DataTestPass = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:pass")
      __obj.asInstanceOf[typings.node.anon.DataTestPass]
    }
    
    inline def DataTestPlan(data: TestPlan): typings.node.anon.DataTestPlan = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:plan")
      __obj.asInstanceOf[typings.node.anon.DataTestPlan]
    }
    
    inline def DataTestStart(data: TestStart): typings.node.anon.DataTestStart = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:start")
      __obj.asInstanceOf[typings.node.anon.DataTestStart]
    }
    
    inline def DataTestStderr(data: TestStderr): typings.node.anon.DataTestStderr = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:stderr")
      __obj.asInstanceOf[typings.node.anon.DataTestStderr]
    }
    
    inline def DataTestStdout(data: TestStdout): typings.node.anon.DataTestStdout = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:stdout")
      __obj.asInstanceOf[typings.node.anon.DataTestStdout]
    }
    
    inline def DataType(data: TestCoverage): typings.node.anon.DataType = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:coverage")
      __obj.asInstanceOf[typings.node.anon.DataType]
    }
    
    inline def DataUndefined(data: Unit): typings.node.anon.DataUndefined = {
      val __obj = js.Dynamic.literal(data = data.asInstanceOf[js.Any])
      __obj.updateDynamic("type")("test:watch:drained")
      __obj.asInstanceOf[typings.node.anon.DataUndefined]
    }
  }
  
  type TestEventGenerator = /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncGenerator<TestEvent, void> */ Any
}

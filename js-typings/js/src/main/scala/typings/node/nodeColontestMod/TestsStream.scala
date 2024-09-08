package typings.node.nodeColontestMod

import typings.node.DiagnosticData
import typings.node.TestComplete
import typings.node.TestCoverage
import typings.node.TestDequeue
import typings.node.TestEnqueue
import typings.node.TestFail
import typings.node.TestPass
import typings.node.TestPlan
import typings.node.TestStart
import typings.node.TestStderr
import typings.node.TestStdout
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.nodeStrings.testColoncomplete
import typings.node.nodeStrings.testColoncoverage
import typings.node.nodeStrings.testColondequeue
import typings.node.nodeStrings.testColondiagnostic
import typings.node.nodeStrings.testColonenqueue
import typings.node.nodeStrings.testColonfail
import typings.node.nodeStrings.testColonpass
import typings.node.nodeStrings.testColonplan
import typings.node.nodeStrings.testColonstart
import typings.node.nodeStrings.testColonstderr
import typings.node.nodeStrings.testColonstdout
import typings.node.nodeStrings.testColonwatchColondrained
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * A successful call to `run()` will return a new `TestsStream` object, streaming a series of events representing the execution of the tests.
  *
  * Some of the events are guaranteed to be emitted in the same order as the tests are defined, while others are emitted in the order that the tests execute.
  * @since v18.9.0, v16.19.0
  */
@js.native
trait TestsStream
  extends StObject
     with ReadableStream {
  
  def addListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testcomplete(event: testColoncomplete, listener: js.Function1[/* data */ TestComplete, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testcoverage(event: testColoncoverage, listener: js.Function1[/* data */ TestCoverage, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testdequeue(event: testColondequeue, listener: js.Function1[/* data */ TestDequeue, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testdiagnostic(event: testColondiagnostic, listener: js.Function1[/* data */ DiagnosticData, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testenqueue(event: testColonenqueue, listener: js.Function1[/* data */ TestEnqueue, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testfail(event: testColonfail, listener: js.Function1[/* data */ TestFail, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testpass(event: testColonpass, listener: js.Function1[/* data */ TestPass, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testplan(event: testColonplan, listener: js.Function1[/* data */ TestPlan, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_teststart(event: testColonstart, listener: js.Function1[/* data */ TestStart, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_teststderr(event: testColonstderr, listener: js.Function1[/* data */ TestStderr, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_teststdout(event: testColonstdout, listener: js.Function1[/* data */ TestStdout, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_testwatchdrained(event: testColonwatchColondrained, listener: js.Function0[Unit]): this.type = js.native
  
  def emit(event: String, args: Any*): Boolean = js.native
  def emit(event: js.Symbol, args: Any*): Boolean = js.native
  @JSName("emit")
  def emit_testcomplete(event: testColoncomplete, data: TestComplete): Boolean = js.native
  @JSName("emit")
  def emit_testcoverage(event: testColoncoverage, data: TestCoverage): Boolean = js.native
  @JSName("emit")
  def emit_testdequeue(event: testColondequeue, data: TestDequeue): Boolean = js.native
  @JSName("emit")
  def emit_testdiagnostic(event: testColondiagnostic, data: DiagnosticData): Boolean = js.native
  @JSName("emit")
  def emit_testenqueue(event: testColonenqueue, data: TestEnqueue): Boolean = js.native
  @JSName("emit")
  def emit_testfail(event: testColonfail, data: TestFail): Boolean = js.native
  @JSName("emit")
  def emit_testpass(event: testColonpass, data: TestPass): Boolean = js.native
  @JSName("emit")
  def emit_testplan(event: testColonplan, data: TestPlan): Boolean = js.native
  @JSName("emit")
  def emit_teststart(event: testColonstart, data: TestStart): Boolean = js.native
  @JSName("emit")
  def emit_teststderr(event: testColonstderr, data: TestStderr): Boolean = js.native
  @JSName("emit")
  def emit_teststdout(event: testColonstdout, data: TestStdout): Boolean = js.native
  @JSName("emit")
  def emit_testwatchdrained(event: testColonwatchColondrained): Boolean = js.native
  
  def on(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("on")
  def on_testcomplete(event: testColoncomplete, listener: js.Function1[/* data */ TestComplete, Unit]): this.type = js.native
  @JSName("on")
  def on_testcoverage(event: testColoncoverage, listener: js.Function1[/* data */ TestCoverage, Unit]): this.type = js.native
  @JSName("on")
  def on_testdequeue(event: testColondequeue, listener: js.Function1[/* data */ TestDequeue, Unit]): this.type = js.native
  @JSName("on")
  def on_testdiagnostic(event: testColondiagnostic, listener: js.Function1[/* data */ DiagnosticData, Unit]): this.type = js.native
  @JSName("on")
  def on_testenqueue(event: testColonenqueue, listener: js.Function1[/* data */ TestEnqueue, Unit]): this.type = js.native
  @JSName("on")
  def on_testfail(event: testColonfail, listener: js.Function1[/* data */ TestFail, Unit]): this.type = js.native
  @JSName("on")
  def on_testpass(event: testColonpass, listener: js.Function1[/* data */ TestPass, Unit]): this.type = js.native
  @JSName("on")
  def on_testplan(event: testColonplan, listener: js.Function1[/* data */ TestPlan, Unit]): this.type = js.native
  @JSName("on")
  def on_teststart(event: testColonstart, listener: js.Function1[/* data */ TestStart, Unit]): this.type = js.native
  @JSName("on")
  def on_teststderr(event: testColonstderr, listener: js.Function1[/* data */ TestStderr, Unit]): this.type = js.native
  @JSName("on")
  def on_teststdout(event: testColonstdout, listener: js.Function1[/* data */ TestStdout, Unit]): this.type = js.native
  @JSName("on")
  def on_testwatchdrained(event: testColonwatchColondrained, listener: js.Function0[Unit]): this.type = js.native
  
  def once(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("once")
  def once_testcomplete(event: testColoncomplete, listener: js.Function1[/* data */ TestComplete, Unit]): this.type = js.native
  @JSName("once")
  def once_testcoverage(event: testColoncoverage, listener: js.Function1[/* data */ TestCoverage, Unit]): this.type = js.native
  @JSName("once")
  def once_testdequeue(event: testColondequeue, listener: js.Function1[/* data */ TestDequeue, Unit]): this.type = js.native
  @JSName("once")
  def once_testdiagnostic(event: testColondiagnostic, listener: js.Function1[/* data */ DiagnosticData, Unit]): this.type = js.native
  @JSName("once")
  def once_testenqueue(event: testColonenqueue, listener: js.Function1[/* data */ TestEnqueue, Unit]): this.type = js.native
  @JSName("once")
  def once_testfail(event: testColonfail, listener: js.Function1[/* data */ TestFail, Unit]): this.type = js.native
  @JSName("once")
  def once_testpass(event: testColonpass, listener: js.Function1[/* data */ TestPass, Unit]): this.type = js.native
  @JSName("once")
  def once_testplan(event: testColonplan, listener: js.Function1[/* data */ TestPlan, Unit]): this.type = js.native
  @JSName("once")
  def once_teststart(event: testColonstart, listener: js.Function1[/* data */ TestStart, Unit]): this.type = js.native
  @JSName("once")
  def once_teststderr(event: testColonstderr, listener: js.Function1[/* data */ TestStderr, Unit]): this.type = js.native
  @JSName("once")
  def once_teststdout(event: testColonstdout, listener: js.Function1[/* data */ TestStdout, Unit]): this.type = js.native
  @JSName("once")
  def once_testwatchdrained(event: testColonwatchColondrained, listener: js.Function0[Unit]): this.type = js.native
  
  def prependListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testcomplete(event: testColoncomplete, listener: js.Function1[/* data */ TestComplete, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testcoverage(event: testColoncoverage, listener: js.Function1[/* data */ TestCoverage, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testdequeue(event: testColondequeue, listener: js.Function1[/* data */ TestDequeue, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testdiagnostic(event: testColondiagnostic, listener: js.Function1[/* data */ DiagnosticData, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testenqueue(event: testColonenqueue, listener: js.Function1[/* data */ TestEnqueue, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testfail(event: testColonfail, listener: js.Function1[/* data */ TestFail, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testpass(event: testColonpass, listener: js.Function1[/* data */ TestPass, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testplan(event: testColonplan, listener: js.Function1[/* data */ TestPlan, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_teststart(event: testColonstart, listener: js.Function1[/* data */ TestStart, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_teststderr(event: testColonstderr, listener: js.Function1[/* data */ TestStderr, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_teststdout(event: testColonstdout, listener: js.Function1[/* data */ TestStdout, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_testwatchdrained(event: testColonwatchColondrained, listener: js.Function0[Unit]): this.type = js.native
  
  def prependOnceListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testcomplete(event: testColoncomplete, listener: js.Function1[/* data */ TestComplete, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testcoverage(event: testColoncoverage, listener: js.Function1[/* data */ TestCoverage, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testdequeue(event: testColondequeue, listener: js.Function1[/* data */ TestDequeue, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testdiagnostic(event: testColondiagnostic, listener: js.Function1[/* data */ DiagnosticData, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testenqueue(event: testColonenqueue, listener: js.Function1[/* data */ TestEnqueue, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testfail(event: testColonfail, listener: js.Function1[/* data */ TestFail, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testpass(event: testColonpass, listener: js.Function1[/* data */ TestPass, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testplan(event: testColonplan, listener: js.Function1[/* data */ TestPlan, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_teststart(event: testColonstart, listener: js.Function1[/* data */ TestStart, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_teststderr(event: testColonstderr, listener: js.Function1[/* data */ TestStderr, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_teststdout(event: testColonstdout, listener: js.Function1[/* data */ TestStdout, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_testwatchdrained(event: testColonwatchColondrained, listener: js.Function0[Unit]): this.type = js.native
}

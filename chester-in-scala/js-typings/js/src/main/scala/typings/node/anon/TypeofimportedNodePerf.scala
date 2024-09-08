package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.perfHooksMod.CreateHistogramOptions
import typings.node.perfHooksMod.EventLoopMonitorOptions
import typings.node.perfHooksMod.IntervalHistogram
import typings.node.perfHooksMod.PerformanceObserverCallback
import typings.node.perfHooksMod.Performance_
import typings.node.perfHooksMod.RecordableHistogram
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodePerf extends StObject {
  
  /**
    * The constructor of this class is not exposed to users directly.
    * @since v8.5.0
    */
  var PerformanceEntry: Instantiable0[typings.node.nodeColonperfHooksMod.PerformanceEntry] = js.native
  
  /**
    * Exposes marks created via the `Performance.mark()` method.
    * @since v18.2.0, v16.17.0
    */
  var PerformanceMark: Instantiable0[typings.node.nodeColonperfHooksMod.PerformanceMark] = js.native
  
  /**
    * Exposes measures created via the `Performance.measure()` method.
    *
    * The constructor of this class is not exposed to users directly.
    * @since v18.2.0, v16.17.0
    */
  var PerformanceMeasure: Instantiable0[typings.node.nodeColonperfHooksMod.PerformanceMeasure] = js.native
  
  /**
    * _This property is an extension by Node.js. It is not available in Web browsers._
    *
    * Provides timing details for Node.js itself. The constructor of this class
    * is not exposed to users.
    * @since v8.5.0
    */
  var PerformanceNodeTiming: Instantiable0[typings.node.nodeColonperfHooksMod.PerformanceNodeTiming] = js.native
  
  /**
    * @since v8.5.0
    */
  var PerformanceObserver: Instantiable1[
    /* callback */ PerformanceObserverCallback, 
    typings.node.nodeColonperfHooksMod.PerformanceObserver
  ] = js.native
  
  var PerformanceObserverEntryList: Instantiable0[typings.node.nodeColonperfHooksMod.PerformanceObserverEntryList] = js.native
  
  /**
    * Provides detailed network timing data regarding the loading of an application's resources.
    *
    * The constructor of this class is not exposed to users directly.
    * @since v18.2.0, v16.17.0
    */
  var PerformanceResourceTiming: Instantiable0[typings.node.nodeColonperfHooksMod.PerformanceResourceTiming] = js.native
  
  val constants: TypeofconstantsNODEPERFORMANCEGCFLAGSALLAVAILABLEGARBAGE = js.native
  
  /**
    * Returns a `RecordableHistogram`.
    * @since v15.9.0, v14.18.0
    */
  def createHistogram(): RecordableHistogram = js.native
  def createHistogram(options: CreateHistogramOptions): RecordableHistogram = js.native
  
  /**
    * _This property is an extension by Node.js. It is not available in Web browsers._
    *
    * Creates an `IntervalHistogram` object that samples and reports the event loop
    * delay over time. The delays will be reported in nanoseconds.
    *
    * Using a timer to detect approximate event loop delay works because the
    * execution of timers is tied specifically to the lifecycle of the libuv
    * event loop. That is, a delay in the loop will cause a delay in the execution
    * of the timer, and those delays are specifically what this API is intended to
    * detect.
    *
    * ```js
    * const { monitorEventLoopDelay } = require('node:perf_hooks');
    * const h = monitorEventLoopDelay({ resolution: 20 });
    * h.enable();
    * // Do something.
    * h.disable();
    * console.log(h.min);
    * console.log(h.max);
    * console.log(h.mean);
    * console.log(h.stddev);
    * console.log(h.percentiles);
    * console.log(h.percentile(50));
    * console.log(h.percentile(99));
    * ```
    * @since v11.10.0
    */
  def monitorEventLoopDelay(): IntervalHistogram = js.native
  def monitorEventLoopDelay(options: EventLoopMonitorOptions): IntervalHistogram = js.native
  
  val performance: Performance_ = js.native
}

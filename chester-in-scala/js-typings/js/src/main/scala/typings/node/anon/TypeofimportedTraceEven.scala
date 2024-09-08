package typings.node.anon

import typings.node.traceEventsMod.CreateTracingOptions
import typings.node.traceEventsMod.Tracing
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedTraceEven extends StObject {
  
  /**
    * Creates and returns a `Tracing` object for the given set of `categories`.
    *
    * ```js
    * const trace_events = require('node:trace_events');
    * const categories = ['node.perf', 'node.async_hooks'];
    * const tracing = trace_events.createTracing({ categories });
    * tracing.enable();
    * // do stuff
    * tracing.disable();
    * ```
    * @since v10.0.0
    */
  def createTracing(options: CreateTracingOptions): Tracing
  
  /**
    * Returns a comma-separated list of all currently-enabled trace event
    * categories. The current set of enabled trace event categories is determined
    * by the _union_ of all currently-enabled `Tracing` objects and any categories
    * enabled using the `--trace-event-categories` flag.
    *
    * Given the file `test.js` below, the command `node --trace-event-categories node.perf test.js` will print `'node.async_hooks,node.perf'` to the console.
    *
    * ```js
    * const trace_events = require('node:trace_events');
    * const t1 = trace_events.createTracing({ categories: ['node.async_hooks'] });
    * const t2 = trace_events.createTracing({ categories: ['node.perf'] });
    * const t3 = trace_events.createTracing({ categories: ['v8'] });
    *
    * t1.enable();
    * t2.enable();
    *
    * console.log(trace_events.getEnabledCategories());
    * ```
    * @since v10.0.0
    */
  def getEnabledCategories(): js.UndefOr[String]
}
object TypeofimportedTraceEven {
  
  inline def apply(createTracing: CreateTracingOptions => Tracing, getEnabledCategories: () => js.UndefOr[String]): TypeofimportedTraceEven = {
    val __obj = js.Dynamic.literal(createTracing = js.Any.fromFunction1(createTracing), getEnabledCategories = js.Any.fromFunction0(getEnabledCategories))
    __obj.asInstanceOf[TypeofimportedTraceEven]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedTraceEven] (val x: Self) extends AnyVal {
    
    inline def setCreateTracing(value: CreateTracingOptions => Tracing): Self = StObject.set(x, "createTracing", js.Any.fromFunction1(value))
    
    inline def setGetEnabledCategories(value: () => js.UndefOr[String]): Self = StObject.set(x, "getEnabledCategories", js.Any.fromFunction0(value))
  }
}

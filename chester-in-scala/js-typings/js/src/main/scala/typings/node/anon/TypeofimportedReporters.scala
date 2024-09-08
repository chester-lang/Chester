package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.nodeColontestReportersMod.TestEventGenerator
import typings.node.nodeColontestReportersMod.lcov
import typings.node.nodeColontestReportersMod.spec
import typings.node.streamMod.TransformOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedReporters extends StObject {
  
  /**
    * The `dot` reporter outputs the test results in a compact format,
    * where each passing test is represented by a `.`,
    * and each failing test is represented by a `X`.
    */
  def dot(source: TestEventGenerator): Any
  
  /**
    * The `junit` reporter outputs test results in a jUnit XML format.
    */
  def junit(source: TestEventGenerator): Any
  
  /**
    * The `lcov` reporter outputs test coverage when used with the [`--experimental-test-coverage`](https://nodejs.org/docs/latest-v22.x/api/cli.html#--experimental-test-coverage) flag.
    */
  var lcov: Instantiable1[
    /* opts */ js.UndefOr[TransformOptions], 
    typings.node.nodeColontestReportersMod.lcov
  ]
  
  /**
    * The `spec` reporter outputs the test results in a human-readable format.
    */
  var spec: Instantiable0[typings.node.nodeColontestReportersMod.spec]
  
  /**
    * The `tap` reporter outputs the test results in the [TAP](https://testanything.org/) format.
    */
  def tap(source: TestEventGenerator): Any
}
object TypeofimportedReporters {
  
  inline def apply(
    dot: TestEventGenerator => Any,
    junit: TestEventGenerator => Any,
    lcov: Instantiable1[/* opts */ js.UndefOr[TransformOptions], lcov],
    spec: Instantiable0[spec],
    tap: TestEventGenerator => Any
  ): TypeofimportedReporters = {
    val __obj = js.Dynamic.literal(dot = js.Any.fromFunction1(dot), junit = js.Any.fromFunction1(junit), lcov = lcov.asInstanceOf[js.Any], spec = spec.asInstanceOf[js.Any], tap = js.Any.fromFunction1(tap))
    __obj.asInstanceOf[TypeofimportedReporters]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedReporters] (val x: Self) extends AnyVal {
    
    inline def setDot(value: TestEventGenerator => Any): Self = StObject.set(x, "dot", js.Any.fromFunction1(value))
    
    inline def setJunit(value: TestEventGenerator => Any): Self = StObject.set(x, "junit", js.Any.fromFunction1(value))
    
    inline def setLcov(value: Instantiable1[/* opts */ js.UndefOr[TransformOptions], lcov]): Self = StObject.set(x, "lcov", value.asInstanceOf[js.Any])
    
    inline def setSpec(value: Instantiable0[spec]): Self = StObject.set(x, "spec", value.asInstanceOf[js.Any])
    
    inline def setTap(value: TestEventGenerator => Any): Self = StObject.set(x, "tap", js.Any.fromFunction1(value))
  }
}

package typings.node.nodeColontestMod

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * An instance of `SuiteContext` is passed to each suite function in order to
  * interact with the test runner. However, the `SuiteContext` constructor is not
  * exposed as part of the API.
  * @since v18.7.0, v16.17.0
  */
@JSImport("node:test", "SuiteContext")
@js.native
open class SuiteContext () extends StObject {
  
  /**
    * The name of the suite.
    * @since v18.8.0, v16.18.0
    */
  val name: String = js.native
  
  /**
    * Can be used to abort test subtasks when the test has been aborted.
    * @since v18.7.0, v16.17.0
    */
  val signal: AbortSignal = js.native
}

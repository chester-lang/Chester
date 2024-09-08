package typings.node.vmMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * Returns an object containing commonly used constants for VM operations.
  * @since v20.12.0
  */
object constants {
  
  /**
    * Stability: 1.1 - Active development
    *
    * A constant that can be used as the `importModuleDynamically` option to `vm.Script`
    * and `vm.compileFunction()` so that Node.js uses the default ESM loader from the main
    * context to load the requested module.
    *
    * For detailed information, see [Support of dynamic `import()` in compilation APIs](https://nodejs.org/docs/latest-v22.x/api/vm.html#support-of-dynamic-import-in-compilation-apis).
    */
  @JSImport("vm", "constants.USE_MAIN_CONTEXT_DEFAULT_LOADER")
  @js.native
  val USE_MAIN_CONTEXT_DEFAULT_LOADER: Double = js.native
}

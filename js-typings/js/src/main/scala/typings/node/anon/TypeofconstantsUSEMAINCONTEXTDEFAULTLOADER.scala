package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofconstantsUSEMAINCONTEXTDEFAULTLOADER extends StObject {
  
  /**
    * Stability: 1.1 - Active development
    *
    * A constant that can be used as the `importModuleDynamically` option to `vm.Script`
    * and `vm.compileFunction()` so that Node.js uses the default ESM loader from the main
    * context to load the requested module.
    *
    * For detailed information, see [Support of dynamic `import()` in compilation APIs](https://nodejs.org/docs/latest-v22.x/api/vm.html#support-of-dynamic-import-in-compilation-apis).
    */
  val USE_MAIN_CONTEXT_DEFAULT_LOADER: Double
}
object TypeofconstantsUSEMAINCONTEXTDEFAULTLOADER {
  
  inline def apply(USE_MAIN_CONTEXT_DEFAULT_LOADER: Double): TypeofconstantsUSEMAINCONTEXTDEFAULTLOADER = {
    val __obj = js.Dynamic.literal(USE_MAIN_CONTEXT_DEFAULT_LOADER = USE_MAIN_CONTEXT_DEFAULT_LOADER.asInstanceOf[js.Any])
    __obj.asInstanceOf[TypeofconstantsUSEMAINCONTEXTDEFAULTLOADER]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofconstantsUSEMAINCONTEXTDEFAULTLOADER] (val x: Self) extends AnyVal {
    
    inline def setUSE_MAIN_CONTEXT_DEFAULT_LOADER(value: Double): Self = StObject.set(x, "USE_MAIN_CONTEXT_DEFAULT_LOADER", value.asInstanceOf[js.Any])
  }
}

package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MockModuleOptions extends StObject {
  
  /**
    * If false, each call to `require()` or `import()` generates a new mock module.
    * If true, subsequent calls will return the same module mock, and the mock module is inserted into the CommonJS cache.
    * @default false
    */
  var cache: js.UndefOr[Boolean] = js.undefined
  
  /**
    * The value to use as the mocked module's default export.
    *
    * If this value is not provided, ESM mocks do not include a default export.
    * If the mock is a CommonJS or builtin module, this setting is used as the value of `module.exports`.
    * If this value is not provided, CJS and builtin mocks use an empty object as the value of `module.exports`.
    */
  var defaultExport: js.UndefOr[Any] = js.undefined
  
  /**
    * An object whose keys and values are used to create the named exports of the mock module.
    *
    * If the mock is a CommonJS or builtin module, these values are copied onto `module.exports`.
    * Therefore, if a mock is created with both named exports and a non-object default export,
    * the mock will throw an exception when used as a CJS or builtin module.
    */
  var namedExports: js.UndefOr[js.Object] = js.undefined
}
object MockModuleOptions {
  
  inline def apply(): MockModuleOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[MockModuleOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MockModuleOptions] (val x: Self) extends AnyVal {
    
    inline def setCache(value: Boolean): Self = StObject.set(x, "cache", value.asInstanceOf[js.Any])
    
    inline def setCacheUndefined: Self = StObject.set(x, "cache", js.undefined)
    
    inline def setDefaultExport(value: Any): Self = StObject.set(x, "defaultExport", value.asInstanceOf[js.Any])
    
    inline def setDefaultExportUndefined: Self = StObject.set(x, "defaultExport", js.undefined)
    
    inline def setNamedExports(value: js.Object): Self = StObject.set(x, "namedExports", value.asInstanceOf[js.Any])
    
    inline def setNamedExportsUndefined: Self = StObject.set(x, "namedExports", js.undefined)
  }
}

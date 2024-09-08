package typings.node.vmMod

import typings.node.bufferMod.global.Buffer
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import typings.node.moduleMod.ImportAttributes
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ScriptOptions
  extends StObject
     with BaseOptions {
  
  /**
    * V8's code cache data for the supplied source.
    */
  var cachedData: js.UndefOr[Buffer | ArrayBufferView] = js.undefined
  
  /**
    * Used to specify how the modules should be loaded during the evaluation of this script when `import()` is called. This option is
    * part of the experimental modules API. We do not recommend using it in a production environment. For detailed information, see
    * [Support of dynamic `import()` in compilation APIs](https://nodejs.org/docs/latest-v22.x/api/vm.html#support-of-dynamic-import-in-compilation-apis).
    */
  var importModuleDynamically: js.UndefOr[
    (js.Function3[
      /* specifier */ String, 
      /* script */ Script, 
      /* importAttributes */ ImportAttributes, 
      Module
    ]) | Double
  ] = js.undefined
  
  /** @deprecated in favor of `script.createCachedData()` */
  var produceCachedData: js.UndefOr[Boolean] = js.undefined
}
object ScriptOptions {
  
  inline def apply(): ScriptOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ScriptOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ScriptOptions] (val x: Self) extends AnyVal {
    
    inline def setCachedData(value: Buffer | ArrayBufferView): Self = StObject.set(x, "cachedData", value.asInstanceOf[js.Any])
    
    inline def setCachedDataUndefined: Self = StObject.set(x, "cachedData", js.undefined)
    
    inline def setImportModuleDynamically(
      value: (js.Function3[
          /* specifier */ String, 
          /* script */ Script, 
          /* importAttributes */ ImportAttributes, 
          Module
        ]) | Double
    ): Self = StObject.set(x, "importModuleDynamically", value.asInstanceOf[js.Any])
    
    inline def setImportModuleDynamicallyFunction3(
      value: (/* specifier */ String, /* script */ Script, /* importAttributes */ ImportAttributes) => Module
    ): Self = StObject.set(x, "importModuleDynamically", js.Any.fromFunction3(value))
    
    inline def setImportModuleDynamicallyUndefined: Self = StObject.set(x, "importModuleDynamically", js.undefined)
    
    inline def setProduceCachedData(value: Boolean): Self = StObject.set(x, "produceCachedData", value.asInstanceOf[js.Any])
    
    inline def setProduceCachedDataUndefined: Self = StObject.set(x, "produceCachedData", js.undefined)
  }
}

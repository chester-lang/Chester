package typings.node.vmMod

import typings.node.bufferMod.global.Buffer
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import typings.node.moduleMod.ImportAttributes
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RunningCodeInNewContextOptions
  extends StObject
     with RunningScriptInNewContextOptions {
  
  var cachedData: js.UndefOr[Buffer | ArrayBufferView] = js.undefined
  
  var importModuleDynamically: js.UndefOr[
    (js.Function3[
      /* specifier */ String, 
      /* script */ Script, 
      /* importAttributes */ ImportAttributes, 
      Module
    ]) | Double
  ] = js.undefined
}
object RunningCodeInNewContextOptions {
  
  inline def apply(): RunningCodeInNewContextOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RunningCodeInNewContextOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: RunningCodeInNewContextOptions] (val x: Self) extends AnyVal {
    
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
  }
}

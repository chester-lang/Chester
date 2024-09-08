package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import typings.node.nodeColonwasiMod.WASI
import typings.node.wasiMod.WASIOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedNodeWasi extends StObject {
  
  /**
    * The `WASI` class provides the WASI system call API and additional convenience
    * methods for working with WASI-based applications. Each `WASI` instance
    * represents a distinct environment.
    * @since v13.3.0, v12.16.0
    */
  var WASI: Instantiable1[/* options */ js.UndefOr[WASIOptions], typings.node.nodeColonwasiMod.WASI]
}
object TypeofimportedNodeWasi {
  
  inline def apply(WASI: Instantiable1[/* options */ js.UndefOr[WASIOptions], WASI]): TypeofimportedNodeWasi = {
    val __obj = js.Dynamic.literal(WASI = WASI.asInstanceOf[js.Any])
    __obj.asInstanceOf[TypeofimportedNodeWasi]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedNodeWasi] (val x: Self) extends AnyVal {
    
    inline def setWASI(value: Instantiable1[/* options */ js.UndefOr[WASIOptions], WASI]): Self = StObject.set(x, "WASI", value.asInstanceOf[js.Any])
  }
}

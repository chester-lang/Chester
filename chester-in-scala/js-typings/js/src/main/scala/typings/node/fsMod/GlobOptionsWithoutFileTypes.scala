package typings.node.fsMod

import typings.node.nodeBooleans.`false`
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait GlobOptionsWithoutFileTypes
  extends StObject
     with GlobOptions {
  
  @JSName("withFileTypes")
  var withFileTypes_GlobOptionsWithoutFileTypes: js.UndefOr[`false`] = js.undefined
}
object GlobOptionsWithoutFileTypes {
  
  inline def apply(): GlobOptionsWithoutFileTypes = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[GlobOptionsWithoutFileTypes]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: GlobOptionsWithoutFileTypes] (val x: Self) extends AnyVal {
    
    inline def setWithFileTypes(value: `false`): Self = StObject.set(x, "withFileTypes", value.asInstanceOf[js.Any])
    
    inline def setWithFileTypesUndefined: Self = StObject.set(x, "withFileTypes", js.undefined)
  }
}

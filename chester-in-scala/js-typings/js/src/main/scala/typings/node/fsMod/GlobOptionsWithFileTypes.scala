package typings.node.fsMod

import typings.node.nodeBooleans.`true`
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait GlobOptionsWithFileTypes
  extends StObject
     with GlobOptions {
  
  @JSName("withFileTypes")
  var withFileTypes_GlobOptionsWithFileTypes: `true`
}
object GlobOptionsWithFileTypes {
  
  inline def apply(): GlobOptionsWithFileTypes = {
    val __obj = js.Dynamic.literal(withFileTypes = true)
    __obj.asInstanceOf[GlobOptionsWithFileTypes]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: GlobOptionsWithFileTypes] (val x: Self) extends AnyVal {
    
    inline def setWithFileTypes(value: `true`): Self = StObject.set(x, "withFileTypes", value.asInstanceOf[js.Any])
  }
}

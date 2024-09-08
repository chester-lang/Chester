package typings.node.fsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait OpenAsBlobOptions extends StObject {
  
  /**
    * An optional mime type for the blob.
    *
    * @default 'undefined'
    */
  var `type`: js.UndefOr[String] = js.undefined
}
object OpenAsBlobOptions {
  
  inline def apply(): OpenAsBlobOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[OpenAsBlobOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: OpenAsBlobOptions] (val x: Self) extends AnyVal {
    
    inline def setType(value: String): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
    
    inline def setTypeUndefined: Self = StObject.set(x, "type", js.undefined)
  }
}

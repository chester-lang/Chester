package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait HTMLElementDeprecatedTagNameMap extends StObject {
  
  /* standard dom */
  var listing: org.scalajs.dom.HTMLPreElement
  
  /* standard dom */
  var xmp: org.scalajs.dom.HTMLPreElement
}
object HTMLElementDeprecatedTagNameMap {
  
  inline def apply(listing: org.scalajs.dom.HTMLPreElement, xmp: org.scalajs.dom.HTMLPreElement): HTMLElementDeprecatedTagNameMap = {
    val __obj = js.Dynamic.literal(listing = listing.asInstanceOf[js.Any], xmp = xmp.asInstanceOf[js.Any])
    __obj.asInstanceOf[HTMLElementDeprecatedTagNameMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: HTMLElementDeprecatedTagNameMap] (val x: Self) extends AnyVal {
    
    inline def setListing(value: org.scalajs.dom.HTMLPreElement): Self = StObject.set(x, "listing", value.asInstanceOf[js.Any])
    
    inline def setXmp(value: org.scalajs.dom.HTMLPreElement): Self = StObject.set(x, "xmp", value.asInstanceOf[js.Any])
  }
}

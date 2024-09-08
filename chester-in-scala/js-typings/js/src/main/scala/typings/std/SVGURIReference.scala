package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait SVGURIReference extends StObject {
  
  /* standard dom */
  val href: org.scalajs.dom.SVGAnimatedString
}
object SVGURIReference {
  
  inline def apply(href: org.scalajs.dom.SVGAnimatedString): SVGURIReference = {
    val __obj = js.Dynamic.literal(href = href.asInstanceOf[js.Any])
    __obj.asInstanceOf[SVGURIReference]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: SVGURIReference] (val x: Self) extends AnyVal {
    
    inline def setHref(value: org.scalajs.dom.SVGAnimatedString): Self = StObject.set(x, "href", value.asInstanceOf[js.Any])
  }
}

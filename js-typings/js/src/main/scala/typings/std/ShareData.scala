package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ShareData extends StObject {
  
  /* standard dom */
  var text: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var title: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var url: js.UndefOr[java.lang.String] = js.undefined
}
object ShareData {
  
  inline def apply(): ShareData = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ShareData]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ShareData] (val x: Self) extends AnyVal {
    
    inline def setText(value: java.lang.String): Self = StObject.set(x, "text", value.asInstanceOf[js.Any])
    
    inline def setTextUndefined: Self = StObject.set(x, "text", js.undefined)
    
    inline def setTitle(value: java.lang.String): Self = StObject.set(x, "title", value.asInstanceOf[js.Any])
    
    inline def setTitleUndefined: Self = StObject.set(x, "title", js.undefined)
    
    inline def setUrl(value: java.lang.String): Self = StObject.set(x, "url", value.asInstanceOf[js.Any])
    
    inline def setUrlUndefined: Self = StObject.set(x, "url", js.undefined)
  }
}

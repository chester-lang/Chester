package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait NavigatorPlugins extends StObject {
  
  /* standard dom */
  def javaEnabled(): scala.Boolean
  
  /* standard dom */
  val mimeTypes: MimeTypeArray
  
  /* standard dom */
  val plugins: PluginArray
}
object NavigatorPlugins {
  
  inline def apply(javaEnabled: () => scala.Boolean, mimeTypes: MimeTypeArray, plugins: PluginArray): NavigatorPlugins = {
    val __obj = js.Dynamic.literal(javaEnabled = js.Any.fromFunction0(javaEnabled), mimeTypes = mimeTypes.asInstanceOf[js.Any], plugins = plugins.asInstanceOf[js.Any])
    __obj.asInstanceOf[NavigatorPlugins]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: NavigatorPlugins] (val x: Self) extends AnyVal {
    
    inline def setJavaEnabled(value: () => scala.Boolean): Self = StObject.set(x, "javaEnabled", js.Any.fromFunction0(value))
    
    inline def setMimeTypes(value: MimeTypeArray): Self = StObject.set(x, "mimeTypes", value.asInstanceOf[js.Any])
    
    inline def setPlugins(value: PluginArray): Self = StObject.set(x, "plugins", value.asInstanceOf[js.Any])
  }
}

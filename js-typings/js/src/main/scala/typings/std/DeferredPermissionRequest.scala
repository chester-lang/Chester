package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DeferredPermissionRequest extends StObject {
  
  /* standard dom */
  def allow(): Unit
  
  /* standard dom */
  def deny(): Unit
  
  /* standard dom */
  val id: Double
  
  /* standard dom */
  val `type`: MSWebViewPermissionType
  
  /* standard dom */
  val uri: java.lang.String
}
object DeferredPermissionRequest {
  
  inline def apply(
    allow: () => Unit,
    deny: () => Unit,
    id: Double,
    `type`: MSWebViewPermissionType,
    uri: java.lang.String
  ): DeferredPermissionRequest = {
    val __obj = js.Dynamic.literal(allow = js.Any.fromFunction0(allow), deny = js.Any.fromFunction0(deny), id = id.asInstanceOf[js.Any], uri = uri.asInstanceOf[js.Any])
    __obj.updateDynamic("type")(`type`.asInstanceOf[js.Any])
    __obj.asInstanceOf[DeferredPermissionRequest]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DeferredPermissionRequest] (val x: Self) extends AnyVal {
    
    inline def setAllow(value: () => Unit): Self = StObject.set(x, "allow", js.Any.fromFunction0(value))
    
    inline def setDeny(value: () => Unit): Self = StObject.set(x, "deny", js.Any.fromFunction0(value))
    
    inline def setId(value: Double): Self = StObject.set(x, "id", value.asInstanceOf[js.Any])
    
    inline def setType(value: MSWebViewPermissionType): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
    
    inline def setUri(value: java.lang.String): Self = StObject.set(x, "uri", value.asInstanceOf[js.Any])
  }
}

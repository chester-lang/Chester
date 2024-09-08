package typings.undiciTypes.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait AllowH2 extends StObject {
  
  var allowH2: js.UndefOr[Boolean] = js.undefined
  
  var keepAlive: js.UndefOr[Boolean | Null] = js.undefined
  
  var keepAliveInitialDelay: js.UndefOr[Double | Null] = js.undefined
  
  var maxCachedSessions: js.UndefOr[Double | Null] = js.undefined
  
  var port: js.UndefOr[Double] = js.undefined
  
  var socketPath: js.UndefOr[String | Null] = js.undefined
  
  var timeout: js.UndefOr[Double | Null] = js.undefined
}
object AllowH2 {
  
  inline def apply(): AllowH2 = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[AllowH2]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: AllowH2] (val x: Self) extends AnyVal {
    
    inline def setAllowH2(value: Boolean): Self = StObject.set(x, "allowH2", value.asInstanceOf[js.Any])
    
    inline def setAllowH2Undefined: Self = StObject.set(x, "allowH2", js.undefined)
    
    inline def setKeepAlive(value: Boolean): Self = StObject.set(x, "keepAlive", value.asInstanceOf[js.Any])
    
    inline def setKeepAliveInitialDelay(value: Double): Self = StObject.set(x, "keepAliveInitialDelay", value.asInstanceOf[js.Any])
    
    inline def setKeepAliveInitialDelayNull: Self = StObject.set(x, "keepAliveInitialDelay", null)
    
    inline def setKeepAliveInitialDelayUndefined: Self = StObject.set(x, "keepAliveInitialDelay", js.undefined)
    
    inline def setKeepAliveNull: Self = StObject.set(x, "keepAlive", null)
    
    inline def setKeepAliveUndefined: Self = StObject.set(x, "keepAlive", js.undefined)
    
    inline def setMaxCachedSessions(value: Double): Self = StObject.set(x, "maxCachedSessions", value.asInstanceOf[js.Any])
    
    inline def setMaxCachedSessionsNull: Self = StObject.set(x, "maxCachedSessions", null)
    
    inline def setMaxCachedSessionsUndefined: Self = StObject.set(x, "maxCachedSessions", js.undefined)
    
    inline def setPort(value: Double): Self = StObject.set(x, "port", value.asInstanceOf[js.Any])
    
    inline def setPortUndefined: Self = StObject.set(x, "port", js.undefined)
    
    inline def setSocketPath(value: String): Self = StObject.set(x, "socketPath", value.asInstanceOf[js.Any])
    
    inline def setSocketPathNull: Self = StObject.set(x, "socketPath", null)
    
    inline def setSocketPathUndefined: Self = StObject.set(x, "socketPath", js.undefined)
    
    inline def setTimeout(value: Double): Self = StObject.set(x, "timeout", value.asInstanceOf[js.Any])
    
    inline def setTimeoutNull: Self = StObject.set(x, "timeout", null)
    
    inline def setTimeoutUndefined: Self = StObject.set(x, "timeout", js.undefined)
  }
}

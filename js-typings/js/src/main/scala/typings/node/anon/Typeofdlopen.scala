package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Typeofdlopen extends StObject {
  
  val RTLD_DEEPBIND: Double
  
  val RTLD_GLOBAL: Double
  
  val RTLD_LAZY: Double
  
  val RTLD_LOCAL: Double
  
  val RTLD_NOW: Double
}
object Typeofdlopen {
  
  inline def apply(
    RTLD_DEEPBIND: Double,
    RTLD_GLOBAL: Double,
    RTLD_LAZY: Double,
    RTLD_LOCAL: Double,
    RTLD_NOW: Double
  ): Typeofdlopen = {
    val __obj = js.Dynamic.literal(RTLD_DEEPBIND = RTLD_DEEPBIND.asInstanceOf[js.Any], RTLD_GLOBAL = RTLD_GLOBAL.asInstanceOf[js.Any], RTLD_LAZY = RTLD_LAZY.asInstanceOf[js.Any], RTLD_LOCAL = RTLD_LOCAL.asInstanceOf[js.Any], RTLD_NOW = RTLD_NOW.asInstanceOf[js.Any])
    __obj.asInstanceOf[Typeofdlopen]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Typeofdlopen] (val x: Self) extends AnyVal {
    
    inline def setRTLD_DEEPBIND(value: Double): Self = StObject.set(x, "RTLD_DEEPBIND", value.asInstanceOf[js.Any])
    
    inline def setRTLD_GLOBAL(value: Double): Self = StObject.set(x, "RTLD_GLOBAL", value.asInstanceOf[js.Any])
    
    inline def setRTLD_LAZY(value: Double): Self = StObject.set(x, "RTLD_LAZY", value.asInstanceOf[js.Any])
    
    inline def setRTLD_LOCAL(value: Double): Self = StObject.set(x, "RTLD_LOCAL", value.asInstanceOf[js.Any])
    
    inline def setRTLD_NOW(value: Double): Self = StObject.set(x, "RTLD_NOW", value.asInstanceOf[js.Any])
  }
}

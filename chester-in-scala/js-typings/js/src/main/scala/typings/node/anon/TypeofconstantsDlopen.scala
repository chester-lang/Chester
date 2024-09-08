package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofconstantsDlopen extends StObject {
  
  val UV_UDP_REUSEADDR: Double
  
  val dlopen: Typeofdlopen
  
  val errno: Typeoferrno
  
  val priority: Typeofpriority
  
  val signals: Typeofsignals
}
object TypeofconstantsDlopen {
  
  inline def apply(
    UV_UDP_REUSEADDR: Double,
    dlopen: Typeofdlopen,
    errno: Typeoferrno,
    priority: Typeofpriority,
    signals: Typeofsignals
  ): TypeofconstantsDlopen = {
    val __obj = js.Dynamic.literal(UV_UDP_REUSEADDR = UV_UDP_REUSEADDR.asInstanceOf[js.Any], dlopen = dlopen.asInstanceOf[js.Any], errno = errno.asInstanceOf[js.Any], priority = priority.asInstanceOf[js.Any], signals = signals.asInstanceOf[js.Any])
    __obj.asInstanceOf[TypeofconstantsDlopen]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofconstantsDlopen] (val x: Self) extends AnyVal {
    
    inline def setDlopen(value: Typeofdlopen): Self = StObject.set(x, "dlopen", value.asInstanceOf[js.Any])
    
    inline def setErrno(value: Typeoferrno): Self = StObject.set(x, "errno", value.asInstanceOf[js.Any])
    
    inline def setPriority(value: Typeofpriority): Self = StObject.set(x, "priority", value.asInstanceOf[js.Any])
    
    inline def setSignals(value: Typeofsignals): Self = StObject.set(x, "signals", value.asInstanceOf[js.Any])
    
    inline def setUV_UDP_REUSEADDR(value: Double): Self = StObject.set(x, "UV_UDP_REUSEADDR", value.asInstanceOf[js.Any])
  }
}

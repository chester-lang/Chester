package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait `8` extends StObject {
  
  var fd: typings.node.nodeInts.`0`
}
object `8` {
  
  inline def apply(): `8` = {
    val __obj = js.Dynamic.literal(fd = 0)
    __obj.asInstanceOf[`8`]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: `8`] (val x: Self) extends AnyVal {
    
    inline def setFd(value: typings.node.nodeInts.`0`): Self = StObject.set(x, "fd", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait `7` extends StObject {
  
  var fd: typings.node.nodeInts.`2`
}
object `7` {
  
  inline def apply(): `7` = {
    val __obj = js.Dynamic.literal(fd = 2)
    __obj.asInstanceOf[`7`]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: `7`] (val x: Self) extends AnyVal {
    
    inline def setFd(value: typings.node.nodeInts.`2`): Self = StObject.set(x, "fd", value.asInstanceOf[js.Any])
  }
}

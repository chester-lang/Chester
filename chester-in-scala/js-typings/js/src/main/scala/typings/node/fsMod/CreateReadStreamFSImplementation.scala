package typings.node.fsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait CreateReadStreamFSImplementation
  extends StObject
     with FSImplementation {
  
  def read(args: Any*): Any
}
object CreateReadStreamFSImplementation {
  
  inline def apply(read: /* repeated */ Any => Any): CreateReadStreamFSImplementation = {
    val __obj = js.Dynamic.literal(read = js.Any.fromFunction1(read))
    __obj.asInstanceOf[CreateReadStreamFSImplementation]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: CreateReadStreamFSImplementation] (val x: Self) extends AnyVal {
    
    inline def setRead(value: /* repeated */ Any => Any): Self = StObject.set(x, "read", js.Any.fromFunction1(value))
  }
}

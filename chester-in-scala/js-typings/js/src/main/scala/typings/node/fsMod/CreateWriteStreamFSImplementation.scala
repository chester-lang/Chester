package typings.node.fsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait CreateWriteStreamFSImplementation
  extends StObject
     with FSImplementation {
  
  def write(args: Any*): Any
  
  var writev: js.UndefOr[js.Function1[/* repeated */ Any, Any]] = js.undefined
}
object CreateWriteStreamFSImplementation {
  
  inline def apply(write: /* repeated */ Any => Any): CreateWriteStreamFSImplementation = {
    val __obj = js.Dynamic.literal(write = js.Any.fromFunction1(write))
    __obj.asInstanceOf[CreateWriteStreamFSImplementation]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: CreateWriteStreamFSImplementation] (val x: Self) extends AnyVal {
    
    inline def setWrite(value: /* repeated */ Any => Any): Self = StObject.set(x, "write", js.Any.fromFunction1(value))
    
    inline def setWritev(value: /* repeated */ Any => Any): Self = StObject.set(x, "writev", js.Any.fromFunction1(value))
    
    inline def setWritevUndefined: Self = StObject.set(x, "writev", js.undefined)
  }
}

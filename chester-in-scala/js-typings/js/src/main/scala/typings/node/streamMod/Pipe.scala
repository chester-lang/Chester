package typings.node.streamMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Pipe extends StObject {
  
  def close(): Unit
  
  def hasRef(): Boolean
  
  def ref(): Unit
  
  def unref(): Unit
}
object Pipe {
  
  inline def apply(close: () => Unit, hasRef: () => Boolean, ref: () => Unit, unref: () => Unit): Pipe = {
    val __obj = js.Dynamic.literal(close = js.Any.fromFunction0(close), hasRef = js.Any.fromFunction0(hasRef), ref = js.Any.fromFunction0(ref), unref = js.Any.fromFunction0(unref))
    __obj.asInstanceOf[Pipe]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Pipe] (val x: Self) extends AnyVal {
    
    inline def setClose(value: () => Unit): Self = StObject.set(x, "close", js.Any.fromFunction0(value))
    
    inline def setHasRef(value: () => Boolean): Self = StObject.set(x, "hasRef", js.Any.fromFunction0(value))
    
    inline def setRef(value: () => Unit): Self = StObject.set(x, "ref", js.Any.fromFunction0(value))
    
    inline def setUnref(value: () => Unit): Self = StObject.set(x, "unref", js.Any.fromFunction0(value))
  }
}

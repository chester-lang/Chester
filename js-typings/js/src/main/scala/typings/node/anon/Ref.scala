package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Ref extends StObject {
  
  /**
    * This method makes the IPC channel keep the event loop of the process running if .unref() has been called before.
    * @since v7.1.0
    */
  def ref(): Unit
  
  /**
    * This method makes the IPC channel not keep the event loop of the process running, and lets it finish even while the channel is open.
    * @since v7.1.0
    */
  def unref(): Unit
}
object Ref {
  
  inline def apply(ref: () => Unit, unref: () => Unit): Ref = {
    val __obj = js.Dynamic.literal(ref = js.Any.fromFunction0(ref), unref = js.Any.fromFunction0(unref))
    __obj.asInstanceOf[Ref]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Ref] (val x: Self) extends AnyVal {
    
    inline def setRef(value: () => Unit): Self = StObject.set(x, "ref", js.Any.fromFunction0(value))
    
    inline def setUnref(value: () => Unit): Self = StObject.set(x, "unref", js.Any.fromFunction0(value))
  }
}

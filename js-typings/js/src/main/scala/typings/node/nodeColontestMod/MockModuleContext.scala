package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/**
  * @since v22.3.0
  * @experimental
  */
trait MockModuleContext extends StObject {
  
  /**
    * Resets the implementation of the mock module.
    * @since v22.3.0
    */
  def restore(): Unit
}
object MockModuleContext {
  
  inline def apply(restore: () => Unit): MockModuleContext = {
    val __obj = js.Dynamic.literal(restore = js.Any.fromFunction0(restore))
    __obj.asInstanceOf[MockModuleContext]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MockModuleContext] (val x: Self) extends AnyVal {
    
    inline def setRestore(value: () => Unit): Self = StObject.set(x, "restore", js.Any.fromFunction0(value))
  }
}

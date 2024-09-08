package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import typings.node.domainMod.Domain
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedNodeDomai extends StObject {
  
  /**
    * The `Domain` class encapsulates the functionality of routing errors and
    * uncaught exceptions to the active `Domain` object.
    *
    * To handle the errors that it catches, listen to its `'error'` event.
    */
  var Domain: Instantiable0[typings.node.nodeColondomainMod.Domain]
  
  def create(): Domain
}
object TypeofimportedNodeDomai {
  
  inline def apply(Domain: Instantiable0[typings.node.nodeColondomainMod.Domain], create: () => Domain): TypeofimportedNodeDomai = {
    val __obj = js.Dynamic.literal(Domain = Domain.asInstanceOf[js.Any], create = js.Any.fromFunction0(create))
    __obj.asInstanceOf[TypeofimportedNodeDomai]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedNodeDomai] (val x: Self) extends AnyVal {
    
    inline def setCreate(value: () => Domain): Self = StObject.set(x, "create", js.Any.fromFunction0(value))
    
    inline def setDomain(value: Instantiable0[typings.node.nodeColondomainMod.Domain]): Self = StObject.set(x, "Domain", value.asInstanceOf[js.Any])
  }
}

package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** An object used to filter the nodes in a NodeIterator or TreeWalker. They don't know anything about the DOM or how to traverse nodes; they just know how to evaluate a single node against the provided filter. */
trait NodeFilter extends StObject {
  
  /* standard dom */
  def acceptNode(node: org.scalajs.dom.Node): Double
}
object NodeFilter {
  
  inline def apply(acceptNode: org.scalajs.dom.Node => Double): NodeFilter = {
    val __obj = js.Dynamic.literal(acceptNode = js.Any.fromFunction1(acceptNode))
    __obj.asInstanceOf[NodeFilter]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: NodeFilter] (val x: Self) extends AnyVal {
    
    inline def setAcceptNode(value: org.scalajs.dom.Node => Double): Self = StObject.set(x, "acceptNode", js.Any.fromFunction1(value))
  }
}

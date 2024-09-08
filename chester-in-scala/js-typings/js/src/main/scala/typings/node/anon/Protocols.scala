package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Protocols extends StObject {
  
  var protocols: js.Array[String]
  
  var servername: String
}
object Protocols {
  
  inline def apply(protocols: js.Array[String], servername: String): Protocols = {
    val __obj = js.Dynamic.literal(protocols = protocols.asInstanceOf[js.Any], servername = servername.asInstanceOf[js.Any])
    __obj.asInstanceOf[Protocols]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Protocols] (val x: Self) extends AnyVal {
    
    inline def setProtocols(value: js.Array[String]): Self = StObject.set(x, "protocols", value.asInstanceOf[js.Any])
    
    inline def setProtocolsVarargs(value: String*): Self = StObject.set(x, "protocols", js.Array(value*))
    
    inline def setServername(value: String): Self = StObject.set(x, "servername", value.asInstanceOf[js.Any])
  }
}

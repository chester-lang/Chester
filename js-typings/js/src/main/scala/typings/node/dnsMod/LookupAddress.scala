package typings.node.dnsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait LookupAddress extends StObject {
  
  /**
    * A string representation of an IPv4 or IPv6 address.
    */
  var address: String
  
  /**
    * `4` or `6`, denoting the family of `address`, or `0` if the address is not an IPv4 or IPv6 address. `0` is a likely indicator of a
    * bug in the name resolution service used by the operating system.
    */
  var family: Double
}
object LookupAddress {
  
  inline def apply(address: String, family: Double): LookupAddress = {
    val __obj = js.Dynamic.literal(address = address.asInstanceOf[js.Any], family = family.asInstanceOf[js.Any])
    __obj.asInstanceOf[LookupAddress]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: LookupAddress] (val x: Self) extends AnyVal {
    
    inline def setAddress(value: String): Self = StObject.set(x, "address", value.asInstanceOf[js.Any])
    
    inline def setFamily(value: Double): Self = StObject.set(x, "family", value.asInstanceOf[js.Any])
  }
}

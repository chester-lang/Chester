package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PublicKey extends StObject {
  
  var privateKey: String
  
  var publicKey: String
}
object PublicKey {
  
  inline def apply(privateKey: String, publicKey: String): PublicKey = {
    val __obj = js.Dynamic.literal(privateKey = privateKey.asInstanceOf[js.Any], publicKey = publicKey.asInstanceOf[js.Any])
    __obj.asInstanceOf[PublicKey]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PublicKey] (val x: Self) extends AnyVal {
    
    inline def setPrivateKey(value: String): Self = StObject.set(x, "privateKey", value.asInstanceOf[js.Any])
    
    inline def setPublicKey(value: String): Self = StObject.set(x, "publicKey", value.asInstanceOf[js.Any])
  }
}

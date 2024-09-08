package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PrivateKeyPublicKey extends StObject {
  
  var privateKey: typings.node.bufferMod.global.Buffer
  
  var publicKey: String
}
object PrivateKeyPublicKey {
  
  inline def apply(privateKey: typings.node.bufferMod.global.Buffer, publicKey: String): PrivateKeyPublicKey = {
    val __obj = js.Dynamic.literal(privateKey = privateKey.asInstanceOf[js.Any], publicKey = publicKey.asInstanceOf[js.Any])
    __obj.asInstanceOf[PrivateKeyPublicKey]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PrivateKeyPublicKey] (val x: Self) extends AnyVal {
    
    inline def setPrivateKey(value: typings.node.bufferMod.global.Buffer): Self = StObject.set(x, "privateKey", value.asInstanceOf[js.Any])
    
    inline def setPublicKey(value: String): Self = StObject.set(x, "publicKey", value.asInstanceOf[js.Any])
  }
}

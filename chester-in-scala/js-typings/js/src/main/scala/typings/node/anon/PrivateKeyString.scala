package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PrivateKeyString extends StObject {
  
  var privateKey: String
  
  var publicKey: typings.node.bufferMod.global.Buffer
}
object PrivateKeyString {
  
  inline def apply(privateKey: String, publicKey: typings.node.bufferMod.global.Buffer): PrivateKeyString = {
    val __obj = js.Dynamic.literal(privateKey = privateKey.asInstanceOf[js.Any], publicKey = publicKey.asInstanceOf[js.Any])
    __obj.asInstanceOf[PrivateKeyString]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PrivateKeyString] (val x: Self) extends AnyVal {
    
    inline def setPrivateKey(value: String): Self = StObject.set(x, "privateKey", value.asInstanceOf[js.Any])
    
    inline def setPublicKey(value: typings.node.bufferMod.global.Buffer): Self = StObject.set(x, "publicKey", value.asInstanceOf[js.Any])
  }
}

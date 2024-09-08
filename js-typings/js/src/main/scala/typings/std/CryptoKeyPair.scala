package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** The CryptoKeyPair dictionary of the Web Crypto API represents a key pair for an asymmetric cryptography algorithm, also known as a public-key algorithm. */
trait CryptoKeyPair extends StObject {
  
  /* standard dom */
  var privateKey: org.scalajs.dom.CryptoKey
  
  /* standard dom */
  var publicKey: org.scalajs.dom.CryptoKey
}
object CryptoKeyPair {
  
  inline def apply(privateKey: org.scalajs.dom.CryptoKey, publicKey: org.scalajs.dom.CryptoKey): CryptoKeyPair = {
    val __obj = js.Dynamic.literal(privateKey = privateKey.asInstanceOf[js.Any], publicKey = publicKey.asInstanceOf[js.Any])
    __obj.asInstanceOf[CryptoKeyPair]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: CryptoKeyPair] (val x: Self) extends AnyVal {
    
    inline def setPrivateKey(value: org.scalajs.dom.CryptoKey): Self = StObject.set(x, "privateKey", value.asInstanceOf[js.Any])
    
    inline def setPublicKey(value: org.scalajs.dom.CryptoKey): Self = StObject.set(x, "publicKey", value.asInstanceOf[js.Any])
  }
}

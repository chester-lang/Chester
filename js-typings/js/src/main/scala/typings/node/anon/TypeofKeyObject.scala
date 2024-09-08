package typings.node.anon

import typings.node.cryptoMod.KeyObject
import typings.node.cryptoMod.webcrypto.CryptoKey
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofKeyObject extends StObject {
  
  /**
    * Example: Converting a `CryptoKey` instance to a `KeyObject`:
    *
    * ```js
    * const { KeyObject } = await import('node:crypto');
    * const { subtle } = globalThis.crypto;
    *
    * const key = await subtle.generateKey({
    *   name: 'HMAC',
    *   hash: 'SHA-256',
    *   length: 256,
    * }, true, ['sign', 'verify']);
    *
    * const keyObject = KeyObject.from(key);
    * console.log(keyObject.symmetricKeySize);
    * // Prints: 32 (symmetric key size in bytes)
    * ```
    * @since v15.0.0
    */
  /* static member */
  def from(key: CryptoKey): KeyObject
}
object TypeofKeyObject {
  
  inline def apply(from: CryptoKey => KeyObject): TypeofKeyObject = {
    val __obj = js.Dynamic.literal(from = js.Any.fromFunction1(from))
    __obj.asInstanceOf[TypeofKeyObject]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofKeyObject] (val x: Self) extends AnyVal {
    
    inline def setFrom(value: CryptoKey => KeyObject): Self = StObject.set(x, "from", js.Any.fromFunction1(value))
  }
}

package typings.node.anon

import typings.node.cryptoMod.BinaryLike
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofCertificate extends StObject {
  
  /**
    * ```js
    * const { Certificate } = await import('node:crypto');
    * const spkac = getSpkacSomehow();
    * const challenge = Certificate.exportChallenge(spkac);
    * console.log(challenge.toString('utf8'));
    * // Prints: the challenge as a UTF8 string
    * ```
    * @since v9.0.0
    * @param encoding The `encoding` of the `spkac` string.
    * @return The challenge component of the `spkac` data structure, which includes a public key and a challenge.
    */
  /* static member */
  def exportChallenge(spkac: BinaryLike): typings.node.bufferMod.global.Buffer = js.native
  
  /**
    * ```js
    * const { Certificate } = await import('node:crypto');
    * const spkac = getSpkacSomehow();
    * const publicKey = Certificate.exportPublicKey(spkac);
    * console.log(publicKey);
    * // Prints: the public key as <Buffer ...>
    * ```
    * @since v9.0.0
    * @param encoding The `encoding` of the `spkac` string.
    * @return The public key component of the `spkac` data structure, which includes a public key and a challenge.
    */
  /* static member */
  def exportPublicKey(spkac: BinaryLike): typings.node.bufferMod.global.Buffer = js.native
  def exportPublicKey(spkac: BinaryLike, encoding: String): typings.node.bufferMod.global.Buffer = js.native
  
  /**
    * ```js
    * import { Buffer } from 'node:buffer';
    * const { Certificate } = await import('node:crypto');
    *
    * const spkac = getSpkacSomehow();
    * console.log(Certificate.verifySpkac(Buffer.from(spkac)));
    * // Prints: true or false
    * ```
    * @since v9.0.0
    * @param encoding The `encoding` of the `spkac` string.
    * @return `true` if the given `spkac` data structure is valid, `false` otherwise.
    */
  /* static member */
  def verifySpkac(spkac: ArrayBufferView): Boolean = js.native
}

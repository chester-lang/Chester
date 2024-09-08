package typings.node.anon

import typings.node.cryptoMod.DSAKeyPairKeyObjectOptions
import typings.node.cryptoMod.DSAKeyPairOptions
import typings.node.cryptoMod.ECKeyPairKeyObjectOptions
import typings.node.cryptoMod.ECKeyPairOptions
import typings.node.cryptoMod.ED25519KeyPairKeyObjectOptions
import typings.node.cryptoMod.ED25519KeyPairOptions
import typings.node.cryptoMod.ED448KeyPairKeyObjectOptions
import typings.node.cryptoMod.ED448KeyPairOptions
import typings.node.cryptoMod.KeyObject
import typings.node.cryptoMod.KeyPairKeyObjectResult
import typings.node.cryptoMod.RSAKeyPairKeyObjectOptions
import typings.node.cryptoMod.RSAKeyPairOptions
import typings.node.cryptoMod.RSAPSSKeyPairKeyObjectOptions
import typings.node.cryptoMod.RSAPSSKeyPairOptions
import typings.node.cryptoMod.X25519KeyPairKeyObjectOptions
import typings.node.cryptoMod.X25519KeyPairOptions
import typings.node.cryptoMod.X448KeyPairKeyObjectOptions
import typings.node.cryptoMod.X448KeyPairOptions
import typings.node.nodeStrings.`rsa-pss`
import typings.node.nodeStrings.der
import typings.node.nodeStrings.dsa
import typings.node.nodeStrings.ec
import typings.node.nodeStrings.ed25519
import typings.node.nodeStrings.ed448
import typings.node.nodeStrings.pem
import typings.node.nodeStrings.rsa
import typings.node.nodeStrings.x25519
import typings.node.nodeStrings.x448
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofgenerateKeyPair extends StObject {
  
  def apply(
    `type`: `rsa-pss`,
    options: RSAPSSKeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: `rsa-pss`,
    options: RSAPSSKeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: dsa,
    options: DSAKeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: dsa,
    options: DSAKeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: ec,
    options: ECKeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: ec,
    options: ECKeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: ed25519,
    options: Unit,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: ed25519,
    options: ED25519KeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: ed25519,
    options: ED25519KeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: ed448,
    options: Unit,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: ed448,
    options: ED448KeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: ed448,
    options: ED448KeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: rsa,
    options: RSAKeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  /**
    * Generates a new asymmetric key pair of the given `type`. RSA, RSA-PSS, DSA, EC,
    * Ed25519, Ed448, X25519, X448, and DH are currently supported.
    *
    * If a `publicKeyEncoding` or `privateKeyEncoding` was specified, this function
    * behaves as if `keyObject.export()` had been called on its result. Otherwise,
    * the respective part of the key is returned as a `KeyObject`.
    *
    * It is recommended to encode public keys as `'spki'` and private keys as `'pkcs8'` with encryption for long-term storage:
    *
    * ```js
    * const {
    *   generateKeyPair,
    * } = await import('node:crypto');
    *
    * generateKeyPair('rsa', {
    *   modulusLength: 4096,
    *   publicKeyEncoding: {
    *     type: 'spki',
    *     format: 'pem',
    *   },
    *   privateKeyEncoding: {
    *     type: 'pkcs8',
    *     format: 'pem',
    *     cipher: 'aes-256-cbc',
    *     passphrase: 'top secret',
    *   },
    * }, (err, publicKey, privateKey) => {
    *   // Handle errors and use the generated key pair.
    * });
    * ```
    *
    * On completion, `callback` will be called with `err` set to `undefined` and `publicKey` / `privateKey` representing the generated key pair.
    *
    * If this method is invoked as its `util.promisify()` ed version, it returns
    * a `Promise` for an `Object` with `publicKey` and `privateKey` properties.
    * @since v10.12.0
    * @param type Must be `'rsa'`, `'rsa-pss'`, `'dsa'`, `'ec'`, `'ed25519'`, `'ed448'`, `'x25519'`, `'x448'`, or `'dh'`.
    */
  def apply(
    `type`: rsa,
    options: RSAKeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: x25519,
    options: Unit,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: x25519,
    options: X25519KeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: x25519,
    options: X25519KeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    `type`: x448,
    options: Unit,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: x448,
    options: X448KeyPairKeyObjectOptions,
    callback: js.Function3[/* err */ js.Error | Null, /* publicKey */ KeyObject, /* privateKey */ KeyObject, Unit]
  ): Unit = js.native
  def apply(
    `type`: x448,
    options: X448KeyPairOptions[der | pem, der | pem],
    callback: js.Function3[
      js.Error | Null, 
      (/* publicKey */ typings.node.bufferMod.global.Buffer) | (/* publicKey */ String), 
      (/* privateKey */ typings.node.bufferMod.global.Buffer) | (/* privateKey */ String), 
      Unit
    ]
  ): Unit = js.native
  
  def __promisify__(`type`: `rsa-pss`, options: RSAPSSKeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: dsa, options: DSAKeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: ec, options: ECKeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: ed25519, options: ED25519KeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: ed448, options: ED448KeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: rsa, options: RSAKeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: x25519, options: X25519KeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  def __promisify__(`type`: x448, options: X448KeyPairOptions[der | pem, der | pem]): js.Promise[PublicKey] = js.native
  @JSName("__promisify__")
  def __promisify___dsa(`type`: dsa, options: DSAKeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___ec(`type`: ec, options: ECKeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___ed25519(`type`: ed25519): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___ed25519(`type`: ed25519, options: ED25519KeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___ed448(`type`: ed448): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___ed448(`type`: ed448, options: ED448KeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___rsa(`type`: rsa, options: RSAKeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___rsapss(`type`: `rsa-pss`, options: RSAPSSKeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___x25519(`type`: x25519): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___x25519(`type`: x25519, options: X25519KeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___x448(`type`: x448): js.Promise[KeyPairKeyObjectResult] = js.native
  @JSName("__promisify__")
  def __promisify___x448(`type`: x448, options: X448KeyPairKeyObjectOptions): js.Promise[KeyPairKeyObjectResult] = js.native
}

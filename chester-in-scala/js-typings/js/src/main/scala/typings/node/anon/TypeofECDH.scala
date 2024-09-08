package typings.node.anon

import typings.node.cryptoMod.BinaryLike
import typings.node.cryptoMod.BinaryToTextEncoding
import typings.node.nodeStrings.base64
import typings.node.nodeStrings.base64url
import typings.node.nodeStrings.compressed
import typings.node.nodeStrings.hex
import typings.node.nodeStrings.hybrid
import typings.node.nodeStrings.latin1
import typings.node.nodeStrings.uncompressed
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofECDH extends StObject {
  
  /**
    * Converts the EC Diffie-Hellman public key specified by `key` and `curve` to the
    * format specified by `format`. The `format` argument specifies point encoding
    * and can be `'compressed'`, `'uncompressed'` or `'hybrid'`. The supplied key is
    * interpreted using the specified `inputEncoding`, and the returned key is encoded
    * using the specified `outputEncoding`.
    *
    * Use {@link getCurves} to obtain a list of available curve names.
    * On recent OpenSSL releases, `openssl ecparam -list_curves` will also display
    * the name and description of each available elliptic curve.
    *
    * If `format` is not specified the point will be returned in `'uncompressed'` format.
    *
    * If the `inputEncoding` is not provided, `key` is expected to be a `Buffer`, `TypedArray`, or `DataView`.
    *
    * Example (uncompressing a key):
    *
    * ```js
    * const {
    *   createECDH,
    *   ECDH,
    * } = await import('node:crypto');
    *
    * const ecdh = createECDH('secp256k1');
    * ecdh.generateKeys();
    *
    * const compressedKey = ecdh.getPublicKey('hex', 'compressed');
    *
    * const uncompressedKey = ECDH.convertKey(compressedKey,
    *                                         'secp256k1',
    *                                         'hex',
    *                                         'hex',
    *                                         'uncompressed');
    *
    * // The converted key and the uncompressed public key should be the same
    * console.log(uncompressedKey === ecdh.getPublicKey('hex'));
    * ```
    * @since v10.0.0
    * @param inputEncoding The `encoding` of the `key` string.
    * @param outputEncoding The `encoding` of the return value.
    * @param [format='uncompressed']
    */
  /* static member */
  def convertKey(key: BinaryLike, curve: String): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(
    key: BinaryLike,
    curve: String,
    inputEncoding: Unit,
    outputEncoding: latin1 | hex | base64 | base64url
  ): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(
    key: BinaryLike,
    curve: String,
    inputEncoding: Unit,
    outputEncoding: latin1 | hex | base64 | base64url,
    format: uncompressed | compressed | hybrid
  ): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(
    key: BinaryLike,
    curve: String,
    inputEncoding: Unit,
    outputEncoding: Unit,
    format: uncompressed | compressed | hybrid
  ): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(key: BinaryLike, curve: String, inputEncoding: BinaryToTextEncoding): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(
    key: BinaryLike,
    curve: String,
    inputEncoding: BinaryToTextEncoding,
    outputEncoding: latin1 | hex | base64 | base64url
  ): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(
    key: BinaryLike,
    curve: String,
    inputEncoding: BinaryToTextEncoding,
    outputEncoding: latin1 | hex | base64 | base64url,
    format: uncompressed | compressed | hybrid
  ): typings.node.bufferMod.global.Buffer | String = js.native
  def convertKey(
    key: BinaryLike,
    curve: String,
    inputEncoding: BinaryToTextEncoding,
    outputEncoding: Unit,
    format: uncompressed | compressed | hybrid
  ): typings.node.bufferMod.global.Buffer | String = js.native
}

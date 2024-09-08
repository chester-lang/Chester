package typings.node.anon

import typings.node.punycodeMod.ucs2
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedPunycode extends StObject {
  
  /**
    * The `punycode.decode()` method converts a [Punycode](https://tools.ietf.org/html/rfc3492) string of ASCII-only
    * characters to the equivalent string of Unicode codepoints.
    *
    * ```js
    * punycode.decode('maana-pta'); // 'mañana'
    * punycode.decode('--dqo34k'); // '☃-⌘'
    * ```
    * @since v0.5.1
    */
  def decode(string: String): String
  
  /**
    * The `punycode.encode()` method converts a string of Unicode codepoints to a [Punycode](https://tools.ietf.org/html/rfc3492) string of ASCII-only characters.
    *
    * ```js
    * punycode.encode('mañana'); // 'maana-pta'
    * punycode.encode('☃-⌘'); // '--dqo34k'
    * ```
    * @since v0.5.1
    */
  def encode(string: String): String
  
  /**
    * The `punycode.toASCII()` method converts a Unicode string representing an
    * Internationalized Domain Name to [Punycode](https://tools.ietf.org/html/rfc3492). Only the non-ASCII parts of the
    * domain name will be converted. Calling `punycode.toASCII()` on a string that
    * already only contains ASCII characters will have no effect.
    *
    * ```js
    * // encode domain names
    * punycode.toASCII('mañana.com');  // 'xn--maana-pta.com'
    * punycode.toASCII('☃-⌘.com');   // 'xn----dqo34k.com'
    * punycode.toASCII('example.com'); // 'example.com'
    * ```
    * @since v0.6.1
    */
  def toASCII(domain: String): String
  
  /**
    * The `punycode.toUnicode()` method converts a string representing a domain name
    * containing [Punycode](https://tools.ietf.org/html/rfc3492) encoded characters into Unicode. Only the [Punycode](https://tools.ietf.org/html/rfc3492) encoded parts of the domain name are be
    * converted.
    *
    * ```js
    * // decode domain names
    * punycode.toUnicode('xn--maana-pta.com'); // 'mañana.com'
    * punycode.toUnicode('xn----dqo34k.com');  // '☃-⌘.com'
    * punycode.toUnicode('example.com');       // 'example.com'
    * ```
    * @since v0.6.1
    */
  def toUnicode(domain: String): String
  
  /**
    * @deprecated since v7.0.0
    * The version of the punycode module bundled in Node.js is being deprecated.
    * In a future major version of Node.js this module will be removed.
    * Users currently depending on the punycode module should switch to using
    * the userland-provided Punycode.js module instead.
    */
  val ucs2: typings.node.punycodeMod.ucs2
  
  /**
    * @deprecated since v7.0.0
    * The version of the punycode module bundled in Node.js is being deprecated.
    * In a future major version of Node.js this module will be removed.
    * Users currently depending on the punycode module should switch to using
    * the userland-provided Punycode.js module instead.
    */
  val version: String
}
object TypeofimportedPunycode {
  
  inline def apply(
    decode: String => String,
    encode: String => String,
    toASCII: String => String,
    toUnicode: String => String,
    ucs2: ucs2,
    version: String
  ): TypeofimportedPunycode = {
    val __obj = js.Dynamic.literal(decode = js.Any.fromFunction1(decode), encode = js.Any.fromFunction1(encode), toASCII = js.Any.fromFunction1(toASCII), toUnicode = js.Any.fromFunction1(toUnicode), ucs2 = ucs2.asInstanceOf[js.Any], version = version.asInstanceOf[js.Any])
    __obj.asInstanceOf[TypeofimportedPunycode]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedPunycode] (val x: Self) extends AnyVal {
    
    inline def setDecode(value: String => String): Self = StObject.set(x, "decode", js.Any.fromFunction1(value))
    
    inline def setEncode(value: String => String): Self = StObject.set(x, "encode", js.Any.fromFunction1(value))
    
    inline def setToASCII(value: String => String): Self = StObject.set(x, "toASCII", js.Any.fromFunction1(value))
    
    inline def setToUnicode(value: String => String): Self = StObject.set(x, "toUnicode", js.Any.fromFunction1(value))
    
    inline def setUcs2(value: ucs2): Self = StObject.set(x, "ucs2", value.asInstanceOf[js.Any])
    
    inline def setVersion(value: String): Self = StObject.set(x, "version", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Typeofconstants extends StObject {
  
  val DH_CHECK_P_NOT_PRIME: Double
  
  val DH_CHECK_P_NOT_SAFE_PRIME: Double
  
  val DH_NOT_SUITABLE_GENERATOR: Double
  
  val DH_UNABLE_TO_CHECK_GENERATOR: Double
  
  val ENGINE_METHOD_ALL: Double
  
  val ENGINE_METHOD_CIPHERS: Double
  
  val ENGINE_METHOD_DH: Double
  
  val ENGINE_METHOD_DIGESTS: Double
  
  val ENGINE_METHOD_DSA: Double
  
  val ENGINE_METHOD_EC: Double
  
  val ENGINE_METHOD_NONE: Double
  
  val ENGINE_METHOD_PKEY_ASN1_METHS: Double
  
  val ENGINE_METHOD_PKEY_METHS: Double
  
  val ENGINE_METHOD_RAND: Double
  
  val ENGINE_METHOD_RSA: Double
  
  // https://nodejs.org/dist/latest-v22.x/docs/api/crypto.html#crypto-constants
  val OPENSSL_VERSION_NUMBER: Double
  
  val POINT_CONVERSION_COMPRESSED: Double
  
  val POINT_CONVERSION_HYBRID: Double
  
  val POINT_CONVERSION_UNCOMPRESSED: Double
  
  val RSA_NO_PADDING: Double
  
  val RSA_PKCS1_OAEP_PADDING: Double
  
  val RSA_PKCS1_PADDING: Double
  
  val RSA_PKCS1_PSS_PADDING: Double
  
  /** Causes the salt length for RSA_PKCS1_PSS_PADDING to be determined automatically when verifying a signature. */
  val RSA_PSS_SALTLEN_AUTO: Double
  
  /** Sets the salt length for RSA_PKCS1_PSS_PADDING to the digest size when signing or verifying. */
  val RSA_PSS_SALTLEN_DIGEST: Double
  
  /** Sets the salt length for RSA_PKCS1_PSS_PADDING to the maximum permissible value when signing data. */
  val RSA_PSS_SALTLEN_MAX_SIGN: Double
  
  val RSA_SSLV23_PADDING: Double
  
  val RSA_X931_PADDING: Double
  
  /** Applies multiple bug workarounds within OpenSSL. See https://www.openssl.org/docs/man1.0.2/ssl/SSL_CTX_set_options.html for detail. */
  val SSL_OP_ALL: Double
  
  /** Instructs OpenSSL to allow a non-[EC]DHE-based key exchange mode for TLS v1.3 */
  val SSL_OP_ALLOW_NO_DHE_KEX: Double
  
  /** Allows legacy insecure renegotiation between OpenSSL and unpatched clients or servers. See https://www.openssl.org/docs/man1.0.2/ssl/SSL_CTX_set_options.html. */
  val SSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION: Double
  
  /** Attempts to use the server's preferences instead of the client's when selecting a cipher. See https://www.openssl.org/docs/man1.0.2/ssl/SSL_CTX_set_options.html. */
  val SSL_OP_CIPHER_SERVER_PREFERENCE: Double
  
  /** Instructs OpenSSL to use Cisco's version identifier of DTLS_BAD_VER. */
  val SSL_OP_CISCO_ANYCONNECT: Double
  
  /** Instructs OpenSSL to turn on cookie exchange. */
  val SSL_OP_COOKIE_EXCHANGE: Double
  
  /** Instructs OpenSSL to add server-hello extension from an early version of the cryptopro draft. */
  val SSL_OP_CRYPTOPRO_TLSEXT_BUG: Double
  
  /** Instructs OpenSSL to disable a SSL 3.0/TLS 1.0 vulnerability workaround added in OpenSSL 0.9.6d. */
  val SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS: Double
  
  /** Allows initial connection to servers that do not support RI. */
  val SSL_OP_LEGACY_SERVER_CONNECT: Double
  
  /** Instructs OpenSSL to disable support for SSL/TLS compression. */
  val SSL_OP_NO_COMPRESSION: Double
  
  /** Instructs OpenSSL to disable encrypt-then-MAC. */
  val SSL_OP_NO_ENCRYPT_THEN_MAC: Double
  
  val SSL_OP_NO_QUERY_MTU: Double
  
  /** Instructs OpenSSL to disable renegotiation. */
  val SSL_OP_NO_RENEGOTIATION: Double
  
  /** Instructs OpenSSL to always start a new session when performing renegotiation. */
  val SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION: Double
  
  /** Instructs OpenSSL to turn off SSL v2 */
  val SSL_OP_NO_SSLv2: Double
  
  /** Instructs OpenSSL to turn off SSL v3 */
  val SSL_OP_NO_SSLv3: Double
  
  /** Instructs OpenSSL to disable use of RFC4507bis tickets. */
  val SSL_OP_NO_TICKET: Double
  
  /** Instructs OpenSSL to turn off TLS v1 */
  val SSL_OP_NO_TLSv1: Double
  
  /** Instructs OpenSSL to turn off TLS v1.1 */
  val SSL_OP_NO_TLSv1_1: Double
  
  /** Instructs OpenSSL to turn off TLS v1.2 */
  val SSL_OP_NO_TLSv1_2: Double
  
  /** Instructs OpenSSL to turn off TLS v1.3 */
  val SSL_OP_NO_TLSv1_3: Double
  
  /** Instructs OpenSSL server to prioritize ChaCha20-Poly1305 when the client does. This option has no effect if `SSL_OP_CIPHER_SERVER_PREFERENCE` is not enabled. */
  val SSL_OP_PRIORITIZE_CHACHA: Double
  
  /** Instructs OpenSSL to disable version rollback attack detection. */
  val SSL_OP_TLS_ROLLBACK_BUG: Double
  
  /** Specifies the active default cipher list used by the current Node.js process  (colon-separated values). */
  val defaultCipherList: String
  
  /** Specifies the built-in default cipher list used by Node.js (colon-separated values). */
  val defaultCoreCipherList: String
}
object Typeofconstants {
  
  inline def apply(
    DH_CHECK_P_NOT_PRIME: Double,
    DH_CHECK_P_NOT_SAFE_PRIME: Double,
    DH_NOT_SUITABLE_GENERATOR: Double,
    DH_UNABLE_TO_CHECK_GENERATOR: Double,
    ENGINE_METHOD_ALL: Double,
    ENGINE_METHOD_CIPHERS: Double,
    ENGINE_METHOD_DH: Double,
    ENGINE_METHOD_DIGESTS: Double,
    ENGINE_METHOD_DSA: Double,
    ENGINE_METHOD_EC: Double,
    ENGINE_METHOD_NONE: Double,
    ENGINE_METHOD_PKEY_ASN1_METHS: Double,
    ENGINE_METHOD_PKEY_METHS: Double,
    ENGINE_METHOD_RAND: Double,
    ENGINE_METHOD_RSA: Double,
    OPENSSL_VERSION_NUMBER: Double,
    POINT_CONVERSION_COMPRESSED: Double,
    POINT_CONVERSION_HYBRID: Double,
    POINT_CONVERSION_UNCOMPRESSED: Double,
    RSA_NO_PADDING: Double,
    RSA_PKCS1_OAEP_PADDING: Double,
    RSA_PKCS1_PADDING: Double,
    RSA_PKCS1_PSS_PADDING: Double,
    RSA_PSS_SALTLEN_AUTO: Double,
    RSA_PSS_SALTLEN_DIGEST: Double,
    RSA_PSS_SALTLEN_MAX_SIGN: Double,
    RSA_SSLV23_PADDING: Double,
    RSA_X931_PADDING: Double,
    SSL_OP_ALL: Double,
    SSL_OP_ALLOW_NO_DHE_KEX: Double,
    SSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION: Double,
    SSL_OP_CIPHER_SERVER_PREFERENCE: Double,
    SSL_OP_CISCO_ANYCONNECT: Double,
    SSL_OP_COOKIE_EXCHANGE: Double,
    SSL_OP_CRYPTOPRO_TLSEXT_BUG: Double,
    SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS: Double,
    SSL_OP_LEGACY_SERVER_CONNECT: Double,
    SSL_OP_NO_COMPRESSION: Double,
    SSL_OP_NO_ENCRYPT_THEN_MAC: Double,
    SSL_OP_NO_QUERY_MTU: Double,
    SSL_OP_NO_RENEGOTIATION: Double,
    SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION: Double,
    SSL_OP_NO_SSLv2: Double,
    SSL_OP_NO_SSLv3: Double,
    SSL_OP_NO_TICKET: Double,
    SSL_OP_NO_TLSv1: Double,
    SSL_OP_NO_TLSv1_1: Double,
    SSL_OP_NO_TLSv1_2: Double,
    SSL_OP_NO_TLSv1_3: Double,
    SSL_OP_PRIORITIZE_CHACHA: Double,
    SSL_OP_TLS_ROLLBACK_BUG: Double,
    defaultCipherList: String,
    defaultCoreCipherList: String
  ): Typeofconstants = {
    val __obj = js.Dynamic.literal(DH_CHECK_P_NOT_PRIME = DH_CHECK_P_NOT_PRIME.asInstanceOf[js.Any], DH_CHECK_P_NOT_SAFE_PRIME = DH_CHECK_P_NOT_SAFE_PRIME.asInstanceOf[js.Any], DH_NOT_SUITABLE_GENERATOR = DH_NOT_SUITABLE_GENERATOR.asInstanceOf[js.Any], DH_UNABLE_TO_CHECK_GENERATOR = DH_UNABLE_TO_CHECK_GENERATOR.asInstanceOf[js.Any], ENGINE_METHOD_ALL = ENGINE_METHOD_ALL.asInstanceOf[js.Any], ENGINE_METHOD_CIPHERS = ENGINE_METHOD_CIPHERS.asInstanceOf[js.Any], ENGINE_METHOD_DH = ENGINE_METHOD_DH.asInstanceOf[js.Any], ENGINE_METHOD_DIGESTS = ENGINE_METHOD_DIGESTS.asInstanceOf[js.Any], ENGINE_METHOD_DSA = ENGINE_METHOD_DSA.asInstanceOf[js.Any], ENGINE_METHOD_EC = ENGINE_METHOD_EC.asInstanceOf[js.Any], ENGINE_METHOD_NONE = ENGINE_METHOD_NONE.asInstanceOf[js.Any], ENGINE_METHOD_PKEY_ASN1_METHS = ENGINE_METHOD_PKEY_ASN1_METHS.asInstanceOf[js.Any], ENGINE_METHOD_PKEY_METHS = ENGINE_METHOD_PKEY_METHS.asInstanceOf[js.Any], ENGINE_METHOD_RAND = ENGINE_METHOD_RAND.asInstanceOf[js.Any], ENGINE_METHOD_RSA = ENGINE_METHOD_RSA.asInstanceOf[js.Any], OPENSSL_VERSION_NUMBER = OPENSSL_VERSION_NUMBER.asInstanceOf[js.Any], POINT_CONVERSION_COMPRESSED = POINT_CONVERSION_COMPRESSED.asInstanceOf[js.Any], POINT_CONVERSION_HYBRID = POINT_CONVERSION_HYBRID.asInstanceOf[js.Any], POINT_CONVERSION_UNCOMPRESSED = POINT_CONVERSION_UNCOMPRESSED.asInstanceOf[js.Any], RSA_NO_PADDING = RSA_NO_PADDING.asInstanceOf[js.Any], RSA_PKCS1_OAEP_PADDING = RSA_PKCS1_OAEP_PADDING.asInstanceOf[js.Any], RSA_PKCS1_PADDING = RSA_PKCS1_PADDING.asInstanceOf[js.Any], RSA_PKCS1_PSS_PADDING = RSA_PKCS1_PSS_PADDING.asInstanceOf[js.Any], RSA_PSS_SALTLEN_AUTO = RSA_PSS_SALTLEN_AUTO.asInstanceOf[js.Any], RSA_PSS_SALTLEN_DIGEST = RSA_PSS_SALTLEN_DIGEST.asInstanceOf[js.Any], RSA_PSS_SALTLEN_MAX_SIGN = RSA_PSS_SALTLEN_MAX_SIGN.asInstanceOf[js.Any], RSA_SSLV23_PADDING = RSA_SSLV23_PADDING.asInstanceOf[js.Any], RSA_X931_PADDING = RSA_X931_PADDING.asInstanceOf[js.Any], SSL_OP_ALL = SSL_OP_ALL.asInstanceOf[js.Any], SSL_OP_ALLOW_NO_DHE_KEX = SSL_OP_ALLOW_NO_DHE_KEX.asInstanceOf[js.Any], SSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION = SSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION.asInstanceOf[js.Any], SSL_OP_CIPHER_SERVER_PREFERENCE = SSL_OP_CIPHER_SERVER_PREFERENCE.asInstanceOf[js.Any], SSL_OP_CISCO_ANYCONNECT = SSL_OP_CISCO_ANYCONNECT.asInstanceOf[js.Any], SSL_OP_COOKIE_EXCHANGE = SSL_OP_COOKIE_EXCHANGE.asInstanceOf[js.Any], SSL_OP_CRYPTOPRO_TLSEXT_BUG = SSL_OP_CRYPTOPRO_TLSEXT_BUG.asInstanceOf[js.Any], SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS = SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS.asInstanceOf[js.Any], SSL_OP_LEGACY_SERVER_CONNECT = SSL_OP_LEGACY_SERVER_CONNECT.asInstanceOf[js.Any], SSL_OP_NO_COMPRESSION = SSL_OP_NO_COMPRESSION.asInstanceOf[js.Any], SSL_OP_NO_ENCRYPT_THEN_MAC = SSL_OP_NO_ENCRYPT_THEN_MAC.asInstanceOf[js.Any], SSL_OP_NO_QUERY_MTU = SSL_OP_NO_QUERY_MTU.asInstanceOf[js.Any], SSL_OP_NO_RENEGOTIATION = SSL_OP_NO_RENEGOTIATION.asInstanceOf[js.Any], SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION = SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION.asInstanceOf[js.Any], SSL_OP_NO_SSLv2 = SSL_OP_NO_SSLv2.asInstanceOf[js.Any], SSL_OP_NO_SSLv3 = SSL_OP_NO_SSLv3.asInstanceOf[js.Any], SSL_OP_NO_TICKET = SSL_OP_NO_TICKET.asInstanceOf[js.Any], SSL_OP_NO_TLSv1 = SSL_OP_NO_TLSv1.asInstanceOf[js.Any], SSL_OP_NO_TLSv1_1 = SSL_OP_NO_TLSv1_1.asInstanceOf[js.Any], SSL_OP_NO_TLSv1_2 = SSL_OP_NO_TLSv1_2.asInstanceOf[js.Any], SSL_OP_NO_TLSv1_3 = SSL_OP_NO_TLSv1_3.asInstanceOf[js.Any], SSL_OP_PRIORITIZE_CHACHA = SSL_OP_PRIORITIZE_CHACHA.asInstanceOf[js.Any], SSL_OP_TLS_ROLLBACK_BUG = SSL_OP_TLS_ROLLBACK_BUG.asInstanceOf[js.Any], defaultCipherList = defaultCipherList.asInstanceOf[js.Any], defaultCoreCipherList = defaultCoreCipherList.asInstanceOf[js.Any])
    __obj.asInstanceOf[Typeofconstants]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Typeofconstants] (val x: Self) extends AnyVal {
    
    inline def setDH_CHECK_P_NOT_PRIME(value: Double): Self = StObject.set(x, "DH_CHECK_P_NOT_PRIME", value.asInstanceOf[js.Any])
    
    inline def setDH_CHECK_P_NOT_SAFE_PRIME(value: Double): Self = StObject.set(x, "DH_CHECK_P_NOT_SAFE_PRIME", value.asInstanceOf[js.Any])
    
    inline def setDH_NOT_SUITABLE_GENERATOR(value: Double): Self = StObject.set(x, "DH_NOT_SUITABLE_GENERATOR", value.asInstanceOf[js.Any])
    
    inline def setDH_UNABLE_TO_CHECK_GENERATOR(value: Double): Self = StObject.set(x, "DH_UNABLE_TO_CHECK_GENERATOR", value.asInstanceOf[js.Any])
    
    inline def setDefaultCipherList(value: String): Self = StObject.set(x, "defaultCipherList", value.asInstanceOf[js.Any])
    
    inline def setDefaultCoreCipherList(value: String): Self = StObject.set(x, "defaultCoreCipherList", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_ALL(value: Double): Self = StObject.set(x, "ENGINE_METHOD_ALL", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_CIPHERS(value: Double): Self = StObject.set(x, "ENGINE_METHOD_CIPHERS", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_DH(value: Double): Self = StObject.set(x, "ENGINE_METHOD_DH", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_DIGESTS(value: Double): Self = StObject.set(x, "ENGINE_METHOD_DIGESTS", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_DSA(value: Double): Self = StObject.set(x, "ENGINE_METHOD_DSA", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_EC(value: Double): Self = StObject.set(x, "ENGINE_METHOD_EC", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_NONE(value: Double): Self = StObject.set(x, "ENGINE_METHOD_NONE", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_PKEY_ASN1_METHS(value: Double): Self = StObject.set(x, "ENGINE_METHOD_PKEY_ASN1_METHS", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_PKEY_METHS(value: Double): Self = StObject.set(x, "ENGINE_METHOD_PKEY_METHS", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_RAND(value: Double): Self = StObject.set(x, "ENGINE_METHOD_RAND", value.asInstanceOf[js.Any])
    
    inline def setENGINE_METHOD_RSA(value: Double): Self = StObject.set(x, "ENGINE_METHOD_RSA", value.asInstanceOf[js.Any])
    
    inline def setOPENSSL_VERSION_NUMBER(value: Double): Self = StObject.set(x, "OPENSSL_VERSION_NUMBER", value.asInstanceOf[js.Any])
    
    inline def setPOINT_CONVERSION_COMPRESSED(value: Double): Self = StObject.set(x, "POINT_CONVERSION_COMPRESSED", value.asInstanceOf[js.Any])
    
    inline def setPOINT_CONVERSION_HYBRID(value: Double): Self = StObject.set(x, "POINT_CONVERSION_HYBRID", value.asInstanceOf[js.Any])
    
    inline def setPOINT_CONVERSION_UNCOMPRESSED(value: Double): Self = StObject.set(x, "POINT_CONVERSION_UNCOMPRESSED", value.asInstanceOf[js.Any])
    
    inline def setRSA_NO_PADDING(value: Double): Self = StObject.set(x, "RSA_NO_PADDING", value.asInstanceOf[js.Any])
    
    inline def setRSA_PKCS1_OAEP_PADDING(value: Double): Self = StObject.set(x, "RSA_PKCS1_OAEP_PADDING", value.asInstanceOf[js.Any])
    
    inline def setRSA_PKCS1_PADDING(value: Double): Self = StObject.set(x, "RSA_PKCS1_PADDING", value.asInstanceOf[js.Any])
    
    inline def setRSA_PKCS1_PSS_PADDING(value: Double): Self = StObject.set(x, "RSA_PKCS1_PSS_PADDING", value.asInstanceOf[js.Any])
    
    inline def setRSA_PSS_SALTLEN_AUTO(value: Double): Self = StObject.set(x, "RSA_PSS_SALTLEN_AUTO", value.asInstanceOf[js.Any])
    
    inline def setRSA_PSS_SALTLEN_DIGEST(value: Double): Self = StObject.set(x, "RSA_PSS_SALTLEN_DIGEST", value.asInstanceOf[js.Any])
    
    inline def setRSA_PSS_SALTLEN_MAX_SIGN(value: Double): Self = StObject.set(x, "RSA_PSS_SALTLEN_MAX_SIGN", value.asInstanceOf[js.Any])
    
    inline def setRSA_SSLV23_PADDING(value: Double): Self = StObject.set(x, "RSA_SSLV23_PADDING", value.asInstanceOf[js.Any])
    
    inline def setRSA_X931_PADDING(value: Double): Self = StObject.set(x, "RSA_X931_PADDING", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_ALL(value: Double): Self = StObject.set(x, "SSL_OP_ALL", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_ALLOW_NO_DHE_KEX(value: Double): Self = StObject.set(x, "SSL_OP_ALLOW_NO_DHE_KEX", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION(value: Double): Self = StObject.set(x, "SSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_CIPHER_SERVER_PREFERENCE(value: Double): Self = StObject.set(x, "SSL_OP_CIPHER_SERVER_PREFERENCE", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_CISCO_ANYCONNECT(value: Double): Self = StObject.set(x, "SSL_OP_CISCO_ANYCONNECT", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_COOKIE_EXCHANGE(value: Double): Self = StObject.set(x, "SSL_OP_COOKIE_EXCHANGE", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_CRYPTOPRO_TLSEXT_BUG(value: Double): Self = StObject.set(x, "SSL_OP_CRYPTOPRO_TLSEXT_BUG", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_DONT_INSERT_EMPTY_FRAGMENTS(value: Double): Self = StObject.set(x, "SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_LEGACY_SERVER_CONNECT(value: Double): Self = StObject.set(x, "SSL_OP_LEGACY_SERVER_CONNECT", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_COMPRESSION(value: Double): Self = StObject.set(x, "SSL_OP_NO_COMPRESSION", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_ENCRYPT_THEN_MAC(value: Double): Self = StObject.set(x, "SSL_OP_NO_ENCRYPT_THEN_MAC", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_QUERY_MTU(value: Double): Self = StObject.set(x, "SSL_OP_NO_QUERY_MTU", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_RENEGOTIATION(value: Double): Self = StObject.set(x, "SSL_OP_NO_RENEGOTIATION", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION(value: Double): Self = StObject.set(x, "SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_SSLv2(value: Double): Self = StObject.set(x, "SSL_OP_NO_SSLv2", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_SSLv3(value: Double): Self = StObject.set(x, "SSL_OP_NO_SSLv3", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_TICKET(value: Double): Self = StObject.set(x, "SSL_OP_NO_TICKET", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_TLSv1(value: Double): Self = StObject.set(x, "SSL_OP_NO_TLSv1", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_TLSv1_1(value: Double): Self = StObject.set(x, "SSL_OP_NO_TLSv1_1", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_TLSv1_2(value: Double): Self = StObject.set(x, "SSL_OP_NO_TLSv1_2", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_NO_TLSv1_3(value: Double): Self = StObject.set(x, "SSL_OP_NO_TLSv1_3", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_PRIORITIZE_CHACHA(value: Double): Self = StObject.set(x, "SSL_OP_PRIORITIZE_CHACHA", value.asInstanceOf[js.Any])
    
    inline def setSSL_OP_TLS_ROLLBACK_BUG(value: Double): Self = StObject.set(x, "SSL_OP_TLS_ROLLBACK_BUG", value.asInstanceOf[js.Any])
  }
}

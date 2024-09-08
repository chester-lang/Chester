package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Pbkdf2Params
  extends StObject
     with Algorithm {
  
  /* standard dom */
  var hash: HashAlgorithmIdentifier
  
  /* standard dom */
  var iterations: Double
  
  /* standard dom */
  var salt: js.typedarray.Int8Array | js.typedarray.Int16Array | js.typedarray.Int32Array | js.typedarray.Uint8Array | js.typedarray.Uint16Array | js.typedarray.Uint32Array | js.typedarray.Uint8ClampedArray | js.typedarray.Float32Array | js.typedarray.Float64Array | js.typedarray.DataView | js.typedarray.ArrayBuffer
}
object Pbkdf2Params {
  
  inline def apply(
    hash: HashAlgorithmIdentifier,
    iterations: Double,
    name: java.lang.String,
    salt: js.typedarray.Int8Array | js.typedarray.Int16Array | js.typedarray.Int32Array | js.typedarray.Uint8Array | js.typedarray.Uint16Array | js.typedarray.Uint32Array | js.typedarray.Uint8ClampedArray | js.typedarray.Float32Array | js.typedarray.Float64Array | js.typedarray.DataView | js.typedarray.ArrayBuffer
  ): Pbkdf2Params = {
    val __obj = js.Dynamic.literal(hash = hash.asInstanceOf[js.Any], iterations = iterations.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any], salt = salt.asInstanceOf[js.Any])
    __obj.asInstanceOf[Pbkdf2Params]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Pbkdf2Params] (val x: Self) extends AnyVal {
    
    inline def setHash(value: HashAlgorithmIdentifier): Self = StObject.set(x, "hash", value.asInstanceOf[js.Any])
    
    inline def setIterations(value: Double): Self = StObject.set(x, "iterations", value.asInstanceOf[js.Any])
    
    inline def setSalt(
      value: js.typedarray.Int8Array | js.typedarray.Int16Array | js.typedarray.Int32Array | js.typedarray.Uint8Array | js.typedarray.Uint16Array | js.typedarray.Uint32Array | js.typedarray.Uint8ClampedArray | js.typedarray.Float32Array | js.typedarray.Float64Array | js.typedarray.DataView | js.typedarray.ArrayBuffer
    ): Self = StObject.set(x, "salt", value.asInstanceOf[js.Any])
  }
}

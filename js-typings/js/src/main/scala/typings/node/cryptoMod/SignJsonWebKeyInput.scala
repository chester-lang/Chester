package typings.node.cryptoMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait SignJsonWebKeyInput
  extends StObject
     with JsonWebKeyInput
     with SigningOptions
object SignJsonWebKeyInput {
  
  inline def apply(key: JsonWebKey): SignJsonWebKeyInput = {
    val __obj = js.Dynamic.literal(format = "jwk", key = key.asInstanceOf[js.Any])
    __obj.asInstanceOf[SignJsonWebKeyInput]
  }
}

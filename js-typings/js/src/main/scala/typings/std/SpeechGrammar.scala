package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait SpeechGrammar extends StObject {
  
  /* standard dom */
  var src: java.lang.String
  
  /* standard dom */
  var weight: Double
}
object SpeechGrammar {
  
  inline def apply(src: java.lang.String, weight: Double): SpeechGrammar = {
    val __obj = js.Dynamic.literal(src = src.asInstanceOf[js.Any], weight = weight.asInstanceOf[js.Any])
    __obj.asInstanceOf[SpeechGrammar]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: SpeechGrammar] (val x: Self) extends AnyVal {
    
    inline def setSrc(value: java.lang.String): Self = StObject.set(x, "src", value.asInstanceOf[js.Any])
    
    inline def setWeight(value: Double): Self = StObject.set(x, "weight", value.asInstanceOf[js.Any])
  }
}

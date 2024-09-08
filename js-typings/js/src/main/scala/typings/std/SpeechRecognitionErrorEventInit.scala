package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait SpeechRecognitionErrorEventInit
  extends StObject
     with EventInit {
  
  /* standard dom */
  var error: SpeechRecognitionErrorCode
  
  /* standard dom */
  var message: js.UndefOr[java.lang.String] = js.undefined
}
object SpeechRecognitionErrorEventInit {
  
  inline def apply(error: SpeechRecognitionErrorCode): SpeechRecognitionErrorEventInit = {
    val __obj = js.Dynamic.literal(error = error.asInstanceOf[js.Any])
    __obj.asInstanceOf[SpeechRecognitionErrorEventInit]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: SpeechRecognitionErrorEventInit] (val x: Self) extends AnyVal {
    
    inline def setError(value: SpeechRecognitionErrorCode): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setMessage(value: java.lang.String): Self = StObject.set(x, "message", value.asInstanceOf[js.Any])
    
    inline def setMessageUndefined: Self = StObject.set(x, "message", js.undefined)
  }
}

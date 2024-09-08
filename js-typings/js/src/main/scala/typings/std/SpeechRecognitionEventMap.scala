package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait SpeechRecognitionEventMap extends StObject {
  
  /* standard dom */
  var audioend: org.scalajs.dom.Event
  
  /* standard dom */
  var audiostart: org.scalajs.dom.Event
  
  /* standard dom */
  var end: org.scalajs.dom.Event
  
  /* standard dom */
  var error: SpeechRecognitionErrorEvent
  
  /* standard dom */
  var nomatch: SpeechRecognitionEvent
  
  /* standard dom */
  var result: SpeechRecognitionEvent
  
  /* standard dom */
  var soundend: org.scalajs.dom.Event
  
  /* standard dom */
  var soundstart: org.scalajs.dom.Event
  
  /* standard dom */
  var speechend: org.scalajs.dom.Event
  
  /* standard dom */
  var speechstart: org.scalajs.dom.Event
  
  /* standard dom */
  var start: org.scalajs.dom.Event
}
object SpeechRecognitionEventMap {
  
  inline def apply(
    audioend: org.scalajs.dom.Event,
    audiostart: org.scalajs.dom.Event,
    end: org.scalajs.dom.Event,
    error: SpeechRecognitionErrorEvent,
    nomatch: SpeechRecognitionEvent,
    result: SpeechRecognitionEvent,
    soundend: org.scalajs.dom.Event,
    soundstart: org.scalajs.dom.Event,
    speechend: org.scalajs.dom.Event,
    speechstart: org.scalajs.dom.Event,
    start: org.scalajs.dom.Event
  ): SpeechRecognitionEventMap = {
    val __obj = js.Dynamic.literal(audioend = audioend.asInstanceOf[js.Any], audiostart = audiostart.asInstanceOf[js.Any], end = end.asInstanceOf[js.Any], error = error.asInstanceOf[js.Any], nomatch = nomatch.asInstanceOf[js.Any], result = result.asInstanceOf[js.Any], soundend = soundend.asInstanceOf[js.Any], soundstart = soundstart.asInstanceOf[js.Any], speechend = speechend.asInstanceOf[js.Any], speechstart = speechstart.asInstanceOf[js.Any], start = start.asInstanceOf[js.Any])
    __obj.asInstanceOf[SpeechRecognitionEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: SpeechRecognitionEventMap] (val x: Self) extends AnyVal {
    
    inline def setAudioend(value: org.scalajs.dom.Event): Self = StObject.set(x, "audioend", value.asInstanceOf[js.Any])
    
    inline def setAudiostart(value: org.scalajs.dom.Event): Self = StObject.set(x, "audiostart", value.asInstanceOf[js.Any])
    
    inline def setEnd(value: org.scalajs.dom.Event): Self = StObject.set(x, "end", value.asInstanceOf[js.Any])
    
    inline def setError(value: SpeechRecognitionErrorEvent): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setNomatch(value: SpeechRecognitionEvent): Self = StObject.set(x, "nomatch", value.asInstanceOf[js.Any])
    
    inline def setResult(value: SpeechRecognitionEvent): Self = StObject.set(x, "result", value.asInstanceOf[js.Any])
    
    inline def setSoundend(value: org.scalajs.dom.Event): Self = StObject.set(x, "soundend", value.asInstanceOf[js.Any])
    
    inline def setSoundstart(value: org.scalajs.dom.Event): Self = StObject.set(x, "soundstart", value.asInstanceOf[js.Any])
    
    inline def setSpeechend(value: org.scalajs.dom.Event): Self = StObject.set(x, "speechend", value.asInstanceOf[js.Any])
    
    inline def setSpeechstart(value: org.scalajs.dom.Event): Self = StObject.set(x, "speechstart", value.asInstanceOf[js.Any])
    
    inline def setStart(value: org.scalajs.dom.Event): Self = StObject.set(x, "start", value.asInstanceOf[js.Any])
  }
}

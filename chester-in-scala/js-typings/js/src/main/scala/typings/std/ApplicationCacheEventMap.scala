package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ApplicationCacheEventMap extends StObject {
  
  /* standard dom */
  var cached: org.scalajs.dom.Event
  
  /* standard dom */
  var checking: org.scalajs.dom.Event
  
  /* standard dom */
  var downloading: org.scalajs.dom.Event
  
  /* standard dom */
  var error: org.scalajs.dom.Event
  
  /* standard dom */
  var noupdate: org.scalajs.dom.Event
  
  /* standard dom */
  var obsolete: org.scalajs.dom.Event
  
  /* standard dom */
  var progress: org.scalajs.dom.ProgressEvent
  
  /* standard dom */
  var updateready: org.scalajs.dom.Event
}
object ApplicationCacheEventMap {
  
  inline def apply(
    cached: org.scalajs.dom.Event,
    checking: org.scalajs.dom.Event,
    downloading: org.scalajs.dom.Event,
    error: org.scalajs.dom.Event,
    noupdate: org.scalajs.dom.Event,
    obsolete: org.scalajs.dom.Event,
    progress: org.scalajs.dom.ProgressEvent,
    updateready: org.scalajs.dom.Event
  ): ApplicationCacheEventMap = {
    val __obj = js.Dynamic.literal(cached = cached.asInstanceOf[js.Any], checking = checking.asInstanceOf[js.Any], downloading = downloading.asInstanceOf[js.Any], error = error.asInstanceOf[js.Any], noupdate = noupdate.asInstanceOf[js.Any], obsolete = obsolete.asInstanceOf[js.Any], progress = progress.asInstanceOf[js.Any], updateready = updateready.asInstanceOf[js.Any])
    __obj.asInstanceOf[ApplicationCacheEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ApplicationCacheEventMap] (val x: Self) extends AnyVal {
    
    inline def setCached(value: org.scalajs.dom.Event): Self = StObject.set(x, "cached", value.asInstanceOf[js.Any])
    
    inline def setChecking(value: org.scalajs.dom.Event): Self = StObject.set(x, "checking", value.asInstanceOf[js.Any])
    
    inline def setDownloading(value: org.scalajs.dom.Event): Self = StObject.set(x, "downloading", value.asInstanceOf[js.Any])
    
    inline def setError(value: org.scalajs.dom.Event): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setNoupdate(value: org.scalajs.dom.Event): Self = StObject.set(x, "noupdate", value.asInstanceOf[js.Any])
    
    inline def setObsolete(value: org.scalajs.dom.Event): Self = StObject.set(x, "obsolete", value.asInstanceOf[js.Any])
    
    inline def setProgress(value: org.scalajs.dom.ProgressEvent): Self = StObject.set(x, "progress", value.asInstanceOf[js.Any])
    
    inline def setUpdateready(value: org.scalajs.dom.Event): Self = StObject.set(x, "updateready", value.asInstanceOf[js.Any])
  }
}

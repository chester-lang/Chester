package typings.node.streamMod

import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PipelineOptions extends StObject {
  
  var end: js.UndefOr[Boolean] = js.undefined
  
  var signal: js.UndefOr[AbortSignal] = js.undefined
}
object PipelineOptions {
  
  inline def apply(): PipelineOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PipelineOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PipelineOptions] (val x: Self) extends AnyVal {
    
    inline def setEnd(value: Boolean): Self = StObject.set(x, "end", value.asInstanceOf[js.Any])
    
    inline def setEndUndefined: Self = StObject.set(x, "end", js.undefined)
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
  }
}

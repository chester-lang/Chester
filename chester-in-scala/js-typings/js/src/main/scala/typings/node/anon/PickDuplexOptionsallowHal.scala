package typings.node.anon

import typings.node.bufferMod.global.BufferEncoding
import typings.node.globalsMod.global.AbortSignal
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Inlined std.Pick<node.stream.DuplexOptions, 'allowHalfOpen' | 'decodeStrings' | 'encoding' | 'highWaterMark' | 'objectMode' | 'signal'> */
trait PickDuplexOptionsallowHal extends StObject {
  
  var allowHalfOpen: js.UndefOr[Boolean] = js.undefined
  
  var decodeStrings: js.UndefOr[Boolean] = js.undefined
  
  var encoding: js.UndefOr[BufferEncoding] = js.undefined
  
  var highWaterMark: js.UndefOr[Double] = js.undefined
  
  var objectMode: js.UndefOr[Boolean] = js.undefined
  
  var signal: js.UndefOr[AbortSignal] = js.undefined
}
object PickDuplexOptionsallowHal {
  
  inline def apply(): PickDuplexOptionsallowHal = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PickDuplexOptionsallowHal]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PickDuplexOptionsallowHal] (val x: Self) extends AnyVal {
    
    inline def setAllowHalfOpen(value: Boolean): Self = StObject.set(x, "allowHalfOpen", value.asInstanceOf[js.Any])
    
    inline def setAllowHalfOpenUndefined: Self = StObject.set(x, "allowHalfOpen", js.undefined)
    
    inline def setDecodeStrings(value: Boolean): Self = StObject.set(x, "decodeStrings", value.asInstanceOf[js.Any])
    
    inline def setDecodeStringsUndefined: Self = StObject.set(x, "decodeStrings", js.undefined)
    
    inline def setEncoding(value: BufferEncoding): Self = StObject.set(x, "encoding", value.asInstanceOf[js.Any])
    
    inline def setEncodingUndefined: Self = StObject.set(x, "encoding", js.undefined)
    
    inline def setHighWaterMark(value: Double): Self = StObject.set(x, "highWaterMark", value.asInstanceOf[js.Any])
    
    inline def setHighWaterMarkUndefined: Self = StObject.set(x, "highWaterMark", js.undefined)
    
    inline def setObjectMode(value: Boolean): Self = StObject.set(x, "objectMode", value.asInstanceOf[js.Any])
    
    inline def setObjectModeUndefined: Self = StObject.set(x, "objectMode", js.undefined)
    
    inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
  }
}

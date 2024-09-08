package typings.node.anon

import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.OpenMode
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* Inlined node.node:fs.ObjectEncodingOptions & node.fs/promises.FlagAndOpenMode & {  flush :boolean | undefined} */
trait ObjectEncodingOptionsFlag_ extends StObject {
  
  var encoding: js.UndefOr[BufferEncoding | Null] = js.undefined
  
  var flag: js.UndefOr[OpenMode] = js.undefined
  
  var flush: js.UndefOr[Boolean] = js.undefined
  
  var mode: js.UndefOr[typings.node.fsMod.Mode] = js.undefined
}
object ObjectEncodingOptionsFlag_ {
  
  inline def apply(): ObjectEncodingOptionsFlag_ = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ObjectEncodingOptionsFlag_]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ObjectEncodingOptionsFlag_] (val x: Self) extends AnyVal {
    
    inline def setEncoding(value: BufferEncoding): Self = StObject.set(x, "encoding", value.asInstanceOf[js.Any])
    
    inline def setEncodingNull: Self = StObject.set(x, "encoding", null)
    
    inline def setEncodingUndefined: Self = StObject.set(x, "encoding", js.undefined)
    
    inline def setFlag(value: OpenMode): Self = StObject.set(x, "flag", value.asInstanceOf[js.Any])
    
    inline def setFlagUndefined: Self = StObject.set(x, "flag", js.undefined)
    
    inline def setFlush(value: Boolean): Self = StObject.set(x, "flush", value.asInstanceOf[js.Any])
    
    inline def setFlushUndefined: Self = StObject.set(x, "flush", js.undefined)
    
    inline def setMode(value: typings.node.fsMod.Mode): Self = StObject.set(x, "mode", value.asInstanceOf[js.Any])
    
    inline def setModeUndefined: Self = StObject.set(x, "mode", js.undefined)
  }
}

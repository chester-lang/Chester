package typings.node.fsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait WriteStreamOptions
  extends StObject
     with StreamOptions {
  
  var flush: js.UndefOr[Boolean] = js.undefined
  
  var fs: js.UndefOr[CreateWriteStreamFSImplementation | Null] = js.undefined
}
object WriteStreamOptions {
  
  inline def apply(): WriteStreamOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[WriteStreamOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: WriteStreamOptions] (val x: Self) extends AnyVal {
    
    inline def setFlush(value: Boolean): Self = StObject.set(x, "flush", value.asInstanceOf[js.Any])
    
    inline def setFlushUndefined: Self = StObject.set(x, "flush", js.undefined)
    
    inline def setFs(value: CreateWriteStreamFSImplementation): Self = StObject.set(x, "fs", value.asInstanceOf[js.Any])
    
    inline def setFsNull: Self = StObject.set(x, "fs", null)
    
    inline def setFsUndefined: Self = StObject.set(x, "fs", js.undefined)
  }
}

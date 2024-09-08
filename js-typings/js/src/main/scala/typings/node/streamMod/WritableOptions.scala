package typings.node.streamMod

import typings.node.anon.Chunk
import typings.node.bufferMod.global.BufferEncoding
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait WritableOptions
  extends StObject
     with StreamOptions[Writable] {
  
  var decodeStrings: js.UndefOr[Boolean] = js.undefined
  
  var defaultEncoding: js.UndefOr[BufferEncoding] = js.undefined
  
  var `final`: js.UndefOr[
    js.ThisFunction1[
      /* this */ Writable, 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var write: js.UndefOr[
    js.ThisFunction3[
      /* this */ Writable, 
      /* chunk */ Any, 
      /* encoding */ BufferEncoding, 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var writev: js.UndefOr[
    js.ThisFunction2[
      /* this */ Writable, 
      /* chunks */ js.Array[Chunk], 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
}
object WritableOptions {
  
  inline def apply(): WritableOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[WritableOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: WritableOptions] (val x: Self) extends AnyVal {
    
    inline def setDecodeStrings(value: Boolean): Self = StObject.set(x, "decodeStrings", value.asInstanceOf[js.Any])
    
    inline def setDecodeStringsUndefined: Self = StObject.set(x, "decodeStrings", js.undefined)
    
    inline def setDefaultEncoding(value: BufferEncoding): Self = StObject.set(x, "defaultEncoding", value.asInstanceOf[js.Any])
    
    inline def setDefaultEncodingUndefined: Self = StObject.set(x, "defaultEncoding", js.undefined)
    
    inline def setFinal(
      value: js.ThisFunction1[
          /* this */ Writable, 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "final", value.asInstanceOf[js.Any])
    
    inline def setFinalUndefined: Self = StObject.set(x, "final", js.undefined)
    
    inline def setWrite(
      value: js.ThisFunction3[
          /* this */ Writable, 
          /* chunk */ Any, 
          /* encoding */ BufferEncoding, 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "write", value.asInstanceOf[js.Any])
    
    inline def setWriteUndefined: Self = StObject.set(x, "write", js.undefined)
    
    inline def setWritev(
      value: js.ThisFunction2[
          /* this */ Writable, 
          /* chunks */ js.Array[Chunk], 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "writev", value.asInstanceOf[js.Any])
    
    inline def setWritevUndefined: Self = StObject.set(x, "writev", js.undefined)
  }
}

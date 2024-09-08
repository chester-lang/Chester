package typings.node.streamMod

import typings.node.bufferMod.global.BufferEncoding
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ReadableOptions
  extends StObject
     with StreamOptions[Readable] {
  
  var encoding: js.UndefOr[BufferEncoding] = js.undefined
  
  var read: js.UndefOr[js.ThisFunction1[/* this */ Readable, /* size */ Double, Unit]] = js.undefined
}
object ReadableOptions {
  
  inline def apply(): ReadableOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ReadableOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ReadableOptions] (val x: Self) extends AnyVal {
    
    inline def setEncoding(value: BufferEncoding): Self = StObject.set(x, "encoding", value.asInstanceOf[js.Any])
    
    inline def setEncodingUndefined: Self = StObject.set(x, "encoding", js.undefined)
    
    inline def setRead(value: js.ThisFunction1[/* this */ Readable, /* size */ Double, Unit]): Self = StObject.set(x, "read", value.asInstanceOf[js.Any])
    
    inline def setReadUndefined: Self = StObject.set(x, "read", js.undefined)
  }
}

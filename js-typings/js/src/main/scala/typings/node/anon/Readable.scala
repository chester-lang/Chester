package typings.node.anon

import typings.node.streamWebMod.ReadableStream
import typings.node.streamWebMod.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Readable extends StObject {
  
  var readable: ReadableStream[Any]
  
  var writable: WritableStream[Any]
}
object Readable {
  
  inline def apply(readable: ReadableStream[Any], writable: WritableStream[Any]): Readable = {
    val __obj = js.Dynamic.literal(readable = readable.asInstanceOf[js.Any], writable = writable.asInstanceOf[js.Any])
    __obj.asInstanceOf[Readable]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Readable] (val x: Self) extends AnyVal {
    
    inline def setReadable(value: ReadableStream[Any]): Self = StObject.set(x, "readable", value.asInstanceOf[js.Any])
    
    inline def setWritable(value: WritableStream[Any]): Self = StObject.set(x, "writable", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import typings.node.bufferMod.global.BufferEncoding
import typings.node.nodeColonstringDecoderMod.StringDecoder
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedNodeStrin extends StObject {
  
  var StringDecoder: Instantiable1[
    /* encoding */ js.UndefOr[BufferEncoding], 
    typings.node.nodeColonstringDecoderMod.StringDecoder
  ]
}
object TypeofimportedNodeStrin {
  
  inline def apply(StringDecoder: Instantiable1[/* encoding */ js.UndefOr[BufferEncoding], StringDecoder]): TypeofimportedNodeStrin = {
    val __obj = js.Dynamic.literal(StringDecoder = StringDecoder.asInstanceOf[js.Any])
    __obj.asInstanceOf[TypeofimportedNodeStrin]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedNodeStrin] (val x: Self) extends AnyVal {
    
    inline def setStringDecoder(value: Instantiable1[/* encoding */ js.UndefOr[BufferEncoding], StringDecoder]): Self = StObject.set(x, "StringDecoder", value.asInstanceOf[js.Any])
  }
}

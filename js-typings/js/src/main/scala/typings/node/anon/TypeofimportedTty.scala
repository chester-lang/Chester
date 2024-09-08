package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import typings.node.netMod.SocketConstructorOpts
import typings.node.ttyMod.ReadStream
import typings.node.ttyMod.WriteStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedTty extends StObject {
  
  /**
    * Represents the readable side of a TTY. In normal circumstances `process.stdin` will be the only `tty.ReadStream` instance in a Node.js
    * process and there should be no reason to create additional instances.
    * @since v0.5.8
    */
  var ReadStream: Instantiable2[
    /* fd */ Double, 
    /* options */ js.UndefOr[SocketConstructorOpts], 
    typings.node.ttyMod.ReadStream
  ]
  
  /**
    * Represents the writable side of a TTY. In normal circumstances, `process.stdout` and `process.stderr` will be the only`tty.WriteStream` instances created for a Node.js process and there
    * should be no reason to create additional instances.
    * @since v0.5.8
    */
  var WriteStream: Instantiable1[/* fd */ Double, typings.node.ttyMod.WriteStream]
  
  /**
    * The `tty.isatty()` method returns `true` if the given `fd` is associated with
    * a TTY and `false` if it is not, including whenever `fd` is not a non-negative
    * integer.
    * @since v0.5.8
    * @param fd A numeric file descriptor
    */
  def isatty(fd: Double): Boolean
}
object TypeofimportedTty {
  
  inline def apply(
    ReadStream: Instantiable2[/* fd */ Double, /* options */ js.UndefOr[SocketConstructorOpts], ReadStream],
    WriteStream: Instantiable1[/* fd */ Double, WriteStream],
    isatty: Double => Boolean
  ): TypeofimportedTty = {
    val __obj = js.Dynamic.literal(ReadStream = ReadStream.asInstanceOf[js.Any], WriteStream = WriteStream.asInstanceOf[js.Any], isatty = js.Any.fromFunction1(isatty))
    __obj.asInstanceOf[TypeofimportedTty]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedTty] (val x: Self) extends AnyVal {
    
    inline def setIsatty(value: Double => Boolean): Self = StObject.set(x, "isatty", js.Any.fromFunction1(value))
    
    inline def setReadStream(value: Instantiable2[/* fd */ Double, /* options */ js.UndefOr[SocketConstructorOpts], ReadStream]): Self = StObject.set(x, "ReadStream", value.asInstanceOf[js.Any])
    
    inline def setWriteStream(value: Instantiable1[/* fd */ Double, WriteStream]): Self = StObject.set(x, "WriteStream", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import typings.node.fsMod.BufferEncodingOption
import typings.node.fsMod.EncodingOption
import typings.node.fsMod.PathLike
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofreadlinkPromisify extends StObject {
  
  def apply(
    path: PathLike,
    callback: js.Function2[/* err */ ErrnoException | Null, /* linkString */ String, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: BufferEncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* linkString */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: EncodingOption,
    callback: js.Function2[
      ErrnoException | Null, 
      typings.node.bufferMod.global.Buffer | (/* linkString */ String), 
      Unit
    ]
  ): Unit = js.native
  
  /**
    * Asynchronous readlink(2) - read value of a symbolic link.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(path: PathLike): js.Promise[String] = js.native
  /**
    * Asynchronous readlink(2) - read value of a symbolic link.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(path: PathLike, options: BufferEncodingOption): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(path: PathLike, options: EncodingOption): js.Promise[String] = js.native
}

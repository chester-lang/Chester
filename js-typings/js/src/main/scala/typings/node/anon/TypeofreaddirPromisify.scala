package typings.node.anon

import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.Dirent
import typings.node.fsMod.PathLike
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.nodeStrings.buffer_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofreaddirPromisify extends StObject {
  
  def apply(
    path: PathLike,
    callback: js.Function2[/* err */ ErrnoException | Null, /* files */ js.Array[String], Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: Null,
    callback: js.Function2[
      ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: Unit,
    callback: js.Function2[
      ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: Encoding,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer], 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: ObjectEncodingOptionswithEncoding,
    callback: js.Function2[/* err */ ErrnoException | Null, /* files */ js.Array[Dirent], Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: ObjectEncodingOptionswith,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: Recursive,
    callback: js.Function2[/* err */ ErrnoException | Null, /* files */ js.Array[String], Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: BufferEncoding,
    callback: js.Function2[
      ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: buffer_,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer], 
      Unit
    ]
  ): Unit = js.native
  
  /**
    * Asynchronous readdir(3) - read a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(path: PathLike): js.Promise[js.Array[String]] = js.native
  /**
    * Asynchronous readdir(3) - read a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(path: PathLike, options: Encoding): js.Promise[js.Array[typings.node.bufferMod.global.Buffer]] = js.native
  def __promisify__(path: PathLike, options: ObjectEncodingOptionswith): js.Promise[js.Array[typings.node.bufferMod.global.Buffer | String]] = js.native
  /**
    * Asynchronous readdir(3) - read a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options If called with `withFileTypes: true` the result data will be an array of Dirent
    */
  def __promisify__(path: PathLike, options: ObjectEncodingOptionswithEncoding): js.Promise[js.Array[Dirent]] = js.native
  def __promisify__(path: PathLike, options: Recursive): js.Promise[js.Array[String]] = js.native
  def __promisify__(path: PathLike, options: BufferEncoding): js.Promise[js.Array[String]] = js.native
  @JSName("__promisify__")
  def __promisify___buffer(path: PathLike, options: buffer_): js.Promise[js.Array[typings.node.bufferMod.global.Buffer]] = js.native
}

package typings.node.anon

import typings.node.fsMod.BufferEncodingOption
import typings.node.fsMod.EncodingOption
import typings.node.fsMod.PathLike
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofrealpath extends StObject {
  
  def apply(
    path: PathLike,
    options: EncodingOption,
    callback: js.Function2[/* err */ ErrnoException | Null, /* resolvedPath */ String, Unit]
  ): Unit = js.native
  
  /**
    * Asynchronous realpath(3) - return the canonicalized absolute pathname.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(path: PathLike): js.Promise[String] = js.native
  /**
    * Asynchronous realpath(3) - return the canonicalized absolute pathname.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(path: PathLike, options: BufferEncodingOption): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(path: PathLike, options: EncodingOption): js.Promise[String] = js.native
  
  def native(
    path: PathLike,
    callback: js.Function2[/* err */ ErrnoException | Null, /* resolvedPath */ String, Unit]
  ): Unit = js.native
  def native(
    path: PathLike,
    options: BufferEncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* resolvedPath */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  /**
    * Asynchronous [`realpath(3)`](http://man7.org/linux/man-pages/man3/realpath.3.html).
    *
    * The `callback` gets two arguments `(err, resolvedPath)`.
    *
    * Only paths that can be converted to UTF8 strings are supported.
    *
    * The optional `options` argument can be a string specifying an encoding, or an
    * object with an `encoding` property specifying the character encoding to use for
    * the path passed to the callback. If the `encoding` is set to `'buffer'`,
    * the path returned will be passed as a `Buffer` object.
    *
    * On Linux, when Node.js is linked against musl libc, the procfs file system must
    * be mounted on `/proc` in order for this function to work. Glibc does not have
    * this restriction.
    * @since v9.2.0
    */
  def native(
    path: PathLike,
    options: EncodingOption,
    callback: js.Function2[
      ErrnoException | Null, 
      typings.node.bufferMod.global.Buffer | (/* resolvedPath */ String), 
      Unit
    ]
  ): Unit = js.native
}

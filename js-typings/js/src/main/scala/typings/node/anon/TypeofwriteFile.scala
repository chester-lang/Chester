package typings.node.anon

import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.PathOrFileDescriptor
import typings.node.fsMod.WriteFileOptions
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofwriteFile extends StObject {
  
  def apply(file: PathOrFileDescriptor, data: String, options: WriteFileOptions, callback: NoParamCallback): Unit = js.native
  def apply(
    file: PathOrFileDescriptor,
    data: ArrayBufferView,
    options: WriteFileOptions,
    callback: NoParamCallback
  ): Unit = js.native
  
  /**
    * Asynchronously writes data to a file, replacing the file if it already exists.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * URL support is _experimental_.
    * If a file descriptor is provided, the underlying file will _not_ be closed automatically.
    * @param data The data to write. If something other than a Buffer or Uint8Array is provided, the value is coerced to a string.
    * @param options Either the encoding for the file, or an object optionally specifying the encoding, file mode, and flag.
    * If `encoding` is not supplied, the default of `'utf8'` is used.
    * If `mode` is not supplied, the default of `0o666` is used.
    * If `mode` is a string, it is parsed as an octal integer.
    * If `flag` is not supplied, the default of `'w'` is used.
    */
  def __promisify__(path: PathOrFileDescriptor, data: String): js.Promise[Unit] = js.native
  def __promisify__(path: PathOrFileDescriptor, data: String, options: WriteFileOptions): js.Promise[Unit] = js.native
  def __promisify__(path: PathOrFileDescriptor, data: ArrayBufferView): js.Promise[Unit] = js.native
  def __promisify__(path: PathOrFileDescriptor, data: ArrayBufferView, options: WriteFileOptions): js.Promise[Unit] = js.native
}

package typings.node.anon

import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.PathOrFileDescriptor
import typings.node.fsMod.WriteFileOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofappendFilePromisify extends StObject {
  
  def apply(file: PathOrFileDescriptor, data: String, callback: NoParamCallback): Unit = js.native
  def apply(file: PathOrFileDescriptor, data: js.typedarray.Uint8Array, callback: NoParamCallback): Unit = js.native
  def apply(path: PathOrFileDescriptor, data: String, options: WriteFileOptions, callback: NoParamCallback): Unit = js.native
  def apply(
    path: PathOrFileDescriptor,
    data: js.typedarray.Uint8Array,
    options: WriteFileOptions,
    callback: NoParamCallback
  ): Unit = js.native
  
  /**
    * Asynchronously append data to a file, creating the file if it does not exist.
    * @param file A path to a file. If a URL is provided, it must use the `file:` protocol.
    * URL support is _experimental_.
    * If a file descriptor is provided, the underlying file will _not_ be closed automatically.
    * @param data The data to write. If something other than a Buffer or Uint8Array is provided, the value is coerced to a string.
    * @param options Either the encoding for the file, or an object optionally specifying the encoding, file mode, and flag.
    * If `encoding` is not supplied, the default of `'utf8'` is used.
    * If `mode` is not supplied, the default of `0o666` is used.
    * If `mode` is a string, it is parsed as an octal integer.
    * If `flag` is not supplied, the default of `'a'` is used.
    */
  def __promisify__(file: PathOrFileDescriptor, data: String): js.Promise[Unit] = js.native
  def __promisify__(file: PathOrFileDescriptor, data: String, options: WriteFileOptions): js.Promise[Unit] = js.native
  def __promisify__(file: PathOrFileDescriptor, data: js.typedarray.Uint8Array): js.Promise[Unit] = js.native
  def __promisify__(file: PathOrFileDescriptor, data: js.typedarray.Uint8Array, options: WriteFileOptions): js.Promise[Unit] = js.native
}

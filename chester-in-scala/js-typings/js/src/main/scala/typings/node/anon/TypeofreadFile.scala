package typings.node.anon

import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.PathOrFileDescriptor
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofreadFile extends StObject {
  
  def apply(
    path: PathOrFileDescriptor,
    options: Null,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathOrFileDescriptor,
    options: Unit,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def apply(
    path: PathOrFileDescriptor,
    options: encodingnullundefinedflagEncoding,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  
  /**
    * Asynchronously reads the entire contents of a file.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * If a file descriptor is provided, the underlying file will _not_ be closed automatically.
    * @param options An object that may contain an optional flag.
    * If a flag is not provided, it defaults to `'r'`.
    */
  /**
    * Asynchronously reads the entire contents of a file.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * URL support is _experimental_.
    * If a file descriptor is provided, the underlying file will _not_ be closed automatically.
    * @param options Either the encoding for the result, or an object that contains the encoding and an optional flag.
    * If a flag is not provided, it defaults to `'r'`.
    */
  def __promisify__(path: PathOrFileDescriptor): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  /**
    * Asynchronously reads the entire contents of a file.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * URL support is _experimental_.
    * If a file descriptor is provided, the underlying file will _not_ be closed automatically.
    * @param options Either the encoding for the result, or an object that contains the encoding and an optional flag.
    * If a flag is not provided, it defaults to `'r'`.
    */
  def __promisify__(path: PathOrFileDescriptor, options: EncodingFlag): js.Promise[String] = js.native
  def __promisify__(path: PathOrFileDescriptor, options: Flag): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(path: PathOrFileDescriptor, options: ObjectEncodingOptionsflagEncoding): js.Promise[String | typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(path: PathOrFileDescriptor, options: BufferEncoding): js.Promise[String] = js.native
}

package typings.node.anon

import typings.node.nodeColonbufferMod.Blob
import typings.node.streamMod.Duplex
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofDuplex extends StObject {
  
  def from(src: String): Duplex = js.native
  def from(src: js.Iterable[Any]): Duplex = js.native
  def from(src: js.Object): Duplex = js.native
  def from(src: js.Promise[Any]): Duplex = js.native
  def from(src: js.typedarray.ArrayBuffer): Duplex = js.native
  def from(
    src: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): Duplex = js.native
  def from(src: Blob): Duplex = js.native
  /**
    * A utility method for creating duplex streams.
    *
    * - `Stream` converts writable stream into writable `Duplex` and readable stream
    *   to `Duplex`.
    * - `Blob` converts into readable `Duplex`.
    * - `string` converts into readable `Duplex`.
    * - `ArrayBuffer` converts into readable `Duplex`.
    * - `AsyncIterable` converts into a readable `Duplex`. Cannot yield `null`.
    * - `AsyncGeneratorFunction` converts into a readable/writable transform
    *   `Duplex`. Must take a source `AsyncIterable` as first parameter. Cannot yield
    *   `null`.
    * - `AsyncFunction` converts into a writable `Duplex`. Must return
    *   either `null` or `undefined`
    * - `Object ({ writable, readable })` converts `readable` and
    *   `writable` into `Stream` and then combines them into `Duplex` where the
    *   `Duplex` will write to the `writable` and read from the `readable`.
    * - `Promise` converts into readable `Duplex`. Value `null` is ignored.
    *
    * @since v16.8.0
    */
  /* static member */
  def from(src: typings.node.streamMod.Stream): Duplex = js.native
  
  /**
    * A utility method for creating a `Duplex` from a web `ReadableStream` and `WritableStream`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  def fromWeb(duplexStream: Readable): Duplex = js.native
  def fromWeb(duplexStream: Readable, options: PickDuplexOptionsallowHal): Duplex = js.native
  
  /**
    * A utility method for creating a web `ReadableStream` and `WritableStream` from a `Duplex`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  def toWeb(streamDuplex: Duplex): Readable = js.native
}

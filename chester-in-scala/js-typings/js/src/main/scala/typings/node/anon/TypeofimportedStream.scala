package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.streamMod.DuplexOptions
import typings.node.streamMod.ReadableOptions
import typings.node.streamMod.TransformOptions
import typings.node.streamMod.Writable
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedStream extends StObject {
  
  /**
    * Duplex streams are streams that implement both the `Readable` and `Writable` interfaces.
    *
    * Examples of `Duplex` streams include:
    *
    * * `TCP sockets`
    * * `zlib streams`
    * * `crypto streams`
    * @since v0.9.4
    */
  var Duplex: Instantiable1[/* opts */ js.UndefOr[DuplexOptions], typings.node.streamMod.Duplex] = js.native
  
  /**
    * The `stream.PassThrough` class is a trivial implementation of a `Transform` stream that simply passes the input bytes across to the output. Its purpose is
    * primarily for examples and testing, but there are some use cases where `stream.PassThrough` is useful as a building block for novel sorts of streams.
    */
  var PassThrough: Instantiable0[typings.node.streamMod.PassThrough] = js.native
  
  /**
    * @since v0.9.4
    */
  var Readable: Instantiable0[typings.node.streamMod.Readable] = js.native
  
  val ReadableBase: Any = js.native
  
  var Stream: Instantiable1[/* opts */ js.UndefOr[ReadableOptions], typings.node.streamMod.Stream] = js.native
  
  /**
    * Transform streams are `Duplex` streams where the output is in some way
    * related to the input. Like all `Duplex` streams, `Transform` streams
    * implement both the `Readable` and `Writable` interfaces.
    *
    * Examples of `Transform` streams include:
    *
    * * `zlib streams`
    * * `crypto streams`
    * @since v0.9.4
    */
  var Transform: Instantiable1[/* opts */ js.UndefOr[TransformOptions], typings.node.streamMod.Transform] = js.native
  
  /**
    * @since v0.9.4
    */
  var Writable: Instantiable0[typings.node.streamMod.Writable] = js.native
  
  /**
    * A stream to attach a signal to.
    *
    * Attaches an AbortSignal to a readable or writeable stream. This lets code
    * control stream destruction using an `AbortController`.
    *
    * Calling `abort` on the `AbortController` corresponding to the passed `AbortSignal` will behave the same way as calling `.destroy(new AbortError())` on the
    * stream, and `controller.error(new AbortError())` for webstreams.
    *
    * ```js
    * const fs = require('node:fs');
    *
    * const controller = new AbortController();
    * const read = addAbortSignal(
    *   controller.signal,
    *   fs.createReadStream(('object.json')),
    * );
    * // Later, abort the operation closing the stream
    * controller.abort();
    * ```
    *
    * Or using an `AbortSignal` with a readable stream as an async iterable:
    *
    * ```js
    * const controller = new AbortController();
    * setTimeout(() => controller.abort(), 10_000); // set a timeout
    * const stream = addAbortSignal(
    *   controller.signal,
    *   fs.createReadStream(('object.json')),
    * );
    * (async () => {
    *   try {
    *     for await (const chunk of stream) {
    *       await process(chunk);
    *     }
    *   } catch (e) {
    *     if (e.name === 'AbortError') {
    *       // The operation was cancelled
    *     } else {
    *       throw e;
    *     }
    *   }
    * })();
    * ```
    *
    * Or using an `AbortSignal` with a ReadableStream:
    *
    * ```js
    * const controller = new AbortController();
    * const rs = new ReadableStream({
    *   start(controller) {
    *     controller.enqueue('hello');
    *     controller.enqueue('world');
    *     controller.close();
    *   },
    * });
    *
    * addAbortSignal(controller.signal, rs);
    *
    * finished(rs, (err) => {
    *   if (err) {
    *     if (err.name === 'AbortError') {
    *       // The operation was cancelled
    *     }
    *   }
    * });
    *
    * const reader = rs.getReader();
    *
    * reader.read().then(({ value, done }) => {
    *   console.log(value); // hello
    *   console.log(done); // false
    *   controller.abort();
    * });
    * ```
    * @since v15.4.0
    * @param signal A signal representing possible cancellation
    * @param stream A stream to attach a signal to.
    */
  def addAbortSignal[T /* <: typings.node.streamMod.Stream */](signal: AbortSignal, stream: T): T = js.native
  
  val finished: Typeoffinished = js.native
  
  /**
    * Returns the default highWaterMark used by streams.
    * Defaults to `65536` (64 KiB), or `16` for `objectMode`.
    * @since v19.9.0
    */
  def getDefaultHighWaterMark(objectMode: Boolean): Double = js.native
  
  def isErrored(stream: ReadableStream): Boolean = js.native
  def isErrored(stream: WritableStream): Boolean = js.native
  /**
    * Returns whether the stream has encountered an error.
    * @since v17.3.0, v16.14.0
    * @experimental
    */
  def isErrored(stream: typings.node.streamMod.Readable): Boolean = js.native
  def isErrored(stream: Writable): Boolean = js.native
  
  def isReadable(stream: ReadableStream): Boolean = js.native
  /**
    * Returns whether the stream is readable.
    * @since v17.4.0, v16.14.0
    * @experimental
    */
  def isReadable(stream: typings.node.streamMod.Readable): Boolean = js.native
  
  val pipeline: Typeofpipeline = js.native
  
  /**
    * Sets the default highWaterMark used by streams.
    * @since v19.9.0
    * @param value highWaterMark value
    */
  def setDefaultHighWaterMark(objectMode: Boolean, value: Double): Unit = js.native
}

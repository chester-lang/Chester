package typings.node.streamMod

import typings.node.anon.Chunk
import typings.node.anon.PickDuplexOptionsallowHal
import typings.node.bufferMod.global.BufferEncoding
import typings.node.nodeColonbufferMod.Blob
import typings.node.nodeStrings.close
import typings.node.nodeStrings.data
import typings.node.nodeStrings.drain
import typings.node.nodeStrings.end
import typings.node.nodeStrings.error
import typings.node.nodeStrings.finish
import typings.node.nodeStrings.pause
import typings.node.nodeStrings.pipe
import typings.node.nodeStrings.readable
import typings.node.nodeStrings.resume
import typings.node.nodeStrings.unpipe
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

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
@JSImport("stream", "Duplex")
@js.native
open class Duplex () extends StObject {
  def this(opts: DuplexOptions) = this()
  
  def _destroy(error: js.Error, callback: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Unit = js.native
  def _destroy(error: Null, callback: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Unit = js.native
  
  def _final(callback: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Unit = js.native
  
  def _write(
    chunk: Any,
    encoding: BufferEncoding,
    callback: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]
  ): Unit = js.native
  
  var _writev: js.UndefOr[
    js.Function2[
      /* chunks */ js.Array[Chunk], 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.native
  
  def addListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def addListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  /**
    * Event emitter
    * The defined events on documents including:
    * 1.  close
    * 2.  data
    * 3.  drain
    * 4.  end
    * 5.  error
    * 6.  finish
    * 7.  pause
    * 8.  pipe
    * 9.  readable
    * 10. resume
    * 11. unpipe
    */
  @JSName("addListener")
  def addListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_drain(event: drain, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_finish(event: finish, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_pipe(event: pipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_unpipe(event: unpipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  
  /**
    * If `false` then the stream will automatically end the writable side when the
    * readable side ends. Set initially by the `allowHalfOpen` constructor option,
    * which defaults to `true`.
    *
    * This can be changed manually to change the half-open behavior of an existing
    * `Duplex` stream instance, but must be changed before the `'end'` event is emitted.
    * @since v0.9.4
    */
  var allowHalfOpen: Boolean = js.native
  
  val closed: Boolean = js.native
  
  def cork(): Unit = js.native
  
  def emit(event: String, args: Any*): Boolean = js.native
  def emit(event: js.Symbol, args: Any*): Boolean = js.native
  @JSName("emit")
  def emit_close(event: close): Boolean = js.native
  @JSName("emit")
  def emit_data(event: data, chunk: Any): Boolean = js.native
  @JSName("emit")
  def emit_drain(event: drain): Boolean = js.native
  @JSName("emit")
  def emit_end(event: end): Boolean = js.native
  @JSName("emit")
  def emit_error(event: error, err: js.Error): Boolean = js.native
  @JSName("emit")
  def emit_finish(event: finish): Boolean = js.native
  @JSName("emit")
  def emit_pause(event: pause): Boolean = js.native
  @JSName("emit")
  def emit_pipe(event: pipe, src: Readable): Boolean = js.native
  @JSName("emit")
  def emit_readable(event: readable): Boolean = js.native
  @JSName("emit")
  def emit_resume(event: resume): Boolean = js.native
  @JSName("emit")
  def emit_unpipe(event: unpipe, src: Readable): Boolean = js.native
  
  def end(): this.type = js.native
  def end(cb: js.Function0[Unit]): this.type = js.native
  def end(chunk: Any): this.type = js.native
  def end(chunk: Any, cb: js.Function0[Unit]): this.type = js.native
  def end(chunk: Any, encoding: Unit, cb: js.Function0[Unit]): this.type = js.native
  def end(chunk: Any, encoding: BufferEncoding): this.type = js.native
  def end(chunk: Any, encoding: BufferEncoding, cb: js.Function0[Unit]): this.type = js.native
  
  val errored: js.Error | Null = js.native
  
  def on(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def on(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("on")
  def on_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("on")
  def on_drain(event: drain, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("on")
  def on_finish(event: finish, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_pipe(event: pipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  @JSName("on")
  def on_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_unpipe(event: unpipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  
  def once(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def once(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("once")
  def once_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("once")
  def once_drain(event: drain, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("once")
  def once_finish(event: finish, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_pipe(event: pipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  @JSName("once")
  def once_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_unpipe(event: unpipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  
  def prependListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def prependListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_drain(event: drain, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_finish(event: finish, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_pipe(event: pipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_unpipe(event: unpipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  
  def prependOnceListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def prependOnceListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_drain(event: drain, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_finish(event: finish, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_pipe(event: pipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_unpipe(event: unpipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  
  def removeListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def removeListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_drain(event: drain, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_finish(event: finish, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_pipe(event: pipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_unpipe(event: unpipe, listener: js.Function1[/* src */ Readable, Unit]): this.type = js.native
  
  def setDefaultEncoding(encoding: BufferEncoding): this.type = js.native
  
  def uncork(): Unit = js.native
  
  val writable: Boolean = js.native
  
  val writableCorked: Double = js.native
  
  val writableEnded: Boolean = js.native
  
  val writableFinished: Boolean = js.native
  
  val writableHighWaterMark: Double = js.native
  
  val writableLength: Double = js.native
  
  val writableNeedDrain: Boolean = js.native
  
  val writableObjectMode: Boolean = js.native
  
  def write(chunk: Any): Boolean = js.native
  def write(chunk: Any, cb: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Boolean = js.native
  def write(chunk: Any, encoding: Unit, cb: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Boolean = js.native
  def write(chunk: Any, encoding: BufferEncoding): Boolean = js.native
  def write(
    chunk: Any,
    encoding: BufferEncoding,
    cb: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]
  ): Boolean = js.native
}
object Duplex {
  
  @JSImport("stream", "Duplex")
  @js.native
  val ^ : js.Any = js.native
  
  inline def from(src: String): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def from(src: js.Iterable[Any]): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def from(src: js.Object): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def from(src: js.Promise[Any]): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def from(src: js.typedarray.ArrayBuffer): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def from(
    src: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncGeneratorFunction */ Any
  ): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def from(src: Blob): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
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
  inline def from(src: Stream): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("from")(src.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  
  /**
    * A utility method for creating a `Duplex` from a web `ReadableStream` and `WritableStream`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  inline def fromWeb(duplexStream: typings.node.anon.Readable): Duplex = ^.asInstanceOf[js.Dynamic].applyDynamic("fromWeb")(duplexStream.asInstanceOf[js.Any]).asInstanceOf[Duplex]
  inline def fromWeb(duplexStream: typings.node.anon.Readable, options: PickDuplexOptionsallowHal): Duplex = (^.asInstanceOf[js.Dynamic].applyDynamic("fromWeb")(duplexStream.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Duplex]
  
  /**
    * A utility method for creating a web `ReadableStream` and `WritableStream` from a `Duplex`.
    * @since v17.0.0
    * @experimental
    */
  /* static member */
  inline def toWeb(streamDuplex: Duplex): typings.node.anon.Readable = ^.asInstanceOf[js.Dynamic].applyDynamic("toWeb")(streamDuplex.asInstanceOf[js.Any]).asInstanceOf[typings.node.anon.Readable]
}

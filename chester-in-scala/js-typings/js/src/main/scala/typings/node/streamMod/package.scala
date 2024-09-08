package typings.node.streamMod

import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.globalsMod.global.NodeJS.ReadWriteStream
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}


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
inline def addAbortSignal[T /* <: Stream */](signal: AbortSignal, stream: T): T = (^.asInstanceOf[js.Dynamic].applyDynamic("addAbortSignal")(signal.asInstanceOf[js.Any], stream.asInstanceOf[js.Any])).asInstanceOf[T]

inline def finished(stream: ReadWriteStream, callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]): js.Function0[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("finished")(stream.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[js.Function0[Unit]]
inline def finished(
  stream: ReadWriteStream,
  options: FinishedOptions,
  callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]
): js.Function0[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("finished")(stream.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[js.Function0[Unit]]
inline def finished(stream: ReadableStream, callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]): js.Function0[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("finished")(stream.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[js.Function0[Unit]]
/**
  * A readable and/or writable stream/webstream.
  *
  * A function to get notified when a stream is no longer readable, writable
  * or has experienced an error or a premature close event.
  *
  * ```js
  * const { finished } = require('node:stream');
  * const fs = require('node:fs');
  *
  * const rs = fs.createReadStream('archive.tar');
  *
  * finished(rs, (err) => {
  *   if (err) {
  *     console.error('Stream failed.', err);
  *   } else {
  *     console.log('Stream is done reading.');
  *   }
  * });
  *
  * rs.resume(); // Drain the stream.
  * ```
  *
  * Especially useful in error handling scenarios where a stream is destroyed
  * prematurely (like an aborted HTTP request), and will not emit `'end'` or `'finish'`.
  *
  * The `finished` API provides [`promise version`](https://nodejs.org/docs/latest-v22.x/api/stream.html#streamfinishedstream-options).
  *
  * `stream.finished()` leaves dangling event listeners (in particular `'error'`, `'end'`, `'finish'` and `'close'`) after `callback` has been
  * invoked. The reason for this is so that unexpected `'error'` events (due to
  * incorrect stream implementations) do not cause unexpected crashes.
  * If this is unwanted behavior then the returned cleanup function needs to be
  * invoked in the callback:
  *
  * ```js
  * const cleanup = finished(rs, (err) => {
  *   cleanup();
  *   // ...
  * });
  * ```
  * @since v10.0.0
  * @param stream A readable and/or writable stream.
  * @param callback A callback function that takes an optional error argument.
  * @returns A cleanup function which removes all registered listeners.
  */
inline def finished(
  stream: ReadableStream,
  options: FinishedOptions,
  callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]
): js.Function0[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("finished")(stream.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[js.Function0[Unit]]
inline def finished(stream: WritableStream, callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]): js.Function0[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("finished")(stream.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[js.Function0[Unit]]
inline def finished(
  stream: WritableStream,
  options: FinishedOptions,
  callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]
): js.Function0[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("finished")(stream.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[js.Function0[Unit]]

/**
  * Returns the default highWaterMark used by streams.
  * Defaults to `65536` (64 KiB), or `16` for `objectMode`.
  * @since v19.9.0
  */
inline def getDefaultHighWaterMark(objectMode: Boolean): Double = ^.asInstanceOf[js.Dynamic].applyDynamic("getDefaultHighWaterMark")(objectMode.asInstanceOf[js.Any]).asInstanceOf[Double]

inline def isErrored(stream: ReadableStream): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isErrored")(stream.asInstanceOf[js.Any]).asInstanceOf[Boolean]
inline def isErrored(stream: WritableStream): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isErrored")(stream.asInstanceOf[js.Any]).asInstanceOf[Boolean]
/**
  * Returns whether the stream has encountered an error.
  * @since v17.3.0, v16.14.0
  * @experimental
  */
inline def isErrored(stream: Readable): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isErrored")(stream.asInstanceOf[js.Any]).asInstanceOf[Boolean]
inline def isErrored(stream: Writable): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isErrored")(stream.asInstanceOf[js.Any]).asInstanceOf[Boolean]

inline def isReadable(stream: ReadableStream): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isReadable")(stream.asInstanceOf[js.Any]).asInstanceOf[Boolean]
/**
  * Returns whether the stream is readable.
  * @since v17.4.0, v16.14.0
  * @experimental
  */
inline def isReadable(stream: Readable): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isReadable")(stream.asInstanceOf[js.Any]).asInstanceOf[Boolean]

inline def pipeline(
  stream1: ReadableStream,
  stream2: ReadWriteStream,
  streams: (ReadWriteStream | WritableStream | (js.Function1[/* err */ ErrnoException | Null, Unit]))*
): WritableStream = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")((scala.List(stream1.asInstanceOf[js.Any], stream2.asInstanceOf[js.Any])).`++`(streams.asInstanceOf[Seq[js.Any]])*)).asInstanceOf[WritableStream]
inline def pipeline(
  stream1: ReadableStream,
  stream2: WritableStream,
  streams: (ReadWriteStream | WritableStream | (js.Function1[/* err */ ErrnoException | Null, Unit]))*
): WritableStream = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")((scala.List(stream1.asInstanceOf[js.Any], stream2.asInstanceOf[js.Any])).`++`(streams.asInstanceOf[Seq[js.Any]])*)).asInstanceOf[WritableStream]
inline def pipeline(
  streams: js.Array[ReadableStream | WritableStream | ReadWriteStream],
  callback: js.Function1[/* err */ ErrnoException | Null, Unit]
): WritableStream = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(streams.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[WritableStream]
/**
  * A module method to pipe between streams and generators forwarding errors and
  * properly cleaning up and provide a callback when the pipeline is complete.
  *
  * ```js
  * const { pipeline } = require('node:stream');
  * const fs = require('node:fs');
  * const zlib = require('node:zlib');
  *
  * // Use the pipeline API to easily pipe a series of streams
  * // together and get notified when the pipeline is fully done.
  *
  * // A pipeline to gzip a potentially huge tar file efficiently:
  *
  * pipeline(
  *   fs.createReadStream('archive.tar'),
  *   zlib.createGzip(),
  *   fs.createWriteStream('archive.tar.gz'),
  *   (err) => {
  *     if (err) {
  *       console.error('Pipeline failed.', err);
  *     } else {
  *       console.log('Pipeline succeeded.');
  *     }
  *   },
  * );
  * ```
  *
  * The `pipeline` API provides a [`promise version`](https://nodejs.org/docs/latest-v22.x/api/stream.html#streampipelinesource-transforms-destination-options).
  *
  * `stream.pipeline()` will call `stream.destroy(err)` on all streams except:
  *
  * * `Readable` streams which have emitted `'end'` or `'close'`.
  * * `Writable` streams which have emitted `'finish'` or `'close'`.
  *
  * `stream.pipeline()` leaves dangling event listeners on the streams
  * after the `callback` has been invoked. In the case of reuse of streams after
  * failure, this can cause event listener leaks and swallowed errors. If the last
  * stream is readable, dangling event listeners will be removed so that the last
  * stream can be consumed later.
  *
  * `stream.pipeline()` closes all the streams when an error is raised.
  * The `IncomingRequest` usage with `pipeline` could lead to an unexpected behavior
  * once it would destroy the socket without sending the expected response.
  * See the example below:
  *
  * ```js
  * const fs = require('node:fs');
  * const http = require('node:http');
  * const { pipeline } = require('node:stream');
  *
  * const server = http.createServer((req, res) => {
  *   const fileStream = fs.createReadStream('./fileNotExist.txt');
  *   pipeline(fileStream, res, (err) => {
  *     if (err) {
  *       console.log(err); // No such file
  *       // this message can't be sent once `pipeline` already destroyed the socket
  *       return res.end('error!!!');
  *     }
  *   });
  * });
  * ```
  * @since v10.0.0
  * @param callback Called when the pipeline is fully done.
  */
inline def pipeline[A /* <: PipelineSource[Any] */, B /* <: PipelineDestination[A, Any] */](source: A, destination: B, callback: PipelineCallback[B]): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(source.asInstanceOf[js.Any], destination.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[/* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any]
inline def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, B /* <: PipelineDestination[T1, Any] */](source: A, transform1: T1, destination: B, callback: PipelineCallback[B]): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(source.asInstanceOf[js.Any], transform1.asInstanceOf[js.Any], destination.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[/* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any]
inline def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, B /* <: PipelineDestination[T2, Any] */](source: A, transform1: T1, transform2: T2, destination: B, callback: PipelineCallback[B]): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(source.asInstanceOf[js.Any], transform1.asInstanceOf[js.Any], transform2.asInstanceOf[js.Any], destination.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[/* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any]
inline def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, B /* <: PipelineDestination[T3, Any] */](
  source: A,
  transform1: T1,
  transform2: T2,
  transform3: T3,
  destination: B,
  callback: PipelineCallback[B]
): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(source.asInstanceOf[js.Any], transform1.asInstanceOf[js.Any], transform2.asInstanceOf[js.Any], transform3.asInstanceOf[js.Any], destination.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[/* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any]
inline def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, T4 /* <: PipelineTransform[T3, Any] */, B /* <: PipelineDestination[T4, Any] */](
  source: A,
  transform1: T1,
  transform2: T2,
  transform3: T3,
  transform4: T4,
  destination: B,
  callback: PipelineCallback[B]
): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(source.asInstanceOf[js.Any], transform1.asInstanceOf[js.Any], transform2.asInstanceOf[js.Any], transform3.asInstanceOf[js.Any], transform4.asInstanceOf[js.Any], destination.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[/* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any]

/**
  * Sets the default highWaterMark used by streams.
  * @since v19.9.0
  * @param value highWaterMark value
  */
inline def setDefaultHighWaterMark(objectMode: Boolean, value: Double): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("setDefaultHighWaterMark")(objectMode.asInstanceOf[js.Any], value.asInstanceOf[js.Any])).asInstanceOf[Unit]

type ComposeFnParam = js.Function1[/* source */ Any, Unit]

/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  S extends node.stream.PipelineDestinationPromiseFunction<any, infer P> ? (err : node.node/globals.<global>.NodeJS.ErrnoException | null, value : P): void : (err : node.node/globals.<global>.NodeJS.ErrnoException | null): void
  }}}
  */
type PipelineCallback[S /* <: PipelineDestination[Any, Any] */] = js.Function1[/* err */ ErrnoException | Null, Unit]

type PipelineDestinationIterableFunction[T] = js.Function1[
/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ /* source */ Any, 
/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any]

type PipelineDestinationPromiseFunction[T, P] = js.Function1[
/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ /* source */ Any, 
js.Promise[P]]

/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  S extends node.stream.PipelineDestinationPromiseFunction<any, infer P> ? std.Promise<P> : std.Promise<void>
  }}}
  */
type PipelinePromise[S /* <: PipelineDestination[Any, Any] */] = js.Promise[Unit]

type PipelineSource[T] = js.Iterable[T] | (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ Any) | ReadableStream | PipelineSourceFunction[T]

type PipelineSourceFunction[T] = js.Function0[
js.Iterable[T] | (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ Any)]

type PipelineTransform[S /* <: PipelineTransformSource[Any] */, U] = ReadWriteStream | (js.Function1[
/* import warning: importer.ImportType#apply Failed type conversion: S extends (args : ...any): std.Iterable<infer ST> | / * import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<infer ST> * / any ? / * import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<ST> * / any : S */ /* source */ js.Any, 
/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<U> */ Any])

type PipelineTransformSource[T] = PipelineSource[T] | (PipelineTransform[Any, T])

type TransformCallback = js.Function2[/* error */ js.UndefOr[js.Error | Null], /* data */ js.UndefOr[Any], Unit]

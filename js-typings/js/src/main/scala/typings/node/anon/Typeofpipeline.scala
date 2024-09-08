package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.globalsMod.global.NodeJS.ReadWriteStream
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.streamMod.PipelineCallback
import typings.node.streamMod.PipelineDestination
import typings.node.streamMod.PipelineOptions
import typings.node.streamMod.PipelineSource
import typings.node.streamMod.PipelineTransform
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofpipeline extends StObject {
  
  def apply(
    stream1: ReadableStream,
    stream2: ReadWriteStream,
    streams: (ReadWriteStream | WritableStream | (js.Function1[/* err */ ErrnoException | Null, Unit]))*
  ): WritableStream = js.native
  def apply(
    stream1: ReadableStream,
    stream2: WritableStream,
    streams: (ReadWriteStream | WritableStream | (js.Function1[/* err */ ErrnoException | Null, Unit]))*
  ): WritableStream = js.native
  def apply(
    streams: js.Array[ReadableStream | WritableStream | ReadWriteStream],
    callback: js.Function1[/* err */ ErrnoException | Null, Unit]
  ): WritableStream = js.native
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
  def apply[A /* <: PipelineSource[Any] */, B /* <: PipelineDestination[A, Any] */](source: A, destination: B, callback: PipelineCallback[B]): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = js.native
  def apply[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, B /* <: PipelineDestination[T1, Any] */](source: A, transform1: T1, destination: B, callback: PipelineCallback[B]): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = js.native
  def apply[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, B /* <: PipelineDestination[T2, Any] */](source: A, transform1: T1, transform2: T2, destination: B, callback: PipelineCallback[B]): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = js.native
  def apply[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, B /* <: PipelineDestination[T3, Any] */](
    source: A,
    transform1: T1,
    transform2: T2,
    transform3: T3,
    destination: B,
    callback: PipelineCallback[B]
  ): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = js.native
  def apply[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, T4 /* <: PipelineTransform[T3, Any] */, B /* <: PipelineDestination[T4, Any] */](
    source: A,
    transform1: T1,
    transform2: T2,
    transform3: T3,
    transform4: T4,
    destination: B,
    callback: PipelineCallback[B]
  ): /* import warning: importer.ImportType#apply Failed type conversion: B extends node.node/globals.<global>.NodeJS.WritableStream ? B : node.node/globals.<global>.NodeJS.WritableStream */ js.Any = js.native
  
  def __promisify__(
    stream1: ReadableStream,
    stream2: ReadWriteStream,
    streams: (ReadWriteStream | WritableStream | PipelineOptions)*
  ): js.Promise[Unit] = js.native
  def __promisify__(
    stream1: ReadableStream,
    stream2: WritableStream,
    streams: (ReadWriteStream | WritableStream | PipelineOptions)*
  ): js.Promise[Unit] = js.native
  def __promisify__(streams: js.Array[ReadableStream | WritableStream | ReadWriteStream]): js.Promise[Unit] = js.native
  def __promisify__(streams: js.Array[ReadableStream | WritableStream | ReadWriteStream], options: PipelineOptions): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, B /* <: PipelineDestination[A, Any] */](source: A, destination: B): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, B /* <: PipelineDestination[A, Any] */](source: A, destination: B, options: PipelineOptions): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, B /* <: PipelineDestination[T1, Any] */](source: A, transform1: T1, destination: B): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, B /* <: PipelineDestination[T1, Any] */](source: A, transform1: T1, destination: B, options: PipelineOptions): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, B /* <: PipelineDestination[T2, Any] */](source: A, transform1: T1, transform2: T2, destination: B): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, B /* <: PipelineDestination[T2, Any] */](source: A, transform1: T1, transform2: T2, destination: B, options: PipelineOptions): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, B /* <: PipelineDestination[T3, Any] */](source: A, transform1: T1, transform2: T2, transform3: T3, destination: B): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, B /* <: PipelineDestination[T3, Any] */](
    source: A,
    transform1: T1,
    transform2: T2,
    transform3: T3,
    destination: B,
    options: PipelineOptions
  ): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, T4 /* <: PipelineTransform[T3, Any] */, B /* <: PipelineDestination[T4, Any] */](source: A, transform1: T1, transform2: T2, transform3: T3, transform4: T4, destination: B): js.Promise[Unit] = js.native
  def __promisify__[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, T4 /* <: PipelineTransform[T3, Any] */, B /* <: PipelineDestination[T4, Any] */](
    source: A,
    transform1: T1,
    transform2: T2,
    transform3: T3,
    transform4: T4,
    destination: B,
    options: PipelineOptions
  ): js.Promise[Unit] = js.native
}

package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.globalsMod.global.NodeJS.ReadWriteStream
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.streamMod.FinishedOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoffinished extends StObject {
  
  def apply(stream: ReadWriteStream, callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]): js.Function0[Unit] = js.native
  def apply(
    stream: ReadWriteStream,
    options: FinishedOptions,
    callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]
  ): js.Function0[Unit] = js.native
  def apply(stream: ReadableStream, callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]): js.Function0[Unit] = js.native
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
  def apply(
    stream: ReadableStream,
    options: FinishedOptions,
    callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]
  ): js.Function0[Unit] = js.native
  def apply(stream: WritableStream, callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]): js.Function0[Unit] = js.native
  def apply(
    stream: WritableStream,
    options: FinishedOptions,
    callback: js.Function1[/* err */ js.UndefOr[ErrnoException | Null], Unit]
  ): js.Function0[Unit] = js.native
  
  def __promisify__(stream: ReadWriteStream): js.Promise[Unit] = js.native
  def __promisify__(stream: ReadWriteStream, options: FinishedOptions): js.Promise[Unit] = js.native
  def __promisify__(stream: ReadableStream): js.Promise[Unit] = js.native
  def __promisify__(stream: ReadableStream, options: FinishedOptions): js.Promise[Unit] = js.native
  def __promisify__(stream: WritableStream): js.Promise[Unit] = js.native
  def __promisify__(stream: WritableStream, options: FinishedOptions): js.Promise[Unit] = js.native
}

package typings.node.streamMod

import typings.node.anon.DestroyOnReturn
import typings.node.anon.PickArrayOptionssignal
import typings.node.bufferMod.global.BufferEncoding
import typings.node.nodeStrings.close
import typings.node.nodeStrings.data
import typings.node.nodeStrings.end
import typings.node.nodeStrings.error
import typings.node.nodeStrings.pause
import typings.node.nodeStrings.readable
import typings.node.nodeStrings.resume
import typings.std.ReadableStream
import typings.std.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait ReadableBase
  extends StObject
     with ReadableStream[Any] {
  
  var _construct: js.UndefOr[
    js.Function1[/* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], Unit]
  ] = js.native
  
  def _destroy(error: js.Error, callback: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Unit = js.native
  def _destroy(error: Null, callback: js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit]): Unit = js.native
  
  def _read(size: Double): Unit = js.native
  
  def addListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def addListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  /**
    * Event emitter
    * The defined events on documents including:
    * 1. close
    * 2. data
    * 3. end
    * 4. error
    * 5. pause
    * 6. readable
    * 7. resume
    */
  @JSName("addListener")
  def addListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  
  /**
    * This method returns a new stream with chunks of the underlying stream paired with a counter
    * in the form `[index, chunk]`. The first index value is `0` and it increases by 1 for each chunk produced.
    * @since v17.5.0
    * @returns a stream of indexed pairs.
    */
  def asIndexedPairs(): Readable = js.native
  def asIndexedPairs(options: PickArrayOptionssignal): Readable = js.native
  
  /**
    * Is `true` after `'close'` has been emitted.
    * @since v18.0.0
    */
  val closed: Boolean = js.native
  
  /**
    * Destroy the stream. Optionally emit an `'error'` event, and emit a `'close'` event (unless `emitClose` is set to `false`). After this call, the readable
    * stream will release any internal resources and subsequent calls to `push()` will be ignored.
    *
    * Once `destroy()` has been called any further calls will be a no-op and no
    * further errors except from `_destroy()` may be emitted as `'error'`.
    *
    * Implementors should not override this method, but instead implement `readable._destroy()`.
    * @since v8.0.0
    * @param error Error which will be passed as payload in `'error'` event
    */
  def destroy(): this.type = js.native
  def destroy(error: js.Error): this.type = js.native
  
  /**
    * Is `true` after `readable.destroy()` has been called.
    * @since v8.0.0
    */
  var destroyed: Boolean = js.native
  
  /**
    * This method returns a new stream with the first *limit* chunks dropped from the start.
    * @since v17.5.0
    * @param limit the number of chunks to drop from the readable.
    * @returns a stream with *limit* chunks dropped from the start.
    */
  def drop(limit: Double): Readable = js.native
  def drop(limit: Double, options: PickArrayOptionssignal): Readable = js.native
  
  def emit(event: String, args: Any*): Boolean = js.native
  def emit(event: js.Symbol, args: Any*): Boolean = js.native
  @JSName("emit")
  def emit_close(event: close): Boolean = js.native
  @JSName("emit")
  def emit_data(event: data, chunk: Any): Boolean = js.native
  @JSName("emit")
  def emit_end(event: end): Boolean = js.native
  @JSName("emit")
  def emit_error(event: error, err: js.Error): Boolean = js.native
  @JSName("emit")
  def emit_pause(event: pause): Boolean = js.native
  @JSName("emit")
  def emit_readable(event: readable): Boolean = js.native
  @JSName("emit")
  def emit_resume(event: resume): Boolean = js.native
  
  /**
    * Returns error if the stream has been destroyed with an error.
    * @since v18.0.0
    */
  val errored: js.Error | Null = js.native
  
  /**
    * This method is similar to `Array.prototype.every` and calls *fn* on each chunk in the stream
    * to check if all awaited return values are truthy value for *fn*. Once an *fn* call on a chunk
    * `await`ed return value is falsy, the stream is destroyed and the promise is fulfilled with `false`.
    * If all of the *fn* calls on the chunks return a truthy value, the promise is fulfilled with `true`.
    * @since v17.5.0
    * @param fn a function to call on each chunk of the stream. Async or not.
    * @returns a promise evaluating to `true` if *fn* returned a truthy value for every one of the chunks.
    */
  def every(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ]
  ): js.Promise[Boolean] = js.native
  def every(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ],
    options: ArrayOptions
  ): js.Promise[Boolean] = js.native
  
  /**
    * This method allows filtering the stream. For each chunk in the stream the *fn* function will be called
    * and if it returns a truthy value, the chunk will be passed to the result stream.
    * If the *fn* function returns a promise - that promise will be `await`ed.
    * @since v17.4.0, v16.14.0
    * @param fn a function to filter chunks from the stream. Async or not.
    * @returns a stream filtered with the predicate *fn*.
    */
  def filter(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ]
  ): Readable = js.native
  def filter(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ],
    options: ArrayOptions
  ): Readable = js.native
  
  def find(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ]
  ): js.Promise[Any] = js.native
  def find(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ],
    options: ArrayOptions
  ): js.Promise[Any] = js.native
  /**
    * This method is similar to `Array.prototype.find` and calls *fn* on each chunk in the stream
    * to find a chunk with a truthy value for *fn*. Once an *fn* call's awaited return value is truthy,
    * the stream is destroyed and the promise is fulfilled with value for which *fn* returned a truthy value.
    * If all of the *fn* calls on the chunks return a falsy value, the promise is fulfilled with `undefined`.
    * @since v17.5.0
    * @param fn a function to call on each chunk of the stream. Async or not.
    * @returns a promise evaluating to the first chunk for which *fn* evaluated with a truthy value,
    * or `undefined` if no element was found.
    */
  @JSName("find")
  def find_T[T](
    fn: js.Function2[/* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], /* is T */ Boolean]
  ): js.Promise[js.UndefOr[T]] = js.native
  @JSName("find")
  def find_T[T](
    fn: js.Function2[/* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], /* is T */ Boolean],
    options: ArrayOptions
  ): js.Promise[js.UndefOr[T]] = js.native
  
  /**
    * This method returns a new stream by applying the given callback to each chunk of the stream
    * and then flattening the result.
    *
    * It is possible to return a stream or another iterable or async iterable from *fn* and the result streams
    * will be merged (flattened) into the returned stream.
    * @since v17.5.0
    * @param fn a function to map over every chunk in the stream. May be async. May be a stream or generator.
    * @returns a stream flat-mapped with the function *fn*.
    */
  def flatMap(fn: js.Function2[/* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], Any]): Readable = js.native
  def flatMap(
    fn: js.Function2[/* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], Any],
    options: ArrayOptions
  ): Readable = js.native
  
  /**
    * This method allows iterating a stream. For each chunk in the stream the *fn* function will be called.
    * If the *fn* function returns a promise - that promise will be `await`ed.
    *
    * This method is different from `for await...of` loops in that it can optionally process chunks concurrently.
    * In addition, a `forEach` iteration can only be stopped by having passed a `signal` option
    * and aborting the related AbortController while `for await...of` can be stopped with `break` or `return`.
    * In either case the stream will be destroyed.
    *
    * This method is different from listening to the `'data'` event in that it uses the `readable` event
    * in the underlying machinary and can limit the number of concurrent *fn* calls.
    * @since v17.5.0
    * @param fn a function to call on each chunk of the stream. Async or not.
    * @returns a promise for when the stream has finished.
    */
  def forEach(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Unit | js.Promise[Unit]
    ]
  ): js.Promise[Unit] = js.native
  def forEach(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Unit | js.Promise[Unit]
    ],
    options: ArrayOptions
  ): js.Promise[Unit] = js.native
  
  /**
    * The `readable.isPaused()` method returns the current operating state of the `Readable`.
    * This is used primarily by the mechanism that underlies the `readable.pipe()` method.
    * In most typical cases, there will be no reason to use this method directly.
    *
    * ```js
    * const readable = new stream.Readable();
    *
    * readable.isPaused(); // === false
    * readable.pause();
    * readable.isPaused(); // === true
    * readable.resume();
    * readable.isPaused(); // === false
    * ```
    * @since v0.11.14
    */
  def isPaused(): Boolean = js.native
  
  /**
    * The iterator created by this method gives users the option to cancel the destruction
    * of the stream if the `for await...of` loop is exited by `return`, `break`, or `throw`,
    * or if the iterator should destroy the stream if the stream emitted an error during iteration.
    * @since v16.3.0
    * @param options.destroyOnReturn When set to `false`, calling `return` on the async iterator,
    * or exiting a `for await...of` iteration using a `break`, `return`, or `throw` will not destroy the stream.
    * **Default: `true`**.
    */
  def iterator(): Any = js.native
  def iterator(options: DestroyOnReturn): Any = js.native
  
  /**
    * This method allows mapping over the stream. The *fn* function will be called for every chunk in the stream.
    * If the *fn* function returns a promise - that promise will be `await`ed before being passed to the result stream.
    * @since v17.4.0, v16.14.0
    * @param fn a function to map over every chunk in the stream. Async or not.
    * @returns a stream mapped with the function *fn*.
    */
  def map(fn: js.Function2[/* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], Any]): Readable = js.native
  def map(
    fn: js.Function2[/* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], Any],
    options: ArrayOptions
  ): Readable = js.native
  
  def on(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def on(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("on")
  def on_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("on")
  def on_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("on")
  def on_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  
  def once(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def once(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("once")
  def once_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("once")
  def once_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("once")
  def once_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  
  /**
    * The `readable.pause()` method will cause a stream in flowing mode to stop
    * emitting `'data'` events, switching out of flowing mode. Any data that
    * becomes available will remain in the internal buffer.
    *
    * ```js
    * const readable = getReadableStreamSomehow();
    * readable.on('data', (chunk) => {
    *   console.log(`Received ${chunk.length} bytes of data.`);
    *   readable.pause();
    *   console.log('There will be no additional data for 1 second.');
    *   setTimeout(() => {
    *     console.log('Now data will start flowing again.');
    *     readable.resume();
    *   }, 1000);
    * });
    * ```
    *
    * The `readable.pause()` method has no effect if there is a `'readable'` event listener.
    * @since v0.9.4
    */
  def pause(): this.type = js.native
  
  def prependListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def prependListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  
  def prependOnceListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def prependOnceListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  
  def push(chunk: Any): Boolean = js.native
  def push(chunk: Any, encoding: BufferEncoding): Boolean = js.native
  
  /**
    * The `readable.read()` method reads data out of the internal buffer and
    * returns it. If no data is available to be read, `null` is returned. By default,
    * the data is returned as a `Buffer` object unless an encoding has been
    * specified using the `readable.setEncoding()` method or the stream is operating
    * in object mode.
    *
    * The optional `size` argument specifies a specific number of bytes to read. If
    * `size` bytes are not available to be read, `null` will be returned _unless_ the
    * stream has ended, in which case all of the data remaining in the internal buffer
    * will be returned.
    *
    * If the `size` argument is not specified, all of the data contained in the
    * internal buffer will be returned.
    *
    * The `size` argument must be less than or equal to 1 GiB.
    *
    * The `readable.read()` method should only be called on `Readable` streams
    * operating in paused mode. In flowing mode, `readable.read()` is called
    * automatically until the internal buffer is fully drained.
    *
    * ```js
    * const readable = getReadableStreamSomehow();
    *
    * // 'readable' may be triggered multiple times as data is buffered in
    * readable.on('readable', () => {
    *   let chunk;
    *   console.log('Stream is readable (new data received in buffer)');
    *   // Use a loop to make sure we read all currently available data
    *   while (null !== (chunk = readable.read())) {
    *     console.log(`Read ${chunk.length} bytes of data...`);
    *   }
    * });
    *
    * // 'end' will be triggered once when there is no more data available
    * readable.on('end', () => {
    *   console.log('Reached end of stream.');
    * });
    * ```
    *
    * Each call to `readable.read()` returns a chunk of data, or `null`. The chunks
    * are not concatenated. A `while` loop is necessary to consume all data
    * currently in the buffer. When reading a large file `.read()` may return `null`,
    * having consumed all buffered content so far, but there is still more data to
    * come not yet buffered. In this case a new `'readable'` event will be emitted
    * when there is more data in the buffer. Finally the `'end'` event will be
    * emitted when there is no more data to come.
    *
    * Therefore to read a file's whole contents from a `readable`, it is necessary
    * to collect chunks across multiple `'readable'` events:
    *
    * ```js
    * const chunks = [];
    *
    * readable.on('readable', () => {
    *   let chunk;
    *   while (null !== (chunk = readable.read())) {
    *     chunks.push(chunk);
    *   }
    * });
    *
    * readable.on('end', () => {
    *   const content = chunks.join('');
    * });
    * ```
    *
    * A `Readable` stream in object mode will always return a single item from
    * a call to `readable.read(size)`, regardless of the value of the `size` argument.
    *
    * If the `readable.read()` method returns a chunk of data, a `'data'` event will
    * also be emitted.
    *
    * Calling {@link read} after the `'end'` event has
    * been emitted will return `null`. No runtime error will be raised.
    * @since v0.9.4
    * @param size Optional argument to specify how much data to read.
    */
  def read(): Any = js.native
  def read(size: Double): Any = js.native
  
  /**
    * Is `true` if it is safe to call {@link read}, which means
    * the stream has not been destroyed or emitted `'error'` or `'end'`.
    * @since v11.4.0
    */
  var readable: Boolean = js.native
  
  /**
    * Returns whether the stream was destroyed or errored before emitting `'end'`.
    * @since v16.8.0
    * @experimental
    */
  val readableAborted: Boolean = js.native
  
  /**
    * Returns whether `'data'` has been emitted.
    * @since v16.7.0, v14.18.0
    * @experimental
    */
  val readableDidRead: Boolean = js.native
  
  /**
    * Getter for the property `encoding` of a given `Readable` stream. The `encoding` property can be set using the {@link setEncoding} method.
    * @since v12.7.0
    */
  val readableEncoding: BufferEncoding | Null = js.native
  
  /**
    * Becomes `true` when [`'end'`](https://nodejs.org/docs/latest-v22.x/api/stream.html#event-end) event is emitted.
    * @since v12.9.0
    */
  val readableEnded: Boolean = js.native
  
  /**
    * This property reflects the current state of a `Readable` stream as described
    * in the [Three states](https://nodejs.org/docs/latest-v22.x/api/stream.html#three-states) section.
    * @since v9.4.0
    */
  val readableFlowing: Boolean | Null = js.native
  
  /**
    * Returns the value of `highWaterMark` passed when creating this `Readable`.
    * @since v9.3.0
    */
  val readableHighWaterMark: Double = js.native
  
  /**
    * This property contains the number of bytes (or objects) in the queue
    * ready to be read. The value provides introspection data regarding
    * the status of the `highWaterMark`.
    * @since v9.4.0
    */
  val readableLength: Double = js.native
  
  /**
    * Getter for the property `objectMode` of a given `Readable` stream.
    * @since v12.3.0
    */
  val readableObjectMode: Boolean = js.native
  
  /**
    * This method calls *fn* on each chunk of the stream in order, passing it the result from the calculation
    * on the previous element. It returns a promise for the final value of the reduction.
    *
    * If no *initial* value is supplied the first chunk of the stream is used as the initial value.
    * If the stream is empty, the promise is rejected with a `TypeError` with the `ERR_INVALID_ARGS` code property.
    *
    * The reducer function iterates the stream element-by-element which means that there is no *concurrency* parameter
    * or parallelism. To perform a reduce concurrently, you can extract the async function to `readable.map` method.
    * @since v17.5.0
    * @param fn a reducer function to call over every chunk in the stream. Async or not.
    * @param initial the initial value to use in the reduction.
    * @returns a promise for the final value of the reduction.
    */
  def reduce[T](
    fn: js.Function3[
      /* previous */ Any, 
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      T
    ]
  ): js.Promise[T] = js.native
  def reduce[T](
    fn: js.Function3[/* previous */ T, /* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], T],
    initial: T
  ): js.Promise[T] = js.native
  def reduce[T](
    fn: js.Function3[/* previous */ T, /* data */ Any, /* options */ js.UndefOr[PickArrayOptionssignal], T],
    initial: T,
    options: PickArrayOptionssignal
  ): js.Promise[T] = js.native
  def reduce[T](
    fn: js.Function3[
      /* previous */ Any, 
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      T
    ],
    initial: Unit,
    options: PickArrayOptionssignal
  ): js.Promise[T] = js.native
  
  def removeListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  def removeListener(event: js.Symbol, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_close(event: close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_data(event: data, listener: js.Function1[/* chunk */ Any, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_end(event: end, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_error(event: error, listener: js.Function1[/* err */ js.Error, Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_pause(event: pause, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_readable(event: readable, listener: js.Function0[Unit]): this.type = js.native
  @JSName("removeListener")
  def removeListener_resume(event: resume, listener: js.Function0[Unit]): this.type = js.native
  
  /**
    * The `readable.resume()` method causes an explicitly paused `Readable` stream to
    * resume emitting `'data'` events, switching the stream into flowing mode.
    *
    * The `readable.resume()` method can be used to fully consume the data from a
    * stream without actually processing any of that data:
    *
    * ```js
    * getReadableStreamSomehow()
    *   .resume()
    *   .on('end', () => {
    *     console.log('Reached the end, but did not read anything.');
    *   });
    * ```
    *
    * The `readable.resume()` method has no effect if there is a `'readable'` event listener.
    * @since v0.9.4
    */
  def resume(): this.type = js.native
  
  /**
    * The `readable.setEncoding()` method sets the character encoding for
    * data read from the `Readable` stream.
    *
    * By default, no encoding is assigned and stream data will be returned as `Buffer` objects. Setting an encoding causes the stream data
    * to be returned as strings of the specified encoding rather than as `Buffer` objects. For instance, calling `readable.setEncoding('utf8')` will cause the
    * output data to be interpreted as UTF-8 data, and passed as strings. Calling `readable.setEncoding('hex')` will cause the data to be encoded in hexadecimal
    * string format.
    *
    * The `Readable` stream will properly handle multi-byte characters delivered
    * through the stream that would otherwise become improperly decoded if simply
    * pulled from the stream as `Buffer` objects.
    *
    * ```js
    * const readable = getReadableStreamSomehow();
    * readable.setEncoding('utf8');
    * readable.on('data', (chunk) => {
    *   assert.equal(typeof chunk, 'string');
    *   console.log('Got %d characters of string data:', chunk.length);
    * });
    * ```
    * @since v0.9.4
    * @param encoding The encoding to use.
    */
  def setEncoding(encoding: BufferEncoding): this.type = js.native
  
  /**
    * This method is similar to `Array.prototype.some` and calls *fn* on each chunk in the stream
    * until the awaited return value is `true` (or any truthy value). Once an *fn* call on a chunk
    * `await`ed return value is truthy, the stream is destroyed and the promise is fulfilled with `true`.
    * If none of the *fn* calls on the chunks return a truthy value, the promise is fulfilled with `false`.
    * @since v17.5.0
    * @param fn a function to call on each chunk of the stream. Async or not.
    * @returns a promise evaluating to `true` if *fn* returned a truthy value for at least one of the chunks.
    */
  def some(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ]
  ): js.Promise[Boolean] = js.native
  def some(
    fn: js.Function2[
      /* data */ Any, 
      /* options */ js.UndefOr[PickArrayOptionssignal], 
      Boolean | js.Promise[Boolean]
    ],
    options: ArrayOptions
  ): js.Promise[Boolean] = js.native
  
  /**
    * This method returns a new stream with the first *limit* chunks.
    * @since v17.5.0
    * @param limit the number of chunks to take from the readable.
    * @returns a stream with *limit* chunks taken.
    */
  def take(limit: Double): Readable = js.native
  def take(limit: Double, options: PickArrayOptionssignal): Readable = js.native
  
  /**
    * This method allows easily obtaining the contents of a stream.
    *
    * As this method reads the entire stream into memory, it negates the benefits of streams. It's intended
    * for interoperability and convenience, not as the primary way to consume streams.
    * @since v17.5.0
    * @returns a promise containing an array with the contents of the stream.
    */
  def toArray(): js.Promise[js.Array[Any]] = js.native
  def toArray(options: PickArrayOptionssignal): js.Promise[js.Array[Any]] = js.native
  
  /**
    * The `readable.unpipe()` method detaches a `Writable` stream previously attached
    * using the {@link pipe} method.
    *
    * If the `destination` is not specified, then _all_ pipes are detached.
    *
    * If the `destination` is specified, but no pipe is set up for it, then
    * the method does nothing.
    *
    * ```js
    * const fs = require('node:fs');
    * const readable = getReadableStreamSomehow();
    * const writable = fs.createWriteStream('file.txt');
    * // All the data from readable goes into 'file.txt',
    * // but only for the first second.
    * readable.pipe(writable);
    * setTimeout(() => {
    *   console.log('Stop writing to file.txt.');
    *   readable.unpipe(writable);
    *   console.log('Manually close the file stream.');
    *   writable.end();
    * }, 1000);
    * ```
    * @since v0.9.4
    * @param destination Optional specific stream to unpipe
    */
  def unpipe(): this.type = js.native
  def unpipe(destination: WritableStream[Any]): this.type = js.native
  
  /**
    * Passing `chunk` as `null` signals the end of the stream (EOF) and behaves the
    * same as `readable.push(null)`, after which no more data can be written. The EOF
    * signal is put at the end of the buffer and any buffered data will still be
    * flushed.
    *
    * The `readable.unshift()` method pushes a chunk of data back into the internal
    * buffer. This is useful in certain situations where a stream is being consumed by
    * code that needs to "un-consume" some amount of data that it has optimistically
    * pulled out of the source, so that the data can be passed on to some other party.
    *
    * The `stream.unshift(chunk)` method cannot be called after the `'end'` event
    * has been emitted or a runtime error will be thrown.
    *
    * Developers using `stream.unshift()` often should consider switching to
    * use of a `Transform` stream instead. See the `API for stream implementers` section for more information.
    *
    * ```js
    * // Pull off a header delimited by \n\n.
    * // Use unshift() if we get too much.
    * // Call the callback with (error, header, stream).
    * const { StringDecoder } = require('node:string_decoder');
    * function parseHeader(stream, callback) {
    *   stream.on('error', callback);
    *   stream.on('readable', onReadable);
    *   const decoder = new StringDecoder('utf8');
    *   let header = '';
    *   function onReadable() {
    *     let chunk;
    *     while (null !== (chunk = stream.read())) {
    *       const str = decoder.write(chunk);
    *       if (str.includes('\n\n')) {
    *         // Found the header boundary.
    *         const split = str.split(/\n\n/);
    *         header += split.shift();
    *         const remaining = split.join('\n\n');
    *         const buf = Buffer.from(remaining, 'utf8');
    *         stream.removeListener('error', callback);
    *         // Remove the 'readable' listener before unshifting.
    *         stream.removeListener('readable', onReadable);
    *         if (buf.length)
    *           stream.unshift(buf);
    *         // Now the body of the message can be read from the stream.
    *         callback(null, header, stream);
    *         return;
    *       }
    *       // Still reading the header.
    *       header += str;
    *     }
    *   }
    * }
    * ```
    *
    * Unlike {@link push}, `stream.unshift(chunk)` will not
    * end the reading process by resetting the internal reading state of the stream.
    * This can cause unexpected results if `readable.unshift()` is called during a
    * read (i.e. from within a {@link _read} implementation on a
    * custom stream). Following the call to `readable.unshift()` with an immediate {@link push} will reset the reading state appropriately,
    * however it is best to simply avoid calling `readable.unshift()` while in the
    * process of performing a read.
    * @since v0.9.11
    * @param chunk Chunk of data to unshift onto the read queue. For streams not operating in object mode, `chunk` must
    * be a {string}, {Buffer}, {TypedArray}, {DataView} or `null`. For object mode streams, `chunk` may be any JavaScript value.
    * @param encoding Encoding of string chunks. Must be a valid `Buffer` encoding, such as `'utf8'` or `'ascii'`.
    */
  def unshift(chunk: Any): Unit = js.native
  def unshift(chunk: Any, encoding: BufferEncoding): Unit = js.native
  
  /**
    * Prior to Node.js 0.10, streams did not implement the entire `node:stream` module API as it is currently defined. (See `Compatibility` for more
    * information.)
    *
    * When using an older Node.js library that emits `'data'` events and has a {@link pause} method that is advisory only, the `readable.wrap()` method can be used to create a `Readable`
    * stream that uses
    * the old stream as its data source.
    *
    * It will rarely be necessary to use `readable.wrap()` but the method has been
    * provided as a convenience for interacting with older Node.js applications and
    * libraries.
    *
    * ```js
    * const { OldReader } = require('./old-api-module.js');
    * const { Readable } = require('node:stream');
    * const oreader = new OldReader();
    * const myReader = new Readable().wrap(oreader);
    *
    * myReader.on('readable', () => {
    *   myReader.read(); // etc.
    * });
    * ```
    * @since v0.9.4
    * @param stream An "old style" readable stream
    */
  def wrap(stream: org.scalajs.dom.ReadableStream[Any]): this.type = js.native
}

package typings.node

import typings.node.anon.Error
import typings.node.anon.ErrorUnknown
import typings.node.nodeColonasyncHooksMod.AsyncLocalStorage
import typings.std.Parameters
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object diagnosticsChannelMod {
  
  @JSImport("diagnostics_channel", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * The class `Channel` represents an individual named channel within the data
    * pipeline. It is used to track subscribers and to publish messages when there
    * are subscribers present. It exists as a separate object to avoid channel
    * lookups at publish time, enabling very fast publish speeds and allowing
    * for heavy use while incurring very minimal cost. Channels are created with {@link channel}, constructing a channel directly
    * with `new Channel(name)` is not supported.
    * @since v15.1.0, v14.17.0
    */
  @JSImport("diagnostics_channel", "Channel")
  @js.native
  open class Channel_[StoreType, ContextType] protected () extends StObject {
    /* private */ def this(name: String) = this()
    /* private */ def this(name: js.Symbol) = this()
    
    /**
      * When `channel.runStores(context, ...)` is called, the given context data
      * will be applied to any store bound to the channel. If the store has already been
      * bound the previous `transform` function will be replaced with the new one.
      * The `transform` function may be omitted to set the given context data as the
      * context directly.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      * import { AsyncLocalStorage } from 'node:async_hooks';
      *
      * const store = new AsyncLocalStorage();
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * channel.bindStore(store, (data) => {
      *   return { data };
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param store The store to which to bind the context data
      * @param transform Transform context data before setting the store context
      */
    def bindStore(store: AsyncLocalStorage[StoreType]): Unit = js.native
    def bindStore(store: AsyncLocalStorage[StoreType], transform: js.Function1[/* context */ ContextType, StoreType]): Unit = js.native
    
    /**
      * Check if there are active subscribers to this channel. This is helpful if
      * the message you want to send might be expensive to prepare.
      *
      * This API is optional but helpful when trying to publish messages from very
      * performance-sensitive code.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * if (channel.hasSubscribers) {
      *   // There are subscribers, prepare and publish message
      * }
      * ```
      * @since v15.1.0, v14.17.0
      */
    val hasSubscribers: Boolean = js.native
    
    val name: String | js.Symbol = js.native
    
    /**
      * Publish a message to any subscribers to the channel. This will trigger
      * message handlers synchronously so they will execute within the same context.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * channel.publish({
      *   some: 'message',
      * });
      * ```
      * @since v15.1.0, v14.17.0
      * @param message The message to send to the channel subscribers
      */
    def publish(message: Any): Unit = js.native
    
    /**
      * Applies the given data to any AsyncLocalStorage instances bound to the channel
      * for the duration of the given function, then publishes to the channel within
      * the scope of that data is applied to the stores.
      *
      * If a transform function was given to `channel.bindStore(store)` it will be
      * applied to transform the message data before it becomes the context value for
      * the store. The prior storage context is accessible from within the transform
      * function in cases where context linking is required.
      *
      * The context applied to the store should be accessible in any async code which
      * continues from execution which began during the given function, however
      * there are some situations in which `context loss` may occur.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      * import { AsyncLocalStorage } from 'node:async_hooks';
      *
      * const store = new AsyncLocalStorage();
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * channel.bindStore(store, (message) => {
      *   const parent = store.getStore();
      *   return new Span(message, parent);
      * });
      * channel.runStores({ some: 'message' }, () => {
      *   store.getStore(); // Span({ some: 'message' })
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param context Message to send to subscribers and bind to stores
      * @param fn Handler to run within the entered storage context
      * @param thisArg The receiver to be used for the function call.
      * @param args Optional arguments to pass to the function.
      */
    def runStores(): Unit = js.native
    
    /**
      * Register a message handler to subscribe to this channel. This message handler
      * will be run synchronously whenever a message is published to the channel. Any
      * errors thrown in the message handler will trigger an `'uncaughtException'`.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * channel.subscribe((message, name) => {
      *   // Received data
      * });
      * ```
      * @since v15.1.0, v14.17.0
      * @deprecated Since v18.7.0,v16.17.0 - Use {@link subscribe(name, onMessage)}
      * @param onMessage The handler to receive channel messages
      */
    def subscribe(onMessage: ChannelListener): Unit = js.native
    
    /**
      * Remove a message handler previously registered to this channel with `channel.bindStore(store)`.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      * import { AsyncLocalStorage } from 'node:async_hooks';
      *
      * const store = new AsyncLocalStorage();
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * channel.bindStore(store);
      * channel.unbindStore(store);
      * ```
      * @since v19.9.0
      * @experimental
      * @param store The store to unbind from the channel.
      * @return `true` if the store was found, `false` otherwise.
      */
    def unbindStore(store: Any): Unit = js.native
    
    /**
      * Remove a message handler previously registered to this channel with `channel.subscribe(onMessage)`.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channel = diagnostics_channel.channel('my-channel');
      *
      * function onMessage(message, name) {
      *   // Received data
      * }
      *
      * channel.subscribe(onMessage);
      *
      * channel.unsubscribe(onMessage);
      * ```
      * @since v15.1.0, v14.17.0
      * @deprecated Since v18.7.0,v16.17.0 - Use {@link unsubscribe(name, onMessage)}
      * @param onMessage The previous subscribed handler to remove
      * @return `true` if the handler was found, `false` otherwise.
      */
    def unsubscribe(onMessage: ChannelListener): Unit = js.native
  }
  
  /**
    * The class `TracingChannel` is a collection of `TracingChannel Channels` which
    * together express a single traceable action. It is used to formalize and
    * simplify the process of producing events for tracing application flow. {@link tracingChannel} is used to construct a `TracingChannel`. As with `Channel` it is recommended to create and reuse a
    * single `TracingChannel` at the top-level of the file rather than creating them
    * dynamically.
    * @since v19.9.0
    * @experimental
    */
  @JSImport("diagnostics_channel", "TracingChannel")
  @js.native
  open class TracingChannel_[StoreType, ContextType /* <: js.Object */] ()
    extends StObject
       with TracingChannelCollection[Any, Any] {
    
    /* CompleteClass */
    var asyncEnd: Channel_[Any, Any] = js.native
    @JSName("asyncEnd")
    var asyncEnd_TracingChannel_ : Channel_[StoreType, ContextType] = js.native
    
    /* CompleteClass */
    var asyncStart: Channel_[Any, Any] = js.native
    @JSName("asyncStart")
    var asyncStart_TracingChannel_ : Channel_[StoreType, ContextType] = js.native
    
    /* CompleteClass */
    var end: Channel_[Any, Any] = js.native
    @JSName("end")
    var end_TracingChannel_ : Channel_[StoreType, ContextType] = js.native
    
    /* CompleteClass */
    var error: Channel_[Any, Any] = js.native
    @JSName("error")
    var error_TracingChannel_ : Channel_[StoreType, ContextType] = js.native
    
    /* CompleteClass */
    var start: Channel_[Any, Any] = js.native
    @JSName("start")
    var start_TracingChannel_ : Channel_[StoreType, ContextType] = js.native
    
    /**
      * Helper to subscribe a collection of functions to the corresponding channels.
      * This is the same as calling `channel.subscribe(onMessage)` on each channel
      * individually.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channels = diagnostics_channel.tracingChannel('my-channel');
      *
      * channels.subscribe({
      *   start(message) {
      *     // Handle start message
      *   },
      *   end(message) {
      *     // Handle end message
      *   },
      *   asyncStart(message) {
      *     // Handle asyncStart message
      *   },
      *   asyncEnd(message) {
      *     // Handle asyncEnd message
      *   },
      *   error(message) {
      *     // Handle error message
      *   },
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param subscribers Set of `TracingChannel Channels` subscribers
      */
    def subscribe(subscribers: TracingChannelSubscribers[ContextType]): Unit = js.native
    
    /**
      * Trace a callback-receiving function call. This will always produce a `start event` and `end event` around the synchronous portion of the
      * function execution, and will produce a `asyncStart event` and `asyncEnd event` around the callback execution. It may also produce an `error event` if the given function throws an error or
      * the returned
      * promise rejects. This will run the given function using `channel.runStores(context, ...)` on the `start` channel which ensures all
      * events should have any bound stores set to match this trace context.
      *
      * The `position` will be -1 by default to indicate the final argument should
      * be used as the callback.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channels = diagnostics_channel.tracingChannel('my-channel');
      *
      * channels.traceCallback((arg1, callback) => {
      *   // Do something
      *   callback(null, 'result');
      * }, 1, {
      *   some: 'thing',
      * }, thisArg, arg1, callback);
      * ```
      *
      * The callback will also be run with `channel.runStores(context, ...)` which
      * enables context loss recovery in some cases.
      *
      * To ensure only correct trace graphs are formed, events will only be published if subscribers are present prior to starting the trace. Subscriptions
      * which are added after the trace begins will not receive future events from that trace, only future traces will be seen.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      * import { AsyncLocalStorage } from 'node:async_hooks';
      *
      * const channels = diagnostics_channel.tracingChannel('my-channel');
      * const myStore = new AsyncLocalStorage();
      *
      * // The start channel sets the initial store data to something
      * // and stores that store data value on the trace context object
      * channels.start.bindStore(myStore, (data) => {
      *   const span = new Span(data);
      *   data.span = span;
      *   return span;
      * });
      *
      * // Then asyncStart can restore from that data it stored previously
      * channels.asyncStart.bindStore(myStore, (data) => {
      *   return data.span;
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param fn callback using function to wrap a trace around
      * @param position Zero-indexed argument position of expected callback
      * @param context Shared object to correlate trace events through
      * @param thisArg The receiver to be used for the function call
      * @param args Optional arguments to pass to the function
      * @return The return value of the given function
      */
    def traceCallback[Fn /* <: js.ThisFunction1[/* this */ Any, /* args */ Any, Any] */](
      fn: Fn,
      position: Double,
      context: ContextType,
      thisArg: Any,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Parameters<Fn> is not an array type */ args: Parameters[Fn]
    ): Unit = js.native
    def traceCallback[Fn /* <: js.ThisFunction1[/* this */ Any, /* args */ Any, Any] */](
      fn: Fn,
      position: Double,
      context: Unit,
      thisArg: Any,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Parameters<Fn> is not an array type */ args: Parameters[Fn]
    ): Unit = js.native
    def traceCallback[Fn /* <: js.ThisFunction1[/* this */ Any, /* args */ Any, Any] */](
      fn: Fn,
      position: Unit,
      context: ContextType,
      thisArg: Any,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Parameters<Fn> is not an array type */ args: Parameters[Fn]
    ): Unit = js.native
    def traceCallback[Fn /* <: js.ThisFunction1[/* this */ Any, /* args */ Any, Any] */](
      fn: Fn,
      position: Unit,
      context: Unit,
      thisArg: Any,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Parameters<Fn> is not an array type */ args: Parameters[Fn]
    ): Unit = js.native
    
    /**
      * Trace a promise-returning function call. This will always produce a `start event` and `end event` around the synchronous portion of the
      * function execution, and will produce an `asyncStart event` and `asyncEnd event` when a promise continuation is reached. It may also
      * produce an `error event` if the given function throws an error or the
      * returned promise rejects. This will run the given function using `channel.runStores(context, ...)` on the `start` channel which ensures all
      * events should have any bound stores set to match this trace context.
      *
      * To ensure only correct trace graphs are formed, events will only be published if subscribers are present prior to starting the trace. Subscriptions
      * which are added after the trace begins will not receive future events from that trace, only future traces will be seen.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channels = diagnostics_channel.tracingChannel('my-channel');
      *
      * channels.tracePromise(async () => {
      *   // Do something
      * }, {
      *   some: 'thing',
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param fn Promise-returning function to wrap a trace around
      * @param context Shared object to correlate trace events through
      * @param thisArg The receiver to be used for the function call
      * @param args Optional arguments to pass to the function
      * @return Chained from promise returned by the given function
      */
    def tracePromise[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, js.Promise[Any]],
      context: ContextType,
      thisArg: ThisArg,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    def tracePromise[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, js.Promise[Any]],
      context: ContextType,
      thisArg: Unit,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    def tracePromise[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, js.Promise[Any]],
      context: Unit,
      thisArg: ThisArg,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    def tracePromise[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, js.Promise[Any]],
      context: Unit,
      thisArg: Unit,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    
    /**
      * Trace a synchronous function call. This will always produce a `start event` and `end event` around the execution and may produce an `error event` if the given function throws an error.
      * This will run the given function using `channel.runStores(context, ...)` on the `start` channel which ensures all
      * events should have any bound stores set to match this trace context.
      *
      * To ensure only correct trace graphs are formed, events will only be published if subscribers are present prior to starting the trace. Subscriptions
      * which are added after the trace begins will not receive future events from that trace, only future traces will be seen.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channels = diagnostics_channel.tracingChannel('my-channel');
      *
      * channels.traceSync(() => {
      *   // Do something
      * }, {
      *   some: 'thing',
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param fn Function to wrap a trace around
      * @param context Shared object to correlate events through
      * @param thisArg The receiver to be used for the function call
      * @param args Optional arguments to pass to the function
      * @return The return value of the given function
      */
    def traceSync[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, Any],
      context: ContextType,
      thisArg: ThisArg,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    def traceSync[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, Any],
      context: ContextType,
      thisArg: Unit,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    def traceSync[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, Any],
      context: Unit,
      thisArg: ThisArg,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    def traceSync[ThisArg, Args /* <: js.Array[Any] */](
      fn: js.ThisFunction1[/* this */ ThisArg, /* args */ Args, Any],
      context: Unit,
      thisArg: Unit,
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args is not an array type */ args: Args
    ): Unit = js.native
    
    /**
      * Helper to unsubscribe a collection of functions from the corresponding channels.
      * This is the same as calling `channel.unsubscribe(onMessage)` on each channel
      * individually.
      *
      * ```js
      * import diagnostics_channel from 'node:diagnostics_channel';
      *
      * const channels = diagnostics_channel.tracingChannel('my-channel');
      *
      * channels.unsubscribe({
      *   start(message) {
      *     // Handle start message
      *   },
      *   end(message) {
      *     // Handle end message
      *   },
      *   asyncStart(message) {
      *     // Handle asyncStart message
      *   },
      *   asyncEnd(message) {
      *     // Handle asyncEnd message
      *   },
      *   error(message) {
      *     // Handle error message
      *   },
      * });
      * ```
      * @since v19.9.0
      * @experimental
      * @param subscribers Set of `TracingChannel Channels` subscribers
      * @return `true` if all handlers were successfully unsubscribed, and `false` otherwise.
      */
    def unsubscribe(subscribers: TracingChannelSubscribers[ContextType]): Unit = js.native
  }
  
  /**
    * This is the primary entry-point for anyone wanting to publish to a named
    * channel. It produces a channel object which is optimized to reduce overhead at
    * publish time as much as possible.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * const channel = diagnostics_channel.channel('my-channel');
    * ```
    * @since v15.1.0, v14.17.0
    * @param name The channel name
    * @return The named channel object
    */
  inline def channel(name: String): Channel_[Any, Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("channel")(name.asInstanceOf[js.Any]).asInstanceOf[Channel_[Any, Any]]
  inline def channel(name: js.Symbol): Channel_[Any, Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("channel")(name.asInstanceOf[js.Any]).asInstanceOf[Channel_[Any, Any]]
  
  /**
    * Check if there are active subscribers to the named channel. This is helpful if
    * the message you want to send might be expensive to prepare.
    *
    * This API is optional but helpful when trying to publish messages from very
    * performance-sensitive code.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * if (diagnostics_channel.hasSubscribers('my-channel')) {
    *   // There are subscribers, prepare and publish message
    * }
    * ```
    * @since v15.1.0, v14.17.0
    * @param name The channel name
    * @return If there are active subscribers
    */
  inline def hasSubscribers(name: String): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("hasSubscribers")(name.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  inline def hasSubscribers(name: js.Symbol): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("hasSubscribers")(name.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Register a message handler to subscribe to this channel. This message handler
    * will be run synchronously whenever a message is published to the channel. Any
    * errors thrown in the message handler will trigger an `'uncaughtException'`.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * diagnostics_channel.subscribe('my-channel', (message, name) => {
    *   // Received data
    * });
    * ```
    * @since v18.7.0, v16.17.0
    * @param name The channel name
    * @param onMessage The handler to receive channel messages
    */
  inline def subscribe(name: String, onMessage: ChannelListener): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("subscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def subscribe(name: js.Symbol, onMessage: ChannelListener): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("subscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Unit]
  
  /**
    * Creates a `TracingChannel` wrapper for the given `TracingChannel Channels`. If a name is given, the corresponding tracing
    * channels will be created in the form of `tracing:${name}:${eventType}` where `eventType` corresponds to the types of `TracingChannel Channels`.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * const channelsByName = diagnostics_channel.tracingChannel('my-channel');
    *
    * // or...
    *
    * const channelsByCollection = diagnostics_channel.tracingChannel({
    *   start: diagnostics_channel.channel('tracing:my-channel:start'),
    *   end: diagnostics_channel.channel('tracing:my-channel:end'),
    *   asyncStart: diagnostics_channel.channel('tracing:my-channel:asyncStart'),
    *   asyncEnd: diagnostics_channel.channel('tracing:my-channel:asyncEnd'),
    *   error: diagnostics_channel.channel('tracing:my-channel:error'),
    * });
    * ```
    * @since v19.9.0
    * @experimental
    * @param nameOrChannels Channel name or object containing all the `TracingChannel Channels`
    * @return Collection of channels to trace with
    */
  inline def tracingChannel[StoreType, ContextType /* <: js.Object */](nameOrChannels: String): TracingChannel_[StoreType, ContextType] = ^.asInstanceOf[js.Dynamic].applyDynamic("tracingChannel")(nameOrChannels.asInstanceOf[js.Any]).asInstanceOf[TracingChannel_[StoreType, ContextType]]
  inline def tracingChannel[StoreType, ContextType /* <: js.Object */](nameOrChannels: TracingChannelCollection[StoreType, ContextType]): TracingChannel_[StoreType, ContextType] = ^.asInstanceOf[js.Dynamic].applyDynamic("tracingChannel")(nameOrChannels.asInstanceOf[js.Any]).asInstanceOf[TracingChannel_[StoreType, ContextType]]
  
  /**
    * Remove a message handler previously registered to this channel with {@link subscribe}.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * function onMessage(message, name) {
    *   // Received data
    * }
    *
    * diagnostics_channel.subscribe('my-channel', onMessage);
    *
    * diagnostics_channel.unsubscribe('my-channel', onMessage);
    * ```
    * @since v18.7.0, v16.17.0
    * @param name The channel name
    * @param onMessage The previous subscribed handler to remove
    * @return `true` if the handler was found, `false` otherwise.
    */
  inline def unsubscribe(name: String, onMessage: ChannelListener): Boolean = (^.asInstanceOf[js.Dynamic].applyDynamic("unsubscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Boolean]
  inline def unsubscribe(name: js.Symbol, onMessage: ChannelListener): Boolean = (^.asInstanceOf[js.Dynamic].applyDynamic("unsubscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Boolean]
  
  type ChannelListener = js.Function2[/* message */ Any, /* name */ String | js.Symbol, Unit]
  
  trait TracingChannelCollection[StoreType, ContextType] extends StObject {
    
    var asyncEnd: Channel_[StoreType, ContextType]
    
    var asyncStart: Channel_[StoreType, ContextType]
    
    var end: Channel_[StoreType, ContextType]
    
    var error: Channel_[StoreType, ContextType]
    
    var start: Channel_[StoreType, ContextType]
  }
  object TracingChannelCollection {
    
    inline def apply[StoreType, ContextType](
      asyncEnd: Channel_[StoreType, ContextType],
      asyncStart: Channel_[StoreType, ContextType],
      end: Channel_[StoreType, ContextType],
      error: Channel_[StoreType, ContextType],
      start: Channel_[StoreType, ContextType]
    ): TracingChannelCollection[StoreType, ContextType] = {
      val __obj = js.Dynamic.literal(asyncEnd = asyncEnd.asInstanceOf[js.Any], asyncStart = asyncStart.asInstanceOf[js.Any], end = end.asInstanceOf[js.Any], error = error.asInstanceOf[js.Any], start = start.asInstanceOf[js.Any])
      __obj.asInstanceOf[TracingChannelCollection[StoreType, ContextType]]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: TracingChannelCollection[?, ?], StoreType, ContextType] (val x: Self & (TracingChannelCollection[StoreType, ContextType])) extends AnyVal {
      
      inline def setAsyncEnd(value: Channel_[StoreType, ContextType]): Self = StObject.set(x, "asyncEnd", value.asInstanceOf[js.Any])
      
      inline def setAsyncStart(value: Channel_[StoreType, ContextType]): Self = StObject.set(x, "asyncStart", value.asInstanceOf[js.Any])
      
      inline def setEnd(value: Channel_[StoreType, ContextType]): Self = StObject.set(x, "end", value.asInstanceOf[js.Any])
      
      inline def setError(value: Channel_[StoreType, ContextType]): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
      
      inline def setStart(value: Channel_[StoreType, ContextType]): Self = StObject.set(x, "start", value.asInstanceOf[js.Any])
    }
  }
  
  trait TracingChannelSubscribers[ContextType /* <: js.Object */] extends StObject {
    
    def asyncEnd(message: ContextType & Error): Unit
    
    def asyncStart(message: ContextType & Error): Unit
    
    def end(message: ContextType & Error): Unit
    
    def error(message: ContextType & ErrorUnknown): Unit
    
    def start(message: ContextType): Unit
  }
  object TracingChannelSubscribers {
    
    inline def apply[ContextType /* <: js.Object */](
      asyncEnd: ContextType & Error => Unit,
      asyncStart: ContextType & Error => Unit,
      end: ContextType & Error => Unit,
      error: ContextType & ErrorUnknown => Unit,
      start: ContextType => Unit
    ): TracingChannelSubscribers[ContextType] = {
      val __obj = js.Dynamic.literal(asyncEnd = js.Any.fromFunction1(asyncEnd), asyncStart = js.Any.fromFunction1(asyncStart), end = js.Any.fromFunction1(end), error = js.Any.fromFunction1(error), start = js.Any.fromFunction1(start))
      __obj.asInstanceOf[TracingChannelSubscribers[ContextType]]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: TracingChannelSubscribers[?], ContextType /* <: js.Object */] (val x: Self & TracingChannelSubscribers[ContextType]) extends AnyVal {
      
      inline def setAsyncEnd(value: ContextType & Error => Unit): Self = StObject.set(x, "asyncEnd", js.Any.fromFunction1(value))
      
      inline def setAsyncStart(value: ContextType & Error => Unit): Self = StObject.set(x, "asyncStart", js.Any.fromFunction1(value))
      
      inline def setEnd(value: ContextType & Error => Unit): Self = StObject.set(x, "end", js.Any.fromFunction1(value))
      
      inline def setError(value: ContextType & ErrorUnknown => Unit): Self = StObject.set(x, "error", js.Any.fromFunction1(value))
      
      inline def setStart(value: ContextType => Unit): Self = StObject.set(x, "start", js.Any.fromFunction1(value))
    }
  }
}

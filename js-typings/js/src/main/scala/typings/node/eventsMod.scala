package typings.node

import org.scalajs.dom.Event
import org.scalajs.dom.EventTarget
import typings.node.asyncHooksMod.AsyncResourceOptions
import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.Disposable
import typings.node.nodeColonasyncHooksMod.AsyncResource
import typings.std.Record
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object eventsMod {
  
  /**
    * The `EventEmitter` class is defined and exposed by the `node:events` module:
    *
    * ```js
    * import { EventEmitter } from 'node:events';
    * ```
    *
    * All `EventEmitter`s emit the event `'newListener'` when new listeners are
    * added and `'removeListener'` when existing listeners are removed.
    *
    * It supports the following option:
    * @since v0.1.26
    */
  @JSImport("events", JSImport.Namespace)
  @js.native
  open class ^[T /* <: EventMap[T] */] () extends StObject {
    def this(options: EventEmitterOptions) = this()
  }
  @JSImport("events", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * The `EventEmitter` class is defined and exposed by the `node:events` module:
    *
    * ```js
    * import { EventEmitter } from 'node:events';
    * ```
    *
    * All `EventEmitter`s emit the event `'newListener'` when new listeners are
    * added and `'removeListener'` when existing listeners are removed.
    *
    * It supports the following option:
    * @since v0.1.26
    */
  @JSImport("events", "EventEmitter")
  @js.native
  open class EventEmitter[T /* <: EventMap[T] */] ()
    extends StObject
       with typings.node.eventsMod.global.NodeJS.EventEmitter[T] {
    def this(options: EventEmitterOptions) = this()
  }
  object EventEmitter {
    
    @JSImport("events", "EventEmitter")
    @js.native
    val ^ : js.Any = js.native
    
    @JSImport("events", "EventEmitter.EventEmitterAsyncResource")
    @js.native
    /**
      * @param options Only optional in child class.
      */
    open class EventEmitterAsyncResource () extends StObject {
      def this(options: EventEmitterAsyncResourceOptions) = this()
    }
    
    /**
      * Listens once to the `abort` event on the provided `signal`.
      *
      * Listening to the `abort` event on abort signals is unsafe and may
      * lead to resource leaks since another third party with the signal can
      * call `e.stopImmediatePropagation()`. Unfortunately Node.js cannot change
      * this since it would violate the web standard. Additionally, the original
      * API makes it easy to forget to remove listeners.
      *
      * This API allows safely using `AbortSignal`s in Node.js APIs by solving these
      * two issues by listening to the event such that `stopImmediatePropagation` does
      * not prevent the listener from running.
      *
      * Returns a disposable so that it may be unsubscribed from more easily.
      *
      * ```js
      * import { addAbortListener } from 'node:events';
      *
      * function example(signal) {
      *   let disposable;
      *   try {
      *     signal.addEventListener('abort', (e) => e.stopImmediatePropagation());
      *     disposable = addAbortListener(signal, (e) => {
      *       // Do something when signal is aborted.
      *     });
      *   } finally {
      *     disposable?.[Symbol.dispose]();
      *   }
      * }
      * ```
      * @since v20.5.0
      * @experimental
      * @return Disposable that removes the `abort` listener.
      */
    /* static member */
    inline def addAbortListener(signal: AbortSignal, resource: js.Function1[/* event */ Event, Unit]): Disposable = (^.asInstanceOf[js.Dynamic].applyDynamic("addAbortListener")(signal.asInstanceOf[js.Any], resource.asInstanceOf[js.Any])).asInstanceOf[Disposable]
    
    /**
      * Value: `Symbol.for('nodejs.rejection')`
      *
      * See how to write a custom `rejection handler`.
      * @since v13.4.0, v12.16.0
      */
    /* static member */
    @JSImport("events", "EventEmitter.captureRejectionSymbol")
    @js.native
    val captureRejectionSymbol: js.Symbol = js.native
    
    /**
      * Value: [boolean](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Data_structures#Boolean_type)
      *
      * Change the default `captureRejections` option on all new `EventEmitter` objects.
      * @since v13.4.0, v12.16.0
      */
    /* static member */
    @JSImport("events", "EventEmitter.captureRejections")
    @js.native
    def captureRejections: Boolean = js.native
    inline def captureRejections_=(x: Boolean): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("captureRejections")(x.asInstanceOf[js.Any])
    
    /**
      * By default, a maximum of `10` listeners can be registered for any single
      * event. This limit can be changed for individual `EventEmitter` instances
      * using the `emitter.setMaxListeners(n)` method. To change the default
      * for _all_`EventEmitter` instances, the `events.defaultMaxListeners` property
      * can be used. If this value is not a positive number, a `RangeError` is thrown.
      *
      * Take caution when setting the `events.defaultMaxListeners` because the
      * change affects _all_ `EventEmitter` instances, including those created before
      * the change is made. However, calling `emitter.setMaxListeners(n)` still has
      * precedence over `events.defaultMaxListeners`.
      *
      * This is not a hard limit. The `EventEmitter` instance will allow
      * more listeners to be added but will output a trace warning to stderr indicating
      * that a "possible EventEmitter memory leak" has been detected. For any single
      * `EventEmitter`, the `emitter.getMaxListeners()` and `emitter.setMaxListeners()` methods can be used to
      * temporarily avoid this warning:
      *
      * ```js
      * import { EventEmitter } from 'node:events';
      * const emitter = new EventEmitter();
      * emitter.setMaxListeners(emitter.getMaxListeners() + 1);
      * emitter.once('event', () => {
      *   // do stuff
      *   emitter.setMaxListeners(Math.max(emitter.getMaxListeners() - 1, 0));
      * });
      * ```
      *
      * The `--trace-warnings` command-line flag can be used to display the
      * stack trace for such warnings.
      *
      * The emitted warning can be inspected with `process.on('warning')` and will
      * have the additional `emitter`, `type`, and `count` properties, referring to
      * the event emitter instance, the event's name and the number of attached
      * listeners, respectively.
      * Its `name` property is set to `'MaxListenersExceededWarning'`.
      * @since v0.11.2
      */
    /* static member */
    @JSImport("events", "EventEmitter.defaultMaxListeners")
    @js.native
    def defaultMaxListeners: Double = js.native
    inline def defaultMaxListeners_=(x: Double): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("defaultMaxListeners")(x.asInstanceOf[js.Any])
    
    /**
      * This symbol shall be used to install a listener for only monitoring `'error'` events. Listeners installed using this symbol are called before the regular `'error'` listeners are called.
      *
      * Installing a listener using this symbol does not change the behavior once an `'error'` event is emitted. Therefore, the process will still crash if no
      * regular `'error'` listener is installed.
      * @since v13.6.0, v12.17.0
      */
    /* static member */
    @JSImport("events", "EventEmitter.errorMonitor")
    @js.native
    val errorMonitor: js.Symbol = js.native
    
    /**
      * Returns a copy of the array of listeners for the event named `eventName`.
      *
      * For `EventEmitter`s this behaves exactly the same as calling `.listeners` on
      * the emitter.
      *
      * For `EventTarget`s this is the only way to get the event listeners for the
      * event target. This is useful for debugging and diagnostic purposes.
      *
      * ```js
      * import { getEventListeners, EventEmitter } from 'node:events';
      *
      * {
      *   const ee = new EventEmitter();
      *   const listener = () => console.log('Events are fun');
      *   ee.on('foo', listener);
      *   console.log(getEventListeners(ee, 'foo')); // [ [Function: listener] ]
      * }
      * {
      *   const et = new EventTarget();
      *   const listener = () => console.log('Events are fun');
      *   et.addEventListener('foo', listener);
      *   console.log(getEventListeners(et, 'foo')); // [ [Function: listener] ]
      * }
      * ```
      * @since v15.2.0, v14.17.0
      */
    /* static member */
    inline def getEventListeners(emitter: EventTarget, name: String): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
    inline def getEventListeners(emitter: EventTarget, name: js.Symbol): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
    inline def getEventListeners(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], name: String): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
    inline def getEventListeners(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], name: js.Symbol): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
    
    /**
      * Returns the currently set max amount of listeners.
      *
      * For `EventEmitter`s this behaves exactly the same as calling `.getMaxListeners` on
      * the emitter.
      *
      * For `EventTarget`s this is the only way to get the max event listeners for the
      * event target. If the number of event handlers on a single EventTarget exceeds
      * the max set, the EventTarget will print a warning.
      *
      * ```js
      * import { getMaxListeners, setMaxListeners, EventEmitter } from 'node:events';
      *
      * {
      *   const ee = new EventEmitter();
      *   console.log(getMaxListeners(ee)); // 10
      *   setMaxListeners(11, ee);
      *   console.log(getMaxListeners(ee)); // 11
      * }
      * {
      *   const et = new EventTarget();
      *   console.log(getMaxListeners(et)); // 10
      *   setMaxListeners(11, et);
      *   console.log(getMaxListeners(et)); // 11
      * }
      * ```
      * @since v19.9.0
      */
    /* static member */
    inline def getMaxListeners(emitter: EventTarget): Double = ^.asInstanceOf[js.Dynamic].applyDynamic("getMaxListeners")(emitter.asInstanceOf[js.Any]).asInstanceOf[Double]
    inline def getMaxListeners(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap]): Double = ^.asInstanceOf[js.Dynamic].applyDynamic("getMaxListeners")(emitter.asInstanceOf[js.Any]).asInstanceOf[Double]
    
    /**
      * A class method that returns the number of listeners for the given `eventName` registered on the given `emitter`.
      *
      * ```js
      * import { EventEmitter, listenerCount } from 'node:events';
      *
      * const myEmitter = new EventEmitter();
      * myEmitter.on('event', () => {});
      * myEmitter.on('event', () => {});
      * console.log(listenerCount(myEmitter, 'event'));
      * // Prints: 2
      * ```
      * @since v0.9.12
      * @deprecated Since v3.2.0 - Use `listenerCount` instead.
      * @param emitter The emitter to query
      * @param eventName The event name
      */
    /* static member */
    inline def listenerCount(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: String): Double = (^.asInstanceOf[js.Dynamic].applyDynamic("listenerCount")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Double]
    inline def listenerCount(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: js.Symbol): Double = (^.asInstanceOf[js.Dynamic].applyDynamic("listenerCount")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Double]
    
    /* static member */
    inline def on(emitter: EventTarget, eventName: String): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
    inline def on(emitter: EventTarget, eventName: String, options: StaticEventEmitterIteratorOptions): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
    /**
      * ```js
      * import { on, EventEmitter } from 'node:events';
      * import process from 'node:process';
      *
      * const ee = new EventEmitter();
      *
      * // Emit later on
      * process.nextTick(() => {
      *   ee.emit('foo', 'bar');
      *   ee.emit('foo', 42);
      * });
      *
      * for await (const event of on(ee, 'foo')) {
      *   // The execution of this inner block is synchronous and it
      *   // processes one event at a time (even with await). Do not use
      *   // if concurrent execution is required.
      *   console.log(event); // prints ['bar'] [42]
      * }
      * // Unreachable here
      * ```
      *
      * Returns an `AsyncIterator` that iterates `eventName` events. It will throw
      * if the `EventEmitter` emits `'error'`. It removes all listeners when
      * exiting the loop. The `value` returned by each iteration is an array
      * composed of the emitted event arguments.
      *
      * An `AbortSignal` can be used to cancel waiting on events:
      *
      * ```js
      * import { on, EventEmitter } from 'node:events';
      * import process from 'node:process';
      *
      * const ac = new AbortController();
      *
      * (async () => {
      *   const ee = new EventEmitter();
      *
      *   // Emit later on
      *   process.nextTick(() => {
      *     ee.emit('foo', 'bar');
      *     ee.emit('foo', 42);
      *   });
      *
      *   for await (const event of on(ee, 'foo', { signal: ac.signal })) {
      *     // The execution of this inner block is synchronous and it
      *     // processes one event at a time (even with await). Do not use
      *     // if concurrent execution is required.
      *     console.log(event); // prints ['bar'] [42]
      *   }
      *   // Unreachable here
      * })();
      *
      * process.nextTick(() => ac.abort());
      * ```
      *
      * Use the `close` option to specify an array of event names that will end the iteration:
      *
      * ```js
      * import { on, EventEmitter } from 'node:events';
      * import process from 'node:process';
      *
      * const ee = new EventEmitter();
      *
      * // Emit later on
      * process.nextTick(() => {
      *   ee.emit('foo', 'bar');
      *   ee.emit('foo', 42);
      *   ee.emit('close');
      * });
      *
      * for await (const event of on(ee, 'foo', { close: ['close'] })) {
      *   console.log(event); // prints ['bar'] [42]
      * }
      * // the loop will exit after 'close' is emitted
      * console.log('done'); // prints 'done'
      * ```
      * @since v13.6.0, v12.16.0
      * @return An `AsyncIterator` that iterates `eventName` events emitted by the `emitter`
      */
    /* static member */
    inline def on(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: String): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
    inline def on(
      emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
      eventName: String,
      options: StaticEventEmitterIteratorOptions
    ): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
    inline def on(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: js.Symbol): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
    inline def on(
      emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
      eventName: js.Symbol,
      options: StaticEventEmitterIteratorOptions
    ): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
    
    /* static member */
    inline def once(emitter: EventTarget, eventName: String): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
    inline def once(emitter: EventTarget, eventName: String, options: StaticEventEmitterOptions): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
    /**
      * Creates a `Promise` that is fulfilled when the `EventEmitter` emits the given
      * event or that is rejected if the `EventEmitter` emits `'error'` while waiting.
      * The `Promise` will resolve with an array of all the arguments emitted to the
      * given event.
      *
      * This method is intentionally generic and works with the web platform [EventTarget](https://dom.spec.whatwg.org/#interface-eventtarget) interface, which has no special`'error'` event
      * semantics and does not listen to the `'error'` event.
      *
      * ```js
      * import { once, EventEmitter } from 'node:events';
      * import process from 'node:process';
      *
      * const ee = new EventEmitter();
      *
      * process.nextTick(() => {
      *   ee.emit('myevent', 42);
      * });
      *
      * const [value] = await once(ee, 'myevent');
      * console.log(value);
      *
      * const err = new Error('kaboom');
      * process.nextTick(() => {
      *   ee.emit('error', err);
      * });
      *
      * try {
      *   await once(ee, 'myevent');
      * } catch (err) {
      *   console.error('error happened', err);
      * }
      * ```
      *
      * The special handling of the `'error'` event is only used when `events.once()` is used to wait for another event. If `events.once()` is used to wait for the
      * '`error'` event itself, then it is treated as any other kind of event without
      * special handling:
      *
      * ```js
      * import { EventEmitter, once } from 'node:events';
      *
      * const ee = new EventEmitter();
      *
      * once(ee, 'error')
      *   .then(([err]) => console.log('ok', err.message))
      *   .catch((err) => console.error('error', err.message));
      *
      * ee.emit('error', new Error('boom'));
      *
      * // Prints: ok boom
      * ```
      *
      * An `AbortSignal` can be used to cancel waiting for the event:
      *
      * ```js
      * import { EventEmitter, once } from 'node:events';
      *
      * const ee = new EventEmitter();
      * const ac = new AbortController();
      *
      * async function foo(emitter, event, signal) {
      *   try {
      *     await once(emitter, event, { signal });
      *     console.log('event emitted!');
      *   } catch (error) {
      *     if (error.name === 'AbortError') {
      *       console.error('Waiting for the event was canceled!');
      *     } else {
      *       console.error('There was an error', error.message);
      *     }
      *   }
      * }
      *
      * foo(ee, 'foo', ac.signal);
      * ac.abort(); // Abort waiting for the event
      * ee.emit('foo'); // Prints: Waiting for the event was canceled!
      * ```
      * @since v11.13.0, v10.16.0
      */
    /* static member */
    inline def once(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: String): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
    inline def once(
      emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
      eventName: String,
      options: StaticEventEmitterOptions
    ): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
    inline def once(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: js.Symbol): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
    inline def once(
      emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
      eventName: js.Symbol,
      options: StaticEventEmitterOptions
    ): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
    
    /**
      * ```js
      * import { setMaxListeners, EventEmitter } from 'node:events';
      *
      * const target = new EventTarget();
      * const emitter = new EventEmitter();
      *
      * setMaxListeners(5, target, emitter);
      * ```
      * @since v15.4.0
      * @param n A non-negative number. The maximum number of listeners per `EventTarget` event.
      * @param eventsTargets Zero or more {EventTarget} or {EventEmitter} instances. If none are specified, `n` is set as the default max for all newly created {EventTarget} and {EventEmitter}
      * objects.
      */
    /* static member */
    inline def setMaxListeners(
      n: Double,
      eventTargets: (EventTarget | typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap])*
    ): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setMaxListeners")(scala.List(n.asInstanceOf[js.Any]).`++`(eventTargets.asInstanceOf[Seq[js.Any]])*).asInstanceOf[Unit]
    inline def setMaxListeners(
      n: Unit,
      eventTargets: (EventTarget | typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap])*
    ): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setMaxListeners")(scala.List(n.asInstanceOf[js.Any]).`++`(eventTargets.asInstanceOf[Seq[js.Any]])*).asInstanceOf[Unit]
  }
  
  @JSImport("events", "EventEmitterAsyncResource")
  @js.native
  /**
    * @param options Only optional in child class.
    */
  open class EventEmitterAsyncResource () extends StObject {
    def this(options: EventEmitterAsyncResourceOptions) = this()
    
    /**
      * The unique `asyncId` assigned to the resource.
      */
    val asyncId: Double = js.native
    
    /**
      * The returned `AsyncResource` object has an additional `eventEmitter` property
      * that provides a reference to this `EventEmitterAsyncResource`.
      */
    val asyncResource: EventEmitterReferencingAsyncResource = js.native
    
    /**
      * Call all `destroy` hooks. This should only ever be called once. An error will
      * be thrown if it is called more than once. This **must** be manually called. If
      * the resource is left to be collected by the GC then the `destroy` hooks will
      * never be called.
      */
    def emitDestroy(): Unit = js.native
    
    /**
      * The same triggerAsyncId that is passed to the AsyncResource constructor.
      */
    val triggerAsyncId: Double = js.native
  }
  
  /**
    * Listens once to the `abort` event on the provided `signal`.
    *
    * Listening to the `abort` event on abort signals is unsafe and may
    * lead to resource leaks since another third party with the signal can
    * call `e.stopImmediatePropagation()`. Unfortunately Node.js cannot change
    * this since it would violate the web standard. Additionally, the original
    * API makes it easy to forget to remove listeners.
    *
    * This API allows safely using `AbortSignal`s in Node.js APIs by solving these
    * two issues by listening to the event such that `stopImmediatePropagation` does
    * not prevent the listener from running.
    *
    * Returns a disposable so that it may be unsubscribed from more easily.
    *
    * ```js
    * import { addAbortListener } from 'node:events';
    *
    * function example(signal) {
    *   let disposable;
    *   try {
    *     signal.addEventListener('abort', (e) => e.stopImmediatePropagation());
    *     disposable = addAbortListener(signal, (e) => {
    *       // Do something when signal is aborted.
    *     });
    *   } finally {
    *     disposable?.[Symbol.dispose]();
    *   }
    * }
    * ```
    * @since v20.5.0
    * @experimental
    * @return Disposable that removes the `abort` listener.
    */
  /* static member */
  inline def addAbortListener(signal: AbortSignal, resource: js.Function1[/* event */ Event, Unit]): Disposable = (^.asInstanceOf[js.Dynamic].applyDynamic("addAbortListener")(signal.asInstanceOf[js.Any], resource.asInstanceOf[js.Any])).asInstanceOf[Disposable]
  
  /**
    * Value: `Symbol.for('nodejs.rejection')`
    *
    * See how to write a custom `rejection handler`.
    * @since v13.4.0, v12.16.0
    */
  /* static member */
  @JSImport("events", "captureRejectionSymbol")
  @js.native
  val captureRejectionSymbol: js.Symbol = js.native
  
  /**
    * Value: [boolean](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Data_structures#Boolean_type)
    *
    * Change the default `captureRejections` option on all new `EventEmitter` objects.
    * @since v13.4.0, v12.16.0
    */
  /* static member */
  @JSImport("events", "captureRejections")
  @js.native
  def captureRejections: Boolean = js.native
  inline def captureRejections_=(x: Boolean): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("captureRejections")(x.asInstanceOf[js.Any])
  
  /**
    * By default, a maximum of `10` listeners can be registered for any single
    * event. This limit can be changed for individual `EventEmitter` instances
    * using the `emitter.setMaxListeners(n)` method. To change the default
    * for _all_`EventEmitter` instances, the `events.defaultMaxListeners` property
    * can be used. If this value is not a positive number, a `RangeError` is thrown.
    *
    * Take caution when setting the `events.defaultMaxListeners` because the
    * change affects _all_ `EventEmitter` instances, including those created before
    * the change is made. However, calling `emitter.setMaxListeners(n)` still has
    * precedence over `events.defaultMaxListeners`.
    *
    * This is not a hard limit. The `EventEmitter` instance will allow
    * more listeners to be added but will output a trace warning to stderr indicating
    * that a "possible EventEmitter memory leak" has been detected. For any single
    * `EventEmitter`, the `emitter.getMaxListeners()` and `emitter.setMaxListeners()` methods can be used to
    * temporarily avoid this warning:
    *
    * ```js
    * import { EventEmitter } from 'node:events';
    * const emitter = new EventEmitter();
    * emitter.setMaxListeners(emitter.getMaxListeners() + 1);
    * emitter.once('event', () => {
    *   // do stuff
    *   emitter.setMaxListeners(Math.max(emitter.getMaxListeners() - 1, 0));
    * });
    * ```
    *
    * The `--trace-warnings` command-line flag can be used to display the
    * stack trace for such warnings.
    *
    * The emitted warning can be inspected with `process.on('warning')` and will
    * have the additional `emitter`, `type`, and `count` properties, referring to
    * the event emitter instance, the event's name and the number of attached
    * listeners, respectively.
    * Its `name` property is set to `'MaxListenersExceededWarning'`.
    * @since v0.11.2
    */
  /* static member */
  @JSImport("events", "defaultMaxListeners")
  @js.native
  def defaultMaxListeners: Double = js.native
  inline def defaultMaxListeners_=(x: Double): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("defaultMaxListeners")(x.asInstanceOf[js.Any])
  
  /**
    * This symbol shall be used to install a listener for only monitoring `'error'` events. Listeners installed using this symbol are called before the regular `'error'` listeners are called.
    *
    * Installing a listener using this symbol does not change the behavior once an `'error'` event is emitted. Therefore, the process will still crash if no
    * regular `'error'` listener is installed.
    * @since v13.6.0, v12.17.0
    */
  /* static member */
  @JSImport("events", "errorMonitor")
  @js.native
  val errorMonitor: js.Symbol = js.native
  
  /**
    * Returns a copy of the array of listeners for the event named `eventName`.
    *
    * For `EventEmitter`s this behaves exactly the same as calling `.listeners` on
    * the emitter.
    *
    * For `EventTarget`s this is the only way to get the event listeners for the
    * event target. This is useful for debugging and diagnostic purposes.
    *
    * ```js
    * import { getEventListeners, EventEmitter } from 'node:events';
    *
    * {
    *   const ee = new EventEmitter();
    *   const listener = () => console.log('Events are fun');
    *   ee.on('foo', listener);
    *   console.log(getEventListeners(ee, 'foo')); // [ [Function: listener] ]
    * }
    * {
    *   const et = new EventTarget();
    *   const listener = () => console.log('Events are fun');
    *   et.addEventListener('foo', listener);
    *   console.log(getEventListeners(et, 'foo')); // [ [Function: listener] ]
    * }
    * ```
    * @since v15.2.0, v14.17.0
    */
  /* static member */
  inline def getEventListeners(emitter: EventTarget, name: String): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
  inline def getEventListeners(emitter: EventTarget, name: js.Symbol): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
  inline def getEventListeners(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], name: String): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
  inline def getEventListeners(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], name: js.Symbol): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
  
  /**
    * Returns the currently set max amount of listeners.
    *
    * For `EventEmitter`s this behaves exactly the same as calling `.getMaxListeners` on
    * the emitter.
    *
    * For `EventTarget`s this is the only way to get the max event listeners for the
    * event target. If the number of event handlers on a single EventTarget exceeds
    * the max set, the EventTarget will print a warning.
    *
    * ```js
    * import { getMaxListeners, setMaxListeners, EventEmitter } from 'node:events';
    *
    * {
    *   const ee = new EventEmitter();
    *   console.log(getMaxListeners(ee)); // 10
    *   setMaxListeners(11, ee);
    *   console.log(getMaxListeners(ee)); // 11
    * }
    * {
    *   const et = new EventTarget();
    *   console.log(getMaxListeners(et)); // 10
    *   setMaxListeners(11, et);
    *   console.log(getMaxListeners(et)); // 11
    * }
    * ```
    * @since v19.9.0
    */
  /* static member */
  inline def getMaxListeners(emitter: EventTarget): Double = ^.asInstanceOf[js.Dynamic].applyDynamic("getMaxListeners")(emitter.asInstanceOf[js.Any]).asInstanceOf[Double]
  inline def getMaxListeners(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap]): Double = ^.asInstanceOf[js.Dynamic].applyDynamic("getMaxListeners")(emitter.asInstanceOf[js.Any]).asInstanceOf[Double]
  
  /**
    * A class method that returns the number of listeners for the given `eventName` registered on the given `emitter`.
    *
    * ```js
    * import { EventEmitter, listenerCount } from 'node:events';
    *
    * const myEmitter = new EventEmitter();
    * myEmitter.on('event', () => {});
    * myEmitter.on('event', () => {});
    * console.log(listenerCount(myEmitter, 'event'));
    * // Prints: 2
    * ```
    * @since v0.9.12
    * @deprecated Since v3.2.0 - Use `listenerCount` instead.
    * @param emitter The emitter to query
    * @param eventName The event name
    */
  /* static member */
  inline def listenerCount(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: String): Double = (^.asInstanceOf[js.Dynamic].applyDynamic("listenerCount")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Double]
  inline def listenerCount(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: js.Symbol): Double = (^.asInstanceOf[js.Dynamic].applyDynamic("listenerCount")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Double]
  
  /* static member */
  inline def on(emitter: EventTarget, eventName: String): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(emitter: EventTarget, eventName: String, options: StaticEventEmitterIteratorOptions): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
  /**
    * ```js
    * import { on, EventEmitter } from 'node:events';
    * import process from 'node:process';
    *
    * const ee = new EventEmitter();
    *
    * // Emit later on
    * process.nextTick(() => {
    *   ee.emit('foo', 'bar');
    *   ee.emit('foo', 42);
    * });
    *
    * for await (const event of on(ee, 'foo')) {
    *   // The execution of this inner block is synchronous and it
    *   // processes one event at a time (even with await). Do not use
    *   // if concurrent execution is required.
    *   console.log(event); // prints ['bar'] [42]
    * }
    * // Unreachable here
    * ```
    *
    * Returns an `AsyncIterator` that iterates `eventName` events. It will throw
    * if the `EventEmitter` emits `'error'`. It removes all listeners when
    * exiting the loop. The `value` returned by each iteration is an array
    * composed of the emitted event arguments.
    *
    * An `AbortSignal` can be used to cancel waiting on events:
    *
    * ```js
    * import { on, EventEmitter } from 'node:events';
    * import process from 'node:process';
    *
    * const ac = new AbortController();
    *
    * (async () => {
    *   const ee = new EventEmitter();
    *
    *   // Emit later on
    *   process.nextTick(() => {
    *     ee.emit('foo', 'bar');
    *     ee.emit('foo', 42);
    *   });
    *
    *   for await (const event of on(ee, 'foo', { signal: ac.signal })) {
    *     // The execution of this inner block is synchronous and it
    *     // processes one event at a time (even with await). Do not use
    *     // if concurrent execution is required.
    *     console.log(event); // prints ['bar'] [42]
    *   }
    *   // Unreachable here
    * })();
    *
    * process.nextTick(() => ac.abort());
    * ```
    *
    * Use the `close` option to specify an array of event names that will end the iteration:
    *
    * ```js
    * import { on, EventEmitter } from 'node:events';
    * import process from 'node:process';
    *
    * const ee = new EventEmitter();
    *
    * // Emit later on
    * process.nextTick(() => {
    *   ee.emit('foo', 'bar');
    *   ee.emit('foo', 42);
    *   ee.emit('close');
    * });
    *
    * for await (const event of on(ee, 'foo', { close: ['close'] })) {
    *   console.log(event); // prints ['bar'] [42]
    * }
    * // the loop will exit after 'close' is emitted
    * console.log('done'); // prints 'done'
    * ```
    * @since v13.6.0, v12.16.0
    * @return An `AsyncIterator` that iterates `eventName` events emitted by the `emitter`
    */
  /* static member */
  inline def on(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: String): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(
    emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
    eventName: String,
    options: StaticEventEmitterIteratorOptions
  ): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: js.Symbol): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(
    emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
    eventName: js.Symbol,
    options: StaticEventEmitterIteratorOptions
  ): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
  
  /* static member */
  inline def once(emitter: EventTarget, eventName: String): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(emitter: EventTarget, eventName: String, options: StaticEventEmitterOptions): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  /**
    * Creates a `Promise` that is fulfilled when the `EventEmitter` emits the given
    * event or that is rejected if the `EventEmitter` emits `'error'` while waiting.
    * The `Promise` will resolve with an array of all the arguments emitted to the
    * given event.
    *
    * This method is intentionally generic and works with the web platform [EventTarget](https://dom.spec.whatwg.org/#interface-eventtarget) interface, which has no special`'error'` event
    * semantics and does not listen to the `'error'` event.
    *
    * ```js
    * import { once, EventEmitter } from 'node:events';
    * import process from 'node:process';
    *
    * const ee = new EventEmitter();
    *
    * process.nextTick(() => {
    *   ee.emit('myevent', 42);
    * });
    *
    * const [value] = await once(ee, 'myevent');
    * console.log(value);
    *
    * const err = new Error('kaboom');
    * process.nextTick(() => {
    *   ee.emit('error', err);
    * });
    *
    * try {
    *   await once(ee, 'myevent');
    * } catch (err) {
    *   console.error('error happened', err);
    * }
    * ```
    *
    * The special handling of the `'error'` event is only used when `events.once()` is used to wait for another event. If `events.once()` is used to wait for the
    * '`error'` event itself, then it is treated as any other kind of event without
    * special handling:
    *
    * ```js
    * import { EventEmitter, once } from 'node:events';
    *
    * const ee = new EventEmitter();
    *
    * once(ee, 'error')
    *   .then(([err]) => console.log('ok', err.message))
    *   .catch((err) => console.error('error', err.message));
    *
    * ee.emit('error', new Error('boom'));
    *
    * // Prints: ok boom
    * ```
    *
    * An `AbortSignal` can be used to cancel waiting for the event:
    *
    * ```js
    * import { EventEmitter, once } from 'node:events';
    *
    * const ee = new EventEmitter();
    * const ac = new AbortController();
    *
    * async function foo(emitter, event, signal) {
    *   try {
    *     await once(emitter, event, { signal });
    *     console.log('event emitted!');
    *   } catch (error) {
    *     if (error.name === 'AbortError') {
    *       console.error('Waiting for the event was canceled!');
    *     } else {
    *       console.error('There was an error', error.message);
    *     }
    *   }
    * }
    *
    * foo(ee, 'foo', ac.signal);
    * ac.abort(); // Abort waiting for the event
    * ee.emit('foo'); // Prints: Waiting for the event was canceled!
    * ```
    * @since v11.13.0, v10.16.0
    */
  /* static member */
  inline def once(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: String): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(
    emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
    eventName: String,
    options: StaticEventEmitterOptions
  ): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap], eventName: js.Symbol): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(
    emitter: typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap],
    eventName: js.Symbol,
    options: StaticEventEmitterOptions
  ): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  
  /**
    * ```js
    * import { setMaxListeners, EventEmitter } from 'node:events';
    *
    * const target = new EventTarget();
    * const emitter = new EventEmitter();
    *
    * setMaxListeners(5, target, emitter);
    * ```
    * @since v15.4.0
    * @param n A non-negative number. The maximum number of listeners per `EventTarget` event.
    * @param eventsTargets Zero or more {EventTarget} or {EventEmitter} instances. If none are specified, `n` is set as the default max for all newly created {EventTarget} and {EventEmitter}
    * objects.
    */
  /* static member */
  inline def setMaxListeners(
    n: Double,
    eventTargets: (EventTarget | typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap])*
  ): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setMaxListeners")(scala.List(n.asInstanceOf[js.Any]).`++`(eventTargets.asInstanceOf[Seq[js.Any]])*).asInstanceOf[Unit]
  inline def setMaxListeners(
    n: Unit,
    eventTargets: (EventTarget | typings.node.eventsMod.global.NodeJS.EventEmitter[DefaultEventMap])*
  ): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setMaxListeners")(scala.List(n.asInstanceOf[js.Any]).`++`(eventTargets.asInstanceOf[Seq[js.Any]])*).asInstanceOf[Unit]
  
  trait Abortable extends StObject {
    
    /**
      * When provided the corresponding `AbortController` can be used to cancel an asynchronous action.
      */
    var signal: js.UndefOr[AbortSignal] = js.undefined
  }
  object Abortable {
    
    inline def apply(): Abortable = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[Abortable]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: Abortable] (val x: Self) extends AnyVal {
      
      inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
      
      inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    }
  }
  
  type AnyRest = /* args */ Array[Any]
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    T extends node.events.DefaultEventMap ? node.events.AnyRest : K extends keyof T ? T[K] : never
    }}}
    */
  type Args[K, T] = AnyRest
  
  type DefaultEventMap = js.Array[scala.Nothing]
  
  trait EventEmitterAsyncResourceOptions
    extends StObject
       with AsyncResourceOptions
       with EventEmitterOptions {
    
    /**
      * The type of async event, this is required when instantiating `EventEmitterAsyncResource`
      * directly rather than as a child class.
      * @default new.target.name if instantiated as a child class.
      */
    var name: js.UndefOr[String] = js.undefined
  }
  object EventEmitterAsyncResourceOptions {
    
    inline def apply(): EventEmitterAsyncResourceOptions = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[EventEmitterAsyncResourceOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: EventEmitterAsyncResourceOptions] (val x: Self) extends AnyVal {
      
      inline def setName(value: String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
      
      inline def setNameUndefined: Self = StObject.set(x, "name", js.undefined)
    }
  }
  
  // NOTE: This class is in the docs but is **not actually exported** by Node.
  // If https://github.com/nodejs/node/issues/39903 gets resolved and Node
  // actually starts exporting the class, uncomment below.
  // import { EventListener, EventListenerObject } from '__dom-events';
  // /** The NodeEventTarget is a Node.js-specific extension to EventTarget that emulates a subset of the EventEmitter API. */
  // interface NodeEventTarget extends EventTarget {
  //     /**
  //      * Node.js-specific extension to the `EventTarget` class that emulates the equivalent `EventEmitter` API.
  //      * The only difference between `addListener()` and `addEventListener()` is that addListener() will return a reference to the EventTarget.
  //      */
  //     addListener(type: string, listener: EventListener | EventListenerObject, options?: { once: boolean }): this;
  //     /** Node.js-specific extension to the `EventTarget` class that returns an array of event `type` names for which event listeners are registered. */
  //     eventNames(): string[];
  //     /** Node.js-specific extension to the `EventTarget` class that returns the number of event listeners registered for the `type`. */
  //     listenerCount(type: string): number;
  //     /** Node.js-specific alias for `eventTarget.removeListener()`. */
  //     off(type: string, listener: EventListener | EventListenerObject): this;
  //     /** Node.js-specific alias for `eventTarget.addListener()`. */
  //     on(type: string, listener: EventListener | EventListenerObject, options?: { once: boolean }): this;
  //     /** Node.js-specific extension to the `EventTarget` class that adds a `once` listener for the given event `type`. This is equivalent to calling `on` with the `once` option set to `true`. */
  //     once(type: string, listener: EventListener | EventListenerObject): this;
  //     /**
  //      * Node.js-specific extension to the `EventTarget` class.
  //      * If `type` is specified, removes all registered listeners for `type`,
  //      * otherwise removes all registered listeners.
  //      */
  //     removeAllListeners(type: string): this;
  //     /**
  //      * Node.js-specific extension to the `EventTarget` class that removes the listener for the given `type`.
  //      * The only difference between `removeListener()` and `removeEventListener()` is that `removeListener()` will return a reference to the `EventTarget`.
  //      */
  //     removeListener(type: string, listener: EventListener | EventListenerObject): this;
  // }
  trait EventEmitterOptions extends StObject {
    
    /**
      * Enables automatic capturing of promise rejection.
      */
    var captureRejections: js.UndefOr[Boolean] = js.undefined
  }
  object EventEmitterOptions {
    
    inline def apply(): EventEmitterOptions = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[EventEmitterOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: EventEmitterOptions] (val x: Self) extends AnyVal {
      
      inline def setCaptureRejections(value: Boolean): Self = StObject.set(x, "captureRejections", value.asInstanceOf[js.Any])
      
      inline def setCaptureRejectionsUndefined: Self = StObject.set(x, "captureRejections", js.undefined)
    }
  }
  
  @js.native
  trait EventEmitterReferencingAsyncResource extends AsyncResource {
    
    val eventEmitter: EventEmitterAsyncResource = js.native
  }
  
  type EventMap[T] = (Record[/* keyof T */ String, js.Array[Any]]) | DefaultEventMap
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    T extends node.events.DefaultEventMap ? string | symbol : K | keyof T
    }}}
    */
  type Key[K, T] = String | js.Symbol
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    T extends node.events.DefaultEventMap ? string | symbol : K & keyof T
    }}}
    */
  type Key2[K, T] = String | js.Symbol
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    T extends node.events.DefaultEventMap ? F : K extends keyof T ? T[K] extends std.Array<unknown> ? (args : T[K]): void : never : never
    }}}
    */
  type Listener[K, T, F] = F
  
  type Listener1[K, T] = Listener[K, T, js.Function1[/* repeated */ Any, Unit]]
  
  type Listener2[K, T] = Listener[K, T, js.Function]
  
  trait StaticEventEmitterIteratorOptions
    extends StObject
       with StaticEventEmitterOptions {
    
    /**
      * Names of events that will end the iteration.
      */
    var close: js.UndefOr[js.Array[String]] = js.undefined
    
    /**
      * The high watermark. The emitter is paused every time the size of events being buffered is higher than it.
      * Supported only on emitters implementing `pause()` and `resume()` methods.
      * @default Number.MAX_SAFE_INTEGER
      */
    var highWaterMark: js.UndefOr[Double] = js.undefined
    
    /**
      * The low watermark. The emitter is resumed every time the size of events being buffered is lower than it.
      * Supported only on emitters implementing `pause()` and `resume()` methods.
      * @default 1
      */
    var lowWaterMark: js.UndefOr[Double] = js.undefined
  }
  object StaticEventEmitterIteratorOptions {
    
    inline def apply(): StaticEventEmitterIteratorOptions = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[StaticEventEmitterIteratorOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: StaticEventEmitterIteratorOptions] (val x: Self) extends AnyVal {
      
      inline def setClose(value: js.Array[String]): Self = StObject.set(x, "close", value.asInstanceOf[js.Any])
      
      inline def setCloseUndefined: Self = StObject.set(x, "close", js.undefined)
      
      inline def setCloseVarargs(value: String*): Self = StObject.set(x, "close", js.Array(value*))
      
      inline def setHighWaterMark(value: Double): Self = StObject.set(x, "highWaterMark", value.asInstanceOf[js.Any])
      
      inline def setHighWaterMarkUndefined: Self = StObject.set(x, "highWaterMark", js.undefined)
      
      inline def setLowWaterMark(value: Double): Self = StObject.set(x, "lowWaterMark", value.asInstanceOf[js.Any])
      
      inline def setLowWaterMarkUndefined: Self = StObject.set(x, "lowWaterMark", js.undefined)
    }
  }
  
  trait StaticEventEmitterOptions extends StObject {
    
    /**
      * Can be used to cancel awaiting events.
      */
    var signal: js.UndefOr[AbortSignal] = js.undefined
  }
  object StaticEventEmitterOptions {
    
    inline def apply(): StaticEventEmitterOptions = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[StaticEventEmitterOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: StaticEventEmitterOptions] (val x: Self) extends AnyVal {
      
      inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
      
      inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    }
  }
  
  object global {
    
    object NodeJS {
      
      @js.native
      trait EventEmitter[T /* <: EventMap[T] */] extends StObject {
        
        /**
          * Alias for `emitter.on(eventName, listener)`.
          * @since v0.1.26
          */
        def addListener[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * Synchronously calls each of the listeners registered for the event named `eventName`, in the order they were registered, passing the supplied arguments
          * to each.
          *
          * Returns `true` if the event had listeners, `false` otherwise.
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          * const myEmitter = new EventEmitter();
          *
          * // First listener
          * myEmitter.on('event', function firstListener() {
          *   console.log('Helloooo! first listener');
          * });
          * // Second listener
          * myEmitter.on('event', function secondListener(arg1, arg2) {
          *   console.log(`event with parameters ${arg1}, ${arg2} in second listener`);
          * });
          * // Third listener
          * myEmitter.on('event', function thirdListener(...args) {
          *   const parameters = args.join(', ');
          *   console.log(`event with parameters ${parameters} in third listener`);
          * });
          *
          * console.log(myEmitter.listeners('event'));
          *
          * myEmitter.emit('event', 1, 2, 3, 4, 5);
          *
          * // Prints:
          * // [
          * //   [Function: firstListener],
          * //   [Function: secondListener],
          * //   [Function: thirdListener]
          * // ]
          * // Helloooo! first listener
          * // event with parameters 1, 2 in second listener
          * // event with parameters 1, 2, 3, 4, 5 in third listener
          * ```
          * @since v0.1.26
          */
        def emit[K](
          eventName: Key[K, T],
          /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type Args<K, T> is not an array type */ args: Args[K, T]
        ): Boolean = js.native
        
        /**
          * Returns an array listing the events for which the emitter has registered
          * listeners. The values in the array are strings or `Symbol`s.
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          *
          * const myEE = new EventEmitter();
          * myEE.on('foo', () => {});
          * myEE.on('bar', () => {});
          *
          * const sym = Symbol('symbol');
          * myEE.on(sym, () => {});
          *
          * console.log(myEE.eventNames());
          * // Prints: [ 'foo', 'bar', Symbol(symbol) ]
          * ```
          * @since v6.0.0
          */
        def eventNames(): js.Array[(String & (Key2[Any, T])) | (js.Symbol & (Key2[Any, T]))] = js.native
        
        /**
          * Returns the current max listener value for the `EventEmitter` which is either
          * set by `emitter.setMaxListeners(n)` or defaults to {@link defaultMaxListeners}.
          * @since v1.0.0
          */
        def getMaxListeners(): Double = js.native
        
        /**
          * Returns the number of listeners listening for the event named `eventName`.
          * If `listener` is provided, it will return how many times the listener is found
          * in the list of the listeners of the event.
          * @since v3.2.0
          * @param eventName The name of the event being listened for
          * @param listener The event handler function
          */
        def listenerCount[K](eventName: Key[K, T]): Double = js.native
        def listenerCount[K](eventName: Key[K, T], listener: Listener2[K, T]): Double = js.native
        
        /**
          * Returns a copy of the array of listeners for the event named `eventName`.
          *
          * ```js
          * server.on('connection', (stream) => {
          *   console.log('someone connected!');
          * });
          * console.log(util.inspect(server.listeners('connection')));
          * // Prints: [ [Function] ]
          * ```
          * @since v0.1.26
          */
        def listeners[K](eventName: Key[K, T]): js.Array[Listener2[K, T]] = js.native
        
        /**
          * Alias for `emitter.removeListener()`.
          * @since v10.0.0
          */
        def off[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * Adds the `listener` function to the end of the listeners array for the event
          * named `eventName`. No checks are made to see if the `listener` has already
          * been added. Multiple calls passing the same combination of `eventName` and
          * `listener` will result in the `listener` being added, and called, multiple times.
          *
          * ```js
          * server.on('connection', (stream) => {
          *   console.log('someone connected!');
          * });
          * ```
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          *
          * By default, event listeners are invoked in the order they are added. The `emitter.prependListener()` method can be used as an alternative to add the
          * event listener to the beginning of the listeners array.
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          * const myEE = new EventEmitter();
          * myEE.on('foo', () => console.log('a'));
          * myEE.prependListener('foo', () => console.log('b'));
          * myEE.emit('foo');
          * // Prints:
          * //   b
          * //   a
          * ```
          * @since v0.1.101
          * @param eventName The name of the event.
          * @param listener The callback function
          */
        def on[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * Adds a **one-time** `listener` function for the event named `eventName`. The
          * next time `eventName` is triggered, this listener is removed and then invoked.
          *
          * ```js
          * server.once('connection', (stream) => {
          *   console.log('Ah, we have our first user!');
          * });
          * ```
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          *
          * By default, event listeners are invoked in the order they are added. The `emitter.prependOnceListener()` method can be used as an alternative to add the
          * event listener to the beginning of the listeners array.
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          * const myEE = new EventEmitter();
          * myEE.once('foo', () => console.log('a'));
          * myEE.prependOnceListener('foo', () => console.log('b'));
          * myEE.emit('foo');
          * // Prints:
          * //   b
          * //   a
          * ```
          * @since v0.3.0
          * @param eventName The name of the event.
          * @param listener The callback function
          */
        def once[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * Adds the `listener` function to the _beginning_ of the listeners array for the
          * event named `eventName`. No checks are made to see if the `listener` has
          * already been added. Multiple calls passing the same combination of `eventName`
          * and `listener` will result in the `listener` being added, and called, multiple times.
          *
          * ```js
          * server.prependListener('connection', (stream) => {
          *   console.log('someone connected!');
          * });
          * ```
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          * @since v6.0.0
          * @param eventName The name of the event.
          * @param listener The callback function
          */
        def prependListener[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * Adds a **one-time**`listener` function for the event named `eventName` to the _beginning_ of the listeners array. The next time `eventName` is triggered, this
          * listener is removed, and then invoked.
          *
          * ```js
          * server.prependOnceListener('connection', (stream) => {
          *   console.log('Ah, we have our first user!');
          * });
          * ```
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          * @since v6.0.0
          * @param eventName The name of the event.
          * @param listener The callback function
          */
        def prependOnceListener[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * Returns a copy of the array of listeners for the event named `eventName`,
          * including any wrappers (such as those created by `.once()`).
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          * const emitter = new EventEmitter();
          * emitter.once('log', () => console.log('log once'));
          *
          * // Returns a new Array with a function `onceWrapper` which has a property
          * // `listener` which contains the original listener bound above
          * const listeners = emitter.rawListeners('log');
          * const logFnWrapper = listeners[0];
          *
          * // Logs "log once" to the console and does not unbind the `once` event
          * logFnWrapper.listener();
          *
          * // Logs "log once" to the console and removes the listener
          * logFnWrapper();
          *
          * emitter.on('log', () => console.log('log persistently'));
          * // Will return a new Array with a single function bound by `.on()` above
          * const newListeners = emitter.rawListeners('log');
          *
          * // Logs "log persistently" twice
          * newListeners[0]();
          * emitter.emit('log');
          * ```
          * @since v9.4.0
          */
        def rawListeners[K](eventName: Key[K, T]): js.Array[Listener2[K, T]] = js.native
        
        /**
          * Removes all listeners, or those of the specified `eventName`.
          *
          * It is bad practice to remove listeners added elsewhere in the code,
          * particularly when the `EventEmitter` instance was created by some other
          * component or module (e.g. sockets or file streams).
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          * @since v0.1.26
          */
        def removeAllListeners(): this.type = js.native
        def removeAllListeners(eventName: Key[Any, T]): this.type = js.native
        
        /**
          * Removes the specified `listener` from the listener array for the event named `eventName`.
          *
          * ```js
          * const callback = (stream) => {
          *   console.log('someone connected!');
          * };
          * server.on('connection', callback);
          * // ...
          * server.removeListener('connection', callback);
          * ```
          *
          * `removeListener()` will remove, at most, one instance of a listener from the
          * listener array. If any single listener has been added multiple times to the
          * listener array for the specified `eventName`, then `removeListener()` must be
          * called multiple times to remove each instance.
          *
          * Once an event is emitted, all listeners attached to it at the
          * time of emitting are called in order. This implies that any `removeListener()` or `removeAllListeners()` calls _after_ emitting and _before_ the last listener finishes execution
          * will not remove them from`emit()` in progress. Subsequent events behave as expected.
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          * class MyEmitter extends EventEmitter {}
          * const myEmitter = new MyEmitter();
          *
          * const callbackA = () => {
          *   console.log('A');
          *   myEmitter.removeListener('event', callbackB);
          * };
          *
          * const callbackB = () => {
          *   console.log('B');
          * };
          *
          * myEmitter.on('event', callbackA);
          *
          * myEmitter.on('event', callbackB);
          *
          * // callbackA removes listener callbackB but it will still be called.
          * // Internal listener array at time of emit [callbackA, callbackB]
          * myEmitter.emit('event');
          * // Prints:
          * //   A
          * //   B
          *
          * // callbackB is now removed.
          * // Internal listener array [callbackA]
          * myEmitter.emit('event');
          * // Prints:
          * //   A
          * ```
          *
          * Because listeners are managed using an internal array, calling this will
          * change the position indices of any listener registered _after_ the listener
          * being removed. This will not impact the order in which listeners are called,
          * but it means that any copies of the listener array as returned by
          * the `emitter.listeners()` method will need to be recreated.
          *
          * When a single function has been added as a handler multiple times for a single
          * event (as in the example below), `removeListener()` will remove the most
          * recently added instance. In the example the `once('ping')` listener is removed:
          *
          * ```js
          * import { EventEmitter } from 'node:events';
          * const ee = new EventEmitter();
          *
          * function pong() {
          *   console.log('pong');
          * }
          *
          * ee.on('ping', pong);
          * ee.once('ping', pong);
          * ee.removeListener('ping', pong);
          *
          * ee.emit('ping');
          * ee.emit('ping');
          * ```
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          * @since v0.1.26
          */
        def removeListener[K](eventName: Key[K, T], listener: Listener1[K, T]): this.type = js.native
        
        /**
          * By default `EventEmitter`s will print a warning if more than `10` listeners are
          * added for a particular event. This is a useful default that helps finding
          * memory leaks. The `emitter.setMaxListeners()` method allows the limit to be
          * modified for this specific `EventEmitter` instance. The value can be set to `Infinity` (or `0`) to indicate an unlimited number of listeners.
          *
          * Returns a reference to the `EventEmitter`, so that calls can be chained.
          * @since v0.3.5
          */
        def setMaxListeners(n: Double): this.type = js.native
      }
    }
  }
}

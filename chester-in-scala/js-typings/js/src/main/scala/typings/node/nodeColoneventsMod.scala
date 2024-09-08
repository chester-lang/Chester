package typings.node

import org.scalajs.dom.Event
import org.scalajs.dom.EventTarget
import typings.node.eventsMod.DefaultEventMap
import typings.node.eventsMod.EventEmitterAsyncResourceOptions
import typings.node.eventsMod.EventEmitterOptions
import typings.node.eventsMod.EventMap
import typings.node.eventsMod.StaticEventEmitterIteratorOptions
import typings.node.eventsMod.StaticEventEmitterOptions
import typings.node.eventsMod.global.NodeJS.EventEmitter
import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.Disposable
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object nodeColoneventsMod {
  
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
  @JSImport("node:events", JSImport.Namespace)
  @js.native
  open class ^[T /* <: EventMap[T] */] () extends StObject {
    def this(options: EventEmitterOptions) = this()
  }
  @JSImport("node:events", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  @JSImport("node:events", "EventEmitterAsyncResource")
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
  @JSImport("node:events", "captureRejectionSymbol")
  @js.native
  val captureRejectionSymbol: js.Symbol = js.native
  
  /**
    * Value: [boolean](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Data_structures#Boolean_type)
    *
    * Change the default `captureRejections` option on all new `EventEmitter` objects.
    * @since v13.4.0, v12.16.0
    */
  /* static member */
  @JSImport("node:events", "captureRejections")
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
  @JSImport("node:events", "defaultMaxListeners")
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
  @JSImport("node:events", "errorMonitor")
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
  inline def getEventListeners(emitter: EventEmitter[DefaultEventMap], name: String): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
  inline def getEventListeners(emitter: EventEmitter[DefaultEventMap], name: js.Symbol): js.Array[js.Function] = (^.asInstanceOf[js.Dynamic].applyDynamic("getEventListeners")(emitter.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[js.Array[js.Function]]
  
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
  inline def getMaxListeners(emitter: EventEmitter[DefaultEventMap]): Double = ^.asInstanceOf[js.Dynamic].applyDynamic("getMaxListeners")(emitter.asInstanceOf[js.Any]).asInstanceOf[Double]
  
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
  inline def listenerCount(emitter: EventEmitter[DefaultEventMap], eventName: String): Double = (^.asInstanceOf[js.Dynamic].applyDynamic("listenerCount")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Double]
  inline def listenerCount(emitter: EventEmitter[DefaultEventMap], eventName: js.Symbol): Double = (^.asInstanceOf[js.Dynamic].applyDynamic("listenerCount")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Double]
  
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
  inline def on(emitter: EventEmitter[DefaultEventMap], eventName: String): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(
    emitter: EventEmitter[DefaultEventMap],
    eventName: String,
    options: StaticEventEmitterIteratorOptions
  ): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(emitter: EventEmitter[DefaultEventMap], eventName: js.Symbol): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("on")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[Any]
  inline def on(
    emitter: EventEmitter[DefaultEventMap],
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
  inline def once(emitter: EventEmitter[DefaultEventMap], eventName: String): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(emitter: EventEmitter[DefaultEventMap], eventName: String, options: StaticEventEmitterOptions): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(emitter: EventEmitter[DefaultEventMap], eventName: js.Symbol): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  inline def once(emitter: EventEmitter[DefaultEventMap], eventName: js.Symbol, options: StaticEventEmitterOptions): js.Promise[js.Array[Any]] = (^.asInstanceOf[js.Dynamic].applyDynamic("once")(emitter.asInstanceOf[js.Any], eventName.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[js.Array[Any]]]
  
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
  inline def setMaxListeners(n: Double, eventTargets: (EventTarget | EventEmitter[DefaultEventMap])*): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setMaxListeners")(scala.List(n.asInstanceOf[js.Any]).`++`(eventTargets.asInstanceOf[Seq[js.Any]])*).asInstanceOf[Unit]
  inline def setMaxListeners(n: Unit, eventTargets: (EventTarget | EventEmitter[DefaultEventMap])*): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setMaxListeners")(scala.List(n.asInstanceOf[js.Any]).`++`(eventTargets.asInstanceOf[Seq[js.Any]])*).asInstanceOf[Unit]
}

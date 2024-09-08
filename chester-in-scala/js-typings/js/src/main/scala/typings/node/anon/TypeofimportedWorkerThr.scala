package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import typings.node.nodeColonurlMod.URL
import typings.node.vmMod.Context
import typings.node.workerThreadsMod.BroadcastChannel
import typings.node.workerThreadsMod.MessageChannel
import typings.node.workerThreadsMod.MessagePort
import typings.node.workerThreadsMod.ResourceLimits_
import typings.node.workerThreadsMod.Serializable
import typings.node.workerThreadsMod.Worker
import typings.node.workerThreadsMod.WorkerOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedWorkerThr extends StObject {
  
  /**
    * Instances of `BroadcastChannel` allow asynchronous one-to-many communication
    * with all other `BroadcastChannel` instances bound to the same channel name.
    *
    * ```js
    * 'use strict';
    *
    * const {
    *   isMainThread,
    *   BroadcastChannel,
    *   Worker,
    * } = require('node:worker_threads');
    *
    * const bc = new BroadcastChannel('hello');
    *
    * if (isMainThread) {
    *   let c = 0;
    *   bc.onmessage = (event) => {
    *     console.log(event.data);
    *     if (++c === 10) bc.close();
    *   };
    *   for (let n = 0; n < 10; n++)
    *     new Worker(__filename);
    * } else {
    *   bc.postMessage('hello from every worker');
    *   bc.close();
    * }
    * ```
    * @since v15.4.0
    */
  var BroadcastChannel: Instantiable1[/* name */ String, typings.node.workerThreadsMod.BroadcastChannel]
  
  /**
    * Instances of the `worker.MessageChannel` class represent an asynchronous,
    * two-way communications channel.
    * The `MessageChannel` has no methods of its own. `new MessageChannel()` yields an object with `port1` and `port2` properties, which refer to linked `MessagePort` instances.
    *
    * ```js
    * const { MessageChannel } = require('node:worker_threads');
    *
    * const { port1, port2 } = new MessageChannel();
    * port1.on('message', (message) => console.log('received', message));
    * port2.postMessage({ foo: 'bar' });
    * // Prints: received { foo: 'bar' } from the `port1.on('message')` listener
    * ```
    * @since v10.5.0
    */
  var MessageChannel: Instantiable0[typings.node.workerThreadsMod.MessageChannel]
  
  /**
    * Instances of the `worker.MessagePort` class represent one end of an
    * asynchronous, two-way communications channel. It can be used to transfer
    * structured data, memory regions and other `MessagePort`s between different `Worker`s.
    *
    * This implementation matches [browser `MessagePort`](https://developer.mozilla.org/en-US/docs/Web/API/MessagePort) s.
    * @since v10.5.0
    */
  var MessagePort: Instantiable0[typings.node.workerThreadsMod.MessagePort]
  
  val SHARE_ENV: js.Symbol
  
  /**
    * The `Worker` class represents an independent JavaScript execution thread.
    * Most Node.js APIs are available inside of it.
    *
    * Notable differences inside a Worker environment are:
    *
    * * The `process.stdin`, `process.stdout`, and `process.stderr` streams may be redirected by the parent thread.
    * * The `require('node:worker_threads').isMainThread` property is set to `false`.
    * * The `require('node:worker_threads').parentPort` message port is available.
    * * `process.exit()` does not stop the whole program, just the single thread,
    * and `process.abort()` is not available.
    * * `process.chdir()` and `process` methods that set group or user ids
    * are not available.
    * * `process.env` is a copy of the parent thread's environment variables,
    * unless otherwise specified. Changes to one copy are not visible in other
    * threads, and are not visible to native add-ons (unless `worker.SHARE_ENV` is passed as the `env` option to the `Worker` constructor). On Windows, unlike the main thread, a copy of the
    * environment variables operates in a case-sensitive manner.
    * * `process.title` cannot be modified.
    * * Signals are not delivered through `process.on('...')`.
    * * Execution may stop at any point as a result of `worker.terminate()` being invoked.
    * * IPC channels from parent processes are not accessible.
    * * The `trace_events` module is not supported.
    * * Native add-ons can only be loaded from multiple threads if they fulfill `certain conditions`.
    *
    * Creating `Worker` instances inside of other `Worker`s is possible.
    *
    * Like [Web Workers](https://developer.mozilla.org/en-US/docs/Web/API/Web_Workers_API) and the `node:cluster module`, two-way communication
    * can be achieved through inter-thread message passing. Internally, a `Worker` has
    * a built-in pair of `MessagePort` s that are already associated with each
    * other when the `Worker` is created. While the `MessagePort` object on the parent
    * side is not directly exposed, its functionalities are exposed through `worker.postMessage()` and the `worker.on('message')` event
    * on the `Worker` object for the parent thread.
    *
    * To create custom messaging channels (which is encouraged over using the default
    * global channel because it facilitates separation of concerns), users can create
    * a `MessageChannel` object on either thread and pass one of the`MessagePort`s on that `MessageChannel` to the other thread through a
    * pre-existing channel, such as the global one.
    *
    * See `port.postMessage()` for more information on how messages are passed,
    * and what kind of JavaScript values can be successfully transported through
    * the thread barrier.
    *
    * ```js
    * const assert = require('node:assert');
    * const {
    *   Worker, MessageChannel, MessagePort, isMainThread, parentPort,
    * } = require('node:worker_threads');
    * if (isMainThread) {
    *   const worker = new Worker(__filename);
    *   const subChannel = new MessageChannel();
    *   worker.postMessage({ hereIsYourPort: subChannel.port1 }, [subChannel.port1]);
    *   subChannel.port2.on('message', (value) => {
    *     console.log('received:', value);
    *   });
    * } else {
    *   parentPort.once('message', (value) => {
    *     assert(value.hereIsYourPort instanceof MessagePort);
    *     value.hereIsYourPort.postMessage('the worker is sending this');
    *     value.hereIsYourPort.close();
    *   });
    * }
    * ```
    * @since v10.5.0
    */
  var Worker: Instantiable2[
    /* filename */ String | URL, 
    /* options */ js.UndefOr[WorkerOptions], 
    typings.node.workerThreadsMod.Worker
  ]
  
  /**
    * Within a worker thread, `worker.getEnvironmentData()` returns a clone
    * of data passed to the spawning thread's `worker.setEnvironmentData()`.
    * Every new `Worker` receives its own copy of the environment data
    * automatically.
    *
    * ```js
    * const {
    *   Worker,
    *   isMainThread,
    *   setEnvironmentData,
    *   getEnvironmentData,
    * } = require('node:worker_threads');
    *
    * if (isMainThread) {
    *   setEnvironmentData('Hello', 'World!');
    *   const worker = new Worker(__filename);
    * } else {
    *   console.log(getEnvironmentData('Hello'));  // Prints 'World!'.
    * }
    * ```
    * @since v15.12.0, v14.18.0
    * @param key Any arbitrary, cloneable JavaScript value that can be used as a {Map} key.
    */
  def getEnvironmentData(key: Serializable): Serializable
  
  val isMainThread: Boolean
  
  /**
    * Mark an object as not transferable. If `object` occurs in the transfer list of
    * a `port.postMessage()` call, it is ignored.
    *
    * In particular, this makes sense for objects that can be cloned, rather than
    * transferred, and which are used by other objects on the sending side.
    * For example, Node.js marks the `ArrayBuffer`s it uses for its `Buffer pool` with this.
    *
    * This operation cannot be undone.
    *
    * ```js
    * const { MessageChannel, markAsUntransferable } = require('node:worker_threads');
    *
    * const pooledBuffer = new ArrayBuffer(8);
    * const typedArray1 = new Uint8Array(pooledBuffer);
    * const typedArray2 = new Float64Array(pooledBuffer);
    *
    * markAsUntransferable(pooledBuffer);
    *
    * const { port1 } = new MessageChannel();
    * port1.postMessage(typedArray1, [ typedArray1.buffer ]);
    *
    * // The following line prints the contents of typedArray1 -- it still owns
    * // its memory and has been cloned, not transferred. Without
    * // `markAsUntransferable()`, this would print an empty Uint8Array.
    * // typedArray2 is intact as well.
    * console.log(typedArray1);
    * console.log(typedArray2);
    * ```
    *
    * There is no equivalent to this API in browsers.
    * @since v14.5.0, v12.19.0
    */
  def markAsUntransferable(`object`: js.Object): Unit
  
  /**
    * Transfer a `MessagePort` to a different `vm` Context. The original `port` object is rendered unusable, and the returned `MessagePort` instance
    * takes its place.
    *
    * The returned `MessagePort` is an object in the target context and
    * inherits from its global `Object` class. Objects passed to the [`port.onmessage()`](https://developer.mozilla.org/en-US/docs/Web/API/MessagePort/onmessage) listener are also created in the
    * target context
    * and inherit from its global `Object` class.
    *
    * However, the created `MessagePort` no longer inherits from [`EventTarget`](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget), and only
    * [`port.onmessage()`](https://developer.mozilla.org/en-US/docs/Web/API/MessagePort/onmessage) can be used to receive
    * events using it.
    * @since v11.13.0
    * @param port The message port to transfer.
    * @param contextifiedSandbox A `contextified` object as returned by the `vm.createContext()` method.
    */
  def moveMessagePortToContext(port: MessagePort, contextifiedSandbox: Context): MessagePort
  
  val parentPort: Null | MessagePort
  
  /**
    * Receive a single message from a given `MessagePort`. If no message is available,`undefined` is returned, otherwise an object with a single `message` property
    * that contains the message payload, corresponding to the oldest message in the `MessagePort`'s queue.
    *
    * ```js
    * const { MessageChannel, receiveMessageOnPort } = require('node:worker_threads');
    * const { port1, port2 } = new MessageChannel();
    * port1.postMessage({ hello: 'world' });
    *
    * console.log(receiveMessageOnPort(port2));
    * // Prints: { message: { hello: 'world' } }
    * console.log(receiveMessageOnPort(port2));
    * // Prints: undefined
    * ```
    *
    * When this function is used, no `'message'` event is emitted and the `onmessage` listener is not invoked.
    * @since v12.3.0
    */
  def receiveMessageOnPort(port: MessagePort): js.UndefOr[Message]
  
  val resourceLimits: ResourceLimits_
  
  /**
    * The `worker.setEnvironmentData()` API sets the content of `worker.getEnvironmentData()` in the current thread and all new `Worker` instances spawned from the current context.
    * @since v15.12.0, v14.18.0
    * @param key Any arbitrary, cloneable JavaScript value that can be used as a {Map} key.
    * @param value Any arbitrary, cloneable JavaScript value that will be cloned and passed automatically to all new `Worker` instances. If `value` is passed as `undefined`, any previously set value
    * for the `key` will be deleted.
    */
  def setEnvironmentData(key: Serializable, value: Serializable): Unit
  
  val threadId: Double
  
  val workerData: Any
}
object TypeofimportedWorkerThr {
  
  inline def apply(
    BroadcastChannel: Instantiable1[/* name */ String, BroadcastChannel],
    MessageChannel: Instantiable0[MessageChannel],
    MessagePort: Instantiable0[MessagePort],
    SHARE_ENV: js.Symbol,
    Worker: Instantiable2[/* filename */ String | URL, /* options */ js.UndefOr[WorkerOptions], Worker],
    getEnvironmentData: Serializable => Serializable,
    isMainThread: Boolean,
    markAsUntransferable: js.Object => Unit,
    moveMessagePortToContext: (MessagePort, Context) => MessagePort,
    receiveMessageOnPort: MessagePort => js.UndefOr[Message],
    resourceLimits: ResourceLimits_,
    setEnvironmentData: (Serializable, Serializable) => Unit,
    threadId: Double,
    workerData: Any
  ): TypeofimportedWorkerThr = {
    val __obj = js.Dynamic.literal(BroadcastChannel = BroadcastChannel.asInstanceOf[js.Any], MessageChannel = MessageChannel.asInstanceOf[js.Any], MessagePort = MessagePort.asInstanceOf[js.Any], SHARE_ENV = SHARE_ENV.asInstanceOf[js.Any], Worker = Worker.asInstanceOf[js.Any], getEnvironmentData = js.Any.fromFunction1(getEnvironmentData), isMainThread = isMainThread.asInstanceOf[js.Any], markAsUntransferable = js.Any.fromFunction1(markAsUntransferable), moveMessagePortToContext = js.Any.fromFunction2(moveMessagePortToContext), receiveMessageOnPort = js.Any.fromFunction1(receiveMessageOnPort), resourceLimits = resourceLimits.asInstanceOf[js.Any], setEnvironmentData = js.Any.fromFunction2(setEnvironmentData), threadId = threadId.asInstanceOf[js.Any], workerData = workerData.asInstanceOf[js.Any], parentPort = null)
    __obj.asInstanceOf[TypeofimportedWorkerThr]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedWorkerThr] (val x: Self) extends AnyVal {
    
    inline def setBroadcastChannel(value: Instantiable1[/* name */ String, BroadcastChannel]): Self = StObject.set(x, "BroadcastChannel", value.asInstanceOf[js.Any])
    
    inline def setGetEnvironmentData(value: Serializable => Serializable): Self = StObject.set(x, "getEnvironmentData", js.Any.fromFunction1(value))
    
    inline def setIsMainThread(value: Boolean): Self = StObject.set(x, "isMainThread", value.asInstanceOf[js.Any])
    
    inline def setMarkAsUntransferable(value: js.Object => Unit): Self = StObject.set(x, "markAsUntransferable", js.Any.fromFunction1(value))
    
    inline def setMessageChannel(value: Instantiable0[MessageChannel]): Self = StObject.set(x, "MessageChannel", value.asInstanceOf[js.Any])
    
    inline def setMessagePort(value: Instantiable0[MessagePort]): Self = StObject.set(x, "MessagePort", value.asInstanceOf[js.Any])
    
    inline def setMoveMessagePortToContext(value: (MessagePort, Context) => MessagePort): Self = StObject.set(x, "moveMessagePortToContext", js.Any.fromFunction2(value))
    
    inline def setParentPort(value: MessagePort): Self = StObject.set(x, "parentPort", value.asInstanceOf[js.Any])
    
    inline def setParentPortNull: Self = StObject.set(x, "parentPort", null)
    
    inline def setReceiveMessageOnPort(value: MessagePort => js.UndefOr[Message]): Self = StObject.set(x, "receiveMessageOnPort", js.Any.fromFunction1(value))
    
    inline def setResourceLimits(value: ResourceLimits_): Self = StObject.set(x, "resourceLimits", value.asInstanceOf[js.Any])
    
    inline def setSHARE_ENV(value: js.Symbol): Self = StObject.set(x, "SHARE_ENV", value.asInstanceOf[js.Any])
    
    inline def setSetEnvironmentData(value: (Serializable, Serializable) => Unit): Self = StObject.set(x, "setEnvironmentData", js.Any.fromFunction2(value))
    
    inline def setThreadId(value: Double): Self = StObject.set(x, "threadId", value.asInstanceOf[js.Any])
    
    inline def setWorker(value: Instantiable2[/* filename */ String | URL, /* options */ js.UndefOr[WorkerOptions], Worker]): Self = StObject.set(x, "Worker", value.asInstanceOf[js.Any])
    
    inline def setWorkerData(value: Any): Self = StObject.set(x, "workerData", value.asInstanceOf[js.Any])
  }
}

package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable2
import typings.node.asyncHooksMod.AsyncHook
import typings.node.asyncHooksMod.AsyncResourceOptions
import typings.node.asyncHooksMod.HookCallbacks
import typings.node.nodeColonasyncHooksMod.AsyncLocalStorage
import typings.node.nodeColonasyncHooksMod.AsyncResource
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedNodeAsync extends StObject {
  
  /**
    * This class creates stores that stay coherent through asynchronous operations.
    *
    * While you can create your own implementation on top of the `node:async_hooks` module, `AsyncLocalStorage` should be preferred as it is a performant and memory
    * safe implementation that involves significant optimizations that are non-obvious
    * to implement.
    *
    * The following example uses `AsyncLocalStorage` to build a simple logger
    * that assigns IDs to incoming HTTP requests and includes them in messages
    * logged within each request.
    *
    * ```js
    * import http from 'node:http';
    * import { AsyncLocalStorage } from 'node:async_hooks';
    *
    * const asyncLocalStorage = new AsyncLocalStorage();
    *
    * function logWithId(msg) {
    *   const id = asyncLocalStorage.getStore();
    *   console.log(`${id !== undefined ? id : '-'}:`, msg);
    * }
    *
    * let idSeq = 0;
    * http.createServer((req, res) => {
    *   asyncLocalStorage.run(idSeq++, () => {
    *     logWithId('start');
    *     // Imagine any chain of async operations here
    *     setImmediate(() => {
    *       logWithId('finish');
    *       res.end();
    *     });
    *   });
    * }).listen(8080);
    *
    * http.get('http://localhost:8080');
    * http.get('http://localhost:8080');
    * // Prints:
    * //   0: start
    * //   1: start
    * //   0: finish
    * //   1: finish
    * ```
    *
    * Each instance of `AsyncLocalStorage` maintains an independent storage context.
    * Multiple instances can safely exist simultaneously without risk of interfering
    * with each other's data.
    * @since v13.10.0, v12.17.0
    */
  var AsyncLocalStorage: Instantiable0[typings.node.nodeColonasyncHooksMod.AsyncLocalStorage[js.Object]]
  
  /**
    * The class `AsyncResource` is designed to be extended by the embedder's async
    * resources. Using this, users can easily trigger the lifetime events of their
    * own resources.
    *
    * The `init` hook will trigger when an `AsyncResource` is instantiated.
    *
    * The following is an overview of the `AsyncResource` API.
    *
    * ```js
    * import { AsyncResource, executionAsyncId } from 'node:async_hooks';
    *
    * // AsyncResource() is meant to be extended. Instantiating a
    * // new AsyncResource() also triggers init. If triggerAsyncId is omitted then
    * // async_hook.executionAsyncId() is used.
    * const asyncResource = new AsyncResource(
    *   type, { triggerAsyncId: executionAsyncId(), requireManualDestroy: false },
    * );
    *
    * // Run a function in the execution context of the resource. This will
    * // * establish the context of the resource
    * // * trigger the AsyncHooks before callbacks
    * // * call the provided function `fn` with the supplied arguments
    * // * trigger the AsyncHooks after callbacks
    * // * restore the original execution context
    * asyncResource.runInAsyncScope(fn, thisArg, ...args);
    *
    * // Call AsyncHooks destroy callbacks.
    * asyncResource.emitDestroy();
    *
    * // Return the unique ID assigned to the AsyncResource instance.
    * asyncResource.asyncId();
    *
    * // Return the trigger ID for the AsyncResource instance.
    * asyncResource.triggerAsyncId();
    * ```
    */
  var AsyncResource: Instantiable2[
    /* type */ String, 
    /* triggerAsyncId */ js.UndefOr[Double | AsyncResourceOptions], 
    typings.node.nodeColonasyncHooksMod.AsyncResource
  ]
  
  /**
    * Registers functions to be called for different lifetime events of each async
    * operation.
    *
    * The callbacks `init()`/`before()`/`after()`/`destroy()` are called for the
    * respective asynchronous event during a resource's lifetime.
    *
    * All callbacks are optional. For example, if only resource cleanup needs to
    * be tracked, then only the `destroy` callback needs to be passed. The
    * specifics of all functions that can be passed to `callbacks` is in the `Hook Callbacks` section.
    *
    * ```js
    * import { createHook } from 'node:async_hooks';
    *
    * const asyncHook = createHook({
    *   init(asyncId, type, triggerAsyncId, resource) { },
    *   destroy(asyncId) { },
    * });
    * ```
    *
    * The callbacks will be inherited via the prototype chain:
    *
    * ```js
    * class MyAsyncCallbacks {
    *   init(asyncId, type, triggerAsyncId, resource) { }
    *   destroy(asyncId) {}
    * }
    *
    * class MyAddedCallbacks extends MyAsyncCallbacks {
    *   before(asyncId) { }
    *   after(asyncId) { }
    * }
    *
    * const asyncHook = async_hooks.createHook(new MyAddedCallbacks());
    * ```
    *
    * Because promises are asynchronous resources whose lifecycle is tracked
    * via the async hooks mechanism, the `init()`, `before()`, `after()`, and`destroy()` callbacks _must not_ be async functions that return promises.
    * @since v8.1.0
    * @param callbacks The `Hook Callbacks` to register
    * @return Instance used for disabling and enabling hooks
    */
  def createHook(callbacks: HookCallbacks): AsyncHook
  
  /**
    * ```js
    * import { executionAsyncId } from 'node:async_hooks';
    * import fs from 'node:fs';
    *
    * console.log(executionAsyncId());  // 1 - bootstrap
    * const path = '.';
    * fs.open(path, 'r', (err, fd) => {
    *   console.log(executionAsyncId());  // 6 - open()
    * });
    * ```
    *
    * The ID returned from `executionAsyncId()` is related to execution timing, not
    * causality (which is covered by `triggerAsyncId()`):
    *
    * ```js
    * const server = net.createServer((conn) => {
    *   // Returns the ID of the server, not of the new connection, because the
    *   // callback runs in the execution scope of the server's MakeCallback().
    *   async_hooks.executionAsyncId();
    *
    * }).listen(port, () => {
    *   // Returns the ID of a TickObject (process.nextTick()) because all
    *   // callbacks passed to .listen() are wrapped in a nextTick().
    *   async_hooks.executionAsyncId();
    * });
    * ```
    *
    * Promise contexts may not get precise `executionAsyncIds` by default.
    * See the section on [promise execution tracking](https://nodejs.org/docs/latest-v22.x/api/async_hooks.html#promise-execution-tracking).
    * @since v8.1.0
    * @return The `asyncId` of the current execution context. Useful to track when something calls.
    */
  def executionAsyncId(): Double
  
  /**
    * Resource objects returned by `executionAsyncResource()` are most often internal
    * Node.js handle objects with undocumented APIs. Using any functions or properties
    * on the object is likely to crash your application and should be avoided.
    *
    * Using `executionAsyncResource()` in the top-level execution context will
    * return an empty object as there is no handle or request object to use,
    * but having an object representing the top-level can be helpful.
    *
    * ```js
    * import { open } from 'node:fs';
    * import { executionAsyncId, executionAsyncResource } from 'node:async_hooks';
    *
    * console.log(executionAsyncId(), executionAsyncResource());  // 1 {}
    * open(new URL(import.meta.url), 'r', (err, fd) => {
    *   console.log(executionAsyncId(), executionAsyncResource());  // 7 FSReqWrap
    * });
    * ```
    *
    * This can be used to implement continuation local storage without the
    * use of a tracking `Map` to store the metadata:
    *
    * ```js
    * import { createServer } from 'node:http';
    * import {
    *   executionAsyncId,
    *   executionAsyncResource,
    *   createHook,
    * } from 'async_hooks';
    * const sym = Symbol('state'); // Private symbol to avoid pollution
    *
    * createHook({
    *   init(asyncId, type, triggerAsyncId, resource) {
    *     const cr = executionAsyncResource();
    *     if (cr) {
    *       resource[sym] = cr[sym];
    *     }
    *   },
    * }).enable();
    *
    * const server = createServer((req, res) => {
    *   executionAsyncResource()[sym] = { state: req.url };
    *   setTimeout(function() {
    *     res.end(JSON.stringify(executionAsyncResource()[sym]));
    *   }, 100);
    * }).listen(3000);
    * ```
    * @since v13.9.0, v12.17.0
    * @return The resource representing the current execution. Useful to store data within the resource.
    */
  def executionAsyncResource(): js.Object
  
  /**
    * ```js
    * const server = net.createServer((conn) => {
    *   // The resource that caused (or triggered) this callback to be called
    *   // was that of the new connection. Thus the return value of triggerAsyncId()
    *   // is the asyncId of "conn".
    *   async_hooks.triggerAsyncId();
    *
    * }).listen(port, () => {
    *   // Even though all callbacks passed to .listen() are wrapped in a nextTick()
    *   // the callback itself exists because the call to the server's .listen()
    *   // was made. So the return value would be the ID of the server.
    *   async_hooks.triggerAsyncId();
    * });
    * ```
    *
    * Promise contexts may not get valid `triggerAsyncId`s by default. See
    * the section on [promise execution tracking](https://nodejs.org/docs/latest-v22.x/api/async_hooks.html#promise-execution-tracking).
    * @return The ID of the resource responsible for calling the callback that is currently being executed.
    */
  def triggerAsyncId(): Double
}
object TypeofimportedNodeAsync {
  
  inline def apply(
    AsyncLocalStorage: Instantiable0[AsyncLocalStorage[js.Object]],
    AsyncResource: Instantiable2[
      /* type */ String, 
      /* triggerAsyncId */ js.UndefOr[Double | AsyncResourceOptions], 
      AsyncResource
    ],
    createHook: HookCallbacks => AsyncHook,
    executionAsyncId: () => Double,
    executionAsyncResource: () => js.Object,
    triggerAsyncId: () => Double
  ): TypeofimportedNodeAsync = {
    val __obj = js.Dynamic.literal(AsyncLocalStorage = AsyncLocalStorage.asInstanceOf[js.Any], AsyncResource = AsyncResource.asInstanceOf[js.Any], createHook = js.Any.fromFunction1(createHook), executionAsyncId = js.Any.fromFunction0(executionAsyncId), executionAsyncResource = js.Any.fromFunction0(executionAsyncResource), triggerAsyncId = js.Any.fromFunction0(triggerAsyncId))
    __obj.asInstanceOf[TypeofimportedNodeAsync]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedNodeAsync] (val x: Self) extends AnyVal {
    
    inline def setAsyncLocalStorage(value: Instantiable0[AsyncLocalStorage[js.Object]]): Self = StObject.set(x, "AsyncLocalStorage", value.asInstanceOf[js.Any])
    
    inline def setAsyncResource(
      value: Instantiable2[
          /* type */ String, 
          /* triggerAsyncId */ js.UndefOr[Double | AsyncResourceOptions], 
          AsyncResource
        ]
    ): Self = StObject.set(x, "AsyncResource", value.asInstanceOf[js.Any])
    
    inline def setCreateHook(value: HookCallbacks => AsyncHook): Self = StObject.set(x, "createHook", js.Any.fromFunction1(value))
    
    inline def setExecutionAsyncId(value: () => Double): Self = StObject.set(x, "executionAsyncId", js.Any.fromFunction0(value))
    
    inline def setExecutionAsyncResource(value: () => js.Object): Self = StObject.set(x, "executionAsyncResource", js.Any.fromFunction0(value))
    
    inline def setTriggerAsyncId(value: () => Double): Self = StObject.set(x, "triggerAsyncId", js.Any.fromFunction0(value))
  }
}

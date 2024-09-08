package typings.node

import org.scalablytyped.runtime.Shortcut
import typings.node.childProcessMod.MessageOptions
import typings.node.childProcessMod.SendHandle
import typings.node.childProcessMod.Serializable
import typings.node.eventsMod.EventEmitterOptions
import typings.node.globalsMod.global.NodeJS.Dict
import typings.node.nodeColonchildProcessMod.ChildProcess
import typings.node.nodeColonnetMod.Server
import typings.node.nodeColonnetMod.Socket
import typings.node.nodeInts.`-1`
import typings.node.nodeInts.`4`
import typings.node.nodeInts.`6`
import typings.node.nodeStrings.disconnect
import typings.node.nodeStrings.error
import typings.node.nodeStrings.exit
import typings.node.nodeStrings.fork
import typings.node.nodeStrings.listening
import typings.node.nodeStrings.message
import typings.node.nodeStrings.online
import typings.node.nodeStrings.setup
import typings.node.nodeStrings.udp4
import typings.node.nodeStrings.udp6
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object clusterMod extends Shortcut {
  
  @JSImport("cluster", JSImport.Default)
  @js.native
  val default: Cluster = js.native
  
  @JSImport("cluster", "Worker")
  @js.native
  open class Worker () extends StObject {
    def this(options: EventEmitterOptions) = this()
    
    /**
      * events.EventEmitter
      *   1. disconnect
      *   2. error
      *   3. exit
      *   4. listening
      *   5. message
      *   6. online
      */
    def addListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_disconnect(event: disconnect, listener: js.Function0[Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_exit(event: exit, listener: js.Function2[/* code */ Double, /* signal */ String, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_listening(event: listening, listener: js.Function1[/* address */ Address, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_message(event: message, listener: js.Function2[/* message */ Any, /* handle */ Socket | Server, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("addListener")
    def addListener_online(event: online, listener: js.Function0[Unit]): this.type = js.native
    
    def destroy(): Unit = js.native
    def destroy(signal: String): Unit = js.native
    
    /**
      * In a worker, this function will close all servers, wait for the `'close'` event
      * on those servers, and then disconnect the IPC channel.
      *
      * In the primary, an internal message is sent to the worker causing it to call `.disconnect()` on itself.
      *
      * Causes `.exitedAfterDisconnect` to be set.
      *
      * After a server is closed, it will no longer accept new connections,
      * but connections may be accepted by any other listening worker. Existing
      * connections will be allowed to close as usual. When no more connections exist,
      * see `server.close()`, the IPC channel to the worker will close allowing it
      * to die gracefully.
      *
      * The above applies _only_ to server connections, client connections are not
      * automatically closed by workers, and disconnect does not wait for them to close
      * before exiting.
      *
      * In a worker, `process.disconnect` exists, but it is not this function;
      * it is `disconnect()`.
      *
      * Because long living server connections may block workers from disconnecting, it
      * may be useful to send a message, so application specific actions may be taken to
      * close them. It also may be useful to implement a timeout, killing a worker if
      * the `'disconnect'` event has not been emitted after some time.
      *
      * ```js
      * if (cluster.isPrimary) {
      *   const worker = cluster.fork();
      *   let timeout;
      *
      *   worker.on('listening', (address) => {
      *     worker.send('shutdown');
      *     worker.disconnect();
      *     timeout = setTimeout(() => {
      *       worker.kill();
      *     }, 2000);
      *   });
      *
      *   worker.on('disconnect', () => {
      *     clearTimeout(timeout);
      *   });
      *
      * } else if (cluster.isWorker) {
      *   const net = require('node:net');
      *   const server = net.createServer((socket) => {
      *     // Connections never end
      *   });
      *
      *   server.listen(8000);
      *
      *   process.on('message', (msg) => {
      *     if (msg === 'shutdown') {
      *       // Initiate graceful close of any connections to server
      *     }
      *   });
      * }
      * ```
      * @since v0.7.7
      * @return A reference to `worker`.
      */
    def disconnect(): Unit = js.native
    
    def emit(event: String, args: Any*): Boolean = js.native
    def emit(event: js.Symbol, args: Any*): Boolean = js.native
    @JSName("emit")
    def emit_disconnect(event: disconnect): Boolean = js.native
    @JSName("emit")
    def emit_error(event: error, error: js.Error): Boolean = js.native
    @JSName("emit")
    def emit_exit(event: exit, code: Double, signal: String): Boolean = js.native
    @JSName("emit")
    def emit_listening(event: listening, address: Address): Boolean = js.native
    @JSName("emit")
    def emit_message(event: message, message: Any, handle: Server): Boolean = js.native
    @JSName("emit")
    def emit_message(event: message, message: Any, handle: Socket): Boolean = js.native
    @JSName("emit")
    def emit_online(event: online): Boolean = js.native
    
    /**
      * This property is `true` if the worker exited due to `.disconnect()`.
      * If the worker exited any other way, it is `false`. If the
      * worker has not exited, it is `undefined`.
      *
      * The boolean `worker.exitedAfterDisconnect` allows distinguishing between
      * voluntary and accidental exit, the primary may choose not to respawn a worker
      * based on this value.
      *
      * ```js
      * cluster.on('exit', (worker, code, signal) => {
      *   if (worker.exitedAfterDisconnect === true) {
      *     console.log('Oh, it was just voluntary – no need to worry');
      *   }
      * });
      *
      * // kill worker
      * worker.kill();
      * ```
      * @since v6.0.0
      */
    var exitedAfterDisconnect: Boolean = js.native
    
    /**
      * Each new worker is given its own unique id, this id is stored in the `id`.
      *
      * While a worker is alive, this is the key that indexes it in `cluster.workers`.
      * @since v0.8.0
      */
    var id: Double = js.native
    
    /**
      * This function returns `true` if the worker is connected to its primary via its
      * IPC channel, `false` otherwise. A worker is connected to its primary after it
      * has been created. It is disconnected after the `'disconnect'` event is emitted.
      * @since v0.11.14
      */
    def isConnected(): Boolean = js.native
    
    /**
      * This function returns `true` if the worker's process has terminated (either
      * because of exiting or being signaled). Otherwise, it returns `false`.
      *
      * ```js
      * import cluster from 'node:cluster';
      * import http from 'node:http';
      * import { availableParallelism } from 'node:os';
      * import process from 'node:process';
      *
      * const numCPUs = availableParallelism();
      *
      * if (cluster.isPrimary) {
      *   console.log(`Primary ${process.pid} is running`);
      *
      *   // Fork workers.
      *   for (let i = 0; i < numCPUs; i++) {
      *     cluster.fork();
      *   }
      *
      *   cluster.on('fork', (worker) => {
      *     console.log('worker is dead:', worker.isDead());
      *   });
      *
      *   cluster.on('exit', (worker, code, signal) => {
      *     console.log('worker is dead:', worker.isDead());
      *   });
      * } else {
      *   // Workers can share any TCP connection. In this case, it is an HTTP server.
      *   http.createServer((req, res) => {
      *     res.writeHead(200);
      *     res.end(`Current process\n ${process.pid}`);
      *     process.kill(process.pid);
      *   }).listen(8000);
      * }
      * ```
      * @since v0.11.14
      */
    def isDead(): Boolean = js.native
    
    /**
      * This function will kill the worker. In the primary worker, it does this by
      * disconnecting the `worker.process`, and once disconnected, killing with `signal`. In the worker, it does it by killing the process with `signal`.
      *
      * The `kill()` function kills the worker process without waiting for a graceful
      * disconnect, it has the same behavior as `worker.process.kill()`.
      *
      * This method is aliased as `worker.destroy()` for backwards compatibility.
      *
      * In a worker, `process.kill()` exists, but it is not this function;
      * it is [`kill()`](https://nodejs.org/docs/latest-v22.x/api/process.html#processkillpid-signal).
      * @since v0.9.12
      * @param [signal='SIGTERM'] Name of the kill signal to send to the worker process.
      */
    def kill(): Unit = js.native
    def kill(signal: String): Unit = js.native
    
    def on(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("on")
    def on_disconnect(event: disconnect, listener: js.Function0[Unit]): this.type = js.native
    @JSName("on")
    def on_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
    @JSName("on")
    def on_exit(event: exit, listener: js.Function2[/* code */ Double, /* signal */ String, Unit]): this.type = js.native
    @JSName("on")
    def on_listening(event: listening, listener: js.Function1[/* address */ Address, Unit]): this.type = js.native
    @JSName("on")
    def on_message(event: message, listener: js.Function2[/* message */ Any, /* handle */ Socket | Server, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("on")
    def on_online(event: online, listener: js.Function0[Unit]): this.type = js.native
    
    def once(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("once")
    def once_disconnect(event: disconnect, listener: js.Function0[Unit]): this.type = js.native
    @JSName("once")
    def once_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
    @JSName("once")
    def once_exit(event: exit, listener: js.Function2[/* code */ Double, /* signal */ String, Unit]): this.type = js.native
    @JSName("once")
    def once_listening(event: listening, listener: js.Function1[/* address */ Address, Unit]): this.type = js.native
    @JSName("once")
    def once_message(event: message, listener: js.Function2[/* message */ Any, /* handle */ Socket | Server, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("once")
    def once_online(event: online, listener: js.Function0[Unit]): this.type = js.native
    
    def prependListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_disconnect(event: disconnect, listener: js.Function0[Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_exit(event: exit, listener: js.Function2[/* code */ Double, /* signal */ String, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_listening(event: listening, listener: js.Function1[/* address */ Address, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_message(event: message, listener: js.Function2[/* message */ Any, /* handle */ Socket | Server, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("prependListener")
    def prependListener_online(event: online, listener: js.Function0[Unit]): this.type = js.native
    
    def prependOnceListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_disconnect(event: disconnect, listener: js.Function0[Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_exit(event: exit, listener: js.Function2[/* code */ Double, /* signal */ String, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_listening(event: listening, listener: js.Function1[/* address */ Address, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_message(event: message, listener: js.Function2[/* message */ Any, /* handle */ Socket | Server, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("prependOnceListener")
    def prependOnceListener_online(event: online, listener: js.Function0[Unit]): this.type = js.native
    
    /**
      * All workers are created using [`child_process.fork()`](https://nodejs.org/docs/latest-v22.x/api/child_process.html#child_processforkmodulepath-args-options), the returned object
      * from this function is stored as `.process`. In a worker, the global `process` is stored.
      *
      * See: [Child Process module](https://nodejs.org/docs/latest-v22.x/api/child_process.html#child_processforkmodulepath-args-options).
      *
      * Workers will call `process.exit(0)` if the `'disconnect'` event occurs
      * on `process` and `.exitedAfterDisconnect` is not `true`. This protects against
      * accidental disconnection.
      * @since v0.7.0
      */
    var process: ChildProcess = js.native
    
    /**
      * Send a message to a worker or primary, optionally with a handle.
      *
      * In the primary, this sends a message to a specific worker. It is identical to [`ChildProcess.send()`](https://nodejs.org/docs/latest-v22.x/api/child_process.html#subprocesssendmessage-sendhandle-options-callback).
      *
      * In a worker, this sends a message to the primary. It is identical to `process.send()`.
      *
      * This example will echo back all messages from the primary:
      *
      * ```js
      * if (cluster.isPrimary) {
      *   const worker = cluster.fork();
      *   worker.send('hi there');
      *
      * } else if (cluster.isWorker) {
      *   process.on('message', (msg) => {
      *     process.send(msg);
      *   });
      * }
      * ```
      * @since v0.7.0
      * @param options The `options` argument, if present, is an object used to parameterize the sending of certain types of handles.
      */
    def send(message: Serializable): Boolean = js.native
    def send(message: Serializable, callback: js.Function1[/* error */ js.Error | Null, Unit]): Boolean = js.native
    def send(message: Serializable, sendHandle: SendHandle): Boolean = js.native
    def send(
      message: Serializable,
      sendHandle: SendHandle,
      callback: js.Function1[/* error */ js.Error | Null, Unit]
    ): Boolean = js.native
    def send(
      message: Serializable,
      sendHandle: SendHandle,
      options: Unit,
      callback: js.Function1[/* error */ js.Error | Null, Unit]
    ): Boolean = js.native
    def send(message: Serializable, sendHandle: SendHandle, options: MessageOptions): Boolean = js.native
    def send(
      message: Serializable,
      sendHandle: SendHandle,
      options: MessageOptions,
      callback: js.Function1[/* error */ js.Error | Null, Unit]
    ): Boolean = js.native
  }
  
  trait Address extends StObject {
    
    var address: String
    
    /**
      * The `addressType` is one of:
      *
      * * `4` (TCPv4)
      * * `6` (TCPv6)
      * * `-1` (Unix domain socket)
      * * `'udp4'` or `'udp6'` (UDPv4 or UDPv6)
      */
    var addressType: `4` | `6` | `-1` | udp4 | udp6
    
    var port: Double
  }
  object Address {
    
    inline def apply(address: String, addressType: `4` | `6` | `-1` | udp4 | udp6, port: Double): Address = {
      val __obj = js.Dynamic.literal(address = address.asInstanceOf[js.Any], addressType = addressType.asInstanceOf[js.Any], port = port.asInstanceOf[js.Any])
      __obj.asInstanceOf[Address]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: Address] (val x: Self) extends AnyVal {
      
      inline def setAddress(value: String): Self = StObject.set(x, "address", value.asInstanceOf[js.Any])
      
      inline def setAddressType(value: `4` | `6` | `-1` | udp4 | udp6): Self = StObject.set(x, "addressType", value.asInstanceOf[js.Any])
      
      inline def setPort(value: Double): Self = StObject.set(x, "port", value.asInstanceOf[js.Any])
    }
  }
  
  @js.native
  trait Cluster extends StObject {
    
    val SCHED_NONE: Double = js.native
    
    val SCHED_RR: Double = js.native
    
    /**
      * events.EventEmitter
      *   1. disconnect
      *   2. exit
      *   3. fork
      *   4. listening
      *   5. message
      *   6. online
      *   7. setup
      */
    def addListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_disconnect(event: disconnect, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_exit(
      event: exit,
      listener: js.Function3[/* worker */ Worker, /* code */ Double, /* signal */ String, Unit]
    ): this.type = js.native
    @JSName("addListener")
    def addListener_fork(event: fork, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_listening(event: listening, listener: js.Function2[/* worker */ Worker, /* address */ Address, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_message(
      event: message,
      listener: js.Function3[/* worker */ Worker, /* message */ Any, /* handle */ Socket | Server, Unit]
    ): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("addListener")
    def addListener_online(event: online, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("addListener")
    def addListener_setup(event: setup, listener: js.Function1[/* settings */ ClusterSettings, Unit]): this.type = js.native
    
    def disconnect(): Unit = js.native
    def disconnect(callback: js.Function0[Unit]): Unit = js.native
    
    def emit(event: String, args: Any*): Boolean = js.native
    def emit(event: js.Symbol, args: Any*): Boolean = js.native
    @JSName("emit")
    def emit_disconnect(event: disconnect, worker: Worker): Boolean = js.native
    @JSName("emit")
    def emit_exit(event: exit, worker: Worker, code: Double, signal: String): Boolean = js.native
    @JSName("emit")
    def emit_fork(event: fork, worker: Worker): Boolean = js.native
    @JSName("emit")
    def emit_listening(event: listening, worker: Worker, address: Address): Boolean = js.native
    @JSName("emit")
    def emit_message(event: message, worker: Worker, message: Any, handle: Server): Boolean = js.native
    @JSName("emit")
    def emit_message(event: message, worker: Worker, message: Any, handle: Socket): Boolean = js.native
    @JSName("emit")
    def emit_online(event: online, worker: Worker): Boolean = js.native
    @JSName("emit")
    def emit_setup(event: setup, settings: ClusterSettings): Boolean = js.native
    
    /**
      * Spawn a new worker process.
      *
      * This can only be called from the primary process.
      * @param env Key/value pairs to add to worker process environment.
      * @since v0.6.0
      */
    def fork(): Worker = js.native
    def fork(env: Any): Worker = js.native
    
    /** @deprecated since v16.0.0 - use isPrimary. */
    val isMaster: Boolean = js.native
    
    /**
      * True if the process is a primary. This is determined by the `process.env.NODE_UNIQUE_ID`. If `process.env.NODE_UNIQUE_ID`
      * is undefined, then `isPrimary` is `true`.
      * @since v16.0.0
      */
    val isPrimary: Boolean = js.native
    
    /**
      * True if the process is not a primary (it is the negation of `cluster.isPrimary`).
      * @since v0.6.0
      */
    val isWorker: Boolean = js.native
    
    def on(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("on")
    def on_disconnect(event: disconnect, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("on")
    def on_exit(
      event: exit,
      listener: js.Function3[/* worker */ Worker, /* code */ Double, /* signal */ String, Unit]
    ): this.type = js.native
    @JSName("on")
    def on_fork(event: fork, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("on")
    def on_listening(event: listening, listener: js.Function2[/* worker */ Worker, /* address */ Address, Unit]): this.type = js.native
    @JSName("on")
    def on_message(
      event: message,
      listener: js.Function3[/* worker */ Worker, /* message */ Any, /* handle */ Socket | Server, Unit]
    ): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("on")
    def on_online(event: online, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("on")
    def on_setup(event: setup, listener: js.Function1[/* settings */ ClusterSettings, Unit]): this.type = js.native
    
    def once(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("once")
    def once_disconnect(event: disconnect, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("once")
    def once_exit(
      event: exit,
      listener: js.Function3[/* worker */ Worker, /* code */ Double, /* signal */ String, Unit]
    ): this.type = js.native
    @JSName("once")
    def once_fork(event: fork, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("once")
    def once_listening(event: listening, listener: js.Function2[/* worker */ Worker, /* address */ Address, Unit]): this.type = js.native
    @JSName("once")
    def once_message(
      event: message,
      listener: js.Function3[/* worker */ Worker, /* message */ Any, /* handle */ Socket | Server, Unit]
    ): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("once")
    def once_online(event: online, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("once")
    def once_setup(event: setup, listener: js.Function1[/* settings */ ClusterSettings, Unit]): this.type = js.native
    
    def prependListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_disconnect(event: disconnect, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_exit(
      event: exit,
      listener: js.Function3[/* worker */ Worker, /* code */ Double, /* signal */ String, Unit]
    ): this.type = js.native
    @JSName("prependListener")
    def prependListener_fork(event: fork, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_listening(event: listening, listener: js.Function2[/* worker */ Worker, /* address */ Address, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("prependListener")
    def prependListener_message(
      event: message,
      listener: js.Function3[/* worker */ Worker, /* message */ Any, /* handle */ js.UndefOr[Socket | Server], Unit]
    ): this.type = js.native
    @JSName("prependListener")
    def prependListener_online(event: online, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("prependListener")
    def prependListener_setup(event: setup, listener: js.Function1[/* settings */ ClusterSettings, Unit]): this.type = js.native
    
    def prependOnceListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_disconnect(event: disconnect, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_exit(
      event: exit,
      listener: js.Function3[/* worker */ Worker, /* code */ Double, /* signal */ String, Unit]
    ): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_fork(event: fork, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_listening(event: listening, listener: js.Function2[/* worker */ Worker, /* address */ Address, Unit]): this.type = js.native
    // the handle is a net.Socket or net.Server object, or undefined.
    @JSName("prependOnceListener")
    def prependOnceListener_message(
      event: message,
      listener: js.Function3[/* worker */ Worker, /* message */ Any, /* handle */ Socket | Server, Unit]
    ): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_online(event: online, listener: js.Function1[/* worker */ Worker, Unit]): this.type = js.native
    @JSName("prependOnceListener")
    def prependOnceListener_setup(event: setup, listener: js.Function1[/* settings */ ClusterSettings, Unit]): this.type = js.native
    
    /**
      * The scheduling policy, either `cluster.SCHED_RR` for round-robin or `cluster.SCHED_NONE` to leave it to the operating system. This is a
      * global setting and effectively frozen once either the first worker is spawned, or [`.setupPrimary()`](https://nodejs.org/docs/latest-v22.x/api/cluster.html#clustersetupprimarysettings)
      * is called, whichever comes first.
      *
      * `SCHED_RR` is the default on all operating systems except Windows. Windows will change to `SCHED_RR` once libuv is able to effectively distribute
      * IOCP handles without incurring a large performance hit.
      *
      * `cluster.schedulingPolicy` can also be set through the `NODE_CLUSTER_SCHED_POLICY` environment variable. Valid values are `'rr'` and `'none'`.
      * @since v0.11.2
      */
    var schedulingPolicy: Double = js.native
    
    /**
      * After calling [`.setupPrimary()`](https://nodejs.org/docs/latest-v22.x/api/cluster.html#clustersetupprimarysettings)
      * (or [`.fork()`](https://nodejs.org/docs/latest-v22.x/api/cluster.html#clusterforkenv)) this settings object will contain
      * the settings, including the default values.
      *
      * This object is not intended to be changed or set manually.
      * @since v0.7.1
      */
    val settings: ClusterSettings = js.native
    
    /** @deprecated since v16.0.0 - use [`.setupPrimary()`](https://nodejs.org/docs/latest-v22.x/api/cluster.html#clustersetupprimarysettings) instead. */
    def setupMaster(): Unit = js.native
    def setupMaster(settings: ClusterSettings): Unit = js.native
    
    /**
      * `setupPrimary` is used to change the default 'fork' behavior. Once called, the settings will be present in `cluster.settings`.
      *
      * Any settings changes only affect future calls to [`.fork()`](https://nodejs.org/docs/latest-v22.x/api/cluster.html#clusterforkenv)
      * and have no effect on workers that are already running.
      *
      * The only attribute of a worker that cannot be set via `.setupPrimary()` is the `env` passed to
      * [`.fork()`](https://nodejs.org/docs/latest-v22.x/api/cluster.html#clusterforkenv).
      *
      * The defaults above apply to the first call only; the defaults for later calls are the current values at the time of
      * `cluster.setupPrimary()` is called.
      *
      * ```js
      * import cluster from 'node:cluster';
      *
      * cluster.setupPrimary({
      *   exec: 'worker.js',
      *   args: ['--use', 'https'],
      *   silent: true,
      * });
      * cluster.fork(); // https worker
      * cluster.setupPrimary({
      *   exec: 'worker.js',
      *   args: ['--use', 'http'],
      * });
      * cluster.fork(); // http worker
      * ```
      *
      * This can only be called from the primary process.
      * @since v16.0.0
      */
    def setupPrimary(): Unit = js.native
    def setupPrimary(settings: ClusterSettings): Unit = js.native
    
    /**
      * A reference to the current worker object. Not available in the primary process.
      *
      * ```js
      * import cluster from 'node:cluster';
      *
      * if (cluster.isPrimary) {
      *   console.log('I am primary');
      *   cluster.fork();
      *   cluster.fork();
      * } else if (cluster.isWorker) {
      *   console.log(`I am worker #${cluster.worker.id}`);
      * }
      * ```
      * @since v0.7.0
      */
    val worker: js.UndefOr[Worker] = js.native
    
    /**
      * A hash that stores the active worker objects, keyed by `id` field. This makes it easy to loop through all the workers. It is only available in the primary process.
      *
      * A worker is removed from `cluster.workers` after the worker has disconnected _and_ exited. The order between these two events cannot be determined in advance. However, it
      * is guaranteed that the removal from the `cluster.workers` list happens before the last `'disconnect'` or `'exit'` event is emitted.
      *
      * ```js
      * import cluster from 'node:cluster';
      *
      * for (const worker of Object.values(cluster.workers)) {
      *   worker.send('big announcement to all workers');
      * }
      * ```
      * @since v0.7.0
      */
    val workers: js.UndefOr[Dict[Worker]] = js.native
  }
  
  trait ClusterSettings extends StObject {
    
    /**
      * String arguments passed to worker.
      * @default process.argv.slice(2)
      */
    var args: js.UndefOr[js.Array[String]] = js.undefined
    
    /**
      * Current working directory of the worker process.
      * @default undefined (inherits from parent process)
      */
    var cwd: js.UndefOr[String] = js.undefined
    
    /**
      * File path to worker file.
      * @default process.argv[1]
      */
    var exec: js.UndefOr[String] = js.undefined
    
    /**
      * List of string arguments passed to the Node.js executable.
      * @default process.execArgv
      */
    var execArgv: js.UndefOr[js.Array[String]] = js.undefined
    
    /**
      * Sets the group identity of the process. (See [`setgid(2)`](https://man7.org/linux/man-pages/man2/setgid.2.html).)
      */
    var gid: js.UndefOr[Double] = js.undefined
    
    /**
      * Sets inspector port of worker. This can be a number, or a function that takes no arguments and returns a number.
      * By default each worker gets its own port, incremented from the primary's `process.debugPort`.
      */
    var inspectPort: js.UndefOr[Double | js.Function0[Double]] = js.undefined
    
    /**
      * Specify the kind of serialization used for sending messages between processes. Possible values are `'json'` and `'advanced'`.
      * See [Advanced serialization for `child_process`](https://nodejs.org/docs/latest-v22.x/api/child_process.html#advanced-serialization) for more details.
      * @default false
      */
    var serialization: js.UndefOr[SerializationType] = js.undefined
    
    /**
      * Whether or not to send output to parent's stdio.
      * @default false
      */
    var silent: js.UndefOr[Boolean] = js.undefined
    
    /**
      * Configures the stdio of forked processes. Because the cluster module relies on IPC to function, this configuration must
      * contain an `'ipc'` entry. When this option is provided, it overrides `silent`. See [`child_prcess.spawn()`](https://nodejs.org/docs/latest-v22.x/api/child_process.html#child_processspawncommand-args-options)'s
      * [`stdio`](https://nodejs.org/docs/latest-v22.x/api/child_process.html#optionsstdio).
      */
    var stdio: js.UndefOr[js.Array[Any]] = js.undefined
    
    /**
      * Sets the user identity of the process. (See [`setuid(2)`](https://man7.org/linux/man-pages/man2/setuid.2.html).)
      */
    var uid: js.UndefOr[Double] = js.undefined
    
    /**
      * Hide the forked processes console window that would normally be created on Windows systems.
      * @default false
      */
    var windowsHide: js.UndefOr[Boolean] = js.undefined
  }
  object ClusterSettings {
    
    inline def apply(): ClusterSettings = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[ClusterSettings]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: ClusterSettings] (val x: Self) extends AnyVal {
      
      inline def setArgs(value: js.Array[String]): Self = StObject.set(x, "args", value.asInstanceOf[js.Any])
      
      inline def setArgsUndefined: Self = StObject.set(x, "args", js.undefined)
      
      inline def setArgsVarargs(value: String*): Self = StObject.set(x, "args", js.Array(value*))
      
      inline def setCwd(value: String): Self = StObject.set(x, "cwd", value.asInstanceOf[js.Any])
      
      inline def setCwdUndefined: Self = StObject.set(x, "cwd", js.undefined)
      
      inline def setExec(value: String): Self = StObject.set(x, "exec", value.asInstanceOf[js.Any])
      
      inline def setExecArgv(value: js.Array[String]): Self = StObject.set(x, "execArgv", value.asInstanceOf[js.Any])
      
      inline def setExecArgvUndefined: Self = StObject.set(x, "execArgv", js.undefined)
      
      inline def setExecArgvVarargs(value: String*): Self = StObject.set(x, "execArgv", js.Array(value*))
      
      inline def setExecUndefined: Self = StObject.set(x, "exec", js.undefined)
      
      inline def setGid(value: Double): Self = StObject.set(x, "gid", value.asInstanceOf[js.Any])
      
      inline def setGidUndefined: Self = StObject.set(x, "gid", js.undefined)
      
      inline def setInspectPort(value: Double | js.Function0[Double]): Self = StObject.set(x, "inspectPort", value.asInstanceOf[js.Any])
      
      inline def setInspectPortFunction0(value: () => Double): Self = StObject.set(x, "inspectPort", js.Any.fromFunction0(value))
      
      inline def setInspectPortUndefined: Self = StObject.set(x, "inspectPort", js.undefined)
      
      inline def setSerialization(value: SerializationType): Self = StObject.set(x, "serialization", value.asInstanceOf[js.Any])
      
      inline def setSerializationUndefined: Self = StObject.set(x, "serialization", js.undefined)
      
      inline def setSilent(value: Boolean): Self = StObject.set(x, "silent", value.asInstanceOf[js.Any])
      
      inline def setSilentUndefined: Self = StObject.set(x, "silent", js.undefined)
      
      inline def setStdio(value: js.Array[Any]): Self = StObject.set(x, "stdio", value.asInstanceOf[js.Any])
      
      inline def setStdioUndefined: Self = StObject.set(x, "stdio", js.undefined)
      
      inline def setStdioVarargs(value: Any*): Self = StObject.set(x, "stdio", js.Array(value*))
      
      inline def setUid(value: Double): Self = StObject.set(x, "uid", value.asInstanceOf[js.Any])
      
      inline def setUidUndefined: Self = StObject.set(x, "uid", js.undefined)
      
      inline def setWindowsHide(value: Boolean): Self = StObject.set(x, "windowsHide", value.asInstanceOf[js.Any])
      
      inline def setWindowsHideUndefined: Self = StObject.set(x, "windowsHide", js.undefined)
    }
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.node.nodeStrings.json
    - typings.node.nodeStrings.advanced
  */
  trait SerializationType extends StObject
  object SerializationType {
    
    inline def advanced: typings.node.nodeStrings.advanced = "advanced".asInstanceOf[typings.node.nodeStrings.advanced]
    
    inline def json: typings.node.nodeStrings.json = "json".asInstanceOf[typings.node.nodeStrings.json]
  }
  
  type _To = Cluster
  
  /* This means you don't have to write `default`, but can instead just say `clusterMod.foo` */
  override def _to: Cluster = default
}

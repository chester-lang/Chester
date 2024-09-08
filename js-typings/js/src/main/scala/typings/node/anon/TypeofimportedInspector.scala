package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import typings.node.globalsMod.global.Disposable
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedInspector extends StObject {
  
  val Console: Any = js.native
  
  val Debugger: Any = js.native
  
  val HeapProfiler: Any = js.native
  
  val NodeRuntime: Any = js.native
  
  val NodeTracing: Any = js.native
  
  val NodeWorker: Any = js.native
  
  val Profiler: Any = js.native
  
  val Runtime: Any = js.native
  
  val Schema: Any = js.native
  
  /**
    * The `inspector.Session` is used for dispatching messages to the V8 inspector
    * back-end and receiving message responses and notifications.
    */
  var Session: Instantiable0[typings.node.inspectorMod.Session] = js.native
  
  /**
    * Deactivate the inspector. Blocks until there are no active connections.
    */
  def close(): Unit = js.native
  
  /**
    * Activate inspector on host and port. Equivalent to `node --inspect=[[host:]port]`, but can be done programmatically after node has
    * started.
    *
    * If wait is `true`, will block until a client has connected to the inspect port
    * and flow control has been passed to the debugger client.
    *
    * See the `security warning` regarding the `host` parameter usage.
    * @param [port='what was specified on the CLI'] Port to listen on for inspector connections. Optional.
    * @param [host='what was specified on the CLI'] Host to listen on for inspector connections. Optional.
    * @param [wait=false] Block until a client has connected. Optional.
    * @returns Disposable that calls `inspector.close()`.
    */
  def open(): Disposable = js.native
  def open(port: Double): Disposable = js.native
  def open(port: Double, host: String): Disposable = js.native
  def open(port: Double, host: String, wait: Boolean): Disposable = js.native
  def open(port: Double, host: Unit, wait: Boolean): Disposable = js.native
  def open(port: Unit, host: String): Disposable = js.native
  def open(port: Unit, host: String, wait: Boolean): Disposable = js.native
  def open(port: Unit, host: Unit, wait: Boolean): Disposable = js.native
  
  /**
    * Return the URL of the active inspector, or `undefined` if there is none.
    *
    * ```console
    * $ node --inspect -p 'inspector.url()'
    * Debugger listening on ws://127.0.0.1:9229/166e272e-7a30-4d09-97ce-f1c012b43c34
    * For help, see: https://nodejs.org/en/docs/inspector
    * ws://127.0.0.1:9229/166e272e-7a30-4d09-97ce-f1c012b43c34
    *
    * $ node --inspect=localhost:3000 -p 'inspector.url()'
    * Debugger listening on ws://localhost:3000/51cf8d0e-3c36-4c59-8efd-54519839e56a
    * For help, see: https://nodejs.org/en/docs/inspector
    * ws://localhost:3000/51cf8d0e-3c36-4c59-8efd-54519839e56a
    *
    * $ node -p 'inspector.url()'
    * undefined
    * ```
    */
  def url(): js.UndefOr[String] = js.native
  
  /**
    * Blocks until a client (existing or connected later) has sent `Runtime.runIfWaitingForDebugger` command.
    *
    * An exception will be thrown if there is no active inspector.
    * @since v12.7.0
    */
  def waitForDebugger(): Unit = js.native
}

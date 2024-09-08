package typings.node.fsMod

import typings.node.bufferMod.global.Buffer
import typings.node.nodeStrings.change
import typings.node.nodeStrings.error
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait FSWatcher extends StObject {
  
  /**
    * events.EventEmitter
    *   1. change
    *   2. close
    *   3. error
    */
  def addListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_change(
    event: change,
    listener: js.Function2[/* eventType */ String, /* filename */ String | Buffer, Unit]
  ): this.type = js.native
  @JSName("addListener")
  def addListener_close(event: typings.node.nodeStrings.close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("addListener")
  def addListener_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
  
  /**
    * Stop watching for changes on the given `fs.FSWatcher`. Once stopped, the `fs.FSWatcher` object is no longer usable.
    * @since v0.5.8
    */
  def close(): Unit = js.native
  
  def on(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("on")
  def on_change(
    event: change,
    listener: js.Function2[/* eventType */ String, /* filename */ String | Buffer, Unit]
  ): this.type = js.native
  @JSName("on")
  def on_close(event: typings.node.nodeStrings.close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("on")
  def on_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
  
  def once(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("once")
  def once_change(
    event: change,
    listener: js.Function2[/* eventType */ String, /* filename */ String | Buffer, Unit]
  ): this.type = js.native
  @JSName("once")
  def once_close(event: typings.node.nodeStrings.close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("once")
  def once_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
  
  def prependListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_change(
    event: change,
    listener: js.Function2[/* eventType */ String, /* filename */ String | Buffer, Unit]
  ): this.type = js.native
  @JSName("prependListener")
  def prependListener_close(event: typings.node.nodeStrings.close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependListener")
  def prependListener_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
  
  def prependOnceListener(event: String, listener: js.Function1[/* repeated */ Any, Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_change(
    event: change,
    listener: js.Function2[/* eventType */ String, /* filename */ String | Buffer, Unit]
  ): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_close(event: typings.node.nodeStrings.close, listener: js.Function0[Unit]): this.type = js.native
  @JSName("prependOnceListener")
  def prependOnceListener_error(event: error, listener: js.Function1[/* error */ js.Error, Unit]): this.type = js.native
  
  /**
    * When called, requests that the Node.js event loop _not_ exit so long as the `fs.FSWatcher` is active. Calling `watcher.ref()` multiple times will have
    * no effect.
    *
    * By default, all `fs.FSWatcher` objects are "ref'ed", making it normally
    * unnecessary to call `watcher.ref()` unless `watcher.unref()` had been
    * called previously.
    * @since v14.3.0, v12.20.0
    */
  def ref(): this.type = js.native
  
  /**
    * When called, the active `fs.FSWatcher` object will not require the Node.js
    * event loop to remain active. If there is no other activity keeping the
    * event loop running, the process may exit before the `fs.FSWatcher` object's
    * callback is invoked. Calling `watcher.unref()` multiple times will have
    * no effect.
    * @since v14.3.0, v12.20.0
    */
  def unref(): this.type = js.native
}

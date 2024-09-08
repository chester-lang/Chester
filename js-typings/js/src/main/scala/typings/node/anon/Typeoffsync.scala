package typings.node.anon

import typings.node.fsMod.NoParamCallback
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoffsync extends StObject {
  
  def apply(fd: Double, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronous fsync(2) - synchronize a file's in-core state with the underlying storage device.
    * @param fd A file descriptor.
    */
  def __promisify__(fd: Double): js.Promise[Unit] = js.native
}

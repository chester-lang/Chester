package typings.node.anon

import typings.node.fsMod.NoParamCallback
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofftruncate extends StObject {
  
  def apply(fd: Double, len: Double, callback: NoParamCallback): Unit = js.native
  def apply(fd: Double, len: Null, callback: NoParamCallback): Unit = js.native
  def apply(fd: Double, len: Unit, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronous ftruncate(2) - Truncate a file to a specified length.
    * @param fd A file descriptor.
    * @param len If not specified, defaults to `0`.
    */
  def __promisify__(fd: Double): js.Promise[Unit] = js.native
  def __promisify__(fd: Double, len: Double): js.Promise[Unit] = js.native
}

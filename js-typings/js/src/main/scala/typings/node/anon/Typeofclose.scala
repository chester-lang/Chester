package typings.node.anon

import typings.node.fsMod.NoParamCallback
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofclose extends StObject {
  
  def apply(fd: Double): Unit = js.native
  def apply(fd: Double, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronous close(2) - close a file descriptor.
    * @param fd A file descriptor.
    */
  def __promisify__(fd: Double): js.Promise[Unit] = js.native
}

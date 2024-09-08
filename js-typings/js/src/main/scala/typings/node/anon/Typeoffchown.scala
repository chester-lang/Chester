package typings.node.anon

import typings.node.fsMod.NoParamCallback
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoffchown extends StObject {
  
  def apply(fd: Double, uid: Double, gid: Double, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronous fchown(2) - Change ownership of a file.
    * @param fd A file descriptor.
    */
  def __promisify__(fd: Double, uid: Double, gid: Double): js.Promise[Unit] = js.native
}

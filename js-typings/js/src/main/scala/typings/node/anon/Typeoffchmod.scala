package typings.node.anon

import typings.node.fsMod.NoParamCallback
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoffchmod extends StObject {
  
  def apply(fd: Double, mode: typings.node.fsMod.Mode, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronous fchmod(2) - Change permissions of a file.
    * @param fd A file descriptor.
    * @param mode A file mode. If a string is passed, it is parsed as an octal integer.
    */
  def __promisify__(fd: Double, mode: typings.node.fsMod.Mode): js.Promise[Unit] = js.native
}

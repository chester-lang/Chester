package typings.node.anon

import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.TimeLike
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoffutimes extends StObject {
  
  def apply(fd: Double, atime: TimeLike, mtime: TimeLike, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronously change file timestamps of the file referenced by the supplied file descriptor.
    * @param fd A file descriptor.
    * @param atime The last access time. If a string is provided, it will be coerced to number.
    * @param mtime The last modified time. If a string is provided, it will be coerced to number.
    */
  def __promisify__(fd: Double, atime: TimeLike, mtime: TimeLike): js.Promise[Unit] = js.native
}

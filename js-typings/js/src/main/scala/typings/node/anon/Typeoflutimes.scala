package typings.node.anon

import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.PathLike
import typings.node.fsMod.TimeLike
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoflutimes extends StObject {
  
  def apply(path: PathLike, atime: TimeLike, mtime: TimeLike, callback: NoParamCallback): Unit = js.native
  
  /**
    * Changes the access and modification times of a file in the same way as `fsPromises.utimes()`,
    * with the difference that if the path refers to a symbolic link, then the link is not
    * dereferenced: instead, the timestamps of the symbolic link itself are changed.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param atime The last access time. If a string is provided, it will be coerced to number.
    * @param mtime The last modified time. If a string is provided, it will be coerced to number.
    */
  def __promisify__(path: PathLike, atime: TimeLike, mtime: TimeLike): js.Promise[Unit] = js.native
}

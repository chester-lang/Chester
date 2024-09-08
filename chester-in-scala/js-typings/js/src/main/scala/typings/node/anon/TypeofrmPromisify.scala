package typings.node.anon

import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.PathLike
import typings.node.fsMod.RmOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofrmPromisify extends StObject {
  
  def apply(path: PathLike, callback: NoParamCallback): Unit = js.native
  def apply(path: PathLike, options: RmOptions, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronously removes files and directories (modeled on the standard POSIX `rm` utility).
    */
  def __promisify__(path: PathLike): js.Promise[Unit] = js.native
  def __promisify__(path: PathLike, options: RmOptions): js.Promise[Unit] = js.native
}

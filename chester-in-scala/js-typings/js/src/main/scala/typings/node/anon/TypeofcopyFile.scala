package typings.node.anon

import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.PathLike
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofcopyFile extends StObject {
  
  def apply(src: PathLike, dest: PathLike, callback: NoParamCallback): Unit = js.native
  
  def __promisify__(src: PathLike, dst: PathLike): js.Promise[Unit] = js.native
  def __promisify__(src: PathLike, dst: PathLike, mode: Double): js.Promise[Unit] = js.native
}

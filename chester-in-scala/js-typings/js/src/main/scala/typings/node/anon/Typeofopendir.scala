package typings.node.anon

import typings.node.fsMod.Dir
import typings.node.fsMod.OpenDirOptions
import typings.node.fsMod.PathLike
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofopendir extends StObject {
  
  def apply(path: PathLike, cb: js.Function2[/* err */ ErrnoException | Null, /* dir */ Dir, Unit]): Unit = js.native
  
  def __promisify__(path: PathLike): js.Promise[Dir] = js.native
  def __promisify__(path: PathLike, options: OpenDirOptions): js.Promise[Dir] = js.native
}

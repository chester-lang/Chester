package typings.node.anon

import typings.node.fsMod.BigIntStats
import typings.node.fsMod.PathLike
import typings.node.fsMod.StatOptions
import typings.node.fsMod.Stats
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofstat extends StObject {
  
  def apply(path: PathLike, callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]): Unit = js.native
  
  /**
    * Asynchronous stat(2) - Get file status.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    */
  def __promisify__(path: PathLike): js.Promise[Stats] = js.native
  def __promisify__(path: PathLike, options: StatOptionsbigintfalseund): js.Promise[Stats] = js.native
  def __promisify__(path: PathLike, options: StatOptionsbiginttrue): js.Promise[BigIntStats] = js.native
  def __promisify__(path: PathLike, options: StatOptions): js.Promise[Stats | BigIntStats] = js.native
}

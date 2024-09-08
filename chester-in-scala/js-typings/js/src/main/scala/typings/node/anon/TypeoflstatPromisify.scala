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
trait TypeoflstatPromisify extends StObject {
  
  def apply(path: PathLike, callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]): Unit = js.native
  def apply(
    path: PathLike,
    options: Unit,
    callback: js.Function2[ErrnoException | Null, BigIntStats | (/* stats */ Stats), Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: StatOptionsbigintfalseund,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: StatOptionsbiginttrue,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ BigIntStats, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: StatOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats | BigIntStats, Unit]
  ): Unit = js.native
  
  /**
    * Asynchronous lstat(2) - Get file status. Does not dereference symbolic links.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    */
  def __promisify__(path: PathLike): js.Promise[Stats] = js.native
  def __promisify__(path: PathLike, options: StatOptionsbigintfalseund): js.Promise[Stats] = js.native
  def __promisify__(path: PathLike, options: StatOptionsbiginttrue): js.Promise[BigIntStats] = js.native
  def __promisify__(path: PathLike, options: StatOptions): js.Promise[Stats | BigIntStats] = js.native
}

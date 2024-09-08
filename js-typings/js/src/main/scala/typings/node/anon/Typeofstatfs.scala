package typings.node.anon

import typings.node.fsMod.BigIntStatsFs
import typings.node.fsMod.PathLike
import typings.node.fsMod.StatFsOptions
import typings.node.fsMod.StatsFs
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofstatfs extends StObject {
  
  def apply(path: PathLike, callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ StatsFs, Unit]): Unit = js.native
  
  /**
    * Asynchronous statfs(2) - Returns information about the mounted file system which contains path. The callback gets two arguments (err, stats) where stats is an <fs.StatFs> object.
    * @param path A path to an existing file or directory on the file system to be queried.
    */
  def __promisify__(path: PathLike): js.Promise[StatsFs] = js.native
  def __promisify__(path: PathLike, options: StatFsOptionsbigintfalseu): js.Promise[StatsFs] = js.native
  def __promisify__(path: PathLike, options: StatFsOptionsbiginttrue): js.Promise[BigIntStatsFs] = js.native
  def __promisify__(path: PathLike, options: StatFsOptions): js.Promise[StatsFs | BigIntStatsFs] = js.native
}

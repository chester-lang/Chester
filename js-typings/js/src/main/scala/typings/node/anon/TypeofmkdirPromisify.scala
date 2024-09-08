package typings.node.anon

import typings.node.fsMod.MakeDirectoryOptions
import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.PathLike
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofmkdirPromisify extends StObject {
  
  def apply(path: PathLike, callback: NoParamCallback): Unit = js.native
  def apply(
    path: PathLike,
    options: Null,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def apply(path: PathLike, options: Null, callback: NoParamCallback): Unit = js.native
  def apply(
    path: PathLike,
    options: Unit,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def apply(path: PathLike, options: Unit, callback: NoParamCallback): Unit = js.native
  def apply(path: PathLike, options: MakeDirectoryOptionsrecurMode, callback: NoParamCallback): Unit = js.native
  def apply(
    path: PathLike,
    options: MakeDirectoryOptionsrecur,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: MakeDirectoryOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    options: typings.node.fsMod.Mode,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def apply(path: PathLike, options: typings.node.fsMod.Mode, callback: NoParamCallback): Unit = js.native
  
  /**
    * Asynchronous mkdir(2) - create a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options Either the file mode, or an object optionally specifying the file mode and whether parent folders
    * should be created. If a string is passed, it is parsed as an octal integer. If not specified, defaults to `0o777`.
    */
  def __promisify__(path: PathLike): js.Promise[Unit] = js.native
  /**
    * Asynchronous mkdir(2) - create a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options Either the file mode, or an object optionally specifying the file mode and whether parent folders
    * should be created. If a string is passed, it is parsed as an octal integer. If not specified, defaults to `0o777`.
    */
  def __promisify__(path: PathLike, options: MakeDirectoryOptionsrecur): js.Promise[js.UndefOr[String]] = js.native
  def __promisify__(path: PathLike, options: MakeDirectoryOptionsrecurMode): js.Promise[Unit] = js.native
  def __promisify__(path: PathLike, options: MakeDirectoryOptions): js.Promise[js.UndefOr[String]] = js.native
  def __promisify__(path: PathLike, options: typings.node.fsMod.Mode): js.Promise[Unit] = js.native
}

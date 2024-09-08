package typings.node.anon

import typings.node.fsMod.OpenMode
import typings.node.fsMod.PathLike
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofopenPromisify extends StObject {
  
  def apply(path: PathLike, callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]): Unit = js.native
  def apply(
    path: PathLike,
    flags: Unit,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: Unit,
    mode: Null,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: Unit,
    mode: Unit,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: Unit,
    mode: typings.node.fsMod.Mode,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: OpenMode,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: OpenMode,
    mode: Null,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: OpenMode,
    mode: Unit,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def apply(
    path: PathLike,
    flags: OpenMode,
    mode: typings.node.fsMod.Mode,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  
  /**
    * Asynchronous open(2) - open and possibly create a file.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param mode A file mode. If a string is passed, it is parsed as an octal integer. If not supplied, defaults to `0o666`.
    */
  def __promisify__(path: PathLike, flags: OpenMode): js.Promise[Double] = js.native
  def __promisify__(path: PathLike, flags: OpenMode, mode: typings.node.fsMod.Mode): js.Promise[Double] = js.native
}

package typings.node.anon

import typings.node.fsMod.BigIntStats
import typings.node.fsMod.StatOptions
import typings.node.fsMod.Stats
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeoffstatPromisify extends StObject {
  
  def apply(fd: Double, callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]): Unit = js.native
  def apply(
    fd: Double,
    options: Unit,
    callback: js.Function2[ErrnoException | Null, BigIntStats | (/* stats */ Stats), Unit]
  ): Unit = js.native
  def apply(
    fd: Double,
    options: StatOptionsbigintfalseund,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]
  ): Unit = js.native
  def apply(
    fd: Double,
    options: StatOptionsbiginttrue,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ BigIntStats, Unit]
  ): Unit = js.native
  def apply(
    fd: Double,
    options: StatOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats | BigIntStats, Unit]
  ): Unit = js.native
  
  /**
    * Asynchronous fstat(2) - Get file status.
    * @param fd A file descriptor.
    */
  def __promisify__(fd: Double): js.Promise[Stats] = js.native
  def __promisify__(fd: Double, options: StatOptionsbigintfalseund): js.Promise[Stats] = js.native
  def __promisify__(fd: Double, options: StatOptionsbiginttrue): js.Promise[BigIntStats] = js.native
  def __promisify__(fd: Double, options: StatOptions): js.Promise[Stats | BigIntStats] = js.native
}

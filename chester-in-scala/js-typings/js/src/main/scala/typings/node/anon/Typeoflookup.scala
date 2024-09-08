package typings.node.anon

import typings.node.dnsMod.LookupAddress
import typings.node.dnsMod.LookupAllOptions
import typings.node.dnsMod.LookupOneOptions
import typings.node.dnsMod.LookupOptions
import typings.node.globalsMod.global.NodeJS.ErrnoException
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeoflookup extends StObject {
  
  def apply(
    hostname: String,
    family: Double,
    callback: js.Function3[/* err */ ErrnoException | Null, /* address */ String, /* family */ Double, Unit]
  ): Unit = js.native
  
  def __promisify__(hostname: String): js.Promise[LookupAddress] = js.native
  def __promisify__(hostname: String, options: Double): js.Promise[LookupAddress] = js.native
  def __promisify__(hostname: String, options: LookupAllOptions): js.Promise[js.Array[LookupAddress]] = js.native
  def __promisify__(hostname: String, options: LookupOneOptions): js.Promise[LookupAddress] = js.native
  def __promisify__(hostname: String, options: LookupOptions): js.Promise[LookupAddress | js.Array[LookupAddress]] = js.native
}

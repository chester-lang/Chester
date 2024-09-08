package typings.node.anon

import typings.node.dnsMod.AnyRecord
import typings.node.dnsMod.MxRecord
import typings.node.dnsMod.NaptrRecord
import typings.node.dnsMod.SoaRecord
import typings.node.dnsMod.SrvRecord
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.nodeStrings.A
import typings.node.nodeStrings.AAAA
import typings.node.nodeStrings.ANY
import typings.node.nodeStrings.CNAME
import typings.node.nodeStrings.MX
import typings.node.nodeStrings.NAPTR
import typings.node.nodeStrings.NS
import typings.node.nodeStrings.PTR
import typings.node.nodeStrings.SOA
import typings.node.nodeStrings.SRV
import typings.node.nodeStrings.TXT
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofresolve extends StObject {
  
  def apply(
    hostname: String,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
  ): Unit = js.native
  
  def __promisify__(hostname: String): js.Promise[js.Array[String]] = js.native
  def __promisify__(hostname: String, rrtype: A | AAAA | CNAME | NS | PTR): js.Promise[js.Array[String]] = js.native
  def __promisify__(hostname: String, rrtype: String): js.Promise[
    (js.Array[AnyRecord | js.Array[String] | MxRecord | NaptrRecord | SrvRecord | String]) | SoaRecord
  ] = js.native
  @JSName("__promisify__")
  def __promisify___ANY(hostname: String, rrtype: ANY): js.Promise[js.Array[AnyRecord]] = js.native
  @JSName("__promisify__")
  def __promisify___MX(hostname: String, rrtype: MX): js.Promise[js.Array[MxRecord]] = js.native
  @JSName("__promisify__")
  def __promisify___NAPTR(hostname: String, rrtype: NAPTR): js.Promise[js.Array[NaptrRecord]] = js.native
  @JSName("__promisify__")
  def __promisify___SOA(hostname: String, rrtype: SOA): js.Promise[SoaRecord] = js.native
  @JSName("__promisify__")
  def __promisify___SRV(hostname: String, rrtype: SRV): js.Promise[js.Array[SrvRecord]] = js.native
  @JSName("__promisify__")
  def __promisify___TXT(hostname: String, rrtype: TXT): js.Promise[js.Array[js.Array[String]]] = js.native
}

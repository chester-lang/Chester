package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import typings.node.dnsMod.AnyRecord
import typings.node.dnsMod.LookupAddress
import typings.node.dnsMod.LookupAllOptions
import typings.node.dnsMod.LookupOneOptions
import typings.node.dnsMod.LookupOptions
import typings.node.dnsMod.MxRecord
import typings.node.dnsMod.NaptrRecord
import typings.node.dnsMod.RecordWithTtl
import typings.node.dnsMod.ResolveOptions
import typings.node.dnsMod.ResolveWithTtlOptions
import typings.node.dnsMod.ResolverOptions
import typings.node.dnsMod.SoaRecord
import typings.node.dnsMod.SrvRecord
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.nodeStrings.A
import typings.node.nodeStrings.AAAA
import typings.node.nodeStrings.ANY
import typings.node.nodeStrings.CNAME
import typings.node.nodeStrings.EADDRGETNETWORKPARAMS
import typings.node.nodeStrings.EBADFAMILY
import typings.node.nodeStrings.EBADFLAGS
import typings.node.nodeStrings.EBADHINTS
import typings.node.nodeStrings.EBADNAME
import typings.node.nodeStrings.EBADQUERY
import typings.node.nodeStrings.EBADRESP
import typings.node.nodeStrings.EBADSTR
import typings.node.nodeStrings.ECANCELLED
import typings.node.nodeStrings.ECONNREFUSED
import typings.node.nodeStrings.EDESTRUCTION
import typings.node.nodeStrings.EFILE
import typings.node.nodeStrings.EFORMERR
import typings.node.nodeStrings.ELOADIPHLPAPI
import typings.node.nodeStrings.ENODATA
import typings.node.nodeStrings.ENOMEM
import typings.node.nodeStrings.ENONAME
import typings.node.nodeStrings.ENOTFOUND
import typings.node.nodeStrings.ENOTIMP
import typings.node.nodeStrings.ENOTINITIALIZED
import typings.node.nodeStrings.EREFUSED
import typings.node.nodeStrings.ESERVFAIL
import typings.node.nodeStrings.ETIMEOUT
import typings.node.nodeStrings.MX
import typings.node.nodeStrings.NAPTR
import typings.node.nodeStrings.NS
import typings.node.nodeStrings.PTR
import typings.node.nodeStrings.SOA
import typings.node.nodeStrings.SRV
import typings.node.nodeStrings.TXT
import typings.node.nodeStrings.ipv4first
import typings.node.nodeStrings.ipv6first
import typings.node.nodeStrings.verbatim
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedDns extends StObject {
  
  val ADDRCONFIG: Double = js.native
  
  val ADDRGETNETWORKPARAMS: EADDRGETNETWORKPARAMS = js.native
  
  val ALL: Double = js.native
  
  val BADFAMILY: EBADFAMILY = js.native
  
  val BADFLAGS: EBADFLAGS = js.native
  
  val BADHINTS: EBADHINTS = js.native
  
  val BADNAME: EBADNAME = js.native
  
  val BADQUERY: EBADQUERY = js.native
  
  val BADRESP: EBADRESP = js.native
  
  val BADSTR: EBADSTR = js.native
  
  val CANCELLED: ECANCELLED = js.native
  
  val CONNREFUSED: ECONNREFUSED = js.native
  
  val DESTRUCTION: EDESTRUCTION = js.native
  
  val EOF: typings.node.nodeStrings.EOF = js.native
  
  val FILE: EFILE = js.native
  
  val FORMERR: EFORMERR = js.native
  
  val LOADIPHLPAPI: ELOADIPHLPAPI = js.native
  
  val NODATA: ENODATA = js.native
  
  val NOMEM: ENOMEM = js.native
  
  val NONAME: ENONAME = js.native
  
  val NOTFOUND: ENOTFOUND = js.native
  
  val NOTIMP: ENOTIMP = js.native
  
  val NOTINITIALIZED: ENOTINITIALIZED = js.native
  
  val REFUSED: EREFUSED = js.native
  
  var Resolver: Instantiable1[/* options */ js.UndefOr[ResolverOptions], typings.node.dnsMod.Resolver] = js.native
  
  val SERVFAIL: ESERVFAIL = js.native
  
  val TIMEOUT: ETIMEOUT = js.native
  
  val V4MAPPED: Double = js.native
  
  def getDefaultResultOrder(): ipv4first | ipv6first | verbatim = js.native
  
  def getServers(): js.Array[String] = js.native
  
  val lookup: Typeoflookup = js.native
  def lookup(
    hostname: String,
    callback: js.Function3[/* err */ ErrnoException | Null, /* address */ String, /* family */ Double, Unit]
  ): Unit = js.native
  def lookup(
    hostname: String,
    options: LookupAllOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[LookupAddress], Unit]
  ): Unit = js.native
  def lookup(
    hostname: String,
    options: LookupOneOptions,
    callback: js.Function3[/* err */ ErrnoException | Null, /* address */ String, /* family */ Double, Unit]
  ): Unit = js.native
  def lookup(
    hostname: String,
    options: LookupOptions,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* address */ String | js.Array[LookupAddress], 
      /* family */ Double, 
      Unit
    ]
  ): Unit = js.native
  
  val lookupService: TypeoflookupService = js.native
  
  val promises: Typeofpromises = js.native
  
  val resolve: Typeofresolve = js.native
  def resolve(
    hostname: String,
    rrtype: String,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* addresses */ (js.Array[AnyRecord | js.Array[String] | MxRecord | NaptrRecord | SrvRecord | String]) | SoaRecord, 
      Unit
    ]
  ): Unit = js.native
  
  val resolve4: Typeofresolve4 = js.native
  def resolve4(
    hostname: String,
    options: ResolveOptions,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* addresses */ js.Array[RecordWithTtl | String], 
      Unit
    ]
  ): Unit = js.native
  def resolve4(
    hostname: String,
    options: ResolveWithTtlOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[RecordWithTtl], Unit]
  ): Unit = js.native
  
  val resolve6: Typeofresolve6 = js.native
  def resolve6(
    hostname: String,
    options: ResolveOptions,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* addresses */ js.Array[RecordWithTtl | String], 
      Unit
    ]
  ): Unit = js.native
  def resolve6(
    hostname: String,
    options: ResolveWithTtlOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[RecordWithTtl], Unit]
  ): Unit = js.native
  
  val resolveAny: TypeofresolveAny = js.native
  
  val resolveCaa: TypeofresolveCaa = js.native
  
  val resolveCname: TypeofresolveCname = js.native
  
  val resolveMx: TypeofresolveMx = js.native
  
  val resolveNaptr: TypeofresolveNaptr = js.native
  
  val resolveNs: TypeofresolveNs = js.native
  
  val resolvePtr: TypeofresolvePtr = js.native
  
  val resolveSoa: TypeofresolveSoa = js.native
  
  val resolveSrv: TypeofresolveSrv = js.native
  
  val resolveTxt: TypeofresolveTxt = js.native
  
  @JSName("resolve")
  def resolve_A(
    hostname: String,
    rrtype: A,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_AAAA(
    hostname: String,
    rrtype: AAAA,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_ANY(
    hostname: String,
    rrtype: ANY,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[AnyRecord], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_CNAME(
    hostname: String,
    rrtype: CNAME,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_MX(
    hostname: String,
    rrtype: MX,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[MxRecord], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_NAPTR(
    hostname: String,
    rrtype: NAPTR,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[NaptrRecord], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_NS(
    hostname: String,
    rrtype: NS,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_PTR(
    hostname: String,
    rrtype: PTR,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_SOA(
    hostname: String,
    rrtype: SOA,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ SoaRecord, Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_SRV(
    hostname: String,
    rrtype: SRV,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[SrvRecord], Unit]
  ): Unit = js.native
  @JSName("resolve")
  def resolve_TXT(
    hostname: String,
    rrtype: TXT,
    callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[js.Array[String]], Unit]
  ): Unit = js.native
  
  def reverse(
    ip: String,
    callback: js.Function2[/* err */ ErrnoException | Null, /* hostnames */ js.Array[String], Unit]
  ): Unit = js.native
  
  def setDefaultResultOrder(order: ipv4first | ipv6first | verbatim): Unit = js.native
  
  def setServers(servers: js.Array[String]): Unit = js.native
}

package typings.node.dnsMod

import typings.node.dnsMod.^
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


inline def ADDRCONFIG: Double = ^.asInstanceOf[js.Dynamic].selectDynamic("ADDRCONFIG").asInstanceOf[Double]

inline def ADDRGETNETWORKPARAMS: EADDRGETNETWORKPARAMS = ^.asInstanceOf[js.Dynamic].selectDynamic("ADDRGETNETWORKPARAMS").asInstanceOf[EADDRGETNETWORKPARAMS]

inline def ALL: Double = ^.asInstanceOf[js.Dynamic].selectDynamic("ALL").asInstanceOf[Double]

inline def BADFAMILY: EBADFAMILY = ^.asInstanceOf[js.Dynamic].selectDynamic("BADFAMILY").asInstanceOf[EBADFAMILY]

inline def BADFLAGS: EBADFLAGS = ^.asInstanceOf[js.Dynamic].selectDynamic("BADFLAGS").asInstanceOf[EBADFLAGS]

inline def BADHINTS: EBADHINTS = ^.asInstanceOf[js.Dynamic].selectDynamic("BADHINTS").asInstanceOf[EBADHINTS]

inline def BADNAME: EBADNAME = ^.asInstanceOf[js.Dynamic].selectDynamic("BADNAME").asInstanceOf[EBADNAME]

inline def BADQUERY: EBADQUERY = ^.asInstanceOf[js.Dynamic].selectDynamic("BADQUERY").asInstanceOf[EBADQUERY]

inline def BADRESP: EBADRESP = ^.asInstanceOf[js.Dynamic].selectDynamic("BADRESP").asInstanceOf[EBADRESP]

inline def BADSTR: EBADSTR = ^.asInstanceOf[js.Dynamic].selectDynamic("BADSTR").asInstanceOf[EBADSTR]

inline def CANCELLED: ECANCELLED = ^.asInstanceOf[js.Dynamic].selectDynamic("CANCELLED").asInstanceOf[ECANCELLED]

inline def CONNREFUSED: ECONNREFUSED = ^.asInstanceOf[js.Dynamic].selectDynamic("CONNREFUSED").asInstanceOf[ECONNREFUSED]

inline def DESTRUCTION: EDESTRUCTION = ^.asInstanceOf[js.Dynamic].selectDynamic("DESTRUCTION").asInstanceOf[EDESTRUCTION]

inline def EOF: typings.node.nodeStrings.EOF = ^.asInstanceOf[js.Dynamic].selectDynamic("EOF").asInstanceOf[typings.node.nodeStrings.EOF]

inline def FILE: EFILE = ^.asInstanceOf[js.Dynamic].selectDynamic("FILE").asInstanceOf[EFILE]

inline def FORMERR: EFORMERR = ^.asInstanceOf[js.Dynamic].selectDynamic("FORMERR").asInstanceOf[EFORMERR]

inline def LOADIPHLPAPI: ELOADIPHLPAPI = ^.asInstanceOf[js.Dynamic].selectDynamic("LOADIPHLPAPI").asInstanceOf[ELOADIPHLPAPI]

inline def NODATA: ENODATA = ^.asInstanceOf[js.Dynamic].selectDynamic("NODATA").asInstanceOf[ENODATA]

inline def NOMEM: ENOMEM = ^.asInstanceOf[js.Dynamic].selectDynamic("NOMEM").asInstanceOf[ENOMEM]

inline def NONAME: ENONAME = ^.asInstanceOf[js.Dynamic].selectDynamic("NONAME").asInstanceOf[ENONAME]

inline def NOTFOUND: ENOTFOUND = ^.asInstanceOf[js.Dynamic].selectDynamic("NOTFOUND").asInstanceOf[ENOTFOUND]

inline def NOTIMP: ENOTIMP = ^.asInstanceOf[js.Dynamic].selectDynamic("NOTIMP").asInstanceOf[ENOTIMP]

inline def NOTINITIALIZED: ENOTINITIALIZED = ^.asInstanceOf[js.Dynamic].selectDynamic("NOTINITIALIZED").asInstanceOf[ENOTINITIALIZED]

inline def REFUSED: EREFUSED = ^.asInstanceOf[js.Dynamic].selectDynamic("REFUSED").asInstanceOf[EREFUSED]

inline def SERVFAIL: ESERVFAIL = ^.asInstanceOf[js.Dynamic].selectDynamic("SERVFAIL").asInstanceOf[ESERVFAIL]

inline def TIMEOUT: ETIMEOUT = ^.asInstanceOf[js.Dynamic].selectDynamic("TIMEOUT").asInstanceOf[ETIMEOUT]

inline def V4MAPPED: Double = ^.asInstanceOf[js.Dynamic].selectDynamic("V4MAPPED").asInstanceOf[Double]

inline def getDefaultResultOrder(): ipv4first | ipv6first | verbatim = ^.asInstanceOf[js.Dynamic].applyDynamic("getDefaultResultOrder")().asInstanceOf[ipv4first | ipv6first | verbatim]

inline def getServers(): js.Array[String] = ^.asInstanceOf[js.Dynamic].applyDynamic("getServers")().asInstanceOf[js.Array[String]]

inline def lookup(
  hostname: String,
  callback: js.Function3[/* err */ ErrnoException | Null, /* address */ String, /* family */ Double, Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("lookup")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def lookup(
  hostname: String,
  family: Double,
  callback: js.Function3[/* err */ ErrnoException | Null, /* address */ String, /* family */ Double, Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("lookup")(hostname.asInstanceOf[js.Any], family.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def lookup(
  hostname: String,
  options: LookupAllOptions,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[LookupAddress], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("lookup")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def lookup(
  hostname: String,
  options: LookupOneOptions,
  callback: js.Function3[/* err */ ErrnoException | Null, /* address */ String, /* family */ Double, Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("lookup")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def lookup(
  hostname: String,
  options: LookupOptions,
  callback: js.Function3[
  /* err */ ErrnoException | Null, 
  /* address */ String | js.Array[LookupAddress], 
  /* family */ Double, 
  Unit
]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("lookup")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def lookupService(
  address: String,
  port: Double,
  callback: js.Function3[/* err */ ErrnoException | Null, /* hostname */ String, /* service */ String, Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("lookupService")(address.asInstanceOf[js.Any], port.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolve(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: String,
  callback: js.Function2[
  /* err */ ErrnoException | Null, 
  /* addresses */ (js.Array[AnyRecord | js.Array[String] | MxRecord | NaptrRecord | SrvRecord | String]) | SoaRecord, 
  Unit
]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: AAAA,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: ANY,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[AnyRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: A,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: CNAME,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: MX,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[MxRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: NAPTR,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[NaptrRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: NS,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: PTR,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: SOA,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ SoaRecord, Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: SRV,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[SrvRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve(
  hostname: String,
  rrtype: TXT,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[js.Array[String]], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve")(hostname.asInstanceOf[js.Any], rrtype.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolve4(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve4")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve4(
  hostname: String,
  options: ResolveOptions,
  callback: js.Function2[
  /* err */ ErrnoException | Null, 
  /* addresses */ js.Array[RecordWithTtl | String], 
  Unit
]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve4")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve4(
  hostname: String,
  options: ResolveWithTtlOptions,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[RecordWithTtl], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve4")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolve6(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve6")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve6(
  hostname: String,
  options: ResolveOptions,
  callback: js.Function2[
  /* err */ ErrnoException | Null, 
  /* addresses */ js.Array[RecordWithTtl | String], 
  Unit
]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve6")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def resolve6(
  hostname: String,
  options: ResolveWithTtlOptions,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[RecordWithTtl], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolve6")(hostname.asInstanceOf[js.Any], options.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveAny(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[AnyRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveAny")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveCaa(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* records */ js.Array[CaaRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveCaa")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveCname(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveCname")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveMx(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[MxRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveMx")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveNaptr(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[NaptrRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveNaptr")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveNs(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveNs")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolvePtr(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolvePtr")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveSoa(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* address */ SoaRecord, Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveSoa")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveSrv(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[SrvRecord], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveSrv")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def resolveTxt(
  hostname: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* addresses */ js.Array[js.Array[String]], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("resolveTxt")(hostname.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def reverse(
  ip: String,
  callback: js.Function2[/* err */ ErrnoException | Null, /* hostnames */ js.Array[String], Unit]
): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("reverse")(ip.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def setDefaultResultOrder(order: ipv4first | ipv6first | verbatim): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setDefaultResultOrder")(order.asInstanceOf[js.Any]).asInstanceOf[Unit]

inline def setServers(servers: js.Array[String]): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setServers")(servers.asInstanceOf[js.Any]).asInstanceOf[Unit]

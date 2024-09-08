package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import typings.node.dnsMod.ResolverOptions
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.nodeColondnsMod.Resolver
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
import typings.node.nodeStrings.EOF
import typings.node.nodeStrings.EREFUSED
import typings.node.nodeStrings.ESERVFAIL
import typings.node.nodeStrings.ETIMEOUT
import typings.node.nodeStrings.ipv4first
import typings.node.nodeStrings.ipv6first
import typings.node.nodeStrings.verbatim
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedNodeDns extends StObject {
  
  val ADDRCONFIG: Double
  
  val ADDRGETNETWORKPARAMS: EADDRGETNETWORKPARAMS
  
  val ALL: Double
  
  val BADFAMILY: EBADFAMILY
  
  val BADFLAGS: EBADFLAGS
  
  val BADHINTS: EBADHINTS
  
  val BADNAME: EBADNAME
  
  val BADQUERY: EBADQUERY
  
  val BADRESP: EBADRESP
  
  val BADSTR: EBADSTR
  
  val CANCELLED: ECANCELLED
  
  val CONNREFUSED: ECONNREFUSED
  
  val DESTRUCTION: EDESTRUCTION
  
  val EOF: typings.node.nodeStrings.EOF
  
  val FILE: EFILE
  
  val FORMERR: EFORMERR
  
  val LOADIPHLPAPI: ELOADIPHLPAPI
  
  val NODATA: ENODATA
  
  val NOMEM: ENOMEM
  
  val NONAME: ENONAME
  
  val NOTFOUND: ENOTFOUND
  
  val NOTIMP: ENOTIMP
  
  val NOTINITIALIZED: ENOTINITIALIZED
  
  val REFUSED: EREFUSED
  
  var Resolver: Instantiable1[/* options */ js.UndefOr[ResolverOptions], typings.node.nodeColondnsMod.Resolver]
  
  val SERVFAIL: ESERVFAIL
  
  val TIMEOUT: ETIMEOUT
  
  val V4MAPPED: Double
  
  def getDefaultResultOrder(): ipv4first | ipv6first | verbatim
  
  def getServers(): js.Array[String]
  
  val lookup: TypeoflookupPromisify
  
  val lookupService: TypeoflookupService
  
  val promises: TypeofpromisesADDRGETNETWORKPARAMS
  
  val resolve: TypeofresolvePromisify
  
  val resolve4: Typeofresolve4Promisify
  
  val resolve6: Typeofresolve6Promisify
  
  val resolveAny: TypeofresolveAny
  
  val resolveCaa: TypeofresolveCaa
  
  val resolveCname: TypeofresolveCname
  
  val resolveMx: TypeofresolveMx
  
  val resolveNaptr: TypeofresolveNaptr
  
  val resolveNs: TypeofresolveNs
  
  val resolvePtr: TypeofresolvePtr
  
  val resolveSoa: TypeofresolveSoa
  
  val resolveSrv: TypeofresolveSrv
  
  val resolveTxt: TypeofresolveTxt
  
  def reverse(
    ip: String,
    callback: js.Function2[/* err */ ErrnoException | Null, /* hostnames */ js.Array[String], Unit]
  ): Unit
  
  def setDefaultResultOrder(order: ipv4first | ipv6first | verbatim): Unit
  
  def setServers(servers: js.Array[String]): Unit
}
object TypeofimportedNodeDns {
  
  inline def apply(
    ADDRCONFIG: Double,
    ALL: Double,
    Resolver: Instantiable1[/* options */ js.UndefOr[ResolverOptions], Resolver],
    V4MAPPED: Double,
    getDefaultResultOrder: () => ipv4first | ipv6first | verbatim,
    getServers: () => js.Array[String],
    lookup: TypeoflookupPromisify,
    lookupService: TypeoflookupService,
    promises: TypeofpromisesADDRGETNETWORKPARAMS,
    resolve: TypeofresolvePromisify,
    resolve4: Typeofresolve4Promisify,
    resolve6: Typeofresolve6Promisify,
    resolveAny: TypeofresolveAny,
    resolveCaa: TypeofresolveCaa,
    resolveCname: TypeofresolveCname,
    resolveMx: TypeofresolveMx,
    resolveNaptr: TypeofresolveNaptr,
    resolveNs: TypeofresolveNs,
    resolvePtr: TypeofresolvePtr,
    resolveSoa: TypeofresolveSoa,
    resolveSrv: TypeofresolveSrv,
    resolveTxt: TypeofresolveTxt,
    reverse: (String, js.Function2[/* err */ ErrnoException | Null, /* hostnames */ js.Array[String], Unit]) => Unit,
    setDefaultResultOrder: ipv4first | ipv6first | verbatim => Unit,
    setServers: js.Array[String] => Unit
  ): TypeofimportedNodeDns = {
    val __obj = js.Dynamic.literal(ADDRCONFIG = ADDRCONFIG.asInstanceOf[js.Any], ADDRGETNETWORKPARAMS = "EADDRGETNETWORKPARAMS", ALL = ALL.asInstanceOf[js.Any], BADFAMILY = "EBADFAMILY", BADFLAGS = "EBADFLAGS", BADHINTS = "EBADHINTS", BADNAME = "EBADNAME", BADQUERY = "EBADQUERY", BADRESP = "EBADRESP", BADSTR = "EBADSTR", CANCELLED = "ECANCELLED", CONNREFUSED = "ECONNREFUSED", DESTRUCTION = "EDESTRUCTION", EOF = "EOF", FILE = "EFILE", FORMERR = "EFORMERR", LOADIPHLPAPI = "ELOADIPHLPAPI", NODATA = "ENODATA", NOMEM = "ENOMEM", NONAME = "ENONAME", NOTFOUND = "ENOTFOUND", NOTIMP = "ENOTIMP", NOTINITIALIZED = "ENOTINITIALIZED", REFUSED = "EREFUSED", Resolver = Resolver.asInstanceOf[js.Any], SERVFAIL = "ESERVFAIL", TIMEOUT = "ETIMEOUT", V4MAPPED = V4MAPPED.asInstanceOf[js.Any], getDefaultResultOrder = js.Any.fromFunction0(getDefaultResultOrder), getServers = js.Any.fromFunction0(getServers), lookup = lookup.asInstanceOf[js.Any], lookupService = lookupService.asInstanceOf[js.Any], promises = promises.asInstanceOf[js.Any], resolve = resolve.asInstanceOf[js.Any], resolve4 = resolve4.asInstanceOf[js.Any], resolve6 = resolve6.asInstanceOf[js.Any], resolveAny = resolveAny.asInstanceOf[js.Any], resolveCaa = resolveCaa.asInstanceOf[js.Any], resolveCname = resolveCname.asInstanceOf[js.Any], resolveMx = resolveMx.asInstanceOf[js.Any], resolveNaptr = resolveNaptr.asInstanceOf[js.Any], resolveNs = resolveNs.asInstanceOf[js.Any], resolvePtr = resolvePtr.asInstanceOf[js.Any], resolveSoa = resolveSoa.asInstanceOf[js.Any], resolveSrv = resolveSrv.asInstanceOf[js.Any], resolveTxt = resolveTxt.asInstanceOf[js.Any], reverse = js.Any.fromFunction2(reverse), setDefaultResultOrder = js.Any.fromFunction1(setDefaultResultOrder), setServers = js.Any.fromFunction1(setServers))
    __obj.asInstanceOf[TypeofimportedNodeDns]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedNodeDns] (val x: Self) extends AnyVal {
    
    inline def setADDRCONFIG(value: Double): Self = StObject.set(x, "ADDRCONFIG", value.asInstanceOf[js.Any])
    
    inline def setADDRGETNETWORKPARAMS(value: EADDRGETNETWORKPARAMS): Self = StObject.set(x, "ADDRGETNETWORKPARAMS", value.asInstanceOf[js.Any])
    
    inline def setALL(value: Double): Self = StObject.set(x, "ALL", value.asInstanceOf[js.Any])
    
    inline def setBADFAMILY(value: EBADFAMILY): Self = StObject.set(x, "BADFAMILY", value.asInstanceOf[js.Any])
    
    inline def setBADFLAGS(value: EBADFLAGS): Self = StObject.set(x, "BADFLAGS", value.asInstanceOf[js.Any])
    
    inline def setBADHINTS(value: EBADHINTS): Self = StObject.set(x, "BADHINTS", value.asInstanceOf[js.Any])
    
    inline def setBADNAME(value: EBADNAME): Self = StObject.set(x, "BADNAME", value.asInstanceOf[js.Any])
    
    inline def setBADQUERY(value: EBADQUERY): Self = StObject.set(x, "BADQUERY", value.asInstanceOf[js.Any])
    
    inline def setBADRESP(value: EBADRESP): Self = StObject.set(x, "BADRESP", value.asInstanceOf[js.Any])
    
    inline def setBADSTR(value: EBADSTR): Self = StObject.set(x, "BADSTR", value.asInstanceOf[js.Any])
    
    inline def setCANCELLED(value: ECANCELLED): Self = StObject.set(x, "CANCELLED", value.asInstanceOf[js.Any])
    
    inline def setCONNREFUSED(value: ECONNREFUSED): Self = StObject.set(x, "CONNREFUSED", value.asInstanceOf[js.Any])
    
    inline def setDESTRUCTION(value: EDESTRUCTION): Self = StObject.set(x, "DESTRUCTION", value.asInstanceOf[js.Any])
    
    inline def setEOF(value: EOF): Self = StObject.set(x, "EOF", value.asInstanceOf[js.Any])
    
    inline def setFILE(value: EFILE): Self = StObject.set(x, "FILE", value.asInstanceOf[js.Any])
    
    inline def setFORMERR(value: EFORMERR): Self = StObject.set(x, "FORMERR", value.asInstanceOf[js.Any])
    
    inline def setGetDefaultResultOrder(value: () => ipv4first | ipv6first | verbatim): Self = StObject.set(x, "getDefaultResultOrder", js.Any.fromFunction0(value))
    
    inline def setGetServers(value: () => js.Array[String]): Self = StObject.set(x, "getServers", js.Any.fromFunction0(value))
    
    inline def setLOADIPHLPAPI(value: ELOADIPHLPAPI): Self = StObject.set(x, "LOADIPHLPAPI", value.asInstanceOf[js.Any])
    
    inline def setLookup(value: TypeoflookupPromisify): Self = StObject.set(x, "lookup", value.asInstanceOf[js.Any])
    
    inline def setLookupService(value: TypeoflookupService): Self = StObject.set(x, "lookupService", value.asInstanceOf[js.Any])
    
    inline def setNODATA(value: ENODATA): Self = StObject.set(x, "NODATA", value.asInstanceOf[js.Any])
    
    inline def setNOMEM(value: ENOMEM): Self = StObject.set(x, "NOMEM", value.asInstanceOf[js.Any])
    
    inline def setNONAME(value: ENONAME): Self = StObject.set(x, "NONAME", value.asInstanceOf[js.Any])
    
    inline def setNOTFOUND(value: ENOTFOUND): Self = StObject.set(x, "NOTFOUND", value.asInstanceOf[js.Any])
    
    inline def setNOTIMP(value: ENOTIMP): Self = StObject.set(x, "NOTIMP", value.asInstanceOf[js.Any])
    
    inline def setNOTINITIALIZED(value: ENOTINITIALIZED): Self = StObject.set(x, "NOTINITIALIZED", value.asInstanceOf[js.Any])
    
    inline def setPromises(value: TypeofpromisesADDRGETNETWORKPARAMS): Self = StObject.set(x, "promises", value.asInstanceOf[js.Any])
    
    inline def setREFUSED(value: EREFUSED): Self = StObject.set(x, "REFUSED", value.asInstanceOf[js.Any])
    
    inline def setResolve(value: TypeofresolvePromisify): Self = StObject.set(x, "resolve", value.asInstanceOf[js.Any])
    
    inline def setResolve4(value: Typeofresolve4Promisify): Self = StObject.set(x, "resolve4", value.asInstanceOf[js.Any])
    
    inline def setResolve6(value: Typeofresolve6Promisify): Self = StObject.set(x, "resolve6", value.asInstanceOf[js.Any])
    
    inline def setResolveAny(value: TypeofresolveAny): Self = StObject.set(x, "resolveAny", value.asInstanceOf[js.Any])
    
    inline def setResolveCaa(value: TypeofresolveCaa): Self = StObject.set(x, "resolveCaa", value.asInstanceOf[js.Any])
    
    inline def setResolveCname(value: TypeofresolveCname): Self = StObject.set(x, "resolveCname", value.asInstanceOf[js.Any])
    
    inline def setResolveMx(value: TypeofresolveMx): Self = StObject.set(x, "resolveMx", value.asInstanceOf[js.Any])
    
    inline def setResolveNaptr(value: TypeofresolveNaptr): Self = StObject.set(x, "resolveNaptr", value.asInstanceOf[js.Any])
    
    inline def setResolveNs(value: TypeofresolveNs): Self = StObject.set(x, "resolveNs", value.asInstanceOf[js.Any])
    
    inline def setResolvePtr(value: TypeofresolvePtr): Self = StObject.set(x, "resolvePtr", value.asInstanceOf[js.Any])
    
    inline def setResolveSoa(value: TypeofresolveSoa): Self = StObject.set(x, "resolveSoa", value.asInstanceOf[js.Any])
    
    inline def setResolveSrv(value: TypeofresolveSrv): Self = StObject.set(x, "resolveSrv", value.asInstanceOf[js.Any])
    
    inline def setResolveTxt(value: TypeofresolveTxt): Self = StObject.set(x, "resolveTxt", value.asInstanceOf[js.Any])
    
    inline def setResolver(value: Instantiable1[/* options */ js.UndefOr[ResolverOptions], Resolver]): Self = StObject.set(x, "Resolver", value.asInstanceOf[js.Any])
    
    inline def setReverse(
      value: (String, js.Function2[/* err */ ErrnoException | Null, /* hostnames */ js.Array[String], Unit]) => Unit
    ): Self = StObject.set(x, "reverse", js.Any.fromFunction2(value))
    
    inline def setSERVFAIL(value: ESERVFAIL): Self = StObject.set(x, "SERVFAIL", value.asInstanceOf[js.Any])
    
    inline def setSetDefaultResultOrder(value: ipv4first | ipv6first | verbatim => Unit): Self = StObject.set(x, "setDefaultResultOrder", js.Any.fromFunction1(value))
    
    inline def setSetServers(value: js.Array[String] => Unit): Self = StObject.set(x, "setServers", js.Any.fromFunction1(value))
    
    inline def setTIMEOUT(value: ETIMEOUT): Self = StObject.set(x, "TIMEOUT", value.asInstanceOf[js.Any])
    
    inline def setV4MAPPED(value: Double): Self = StObject.set(x, "V4MAPPED", value.asInstanceOf[js.Any])
  }
}

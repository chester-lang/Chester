package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import typings.node.dnsMod.AnyRecord
import typings.node.dnsMod.CaaRecord
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
import typings.node.nodeStrings.A
import typings.node.nodeStrings.AAAA
import typings.node.nodeStrings.ANY
import typings.node.nodeStrings.CAA
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
trait TypeofpromisesADDRGETNETWORKPARAMS extends StObject {
  
  val ADDRGETNETWORKPARAMS: EADDRGETNETWORKPARAMS = js.native
  
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
  
  // Error codes
  val NODATA: ENODATA = js.native
  
  val NOMEM: ENOMEM = js.native
  
  val NONAME: ENONAME = js.native
  
  val NOTFOUND: ENOTFOUND = js.native
  
  val NOTIMP: ENOTIMP = js.native
  
  val NOTINITIALIZED: ENOTINITIALIZED = js.native
  
  val REFUSED: EREFUSED = js.native
  
  /**
    * An independent resolver for DNS requests.
    *
    * Creating a new resolver uses the default server settings. Setting
    * the servers used for a resolver using [`resolver.setServers()`](https://nodejs.org/docs/latest-v20.x/api/dns.html#dnspromisessetserversservers) does not affect
    * other resolvers:
    *
    * ```js
    * const { Resolver } = require('node:dns').promises;
    * const resolver = new Resolver();
    * resolver.setServers(['4.4.4.4']);
    *
    * // This request will use the server at 4.4.4.4, independent of global settings.
    * resolver.resolve4('example.org').then((addresses) => {
    *   // ...
    * });
    *
    * // Alternatively, the same code can be written using async-await style.
    * (async function() {
    *   const addresses = await resolver.resolve4('example.org');
    * })();
    * ```
    *
    * The following methods from the `dnsPromises` API are available:
    *
    * * `resolver.getServers()`
    * * `resolver.resolve()`
    * * `resolver.resolve4()`
    * * `resolver.resolve6()`
    * * `resolver.resolveAny()`
    * * `resolver.resolveCaa()`
    * * `resolver.resolveCname()`
    * * `resolver.resolveMx()`
    * * `resolver.resolveNaptr()`
    * * `resolver.resolveNs()`
    * * `resolver.resolvePtr()`
    * * `resolver.resolveSoa()`
    * * `resolver.resolveSrv()`
    * * `resolver.resolveTxt()`
    * * `resolver.reverse()`
    * * `resolver.setServers()`
    * @since v10.6.0
    */
  var Resolver: Instantiable1[
    /* options */ js.UndefOr[ResolverOptions], 
    typings.node.nodeColondnsMod.promises.Resolver
  ] = js.native
  
  val SERVFAIL: ESERVFAIL = js.native
  
  val TIMEOUT: ETIMEOUT = js.native
  
  /**
    * Get the default value for `verbatim` in {@link lookup} and [dnsPromises.lookup()](https://nodejs.org/docs/latest-v20.x/api/dns.html#dnspromiseslookuphostname-options).
    * The value could be:
    *
    * * `ipv4first`: for `verbatim` defaulting to `false`.
    * * `verbatim`: for `verbatim` defaulting to `true`.
    * @since v20.1.0
    */
  def getDefaultResultOrder(): ipv4first | verbatim = js.native
  
  /**
    * Returns an array of IP address strings, formatted according to [RFC 5952](https://tools.ietf.org/html/rfc5952#section-6),
    * that are currently configured for DNS resolution. A string will include a port
    * section if a custom port is used.
    *
    * ```js
    * [
    *   '4.4.4.4',
    *   '2001:4860:4860::8888',
    *   '4.4.4.4:1053',
    *   '[2001:4860:4860::8888]:1053',
    * ]
    * ```
    * @since v10.6.0
    */
  def getServers(): js.Array[String] = js.native
  
  def lookup(hostname: String): js.Promise[LookupAddress] = js.native
  /**
    * Resolves a host name (e.g. `'nodejs.org'`) into the first found A (IPv4) or
    * AAAA (IPv6) record. All `option` properties are optional. If `options` is an
    * integer, then it must be `4` or `6` – if `options` is not provided, then IPv4
    * and IPv6 addresses are both returned if found.
    *
    * With the `all` option set to `true`, the `Promise` is resolved with `addresses` being an array of objects with the properties `address` and `family`.
    *
    * On error, the `Promise` is rejected with an [`Error`](https://nodejs.org/docs/latest-v20.x/api/errors.html#class-error) object, where `err.code` is the error code.
    * Keep in mind that `err.code` will be set to `'ENOTFOUND'` not only when
    * the host name does not exist but also when the lookup fails in other ways
    * such as no available file descriptors.
    *
    * [`dnsPromises.lookup()`](https://nodejs.org/docs/latest-v20.x/api/dns.html#dnspromiseslookuphostname-options) does not necessarily have anything to do with the DNS
    * protocol. The implementation uses an operating system facility that can
    * associate names with addresses and vice versa. This implementation can have
    * subtle but important consequences on the behavior of any Node.js program. Please
    * take some time to consult the [Implementation considerations section](https://nodejs.org/docs/latest-v20.x/api/dns.html#implementation-considerations) before
    * using `dnsPromises.lookup()`.
    *
    * Example usage:
    *
    * ```js
    * const dns = require('node:dns');
    * const dnsPromises = dns.promises;
    * const options = {
    *   family: 6,
    *   hints: dns.ADDRCONFIG | dns.V4MAPPED,
    * };
    *
    * dnsPromises.lookup('example.com', options).then((result) => {
    *   console.log('address: %j family: IPv%s', result.address, result.family);
    *   // address: "2606:2800:220:1:248:1893:25c8:1946" family: IPv6
    * });
    *
    * // When options.all is true, the result will be an Array.
    * options.all = true;
    * dnsPromises.lookup('example.com', options).then((result) => {
    *   console.log('addresses: %j', result);
    *   // addresses: [{"address":"2606:2800:220:1:248:1893:25c8:1946","family":6}]
    * });
    * ```
    * @since v10.6.0
    */
  def lookup(hostname: String, family: Double): js.Promise[LookupAddress] = js.native
  def lookup(hostname: String, options: LookupAllOptions): js.Promise[js.Array[LookupAddress]] = js.native
  def lookup(hostname: String, options: LookupOneOptions): js.Promise[LookupAddress] = js.native
  def lookup(hostname: String, options: LookupOptions): js.Promise[LookupAddress | js.Array[LookupAddress]] = js.native
  
  /**
    * Resolves the given `address` and `port` into a host name and service using
    * the operating system's underlying `getnameinfo` implementation.
    *
    * If `address` is not a valid IP address, a `TypeError` will be thrown.
    * The `port` will be coerced to a number. If it is not a legal port, a `TypeError` will be thrown.
    *
    * On error, the `Promise` is rejected with an [`Error`](https://nodejs.org/docs/latest-v20.x/api/errors.html#class-error) object, where `err.code` is the error code.
    *
    * ```js
    * const dnsPromises = require('node:dns').promises;
    * dnsPromises.lookupService('127.0.0.1', 22).then((result) => {
    *   console.log(result.hostname, result.service);
    *   // Prints: localhost ssh
    * });
    * ```
    * @since v10.6.0
    */
  def lookupService(address: String, port: Double): js.Promise[Hostname] = js.native
  
  /**
    * Uses the DNS protocol to resolve a host name (e.g. `'nodejs.org'`) into an array
    * of the resource records. When successful, the `Promise` is resolved with an
    * array of resource records. The type and structure of individual results vary
    * based on `rrtype`:
    *
    * <omitted>
    *
    * On error, the `Promise` is rejected with an [`Error`](https://nodejs.org/docs/latest-v20.x/api/errors.html#class-error) object, where `err.code`
    * is one of the [DNS error codes](https://nodejs.org/docs/latest-v20.x/api/dns.html#error-codes).
    * @since v10.6.0
    * @param hostname Host name to resolve.
    * @param [rrtype='A'] Resource record type.
    */
  def resolve(hostname: String): js.Promise[js.Array[String]] = js.native
  def resolve(hostname: String, rrtype: String): js.Promise[
    (js.Array[AnyRecord | js.Array[String] | MxRecord | NaptrRecord | SrvRecord | String]) | SoaRecord
  ] = js.native
  
  /**
    * Uses the DNS protocol to resolve IPv4 addresses (`A` records) for the `hostname`. On success, the `Promise` is resolved with an array of IPv4
    * addresses (e.g. `['74.125.79.104', '74.125.79.105', '74.125.79.106']`).
    * @since v10.6.0
    * @param hostname Host name to resolve.
    */
  def resolve4(hostname: String): js.Promise[js.Array[String]] = js.native
  def resolve4(hostname: String, options: ResolveOptions): js.Promise[js.Array[RecordWithTtl | String]] = js.native
  def resolve4(hostname: String, options: ResolveWithTtlOptions): js.Promise[js.Array[RecordWithTtl]] = js.native
  
  /**
    * Uses the DNS protocol to resolve IPv6 addresses (`AAAA` records) for the `hostname`. On success, the `Promise` is resolved with an array of IPv6
    * addresses.
    * @since v10.6.0
    * @param hostname Host name to resolve.
    */
  def resolve6(hostname: String): js.Promise[js.Array[String]] = js.native
  def resolve6(hostname: String, options: ResolveOptions): js.Promise[js.Array[RecordWithTtl | String]] = js.native
  def resolve6(hostname: String, options: ResolveWithTtlOptions): js.Promise[js.Array[RecordWithTtl]] = js.native
  
  /**
    * Uses the DNS protocol to resolve all records (also known as `ANY` or `*` query).
    * On success, the `Promise` is resolved with an array containing various types of
    * records. Each object has a property `type` that indicates the type of the
    * current record. And depending on the `type`, additional properties will be
    * present on the object:
    *
    * <omitted>
    *
    * Here is an example of the result object:
    *
    * ```js
    * [ { type: 'A', address: '127.0.0.1', ttl: 299 },
    *   { type: 'CNAME', value: 'example.com' },
    *   { type: 'MX', exchange: 'alt4.aspmx.l.example.com', priority: 50 },
    *   { type: 'NS', value: 'ns1.example.com' },
    *   { type: 'TXT', entries: [ 'v=spf1 include:_spf.example.com ~all' ] },
    *   { type: 'SOA',
    *     nsname: 'ns1.example.com',
    *     hostmaster: 'admin.example.com',
    *     serial: 156696742,
    *     refresh: 900,
    *     retry: 900,
    *     expire: 1800,
    *     minttl: 60 } ]
    * ```
    * @since v10.6.0
    */
  def resolveAny(hostname: String): js.Promise[js.Array[AnyRecord]] = js.native
  
  /**
    * Uses the DNS protocol to resolve `CAA` records for the `hostname`. On success,
    * the `Promise` is resolved with an array of objects containing available
    * certification authority authorization records available for the `hostname` (e.g. `[{critical: 0, iodef: 'mailto:pki@example.com'},{critical: 128, issue: 'pki.example.com'}]`).
    * @since v15.0.0, v14.17.0
    */
  def resolveCaa(hostname: String): js.Promise[js.Array[CaaRecord]] = js.native
  
  /**
    * Uses the DNS protocol to resolve `CNAME` records for the `hostname`. On success,
    * the `Promise` is resolved with an array of canonical name records available for
    * the `hostname` (e.g. `['bar.example.com']`).
    * @since v10.6.0
    */
  def resolveCname(hostname: String): js.Promise[js.Array[String]] = js.native
  
  /**
    * Uses the DNS protocol to resolve mail exchange records (`MX` records) for the `hostname`. On success, the `Promise` is resolved with an array of objects
    * containing both a `priority` and `exchange` property (e.g.`[{priority: 10, exchange: 'mx.example.com'}, ...]`).
    * @since v10.6.0
    */
  def resolveMx(hostname: String): js.Promise[js.Array[MxRecord]] = js.native
  
  /**
    * Uses the DNS protocol to resolve regular expression-based records (`NAPTR` records) for the `hostname`. On success, the `Promise` is resolved with an array
    * of objects with the following properties:
    *
    * * `flags`
    * * `service`
    * * `regexp`
    * * `replacement`
    * * `order`
    * * `preference`
    *
    * ```js
    * {
    *   flags: 's',
    *   service: 'SIP+D2U',
    *   regexp: '',
    *   replacement: '_sip._udp.example.com',
    *   order: 30,
    *   preference: 100
    * }
    * ```
    * @since v10.6.0
    */
  def resolveNaptr(hostname: String): js.Promise[js.Array[NaptrRecord]] = js.native
  
  /**
    * Uses the DNS protocol to resolve name server records (`NS` records) for the `hostname`. On success, the `Promise` is resolved with an array of name server
    * records available for `hostname` (e.g.`['ns1.example.com', 'ns2.example.com']`).
    * @since v10.6.0
    */
  def resolveNs(hostname: String): js.Promise[js.Array[String]] = js.native
  
  /**
    * Uses the DNS protocol to resolve pointer records (`PTR` records) for the `hostname`. On success, the `Promise` is resolved with an array of strings
    * containing the reply records.
    * @since v10.6.0
    */
  def resolvePtr(hostname: String): js.Promise[js.Array[String]] = js.native
  
  /**
    * Uses the DNS protocol to resolve a start of authority record (`SOA` record) for
    * the `hostname`. On success, the `Promise` is resolved with an object with the
    * following properties:
    *
    * * `nsname`
    * * `hostmaster`
    * * `serial`
    * * `refresh`
    * * `retry`
    * * `expire`
    * * `minttl`
    *
    * ```js
    * {
    *   nsname: 'ns.example.com',
    *   hostmaster: 'root.example.com',
    *   serial: 2013101809,
    *   refresh: 10000,
    *   retry: 2400,
    *   expire: 604800,
    *   minttl: 3600
    * }
    * ```
    * @since v10.6.0
    */
  def resolveSoa(hostname: String): js.Promise[SoaRecord] = js.native
  
  /**
    * Uses the DNS protocol to resolve service records (`SRV` records) for the `hostname`. On success, the `Promise` is resolved with an array of objects with
    * the following properties:
    *
    * * `priority`
    * * `weight`
    * * `port`
    * * `name`
    *
    * ```js
    * {
    *   priority: 10,
    *   weight: 5,
    *   port: 21223,
    *   name: 'service.example.com'
    * }
    * ```
    * @since v10.6.0
    */
  def resolveSrv(hostname: String): js.Promise[js.Array[SrvRecord]] = js.native
  
  /**
    * Uses the DNS protocol to resolve text queries (`TXT` records) for the `hostname`. On success, the `Promise` is resolved with a two-dimensional array
    * of the text records available for `hostname` (e.g.`[ ['v=spf1 ip4:0.0.0.0 ', '~all' ] ]`). Each sub-array contains TXT chunks of
    * one record. Depending on the use case, these could be either joined together or
    * treated separately.
    * @since v10.6.0
    */
  def resolveTxt(hostname: String): js.Promise[js.Array[js.Array[String]]] = js.native
  
  @JSName("resolve")
  def resolve_A(hostname: String, rrtype: A): js.Promise[js.Array[String]] = js.native
  @JSName("resolve")
  def resolve_AAAA(hostname: String, rrtype: AAAA): js.Promise[js.Array[String]] = js.native
  @JSName("resolve")
  def resolve_ANY(hostname: String, rrtype: ANY): js.Promise[js.Array[AnyRecord]] = js.native
  @JSName("resolve")
  def resolve_CAA(hostname: String, rrtype: CAA): js.Promise[js.Array[CaaRecord]] = js.native
  @JSName("resolve")
  def resolve_CNAME(hostname: String, rrtype: CNAME): js.Promise[js.Array[String]] = js.native
  @JSName("resolve")
  def resolve_MX(hostname: String, rrtype: MX): js.Promise[js.Array[MxRecord]] = js.native
  @JSName("resolve")
  def resolve_NAPTR(hostname: String, rrtype: NAPTR): js.Promise[js.Array[NaptrRecord]] = js.native
  @JSName("resolve")
  def resolve_NS(hostname: String, rrtype: NS): js.Promise[js.Array[String]] = js.native
  @JSName("resolve")
  def resolve_PTR(hostname: String, rrtype: PTR): js.Promise[js.Array[String]] = js.native
  @JSName("resolve")
  def resolve_SOA(hostname: String, rrtype: SOA): js.Promise[SoaRecord] = js.native
  @JSName("resolve")
  def resolve_SRV(hostname: String, rrtype: SRV): js.Promise[js.Array[SrvRecord]] = js.native
  @JSName("resolve")
  def resolve_TXT(hostname: String, rrtype: TXT): js.Promise[js.Array[js.Array[String]]] = js.native
  
  /**
    * Performs a reverse DNS query that resolves an IPv4 or IPv6 address to an
    * array of host names.
    *
    * On error, the `Promise` is rejected with an [`Error`](https://nodejs.org/docs/latest-v20.x/api/errors.html#class-error) object, where `err.code`
    * is one of the [DNS error codes](https://nodejs.org/docs/latest-v20.x/api/dns.html#error-codes).
    * @since v10.6.0
    */
  def reverse(ip: String): js.Promise[js.Array[String]] = js.native
  
  /**
    * Set the default value of `order` in `dns.lookup()` and `{@link lookup}`. The value could be:
    *
    * * `ipv4first`: sets default `order` to `ipv4first`.
    * * `ipv6first`: sets default `order` to `ipv6first`.
    * * `verbatim`: sets default `order` to `verbatim`.
    *
    * The default is `verbatim` and [dnsPromises.setDefaultResultOrder()](https://nodejs.org/docs/latest-v20.x/api/dns.html#dnspromisessetdefaultresultorderorder)
    * have higher priority than [`--dns-result-order`](https://nodejs.org/docs/latest-v20.x/api/cli.html#--dns-result-orderorder).
    * When using [worker threads](https://nodejs.org/docs/latest-v20.x/api/worker_threads.html), [`dnsPromises.setDefaultResultOrder()`](https://nodejs.org/docs/latest-v20.x/api/dns.html#dnspromisessetdefaultresultorderorder)
    * from the main thread won't affect the default dns orders in workers.
    * @since v16.4.0, v14.18.0
    * @param order must be `'ipv4first'`, `'ipv6first'` or `'verbatim'`.
    */
  def setDefaultResultOrder(order: ipv4first | ipv6first | verbatim): Unit = js.native
  
  /**
    * Sets the IP address and port of servers to be used when performing DNS
    * resolution. The `servers` argument is an array of [RFC 5952](https://tools.ietf.org/html/rfc5952#section-6) formatted
    * addresses. If the port is the IANA default DNS port (53) it can be omitted.
    *
    * ```js
    * dnsPromises.setServers([
    *   '4.4.4.4',
    *   '[2001:4860:4860::8888]',
    *   '4.4.4.4:1053',
    *   '[2001:4860:4860::8888]:1053',
    * ]);
    * ```
    *
    * An error will be thrown if an invalid address is provided.
    *
    * The `dnsPromises.setServers()` method must not be called while a DNS query is in
    * progress.
    *
    * This method works much like [resolve.conf](https://man7.org/linux/man-pages/man5/resolv.conf.5.html).
    * That is, if attempting to resolve with the first server provided results in a `NOTFOUND` error, the `resolve()` method will _not_ attempt to resolve with
    * subsequent servers provided. Fallback DNS servers will only be used if the
    * earlier ones time out or result in some other error.
    * @since v10.6.0
    * @param servers array of `RFC 5952` formatted addresses
    */
  def setServers(servers: js.Array[String]): Unit = js.native
}

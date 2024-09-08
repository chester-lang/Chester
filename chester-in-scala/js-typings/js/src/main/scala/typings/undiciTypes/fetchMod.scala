package typings.undiciTypes

import org.scalajs.dom.AbortSignal
import org.scalajs.dom.Blob
import org.scalajs.dom.ReadableStream
import org.scalajs.dom.URL
import typings.std.IteratorResult
import typings.std.Record
import typings.std.URLSearchParams
import typings.undiciTypes.dispatcherMod.default
import typings.undiciTypes.formdataMod.FormData
import typings.undiciTypes.undiciTypesStrings.half
import typings.undiciTypes.undiciTypesStrings.object_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object fetchMod {
  
  @JSImport("undici-types/fetch", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  @JSImport("undici-types/fetch", "BodyMixin")
  @js.native
  open class BodyMixin () extends StObject {
    
    def arrayBuffer(): js.Promise[js.typedarray.ArrayBuffer] = js.native
    
    def blob(): js.Promise[Blob] = js.native
    
    val body: ReadableStream[Any] | Null = js.native
    
    val bodyUsed: Boolean = js.native
    
    /**
      * @deprecated This method is not recommended for parsing multipart/form-data bodies in server environments.
      * It is recommended to use a library such as [@fastify/busboy](https://www.npmjs.com/package/@fastify/busboy) as follows:
      * 
      * @example
      * ```js
      * import { Busboy } from '@fastify/busboy'
      * import { Readable } from 'node:stream'
      * 
      * const response = await fetch('...')
      * const busboy = new Busboy({ headers: { 'content-type': response.headers.get('content-type') } })
      * 
      * // handle events emitted from `busboy`
      * 
      * Readable.fromWeb(response.body).pipe(busboy)
      * ```
      */
    def formData(): js.Promise[FormData] = js.native
    
    def json(): js.Promise[Any] = js.native
    
    def text(): js.Promise[String] = js.native
  }
  
  @JSImport("undici-types/fetch", "Headers")
  @js.native
  open class Headers ()
    extends StObject
       with SpecIterable[js.Tuple2[String, String]] {
    def this(init: HeadersInit) = this()
    
    def append(name: String, value: String): Unit = js.native
    
    def delete(name: String): Unit = js.native
    
    def entries(): SpecIterableIterator[js.Tuple2[String, String]] = js.native
    
    def forEach(callbackfn: js.Function3[/* value */ String, /* key */ String, /* iterable */ this.type, Unit]): Unit = js.native
    def forEach(
      callbackfn: js.Function3[/* value */ String, /* key */ String, /* iterable */ this.type, Unit],
      thisArg: Any
    ): Unit = js.native
    
    def get(name: String): String | Null = js.native
    
    def getSetCookie(): js.Array[String] = js.native
    
    def has(name: String): Boolean = js.native
    
    @JSName(js.Symbol.iterator)
    val iterator_Headers: js.Function0[SpecIterableIterator[js.Tuple2[String, String]]] = js.native
    
    def keys(): SpecIterableIterator[String] = js.native
    
    def set(name: String, value: String): Unit = js.native
    
    def values(): SpecIterableIterator[String] = js.native
  }
  
  @JSImport("undici-types/fetch", "Request")
  @js.native
  open class Request protected () extends BodyMixin {
    def this(input: RequestInfo) = this()
    def this(input: RequestInfo, init: RequestInit) = this()
    
    val cache: RequestCache = js.native
    
    val credentials: RequestCredentials = js.native
    
    val destination: RequestDestination = js.native
    
    val duplex: RequestDuplex = js.native
    
    val headers: Headers = js.native
    
    val integrity: String = js.native
    
    val keepalive: Boolean = js.native
    
    val method: String = js.native
    
    val mode: RequestMode = js.native
    
    val redirect: RequestRedirect = js.native
    
    val referrer: String = js.native
    
    val referrerPolicy: ReferrerPolicy = js.native
    
    val signal: AbortSignal = js.native
    
    val url: String = js.native
  }
  
  @JSImport("undici-types/fetch", "Response")
  @js.native
  open class Response () extends BodyMixin {
    def this(body: BodyInit) = this()
    def this(body: Unit, init: ResponseInit) = this()
    def this(body: BodyInit, init: ResponseInit) = this()
    
    val headers: Headers = js.native
    
    val ok: Boolean = js.native
    
    val redirected: Boolean = js.native
    
    val status: Double = js.native
    
    val statusText: String = js.native
    
    val `type`: ResponseType = js.native
    
    val url: String = js.native
  }
  /* static members */
  object Response {
    
    @JSImport("undici-types/fetch", "Response")
    @js.native
    val ^ : js.Any = js.native
    
    inline def error(): Response = ^.asInstanceOf[js.Dynamic].applyDynamic("error")().asInstanceOf[Response]
    
    inline def json(data: Any): Response = ^.asInstanceOf[js.Dynamic].applyDynamic("json")(data.asInstanceOf[js.Any]).asInstanceOf[Response]
    inline def json(data: Any, init: ResponseInit): Response = (^.asInstanceOf[js.Dynamic].applyDynamic("json")(data.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[Response]
    
    inline def redirect(url: String, status: ResponseRedirectStatus): Response = (^.asInstanceOf[js.Dynamic].applyDynamic("redirect")(url.asInstanceOf[js.Any], status.asInstanceOf[js.Any])).asInstanceOf[Response]
    inline def redirect(url: URL, status: ResponseRedirectStatus): Response = (^.asInstanceOf[js.Dynamic].applyDynamic("redirect")(url.asInstanceOf[js.Any], status.asInstanceOf[js.Any])).asInstanceOf[Response]
  }
  
  inline def fetch(input: RequestInfo): js.Promise[Response] = ^.asInstanceOf[js.Dynamic].applyDynamic("fetch")(input.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Response]]
  inline def fetch(input: RequestInfo, init: RequestInit): js.Promise[Response] = (^.asInstanceOf[js.Dynamic].applyDynamic("fetch")(input.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Response]]
  
  type BodyInit = js.typedarray.ArrayBuffer | (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<Uint8Array> */ Any) | Blob | FormData | js.Iterable[js.typedarray.Uint8Array] | URLSearchParams | Null | String
  
  type HeadersInit = js.Array[js.Array[String]] | (Record[String, String | js.Array[String]]) | Headers
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings._empty
    - typings.undiciTypes.undiciTypesStrings.`no-referrer`
    - typings.undiciTypes.undiciTypesStrings.`no-referrer-when-downgrade`
    - typings.undiciTypes.undiciTypesStrings.origin
    - typings.undiciTypes.undiciTypesStrings.`origin-when-cross-origin`
    - typings.undiciTypes.undiciTypesStrings.`same-origin`
    - typings.undiciTypes.undiciTypesStrings.`strict-origin`
    - typings.undiciTypes.undiciTypesStrings.`strict-origin-when-cross-origin`
    - typings.undiciTypes.undiciTypesStrings.`unsafe-url`
  */
  trait ReferrerPolicy extends StObject
  object ReferrerPolicy {
    
    inline def _empty: typings.undiciTypes.undiciTypesStrings._empty = "".asInstanceOf[typings.undiciTypes.undiciTypesStrings._empty]
    
    inline def `no-referrer`: typings.undiciTypes.undiciTypesStrings.`no-referrer` = "no-referrer".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`no-referrer`]
    
    inline def `no-referrer-when-downgrade`: typings.undiciTypes.undiciTypesStrings.`no-referrer-when-downgrade` = "no-referrer-when-downgrade".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`no-referrer-when-downgrade`]
    
    inline def origin: typings.undiciTypes.undiciTypesStrings.origin = "origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.origin]
    
    inline def `origin-when-cross-origin`: typings.undiciTypes.undiciTypesStrings.`origin-when-cross-origin` = "origin-when-cross-origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`origin-when-cross-origin`]
    
    inline def `same-origin`: typings.undiciTypes.undiciTypesStrings.`same-origin` = "same-origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`same-origin`]
    
    inline def `strict-origin`: typings.undiciTypes.undiciTypesStrings.`strict-origin` = "strict-origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`strict-origin`]
    
    inline def `strict-origin-when-cross-origin`: typings.undiciTypes.undiciTypesStrings.`strict-origin-when-cross-origin` = "strict-origin-when-cross-origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`strict-origin-when-cross-origin`]
    
    inline def `unsafe-url`: typings.undiciTypes.undiciTypesStrings.`unsafe-url` = "unsafe-url".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`unsafe-url`]
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings.default
    - typings.undiciTypes.undiciTypesStrings.`force-cache`
    - typings.undiciTypes.undiciTypesStrings.`no-cache`
    - typings.undiciTypes.undiciTypesStrings.`no-store`
    - typings.undiciTypes.undiciTypesStrings.`only-if-cached`
    - typings.undiciTypes.undiciTypesStrings.reload
  */
  trait RequestCache extends StObject
  object RequestCache {
    
    inline def default: typings.undiciTypes.undiciTypesStrings.default = "default".asInstanceOf[typings.undiciTypes.undiciTypesStrings.default]
    
    inline def `force-cache`: typings.undiciTypes.undiciTypesStrings.`force-cache` = "force-cache".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`force-cache`]
    
    inline def `no-cache`: typings.undiciTypes.undiciTypesStrings.`no-cache` = "no-cache".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`no-cache`]
    
    inline def `no-store`: typings.undiciTypes.undiciTypesStrings.`no-store` = "no-store".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`no-store`]
    
    inline def `only-if-cached`: typings.undiciTypes.undiciTypesStrings.`only-if-cached` = "only-if-cached".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`only-if-cached`]
    
    inline def reload: typings.undiciTypes.undiciTypesStrings.reload = "reload".asInstanceOf[typings.undiciTypes.undiciTypesStrings.reload]
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings.omit
    - typings.undiciTypes.undiciTypesStrings.include
    - typings.undiciTypes.undiciTypesStrings.`same-origin`
  */
  trait RequestCredentials extends StObject
  object RequestCredentials {
    
    inline def include: typings.undiciTypes.undiciTypesStrings.include = "include".asInstanceOf[typings.undiciTypes.undiciTypesStrings.include]
    
    inline def omit: typings.undiciTypes.undiciTypesStrings.omit = "omit".asInstanceOf[typings.undiciTypes.undiciTypesStrings.omit]
    
    inline def `same-origin`: typings.undiciTypes.undiciTypesStrings.`same-origin` = "same-origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`same-origin`]
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings._empty
    - typings.undiciTypes.undiciTypesStrings.audio
    - typings.undiciTypes.undiciTypesStrings.audioworklet
    - typings.undiciTypes.undiciTypesStrings.document
    - typings.undiciTypes.undiciTypesStrings.embed
    - typings.undiciTypes.undiciTypesStrings.font
    - typings.undiciTypes.undiciTypesStrings.image
    - typings.undiciTypes.undiciTypesStrings.manifest
    - typings.undiciTypes.undiciTypesStrings.object_
    - typings.undiciTypes.undiciTypesStrings.paintworklet
    - typings.undiciTypes.undiciTypesStrings.report
    - typings.undiciTypes.undiciTypesStrings.script
    - typings.undiciTypes.undiciTypesStrings.sharedworker
    - typings.undiciTypes.undiciTypesStrings.style
    - typings.undiciTypes.undiciTypesStrings.track
    - typings.undiciTypes.undiciTypesStrings.video
    - typings.undiciTypes.undiciTypesStrings.worker
    - typings.undiciTypes.undiciTypesStrings.xslt
  */
  trait RequestDestination extends StObject
  object RequestDestination {
    
    inline def _empty: typings.undiciTypes.undiciTypesStrings._empty = "".asInstanceOf[typings.undiciTypes.undiciTypesStrings._empty]
    
    inline def audio: typings.undiciTypes.undiciTypesStrings.audio = "audio".asInstanceOf[typings.undiciTypes.undiciTypesStrings.audio]
    
    inline def audioworklet: typings.undiciTypes.undiciTypesStrings.audioworklet = "audioworklet".asInstanceOf[typings.undiciTypes.undiciTypesStrings.audioworklet]
    
    inline def document: typings.undiciTypes.undiciTypesStrings.document = "document".asInstanceOf[typings.undiciTypes.undiciTypesStrings.document]
    
    inline def embed: typings.undiciTypes.undiciTypesStrings.embed = "embed".asInstanceOf[typings.undiciTypes.undiciTypesStrings.embed]
    
    inline def font: typings.undiciTypes.undiciTypesStrings.font = "font".asInstanceOf[typings.undiciTypes.undiciTypesStrings.font]
    
    inline def image: typings.undiciTypes.undiciTypesStrings.image = "image".asInstanceOf[typings.undiciTypes.undiciTypesStrings.image]
    
    inline def manifest: typings.undiciTypes.undiciTypesStrings.manifest = "manifest".asInstanceOf[typings.undiciTypes.undiciTypesStrings.manifest]
    
    inline def `object`: object_ = "object".asInstanceOf[object_]
    
    inline def paintworklet: typings.undiciTypes.undiciTypesStrings.paintworklet = "paintworklet".asInstanceOf[typings.undiciTypes.undiciTypesStrings.paintworklet]
    
    inline def report: typings.undiciTypes.undiciTypesStrings.report = "report".asInstanceOf[typings.undiciTypes.undiciTypesStrings.report]
    
    inline def script: typings.undiciTypes.undiciTypesStrings.script = "script".asInstanceOf[typings.undiciTypes.undiciTypesStrings.script]
    
    inline def sharedworker: typings.undiciTypes.undiciTypesStrings.sharedworker = "sharedworker".asInstanceOf[typings.undiciTypes.undiciTypesStrings.sharedworker]
    
    inline def style: typings.undiciTypes.undiciTypesStrings.style = "style".asInstanceOf[typings.undiciTypes.undiciTypesStrings.style]
    
    inline def track: typings.undiciTypes.undiciTypesStrings.track = "track".asInstanceOf[typings.undiciTypes.undiciTypesStrings.track]
    
    inline def video: typings.undiciTypes.undiciTypesStrings.video = "video".asInstanceOf[typings.undiciTypes.undiciTypesStrings.video]
    
    inline def worker: typings.undiciTypes.undiciTypesStrings.worker = "worker".asInstanceOf[typings.undiciTypes.undiciTypesStrings.worker]
    
    inline def xslt: typings.undiciTypes.undiciTypesStrings.xslt = "xslt".asInstanceOf[typings.undiciTypes.undiciTypesStrings.xslt]
  }
  
  type RequestDuplex = half
  
  type RequestInfo = String | URL | Request
  
  trait RequestInit extends StObject {
    
    var body: js.UndefOr[BodyInit | Null] = js.undefined
    
    var credentials: js.UndefOr[RequestCredentials] = js.undefined
    
    var dispatcher: js.UndefOr[default] = js.undefined
    
    var duplex: js.UndefOr[RequestDuplex] = js.undefined
    
    var headers: js.UndefOr[HeadersInit] = js.undefined
    
    var integrity: js.UndefOr[String] = js.undefined
    
    var keepalive: js.UndefOr[Boolean] = js.undefined
    
    var method: js.UndefOr[String] = js.undefined
    
    var mode: js.UndefOr[RequestMode] = js.undefined
    
    var redirect: js.UndefOr[RequestRedirect] = js.undefined
    
    var referrer: js.UndefOr[String] = js.undefined
    
    var referrerPolicy: js.UndefOr[ReferrerPolicy] = js.undefined
    
    var signal: js.UndefOr[AbortSignal | Null] = js.undefined
    
    var window: js.UndefOr[Null] = js.undefined
  }
  object RequestInit {
    
    inline def apply(): RequestInit = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[RequestInit]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: RequestInit] (val x: Self) extends AnyVal {
      
      inline def setBody(value: BodyInit): Self = StObject.set(x, "body", value.asInstanceOf[js.Any])
      
      inline def setBodyNull: Self = StObject.set(x, "body", null)
      
      inline def setBodyUndefined: Self = StObject.set(x, "body", js.undefined)
      
      inline def setCredentials(value: RequestCredentials): Self = StObject.set(x, "credentials", value.asInstanceOf[js.Any])
      
      inline def setCredentialsUndefined: Self = StObject.set(x, "credentials", js.undefined)
      
      inline def setDispatcher(value: default): Self = StObject.set(x, "dispatcher", value.asInstanceOf[js.Any])
      
      inline def setDispatcherUndefined: Self = StObject.set(x, "dispatcher", js.undefined)
      
      inline def setDuplex(value: RequestDuplex): Self = StObject.set(x, "duplex", value.asInstanceOf[js.Any])
      
      inline def setDuplexUndefined: Self = StObject.set(x, "duplex", js.undefined)
      
      inline def setHeaders(value: HeadersInit): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
      
      inline def setHeadersUndefined: Self = StObject.set(x, "headers", js.undefined)
      
      inline def setHeadersVarargs(value: js.Array[String]*): Self = StObject.set(x, "headers", js.Array(value*))
      
      inline def setIntegrity(value: String): Self = StObject.set(x, "integrity", value.asInstanceOf[js.Any])
      
      inline def setIntegrityUndefined: Self = StObject.set(x, "integrity", js.undefined)
      
      inline def setKeepalive(value: Boolean): Self = StObject.set(x, "keepalive", value.asInstanceOf[js.Any])
      
      inline def setKeepaliveUndefined: Self = StObject.set(x, "keepalive", js.undefined)
      
      inline def setMethod(value: String): Self = StObject.set(x, "method", value.asInstanceOf[js.Any])
      
      inline def setMethodUndefined: Self = StObject.set(x, "method", js.undefined)
      
      inline def setMode(value: RequestMode): Self = StObject.set(x, "mode", value.asInstanceOf[js.Any])
      
      inline def setModeUndefined: Self = StObject.set(x, "mode", js.undefined)
      
      inline def setRedirect(value: RequestRedirect): Self = StObject.set(x, "redirect", value.asInstanceOf[js.Any])
      
      inline def setRedirectUndefined: Self = StObject.set(x, "redirect", js.undefined)
      
      inline def setReferrer(value: String): Self = StObject.set(x, "referrer", value.asInstanceOf[js.Any])
      
      inline def setReferrerPolicy(value: ReferrerPolicy): Self = StObject.set(x, "referrerPolicy", value.asInstanceOf[js.Any])
      
      inline def setReferrerPolicyUndefined: Self = StObject.set(x, "referrerPolicy", js.undefined)
      
      inline def setReferrerUndefined: Self = StObject.set(x, "referrer", js.undefined)
      
      inline def setSignal(value: AbortSignal): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
      
      inline def setSignalNull: Self = StObject.set(x, "signal", null)
      
      inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    }
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings.cors
    - typings.undiciTypes.undiciTypesStrings.navigate
    - typings.undiciTypes.undiciTypesStrings.`no-cors`
    - typings.undiciTypes.undiciTypesStrings.`same-origin`
  */
  trait RequestMode extends StObject
  object RequestMode {
    
    inline def cors: typings.undiciTypes.undiciTypesStrings.cors = "cors".asInstanceOf[typings.undiciTypes.undiciTypesStrings.cors]
    
    inline def navigate: typings.undiciTypes.undiciTypesStrings.navigate = "navigate".asInstanceOf[typings.undiciTypes.undiciTypesStrings.navigate]
    
    inline def `no-cors`: typings.undiciTypes.undiciTypesStrings.`no-cors` = "no-cors".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`no-cors`]
    
    inline def `same-origin`: typings.undiciTypes.undiciTypesStrings.`same-origin` = "same-origin".asInstanceOf[typings.undiciTypes.undiciTypesStrings.`same-origin`]
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings.error
    - typings.undiciTypes.undiciTypesStrings.follow
    - typings.undiciTypes.undiciTypesStrings.manual
  */
  trait RequestRedirect extends StObject
  object RequestRedirect {
    
    inline def error: typings.undiciTypes.undiciTypesStrings.error = "error".asInstanceOf[typings.undiciTypes.undiciTypesStrings.error]
    
    inline def follow: typings.undiciTypes.undiciTypesStrings.follow = "follow".asInstanceOf[typings.undiciTypes.undiciTypesStrings.follow]
    
    inline def manual: typings.undiciTypes.undiciTypesStrings.manual = "manual".asInstanceOf[typings.undiciTypes.undiciTypesStrings.manual]
  }
  
  trait ResponseInit extends StObject {
    
    val headers: js.UndefOr[HeadersInit] = js.undefined
    
    val status: js.UndefOr[Double] = js.undefined
    
    val statusText: js.UndefOr[String] = js.undefined
  }
  object ResponseInit {
    
    inline def apply(): ResponseInit = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[ResponseInit]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: ResponseInit] (val x: Self) extends AnyVal {
      
      inline def setHeaders(value: HeadersInit): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
      
      inline def setHeadersUndefined: Self = StObject.set(x, "headers", js.undefined)
      
      inline def setHeadersVarargs(value: js.Array[String]*): Self = StObject.set(x, "headers", js.Array(value*))
      
      inline def setStatus(value: Double): Self = StObject.set(x, "status", value.asInstanceOf[js.Any])
      
      inline def setStatusText(value: String): Self = StObject.set(x, "statusText", value.asInstanceOf[js.Any])
      
      inline def setStatusTextUndefined: Self = StObject.set(x, "statusText", js.undefined)
      
      inline def setStatusUndefined: Self = StObject.set(x, "status", js.undefined)
    }
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesInts.`301`
    - typings.undiciTypes.undiciTypesInts.`302`
    - typings.undiciTypes.undiciTypesInts.`303`
    - typings.undiciTypes.undiciTypesInts.`307`
    - typings.undiciTypes.undiciTypesInts.`308`
  */
  trait ResponseRedirectStatus extends StObject
  object ResponseRedirectStatus {
    
    inline def `301`: typings.undiciTypes.undiciTypesInts.`301` = 301.asInstanceOf[typings.undiciTypes.undiciTypesInts.`301`]
    
    inline def `302`: typings.undiciTypes.undiciTypesInts.`302` = 302.asInstanceOf[typings.undiciTypes.undiciTypesInts.`302`]
    
    inline def `303`: typings.undiciTypes.undiciTypesInts.`303` = 303.asInstanceOf[typings.undiciTypes.undiciTypesInts.`303`]
    
    inline def `307`: typings.undiciTypes.undiciTypesInts.`307` = 307.asInstanceOf[typings.undiciTypes.undiciTypesInts.`307`]
    
    inline def `308`: typings.undiciTypes.undiciTypesInts.`308` = 308.asInstanceOf[typings.undiciTypes.undiciTypesInts.`308`]
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.undiciTypes.undiciTypesStrings.basic
    - typings.undiciTypes.undiciTypesStrings.cors
    - typings.undiciTypes.undiciTypesStrings.default
    - typings.undiciTypes.undiciTypesStrings.error
    - typings.undiciTypes.undiciTypesStrings.opaque
    - typings.undiciTypes.undiciTypesStrings.opaqueredirect
  */
  trait ResponseType extends StObject
  object ResponseType {
    
    inline def default: typings.undiciTypes.undiciTypesStrings.default = "default".asInstanceOf[typings.undiciTypes.undiciTypesStrings.default]
    
    inline def basic: typings.undiciTypes.undiciTypesStrings.basic = "basic".asInstanceOf[typings.undiciTypes.undiciTypesStrings.basic]
    
    inline def cors: typings.undiciTypes.undiciTypesStrings.cors = "cors".asInstanceOf[typings.undiciTypes.undiciTypesStrings.cors]
    
    inline def error: typings.undiciTypes.undiciTypesStrings.error = "error".asInstanceOf[typings.undiciTypes.undiciTypesStrings.error]
    
    inline def opaque: typings.undiciTypes.undiciTypesStrings.opaque = "opaque".asInstanceOf[typings.undiciTypes.undiciTypesStrings.opaque]
    
    inline def opaqueredirect: typings.undiciTypes.undiciTypesStrings.opaqueredirect = "opaqueredirect".asInstanceOf[typings.undiciTypes.undiciTypesStrings.opaqueredirect]
  }
  
  @js.native
  trait SpecIterable[T] extends StObject {
    
    @JSName(js.Symbol.iterator)
    var iterator: js.Function0[SpecIterator[T, Any, Unit]] = js.native
  }
  
  @js.native
  trait SpecIterableIterator[T]
    extends StObject
       with SpecIterator[T, Any, Unit] {
    
    @JSName(js.Symbol.iterator)
    var iterator: js.Function0[SpecIterableIterator[T]] = js.native
  }
  
  @js.native
  trait SpecIterator[T, TReturn, TNext] extends StObject {
    
    def next(
      /* import warning: parser.TsParser#functionParam Dropping repeated marker of param args because its type [] | [TNext] is not an array type */ args: js.Array[Any | TNext]
    ): IteratorResult[T, TReturn] = js.native
  }
}

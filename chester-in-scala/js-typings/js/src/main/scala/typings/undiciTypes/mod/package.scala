package typings.undiciTypes.mod

import org.scalajs.dom.URL
import typings.std.Record
import typings.undiciTypes.anon.Domain
import typings.undiciTypes.anon.dispatcherdefaultundefine
import typings.undiciTypes.anon.dispatcherdefaultundefineBlocking
import typings.undiciTypes.anon.dispatcherdefaultundefineBody
import typings.undiciTypes.anon.dispatcherdefaultundefineDispatcher
import typings.undiciTypes.anon.dispatcherdefaultundefineHeaders
import typings.undiciTypes.cacheMod.CacheStorage
import typings.undiciTypes.connectorMod.buildConnector.BuildOptions
import typings.undiciTypes.connectorMod.buildConnector.connector
import typings.undiciTypes.contentTypeMod.MIMEType
import typings.undiciTypes.cookiesMod.Cookie
import typings.undiciTypes.dispatcherMod.Dispatcher.ConnectData
import typings.undiciTypes.dispatcherMod.Dispatcher.PipelineHandler
import typings.undiciTypes.dispatcherMod.Dispatcher.ResponseData
import typings.undiciTypes.dispatcherMod.Dispatcher.StreamData
import typings.undiciTypes.dispatcherMod.Dispatcher.StreamFactory
import typings.undiciTypes.dispatcherMod.Dispatcher.UpgradeData
import typings.undiciTypes.fetchMod.RequestInfo
import typings.undiciTypes.fetchMod.RequestInit
import typings.undiciTypes.mod.^
import typings.undiciTypes.undiciTypesStrings.failure
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}


inline def buildConnector(): connector = ^.asInstanceOf[js.Dynamic].applyDynamic("buildConnector")().asInstanceOf[connector]
inline def buildConnector(options: BuildOptions): connector = ^.asInstanceOf[js.Dynamic].applyDynamic("buildConnector")(options.asInstanceOf[js.Any]).asInstanceOf[connector]

inline def caches: CacheStorage = ^.asInstanceOf[js.Dynamic].selectDynamic("caches").asInstanceOf[CacheStorage]

/** Starts two-way communications with the requested resource. */
inline def connect(url: String): js.Promise[ConnectData] = ^.asInstanceOf[js.Dynamic].applyDynamic("connect")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[ConnectData]]
inline def connect(url: String, options: dispatcherdefaultundefineDispatcher): js.Promise[ConnectData] = (^.asInstanceOf[js.Dynamic].applyDynamic("connect")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[ConnectData]]
inline def connect(url: URL): js.Promise[ConnectData] = ^.asInstanceOf[js.Dynamic].applyDynamic("connect")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[ConnectData]]
inline def connect(url: URL, options: dispatcherdefaultundefineDispatcher): js.Promise[ConnectData] = (^.asInstanceOf[js.Dynamic].applyDynamic("connect")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[ConnectData]]
inline def connect(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any
): js.Promise[ConnectData] = ^.asInstanceOf[js.Dynamic].applyDynamic("connect")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[ConnectData]]
inline def connect(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefineDispatcher
): js.Promise[ConnectData] = (^.asInstanceOf[js.Dynamic].applyDynamic("connect")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[ConnectData]]

inline def deleteCookie(headers: typings.undiciTypes.fetchMod.Headers, name: String): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("deleteCookie")(headers.asInstanceOf[js.Any], name.asInstanceOf[js.Any])).asInstanceOf[Unit]
inline def deleteCookie(headers: typings.undiciTypes.fetchMod.Headers, name: String, attributes: Domain): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("deleteCookie")(headers.asInstanceOf[js.Any], name.asInstanceOf[js.Any], attributes.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def fetch(input: RequestInfo): js.Promise[typings.undiciTypes.fetchMod.Response] = ^.asInstanceOf[js.Dynamic].applyDynamic("fetch")(input.asInstanceOf[js.Any]).asInstanceOf[js.Promise[typings.undiciTypes.fetchMod.Response]]
inline def fetch(input: RequestInfo, init: RequestInit): js.Promise[typings.undiciTypes.fetchMod.Response] = (^.asInstanceOf[js.Dynamic].applyDynamic("fetch")(input.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[js.Promise[typings.undiciTypes.fetchMod.Response]]

inline def getCookies(headers: typings.undiciTypes.fetchMod.Headers): Record[String, String] = ^.asInstanceOf[js.Dynamic].applyDynamic("getCookies")(headers.asInstanceOf[js.Any]).asInstanceOf[Record[String, String]]

inline def getGlobalDispatcher(): typings.undiciTypes.dispatcherMod.default = ^.asInstanceOf[js.Dynamic].applyDynamic("getGlobalDispatcher")().asInstanceOf[typings.undiciTypes.dispatcherMod.default]

inline def getGlobalOrigin(): js.UndefOr[URL] = ^.asInstanceOf[js.Dynamic].applyDynamic("getGlobalOrigin")().asInstanceOf[js.UndefOr[URL]]

inline def getSetCookies(headers: typings.undiciTypes.fetchMod.Headers): js.Array[Cookie] = ^.asInstanceOf[js.Dynamic].applyDynamic("getSetCookies")(headers.asInstanceOf[js.Any]).asInstanceOf[js.Array[Cookie]]

inline def parseMIMEType(input: String): failure | MIMEType = ^.asInstanceOf[js.Dynamic].applyDynamic("parseMIMEType")(input.asInstanceOf[js.Any]).asInstanceOf[failure | MIMEType]

/** For easy use with `stream.pipeline`. */
inline def pipeline(url: String, options: dispatcherdefaultundefineBody, handler: PipelineHandler): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], handler.asInstanceOf[js.Any])).asInstanceOf[Any]
inline def pipeline(url: URL, options: dispatcherdefaultundefineBody, handler: PipelineHandler): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], handler.asInstanceOf[js.Any])).asInstanceOf[Any]
inline def pipeline(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefineBody,
  handler: PipelineHandler
): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], handler.asInstanceOf[js.Any])).asInstanceOf[Any]

/** Performs an HTTP request. */
inline def request(url: String): js.Promise[ResponseData] = ^.asInstanceOf[js.Dynamic].applyDynamic("request")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[ResponseData]]
inline def request(url: String, options: dispatcherdefaultundefine): js.Promise[ResponseData] = (^.asInstanceOf[js.Dynamic].applyDynamic("request")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[ResponseData]]
inline def request(url: URL): js.Promise[ResponseData] = ^.asInstanceOf[js.Dynamic].applyDynamic("request")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[ResponseData]]
inline def request(url: URL, options: dispatcherdefaultundefine): js.Promise[ResponseData] = (^.asInstanceOf[js.Dynamic].applyDynamic("request")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[ResponseData]]
inline def request(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any
): js.Promise[ResponseData] = ^.asInstanceOf[js.Dynamic].applyDynamic("request")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[ResponseData]]
inline def request(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefine
): js.Promise[ResponseData] = (^.asInstanceOf[js.Dynamic].applyDynamic("request")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[ResponseData]]

inline def serializeAMimeType(mimeType: MIMEType): String = ^.asInstanceOf[js.Dynamic].applyDynamic("serializeAMimeType")(mimeType.asInstanceOf[js.Any]).asInstanceOf[String]

inline def setCookie(headers: typings.undiciTypes.fetchMod.Headers, cookie: Cookie): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("setCookie")(headers.asInstanceOf[js.Any], cookie.asInstanceOf[js.Any])).asInstanceOf[Unit]

inline def setGlobalDispatcher[DispatcherImplementation /* <: typings.undiciTypes.dispatcherMod.default */](dispatcher: DispatcherImplementation): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setGlobalDispatcher")(dispatcher.asInstanceOf[js.Any]).asInstanceOf[Unit]

inline def setGlobalOrigin(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setGlobalOrigin")().asInstanceOf[Unit]
inline def setGlobalOrigin(origin: String): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setGlobalOrigin")(origin.asInstanceOf[js.Any]).asInstanceOf[Unit]
inline def setGlobalOrigin(origin: URL): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setGlobalOrigin")(origin.asInstanceOf[js.Any]).asInstanceOf[Unit]

/** A faster version of `request`. */
inline def stream(url: String, options: dispatcherdefaultundefineBlocking, factory: StreamFactory): js.Promise[StreamData] = (^.asInstanceOf[js.Dynamic].applyDynamic("stream")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], factory.asInstanceOf[js.Any])).asInstanceOf[js.Promise[StreamData]]
inline def stream(url: URL, options: dispatcherdefaultundefineBlocking, factory: StreamFactory): js.Promise[StreamData] = (^.asInstanceOf[js.Dynamic].applyDynamic("stream")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], factory.asInstanceOf[js.Any])).asInstanceOf[js.Promise[StreamData]]
inline def stream(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefineBlocking,
  factory: StreamFactory
): js.Promise[StreamData] = (^.asInstanceOf[js.Dynamic].applyDynamic("stream")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], factory.asInstanceOf[js.Any])).asInstanceOf[js.Promise[StreamData]]

/** Upgrade to a different protocol. */
inline def upgrade(url: String): js.Promise[UpgradeData] = ^.asInstanceOf[js.Dynamic].applyDynamic("upgrade")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[UpgradeData]]
inline def upgrade(url: String, options: dispatcherdefaultundefineHeaders): js.Promise[UpgradeData] = (^.asInstanceOf[js.Dynamic].applyDynamic("upgrade")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[UpgradeData]]
inline def upgrade(url: URL): js.Promise[UpgradeData] = ^.asInstanceOf[js.Dynamic].applyDynamic("upgrade")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[UpgradeData]]
inline def upgrade(url: URL, options: dispatcherdefaultundefineHeaders): js.Promise[UpgradeData] = (^.asInstanceOf[js.Dynamic].applyDynamic("upgrade")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[UpgradeData]]
inline def upgrade(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any
): js.Promise[UpgradeData] = ^.asInstanceOf[js.Dynamic].applyDynamic("upgrade")(url.asInstanceOf[js.Any]).asInstanceOf[js.Promise[UpgradeData]]
inline def upgrade(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefineHeaders
): js.Promise[UpgradeData] = (^.asInstanceOf[js.Dynamic].applyDynamic("upgrade")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[UpgradeData]]

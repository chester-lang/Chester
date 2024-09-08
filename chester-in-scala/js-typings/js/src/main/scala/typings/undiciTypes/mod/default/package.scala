package typings.undiciTypes.mod.default

import org.scalajs.dom.URL
import typings.undiciTypes.anon.dispatcherdefaultundefine
import typings.undiciTypes.anon.dispatcherdefaultundefineBlocking
import typings.undiciTypes.anon.dispatcherdefaultundefineBody
import typings.undiciTypes.anon.dispatcherdefaultundefineDispatcher
import typings.undiciTypes.anon.dispatcherdefaultundefineHeaders
import typings.undiciTypes.cacheMod.CacheStorage
import typings.undiciTypes.connectorMod.buildConnector.BuildOptions
import typings.undiciTypes.connectorMod.buildConnector.connector
import typings.undiciTypes.dispatcherMod.Dispatcher.ConnectData
import typings.undiciTypes.dispatcherMod.Dispatcher.DispatcherComposeInterceptor
import typings.undiciTypes.dispatcherMod.Dispatcher.PipelineHandler
import typings.undiciTypes.dispatcherMod.Dispatcher.ResponseData
import typings.undiciTypes.dispatcherMod.Dispatcher.StreamData
import typings.undiciTypes.dispatcherMod.Dispatcher.StreamFactory
import typings.undiciTypes.dispatcherMod.Dispatcher.UpgradeData
import typings.undiciTypes.fetchMod.RequestInfo
import typings.undiciTypes.fetchMod.RequestInit
import typings.undiciTypes.interceptorsMod.RedirectInterceptorOpts
import typings.undiciTypes.mod.default.^
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}


/* was `typeof imported_connector.default` */
inline def buildConnector(): connector = ^.asInstanceOf[js.Dynamic].applyDynamic("buildConnector")().asInstanceOf[connector]
/* was `typeof imported_connector.default` */
inline def buildConnector(options: BuildOptions): connector = ^.asInstanceOf[js.Dynamic].applyDynamic("buildConnector")(options.asInstanceOf[js.Any]).asInstanceOf[connector]

/* was `typeof imported_cache.caches` */
inline def caches: CacheStorage = ^.asInstanceOf[js.Dynamic].selectDynamic("caches").asInstanceOf[CacheStorage]

/** Starts two-way communications with the requested resource. */
/* was `typeof imported_api.connect` */
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

/* was `typeof imported_interceptors.createRedirectInterceptor` */
inline def createRedirectInterceptor(opts: RedirectInterceptorOpts): DispatcherComposeInterceptor = ^.asInstanceOf[js.Dynamic].applyDynamic("createRedirectInterceptor")(opts.asInstanceOf[js.Any]).asInstanceOf[DispatcherComposeInterceptor]

/* was `typeof imported_fetch.fetch` */
inline def fetch(input: RequestInfo): js.Promise[typings.undiciTypes.fetchMod.Response] = ^.asInstanceOf[js.Dynamic].applyDynamic("fetch")(input.asInstanceOf[js.Any]).asInstanceOf[js.Promise[typings.undiciTypes.fetchMod.Response]]
inline def fetch(input: RequestInfo, init: RequestInit): js.Promise[typings.undiciTypes.fetchMod.Response] = (^.asInstanceOf[js.Dynamic].applyDynamic("fetch")(input.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[js.Promise[typings.undiciTypes.fetchMod.Response]]

/* was `typeof imported_global-dispatcher.getGlobalDispatcher` */
inline def getGlobalDispatcher(): typings.undiciTypes.dispatcherMod.default = ^.asInstanceOf[js.Dynamic].applyDynamic("getGlobalDispatcher")().asInstanceOf[typings.undiciTypes.dispatcherMod.default]

/** For easy use with `stream.pipeline`. */
/* was `typeof imported_api.pipeline` */
inline def pipeline(url: String, options: dispatcherdefaultundefineBody, handler: PipelineHandler): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], handler.asInstanceOf[js.Any])).asInstanceOf[Any]
inline def pipeline(url: URL, options: dispatcherdefaultundefineBody, handler: PipelineHandler): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], handler.asInstanceOf[js.Any])).asInstanceOf[Any]
inline def pipeline(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefineBody,
  handler: PipelineHandler
): Any = (^.asInstanceOf[js.Dynamic].applyDynamic("pipeline")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], handler.asInstanceOf[js.Any])).asInstanceOf[Any]

/** Performs an HTTP request. */
/* was `typeof imported_api.request` */
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

/* was `typeof imported_global-dispatcher.setGlobalDispatcher` */
inline def setGlobalDispatcher[DispatcherImplementation /* <: typings.undiciTypes.dispatcherMod.default */](dispatcher: DispatcherImplementation): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("setGlobalDispatcher")(dispatcher.asInstanceOf[js.Any]).asInstanceOf[Unit]

/** A faster version of `request`. */
/* was `typeof imported_api.stream` */
inline def stream(url: String, options: dispatcherdefaultundefineBlocking, factory: StreamFactory): js.Promise[StreamData] = (^.asInstanceOf[js.Dynamic].applyDynamic("stream")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], factory.asInstanceOf[js.Any])).asInstanceOf[js.Promise[StreamData]]
inline def stream(url: URL, options: dispatcherdefaultundefineBlocking, factory: StreamFactory): js.Promise[StreamData] = (^.asInstanceOf[js.Dynamic].applyDynamic("stream")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], factory.asInstanceOf[js.Any])).asInstanceOf[js.Promise[StreamData]]
inline def stream(
  url: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify UrlObject */ Any,
  options: dispatcherdefaultundefineBlocking,
  factory: StreamFactory
): js.Promise[StreamData] = (^.asInstanceOf[js.Dynamic].applyDynamic("stream")(url.asInstanceOf[js.Any], options.asInstanceOf[js.Any], factory.asInstanceOf[js.Any])).asInstanceOf[js.Promise[StreamData]]

/** Upgrade to a different protocol. */
/* was `typeof imported_api.upgrade` */
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

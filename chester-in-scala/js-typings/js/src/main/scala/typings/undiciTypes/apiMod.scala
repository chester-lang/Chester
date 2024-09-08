package typings.undiciTypes

import org.scalajs.dom.URL
import typings.undiciTypes.anon.dispatcherdefaultundefine
import typings.undiciTypes.anon.dispatcherdefaultundefineBlocking
import typings.undiciTypes.anon.dispatcherdefaultundefineBody
import typings.undiciTypes.anon.dispatcherdefaultundefineDispatcher
import typings.undiciTypes.anon.dispatcherdefaultundefineHeaders
import typings.undiciTypes.dispatcherMod.Dispatcher.ConnectData
import typings.undiciTypes.dispatcherMod.Dispatcher.PipelineHandler
import typings.undiciTypes.dispatcherMod.Dispatcher.ResponseData
import typings.undiciTypes.dispatcherMod.Dispatcher.StreamData
import typings.undiciTypes.dispatcherMod.Dispatcher.StreamFactory
import typings.undiciTypes.dispatcherMod.Dispatcher.UpgradeData
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object apiMod {
  
  @JSImport("undici-types/api", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
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
}

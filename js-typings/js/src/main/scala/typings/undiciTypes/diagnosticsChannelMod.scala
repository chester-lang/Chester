package typings.undiciTypes

import org.scalajs.dom.URL
import typings.undiciTypes.connectorMod.buildConnector.Callback
import typings.undiciTypes.connectorMod.buildConnector.Options
import typings.undiciTypes.connectorMod.buildConnector.connector
import typings.undiciTypes.dispatcherMod.Dispatcher.HttpMethod
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object diagnosticsChannelMod {
  
  object DiagnosticsChannel {
    
    trait ClientBeforeConnectMessage extends StObject {
      
      var connectParams: ConnectParams
      
      def connector(options: Options, callback: Callback): Unit
      @JSName("connector")
      var connector_Original: Connector
    }
    object ClientBeforeConnectMessage {
      
      inline def apply(connectParams: ConnectParams, connector: (/* options */ Options, /* callback */ Callback) => Unit): ClientBeforeConnectMessage = {
        val __obj = js.Dynamic.literal(connectParams = connectParams.asInstanceOf[js.Any], connector = js.Any.fromFunction2(connector))
        __obj.asInstanceOf[ClientBeforeConnectMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: ClientBeforeConnectMessage] (val x: Self) extends AnyVal {
        
        inline def setConnectParams(value: ConnectParams): Self = StObject.set(x, "connectParams", value.asInstanceOf[js.Any])
        
        inline def setConnector(value: (/* options */ Options, /* callback */ Callback) => Unit): Self = StObject.set(x, "connector", js.Any.fromFunction2(value))
      }
    }
    
    trait ClientConnectErrorMessage extends StObject {
      
      var connectParams: ConnectParams
      
      def connector(options: Options, callback: Callback): Unit
      @JSName("connector")
      var connector_Original: Connector
      
      var error: Error
      
      var socket: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
    }
    object ClientConnectErrorMessage {
      
      inline def apply(
        connectParams: ConnectParams,
        connector: (/* options */ Options, /* callback */ Callback) => Unit,
        error: Error,
        socket: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
      ): ClientConnectErrorMessage = {
        val __obj = js.Dynamic.literal(connectParams = connectParams.asInstanceOf[js.Any], connector = js.Any.fromFunction2(connector), error = error.asInstanceOf[js.Any], socket = socket.asInstanceOf[js.Any])
        __obj.asInstanceOf[ClientConnectErrorMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: ClientConnectErrorMessage] (val x: Self) extends AnyVal {
        
        inline def setConnectParams(value: ConnectParams): Self = StObject.set(x, "connectParams", value.asInstanceOf[js.Any])
        
        inline def setConnector(value: (/* options */ Options, /* callback */ Callback) => Unit): Self = StObject.set(x, "connector", js.Any.fromFunction2(value))
        
        inline def setError(value: Error): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
        
        inline def setSocket(
          value: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
        ): Self = StObject.set(x, "socket", value.asInstanceOf[js.Any])
      }
    }
    
    trait ClientConnectedMessage extends StObject {
      
      var connectParams: ConnectParams
      
      def connector(options: Options, callback: Callback): Unit
      @JSName("connector")
      var connector_Original: Connector
      
      var socket: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
    }
    object ClientConnectedMessage {
      
      inline def apply(
        connectParams: ConnectParams,
        connector: (/* options */ Options, /* callback */ Callback) => Unit,
        socket: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
      ): ClientConnectedMessage = {
        val __obj = js.Dynamic.literal(connectParams = connectParams.asInstanceOf[js.Any], connector = js.Any.fromFunction2(connector), socket = socket.asInstanceOf[js.Any])
        __obj.asInstanceOf[ClientConnectedMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: ClientConnectedMessage] (val x: Self) extends AnyVal {
        
        inline def setConnectParams(value: ConnectParams): Self = StObject.set(x, "connectParams", value.asInstanceOf[js.Any])
        
        inline def setConnector(value: (/* options */ Options, /* callback */ Callback) => Unit): Self = StObject.set(x, "connector", js.Any.fromFunction2(value))
        
        inline def setSocket(
          value: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
        ): Self = StObject.set(x, "socket", value.asInstanceOf[js.Any])
      }
    }
    
    trait ClientSendHeadersMessage extends StObject {
      
      var headers: String
      
      var request: Request
      
      var socket: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
    }
    object ClientSendHeadersMessage {
      
      inline def apply(
        headers: String,
        request: Request,
        socket: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
      ): ClientSendHeadersMessage = {
        val __obj = js.Dynamic.literal(headers = headers.asInstanceOf[js.Any], request = request.asInstanceOf[js.Any], socket = socket.asInstanceOf[js.Any])
        __obj.asInstanceOf[ClientSendHeadersMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: ClientSendHeadersMessage] (val x: Self) extends AnyVal {
        
        inline def setHeaders(value: String): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
        
        inline def setRequest(value: Request): Self = StObject.set(x, "request", value.asInstanceOf[js.Any])
        
        inline def setSocket(
          value: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Socket */ Any
        ): Self = StObject.set(x, "socket", value.asInstanceOf[js.Any])
      }
    }
    
    trait ConnectParams extends StObject {
      
      var host: String
      
      var hostname: String
      
      var port: String
      
      var protocol: String
      
      var servername: String | Null
    }
    object ConnectParams {
      
      inline def apply(host: String, hostname: String, port: String, protocol: String): ConnectParams = {
        val __obj = js.Dynamic.literal(host = host.asInstanceOf[js.Any], hostname = hostname.asInstanceOf[js.Any], port = port.asInstanceOf[js.Any], protocol = protocol.asInstanceOf[js.Any], servername = null)
        __obj.asInstanceOf[ConnectParams]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: ConnectParams] (val x: Self) extends AnyVal {
        
        inline def setHost(value: String): Self = StObject.set(x, "host", value.asInstanceOf[js.Any])
        
        inline def setHostname(value: String): Self = StObject.set(x, "hostname", value.asInstanceOf[js.Any])
        
        inline def setPort(value: String): Self = StObject.set(x, "port", value.asInstanceOf[js.Any])
        
        inline def setProtocol(value: String): Self = StObject.set(x, "protocol", value.asInstanceOf[js.Any])
        
        inline def setServername(value: String): Self = StObject.set(x, "servername", value.asInstanceOf[js.Any])
        
        inline def setServernameNull: Self = StObject.set(x, "servername", null)
      }
    }
    
    type Connector = connector
    
    type Error = Any
    
    trait Request extends StObject {
      
      var completed: Boolean
      
      var headers: Any
      
      var method: js.UndefOr[HttpMethod] = js.undefined
      
      var origin: js.UndefOr[String | URL] = js.undefined
      
      var path: String
    }
    object Request {
      
      inline def apply(completed: Boolean, headers: Any, path: String): Request = {
        val __obj = js.Dynamic.literal(completed = completed.asInstanceOf[js.Any], headers = headers.asInstanceOf[js.Any], path = path.asInstanceOf[js.Any])
        __obj.asInstanceOf[Request]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: Request] (val x: Self) extends AnyVal {
        
        inline def setCompleted(value: Boolean): Self = StObject.set(x, "completed", value.asInstanceOf[js.Any])
        
        inline def setHeaders(value: Any): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
        
        inline def setMethod(value: HttpMethod): Self = StObject.set(x, "method", value.asInstanceOf[js.Any])
        
        inline def setMethodUndefined: Self = StObject.set(x, "method", js.undefined)
        
        inline def setOrigin(value: String | URL): Self = StObject.set(x, "origin", value.asInstanceOf[js.Any])
        
        inline def setOriginUndefined: Self = StObject.set(x, "origin", js.undefined)
        
        inline def setPath(value: String): Self = StObject.set(x, "path", value.asInstanceOf[js.Any])
      }
    }
    
    trait RequestBodySentMessage extends StObject {
      
      var request: Request
    }
    object RequestBodySentMessage {
      
      inline def apply(request: Request): RequestBodySentMessage = {
        val __obj = js.Dynamic.literal(request = request.asInstanceOf[js.Any])
        __obj.asInstanceOf[RequestBodySentMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RequestBodySentMessage] (val x: Self) extends AnyVal {
        
        inline def setRequest(value: Request): Self = StObject.set(x, "request", value.asInstanceOf[js.Any])
      }
    }
    
    trait RequestCreateMessage extends StObject {
      
      var request: Request
    }
    object RequestCreateMessage {
      
      inline def apply(request: Request): RequestCreateMessage = {
        val __obj = js.Dynamic.literal(request = request.asInstanceOf[js.Any])
        __obj.asInstanceOf[RequestCreateMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RequestCreateMessage] (val x: Self) extends AnyVal {
        
        inline def setRequest(value: Request): Self = StObject.set(x, "request", value.asInstanceOf[js.Any])
      }
    }
    
    trait RequestErrorMessage extends StObject {
      
      var error: Error
      
      var request: Request
    }
    object RequestErrorMessage {
      
      inline def apply(error: Error, request: Request): RequestErrorMessage = {
        val __obj = js.Dynamic.literal(error = error.asInstanceOf[js.Any], request = request.asInstanceOf[js.Any])
        __obj.asInstanceOf[RequestErrorMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RequestErrorMessage] (val x: Self) extends AnyVal {
        
        inline def setError(value: Error): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
        
        inline def setRequest(value: Request): Self = StObject.set(x, "request", value.asInstanceOf[js.Any])
      }
    }
    
    trait RequestHeadersMessage extends StObject {
      
      var request: Request
      
      var response: Response
    }
    object RequestHeadersMessage {
      
      inline def apply(request: Request, response: Response): RequestHeadersMessage = {
        val __obj = js.Dynamic.literal(request = request.asInstanceOf[js.Any], response = response.asInstanceOf[js.Any])
        __obj.asInstanceOf[RequestHeadersMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RequestHeadersMessage] (val x: Self) extends AnyVal {
        
        inline def setRequest(value: Request): Self = StObject.set(x, "request", value.asInstanceOf[js.Any])
        
        inline def setResponse(value: Response): Self = StObject.set(x, "response", value.asInstanceOf[js.Any])
      }
    }
    
    trait RequestTrailersMessage extends StObject {
      
      var request: Request
      
      var trailers: js.Array[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
          ]
    }
    object RequestTrailersMessage {
      
      inline def apply(
        request: Request,
        trailers: js.Array[
              /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
            ]
      ): RequestTrailersMessage = {
        val __obj = js.Dynamic.literal(request = request.asInstanceOf[js.Any], trailers = trailers.asInstanceOf[js.Any])
        __obj.asInstanceOf[RequestTrailersMessage]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RequestTrailersMessage] (val x: Self) extends AnyVal {
        
        inline def setRequest(value: Request): Self = StObject.set(x, "request", value.asInstanceOf[js.Any])
        
        inline def setTrailers(
          value: js.Array[
                  /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
                ]
        ): Self = StObject.set(x, "trailers", value.asInstanceOf[js.Any])
        
        inline def setTrailersVarargs(
          value: (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any)*
        ): Self = StObject.set(x, "trailers", js.Array(value*))
      }
    }
    
    trait Response extends StObject {
      
      var headers: js.Array[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
          ]
      
      var statusCode: Double
      
      var statusText: String
    }
    object Response {
      
      inline def apply(
        headers: js.Array[
              /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
            ],
        statusCode: Double,
        statusText: String
      ): Response = {
        val __obj = js.Dynamic.literal(headers = headers.asInstanceOf[js.Any], statusCode = statusCode.asInstanceOf[js.Any], statusText = statusText.asInstanceOf[js.Any])
        __obj.asInstanceOf[Response]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: Response] (val x: Self) extends AnyVal {
        
        inline def setHeaders(
          value: js.Array[
                  /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
                ]
        ): Self = StObject.set(x, "headers", value.asInstanceOf[js.Any])
        
        inline def setHeadersVarargs(
          value: (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any)*
        ): Self = StObject.set(x, "headers", js.Array(value*))
        
        inline def setStatusCode(value: Double): Self = StObject.set(x, "statusCode", value.asInstanceOf[js.Any])
        
        inline def setStatusText(value: String): Self = StObject.set(x, "statusText", value.asInstanceOf[js.Any])
      }
    }
  }
}

package typings.node.anon

import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable4
import typings.node.http2Mod.ClientHttp2Session
import typings.node.http2Mod.ClientSessionOptions
import typings.node.http2Mod.Http2SecureServer
import typings.node.http2Mod.Http2Server
import typings.node.http2Mod.Http2ServerRequest
import typings.node.http2Mod.Http2ServerResponse
import typings.node.http2Mod.IncomingHttpHeaders
import typings.node.http2Mod.SecureClientSessionOptions
import typings.node.http2Mod.SecureServerOptions
import typings.node.http2Mod.ServerHttp2Session
import typings.node.http2Mod.ServerHttp2Stream
import typings.node.http2Mod.ServerOptions
import typings.node.http2Mod.Settings
import typings.node.nodeColonnetMod.Socket
import typings.node.nodeColonstreamMod.Duplex
import typings.node.nodeColontlsMod.TLSSocket
import typings.node.nodeColonurlMod.URL
import typings.node.streamMod.ReadableOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedHttp2 extends StObject {
  
  var Http2ServerRequest: Instantiable4[
    /* stream */ ServerHttp2Stream, 
    /* headers */ IncomingHttpHeaders, 
    /* options */ ReadableOptions, 
    /* rawHeaders */ js.Array[String], 
    typings.node.http2Mod.Http2ServerRequest
  ] = js.native
  
  var Http2ServerResponse: Instantiable1[/* stream */ ServerHttp2Stream, typings.node.http2Mod.Http2ServerResponse] = js.native
  
  def connect(authority: String): ClientHttp2Session = js.native
  def connect(
    authority: String,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(
    authority: String,
    options: Unit,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(authority: String, options: ClientSessionOptions): ClientHttp2Session = js.native
  def connect(
    authority: String,
    options: ClientSessionOptions,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(authority: String, options: SecureClientSessionOptions): ClientHttp2Session = js.native
  def connect(
    authority: String,
    options: SecureClientSessionOptions,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(authority: URL): ClientHttp2Session = js.native
  def connect(
    authority: URL,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(
    authority: URL,
    options: Unit,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(authority: URL, options: ClientSessionOptions): ClientHttp2Session = js.native
  def connect(
    authority: URL,
    options: ClientSessionOptions,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  def connect(authority: URL, options: SecureClientSessionOptions): ClientHttp2Session = js.native
  def connect(
    authority: URL,
    options: SecureClientSessionOptions,
    listener: js.Function2[/* session */ ClientHttp2Session, /* socket */ Socket | TLSSocket, Unit]
  ): ClientHttp2Session = js.native
  
  val constants: TypeofconstantsDEFAULTSETTINGSENABLEPUSH = js.native
  
  def createSecureServer(): Http2SecureServer = js.native
  def createSecureServer(
    onRequestHandler: js.Function2[/* request */ Http2ServerRequest, /* response */ Http2ServerResponse, Unit]
  ): Http2SecureServer = js.native
  def createSecureServer(options: SecureServerOptions): Http2SecureServer = js.native
  def createSecureServer(
    options: SecureServerOptions,
    onRequestHandler: js.Function2[/* request */ Http2ServerRequest, /* response */ Http2ServerResponse, Unit]
  ): Http2SecureServer = js.native
  
  def createServer(): Http2Server = js.native
  def createServer(
    onRequestHandler: js.Function2[/* request */ Http2ServerRequest, /* response */ Http2ServerResponse, Unit]
  ): Http2Server = js.native
  def createServer(options: ServerOptions): Http2Server = js.native
  def createServer(
    options: ServerOptions,
    onRequestHandler: js.Function2[/* request */ Http2ServerRequest, /* response */ Http2ServerResponse, Unit]
  ): Http2Server = js.native
  
  def getDefaultSettings(): Settings = js.native
  
  def getPackedSettings(settings: Settings): typings.node.bufferMod.global.Buffer = js.native
  
  def getUnpackedSettings(buf: js.typedarray.Uint8Array): Settings = js.native
  
  def performServerHandshake(socket: Duplex): ServerHttp2Session = js.native
  def performServerHandshake(socket: Duplex, options: ServerOptions): ServerHttp2Session = js.native
  
  val sensitiveHeaders: js.Symbol = js.native
}

package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.netMod.NetConnectOpts
import typings.node.netMod.Server
import typings.node.netMod.ServerOpts
import typings.node.netMod.Socket
import typings.node.netMod.SocketAddressInitOptions
import typings.node.netMod.SocketConstructorOpts
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNet extends StObject {
  
  /**
    * The `BlockList` object can be used with some network APIs to specify rules for
    * disabling inbound or outbound access to specific IP addresses, IP ranges, or
    * IP subnets.
    * @since v15.0.0, v14.18.0
    */
  var BlockList: Instantiable0[typings.node.netMod.BlockList] = js.native
  
  /**
    * This class is used to create a TCP or `IPC` server.
    * @since v0.1.90
    */
  var Server: Instantiable1[
    /* connectionListener */ js.UndefOr[js.Function1[/* socket */ Socket, Unit]], 
    typings.node.netMod.Server
  ] = js.native
  
  /**
    * This class is an abstraction of a TCP socket or a streaming `IPC` endpoint
    * (uses named pipes on Windows, and Unix domain sockets otherwise). It is also
    * an `EventEmitter`.
    *
    * A `net.Socket` can be created by the user and used directly to interact with
    * a server. For example, it is returned by {@link createConnection},
    * so the user can use it to talk to the server.
    *
    * It can also be created by Node.js and passed to the user when a connection
    * is received. For example, it is passed to the listeners of a `'connection'` event emitted on a {@link Server}, so the user can use
    * it to interact with the client.
    * @since v0.3.4
    */
  var Socket: Instantiable1[/* options */ js.UndefOr[SocketConstructorOpts], typings.node.netMod.Socket] = js.native
  
  /**
    * @since v15.14.0, v14.18.0
    */
  var SocketAddress: Instantiable1[/* options */ SocketAddressInitOptions, typings.node.netMod.SocketAddress] = js.native
  
  /**
    * Aliases to {@link createConnection}.
    *
    * Possible signatures:
    *
    * * {@link connect}
    * * {@link connect} for `IPC` connections.
    * * {@link connect} for TCP connections.
    */
  def connect(options: NetConnectOpts): Socket = js.native
  def connect(options: NetConnectOpts, connectionListener: js.Function0[Unit]): Socket = js.native
  def connect(path: String): Socket = js.native
  def connect(path: String, connectionListener: js.Function0[Unit]): Socket = js.native
  def connect(port: Double): Socket = js.native
  def connect(port: Double, host: String): Socket = js.native
  def connect(port: Double, host: String, connectionListener: js.Function0[Unit]): Socket = js.native
  def connect(port: Double, host: Unit, connectionListener: js.Function0[Unit]): Socket = js.native
  
  /**
    * A factory function, which creates a new {@link Socket},
    * immediately initiates connection with `socket.connect()`,
    * then returns the `net.Socket` that starts the connection.
    *
    * When the connection is established, a `'connect'` event will be emitted
    * on the returned socket. The last parameter `connectListener`, if supplied,
    * will be added as a listener for the `'connect'` event **once**.
    *
    * Possible signatures:
    *
    * * {@link createConnection}
    * * {@link createConnection} for `IPC` connections.
    * * {@link createConnection} for TCP connections.
    *
    * The {@link connect} function is an alias to this function.
    */
  def createConnection(options: NetConnectOpts): Socket = js.native
  def createConnection(options: NetConnectOpts, connectionListener: js.Function0[Unit]): Socket = js.native
  def createConnection(path: String): Socket = js.native
  def createConnection(path: String, connectionListener: js.Function0[Unit]): Socket = js.native
  def createConnection(port: Double): Socket = js.native
  def createConnection(port: Double, host: String): Socket = js.native
  def createConnection(port: Double, host: String, connectionListener: js.Function0[Unit]): Socket = js.native
  def createConnection(port: Double, host: Unit, connectionListener: js.Function0[Unit]): Socket = js.native
  
  /**
    * Creates a new TCP or `IPC` server.
    *
    * If `allowHalfOpen` is set to `true`, when the other end of the socket
    * signals the end of transmission, the server will only send back the end of
    * transmission when `socket.end()` is explicitly called. For example, in the
    * context of TCP, when a FIN packed is received, a FIN packed is sent
    * back only when `socket.end()` is explicitly called. Until then the
    * connection is half-closed (non-readable but still writable). See `'end'` event and [RFC 1122](https://tools.ietf.org/html/rfc1122) (section 4.2.2.13) for more information.
    *
    * If `pauseOnConnect` is set to `true`, then the socket associated with each
    * incoming connection will be paused, and no data will be read from its handle.
    * This allows connections to be passed between processes without any data being
    * read by the original process. To begin reading data from a paused socket, call `socket.resume()`.
    *
    * The server can be a TCP server or an `IPC` server, depending on what it `listen()` to.
    *
    * Here is an example of a TCP echo server which listens for connections
    * on port 8124:
    *
    * ```js
    * const net = require('node:net');
    * const server = net.createServer((c) => {
    *   // 'connection' listener.
    *   console.log('client connected');
    *   c.on('end', () => {
    *     console.log('client disconnected');
    *   });
    *   c.write('hello\r\n');
    *   c.pipe(c);
    * });
    * server.on('error', (err) => {
    *   throw err;
    * });
    * server.listen(8124, () => {
    *   console.log('server bound');
    * });
    * ```
    *
    * Test this by using `telnet`:
    *
    * ```bash
    * telnet localhost 8124
    * ```
    *
    * To listen on the socket `/tmp/echo.sock`:
    *
    * ```js
    * server.listen('/tmp/echo.sock', () => {
    *   console.log('server bound');
    * });
    * ```
    *
    * Use `nc` to connect to a Unix domain socket server:
    *
    * ```bash
    * nc -U /tmp/echo.sock
    * ```
    * @since v0.5.0
    * @param connectionListener Automatically set as a listener for the {@link 'connection'} event.
    */
  def createServer(): Server = js.native
  def createServer(connectionListener: js.Function1[/* socket */ Socket, Unit]): Server = js.native
  def createServer(options: Unit, connectionListener: js.Function1[/* socket */ Socket, Unit]): Server = js.native
  def createServer(options: ServerOpts): Server = js.native
  def createServer(options: ServerOpts, connectionListener: js.Function1[/* socket */ Socket, Unit]): Server = js.native
  
  /**
    * Gets the current default value of the `autoSelectFamily` option of `socket.connect(options)`.
    * The initial default value is `true`, unless the command line option`--no-network-family-autoselection` is provided.
    * @since v19.4.0
    */
  def getDefaultAutoSelectFamily(): Boolean = js.native
  
  /**
    * Gets the current default value of the `autoSelectFamilyAttemptTimeout` option of `socket.connect(options)`.
    * The initial default value is `250` or the value specified via the command line option `--network-family-autoselection-attempt-timeout`.
    * @returns The current default value of the `autoSelectFamilyAttemptTimeout` option.
    * @since v19.8.0, v18.8.0
    */
  def getDefaultAutoSelectFamilyAttemptTimeout(): Double = js.native
  
  /**
    * Returns `6` if `input` is an IPv6 address. Returns `4` if `input` is an IPv4
    * address in [dot-decimal notation](https://en.wikipedia.org/wiki/Dot-decimal_notation) with no leading zeroes. Otherwise, returns`0`.
    *
    * ```js
    * net.isIP('::1'); // returns 6
    * net.isIP('127.0.0.1'); // returns 4
    * net.isIP('127.000.000.001'); // returns 0
    * net.isIP('127.0.0.1/24'); // returns 0
    * net.isIP('fhqwhgads'); // returns 0
    * ```
    * @since v0.3.0
    */
  def isIP(input: String): Double = js.native
  
  /**
    * Returns `true` if `input` is an IPv4 address in [dot-decimal notation](https://en.wikipedia.org/wiki/Dot-decimal_notation) with no
    * leading zeroes. Otherwise, returns `false`.
    *
    * ```js
    * net.isIPv4('127.0.0.1'); // returns true
    * net.isIPv4('127.000.000.001'); // returns false
    * net.isIPv4('127.0.0.1/24'); // returns false
    * net.isIPv4('fhqwhgads'); // returns false
    * ```
    * @since v0.3.0
    */
  def isIPv4(input: String): Boolean = js.native
  
  /**
    * Returns `true` if `input` is an IPv6 address. Otherwise, returns `false`.
    *
    * ```js
    * net.isIPv6('::1'); // returns true
    * net.isIPv6('fhqwhgads'); // returns false
    * ```
    * @since v0.3.0
    */
  def isIPv6(input: String): Boolean = js.native
  
  /**
    * Sets the default value of the `autoSelectFamily` option of `socket.connect(options)`.
    * @since v19.4.0
    */
  def setDefaultAutoSelectFamily(value: Boolean): Unit = js.native
  
  /**
    * Sets the default value of the `autoSelectFamilyAttemptTimeout` option of `socket.connect(options)`.
    * @param value The new default value, which must be a positive number. If the number is less than `10`, the value `10` is used instead. The initial default value is `250` or the value specified via the command line
    * option `--network-family-autoselection-attempt-timeout`.
    * @since v19.8.0, v18.8.0
    */
  def setDefaultAutoSelectFamilyAttemptTimeout(value: Double): Unit = js.native
}

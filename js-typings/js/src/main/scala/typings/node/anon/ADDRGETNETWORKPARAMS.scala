package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import org.scalajs.dom.URL
import typings.node.bufferMod.global.BufferEncoding
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
import typings.node.fsMod.BigIntStats
import typings.node.fsMod.BigIntStatsFs
import typings.node.fsMod.BufferEncodingOption
import typings.node.fsMod.CopyOptions
import typings.node.fsMod.GlobOptions
import typings.node.fsMod.GlobOptionsWithFileTypes
import typings.node.fsMod.GlobOptionsWithoutFileTypes
import typings.node.fsMod.MakeDirectoryOptions
import typings.node.fsMod.ObjectEncodingOptions
import typings.node.fsMod.OpenDirOptions
import typings.node.fsMod.PathLike
import typings.node.fsMod.RmDirOptions
import typings.node.fsMod.RmOptions
import typings.node.fsMod.StatFsOptions
import typings.node.fsMod.StatOptions
import typings.node.fsMod.TimeLike
import typings.node.fsMod.WatchOptions
import typings.node.fsPromisesMod.FileHandle
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import typings.node.globalsMod.global.NodeJS.ReadWriteStream
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.nodeColonfsMod.Dir
import typings.node.nodeColonfsMod.Dirent
import typings.node.nodeColonfsMod.Stats
import typings.node.nodeColonfsMod.StatsFs
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
import typings.node.nodeStrings.buffer_
import typings.node.nodeStrings.ipv4first
import typings.node.nodeStrings.ipv6first
import typings.node.nodeStrings.verbatim
import typings.node.readlineMod.AsyncCompleter
import typings.node.readlineMod.Completer
import typings.node.readlineMod.ReadLineOptions
import typings.node.readlinePromisesMod.Interface
import typings.node.streamMod.FinishedOptions
import typings.node.streamMod.PipelineDestination
import typings.node.streamMod.PipelineOptions
import typings.node.streamMod.PipelineSource
import typings.node.streamMod.PipelineTransform
import typings.node.timersMod.TimerOptions
import typings.node.timersPromisesMod.Scheduler_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait ADDRGETNETWORKPARAMS extends StObject {
  
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
  
  /**
    * Instances of the `readlinePromises.Interface` class are constructed using the `readlinePromises.createInterface()` method. Every instance is associated with a
    * single `input` `Readable` stream and a single `output` `Writable` stream.
    * The `output` stream is used to print prompts for user input that arrives on,
    * and is read from, the `input` stream.
    * @since v17.0.0
    */
  var Interface: Instantiable0[typings.node.nodeColonreadlinePromisesMod.Interface] = js.native
  
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
    * @since v17.0.0
    */
  var Readline: Instantiable2[
    /* stream */ WritableStream, 
    /* options */ js.UndefOr[AutoCommit], 
    typings.node.nodeColonreadlinePromisesMod.Readline
  ] = js.native
  
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
  var Resolver: Instantiable1[/* options */ js.UndefOr[ResolverOptions], typings.node.dnsPromisesMod.Resolver] = js.native
  
  val SERVFAIL: ESERVFAIL = js.native
  
  val TIMEOUT: ETIMEOUT = js.native
  
  /**
    * Tests a user's permissions for the file or directory specified by `path`.
    * The `mode` argument is an optional integer that specifies the accessibility
    * checks to be performed. `mode` should be either the value `fs.constants.F_OK` or a mask consisting of the bitwise OR of any of `fs.constants.R_OK`, `fs.constants.W_OK`, and `fs.constants.X_OK`
    * (e.g.`fs.constants.W_OK | fs.constants.R_OK`). Check `File access constants` for
    * possible values of `mode`.
    *
    * If the accessibility check is successful, the promise is fulfilled with no
    * value. If any of the accessibility checks fail, the promise is rejected
    * with an [Error](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Error) object. The following example checks if the file`/etc/passwd` can be read and
    * written by the current process.
    *
    * ```js
    * import { access, constants } from 'node:fs/promises';
    *
    * try {
    *   await access('/etc/passwd', constants.R_OK | constants.W_OK);
    *   console.log('can access');
    * } catch {
    *   console.error('cannot access');
    * }
    * ```
    *
    * Using `fsPromises.access()` to check for the accessibility of a file before
    * calling `fsPromises.open()` is not recommended. Doing so introduces a race
    * condition, since other processes may change the file's state between the two
    * calls. Instead, user code should open/read/write the file directly and handle
    * the error raised if the file is not accessible.
    * @since v10.0.0
    * @param [mode=fs.constants.F_OK]
    * @return Fulfills with `undefined` upon success.
    */
  def access(path: PathLike): js.Promise[Unit] = js.native
  def access(path: PathLike, mode: Double): js.Promise[Unit] = js.native
  
  /**
    * Asynchronously append data to a file, creating the file if it does not yet
    * exist. `data` can be a string or a `Buffer`.
    *
    * If `options` is a string, then it specifies the `encoding`.
    *
    * The `mode` option only affects the newly created file. See `fs.open()` for more details.
    *
    * The `path` may be specified as a `FileHandle` that has been opened
    * for appending (using `fsPromises.open()`).
    * @since v10.0.0
    * @param path filename or {FileHandle}
    * @return Fulfills with `undefined` upon success.
    */
  def appendFile(path: PathLike, data: String): js.Promise[Unit] = js.native
  def appendFile(path: PathLike, data: String, options: ObjectEncodingOptionsFlag_): js.Promise[Unit] = js.native
  def appendFile(path: PathLike, data: String, options: BufferEncoding): js.Promise[Unit] = js.native
  def appendFile(path: PathLike, data: js.typedarray.Uint8Array): js.Promise[Unit] = js.native
  def appendFile(path: PathLike, data: js.typedarray.Uint8Array, options: ObjectEncodingOptionsFlag_): js.Promise[Unit] = js.native
  def appendFile(path: PathLike, data: js.typedarray.Uint8Array, options: BufferEncoding): js.Promise[Unit] = js.native
  def appendFile(path: FileHandle, data: String): js.Promise[Unit] = js.native
  def appendFile(path: FileHandle, data: String, options: ObjectEncodingOptionsFlag_): js.Promise[Unit] = js.native
  def appendFile(path: FileHandle, data: String, options: BufferEncoding): js.Promise[Unit] = js.native
  def appendFile(path: FileHandle, data: js.typedarray.Uint8Array): js.Promise[Unit] = js.native
  def appendFile(path: FileHandle, data: js.typedarray.Uint8Array, options: ObjectEncodingOptionsFlag_): js.Promise[Unit] = js.native
  def appendFile(path: FileHandle, data: js.typedarray.Uint8Array, options: BufferEncoding): js.Promise[Unit] = js.native
  
  /**
    * Changes the permissions of a file.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def chmod(path: PathLike, mode: typings.node.fsMod.Mode): js.Promise[Unit] = js.native
  
  /**
    * Changes the ownership of a file.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def chown(path: PathLike, uid: Double, gid: Double): js.Promise[Unit] = js.native
  
  /**
    * Asynchronously copies `src` to `dest`. By default, `dest` is overwritten if it
    * already exists.
    *
    * No guarantees are made about the atomicity of the copy operation. If an
    * error occurs after the destination file has been opened for writing, an attempt
    * will be made to remove the destination.
    *
    * ```js
    * import { copyFile, constants } from 'node:fs/promises';
    *
    * try {
    *   await copyFile('source.txt', 'destination.txt');
    *   console.log('source.txt was copied to destination.txt');
    * } catch {
    *   console.error('The file could not be copied');
    * }
    *
    * // By using COPYFILE_EXCL, the operation will fail if destination.txt exists.
    * try {
    *   await copyFile('source.txt', 'destination.txt', constants.COPYFILE_EXCL);
    *   console.log('source.txt was copied to destination.txt');
    * } catch {
    *   console.error('The file could not be copied');
    * }
    * ```
    * @since v10.0.0
    * @param src source filename to copy
    * @param dest destination filename of the copy operation
    * @param [mode=0] Optional modifiers that specify the behavior of the copy operation. It is possible to create a mask consisting of the bitwise OR of two or more values (e.g.
    * `fs.constants.COPYFILE_EXCL | fs.constants.COPYFILE_FICLONE`)
    * @return Fulfills with `undefined` upon success.
    */
  def copyFile(src: PathLike, dest: PathLike): js.Promise[Unit] = js.native
  def copyFile(src: PathLike, dest: PathLike, mode: Double): js.Promise[Unit] = js.native
  
  /**
    * Asynchronously copies the entire directory structure from `src` to `dest`,
    * including subdirectories and files.
    *
    * When copying a directory to another directory, globs are not supported and
    * behavior is similar to `cp dir1/ dir2/`.
    * @since v16.7.0
    * @experimental
    * @param src source path to copy.
    * @param dest destination path to copy to.
    * @return Fulfills with `undefined` upon success.
    */
  def cp(source: String, destination: String): js.Promise[Unit] = js.native
  def cp(source: String, destination: String, opts: CopyOptions): js.Promise[Unit] = js.native
  def cp(source: String, destination: URL): js.Promise[Unit] = js.native
  def cp(source: String, destination: URL, opts: CopyOptions): js.Promise[Unit] = js.native
  def cp(source: URL, destination: String): js.Promise[Unit] = js.native
  def cp(source: URL, destination: String, opts: CopyOptions): js.Promise[Unit] = js.native
  def cp(source: URL, destination: URL): js.Promise[Unit] = js.native
  def cp(source: URL, destination: URL, opts: CopyOptions): js.Promise[Unit] = js.native
  
  /**
    * The `readlinePromises.createInterface()` method creates a new `readlinePromises.Interface` instance.
    *
    * ```js
    * const readlinePromises = require('node:readline/promises');
    * const rl = readlinePromises.createInterface({
    *   input: process.stdin,
    *   output: process.stdout,
    * });
    * ```
    *
    * Once the `readlinePromises.Interface` instance is created, the most common case
    * is to listen for the `'line'` event:
    *
    * ```js
    * rl.on('line', (line) => {
    *   console.log(`Received: ${line}`);
    * });
    * ```
    *
    * If `terminal` is `true` for this instance then the `output` stream will get
    * the best compatibility if it defines an `output.columns` property and emits
    * a `'resize'` event on the `output` if or when the columns ever change
    * (`process.stdout` does this automatically when it is a TTY).
    * @since v17.0.0
    */
  def createInterface(input: ReadableStream): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: Unit, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: AsyncCompleter): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: AsyncCompleter, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: Completer): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: Completer, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: Unit, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: AsyncCompleter): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: AsyncCompleter, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: Completer): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: Completer, terminal: Boolean): Interface = js.native
  def createInterface(options: ReadLineOptions): Interface = js.native
  
  def finished(stream: ReadWriteStream): js.Promise[Unit] = js.native
  def finished(stream: ReadWriteStream, options: FinishedOptions): js.Promise[Unit] = js.native
  def finished(stream: ReadableStream): js.Promise[Unit] = js.native
  def finished(stream: ReadableStream, options: FinishedOptions): js.Promise[Unit] = js.native
  def finished(stream: WritableStream): js.Promise[Unit] = js.native
  def finished(stream: WritableStream, options: FinishedOptions): js.Promise[Unit] = js.native
  
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
  
  /**
    * Retrieves the files matching the specified pattern.
    */
  def glob(pattern: String): Any = js.native
  def glob(pattern: String, opt: GlobOptions): /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterableIterator<Dirent> */ Any = js.native
  def glob(pattern: String, opt: GlobOptionsWithFileTypes): Any = js.native
  def glob(pattern: String, opt: GlobOptionsWithoutFileTypes): Any = js.native
  def glob(pattern: js.Array[String]): Any = js.native
  def glob(pattern: js.Array[String], opt: GlobOptions): /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterableIterator<Dirent> */ Any = js.native
  def glob(pattern: js.Array[String], opt: GlobOptionsWithFileTypes): Any = js.native
  def glob(pattern: js.Array[String], opt: GlobOptionsWithoutFileTypes): Any = js.native
  
  /**
    * Changes the permissions on a symbolic link.
    *
    * This method is only implemented on macOS.
    * @deprecated Since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def lchmod(path: PathLike, mode: typings.node.fsMod.Mode): js.Promise[Unit] = js.native
  
  /**
    * Changes the ownership on a symbolic link.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def lchown(path: PathLike, uid: Double, gid: Double): js.Promise[Unit] = js.native
  
  /**
    * Creates a new link from the `existingPath` to the `newPath`. See the POSIX [`link(2)`](http://man7.org/linux/man-pages/man2/link.2.html) documentation for more detail.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def link(existingPath: PathLike, newPath: PathLike): js.Promise[Unit] = js.native
  
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
    * Equivalent to `fsPromises.stat()` unless `path` refers to a symbolic link,
    * in which case the link itself is stat-ed, not the file that it refers to.
    * Refer to the POSIX [`lstat(2)`](http://man7.org/linux/man-pages/man2/lstat.2.html) document for more detail.
    * @since v10.0.0
    * @return Fulfills with the {fs.Stats} object for the given symbolic link `path`.
    */
  def lstat(path: PathLike): js.Promise[Stats] = js.native
  def lstat(path: PathLike, opts: StatOptionsbigintfalseund): js.Promise[Stats] = js.native
  def lstat(path: PathLike, opts: StatOptionsbiginttrue): js.Promise[BigIntStats] = js.native
  def lstat(path: PathLike, opts: StatOptions): js.Promise[Stats | BigIntStats] = js.native
  
  /**
    * Changes the access and modification times of a file in the same way as `fsPromises.utimes()`, with the difference that if the path refers to a
    * symbolic link, then the link is not dereferenced: instead, the timestamps of
    * the symbolic link itself are changed.
    * @since v14.5.0, v12.19.0
    * @return Fulfills with `undefined` upon success.
    */
  def lutimes(path: PathLike, atime: TimeLike, mtime: TimeLike): js.Promise[Unit] = js.native
  
  /**
    * Asynchronous mkdir(2) - create a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options Either the file mode, or an object optionally specifying the file mode and whether parent folders
    * should be created. If a string is passed, it is parsed as an octal integer. If not specified, defaults to `0o777`.
    */
  def mkdir(path: PathLike): js.Promise[Unit] = js.native
  /**
    * Asynchronously creates a directory.
    *
    * The optional `options` argument can be an integer specifying `mode` (permission
    * and sticky bits), or an object with a `mode` property and a `recursive` property indicating whether parent directories should be created. Calling `fsPromises.mkdir()` when `path` is a directory
    * that exists results in a
    * rejection only when `recursive` is false.
    *
    * ```js
    * import { mkdir } from 'node:fs/promises';
    *
    * try {
    *   const projectFolder = new URL('./test/project/', import.meta.url);
    *   const createDir = await mkdir(projectFolder, { recursive: true });
    *
    *   console.log(`created ${createDir}`);
    * } catch (err) {
    *   console.error(err.message);
    * }
    * ```
    * @since v10.0.0
    * @return Upon success, fulfills with `undefined` if `recursive` is `false`, or the first directory path created if `recursive` is `true`.
    */
  def mkdir(path: PathLike, options: MakeDirectoryOptionsrecur): js.Promise[js.UndefOr[String]] = js.native
  def mkdir(path: PathLike, options: MakeDirectoryOptionsrecurMode): js.Promise[Unit] = js.native
  def mkdir(path: PathLike, options: MakeDirectoryOptions): js.Promise[js.UndefOr[String]] = js.native
  def mkdir(path: PathLike, options: typings.node.fsMod.Mode): js.Promise[Unit] = js.native
  
  /**
    * Creates a unique temporary directory. A unique directory name is generated by
    * appending six random characters to the end of the provided `prefix`. Due to
    * platform inconsistencies, avoid trailing `X` characters in `prefix`. Some
    * platforms, notably the BSDs, can return more than six random characters, and
    * replace trailing `X` characters in `prefix` with random characters.
    *
    * The optional `options` argument can be a string specifying an encoding, or an
    * object with an `encoding` property specifying the character encoding to use.
    *
    * ```js
    * import { mkdtemp } from 'node:fs/promises';
    * import { join } from 'node:path';
    * import { tmpdir } from 'node:os';
    *
    * try {
    *   await mkdtemp(join(tmpdir(), 'foo-'));
    * } catch (err) {
    *   console.error(err);
    * }
    * ```
    *
    * The `fsPromises.mkdtemp()` method will append the six randomly selected
    * characters directly to the `prefix` string. For instance, given a directory `/tmp`, if the intention is to create a temporary directory _within_ `/tmp`, the `prefix` must end with a trailing
    * platform-specific path separator
    * (`require('node:path').sep`).
    * @since v10.0.0
    * @return Fulfills with a string containing the file system path of the newly created temporary directory.
    */
  /**
    * Asynchronously creates a unique temporary directory.
    * Generates six random characters to be appended behind a required `prefix` to create a unique temporary directory.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def mkdtemp(prefix: String): js.Promise[String] = js.native
  def mkdtemp(prefix: String, options: BufferEncoding): js.Promise[String] = js.native
  /**
    * Asynchronously creates a unique temporary directory.
    * Generates six random characters to be appended behind a required `prefix` to create a unique temporary directory.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def mkdtemp(prefix: String, options: BufferEncodingOption): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def mkdtemp(prefix: String, options: ObjectEncodingOptions): js.Promise[String] = js.native
  
  /**
    * Opens a `FileHandle`.
    *
    * Refer to the POSIX [`open(2)`](http://man7.org/linux/man-pages/man2/open.2.html) documentation for more detail.
    *
    * Some characters (`< > : " / \ | ? *`) are reserved under Windows as documented
    * by [Naming Files, Paths, and Namespaces](https://docs.microsoft.com/en-us/windows/desktop/FileIO/naming-a-file). Under NTFS, if the filename contains
    * a colon, Node.js will open a file system stream, as described by [this MSDN page](https://docs.microsoft.com/en-us/windows/desktop/FileIO/using-streams).
    * @since v10.0.0
    * @param [flags='r'] See `support of file system `flags``.
    * @param [mode=0o666] Sets the file mode (permission and sticky bits) if the file is created.
    * @return Fulfills with a {FileHandle} object.
    */
  def open(path: PathLike): js.Promise[FileHandle] = js.native
  def open(path: PathLike, flags: String): js.Promise[FileHandle] = js.native
  def open(path: PathLike, flags: String, mode: typings.node.fsMod.Mode): js.Promise[FileHandle] = js.native
  def open(path: PathLike, flags: Double): js.Promise[FileHandle] = js.native
  def open(path: PathLike, flags: Double, mode: typings.node.fsMod.Mode): js.Promise[FileHandle] = js.native
  def open(path: PathLike, flags: Unit, mode: typings.node.fsMod.Mode): js.Promise[FileHandle] = js.native
  
  /**
    * Asynchronously open a directory for iterative scanning. See the POSIX [`opendir(3)`](http://man7.org/linux/man-pages/man3/opendir.3.html) documentation for more detail.
    *
    * Creates an `fs.Dir`, which contains all further functions for reading from
    * and cleaning up the directory.
    *
    * The `encoding` option sets the encoding for the `path` while opening the
    * directory and subsequent read operations.
    *
    * Example using async iteration:
    *
    * ```js
    * import { opendir } from 'node:fs/promises';
    *
    * try {
    *   const dir = await opendir('./');
    *   for await (const dirent of dir)
    *     console.log(dirent.name);
    * } catch (err) {
    *   console.error(err);
    * }
    * ```
    *
    * When using the async iterator, the `fs.Dir` object will be automatically
    * closed after the iterator exits.
    * @since v12.12.0
    * @return Fulfills with an {fs.Dir}.
    */
  def opendir(path: PathLike): js.Promise[Dir] = js.native
  def opendir(path: PathLike, options: OpenDirOptions): js.Promise[Dir] = js.native
  
  def pipeline(
    stream1: ReadableStream,
    stream2: ReadWriteStream,
    streams: (ReadWriteStream | WritableStream | PipelineOptions)*
  ): js.Promise[Unit] = js.native
  def pipeline(
    stream1: ReadableStream,
    stream2: WritableStream,
    streams: (ReadWriteStream | WritableStream | PipelineOptions)*
  ): js.Promise[Unit] = js.native
  def pipeline(streams: js.Array[ReadableStream | WritableStream | ReadWriteStream]): js.Promise[Unit] = js.native
  def pipeline(streams: js.Array[ReadableStream | WritableStream | ReadWriteStream], options: PipelineOptions): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, B /* <: PipelineDestination[A, Any] */](source: A, destination: B): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, B /* <: PipelineDestination[A, Any] */](source: A, destination: B, options: PipelineOptions): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, B /* <: PipelineDestination[T1, Any] */](source: A, transform1: T1, destination: B): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, B /* <: PipelineDestination[T1, Any] */](source: A, transform1: T1, destination: B, options: PipelineOptions): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, B /* <: PipelineDestination[T2, Any] */](source: A, transform1: T1, transform2: T2, destination: B): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, B /* <: PipelineDestination[T2, Any] */](source: A, transform1: T1, transform2: T2, destination: B, options: PipelineOptions): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, B /* <: PipelineDestination[T3, Any] */](source: A, transform1: T1, transform2: T2, transform3: T3, destination: B): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, B /* <: PipelineDestination[T3, Any] */](
    source: A,
    transform1: T1,
    transform2: T2,
    transform3: T3,
    destination: B,
    options: PipelineOptions
  ): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, T4 /* <: PipelineTransform[T3, Any] */, B /* <: PipelineDestination[T4, Any] */](source: A, transform1: T1, transform2: T2, transform3: T3, transform4: T4, destination: B): js.Promise[Unit] = js.native
  def pipeline[A /* <: PipelineSource[Any] */, T1 /* <: PipelineTransform[A, Any] */, T2 /* <: PipelineTransform[T1, Any] */, T3 /* <: PipelineTransform[T2, Any] */, T4 /* <: PipelineTransform[T3, Any] */, B /* <: PipelineDestination[T4, Any] */](
    source: A,
    transform1: T1,
    transform2: T2,
    transform3: T3,
    transform4: T4,
    destination: B,
    options: PipelineOptions
  ): js.Promise[Unit] = js.native
  
  /**
    * Asynchronously reads the entire contents of a file.
    *
    * If no encoding is specified (using `options.encoding`), the data is returned
    * as a `Buffer` object. Otherwise, the data will be a string.
    *
    * If `options` is a string, then it specifies the encoding.
    *
    * When the `path` is a directory, the behavior of `fsPromises.readFile()` is
    * platform-specific. On macOS, Linux, and Windows, the promise will be rejected
    * with an error. On FreeBSD, a representation of the directory's contents will be
    * returned.
    *
    * An example of reading a `package.json` file located in the same directory of the
    * running code:
    *
    * ```js
    * import { readFile } from 'node:fs/promises';
    * try {
    *   const filePath = new URL('./package.json', import.meta.url);
    *   const contents = await readFile(filePath, { encoding: 'utf8' });
    *   console.log(contents);
    * } catch (err) {
    *   console.error(err.message);
    * }
    * ```
    *
    * It is possible to abort an ongoing `readFile` using an `AbortSignal`. If a
    * request is aborted the promise returned is rejected with an `AbortError`:
    *
    * ```js
    * import { readFile } from 'node:fs/promises';
    *
    * try {
    *   const controller = new AbortController();
    *   const { signal } = controller;
    *   const promise = readFile(fileName, { signal });
    *
    *   // Abort the request before the promise settles.
    *   controller.abort();
    *
    *   await promise;
    * } catch (err) {
    *   // When a request is aborted - err is an AbortError
    *   console.error(err);
    * }
    * ```
    *
    * Aborting an ongoing request does not abort individual operating
    * system requests but rather the internal buffering `fs.readFile` performs.
    *
    * Any specified `FileHandle` has to support reading.
    * @since v10.0.0
    * @param path filename or `FileHandle`
    * @return Fulfills with the contents of the file.
    */
  /**
    * Asynchronously reads the entire contents of a file.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * If a `FileHandle` is provided, the underlying file will _not_ be closed automatically.
    * @param options An object that may contain an optional flag.
    * If a flag is not provided, it defaults to `'r'`.
    */
  def readFile(path: PathLike): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def readFile(path: PathLike, options: ObjectEncodingOptionsAbor): js.Promise[String | typings.node.bufferMod.global.Buffer] = js.native
  /**
    * Asynchronously reads the entire contents of a file.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * If a `FileHandle` is provided, the underlying file will _not_ be closed automatically.
    * @param options An object that may contain an optional flag.
    * If a flag is not provided, it defaults to `'r'`.
    */
  def readFile(path: PathLike, options: encodingBufferEncodingfla): js.Promise[String] = js.native
  def readFile(path: PathLike, options: encodingnullundefinedflag): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def readFile(path: PathLike, options: BufferEncoding): js.Promise[String] = js.native
  def readFile(path: FileHandle): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def readFile(path: FileHandle, options: ObjectEncodingOptionsAbor): js.Promise[String | typings.node.bufferMod.global.Buffer] = js.native
  def readFile(path: FileHandle, options: encodingBufferEncodingfla): js.Promise[String] = js.native
  def readFile(path: FileHandle, options: encodingnullundefinedflag): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def readFile(path: FileHandle, options: BufferEncoding): js.Promise[String] = js.native
  
  /**
    * Reads the contents of a directory.
    *
    * The optional `options` argument can be a string specifying an encoding, or an
    * object with an `encoding` property specifying the character encoding to use for
    * the filenames. If the `encoding` is set to `'buffer'`, the filenames returned
    * will be passed as `Buffer` objects.
    *
    * If `options.withFileTypes` is set to `true`, the returned array will contain `fs.Dirent` objects.
    *
    * ```js
    * import { readdir } from 'node:fs/promises';
    *
    * try {
    *   const files = await readdir(path);
    *   for (const file of files)
    *     console.log(file);
    * } catch (err) {
    *   console.error(err);
    * }
    * ```
    * @since v10.0.0
    * @return Fulfills with an array of the names of the files in the directory excluding `'.'` and `'..'`.
    */
  /**
    * Asynchronous readdir(3) - read a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def readdir(path: PathLike): js.Promise[js.Array[String]] = js.native
  /**
    * Asynchronous readdir(3) - read a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def readdir(path: PathLike, options: Encoding): js.Promise[js.Array[typings.node.bufferMod.global.Buffer]] = js.native
  def readdir(path: PathLike, options: ObjectEncodingOptionswith): js.Promise[js.Array[String]] = js.native
  /**
    * Asynchronous readdir(3) - read a directory.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options If called with `withFileTypes: true` the result data will be an array of Dirent.
    */
  def readdir(path: PathLike, options: ObjectEncodingOptionswithEncoding): js.Promise[js.Array[Dirent]] = js.native
  def readdir(path: PathLike, options: BufferEncoding): js.Promise[js.Array[String]] = js.native
  @JSName("readdir")
  def readdir_buffer(path: PathLike, options: buffer_): js.Promise[js.Array[typings.node.bufferMod.global.Buffer]] = js.native
  
  /**
    * Reads the contents of the symbolic link referred to by `path`. See the POSIX [`readlink(2)`](http://man7.org/linux/man-pages/man2/readlink.2.html) documentation for more detail. The promise is
    * fulfilled with the`linkString` upon success.
    *
    * The optional `options` argument can be a string specifying an encoding, or an
    * object with an `encoding` property specifying the character encoding to use for
    * the link path returned. If the `encoding` is set to `'buffer'`, the link path
    * returned will be passed as a `Buffer` object.
    * @since v10.0.0
    * @return Fulfills with the `linkString` upon success.
    */
  /**
    * Asynchronous readlink(2) - read value of a symbolic link.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def readlink(path: PathLike): js.Promise[String] = js.native
  def readlink(path: PathLike, options: String): js.Promise[String | typings.node.bufferMod.global.Buffer] = js.native
  def readlink(path: PathLike, options: BufferEncoding): js.Promise[String] = js.native
  /**
    * Asynchronous readlink(2) - read value of a symbolic link.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def readlink(path: PathLike, options: BufferEncodingOption): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def readlink(path: PathLike, options: ObjectEncodingOptions): js.Promise[String] = js.native
  
  /**
    * Determines the actual location of `path` using the same semantics as the `fs.realpath.native()` function.
    *
    * Only paths that can be converted to UTF8 strings are supported.
    *
    * The optional `options` argument can be a string specifying an encoding, or an
    * object with an `encoding` property specifying the character encoding to use for
    * the path. If the `encoding` is set to `'buffer'`, the path returned will be
    * passed as a `Buffer` object.
    *
    * On Linux, when Node.js is linked against musl libc, the procfs file system must
    * be mounted on `/proc` in order for this function to work. Glibc does not have
    * this restriction.
    * @since v10.0.0
    * @return Fulfills with the resolved path upon success.
    */
  /**
    * Asynchronous realpath(3) - return the canonicalized absolute pathname.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def realpath(path: PathLike): js.Promise[String] = js.native
  def realpath(path: PathLike, options: BufferEncoding): js.Promise[String] = js.native
  /**
    * Asynchronous realpath(3) - return the canonicalized absolute pathname.
    * @param path A path to a file. If a URL is provided, it must use the `file:` protocol.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def realpath(path: PathLike, options: BufferEncodingOption): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def realpath(path: PathLike, options: ObjectEncodingOptions): js.Promise[String] = js.native
  
  /**
    * Renames `oldPath` to `newPath`.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def rename(oldPath: PathLike, newPath: PathLike): js.Promise[Unit] = js.native
  
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
    * Removes files and directories (modeled on the standard POSIX `rm` utility).
    * @since v14.14.0
    * @return Fulfills with `undefined` upon success.
    */
  def rm(path: PathLike): js.Promise[Unit] = js.native
  def rm(path: PathLike, options: RmOptions): js.Promise[Unit] = js.native
  
  /**
    * Removes the directory identified by `path`.
    *
    * Using `fsPromises.rmdir()` on a file (not a directory) results in the
    * promise being rejected with an `ENOENT` error on Windows and an `ENOTDIR` error on POSIX.
    *
    * To get a behavior similar to the `rm -rf` Unix command, use `fsPromises.rm()` with options `{ recursive: true, force: true }`.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def rmdir(path: PathLike): js.Promise[Unit] = js.native
  def rmdir(path: PathLike, options: RmDirOptions): js.Promise[Unit] = js.native
  
  val scheduler: Scheduler_ = js.native
  
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
    * ```js
    * import {
    *   setImmediate,
    * } from 'timers/promises';
    *
    * const res = await setImmediate('result');
    *
    * console.log(res);  // Prints 'result'
    * ```
    * @since v15.0.0
    * @param value A value with which the promise is fulfilled.
    */
  def setImmediate[T](): js.Promise[T] = js.native
  def setImmediate[T](value: T): js.Promise[T] = js.native
  def setImmediate[T](value: T, options: TimerOptions): js.Promise[T] = js.native
  def setImmediate[T](value: Unit, options: TimerOptions): js.Promise[T] = js.native
  
  /**
    * Returns an async iterator that generates values in an interval of `delay` ms.
    * If `ref` is `true`, you need to call `next()` of async iterator explicitly
    * or implicitly to keep the event loop alive.
    *
    * ```js
    * import {
    *   setInterval,
    * } from 'timers/promises';
    *
    * const interval = 100;
    * for await (const startTime of setInterval(interval, Date.now())) {
    *   const now = Date.now();
    *   console.log(now);
    *   if ((now - startTime) > 1000)
    *     break;
    * }
    * console.log(Date.now());
    * ```
    * @since v15.9.0
    */
  def setInterval[T](): Any = js.native
  def setInterval[T](delay: Double): Any = js.native
  def setInterval[T](delay: Double, value: T): Any = js.native
  def setInterval[T](delay: Double, value: T, options: TimerOptions): Any = js.native
  def setInterval[T](delay: Double, value: Unit, options: TimerOptions): Any = js.native
  def setInterval[T](delay: Unit, value: T): Any = js.native
  def setInterval[T](delay: Unit, value: T, options: TimerOptions): Any = js.native
  def setInterval[T](delay: Unit, value: Unit, options: TimerOptions): Any = js.native
  
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
  
  /**
    * ```js
    * import {
    *   setTimeout,
    * } from 'timers/promises';
    *
    * const res = await setTimeout(100, 'result');
    *
    * console.log(res);  // Prints 'result'
    * ```
    * @since v15.0.0
    * @param [delay=1] The number of milliseconds to wait before fulfilling the promise.
    * @param value A value with which the promise is fulfilled.
    */
  def setTimeout[T](): js.Promise[T] = js.native
  def setTimeout[T](delay: Double): js.Promise[T] = js.native
  def setTimeout[T](delay: Double, value: T): js.Promise[T] = js.native
  def setTimeout[T](delay: Double, value: T, options: TimerOptions): js.Promise[T] = js.native
  def setTimeout[T](delay: Double, value: Unit, options: TimerOptions): js.Promise[T] = js.native
  def setTimeout[T](delay: Unit, value: T): js.Promise[T] = js.native
  def setTimeout[T](delay: Unit, value: T, options: TimerOptions): js.Promise[T] = js.native
  def setTimeout[T](delay: Unit, value: Unit, options: TimerOptions): js.Promise[T] = js.native
  
  /**
    * @since v10.0.0
    * @return Fulfills with the {fs.Stats} object for the given `path`.
    */
  def stat(path: PathLike): js.Promise[Stats] = js.native
  def stat(path: PathLike, opts: StatOptionsbigintfalseund): js.Promise[Stats] = js.native
  def stat(path: PathLike, opts: StatOptionsbiginttrue): js.Promise[BigIntStats] = js.native
  def stat(path: PathLike, opts: StatOptions): js.Promise[Stats | BigIntStats] = js.native
  
  /**
    * @since v19.6.0, v18.15.0
    * @return Fulfills with the {fs.StatFs} object for the given `path`.
    */
  def statfs(path: PathLike): js.Promise[StatsFs] = js.native
  def statfs(path: PathLike, opts: StatFsOptionsbigintfalseu): js.Promise[StatsFs] = js.native
  def statfs(path: PathLike, opts: StatFsOptionsbiginttrue): js.Promise[BigIntStatsFs] = js.native
  def statfs(path: PathLike, opts: StatFsOptions): js.Promise[StatsFs | BigIntStatsFs] = js.native
  
  /**
    * Creates a symbolic link.
    *
    * The `type` argument is only used on Windows platforms and can be one of `'dir'`, `'file'`, or `'junction'`. If the `type` argument is not a string, Node.js will
    * autodetect `target` type and use `'file'` or `'dir'`. If the `target` does not
    * exist, `'file'` will be used. Windows junction points require the destination
    * path to be absolute. When using `'junction'`, the `target` argument will
    * automatically be normalized to absolute path. Junction points on NTFS volumes
    * can only point to directories.
    * @since v10.0.0
    * @param [type='null']
    * @return Fulfills with `undefined` upon success.
    */
  def symlink(target: PathLike, path: PathLike): js.Promise[Unit] = js.native
  def symlink(target: PathLike, path: PathLike, `type`: String): js.Promise[Unit] = js.native
  
  /**
    * Truncates (shortens or extends the length) of the content at `path` to `len` bytes.
    * @since v10.0.0
    * @param [len=0]
    * @return Fulfills with `undefined` upon success.
    */
  def truncate(path: PathLike): js.Promise[Unit] = js.native
  def truncate(path: PathLike, len: Double): js.Promise[Unit] = js.native
  
  /**
    * If `path` refers to a symbolic link, then the link is removed without affecting
    * the file or directory to which that link refers. If the `path` refers to a file
    * path that is not a symbolic link, the file is deleted. See the POSIX [`unlink(2)`](http://man7.org/linux/man-pages/man2/unlink.2.html) documentation for more detail.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def unlink(path: PathLike): js.Promise[Unit] = js.native
  
  /**
    * Change the file system timestamps of the object referenced by `path`.
    *
    * The `atime` and `mtime` arguments follow these rules:
    *
    * * Values can be either numbers representing Unix epoch time, `Date`s, or a
    * numeric string like `'123456789.0'`.
    * * If the value can not be converted to a number, or is `NaN`, `Infinity`, or `-Infinity`, an `Error` will be thrown.
    * @since v10.0.0
    * @return Fulfills with `undefined` upon success.
    */
  def utimes(path: PathLike, atime: TimeLike, mtime: TimeLike): js.Promise[Unit] = js.native
  
  /**
    * Watch for changes on `filename`, where `filename` is either a file or a directory, returning an `FSWatcher`.
    * @param filename A path to a file or directory. If a URL is provided, it must use the `file:` protocol.
    * @param options Either the encoding for the filename provided to the listener, or an object optionally specifying encoding, persistent, and recursive options.
    * If `encoding` is not supplied, the default of `'utf8'` is used.
    * If `persistent` is not supplied, the default of `true` is used.
    * If `recursive` is not supplied, the default of `false` is used.
    */
  def watch(filename: PathLike): Any = js.native
  def watch(filename: PathLike, options: String): /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<FileChangeInfo<string>> */ Any = js.native
  /**
    * Returns an async iterator that watches for changes on `filename`, where `filename`is either a file or a directory.
    *
    * ```js
    * const { watch } = require('node:fs/promises');
    *
    * const ac = new AbortController();
    * const { signal } = ac;
    * setTimeout(() => ac.abort(), 10000);
    *
    * (async () => {
    *   try {
    *     const watcher = watch(__filename, { signal });
    *     for await (const event of watcher)
    *       console.log(event);
    *   } catch (err) {
    *     if (err.name === 'AbortError')
    *       return;
    *     throw err;
    *   }
    * })();
    * ```
    *
    * On most platforms, `'rename'` is emitted whenever a filename appears or
    * disappears in the directory.
    *
    * All the `caveats` for `fs.watch()` also apply to `fsPromises.watch()`.
    * @since v15.9.0, v14.18.0
    * @return of objects with the properties:
    */
  def watch(filename: PathLike, options: WatchOptionsencodingbuffe): Any = js.native
  def watch(filename: PathLike, options: BufferEncoding): Any = js.native
  def watch(filename: PathLike, options: WatchOptions): Any = js.native
  @JSName("watch")
  def watch_buffer(filename: PathLike, options: buffer_): Any = js.native
  
  /**
    * Asynchronously writes data to a file, replacing the file if it already exists. `data` can be a string, a buffer, an
    * [AsyncIterable](https://tc39.github.io/ecma262/#sec-asynciterable-interface), or an
    * [Iterable](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Iteration_protocols#The_iterable_protocol) object.
    *
    * The `encoding` option is ignored if `data` is a buffer.
    *
    * If `options` is a string, then it specifies the encoding.
    *
    * The `mode` option only affects the newly created file. See `fs.open()` for more details.
    *
    * Any specified `FileHandle` has to support writing.
    *
    * It is unsafe to use `fsPromises.writeFile()` multiple times on the same file
    * without waiting for the promise to be settled.
    *
    * Similarly to `fsPromises.readFile` \- `fsPromises.writeFile` is a convenience
    * method that performs multiple `write` calls internally to write the buffer
    * passed to it. For performance sensitive code consider using `fs.createWriteStream()` or `filehandle.createWriteStream()`.
    *
    * It is possible to use an `AbortSignal` to cancel an `fsPromises.writeFile()`.
    * Cancelation is "best effort", and some amount of data is likely still
    * to be written.
    *
    * ```js
    * import { writeFile } from 'node:fs/promises';
    * import { Buffer } from 'node:buffer';
    *
    * try {
    *   const controller = new AbortController();
    *   const { signal } = controller;
    *   const data = new Uint8Array(Buffer.from('Hello Node.js'));
    *   const promise = writeFile('message.txt', data, { signal });
    *
    *   // Abort the request before the promise settles.
    *   controller.abort();
    *
    *   await promise;
    * } catch (err) {
    *   // When a request is aborted - err is an AbortError
    *   console.error(err);
    * }
    * ```
    *
    * Aborting an ongoing request does not abort individual operating
    * system requests but rather the internal buffering `fs.writeFile` performs.
    * @since v10.0.0
    * @param file filename or `FileHandle`
    * @return Fulfills with `undefined` upon success.
    */
  def writeFile(file: PathLike, data: String): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: String, options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: String, options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: js.Iterable[String | ArrayBufferView]): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: js.Iterable[String | ArrayBufferView], options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: js.Iterable[String | ArrayBufferView], options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(
    file: PathLike,
    data: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<string | NodeJS.ArrayBufferView> */ Any
  ): js.Promise[Unit] = js.native
  def writeFile(
    file: PathLike,
    data: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<string | NodeJS.ArrayBufferView> */ Any,
    options: ObjectEncodingOptionsmode
  ): js.Promise[Unit] = js.native
  def writeFile(
    file: PathLike,
    data: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<string | NodeJS.ArrayBufferView> */ Any,
    options: BufferEncoding
  ): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: ArrayBufferView): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: ArrayBufferView, options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: ArrayBufferView, options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: typings.node.nodeColonstreamMod.Stream): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: typings.node.nodeColonstreamMod.Stream, options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: PathLike, data: typings.node.nodeColonstreamMod.Stream, options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: String): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: String, options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: String, options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: js.Iterable[String | ArrayBufferView]): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: js.Iterable[String | ArrayBufferView], options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: js.Iterable[String | ArrayBufferView], options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(
    file: FileHandle,
    data: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<string | NodeJS.ArrayBufferView> */ Any
  ): js.Promise[Unit] = js.native
  def writeFile(
    file: FileHandle,
    data: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<string | NodeJS.ArrayBufferView> */ Any,
    options: ObjectEncodingOptionsmode
  ): js.Promise[Unit] = js.native
  def writeFile(
    file: FileHandle,
    data: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<string | NodeJS.ArrayBufferView> */ Any,
    options: BufferEncoding
  ): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: ArrayBufferView): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: ArrayBufferView, options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: ArrayBufferView, options: BufferEncoding): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: typings.node.nodeColonstreamMod.Stream): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: typings.node.nodeColonstreamMod.Stream, options: ObjectEncodingOptionsmode): js.Promise[Unit] = js.native
  def writeFile(file: FileHandle, data: typings.node.nodeColonstreamMod.Stream, options: BufferEncoding): js.Promise[Unit] = js.native
}

package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable2
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.readlineMod.AsyncCompleter
import typings.node.readlineMod.Completer
import typings.node.readlineMod.ReadLineOptions
import typings.node.readlinePromisesMod.Interface
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofpromisesInterface extends StObject {
  
  /**
    * Instances of the `readlinePromises.Interface` class are constructed using the `readlinePromises.createInterface()` method. Every instance is associated with a
    * single `input` `Readable` stream and a single `output` `Writable` stream.
    * The `output` stream is used to print prompts for user input that arrives on,
    * and is read from, the `input` stream.
    * @since v17.0.0
    */
  var Interface: Instantiable0[typings.node.nodeColonreadlineMod.promises.Interface] = js.native
  
  /**
    * @since v17.0.0
    */
  var Readline: Instantiable2[
    /* stream */ WritableStream, 
    /* options */ js.UndefOr[AutoCommit], 
    typings.node.nodeColonreadlineMod.promises.Readline
  ] = js.native
  
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
}

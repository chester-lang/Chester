package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.replMod.REPLServer
import typings.node.replMod.ReplOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedRepl extends StObject {
  
  /**
    * Instances of `repl.REPLServer` are created using the {@link start} method
    * or directly using the JavaScript `new` keyword.
    *
    * ```js
    * const repl = require('node:repl');
    *
    * const options = { useColors: true };
    *
    * const firstInstance = repl.start(options);
    * const secondInstance = new repl.REPLServer(options);
    * ```
    * @since v0.1.91
    */
  var REPLServer: Instantiable0[typings.node.replMod.REPLServer] = js.native
  
  /**
    * A flag passed in the REPL options. Evaluates expressions in sloppy mode.
    */
  val REPL_MODE_SLOPPY: js.Symbol = js.native
  
  /**
    * A flag passed in the REPL options. Evaluates expressions in strict mode.
    * This is equivalent to prefacing every repl statement with `'use strict'`.
    */
  val REPL_MODE_STRICT: js.Symbol = js.native
  
  /**
    * Indicates a recoverable error that a `REPLServer` can use to support multi-line input.
    *
    * @see https://nodejs.org/dist/latest-v22.x/docs/api/repl.html#repl_recoverable_errors
    */
  var Recoverable: Instantiable1[/* err */ js.Error, typings.node.replMod.Recoverable] = js.native
  
  /**
    * The `repl.start()` method creates and starts a {@link REPLServer} instance.
    *
    * If `options` is a string, then it specifies the input prompt:
    *
    * ```js
    * const repl = require('node:repl');
    *
    * // a Unix style prompt
    * repl.start('$ ');
    * ```
    * @since v0.1.91
    */
  def start(): REPLServer = js.native
  def start(options: String): REPLServer = js.native
  def start(options: ReplOptions): REPLServer = js.native
  
  /**
    * This is the default "writer" value, if none is passed in the REPL options,
    * and it can be overridden by custom print functions.
    */
  def writer(obj: Any): String = js.native
}

package typings.node.utilMod

import typings.node.anon.ToString
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@JSImport("util", "MIMEType")
@js.native
open class MIMEType protected () extends StObject {
  /**
    * Creates a new MIMEType object by parsing the input.
    *
    * A `TypeError` will be thrown if the `input` is not a valid MIME.
    * Note that an effort will be made to coerce the given values into strings.
    * @param input The input MIME to parse.
    */
  def this(input: String) = this()
  def this(input: ToString) = this()
  
  /**
    * Gets the essence of the MIME. This property is read only.
    * Use `mime.type` or `mime.subtype` to alter the MIME.
    *
    * ```js
    * import { MIMEType } from 'node:util';
    *
    * const myMIME = new MIMEType('text/javascript;key=value');
    * console.log(myMIME.essence);
    * // Prints: text/javascript
    * myMIME.type = 'application';
    * console.log(myMIME.essence);
    * // Prints: application/javascript
    * console.log(String(myMIME));
    * // Prints: application/javascript;key=value
    * ```
    */
  val essence: String = js.native
  
  /**
    * Gets the `MIMEParams` object representing the
    * parameters of the MIME. This property is read-only. See `MIMEParams` documentation for details.
    */
  val params: MIMEParams = js.native
  
  /**
    * Gets and sets the subtype portion of the MIME.
    *
    * ```js
    * import { MIMEType } from 'node:util';
    *
    * const myMIME = new MIMEType('text/ecmascript');
    * console.log(myMIME.subtype);
    * // Prints: ecmascript
    * myMIME.subtype = 'javascript';
    * console.log(myMIME.subtype);
    * // Prints: javascript
    * console.log(String(myMIME));
    * // Prints: text/javascript
    * ```
    */
  var subtype: String = js.native
  
  /**
    * Gets and sets the type portion of the MIME.
    *
    * ```js
    * import { MIMEType } from 'node:util';
    *
    * const myMIME = new MIMEType('text/javascript');
    * console.log(myMIME.type);
    * // Prints: text
    * myMIME.type = 'application';
    * console.log(myMIME.type);
    * // Prints: application
    * console.log(String(myMIME));
    * // Prints: application/javascript
    * ```
    */
  var `type`: String = js.native
}

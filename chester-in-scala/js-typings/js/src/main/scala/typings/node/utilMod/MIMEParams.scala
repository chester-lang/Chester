package typings.node.utilMod

import typings.std.IterableIterator
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@JSImport("util", "MIMEParams")
@js.native
open class MIMEParams () extends StObject {
  
  /**
    * Remove all name-value pairs whose name is `name`.
    */
  def delete(name: String): Unit = js.native
  
  /**
    * Returns an iterator over each of the name-value pairs in the parameters.
    * Each item of the iterator is a JavaScript `Array`. The first item of the array
    * is the `name`, the second item of the array is the `value`.
    */
  def entries(): IterableIterator[js.Tuple2[/* name */ String, /* value */ String]] = js.native
  
  /**
    * Returns the value of the first name-value pair whose name is `name`. If there
    * are no such pairs, `null` is returned.
    * @return or `null` if there is no name-value pair with the given `name`.
    */
  def get(name: String): String | Null = js.native
  
  /**
    * Returns `true` if there is at least one name-value pair whose name is `name`.
    */
  def has(name: String): Boolean = js.native
  
  /**
    * Returns an iterator over each of the name-value pairs in the parameters.
    */
  @JSName(js.Symbol.iterator)
  var iterator: js.Function0[IterableIterator[js.Tuple2[/* name */ String, /* value */ String]]] = js.native
  
  /**
    * Returns an iterator over the names of each name-value pair.
    *
    * ```js
    * import { MIMEType } from 'node:util';
    *
    * const { params } = new MIMEType('text/plain;foo=0;bar=1');
    * for (const name of params.keys()) {
    *   console.log(name);
    * }
    * // Prints:
    * //   foo
    * //   bar
    * ```
    */
  def keys(): IterableIterator[String] = js.native
  
  /**
    * Sets the value in the `MIMEParams` object associated with `name` to `value`. If there are any pre-existing name-value pairs whose names are `name`,
    * set the first such pair's value to `value`.
    *
    * ```js
    * import { MIMEType } from 'node:util';
    *
    * const { params } = new MIMEType('text/plain;foo=0;bar=1');
    * params.set('foo', 'def');
    * params.set('baz', 'xyz');
    * console.log(params.toString());
    * // Prints: foo=def;bar=1;baz=xyz
    * ```
    */
  def set(name: String, value: String): Unit = js.native
  
  /**
    * Returns an iterator over the values of each name-value pair.
    */
  def values(): IterableIterator[String] = js.native
}

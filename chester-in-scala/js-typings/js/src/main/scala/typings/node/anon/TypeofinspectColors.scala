package typings.node.anon

import typings.node.globalsMod.global.NodeJS.Dict
import typings.node.utilMod.InspectOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofinspectColors extends StObject {
  
  def apply(`object`: Any): String = js.native
  def apply(`object`: Any, options: InspectOptions): String = js.native
  def apply(`object`: Any, showHidden: Boolean): String = js.native
  def apply(`object`: Any, showHidden: Boolean, depth: Double): String = js.native
  def apply(`object`: Any, showHidden: Boolean, depth: Double, color: Boolean): String = js.native
  def apply(`object`: Any, showHidden: Boolean, depth: Null, color: Boolean): String = js.native
  def apply(`object`: Any, showHidden: Boolean, depth: Unit, color: Boolean): String = js.native
  def apply(`object`: Any, showHidden: Unit, depth: Double): String = js.native
  def apply(`object`: Any, showHidden: Unit, depth: Double, color: Boolean): String = js.native
  def apply(`object`: Any, showHidden: Unit, depth: Null, color: Boolean): String = js.native
  def apply(`object`: Any, showHidden: Unit, depth: Unit, color: Boolean): String = js.native
  
  var colors: Dict[js.Tuple2[Double, Double]] = js.native
  
  /**
    * That can be used to declare custom inspect functions.
    */
  val custom: js.Symbol = js.native
  
  var defaultOptions: InspectOptions = js.native
  
  /**
    * Allows changing inspect settings from the repl.
    */
  var replDefaults: InspectOptions = js.native
  
  var styles: /* import warning: importer.ImportType#apply Failed type conversion: {[ K in node.util.Style ]: string} */ js.Any = js.native
}

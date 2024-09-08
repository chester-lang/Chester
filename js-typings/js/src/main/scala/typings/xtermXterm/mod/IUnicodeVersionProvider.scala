package typings.xtermXterm.mod

import typings.xtermXterm.xtermXtermInts.`0`
import typings.xtermXterm.xtermXtermInts.`1`
import typings.xtermXterm.xtermXtermInts.`2`
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait IUnicodeVersionProvider extends StObject {
  
  def charProperties(codepoint: Double, preceding: Double): Double
  
  /**
    * String indicating the Unicode version provided.
    */
  val version: String
  
  /**
    * Unicode version dependent wcwidth implementation.
    */
  def wcwidth(codepoint: Double): `0` | `1` | `2`
}
object IUnicodeVersionProvider {
  
  inline def apply(charProperties: (Double, Double) => Double, version: String, wcwidth: Double => `0` | `1` | `2`): IUnicodeVersionProvider = {
    val __obj = js.Dynamic.literal(charProperties = js.Any.fromFunction2(charProperties), version = version.asInstanceOf[js.Any], wcwidth = js.Any.fromFunction1(wcwidth))
    __obj.asInstanceOf[IUnicodeVersionProvider]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: IUnicodeVersionProvider] (val x: Self) extends AnyVal {
    
    inline def setCharProperties(value: (Double, Double) => Double): Self = StObject.set(x, "charProperties", js.Any.fromFunction2(value))
    
    inline def setVersion(value: String): Self = StObject.set(x, "version", value.asInstanceOf[js.Any])
    
    inline def setWcwidth(value: Double => `0` | `1` | `2`): Self = StObject.set(x, "wcwidth", js.Any.fromFunction1(value))
  }
}

package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Typeofpriority extends StObject {
  
  val PRIORITY_ABOVE_NORMAL: Double
  
  val PRIORITY_BELOW_NORMAL: Double
  
  val PRIORITY_HIGH: Double
  
  val PRIORITY_HIGHEST: Double
  
  val PRIORITY_LOW: Double
  
  val PRIORITY_NORMAL: Double
}
object Typeofpriority {
  
  inline def apply(
    PRIORITY_ABOVE_NORMAL: Double,
    PRIORITY_BELOW_NORMAL: Double,
    PRIORITY_HIGH: Double,
    PRIORITY_HIGHEST: Double,
    PRIORITY_LOW: Double,
    PRIORITY_NORMAL: Double
  ): Typeofpriority = {
    val __obj = js.Dynamic.literal(PRIORITY_ABOVE_NORMAL = PRIORITY_ABOVE_NORMAL.asInstanceOf[js.Any], PRIORITY_BELOW_NORMAL = PRIORITY_BELOW_NORMAL.asInstanceOf[js.Any], PRIORITY_HIGH = PRIORITY_HIGH.asInstanceOf[js.Any], PRIORITY_HIGHEST = PRIORITY_HIGHEST.asInstanceOf[js.Any], PRIORITY_LOW = PRIORITY_LOW.asInstanceOf[js.Any], PRIORITY_NORMAL = PRIORITY_NORMAL.asInstanceOf[js.Any])
    __obj.asInstanceOf[Typeofpriority]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Typeofpriority] (val x: Self) extends AnyVal {
    
    inline def setPRIORITY_ABOVE_NORMAL(value: Double): Self = StObject.set(x, "PRIORITY_ABOVE_NORMAL", value.asInstanceOf[js.Any])
    
    inline def setPRIORITY_BELOW_NORMAL(value: Double): Self = StObject.set(x, "PRIORITY_BELOW_NORMAL", value.asInstanceOf[js.Any])
    
    inline def setPRIORITY_HIGH(value: Double): Self = StObject.set(x, "PRIORITY_HIGH", value.asInstanceOf[js.Any])
    
    inline def setPRIORITY_HIGHEST(value: Double): Self = StObject.set(x, "PRIORITY_HIGHEST", value.asInstanceOf[js.Any])
    
    inline def setPRIORITY_LOW(value: Double): Self = StObject.set(x, "PRIORITY_LOW", value.asInstanceOf[js.Any])
    
    inline def setPRIORITY_NORMAL(value: Double): Self = StObject.set(x, "PRIORITY_NORMAL", value.asInstanceOf[js.Any])
  }
}

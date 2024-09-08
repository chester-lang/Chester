package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TestShard extends StObject {
  
  /**
    * A positive integer between 1 and `total` that specifies the index of the shard to run.
    */
  var index: Double
  
  /**
    * A positive integer that specifies the total number of shards to split the test files to.
    */
  var total: Double
}
object TestShard {
  
  inline def apply(index: Double, total: Double): TestShard = {
    val __obj = js.Dynamic.literal(index = index.asInstanceOf[js.Any], total = total.asInstanceOf[js.Any])
    __obj.asInstanceOf[TestShard]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TestShard] (val x: Self) extends AnyVal {
    
    inline def setIndex(value: Double): Self = StObject.set(x, "index", value.asInstanceOf[js.Any])
    
    inline def setTotal(value: Double): Self = StObject.set(x, "total", value.asInstanceOf[js.Any])
  }
}

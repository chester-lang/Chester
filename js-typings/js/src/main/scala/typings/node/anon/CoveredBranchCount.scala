package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait CoveredBranchCount extends StObject {
  
  /**
    * The number of covered branches.
    */
  var coveredBranchCount: Double
  
  /**
    * The percentage of branches covered.
    */
  var coveredBranchPercent: Double
  
  /**
    * The number of covered functions.
    */
  var coveredFunctionCount: Double
  
  /**
    * The percentage of functions covered.
    */
  var coveredFunctionPercent: Double
  
  /**
    * The number of covered lines.
    */
  var coveredLineCount: Double
  
  /**
    * The percentage of lines covered.
    */
  var coveredLinePercent: Double
  
  /**
    * The total number of branches.
    */
  var totalBranchCount: Double
  
  /**
    * The total number of functions.
    */
  var totalFunctionCount: Double
  
  /**
    * The total number of lines.
    */
  var totalLineCount: Double
}
object CoveredBranchCount {
  
  inline def apply(
    coveredBranchCount: Double,
    coveredBranchPercent: Double,
    coveredFunctionCount: Double,
    coveredFunctionPercent: Double,
    coveredLineCount: Double,
    coveredLinePercent: Double,
    totalBranchCount: Double,
    totalFunctionCount: Double,
    totalLineCount: Double
  ): CoveredBranchCount = {
    val __obj = js.Dynamic.literal(coveredBranchCount = coveredBranchCount.asInstanceOf[js.Any], coveredBranchPercent = coveredBranchPercent.asInstanceOf[js.Any], coveredFunctionCount = coveredFunctionCount.asInstanceOf[js.Any], coveredFunctionPercent = coveredFunctionPercent.asInstanceOf[js.Any], coveredLineCount = coveredLineCount.asInstanceOf[js.Any], coveredLinePercent = coveredLinePercent.asInstanceOf[js.Any], totalBranchCount = totalBranchCount.asInstanceOf[js.Any], totalFunctionCount = totalFunctionCount.asInstanceOf[js.Any], totalLineCount = totalLineCount.asInstanceOf[js.Any])
    __obj.asInstanceOf[CoveredBranchCount]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: CoveredBranchCount] (val x: Self) extends AnyVal {
    
    inline def setCoveredBranchCount(value: Double): Self = StObject.set(x, "coveredBranchCount", value.asInstanceOf[js.Any])
    
    inline def setCoveredBranchPercent(value: Double): Self = StObject.set(x, "coveredBranchPercent", value.asInstanceOf[js.Any])
    
    inline def setCoveredFunctionCount(value: Double): Self = StObject.set(x, "coveredFunctionCount", value.asInstanceOf[js.Any])
    
    inline def setCoveredFunctionPercent(value: Double): Self = StObject.set(x, "coveredFunctionPercent", value.asInstanceOf[js.Any])
    
    inline def setCoveredLineCount(value: Double): Self = StObject.set(x, "coveredLineCount", value.asInstanceOf[js.Any])
    
    inline def setCoveredLinePercent(value: Double): Self = StObject.set(x, "coveredLinePercent", value.asInstanceOf[js.Any])
    
    inline def setTotalBranchCount(value: Double): Self = StObject.set(x, "totalBranchCount", value.asInstanceOf[js.Any])
    
    inline def setTotalFunctionCount(value: Double): Self = StObject.set(x, "totalFunctionCount", value.asInstanceOf[js.Any])
    
    inline def setTotalLineCount(value: Double): Self = StObject.set(x, "totalLineCount", value.asInstanceOf[js.Any])
  }
}

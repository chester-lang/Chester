package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Branches extends StObject {
  
  /**
    * An array of branches representing branch coverage.
    */
  var branches: js.Array[Line]
  
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
    * An array of functions representing function coverage.
    */
  var functions: js.Array[Count]
  
  /**
    * An array of lines representing line numbers and the number of times they were covered.
    */
  var lines: js.Array[Line]
  
  /**
    * The absolute path of the file.
    */
  var path: String
  
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
object Branches {
  
  inline def apply(
    branches: js.Array[Line],
    coveredBranchCount: Double,
    coveredBranchPercent: Double,
    coveredFunctionCount: Double,
    coveredFunctionPercent: Double,
    coveredLineCount: Double,
    coveredLinePercent: Double,
    functions: js.Array[Count],
    lines: js.Array[Line],
    path: String,
    totalBranchCount: Double,
    totalFunctionCount: Double,
    totalLineCount: Double
  ): Branches = {
    val __obj = js.Dynamic.literal(branches = branches.asInstanceOf[js.Any], coveredBranchCount = coveredBranchCount.asInstanceOf[js.Any], coveredBranchPercent = coveredBranchPercent.asInstanceOf[js.Any], coveredFunctionCount = coveredFunctionCount.asInstanceOf[js.Any], coveredFunctionPercent = coveredFunctionPercent.asInstanceOf[js.Any], coveredLineCount = coveredLineCount.asInstanceOf[js.Any], coveredLinePercent = coveredLinePercent.asInstanceOf[js.Any], functions = functions.asInstanceOf[js.Any], lines = lines.asInstanceOf[js.Any], path = path.asInstanceOf[js.Any], totalBranchCount = totalBranchCount.asInstanceOf[js.Any], totalFunctionCount = totalFunctionCount.asInstanceOf[js.Any], totalLineCount = totalLineCount.asInstanceOf[js.Any])
    __obj.asInstanceOf[Branches]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Branches] (val x: Self) extends AnyVal {
    
    inline def setBranches(value: js.Array[Line]): Self = StObject.set(x, "branches", value.asInstanceOf[js.Any])
    
    inline def setBranchesVarargs(value: Line*): Self = StObject.set(x, "branches", js.Array(value*))
    
    inline def setCoveredBranchCount(value: Double): Self = StObject.set(x, "coveredBranchCount", value.asInstanceOf[js.Any])
    
    inline def setCoveredBranchPercent(value: Double): Self = StObject.set(x, "coveredBranchPercent", value.asInstanceOf[js.Any])
    
    inline def setCoveredFunctionCount(value: Double): Self = StObject.set(x, "coveredFunctionCount", value.asInstanceOf[js.Any])
    
    inline def setCoveredFunctionPercent(value: Double): Self = StObject.set(x, "coveredFunctionPercent", value.asInstanceOf[js.Any])
    
    inline def setCoveredLineCount(value: Double): Self = StObject.set(x, "coveredLineCount", value.asInstanceOf[js.Any])
    
    inline def setCoveredLinePercent(value: Double): Self = StObject.set(x, "coveredLinePercent", value.asInstanceOf[js.Any])
    
    inline def setFunctions(value: js.Array[Count]): Self = StObject.set(x, "functions", value.asInstanceOf[js.Any])
    
    inline def setFunctionsVarargs(value: Count*): Self = StObject.set(x, "functions", js.Array(value*))
    
    inline def setLines(value: js.Array[Line]): Self = StObject.set(x, "lines", value.asInstanceOf[js.Any])
    
    inline def setLinesVarargs(value: Line*): Self = StObject.set(x, "lines", js.Array(value*))
    
    inline def setPath(value: String): Self = StObject.set(x, "path", value.asInstanceOf[js.Any])
    
    inline def setTotalBranchCount(value: Double): Self = StObject.set(x, "totalBranchCount", value.asInstanceOf[js.Any])
    
    inline def setTotalFunctionCount(value: Double): Self = StObject.set(x, "totalFunctionCount", value.asInstanceOf[js.Any])
    
    inline def setTotalLineCount(value: Double): Self = StObject.set(x, "totalLineCount", value.asInstanceOf[js.Any])
  }
}

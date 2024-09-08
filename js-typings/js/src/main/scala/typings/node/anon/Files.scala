package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Files extends StObject {
  
  /**
    * An array of coverage reports for individual files.
    */
  var files: js.Array[Branches]
  
  /**
    * An object containing a summary of coverage for all files.
    */
  var totals: CoveredBranchCount
  
  /**
    * The working directory when code coverage began. This
    * is useful for displaying relative path names in case
    * the tests changed the working directory of the Node.js process.
    */
  var workingDirectory: String
}
object Files {
  
  inline def apply(files: js.Array[Branches], totals: CoveredBranchCount, workingDirectory: String): Files = {
    val __obj = js.Dynamic.literal(files = files.asInstanceOf[js.Any], totals = totals.asInstanceOf[js.Any], workingDirectory = workingDirectory.asInstanceOf[js.Any])
    __obj.asInstanceOf[Files]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Files] (val x: Self) extends AnyVal {
    
    inline def setFiles(value: js.Array[Branches]): Self = StObject.set(x, "files", value.asInstanceOf[js.Any])
    
    inline def setFilesVarargs(value: Branches*): Self = StObject.set(x, "files", js.Array(value*))
    
    inline def setTotals(value: CoveredBranchCount): Self = StObject.set(x, "totals", value.asInstanceOf[js.Any])
    
    inline def setWorkingDirectory(value: String): Self = StObject.set(x, "workingDirectory", value.asInstanceOf[js.Any])
  }
}

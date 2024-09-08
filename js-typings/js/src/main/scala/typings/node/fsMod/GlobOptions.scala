package typings.node.fsMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait GlobOptions extends StObject {
  
  /**
    * Current working directory.
    * @default process.cwd()
    */
  var cwd: js.UndefOr[String] = js.undefined
  
  /**
    * Function to filter out files/directories. Return true to exclude the item, false to include it.
    */
  var exclude: js.UndefOr[js.Function1[/* fileName */ String, Boolean]] = js.undefined
  
  /**
    * `true` if the glob should return paths as `Dirent`s, `false` otherwise.
    * @default false
    * @since v22.2.0
    */
  var withFileTypes: js.UndefOr[Boolean] = js.undefined
}
object GlobOptions {
  
  inline def apply(): GlobOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[GlobOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: GlobOptions] (val x: Self) extends AnyVal {
    
    inline def setCwd(value: String): Self = StObject.set(x, "cwd", value.asInstanceOf[js.Any])
    
    inline def setCwdUndefined: Self = StObject.set(x, "cwd", js.undefined)
    
    inline def setExclude(value: /* fileName */ String => Boolean): Self = StObject.set(x, "exclude", js.Any.fromFunction1(value))
    
    inline def setExcludeUndefined: Self = StObject.set(x, "exclude", js.undefined)
    
    inline def setWithFileTypes(value: Boolean): Self = StObject.set(x, "withFileTypes", value.asInstanceOf[js.Any])
    
    inline def setWithFileTypesUndefined: Self = StObject.set(x, "withFileTypes", js.undefined)
  }
}

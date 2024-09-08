package typings.undiciTypes

import typings.std.Record
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object utilMod {
  
  object util {
    
    @JSImport("undici-types/util", "util")
    @js.native
    val ^ : js.Any = js.native
    
    inline def headerNameToString(value: String): String = ^.asInstanceOf[js.Dynamic].applyDynamic("headerNameToString")(value.asInstanceOf[js.Any]).asInstanceOf[String]
    inline def headerNameToString(
      value: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any
    ): String = ^.asInstanceOf[js.Dynamic].applyDynamic("headerNameToString")(value.asInstanceOf[js.Any]).asInstanceOf[String]
    
    inline def parseHeaders(
      headers: js.Array[
          (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any) | String | (js.Array[
            (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any) | String
          ])
        ]
    ): Record[String, String | js.Array[String]] = ^.asInstanceOf[js.Dynamic].applyDynamic("parseHeaders")(headers.asInstanceOf[js.Any]).asInstanceOf[Record[String, String | js.Array[String]]]
    inline def parseHeaders(
      headers: js.Array[
          (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any) | String | (js.Array[
            (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Buffer */ Any) | String
          ])
        ],
      obj: Record[String, String | js.Array[String]]
    ): Record[String, String | js.Array[String]] = (^.asInstanceOf[js.Dynamic].applyDynamic("parseHeaders")(headers.asInstanceOf[js.Any], obj.asInstanceOf[js.Any])).asInstanceOf[Record[String, String | js.Array[String]]]
  }
}

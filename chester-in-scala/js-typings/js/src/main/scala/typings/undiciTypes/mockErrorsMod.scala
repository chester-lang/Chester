package typings.undiciTypes

import typings.undiciTypes.errorsMod.default.UndiciError
import typings.undiciTypes.undiciTypesStrings.UND_MOCK_ERR_MOCK_NOT_MATCHED
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object mockErrorsMod {
  
  object default {
    
    @JSImport("undici-types/mock-errors", "default.MockNotMatchedError")
    @js.native
    open class MockNotMatchedError ()
      extends typings.undiciTypes.mockErrorsMod.MockErrors.MockNotMatchedError {
      def this(message: String) = this()
    }
  }
  
  object MockErrors {
    
    @js.native
    trait MockNotMatchedError extends UndiciError {
      
      @JSName("code")
      var code_MockNotMatchedError: UND_MOCK_ERR_MOCK_NOT_MATCHED = js.native
      
      @JSName("name")
      var name_MockNotMatchedError: typings.undiciTypes.undiciTypesStrings.MockNotMatchedError = js.native
    }
  }
}

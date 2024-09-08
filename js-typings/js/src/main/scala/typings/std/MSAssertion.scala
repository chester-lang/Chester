package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MSAssertion extends StObject {
  
  /* standard dom */
  val id: java.lang.String
  
  /* standard dom */
  val `type`: MSCredentialType
}
object MSAssertion {
  
  inline def apply(id: java.lang.String, `type`: MSCredentialType): MSAssertion = {
    val __obj = js.Dynamic.literal(id = id.asInstanceOf[js.Any])
    __obj.updateDynamic("type")(`type`.asInstanceOf[js.Any])
    __obj.asInstanceOf[MSAssertion]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MSAssertion] (val x: Self) extends AnyVal {
    
    inline def setId(value: java.lang.String): Self = StObject.set(x, "id", value.asInstanceOf[js.Any])
    
    inline def setType(value: MSCredentialType): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
  }
}

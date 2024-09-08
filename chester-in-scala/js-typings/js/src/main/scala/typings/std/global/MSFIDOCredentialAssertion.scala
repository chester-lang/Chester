package typings.std.global

import org.scalajs.dom.Algorithm
import typings.std.MSCredentialType
import typings.std.MSTransportType
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("MSFIDOCredentialAssertion")
@js.native
/* standard dom */
open class MSFIDOCredentialAssertion ()
  extends StObject
     with typings.std.MSFIDOCredentialAssertion {
  
  /* standard dom */
  /* CompleteClass */
  override val algorithm: java.lang.String | Algorithm = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val attestation: Any = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val id: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val publicKey: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val transportHints: js.Array[MSTransportType] = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val `type`: MSCredentialType = js.native
}

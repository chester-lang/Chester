package typings.std.global

import typings.std.MSWebViewPermissionState
import typings.std.MSWebViewPermissionType
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("PermissionRequest")
@js.native
/* standard dom */
open class PermissionRequest ()
  extends StObject
     with typings.std.PermissionRequest {
  
  /* standard dom */
  /* CompleteClass */
  override def allow(): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def defer(): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def deny(): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val id: Double = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val state: MSWebViewPermissionState = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val `type`: MSWebViewPermissionType = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val uri: java.lang.String = js.native
}

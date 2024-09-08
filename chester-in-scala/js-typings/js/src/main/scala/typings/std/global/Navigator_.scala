package typings.std.global

import typings.std.ConfirmSiteSpecificExceptionsInformation
import typings.std.ExceptionInformation
import typings.std.Navigator
import typings.std.StoreExceptionsInformation
import typings.std.StoreSiteSpecificExceptionsInformation
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("Navigator")
@js.native
/* standard dom */
open class Navigator_ ()
  extends StObject
     with Navigator {
  
  /* standard dom */
  /* CompleteClass */
  override val appCodeName: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val appName: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val appVersion: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def confirmSiteSpecificTrackingException(args: ConfirmSiteSpecificExceptionsInformation): scala.Boolean = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def confirmWebWideTrackingException(args: ExceptionInformation): scala.Boolean = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val cookieEnabled: scala.Boolean = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val hardwareConcurrency: Double = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def javaEnabled(): scala.Boolean = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val language: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val languages: js.Array[java.lang.String] = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val mimeTypes: typings.std.MimeTypeArray = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val onLine: scala.Boolean = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val platform: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val plugins: typings.std.PluginArray = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val product: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val productSub: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def registerProtocolHandler(scheme: java.lang.String, url: java.lang.String, title: java.lang.String): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def removeSiteSpecificTrackingException(args: ExceptionInformation): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def removeWebWideTrackingException(args: ExceptionInformation): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val storage: typings.std.StorageManager = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def storeSiteSpecificTrackingException(args: StoreSiteSpecificExceptionsInformation): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def storeWebWideTrackingException(args: StoreExceptionsInformation): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def unregisterProtocolHandler(scheme: java.lang.String, url: java.lang.String): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val userAgent: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val vendor: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val vendorSub: java.lang.String = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val webdriver: scala.Boolean = js.native
}

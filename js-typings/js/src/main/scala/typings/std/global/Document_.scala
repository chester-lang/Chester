package typings.std.global

import org.scalajs.dom.EventListenerOptions
import typings.std.AddEventListenerOptions
import typings.std.Document
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("Document")
@js.native
/* standard dom */
open class Document_ ()
  extends StObject
     with Document {
  
  /* standard dom */
  /* CompleteClass */
  override val activeElement: org.scalajs.dom.Element | Null = js.native
  
  /* InferMemberOverrides */
  override def addEventListener(`type`: java.lang.String, listener: EventListenerOrEventListenerObject): Unit = js.native
  /* InferMemberOverrides */
  override def addEventListener(`type`: java.lang.String, listener: EventListenerOrEventListenerObject, options: scala.Boolean): Unit = js.native
  /* InferMemberOverrides */
  override def addEventListener(
    `type`: java.lang.String,
    listener: EventListenerOrEventListenerObject,
    options: AddEventListenerOptions
  ): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def caretPositionFromPoint(x: Double, y: Double): typings.std.CaretPosition | Null = js.native
  
  /** @deprecated */
  /* standard dom */
  /* CompleteClass */
  override def caretRangeFromPoint(x: Double, y: Double): org.scalajs.dom.Range = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def elementFromPoint(x: Double, y: Double): org.scalajs.dom.Element | Null = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def elementsFromPoint(x: Double, y: Double): js.Array[org.scalajs.dom.Element] = js.native
  
  /**
    * Returns document's fullscreen element.
    */
  /* standard dom */
  /* CompleteClass */
  override val fullscreenElement: org.scalajs.dom.Element | Null = js.native
  
  /**
    * Returns the first element within node's descendants whose ID is elementId.
    */
  /* standard dom */
  /* CompleteClass */
  override def getElementById(elementId: java.lang.String): org.scalajs.dom.Element | Null = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def getSelection(): org.scalajs.dom.Selection | Null = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val pointerLockElement: org.scalajs.dom.Element | Null = js.native
  
  /* InferMemberOverrides */
  override def removeEventListener(`type`: java.lang.String, callback: EventListenerOrEventListenerObject): Unit = js.native
  /* InferMemberOverrides */
  override def removeEventListener(
    `type`: java.lang.String,
    callback: EventListenerOrEventListenerObject,
    options: EventListenerOptions
  ): Unit = js.native
  /* InferMemberOverrides */
  override def removeEventListener(`type`: java.lang.String, callback: EventListenerOrEventListenerObject, options: scala.Boolean): Unit = js.native
  
  /**
    * Retrieves a collection of styleSheet objects representing the style sheets that correspond to each instance of a link or style object in the document.
    */
  /* standard dom */
  /* CompleteClass */
  override val styleSheets: org.scalajs.dom.StyleSheetList = js.native
}

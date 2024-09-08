package typings.std.global

import org.scalajs.dom.EventListenerOptions
import typings.std.AddEventListenerOptions
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("SVGMarkerElement")
@js.native
/* standard dom */
open class SVGMarkerElement ()
  extends StObject
     with typings.std.SVGMarkerElement {
  
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
  override val assignedSlot: typings.std.HTMLSlotElement | Null = js.native
  
  /* standard dom */
  /* CompleteClass */
  var innerHTML: java.lang.String = js.native
  
  /**
    * Returns the first following sibling that is an element, and null otherwise.
    */
  /* standard dom */
  /* CompleteClass */
  override val nextElementSibling: org.scalajs.dom.Element | Null = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val preserveAspectRatio: org.scalajs.dom.SVGAnimatedPreserveAspectRatio = js.native
  
  /**
    * Returns the first preceding sibling that is an element, and null otherwise.
    */
  /* standard dom */
  /* CompleteClass */
  override val previousElementSibling: org.scalajs.dom.Element | Null = js.native
  
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
  
  /* standard dom */
  /* CompleteClass */
  override val style: org.scalajs.dom.CSSStyleDeclaration = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val viewBox: org.scalajs.dom.SVGAnimatedRect = js.native
}
object SVGMarkerElement {
  
  /* standard dom */
  @JSGlobal("SVGMarkerElement.SVG_MARKERUNITS_STROKEWIDTH")
  @js.native
  val SVG_MARKERUNITS_STROKEWIDTH: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGMarkerElement.SVG_MARKERUNITS_UNKNOWN")
  @js.native
  val SVG_MARKERUNITS_UNKNOWN: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGMarkerElement.SVG_MARKERUNITS_USERSPACEONUSE")
  @js.native
  val SVG_MARKERUNITS_USERSPACEONUSE: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGMarkerElement.SVG_MARKER_ORIENT_ANGLE")
  @js.native
  val SVG_MARKER_ORIENT_ANGLE: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGMarkerElement.SVG_MARKER_ORIENT_AUTO")
  @js.native
  val SVG_MARKER_ORIENT_AUTO: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGMarkerElement.SVG_MARKER_ORIENT_UNKNOWN")
  @js.native
  val SVG_MARKER_ORIENT_UNKNOWN: Double = js.native
}

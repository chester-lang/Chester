package typings.std.global

import org.scalajs.dom.EventListenerOptions
import typings.std.AddEventListenerOptions
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("SVGFEMorphologyElement")
@js.native
/* standard dom */
open class SVGFEMorphologyElement ()
  extends StObject
     with typings.std.SVGFEMorphologyElement {
  
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
  override val height: org.scalajs.dom.SVGAnimatedLength = js.native
  
  /* standard dom */
  /* CompleteClass */
  var innerHTML: java.lang.String = js.native
  
  /**
    * Returns the first following sibling that is an element, and null otherwise.
    */
  /* standard dom */
  /* CompleteClass */
  override val nextElementSibling: org.scalajs.dom.Element | Null = js.native
  
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
  override val result: org.scalajs.dom.SVGAnimatedString = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val style: org.scalajs.dom.CSSStyleDeclaration = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val width: org.scalajs.dom.SVGAnimatedLength = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val x: org.scalajs.dom.SVGAnimatedLength = js.native
  
  /* standard dom */
  /* CompleteClass */
  override val y: org.scalajs.dom.SVGAnimatedLength = js.native
}
object SVGFEMorphologyElement {
  
  /* standard dom */
  @JSGlobal("SVGFEMorphologyElement.SVG_MORPHOLOGY_OPERATOR_DILATE")
  @js.native
  val SVG_MORPHOLOGY_OPERATOR_DILATE: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGFEMorphologyElement.SVG_MORPHOLOGY_OPERATOR_ERODE")
  @js.native
  val SVG_MORPHOLOGY_OPERATOR_ERODE: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGFEMorphologyElement.SVG_MORPHOLOGY_OPERATOR_UNKNOWN")
  @js.native
  val SVG_MORPHOLOGY_OPERATOR_UNKNOWN: Double = js.native
}

package typings.std.global

import org.scalajs.dom.EventListenerOptions
import typings.std.AddEventListenerOptions
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("SVGFEColorMatrixElement")
@js.native
/* standard dom */
open class SVGFEColorMatrixElement ()
  extends StObject
     with typings.std.SVGFEColorMatrixElement {
  
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
object SVGFEColorMatrixElement {
  
  /* standard dom */
  @JSGlobal("SVGFEColorMatrixElement.SVG_FECOLORMATRIX_TYPE_HUEROTATE")
  @js.native
  val SVG_FECOLORMATRIX_TYPE_HUEROTATE: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGFEColorMatrixElement.SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA")
  @js.native
  val SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGFEColorMatrixElement.SVG_FECOLORMATRIX_TYPE_MATRIX")
  @js.native
  val SVG_FECOLORMATRIX_TYPE_MATRIX: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGFEColorMatrixElement.SVG_FECOLORMATRIX_TYPE_SATURATE")
  @js.native
  val SVG_FECOLORMATRIX_TYPE_SATURATE: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGFEColorMatrixElement.SVG_FECOLORMATRIX_TYPE_UNKNOWN")
  @js.native
  val SVG_FECOLORMATRIX_TYPE_UNKNOWN: Double = js.native
}

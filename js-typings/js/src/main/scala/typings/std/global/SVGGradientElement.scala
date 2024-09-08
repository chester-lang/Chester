package typings.std.global

import org.scalajs.dom.EventListenerOptions
import typings.std.AddEventListenerOptions
import typings.std.EventListenerOrEventListenerObject
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("SVGGradientElement")
@js.native
/* standard dom */
open class SVGGradientElement ()
  extends StObject
     with typings.std.SVGGradientElement {
  
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
  override val href: org.scalajs.dom.SVGAnimatedString = js.native
  
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
  override val style: org.scalajs.dom.CSSStyleDeclaration = js.native
}
object SVGGradientElement {
  
  /* standard dom */
  @JSGlobal("SVGGradientElement.SVG_SPREADMETHOD_PAD")
  @js.native
  val SVG_SPREADMETHOD_PAD: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGGradientElement.SVG_SPREADMETHOD_REFLECT")
  @js.native
  val SVG_SPREADMETHOD_REFLECT: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGGradientElement.SVG_SPREADMETHOD_REPEAT")
  @js.native
  val SVG_SPREADMETHOD_REPEAT: Double = js.native
  
  /* standard dom */
  @JSGlobal("SVGGradientElement.SVG_SPREADMETHOD_UNKNOWN")
  @js.native
  val SVG_SPREADMETHOD_UNKNOWN: Double = js.native
}

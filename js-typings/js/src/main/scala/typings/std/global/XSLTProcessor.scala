package typings.std.global

import org.scalajs.dom.Document
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
@JSGlobal("XSLTProcessor")
@js.native
/* standard dom */
open class XSLTProcessor ()
  extends StObject
     with typings.std.XSLTProcessor {
  
  /* standard dom */
  /* CompleteClass */
  override def clearParameters(): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def getParameter(namespaceURI: java.lang.String, localName: java.lang.String): Any = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def importStylesheet(style: org.scalajs.dom.Node): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def removeParameter(namespaceURI: java.lang.String, localName: java.lang.String): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def reset(): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def setParameter(namespaceURI: java.lang.String, localName: java.lang.String, value: Any): Unit = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def transformToDocument(source: org.scalajs.dom.Node): Document = js.native
  
  /* standard dom */
  /* CompleteClass */
  override def transformToFragment(source: org.scalajs.dom.Node, document: Document): org.scalajs.dom.DocumentFragment = js.native
}

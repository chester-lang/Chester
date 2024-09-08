package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** An XSLTProcessor applies an XSLT stylesheet transformation to an XML document to produce a new XML document as output. It has methods to load the XSLT stylesheet, to manipulate <xsl:param> parameter values, and to apply the transformation to documents. */
trait XSLTProcessor extends StObject {
  
  /* standard dom */
  def clearParameters(): Unit
  
  /* standard dom */
  def getParameter(namespaceURI: java.lang.String, localName: java.lang.String): Any
  
  /* standard dom */
  def importStylesheet(style: org.scalajs.dom.Node): Unit
  
  /* standard dom */
  def removeParameter(namespaceURI: java.lang.String, localName: java.lang.String): Unit
  
  /* standard dom */
  def reset(): Unit
  
  /* standard dom */
  def setParameter(namespaceURI: java.lang.String, localName: java.lang.String, value: Any): Unit
  
  /* standard dom */
  def transformToDocument(source: org.scalajs.dom.Node): org.scalajs.dom.Document
  
  /* standard dom */
  def transformToFragment(source: org.scalajs.dom.Node, document: org.scalajs.dom.Document): org.scalajs.dom.DocumentFragment
}
object XSLTProcessor {
  
  inline def apply(
    clearParameters: () => Unit,
    getParameter: (java.lang.String, java.lang.String) => Any,
    importStylesheet: org.scalajs.dom.Node => Unit,
    removeParameter: (java.lang.String, java.lang.String) => Unit,
    reset: () => Unit,
    setParameter: (java.lang.String, java.lang.String, Any) => Unit,
    transformToDocument: org.scalajs.dom.Node => org.scalajs.dom.Document,
    transformToFragment: (org.scalajs.dom.Node, org.scalajs.dom.Document) => org.scalajs.dom.DocumentFragment
  ): XSLTProcessor = {
    val __obj = js.Dynamic.literal(clearParameters = js.Any.fromFunction0(clearParameters), getParameter = js.Any.fromFunction2(getParameter), importStylesheet = js.Any.fromFunction1(importStylesheet), removeParameter = js.Any.fromFunction2(removeParameter), reset = js.Any.fromFunction0(reset), setParameter = js.Any.fromFunction3(setParameter), transformToDocument = js.Any.fromFunction1(transformToDocument), transformToFragment = js.Any.fromFunction2(transformToFragment))
    __obj.asInstanceOf[XSLTProcessor]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: XSLTProcessor] (val x: Self) extends AnyVal {
    
    inline def setClearParameters(value: () => Unit): Self = StObject.set(x, "clearParameters", js.Any.fromFunction0(value))
    
    inline def setGetParameter(value: (java.lang.String, java.lang.String) => Any): Self = StObject.set(x, "getParameter", js.Any.fromFunction2(value))
    
    inline def setImportStylesheet(value: org.scalajs.dom.Node => Unit): Self = StObject.set(x, "importStylesheet", js.Any.fromFunction1(value))
    
    inline def setRemoveParameter(value: (java.lang.String, java.lang.String) => Unit): Self = StObject.set(x, "removeParameter", js.Any.fromFunction2(value))
    
    inline def setReset(value: () => Unit): Self = StObject.set(x, "reset", js.Any.fromFunction0(value))
    
    inline def setSetParameter(value: (java.lang.String, java.lang.String, Any) => Unit): Self = StObject.set(x, "setParameter", js.Any.fromFunction3(value))
    
    inline def setTransformToDocument(value: org.scalajs.dom.Node => org.scalajs.dom.Document): Self = StObject.set(x, "transformToDocument", js.Any.fromFunction1(value))
    
    inline def setTransformToFragment(value: (org.scalajs.dom.Node, org.scalajs.dom.Document) => org.scalajs.dom.DocumentFragment): Self = StObject.set(x, "transformToFragment", js.Any.fromFunction2(value))
  }
}

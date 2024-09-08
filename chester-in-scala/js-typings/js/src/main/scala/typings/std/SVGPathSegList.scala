package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait SVGPathSegList extends StObject {
  
  /* standard dom */
  def appendItem(newItem: org.scalajs.dom.SVGPathSeg): org.scalajs.dom.SVGPathSeg
  
  /* standard dom */
  def clear(): Unit
  
  /* standard dom */
  def getItem(index: Double): org.scalajs.dom.SVGPathSeg
  
  /* standard dom */
  def initialize(newItem: org.scalajs.dom.SVGPathSeg): org.scalajs.dom.SVGPathSeg
  
  /* standard dom */
  def insertItemBefore(newItem: org.scalajs.dom.SVGPathSeg, index: Double): org.scalajs.dom.SVGPathSeg
  
  /* standard dom */
  val numberOfItems: Double
  
  /* standard dom */
  def removeItem(index: Double): org.scalajs.dom.SVGPathSeg
  
  /* standard dom */
  def replaceItem(newItem: org.scalajs.dom.SVGPathSeg, index: Double): org.scalajs.dom.SVGPathSeg
}
object SVGPathSegList {
  
  inline def apply(
    appendItem: org.scalajs.dom.SVGPathSeg => org.scalajs.dom.SVGPathSeg,
    clear: () => Unit,
    getItem: Double => org.scalajs.dom.SVGPathSeg,
    initialize: org.scalajs.dom.SVGPathSeg => org.scalajs.dom.SVGPathSeg,
    insertItemBefore: (org.scalajs.dom.SVGPathSeg, Double) => org.scalajs.dom.SVGPathSeg,
    numberOfItems: Double,
    removeItem: Double => org.scalajs.dom.SVGPathSeg,
    replaceItem: (org.scalajs.dom.SVGPathSeg, Double) => org.scalajs.dom.SVGPathSeg
  ): SVGPathSegList = {
    val __obj = js.Dynamic.literal(appendItem = js.Any.fromFunction1(appendItem), clear = js.Any.fromFunction0(clear), getItem = js.Any.fromFunction1(getItem), initialize = js.Any.fromFunction1(initialize), insertItemBefore = js.Any.fromFunction2(insertItemBefore), numberOfItems = numberOfItems.asInstanceOf[js.Any], removeItem = js.Any.fromFunction1(removeItem), replaceItem = js.Any.fromFunction2(replaceItem))
    __obj.asInstanceOf[SVGPathSegList]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: SVGPathSegList] (val x: Self) extends AnyVal {
    
    inline def setAppendItem(value: org.scalajs.dom.SVGPathSeg => org.scalajs.dom.SVGPathSeg): Self = StObject.set(x, "appendItem", js.Any.fromFunction1(value))
    
    inline def setClear(value: () => Unit): Self = StObject.set(x, "clear", js.Any.fromFunction0(value))
    
    inline def setGetItem(value: Double => org.scalajs.dom.SVGPathSeg): Self = StObject.set(x, "getItem", js.Any.fromFunction1(value))
    
    inline def setInitialize(value: org.scalajs.dom.SVGPathSeg => org.scalajs.dom.SVGPathSeg): Self = StObject.set(x, "initialize", js.Any.fromFunction1(value))
    
    inline def setInsertItemBefore(value: (org.scalajs.dom.SVGPathSeg, Double) => org.scalajs.dom.SVGPathSeg): Self = StObject.set(x, "insertItemBefore", js.Any.fromFunction2(value))
    
    inline def setNumberOfItems(value: Double): Self = StObject.set(x, "numberOfItems", value.asInstanceOf[js.Any])
    
    inline def setRemoveItem(value: Double => org.scalajs.dom.SVGPathSeg): Self = StObject.set(x, "removeItem", js.Any.fromFunction1(value))
    
    inline def setReplaceItem(value: (org.scalajs.dom.SVGPathSeg, Double) => org.scalajs.dom.SVGPathSeg): Self = StObject.set(x, "replaceItem", js.Any.fromFunction2(value))
  }
}

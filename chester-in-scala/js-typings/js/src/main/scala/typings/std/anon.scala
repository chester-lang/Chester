package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object anon {
  
  @js.native
  trait `0` extends StObject {
    
    /* standard es2015.symbol.wellknown */
    @JSName(js.Symbol.replace)
    var replace: js.Function2[
        /* string */ java.lang.String, 
        /* replacer */ js.Function2[/* substring */ java.lang.String, /* repeated */ Any, java.lang.String], 
        java.lang.String
      ] = js.native
  }
  
  trait CopyWithin extends StObject {
    
    /* standard es2015.symbol.wellknown */
    var copyWithin: scala.Boolean
    
    /* standard es2015.symbol.wellknown */
    var entries: scala.Boolean
    
    /* standard es2015.symbol.wellknown */
    var fill: scala.Boolean
    
    /* standard es2015.symbol.wellknown */
    var find: scala.Boolean
    
    /* standard es2015.symbol.wellknown */
    var findIndex: scala.Boolean
    
    /* standard es2015.symbol.wellknown */
    var keys: scala.Boolean
    
    /* standard es2015.symbol.wellknown */
    var values: scala.Boolean
  }
  object CopyWithin {
    
    inline def apply(
      copyWithin: scala.Boolean,
      entries: scala.Boolean,
      fill: scala.Boolean,
      find: scala.Boolean,
      findIndex: scala.Boolean,
      keys: scala.Boolean,
      values: scala.Boolean
    ): CopyWithin = {
      val __obj = js.Dynamic.literal(copyWithin = copyWithin.asInstanceOf[js.Any], entries = entries.asInstanceOf[js.Any], fill = fill.asInstanceOf[js.Any], find = find.asInstanceOf[js.Any], findIndex = findIndex.asInstanceOf[js.Any], keys = keys.asInstanceOf[js.Any], values = values.asInstanceOf[js.Any])
      __obj.asInstanceOf[CopyWithin]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: CopyWithin] (val x: Self) extends AnyVal {
      
      inline def setCopyWithin(value: scala.Boolean): Self = StObject.set(x, "copyWithin", value.asInstanceOf[js.Any])
      
      inline def setEntries(value: scala.Boolean): Self = StObject.set(x, "entries", value.asInstanceOf[js.Any])
      
      inline def setFill(value: scala.Boolean): Self = StObject.set(x, "fill", value.asInstanceOf[js.Any])
      
      inline def setFind(value: scala.Boolean): Self = StObject.set(x, "find", value.asInstanceOf[js.Any])
      
      inline def setFindIndex(value: scala.Boolean): Self = StObject.set(x, "findIndex", value.asInstanceOf[js.Any])
      
      inline def setKeys(value: scala.Boolean): Self = StObject.set(x, "keys", value.asInstanceOf[js.Any])
      
      inline def setValues(value: scala.Boolean): Self = StObject.set(x, "values", value.asInstanceOf[js.Any])
    }
  }
  
  trait Item[T] extends StObject {
    
    /* standard scripthost */
    def Item(index: Any): T
  }
  object Item {
    
    inline def apply[T](Item: Any => T): Item[T] = {
      val __obj = js.Dynamic.literal(Item = js.Any.fromFunction1(Item))
      __obj.asInstanceOf[Item[T]]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: Item[?], T] (val x: Self & Item[T]) extends AnyVal {
      
      inline def setItem(value: Any => T): Self = StObject.set(x, "Item", js.Any.fromFunction1(value))
    }
  }
  
  trait Length extends StObject {
    
    /* standard scripthost */
    def Item(n: Double): java.lang.String
    
    /* standard scripthost */
    var length: Double
  }
  object Length {
    
    inline def apply(Item: Double => java.lang.String, length: Double): Length = {
      val __obj = js.Dynamic.literal(Item = js.Any.fromFunction1(Item), length = length.asInstanceOf[js.Any])
      __obj.asInstanceOf[Length]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: Length] (val x: Self) extends AnyVal {
      
      inline def setItem(value: Double => java.lang.String): Self = StObject.set(x, "Item", js.Any.fromFunction1(value))
      
      inline def setLength(value: Double): Self = StObject.set(x, "length", value.asInstanceOf[js.Any])
    }
  }
  
  @js.native
  trait LookupNamespaceURI extends StObject {
    
    /* standard dom */
    def lookupNamespaceURI(): java.lang.String | Null = js.native
    def lookupNamespaceURI(prefix: java.lang.String): java.lang.String | Null = js.native
  }
  
  @js.native
  trait Match extends StObject {
    
    /* standard es2015.symbol.wellknown */
    @JSName(js.Symbol.`match`)
    var `match`: js.Function1[/* string */ java.lang.String, RegExpMatchArray | Null] = js.native
  }
  
  trait Proxy[T /* <: js.Object */] extends StObject {
    
    /* standard es2015.proxy */
    var proxy: T
    
    /* standard es2015.proxy */
    def revoke(): Unit
  }
  object Proxy {
    
    inline def apply[T /* <: js.Object */](proxy: T, revoke: () => Unit): Proxy[T] = {
      val __obj = js.Dynamic.literal(proxy = proxy.asInstanceOf[js.Any], revoke = js.Any.fromFunction0(revoke))
      __obj.asInstanceOf[Proxy[T]]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: Proxy[?], T /* <: js.Object */] (val x: Self & Proxy[T]) extends AnyVal {
      
      inline def setProxy(value: T): Self = StObject.set(x, "proxy", value.asInstanceOf[js.Any])
      
      inline def setRevoke(value: () => Unit): Self = StObject.set(x, "revoke", js.Any.fromFunction0(value))
    }
  }
  
  @js.native
  trait Replace extends StObject {
    
    /* standard es2015.symbol.wellknown */
    @JSName(js.Symbol.replace)
    var replace: js.Function2[/* string */ java.lang.String, /* replaceValue */ java.lang.String, java.lang.String] = js.native
  }
  
  @js.native
  trait Search extends StObject {
    
    /* standard es2015.symbol.wellknown */
    @JSName(js.Symbol.search)
    var search: js.Function1[/* string */ java.lang.String, Double] = js.native
  }
  
  @js.native
  trait Split extends StObject {
    
    /* standard es2015.symbol.wellknown */
    @JSName(js.Symbol.split)
    var split: js.Function2[
        /* string */ java.lang.String, 
        /* limit */ js.UndefOr[Double], 
        js.Array[java.lang.String]
      ] = js.native
  }
}

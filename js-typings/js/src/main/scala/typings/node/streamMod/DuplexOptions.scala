package typings.node.streamMod

import typings.node.anon.Chunk
import typings.node.bufferMod.global.BufferEncoding
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* import warning: transforms.RemoveMultipleInheritance#findNewParents newComments Dropped parents 
- typings.node.eventsMod.Abortable because Already inherited
- typings.node.streamMod.StreamOptions because Already inherited
- typings.node.streamMod.WritableOptions because var conflicts: autoDestroy, construct, destroy, emitClose, highWaterMark, objectMode, signal. Inlined decodeStrings, defaultEncoding, write, writev, `final` */ trait DuplexOptions
  extends StObject
     with ReadableOptions {
  
  var allowHalfOpen: js.UndefOr[Boolean] = js.undefined
  
  @JSName("construct")
  var construct_DuplexOptions: js.UndefOr[
    js.ThisFunction1[
      /* this */ Duplex, 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var decodeStrings: js.UndefOr[Boolean] = js.undefined
  
  var defaultEncoding: js.UndefOr[BufferEncoding] = js.undefined
  
  @JSName("destroy")
  var destroy_DuplexOptions: js.UndefOr[
    js.ThisFunction2[
      /* this */ Duplex, 
      /* error */ js.Error | Null, 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var `final`: js.UndefOr[
    js.ThisFunction1[
      (/* this */ Duplex) | (/* this */ Writable), 
      /* callback */ js.Function1[js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  @JSName("read")
  var read_DuplexOptions: js.UndefOr[js.ThisFunction1[/* this */ Duplex, /* size */ Double, Unit]] = js.undefined
  
  var readableHighWaterMark: js.UndefOr[Double] = js.undefined
  
  var readableObjectMode: js.UndefOr[Boolean] = js.undefined
  
  var writableCorked: js.UndefOr[Double] = js.undefined
  
  var writableHighWaterMark: js.UndefOr[Double] = js.undefined
  
  var writableObjectMode: js.UndefOr[Boolean] = js.undefined
  
  var write: js.UndefOr[
    js.ThisFunction3[
      (/* this */ Duplex) | (/* this */ Writable), 
      /* chunk */ Any, 
      /* encoding */ BufferEncoding, 
      /* callback */ js.Function1[js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var writev: js.UndefOr[
    js.ThisFunction2[
      (/* this */ Duplex) | (/* this */ Writable), 
      /* chunks */ js.Array[Chunk], 
      /* callback */ js.Function1[js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
}
object DuplexOptions {
  
  inline def apply(): DuplexOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[DuplexOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DuplexOptions] (val x: Self) extends AnyVal {
    
    inline def setAllowHalfOpen(value: Boolean): Self = StObject.set(x, "allowHalfOpen", value.asInstanceOf[js.Any])
    
    inline def setAllowHalfOpenUndefined: Self = StObject.set(x, "allowHalfOpen", js.undefined)
    
    inline def setConstruct(
      value: js.ThisFunction1[
          /* this */ Duplex, 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "construct", value.asInstanceOf[js.Any])
    
    inline def setConstructUndefined: Self = StObject.set(x, "construct", js.undefined)
    
    inline def setDecodeStrings(value: Boolean): Self = StObject.set(x, "decodeStrings", value.asInstanceOf[js.Any])
    
    inline def setDecodeStringsUndefined: Self = StObject.set(x, "decodeStrings", js.undefined)
    
    inline def setDefaultEncoding(value: BufferEncoding): Self = StObject.set(x, "defaultEncoding", value.asInstanceOf[js.Any])
    
    inline def setDefaultEncodingUndefined: Self = StObject.set(x, "defaultEncoding", js.undefined)
    
    inline def setDestroy(
      value: js.ThisFunction2[
          /* this */ Duplex, 
          /* error */ js.Error | Null, 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "destroy", value.asInstanceOf[js.Any])
    
    inline def setDestroyUndefined: Self = StObject.set(x, "destroy", js.undefined)
    
    inline def setFinal(
      value: js.ThisFunction1[
          (/* this */ Duplex) | (/* this */ Writable), 
          /* callback */ js.Function1[js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "final", value.asInstanceOf[js.Any])
    
    inline def setFinalUndefined: Self = StObject.set(x, "final", js.undefined)
    
    inline def setRead(value: js.ThisFunction1[/* this */ Duplex, /* size */ Double, Unit]): Self = StObject.set(x, "read", value.asInstanceOf[js.Any])
    
    inline def setReadUndefined: Self = StObject.set(x, "read", js.undefined)
    
    inline def setReadableHighWaterMark(value: Double): Self = StObject.set(x, "readableHighWaterMark", value.asInstanceOf[js.Any])
    
    inline def setReadableHighWaterMarkUndefined: Self = StObject.set(x, "readableHighWaterMark", js.undefined)
    
    inline def setReadableObjectMode(value: Boolean): Self = StObject.set(x, "readableObjectMode", value.asInstanceOf[js.Any])
    
    inline def setReadableObjectModeUndefined: Self = StObject.set(x, "readableObjectMode", js.undefined)
    
    inline def setWritableCorked(value: Double): Self = StObject.set(x, "writableCorked", value.asInstanceOf[js.Any])
    
    inline def setWritableCorkedUndefined: Self = StObject.set(x, "writableCorked", js.undefined)
    
    inline def setWritableHighWaterMark(value: Double): Self = StObject.set(x, "writableHighWaterMark", value.asInstanceOf[js.Any])
    
    inline def setWritableHighWaterMarkUndefined: Self = StObject.set(x, "writableHighWaterMark", js.undefined)
    
    inline def setWritableObjectMode(value: Boolean): Self = StObject.set(x, "writableObjectMode", value.asInstanceOf[js.Any])
    
    inline def setWritableObjectModeUndefined: Self = StObject.set(x, "writableObjectMode", js.undefined)
    
    inline def setWrite(
      value: js.ThisFunction3[
          (/* this */ Duplex) | (/* this */ Writable), 
          /* chunk */ Any, 
          /* encoding */ BufferEncoding, 
          /* callback */ js.Function1[js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "write", value.asInstanceOf[js.Any])
    
    inline def setWriteUndefined: Self = StObject.set(x, "write", js.undefined)
    
    inline def setWritev(
      value: js.ThisFunction2[
          (/* this */ Duplex) | (/* this */ Writable), 
          /* chunks */ js.Array[Chunk], 
          /* callback */ js.Function1[js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "writev", value.asInstanceOf[js.Any])
    
    inline def setWritevUndefined: Self = StObject.set(x, "writev", js.undefined)
  }
}

package typings.node.streamMod

import typings.node.eventsMod.Abortable
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait StreamOptions[T /* <: Stream */]
  extends StObject
     with Abortable {
  
  var autoDestroy: js.UndefOr[Boolean] = js.undefined
  
  var construct: js.UndefOr[
    js.ThisFunction1[
      /* this */ T, 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var destroy: js.UndefOr[
    js.ThisFunction2[
      /* this */ T, 
      /* error */ js.Error | Null, 
      /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
      Unit
    ]
  ] = js.undefined
  
  var emitClose: js.UndefOr[Boolean] = js.undefined
  
  var highWaterMark: js.UndefOr[Double] = js.undefined
  
  var objectMode: js.UndefOr[Boolean] = js.undefined
}
object StreamOptions {
  
  inline def apply[T /* <: Stream */](): StreamOptions[T] = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[StreamOptions[T]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: StreamOptions[?], T /* <: Stream */] (val x: Self & StreamOptions[T]) extends AnyVal {
    
    inline def setAutoDestroy(value: Boolean): Self = StObject.set(x, "autoDestroy", value.asInstanceOf[js.Any])
    
    inline def setAutoDestroyUndefined: Self = StObject.set(x, "autoDestroy", js.undefined)
    
    inline def setConstruct(
      value: js.ThisFunction1[
          /* this */ T, 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "construct", value.asInstanceOf[js.Any])
    
    inline def setConstructUndefined: Self = StObject.set(x, "construct", js.undefined)
    
    inline def setDestroy(
      value: js.ThisFunction2[
          /* this */ T, 
          /* error */ js.Error | Null, 
          /* callback */ js.Function1[/* error */ js.UndefOr[js.Error | Null], Unit], 
          Unit
        ]
    ): Self = StObject.set(x, "destroy", value.asInstanceOf[js.Any])
    
    inline def setDestroyUndefined: Self = StObject.set(x, "destroy", js.undefined)
    
    inline def setEmitClose(value: Boolean): Self = StObject.set(x, "emitClose", value.asInstanceOf[js.Any])
    
    inline def setEmitCloseUndefined: Self = StObject.set(x, "emitClose", js.undefined)
    
    inline def setHighWaterMark(value: Double): Self = StObject.set(x, "highWaterMark", value.asInstanceOf[js.Any])
    
    inline def setHighWaterMarkUndefined: Self = StObject.set(x, "highWaterMark", js.undefined)
    
    inline def setObjectMode(value: Boolean): Self = StObject.set(x, "objectMode", value.asInstanceOf[js.Any])
    
    inline def setObjectModeUndefined: Self = StObject.set(x, "objectMode", js.undefined)
  }
}

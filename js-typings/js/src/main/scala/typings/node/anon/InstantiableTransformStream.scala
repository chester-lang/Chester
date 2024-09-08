package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import org.scalablytyped.runtime.Instantiable3
import typings.node.streamWebMod.QueuingStrategy
import typings.node.streamWebMod.TransformStream
import typings.node.streamWebMod.Transformer
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait InstantiableTransformStream
  extends StObject
     with Instantiable0[TransformStream[js.Object, js.Object]]
     with Instantiable1[
      /* transformer */ Transformer[js.Object, js.Object], 
      TransformStream[js.Object, js.Object]
    ]
     with Instantiable2[
      (/* transformer */ Transformer[js.Object, js.Object]) | (/* transformer */ Unit), 
      /* writableStrategy */ QueuingStrategy[js.Object], 
      TransformStream[js.Object, js.Object]
    ]
     with Instantiable3[
      (/* transformer */ Transformer[js.Object, js.Object]) | (/* transformer */ Unit), 
      (/* writableStrategy */ QueuingStrategy[js.Object]) | (/* writableStrategy */ Unit), 
      /* readableStrategy */ QueuingStrategy[js.Object], 
      TransformStream[js.Object, js.Object]
    ]

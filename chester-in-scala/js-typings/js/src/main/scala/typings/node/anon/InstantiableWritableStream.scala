package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import typings.node.streamWebMod.QueuingStrategy
import typings.node.streamWebMod.UnderlyingSink
import typings.node.streamWebMod.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait InstantiableWritableStream
  extends StObject
     with Instantiable0[WritableStream[js.Object]]
     with Instantiable1[/* underlyingSink */ UnderlyingSink[js.Object], WritableStream[js.Object]]
     with Instantiable2[
      (/* underlyingSink */ UnderlyingSink[js.Object]) | (/* underlyingSink */ Unit), 
      /* strategy */ QueuingStrategy[js.Object], 
      WritableStream[js.Object]
    ]

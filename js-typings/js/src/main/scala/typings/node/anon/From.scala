package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import typings.node.streamWebMod.QueuingStrategy
import typings.node.streamWebMod.ReadableStream
import typings.node.streamWebMod.UnderlyingByteSource
import typings.node.streamWebMod.UnderlyingSource
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait From
  extends StObject
     with Instantiable0[ReadableStream[js.Object]]
     with Instantiable1[
      (/* underlyingSource */ UnderlyingByteSource) | (/* underlyingSource */ UnderlyingSource[js.Object]), 
      ReadableStream[js.Object | js.typedarray.Uint8Array]
    ]
     with Instantiable2[
      (/* underlyingSource */ UnderlyingByteSource) | (/* underlyingSource */ UnderlyingSource[js.Object]) | (/* underlyingSource */ Unit), 
      /* strategy */ QueuingStrategy[js.Object | js.typedarray.Uint8Array], 
      ReadableStream[js.Object | js.typedarray.Uint8Array]
    ] {
  
  def from[T](iterable: js.Iterable[T]): ReadableStream[T] = js.native
  def from[T](
    iterable: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ Any
  ): ReadableStream[T] = js.native
}

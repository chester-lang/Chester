package typings.node.streamWebMod

import typings.node.nodeStrings.`deflate-raw`
import typings.node.nodeStrings.deflate
import typings.node.nodeStrings.gzip
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
@JSImport("stream/web", "CompressionStream")
@js.native
open class CompressionStreamCls[R, W] protected ()
  extends StObject
     with CompressionStream[R, W] {
  def this(format: deflate | `deflate-raw` | gzip) = this()
  
  /* CompleteClass */
  override val readable: ReadableStream[R] = js.native
  
  /* CompleteClass */
  override val writable: WritableStream[W] = js.native
}

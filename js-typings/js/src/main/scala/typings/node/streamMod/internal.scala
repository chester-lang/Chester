package typings.node.streamMod

import typings.node.anon.End
import typings.node.anon.Signal
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait internal extends StObject {
  
  def compose[T /* <: ReadableStream */](
    stream: (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ Any) | T
  ): T = js.native
  def compose[T /* <: ReadableStream */](
    stream: (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<T> */ Any) | T,
    options: Signal
  ): T = js.native
  def compose[T /* <: ReadableStream */](stream: js.Iterable[T]): T = js.native
  def compose[T /* <: ReadableStream */](stream: js.Iterable[T], options: Signal): T = js.native
  def compose[T /* <: ReadableStream */](stream: ComposeFnParam): T = js.native
  def compose[T /* <: ReadableStream */](stream: ComposeFnParam, options: Signal): T = js.native
  
  def pipe[T /* <: WritableStream */](destination: T): T = js.native
  def pipe[T /* <: WritableStream */](destination: T, options: End): T = js.native
}

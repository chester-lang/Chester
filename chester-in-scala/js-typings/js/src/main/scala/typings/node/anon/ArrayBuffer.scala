package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.nodeColonbufferMod.Blob
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait ArrayBuffer extends StObject {
  
  def arrayBuffer(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[js.typedarray.ArrayBuffer] = js.native
  def arrayBuffer(stream: ReadableStream): js.Promise[js.typedarray.ArrayBuffer] = js.native
  def arrayBuffer(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[js.typedarray.ArrayBuffer] = js.native
  
  def blob(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[Blob] = js.native
  def blob(stream: ReadableStream): js.Promise[Blob] = js.native
  def blob(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[Blob] = js.native
  
  def buffer(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def buffer(stream: ReadableStream): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def buffer(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  
  def json(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[Any] = js.native
  def json(stream: ReadableStream): js.Promise[Any] = js.native
  def json(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[Any] = js.native
  
  def text(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[String] = js.native
  def text(stream: ReadableStream): js.Promise[String] = js.native
  def text(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[String] = js.native
}

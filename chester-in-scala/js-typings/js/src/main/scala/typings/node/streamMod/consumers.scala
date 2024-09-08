package typings.node.streamMod

import typings.node.bufferMod.global.Buffer
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.nodeColonbufferMod.Blob
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object consumers {
  
  @JSImport("stream", "consumers")
  @js.native
  val ^ : js.Any = js.native
  
  inline def arrayBuffer(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[js.typedarray.ArrayBuffer] = ^.asInstanceOf[js.Dynamic].applyDynamic("arrayBuffer")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[js.typedarray.ArrayBuffer]]
  inline def arrayBuffer(stream: ReadableStream): js.Promise[js.typedarray.ArrayBuffer] = ^.asInstanceOf[js.Dynamic].applyDynamic("arrayBuffer")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[js.typedarray.ArrayBuffer]]
  inline def arrayBuffer(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[js.typedarray.ArrayBuffer] = ^.asInstanceOf[js.Dynamic].applyDynamic("arrayBuffer")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[js.typedarray.ArrayBuffer]]
  
  inline def blob(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[Blob] = ^.asInstanceOf[js.Dynamic].applyDynamic("blob")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Blob]]
  inline def blob(stream: ReadableStream): js.Promise[Blob] = ^.asInstanceOf[js.Dynamic].applyDynamic("blob")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Blob]]
  inline def blob(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[Blob] = ^.asInstanceOf[js.Dynamic].applyDynamic("blob")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Blob]]
  
  inline def buffer(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[Buffer] = ^.asInstanceOf[js.Dynamic].applyDynamic("buffer")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Buffer]]
  inline def buffer(stream: ReadableStream): js.Promise[Buffer] = ^.asInstanceOf[js.Dynamic].applyDynamic("buffer")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Buffer]]
  inline def buffer(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[Buffer] = ^.asInstanceOf[js.Dynamic].applyDynamic("buffer")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Buffer]]
  
  inline def json(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("json")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Any]]
  inline def json(stream: ReadableStream): js.Promise[Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("json")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Any]]
  inline def json(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("json")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Any]]
  
  inline def text(
    stream: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify AsyncIterable<any> */ Any
  ): js.Promise[String] = ^.asInstanceOf[js.Dynamic].applyDynamic("text")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[String]]
  inline def text(stream: ReadableStream): js.Promise[String] = ^.asInstanceOf[js.Dynamic].applyDynamic("text")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[String]]
  inline def text(stream: typings.node.nodeColonstreamMod.Readable): js.Promise[String] = ^.asInstanceOf[js.Dynamic].applyDynamic("text")(stream.asInstanceOf[js.Any]).asInstanceOf[js.Promise[String]]
}
